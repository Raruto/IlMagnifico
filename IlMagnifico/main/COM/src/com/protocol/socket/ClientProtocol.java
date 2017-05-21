package com.protocol.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.NetworkException;
import com.client.IClient;
import com.exceptions.CreateRoomException;
import com.exceptions.JoinRoomException;
import com.exceptions.LoginException;

/**
 * This class is used to define the Socket protocol for communicating with
 * server trough Sockets. The methods here must communicate with
 * {@link ServerProtocol}.
 */
public class ClientProtocol {

	/**
	 * Generic debug tag.
	 */
	private static final String DEBUG_PROTOCOL_EXCEPTION = "Exception while handling server message";

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
		// loadResponses();
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
			mOutput.writeObject(ProtocolConstants.LOGIN_REQUEST);
			mOutput.writeObject(nickname);
			mOutput.flush();
			responseCode = (int) mInput.readObject();
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			throw new NetworkException(e);
		}
		if (responseCode == ProtocolConstants.RESPONSE_PLAYER_ALREADY_EXISTS) {
			throw new LoginException();
		}
	}

	/**
	 * Blocking request to server for join user in the first available room.
	 * 
	 * @throws JoinRoomException
	 *             if no available room has been found where join the player.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public void joinFirstAvailableRoom() throws NetworkException {
		int responseCode;
		try {
			mOutput.writeObject(ProtocolConstants.JOIN_ROOM_REQUEST);
			mOutput.flush();
			responseCode = (int) mInput.readObject();
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			throw new NetworkException(e);
		}
		if (responseCode == ProtocolConstants.RESPONSE_NO_ROOM_AVAILABLE) {
			throw new JoinRoomException();
		}
	}

	/**
	 * Blocking request to server for creating a new room.
	 * 
	 * @param maxPlayers
	 *            that should be accepted in this new room.
	 * @throws CreateRoomException
	 *             if a new room has been created in the meanwhile and the
	 *             player has been added.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public Configuration createNewRoom(int maxPlayers) throws
	// NetworkException {
	// Object response;
	// try {
	// mOutput.writeObject(ProtocolConstants.CREATE_ROOM_REQUEST);
	// mOutput.writeObject(maxPlayers);
	// mOutput.flush();
	// response = mInput.readObject();
	// } catch (ClassNotFoundException | ClassCastException | IOException e) {
	// throw new NetworkException(e);
	// }
	// if (response instanceof Integer && (int) response ==
	// ProtocolConstants.RESPONSE_PLAYER_FORCE_JOINED) {
	// throw new CreateRoomException();
	// } else if (response instanceof Configuration) {
	// return (Configuration) response;
	// }
	// throw new NetworkException("Unknown response");
	// }

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
				mOutput.writeObject(ProtocolConstants.CHAT_MESSAGE);
				mOutput.writeObject(receiver);
				mOutput.writeObject(message);
				mOutput.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
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