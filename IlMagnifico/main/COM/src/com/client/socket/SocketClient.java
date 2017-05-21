package com.client.socket;

import java.io.*;
import java.net.Socket;
import java.util.List;

import com.NetworkException;
import com.client.AbstractClient;
import com.client.ClientException;
import com.client.IClient;
import com.protocol.socket.ClientProtocol;

/**
 * This class is the implementation of {@link AbstractClient} class. It manages
 * the network connection with sockets.
 */
public class SocketClient extends AbstractClient {

	/**
	 * Socket endpoint of client.
	 */
	private Socket mSocket;

	/**
	 * Object input stream for receiving serialized objects from server socket.
	 */
	private ObjectInputStream mInput;

	/**
	 * Object output stream for sending serialized objects to server socket.
	 */
	private ObjectOutputStream mOutput;

	/**
	 * Socket protocol used for communication between client and server.
	 */
	private ClientProtocol mSocketProtocol;

	/**
	 * Create a socket client instance.
	 * 
	 * @param controller
	 *            client controller.
	 * @param address
	 *            of the server.
	 * @param port
	 *            of the server.
	 */
	public SocketClient(IClient controller, String address, int port) {
		super(controller, address, port);
	}

	/**
	 * Open a connection with the server and initialize socket protocol.
	 * 
	 * @throws ClientConnectionException
	 *             if server is not reachable or something went wrong.
	 */
	@Override
	public void startClient() throws ClientException {
		try {
			mSocket = new Socket(getAddress(), getPort());

			System.out.println("Socket Connection established");

			mOutput = new ObjectOutputStream(new BufferedOutputStream(mSocket.getOutputStream()));
			mOutput.flush();
			mInput = new ObjectInputStream(new BufferedInputStream(mSocket.getInputStream()));
		} catch (IOException e) {
			throw new ClientException(e);
		}
	}

	/**
	 * Initialize socket protocol.
	 */
	@Override
	public void initializeConnection() {
		mSocketProtocol = new ClientProtocol(mInput, mOutput, getController());
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
	@Override
	public void loginPlayer(String nickname) throws NetworkException {
		mSocketProtocol.loginPlayer(nickname);
	}

	/**
	 * Blocking request to server for join user in the first available room.
	 * 
	 * @throws JoinRoomException
	 *             if no available room has been found where join the player.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	@Override
	public void joinFirstAvailableRoom() throws NetworkException {
		mSocketProtocol.joinFirstAvailableRoom();
		startNetworkMessageHandlerThread();
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
	// @Override
	// public Configuration createNewRoom(int maxPlayers) throws
	// NetworkException {
	// try {
	// return mSocketProtocol.createNewRoom(maxPlayers);
	// } catch (NetworkException e) {
	// startNetworkMessageHandlerThread();
	// throw e;
	// }
	// }

	/**
	 * Try to apply game configuration to the player room.
	 * 
	 * @param configuration
	 *            that should be applied to the room.
	 * @throws InvalidConfigurationException
	 *             if configuration is not valid.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// @Override
	// public void applyGameConfiguration(Configuration configuration) throws
	// NetworkException {
	// mSocketProtocol.applyConfiguration(configuration);
	// startNetworkMessageHandlerThread();
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
	@Override
	public void sendChatMessage(String receiver, String message) throws NetworkException {
		mSocketProtocol.sendChatMessage(receiver, message);
	}

	/**
	 * Start a new thread that will listen for all incoming messages on socket
	 * input stream and will process them according to the defined socket
	 * protocol.
	 */
	private void startNetworkMessageHandlerThread() {
		ResponseHandler responseHandler = new ResponseHandler();
		responseHandler.start();
	}

	/**
	 * Internal thread that will listen on {@link #mInput inputStream} for
	 * server messages.
	 */
	private class ResponseHandler extends Thread {

		@Override
		public void run() {
			while (true) {
				boolean quit = false;
				try {
					Object object = mInput.readObject();
					mSocketProtocol.handleResponse(object);
				} catch (ClassNotFoundException | IOException e) {
					// Debug.critical("Cannot read server response", e);
					quit = true;
				}
				if (quit) {
					break;
				}
			}
			closeSafely(mInput, "I/O error occurs when closing input stream");
			closeSafely(mOutput, "I/O error occurs when closing output stream");
			closeSafely(mSocket, "I/O error occurs when closing socket");
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
				// Debug.error(message, e);
			}
		}
	}
}