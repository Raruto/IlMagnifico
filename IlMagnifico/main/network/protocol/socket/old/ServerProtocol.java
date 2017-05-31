package main.network.protocol.socket.old;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.exceptions.LoginException;
import main.network.protocol.socket.Constants;
import main.network.protocol.socket.SocketPlayerInterface;

/**
 * This class is used to define the Socket protocol for communicating with
 * clients trough Sockets. The methods here must communicate with
 * {@link ClientProtocol}.
 */
public class ServerProtocol {
	/**
	 * Input stream used to read client requests.
	 */
	private final ObjectInputStream inputStream;

	/**
	 * Output stream used to send messages to client.
	 */
	private final ObjectOutputStream outputStream;

	/**
	 * Interface used as callback to communicate with the server player.
	 */
	private final SocketPlayerInterface mCallback;

	/**
	 * Object used as mutex to ensure that two threads never send a message over
	 * output stream concurrently. (Only one thread can write on output stream).
	 */
	private static final Object OUTPUT_MUTEX = new Object();

	/**
	 * Map of all defined client requests headers.
	 */
	private final HashMap<Object, RequestHandler> mRequestMap;

	/**
	 * Base constructor.
	 * 
	 * @param input
	 *            stream from the socket.
	 * @param output
	 *            stream from the socket.
	 * @param callback
	 *            used to communicate with the server.
	 */
	public ServerProtocol(ObjectInputStream input, ObjectOutputStream output, SocketPlayerInterface callback) {
		inputStream = input;
		outputStream = output;
		mCallback = callback;
		mRequestMap = new HashMap<>();
		loadRequests();
	}

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
			mCallback.loginPlayer(nickname);
			responseCode = Constants.RESPONSE_OK;
		} catch (LoginException e) {
			System.err.println("[socket protocol] LoginException");
			responseCode = Constants.RESPONSE_PLAYER_ALREADY_EXISTS;
		}
		outputStream.writeObject(responseCode);
		outputStream.flush();

		mCallback.joinRoom();
	}

	private void sendChatMessage() {
		try {
			String receiver = (String) inputStream.readObject();
			String message = (String) inputStream.readObject();
			mCallback.sendChatMessage(receiver, message);
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