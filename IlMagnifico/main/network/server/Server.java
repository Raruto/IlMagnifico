package main.network.server;

import java.util.ArrayList;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.exceptions.CreateRoomException;
import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.server.game.Room;
import main.network.server.game.RoomFullException;
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
	public static final int MIN_ROOM_PLAYERS = Costants.MIN_ROOM_PLAYERS;

	/**
	 * Numero massimo di giocatori gestibili da una Partita.
	 */
	public static final int MAX_ROOM_PLAYERS = Costants.MAX_ROOM_PLAYERS;

	/**
	 * MUTEX per evitare la concorrenza tra giocatori durante il login.
	 */
	private static final Object PLAYERS_MUTEX = new Object();

	/**
	 * This object works as mutex to avoid concurrency race between room
	 * joining.
	 */
	private static final Object ROOMS_MUTEX = new Object();

	/**
	 * Giocatori connessi con il server <nickname, RemotePlayer>.
	 */
	private HashMap<String, RemotePlayer> players;

	/**
	 * List of all server room.
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
	private static final String ID = "SERVER";

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
	 *            parametri per la connessione (TODO: FINIRE DI IMPLEMENTARE,
	 *            lato client).
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
	private void startRMIServer(int rmiPort) throws ServerException {
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
	private void startSocketServer(int socketPort) throws ServerException {
		System.out.println("Starting Socket Server...");
		socketServer.startServer(socketPort);
	}

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
			String id = "[" + nickname.toUpperCase() + "]";

			if (!players.containsKey(nickname)) {
				player.setNome(nickname);
				players.put(nickname, player);
				System.out.println(id + " Succesfully logged in!");
				try {
					System.out.println("Trying joining it to a room...");

					joinFirstAvailableRoom(player);
				} catch (JoinRoomException e) {
					// e.printStackTrace();
				}
			} else {
				System.out.println(id + " Already logged in!");
				throw new LoginException();
			}
		}
	}

	/**
	 * Get the player associated to required nickname.
	 * 
	 * @param nickname
	 *            of the player to retrieve.
	 * @return the associated remote player if found.
	 */
	@Override
	public RemotePlayer getPlayer(String nickname) {
		return players.get(nickname);
	}

	/**
	 * Join player to the first available room.
	 * 
	 * @param player
	 *            reference to the player that made the request.
	 * @throws RoomFullException
	 *             if no room is available for the join request.
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
	 * Join player to the first available room.
	 * 
	 * @param remotePlayer
	 *            that would join.
	 * @throws JoinRoomException
	 *             if no available room has been found.
	 */
	@Override
	public void joinFirstAvailableRoom(RemotePlayer remotePlayer) throws JoinRoomException {
		synchronized (ROOMS_MUTEX) {
			try {
				joinLastRoom(remotePlayer);
			} catch (RoomFullException e) {
				try {
					createNewRoom(remotePlayer, MAX_ROOM_PLAYERS, MIN_ROOM_PLAYERS);
					System.out.println("Succesfully created a room!");
				} catch (CreateRoomException e1) {
					e1.printStackTrace();
				}
				throw new JoinRoomException(e);
			}
		}
	}

	/**
	 * Create a new room on server.
	 * 
	 * @param remotePlayer
	 *            that made the request.
	 * @param maxPlayers
	 *            that player would like to add in the room.
	 * @throws CreateRoomException
	 *             if another player has created a new room in the meanwhile.
	 * @return configuration bundle that contains all default configurations.
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

				return /* Configurator.getConfigurationBundle() */;
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
}
