package main.network.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import main.network.NetworkException;
import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.protocol.ErrorCodes;
import main.network.protocol.socket.ServerProtocol;
import main.network.protocol.socket.ServerSocketProtocolInt;
import main.network.server.IServer;
import main.network.server.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Extension of {@link RemotePlayer}. This implementation can communicate to his
 * referenced client.
 */
public class SocketPlayer extends RemotePlayer implements Runnable, ServerSocketProtocolInt {

	/**
	 * Debug constants.
	 */
	private static final String DEBUG_NO_ENOUGH_COINS = "[socket player] no enough coins";
	private static final String DEBUG_NO_ENOUGH_ASSISTANTS = "[socket player] no enough assistants";
	private static final String DEBUG_BALCONY_UNSATISFIABLE = "[socket player] balcony unsatisfiable";
	private static final String DEBUG_EMPORIUM_ALREADY_BUILT = "[socket player] emporium already built";
	private static final String DEBUG_CITY_NOT_FOUND = "[socket player] city not found";
	private static final String DEBUG_CITY_NOT_VALID = "[socket player] city not valid";
	private static final String DEBUG_ROUTE_NOT_VALID = "[socket player] route not valid";
	private static final String DEBUG_POLITIC_CARD_NOT_YET_DRAWN = "[socket player] politic card not yet drawn";
	private static final String DEBUG_MAIN_ACTION_NOT_AVAILABLE = "[socket player] main action not available";
	private static final String DEBUG_FAST_ACTION_NOT_AVAILABLE = "[socket player] fast action not available";
	private static final String DEBUG_NOT_YOUR_TURN = "[socket player] not your turn";
	private static final String DEBUG_UNCAUGHT = "[socket player] uncaught exception";

	/**
	 * Server interface.
	 */
	private final transient IServer mServer;

	/**
	 * Socket where player can communicate with the server and vice versa.
	 */
	private final transient Socket mSocket;

	/**
	 * Input stream for receiving object from the client.
	 */
	private final transient ObjectInputStream mInput;

	/**
	 * Output stream to send object to the client.
	 */
	private final transient ObjectOutputStream mOutput;

	/**
	 * Socket protocol used for communication between client and server.
	 */
	private final transient ServerProtocol mSocketProtocol;

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
		mServer = server;
		mSocket = socket;
		mOutput = new ObjectOutputStream(new BufferedOutputStream(mSocket.getOutputStream()));
		mOutput.flush();
		mInput = new ObjectInputStream(new BufferedInputStream(mSocket.getInputStream()));
		mSocketProtocol = new ServerProtocol(mInput, mOutput, this);
	}

	/**
	 * Listen forever on input stream for reading messages.
	 */
	@Override
	public void run() {
		try {
			// noinspection InfiniteLoopStatement
			while (true) {
				Object object = mInput.readObject();
				mSocketProtocol.handleClientRequest(object);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e);
		} finally {
			closeSafely(mInput, "I/O error occurs when closing input stream");
			closeSafely(mOutput, "I/O error occurs when closing output stream");
			closeSafely(mSocket, "I/O error occurs when closing socket");
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
		mSocketProtocol.sendChatMessage(author, message, privateMessage);
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
		mServer.loginPlayer(nickname, this);
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
			getRoom().sendChatMessage(this, receiver, message);
		} catch (PlayerNotFound e) {
			System.err.println("[socket player] cannot dispatch message to a player that cannot be found");
			// mSocketProtocol.actionNotValid(ErrorCodes.ERROR_CHAT_PLAYER_NOT_FOUND);
		}
	}

	@Override
	public void joinRoom() throws JoinRoomException {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawPoliticCard() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendActionList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void engageAssistant() {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeBusinessPermitTiles(String region) {
		// TODO Auto-generated method stub

	}

	@Override
	public void performAdditionalMainAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void earnFirstSpecialRewards(List<String> cities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void earnSecondSpecialRewards(List<String> regions, List<Integer> indices) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sellAssistant(int price) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buyItem(String marketId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(Object object) throws NetworkException {
		// TODO Auto-generated method stub

	}

}
