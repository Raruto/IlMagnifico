package main.network.protocol.socket.old;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.client.IClient;
import main.network.exceptions.LoginException;
import main.network.protocol.socket.Constants;

/**
 * This class is used to define the Socket protocol for communicating with
 * server trough Sockets. The methods here must communicate with
 * {@link ServerProtocol}.
 */
public class ClientProtocol {
	/**
	 * Input stream used to read server responses.
	 */
	private final ObjectInputStream mInput;

	/**
	 * Output stream used to send messages to server.
	 */
	private final ObjectOutputStream mOutput;

	/**
	 * Interface used as callback to communicate with the client.
	 */
	private final IClient mCallback;

	/**
	 * Object used as mutex to ensure that two threads never send a message over
	 * output stream concurrently. (Only one thread can write on output stream).
	 */
	private static final Object OUTPUT_MUTEX = new Object();

	/**
	 * Map of all defined server responses headers.
	 */
	private final HashMap<Object, ResponseHandler> mResponseMap;

	/**
	 * Base constructor.
	 * 
	 * @param input
	 *            stream from the socket.
	 * @param output
	 *            stream from the socket.
	 * @param callback
	 *            used to communicate with the client.
	 */
	public ClientProtocol(ObjectInputStream input, ObjectOutputStream output, IClient callback) {
		mInput = input;
		mOutput = output;
		mCallback = callback;
		mResponseMap = new HashMap<>();
		loadResponses();
	}

	/**
	 * Load all possible responses and associate an handler.
	 */
	private void loadResponses() {
		mResponseMap.put(Constants.CHAT_MESSAGE, this::onChatMessage);
	}

	/**
	 * Blocking request to server for user login.
	 * 
	 * @param nickname
	 *            to use for login.
	 * @throws LoginException
	 *             if provided nickname is already in use.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public void loginPlayer(String nickname) throws NetworkException {
		int responseCode;
		try {
			mOutput.writeObject(Constants.LOGIN_REQUEST);
			mOutput.writeObject(nickname);
			mOutput.flush();

			responseCode = (int) mInput.readObject();
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			throw new NetworkException(e);
		}
		if (responseCode == Constants.RESPONSE_PLAYER_ALREADY_EXISTS) {
			throw new LoginException();
		}
	}

	/**
	 * Send a chat message to other players or a specified player.
	 * 
	 * @param receiver
	 *            nickname of the specific player if a private message, null if
	 *            should be delivered to all room players.
	 * @param message
	 *            to deliver.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public void sendChatMessage(String receiver, String message) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				mOutput.writeObject(Constants.CHAT_MESSAGE);
				mOutput.writeObject(receiver);
				mOutput.writeObject(message);
				mOutput.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	private void onChatMessage() {
		try {			
			String author = (String) mInput.readObject();
			String message = (String) mInput.readObject();
			boolean privateMessage = (boolean) mInput.readObject();
			mCallback.onChatMessage(privateMessage, author, message);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling server message");
		}
	}

	/**
	 * Handle the server response and execute the defined method.
	 * 
	 * @param object
	 *            response header from server.
	 */
	public void handleResponse(Object object) {
		ResponseHandler handler = mResponseMap.get(object);
		if (handler != null) {
			handler.handle();
		}
	}

	/**
	 * This interface is used like {@link Runnable} interface.
	 */
	@FunctionalInterface
	private interface ResponseHandler {

		/**
		 * Handle the server response.
		 */
		void handle();
	}
}