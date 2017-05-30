package main.network.protocol.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.network.NetworkException;
import main.network.exceptions.LoginException;

/**
 * This class is used to define the Socket protocol for communicating with
 * clients trough Sockets. The methods here must communicate with
 * {@link ClientProtocol}.
 */
public class ServerProtocol {

	/**
	 * Generic debug tag.
	 */
	private static final String DEBUG_PROTOCOL_EXCEPTION = "Exception while handling client request";

	/**
	 * Input stream used to read client requests.
	 */
	private final ObjectInputStream mInput;

	/**
	 * Output stream used to send messages to client.
	 */
	private final ObjectOutputStream mOutput;

	/**
	 * Interface used as callback to communicate with the server player.
	 */
	private final ServerSocketProtocolInt mCallback;

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
	public ServerProtocol(ObjectInputStream input, ObjectOutputStream output, ServerSocketProtocolInt callback) {
		mInput = input;
		mOutput = output;
		mCallback = callback;
		mRequestMap = new HashMap<>();
		loadRequests();
	}

	/**
	 * Load all possible requests and associate an handler.
	 */
	private void loadRequests() {
		mRequestMap.put(ProtocolConstants.LOGIN_REQUEST, this::loginPlayer);
		// mRequestMap.put(ProtocolConstants.JOIN_ROOM_REQUEST, this::joinRoom);
		// mRequestMap.put(ProtocolConstants.CREATE_ROOM_REQUEST,
		// this::createRoom);
		// mRequestMap.put(ProtocolConstants.APPLY_CONFIGURATION_REQUEST,
		// this::applyConfiguration);
		// mRequestMap.put(ProtocolConstants.GET_ACTION_LIST,
		// mCallback::sendActionList);
		// mRequestMap.put(ProtocolConstants.DRAW_POLITIC_CARD,
		// mCallback::drawPoliticCard);
		// mRequestMap.put(ProtocolConstants.ELECT_COUNCILLOR,
		// this::electCouncillor);
		// mRequestMap.put(ProtocolConstants.ACQUIRE_BUSINESS_PERMIT_TILE,
		// this::acquireBusinessPermitTile);
		// mRequestMap.put(ProtocolConstants.BUILD_EMPORIUM_WITH_BUSINESS_PERMIT_TILE,
		// this::buildEmporiumWithBusinessPermitTile);
		// mRequestMap.put(ProtocolConstants.BUILD_EMPORIUM_WITH_KING_HELP,
		// this::buildEmporiumWithKingHelp);
		// mRequestMap.put(ProtocolConstants.ENGAGE_ASSISTANT,
		// mCallback::engageAssistant);
		// mRequestMap.put(ProtocolConstants.CHANGE_BUSINESS_PERMIT_TILES,
		// this::changeBusinessPermitTiles);
		// mRequestMap.put(ProtocolConstants.SEND_ASSISTANT_TO_ELECT_COUNCILLOR,
		// this::sendAssistantElectCouncillor);
		// mRequestMap.put(ProtocolConstants.PERFORM_ADDITIONAL_MAIN_ACTION,
		// mCallback::performAdditionalMainAction);
		// mRequestMap.put(ProtocolConstants.EARN_FIRST_SPECIAL_REWARDS,
		// this::earnFirstSpecialRewards);
		// mRequestMap.put(ProtocolConstants.EARN_SECOND_SPECIAL_REWARDS,
		// this::earnSecondSpecialRewards);
		// mRequestMap.put(ProtocolConstants.EARN_THIRD_SPECIAL_REWARDS,
		// this::earnThirdSpecialRewards);
		// mRequestMap.put(ProtocolConstants.SELL_POLITIC_CARD,
		// this::sellPoliticCard);
		// mRequestMap.put(ProtocolConstants.SELL_BUSINESS_PERMIT_TILE,
		// this::sellBusinessPermitTile);
		// mRequestMap.put(ProtocolConstants.SELL_ASSISTANT,
		// this::sellAssistant);
		// mRequestMap.put(ProtocolConstants.BUY_ITEM, this::buyItem);
		// mRequestMap.put(ProtocolConstants.END_TURN, mCallback::endTurn);
		mRequestMap.put(ProtocolConstants.CHAT_MESSAGE, this::sendChatMessage);
	}

	private void loginPlayer() {
		try {
			String nickname = (String) mInput.readObject();
			loginPlayerAndRespond(nickname);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println(DEBUG_PROTOCOL_EXCEPTION);
		}
	}

	private void loginPlayerAndRespond(String nickname) throws IOException {
		int responseCode;
		try {
			mCallback.loginPlayer(nickname);
			responseCode = ProtocolConstants.RESPONSE_OK;
		} catch (LoginException e) {
			System.err.println("[socket protocol] LoginException");
			responseCode = ProtocolConstants.RESPONSE_PLAYER_ALREADY_EXISTS;
		}
		mOutput.writeObject(responseCode);
		mOutput.flush();

		mCallback.joinRoom();
	}

	private void sendChatMessage() {
		try {
			String receiver = (String) mInput.readObject();
			String message = (String) mInput.readObject();
			mCallback.sendChatMessage(receiver, message);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println(DEBUG_PROTOCOL_EXCEPTION);
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
				mOutput.writeObject(ProtocolConstants.CHAT_MESSAGE);
				mOutput.writeObject(author);
				mOutput.writeObject(message);
				mOutput.writeObject(privateMessage);
				mOutput.flush();
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