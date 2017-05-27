package main.network.protocol.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.client.IClient;
import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;

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
		loadResponses();
	}

	/**
	 * Load all possible responses and associate an handler.
	 */
	private void loadResponses() {
		// mResponseMap.put(ProtocolConstants.ACTION_NOT_VALID,
		// this::actionNotValid);
		// mResponseMap.put(ProtocolConstants.GAME_STARTED, this::gameStarted);
		// mResponseMap.put(ProtocolConstants.GAME_TURN_STARTED,
		// this::gameTurnStarted);
		// mResponseMap.put(ProtocolConstants.COUNTDOWN_UPDATED,
		// this::countdownUpdated);
		// mResponseMap.put(ProtocolConstants.POLITIC_CARD_DRAWN,
		// this::politicCardDrawn);
		// mResponseMap.put(ProtocolConstants.ACTION_LIST, this::actionList);
		// mResponseMap.put(ProtocolConstants.COUNCILLOR_ELECTED,
		// this::councillorElected);
		// mResponseMap.put(ProtocolConstants.BUSINESS_PERMIT_TILE_ACQUIRED,
		// this::businessPermitTileAcquired);
		// mResponseMap.put(ProtocolConstants.EMPORIUM_BUILT_WITH_BUSINESS_PERMIT_TILE,
		// this::emporiumBuiltWithBusinessPermitTile);
		// mResponseMap.put(ProtocolConstants.EMPORIUM_BUILT_WITH_KING_HELP,
		// this::emporiumBuiltWithKingHelp);
		// mResponseMap.put(ProtocolConstants.ASSISTANT_ENGAGED,
		// this::assistantEngaged);
		// mResponseMap.put(ProtocolConstants.BUSINESS_PERMIT_TILES_CHANGED,
		// this::businessPermitTilesChanged);
		// mResponseMap.put(ProtocolConstants.ASSISTANT_SENT_TO_ELECT_COUNCILLOR,
		// this::assistantSentToElectCouncillor);
		// mResponseMap.put(ProtocolConstants.ADDITIONAL_MAIN_ACTION_GRANTED,
		// this::additionalMainActionGranted);
		// mResponseMap.put(ProtocolConstants.FIRST_SPECIAL_REWARD_EARNED,
		// this::firstSpecialRewardsEarned);
		// mResponseMap.put(ProtocolConstants.SECOND_SPECIAL_REWARD_EARNED,
		// this::secondSpecialRewardsEarned);
		// mResponseMap.put(ProtocolConstants.THIRD_SPECIAL_REWARD_EARNED,
		// this::thirdSpecialRewardsEarned);
		// mResponseMap.put(ProtocolConstants.MARKET_SESSION_STARTED,
		// this::marketSessionStarted);
		// mResponseMap.put(ProtocolConstants.MARKET_TURN_STARTED,
		// this::marketTurnStarted);
		// mResponseMap.put(ProtocolConstants.MARKET_ITEM_ADDED_ON_SALE,
		// this::marketItemAddedOnSale);
		// mResponseMap.put(ProtocolConstants.MARKET_ITEM_BOUGHT,
		// this::marketItemBought);
		// mResponseMap.put(ProtocolConstants.MARKET_SESSION_FINISHED,
		// this::marketSessionFinished);
		mResponseMap.put(ProtocolConstants.CHAT_MESSAGE, this::onChatMessage);
		// mResponseMap.put(ProtocolConstants.PLAYER_DISCONNECTED,
		// this::onPlayerDisconnected);
		// mResponseMap.put(ProtocolConstants.LAST_TURN_STARTED,
		// this::onLastTurnStarted);
		// mResponseMap.put(ProtocolConstants.GAME_ENDED, this::onGameEnded);
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

	private void onChatMessage() {
		try {
			String author = (String) mInput.readObject();
			String message = (String) mInput.readObject();
			boolean privateMessage = (boolean) mInput.readObject();
			mCallback.onChatMessage(privateMessage, author, message);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println(DEBUG_PROTOCOL_EXCEPTION);
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