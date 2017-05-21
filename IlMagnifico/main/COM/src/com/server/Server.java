package com.server;

import java.util.ArrayList;
import java.util.HashMap;

import com.exceptions.LoginException;
import com.server.game.Room;
import com.server.rmi.RMIServer;
import com.server.socket.SocketServer;

/**
 * This class represent the server of the game.
 */
public class Server implements IServer {

	/**
	 * Port where socket communication is open.
	 */
	private static final int SOCKET_PORT = 1098;

	/**
	 * Port where RMI communication is open.
	 */
	private static final int RMI_PORT = 1099;

	/**
	 * This object works as mutex to avoid concurrency race between player
	 * login.
	 */
	private static final Object PLAYERS_MUTEX = new Object();

	/**
	 * This object works as mutex to avoid concurrency race between room
	 * joining.
	 */
	private static final Object ROOMS_MUTEX = new Object();

	/**
	 * Players cache.
	 */
	private HashMap<String, RemotePlayer> mPlayers;

	/**
	 * List of all server room.
	 */
	private ArrayList<Room> mRooms;

	/**
	 * Socket server.
	 */
	private SocketServer socketServer;

	/**
	 * RMI server.
	 */
	private RMIServer rmiServer;

	/**
	 * Create a new instance of the class.
	 * 
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private Server() throws ServerException {
		mPlayers = new HashMap<>();
		mRooms = new ArrayList<>();
		socketServer = new SocketServer(this);
		rmiServer = new RMIServer(this);
	}

	/**
	 * Static method to execute the server.
	 * 
	 * @param args
	 *            to pass to the server.
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
	 * Start server connections.
	 * 
	 * @param socketPort
	 *            port where start Socket connection.
	 * @param rmiPort
	 *            port where start RMI connection.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private void startServer(int socketPort, int rmiPort) throws ServerException {
		startRMIServer(rmiPort);
		startSocketServer(socketPort);
	}

	/**
	 * Start RMIserver connection.
	 * 
	 * @param rmiPort
	 *            port where start RMI connection.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private void startRMIServer(int rmiPort) throws ServerException {
		System.out.println("Starting RMI Server...");
		rmiServer.startServer(rmiPort);
	}

	/**
	 * Start SocketServer connection.
	 * 
	 * @param socketPort
	 *            port where start Socket connection.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private void startSocketServer(int socketPort) throws ServerException {
		System.out.println("Starting Socket Server...");
		socketServer.startServer(socketPort);
	}

	/**
	 * Login player with nickname.
	 * 
	 * @param nickname
	 *            of the player that would login.
	 * @param player
	 *            reference to the player that made the request.
	 * @throws LoginException
	 *             if a player with this nickname already exists.
	 */
	@Override
	public void loginPlayer(String nickname, RemotePlayer player) throws LoginException {
		synchronized (PLAYERS_MUTEX) {
			if (!mPlayers.containsKey(nickname)) {
				mPlayers.put(nickname, player);
				player.setNickname(nickname);
			} else {
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
		return mPlayers.get(nickname);
	}

}
