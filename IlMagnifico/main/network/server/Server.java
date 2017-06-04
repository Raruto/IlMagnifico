package main.network.server;

import java.util.ArrayList;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.server.game.RemotePlayer;
import main.network.server.game.Room;
import main.network.server.game.exceptions.CreateRoomException;
import main.network.server.game.exceptions.JoinRoomException;
import main.network.server.game.exceptions.RoomFullException;
import main.network.server.rmi.RMIServer;
import main.network.server.socket.SocketServer;
import main.util.Costants;

/**
 * Server del gioco "Lorenzo Il Magnifico" della "CranioCreations".
 */
public class Server implements IServer {

	/**
	 * Porta in cui è aperta la comunicazione Socket.
	 */
	public static final int SOCKET_PORT = Costants.SOCKET_PORT;

	/**
	 * Porta in cui è aperta la comunicazione RMI.
	 */
	public static final int RMI_PORT = Costants.RMI_PORT;

	/**
	 * Numero minimo di giocatori necessari per creare una Partita.
	 */
	public static final int MIN_ROOM_PLAYERS = Costants.ROOM_MIN_PLAYERS;

	/**
	 * Numero massimo di giocatori gestibili da una Partita.
	 */
	public static final int MAX_ROOM_PLAYERS = Costants.ROOM_MAX_PLAYERS;

	/**
	 * MUTEX per evitare la concorrenza tra giocatori durante il login.
	 */
	private static final Object PLAYERS_MUTEX = new Object();

	/**
	 * MUTEX per evitare la concorrenza tra giocatori durante l'aggiunta ad una
	 * stanza
	 */
	private static final Object ROOMS_MUTEX = new Object();

	/**
	 * Giocatori connessi con il server <nickname, RemotePlayer>.
	 */
	private HashMap<String, RemotePlayer> players;

	/**
	 * Lista di tutte le Stanze presenti sul Server.
	 */
	private ArrayList<Room> rooms;

	/**
	 * Socket server.
	 */
	private SocketServer socketServer;

	/**
	 * RMI server.
	 */
	private RMIServer rmiServer;

	/**
	 * ID usato per identificare il server nelle comunicazioni
	 */
	private static final String ID = Costants.SERVER;

