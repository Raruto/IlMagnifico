package main.network.client;

import java.rmi.RemoteException;
import main.network.server.game.UpdateStats;

/**
 * Interfaccia usata come client controller in {@link AbstractClient} (notifiche
 * dal server verso il client stesso).
 */
public interface IClient {

	/**
	 * Notify game is started and dispatch initial game state.
	 * 
	 * @param baseGame
	 *            snapshot of the initial state of the game on server.
	 */
	// void onGameStarted(BaseGame baseGame);

	/**
	 * Notify internal bus that a new turn is started.
	 * 
	 * @param nickname
	 *            of the player.
	 * @param remainingTime
	 *            time to complete the turn.
	 */
	void onTurnStarted(String nickname, int remainingTime);

	/**
	 * Notify internal bus that the timer countdown is changed.
	 * 
	 * @param remainingTime
	 *            to complete the turn.
	 */
	void onTurnUpdateCountdown(int remainingTime);

	/**
	 * Notify internal bus that a new politic card has been drawn.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onDrawnPoliticCard(UpdateState updateState);

	/**
	 * Notify internal bus that the action list is ready.
	 * 
	 * @param actionList
	 *            from server to show to the user.
	 */
	// void onActionList(ActionList actionList);

	/**
	 * Notify internal bus that server has respond with an error code.
	 * 
	 * @param errorCode
	 *            that identify the error. See {@link ErrorCodes} for details.
	 */
	void onActionNotValid(int errorCode);

	/**
	 * Notify internal bus that a new chat message is arrived.
	 * 
	 * @param privateMessage
	 *            true if message is private, false if public.
	 * @param author
	 *            of the message.
	 * @param message
	 *            body of the message.
	 */
	void onChatMessage(boolean privateMessage, String author, String message);

	void onGameUpdate(UpdateStats update);

	/**
	 * Notify internal bus that a player has disconnected.
	 * 
	 * @param nickname
	 *            of the player that has disconnected.
	 */
	void onPlayerDisconnected(String nickname);

	/**
	 * Notify internal bus that the last game turn is started.
	 * 
	 * @param nickname
	 *            of the player that has started the last game turn.
	 */
	void onLastTurnStarted(String nickname);

	void onNotify(Object object) throws RemoteException;

	/**
	 * Notify internal bus that the game is over.
	 * 
	 * @param updateStates
	 *            list of final update states to apply.
	 * @param ranking
	 *            list of players sorted from the winner to the last loser.
	 */
	// void onGameEnded(UpdateState[] updateStates, List<String> ranking);
}