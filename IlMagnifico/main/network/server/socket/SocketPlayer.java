package main.network.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.protocol.socket.Constants;
import main.network.protocol.socket.SocketPlayerInterface;
import main.network.server.IServer;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Extension of {@link RemotePlayer}. This implementation can communicate to his
 * referenced client.
 */
public class SocketPlayer extends RemotePlayer implements Runnable, SocketPlayerInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5294571565976357669L;

	/**
	 * Server interface.
	 */
	private final transient IServer server;

	/**
	 * Socket where player can communicate with the server and vice versa.
	 */
	private final transient Socket socket;

	/**
	 * Input stream for receiving object from the client.
	 */
	private final transient ObjectInputStream inputStream;

	/**
	 * Output stream to send object to the client.
	 */
	private final transient ObjectOutputStream outputStream;

	/**
	 * Socket protocol used for communication between client and server.
	 */
	//private final transient ServerProtocol socketProtocol;

	/**
	 * Create a new instance of a socket player.
	 * 
	 * @param server
	 *            server interface.
	 * @param socket
	 *            used for communication.
	 * @throws IOException
	 *             if some error occurs while opening input and output streams.
	 */
	/* package-local */ SocketPlayer(IServer server, Socket socket) throws IOException {
		this.server = server;
		this.socket = socket;
		this.outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		this.outputStream.flush();
		this.inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		//this.socketProtocol = new ServerProtocol(inputStream, outputStream, this);
		mRequestMap = new HashMap<>();
		loadRequests();

	}

	/**
	 * Listen forever on input stream for reading messages.
	 */
	@Override
	public void run() {
		try {
			// noinspection InfiniteLoopStatement
			while (true) {
				Object object = inputStream.readObject();
				//socketProtocol.handleClientRequest(object);
				handleClientRequest(object);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e);
		} finally {
			closeSafely(inputStream, "I/O error occurs when closing input stream");
			closeSafely(outputStream, "I/O error occurs when closing output stream");
			closeSafely(socket, "I/O error occurs when closing socket");
		}
	}

	/**
	 * Close safely the connection.
	 * 
	 * @param closeable
	 *            object that implements {@link Closeable} interface.
	 * @param message
	 *            to print in case an exception is thrown while closing.
	 */
	private void closeSafely(Closeable closeable, String message) {
		try {
			closeable.close();
		} catch (IOException e) {
			System.err.println(message);
		}
	}

	/**
	 * Send a chat message to the player.
	 * 
	 * @param author
	 *            nickname of the player that sent the message.
	 * @param message
	 *            that the author has sent.
	 * @param privateMessage
	 *            if message is private, false if public.
	 * @throws NetworkException
	 *             if client is not reachable.
	 */
	@Override
	public void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException {
		//socketProtocol.sendChatMessage(author, message, privateMessage);
		sendChatMessage(author, message, privateMessage);
	}

	/**
	 * Try to login the player into server with the given nickname.
	 * 
	 * @param nickname
	 *            to use.
	 * @throws LoginException
	 *             if another player with the same nickname is already logged.
	 */
	@Override
	public void loginPlayer(String nickname) throws LoginException {
		server.loginPlayer(nickname, this);
	}

	/**
	 * Dispatch a chat message to the receiver or to all players.
	 * 
	 * @param receiver
	 *            nickname of the receiver, null if message is public.
	 * @param message
	 *            body of the message.
	 */
	@Override
	public void sendChatMessage(String receiver, String message) {
		try {
			// getRoom().sendChatMessage(this, receiver, message);
			server.sendChatMessage(this, receiver, message);
		} catch (PlayerNotFound e) {
			System.err.println("[socket player] cannot dispatch message to a player that cannot be found");
			// mSocketProtocol.actionNotValid(ErrorCodes.ERROR_CHAT_PLAYER_NOT_FOUND);
		}
	}

	@Override
	public void joinRoom() throws JoinRoomException {
		try {
			server.joinFirstAvailableRoom(this);
		} catch (JoinRoomException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(Object object) throws NetworkException {
		// TODO Auto-generated method stub

	}

	////////////////////////////////////////////////////////////
	// SERVER PROTOCOL
	///////////////////////////////////////////////////////////

	/**
	 * Interface used as callback to communicate with the server player.
	 */
	//private final ServerSocketProtocolInt mCallback;

	/**
	 * Object used as mutex to ensure that two threads never send a message over
	 * output stream concurrently. (Only one thread can write on output stream).
	 */
	private static /*final*/ Object OUTPUT_MUTEX = new Object();

	/**
	 * Map of all defined client requests headers.
	 */
	private /*final*/ HashMap<Object, RequestHandler> mRequestMap;

	/**
	 * Load all possible requests and associate an handler.
	 */
	private void loadRequests() {
		mRequestMap.put(Constants.LOGIN_REQUEST, this::loginPlayer);
		mRequestMap.put(Constants.CHAT_MESSAGE, this::sendChatMessage);
	}

	private void loginPlayer() {
		try {
			String nickname = (String) inputStream.readObject();
			loginPlayerAndRespond(nickname);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling client request");
		}
	}

	private void loginPlayerAndRespond(String nickname) throws IOException {
		int responseCode;
		try {
			//mCallback.loginPlayer(nickname);
			loginPlayer(nickname);
			responseCode = Constants.RESPONSE_OK;
		} catch (LoginException e) {
			System.err.println("[socket protocol] LoginException");
			responseCode = Constants.RESPONSE_PLAYER_ALREADY_EXISTS;
		}
		outputStream.writeObject(responseCode);
		outputStream.flush();

		//mCallback.joinRoom();
		joinRoom();
	}

	private void sendChatMessage() {
		try {
			String receiver = (String) inputStream.readObject();
			String message = (String) inputStream.readObject();
			//mCallback.sendChatMessage(receiver, message);
			sendChatMessage(receiver, message);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling client request");
		}
	}

	/**
	 * Send a chat message to the player.
	 * 
	 * @param author
	 *            nickname of the player that sent the message.
	 * @param message
	 *            that the author has sent.
	 * @param privateMessage
	 *            if message is private, false if public.
	 * @throws NetworkException
	 *             if client is not reachable.
	 */
	public void sendChatMessage(String author, String message, boolean privateMessage) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(Constants.CHAT_MESSAGE);
				outputStream.writeObject(author);
				outputStream.writeObject(message);
				outputStream.writeObject(privateMessage);
				outputStream.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	/**
	 * Handle the client request and execute the defined method.
	 * 
	 * @param object
	 *            request header from client.
	 */
	public void handleClientRequest(Object object) {
		RequestHandler handler = mRequestMap.get(object);
		if (handler != null) {
			synchronized (OUTPUT_MUTEX) {
				handler.handle();
			}
		}
	}

	/**
	 * This interface is used like {@link Runnable} interface.
	 */
	@FunctionalInterface
	private interface RequestHandler {

		/**
		 * Handle the client request.
		 */
		void handle();
	}

}
