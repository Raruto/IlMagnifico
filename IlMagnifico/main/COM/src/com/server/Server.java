package com.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.NetworkException;
import com.exceptions.JoinRoomException;
import com.exceptions.LoginException;
import com.server.game.Room;
import com.server.rmi.RMIServer;
import com.server.socket.SocketServer;

import model.exceptions.PlayerNotFound;

/**
 * Server del gioco "Lorenzo Il Magnifico" della "CranioCreations".
 */
public class Server implements IServer {

	/**
	 * Porta in cui è aperta la comunicazione Socket.
	 */
	private static final int SOCKET_PORT = 1098;

	/**
	 * Porta in cui è aperta la comunicazione RMI.
	 */
	private static final int RMI_PORT = 1099;

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
	 * Crea una nuova istanza della classe.
	 * 
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	private Server() throws ServerException {
		players = new HashMap<>();
		rooms = new ArrayList<>();
		socketServer = new SocketServer(this);
		rmiServer = new RMIServer(this);
	}

	/**
	 * Metodo statico per eseguire il server.
	 * 
	 * @param args
	 *            parametri per la connessione (DA IMPLEMENTARE).
	 */
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.startServer(SOCKET_PORT, RMI_PORT);

			System.out.print("\nServer listening at: ");
			System.out.println("127.0.0.1" + " (rmi: " + RMI_PORT + ", socket: " + SOCKET_PORT + ")");
			System.out.println();
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Avvia i server per le connessioni .
	 * 
	 * @param socketPort
	 *            porta su cui avviare il server per le connessioni Socket.
	 * @param rmiPort
	 *            Porta in cui avviare il server per le connessioni RMI.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	private void startServer(int socketPort, int rmiPort) throws ServerException {
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
				players.put(nickname, player);
				player.setNickname(nickname);
				System.out.println(id + " Succesfully logged in!");
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
	 * @param remotePlayer
	 *            that would join.
	 * @throws JoinRoomException
	 *             if no available room has been found.
	 */
	@Override
	public void joinFirstAvailableRoom(
			RemotePlayer remotePlayer) /* throws JoinRoomException */ {
		synchronized (ROOMS_MUTEX) {
			/*
			 * try { joinLastRoom(remotePlayer); } catch (RoomFullException e) {
			 * throw new JoinRoomException(e); }
			 */
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
	public void sendChatMessage(RemotePlayer player, String receiver, String message) throws PlayerNotFound {
		String author = player.getNickname();

		System.out.println("[" + author.toUpperCase() + "]" + " " + message);

		if (receiver != null) {
			for (RemotePlayer remotePlayer : players.values()) {
				if (receiver.equals(author)) {
					try {
						remotePlayer.onChatMessage(author, message, true);
					} catch (NetworkException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			throw new PlayerNotFound();
		} else {
			players.entrySet().stream().filter(remotePlayer -> remotePlayer != player).forEach(remotePlayer -> {
				try {
					remotePlayer.getValue().onChatMessage(author, message, true);
				} catch (NetworkException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