	/**
	 * Crea una nuova istanza della classe.
	 * 
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	public Server() throws ServerException {
		players = new HashMap<>();
		rooms = new ArrayList<>();
		socketServer = new SocketServer(this);
		rmiServer = new RMIServer(this);
	}

	/**
	 * Metodo statico per eseguire il server.
	 * 
	 * @param args
	 *            parametri per la connessione
	 */
	public static void main(String[] args) {
		int socketPort = SOCKET_PORT, rmiPort = RMI_PORT;

		// Check if arguments were passed in
		if (args.length != 0) {
			try {
				socketPort = Integer.parseInt(args[0]);
				rmiPort = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.out.println("Proper usage is: [socketPort rmiPort]");
				System.exit(0);
			}
		}

		try {
			Server server = new Server();
			server.startServer(socketPort, rmiPort);

			System.out.print("\nServer listening at: ");
			System.out.println("127.0.0.1" + " (rmi: " + rmiPort + ", socket: " + socketPort + ")");
			System.out.println();
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Avvia i server per le connessioni.
	 * 
	 * @param socketPort
	 *            porta su cui avviare il server per le connessioni Socket.
	 * @param rmiPort
	 *            Porta in cui avviare il server per le connessioni RMI.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	public void startServer(int socketPort, int rmiPort) throws ServerException {
		startRMIServer(rmiPort);
		startSocketServer(socketPort);
	}

	/**
	 * Avvia il server RMI.
	 * 
	 * @param rmiPort
	 *            porta su cui avviare il server per le connessioni RMI.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	public void startRMIServer(int rmiPort) throws ServerException {
		System.out.println("Starting RMI Server...");
		rmiServer.startServer(rmiPort);
	}

	/**
	 * Avvia il server Socket.
	 * 
	 * @param socketPort
	 *            porta su cui avviare il server per le connessioni Socket.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	public void startSocketServer(int socketPort) throws ServerException {
		System.out.println("Starting Socket Server...");
		socketServer.startServer(socketPort);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati sul Server Controller (vedi RMIServer, SocketServer)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Login del giocatore tramite nickname.
	 * 
	 * @param nickname
	 *            nome con cui il giocatore vorrebbe essere identificato sul
	 *            server.
	 * @param player
	 *            riferimento al giocatore che ha effettuato la richiesta (es.
	 *            {@link RMIPlayer}, {@link SocketPlayer}).
	 * @throws LoginException
	 *             se esiste già un altro giocatore con il nome fornito.
	 */
	@Override
	public void loginPlayer(String nickname, RemotePlayer player) throws LoginException {
		synchronized (PLAYERS_MUTEX) {
			System.out.println("New login request: " + nickname);
			String player_id = "[" + nickname.toUpperCase() + "]";

			if (!players.containsKey(nickname) && nickname.length() > 0) {
				player.setNome(nickname);
				players.put(nickname, player);
				System.out.println(player_id + " Succesfully logged in!");
			} else {
				System.out.println(player_id + " Already logged in!");
				throw new LoginException();
			}
		}
	}

	/**
	 * Ritorna il giocatore associato al nome richiesto.
	 * 
	 * @param nickname
	 *            nome associato al giocatore.
	 * @return il giocatore remoto associato (se trovato).
	 */
	@Override
	public RemotePlayer getPlayer(String nickname) {
		return players.get(nickname);
	}

	/**
	 * Aggiunge il giocatore alla prima Stanza disponibile.
	 * 
	 * @param remotePlayer
	 *            giocatore remoto da aggiungere.
	 * @throws JoinRoomException
	 *             se non è stata trovata alcuna stanza disponibile.
	 */
	@Override
	public void joinFirstAvailableRoom(RemotePlayer remotePlayer) throws JoinRoomException {
		synchronized (ROOMS_MUTEX) {
			try {
				System.out.println("Trying joining it to a room...");
				joinLastRoom(remotePlayer);
			} catch (RoomFullException e) {
				try {
					createNewRoom(remotePlayer, MAX_ROOM_PLAYERS, MIN_ROOM_PLAYERS);
					System.out.println("Succesfully created a room!");
				} catch (CreateRoomException e1) {
					// e1.printStackTrace();
				}
				throw new JoinRoomException(e);
			}
		}
	}

	/**
	 * Aggiunge il giocatore alla prima Stanza disponibile (l'ultima della
	 * lista).
	 * 
	 * @param player
	 *            riferimento al giocatore che ha fatto la richiesta.
	 * @throws RoomFullException
	 *             se la Stanza è non disponibile.
	 */
	private void joinLastRoom(RemotePlayer player) throws RoomFullException {
		Room lastRoom = rooms.isEmpty() ? null : rooms.get(rooms.size() - 1);
		if (lastRoom != null) {
			lastRoom.joinPlayer(player);
			player.setRoom(lastRoom);
		} else {
			throw new RoomFullException("No available room found!");
		}
	}

	/**
	 * Crea una nuova Stanza sul server.
	 * 
	 * @param remotePlayer
	 *            giocatore remoto che ha fatto la richiesta.
	 * @param maxPlayers
	 *            numero massimo di giocatori che stanza dovrebbe gestire.
	 * @throws CreateRoomException
	 *             se nel frattempo un altro giocatore ha creato una nuova
	 *             stanza.
	 */
	@Override
	public void createNewRoom(RemotePlayer remotePlayer, int maxPlayers, int minPlayers) throws CreateRoomException {
		synchronized (ROOMS_MUTEX) {
			boolean hasJoinRoom = false;
			try {
				joinLastRoom(remotePlayer);
				hasJoinRoom = true;
			} catch (RoomFullException e) {
				System.err.println("No room has been created in the meanwhile, Player is going to create his room");
			}
			if (!hasJoinRoom) {
				Room room = new Room(remotePlayer, maxPlayers, minPlayers);
				rooms.add(room);
				remotePlayer.setRoom(room);

				try {
					remotePlayer.onChatMessage(ID,
							"You have succesfully created and joined to room #" + room.getRoomNumber() + "!", true);
				} catch (NetworkException e) {
					e.printStackTrace();
				}
			} else {
				throw new CreateRoomException();
			}
		}
	}

	/**
	 * Invia un messaggio di chat a tutti i giocatori o un giocatore specifico.
	 * 
	 * @param player
	 *            MITTENTE del messaggio.
	 * @param receiver
	 *            nome del DESTINATARIO del messaggio. Se null il messaggio
	 *            verrà inviato a tutti i giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws PlayerNotFound
	 *             se il ricevitore non non corrisponde a nessun giocatore
	 *             presente sul server.
	 */
	@Override
	public void sendChatMessage(RemotePlayer player, String receiver, String message) throws PlayerNotFound {

		String author = player.getNome();
		System.out.println("[" + author.toUpperCase() + "]" + " " + message);

		/* Send a UNICAST message */
		if (receiver != null) {
			for (RemotePlayer remotePlayer : players.values()) {
				if (receiver.equals(remotePlayer.getNome())) {
					try {
						remotePlayer.onChatMessage(author, message, true);
					} catch (NetworkException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			throw new PlayerNotFound();
		}
		/* Send a BROADCAST message */
		else {
			players.entrySet().stream().filter(remotePlayer -> remotePlayer.getValue() != player)
					.forEach(remotePlayer -> {
						try {
							remotePlayer.getValue().onChatMessage(author, message, false);
						} catch (NetworkException e) {
							e.printStackTrace();
						}
					});
		}
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws NetworkException {
		// TODO Auto-generated method stub
	}
}
