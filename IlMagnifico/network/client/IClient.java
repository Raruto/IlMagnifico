package network.client;

import java.util.List;

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
	 * Notify internal bus that a new market session is started.
	 */
	void onMarketSessionStarted();

	/**
	 * Notify internal bus that a new market sell turn is started.
	 * 
	 * @param nickname
	 *            of the player that has started the turn.
	 * @param remainingTime
	 *            that the player has to make the actions.
	 */
	void onMarketSellTurnStarted(String nickname, int remainingTime);

	/**
	 * Notify internal bus that a new market buy turn is started.
	 * 
	 * @param nickname
	 *            of the player that has started the turn.
	 * @param remainingTime
	 *            that the player has to make the actions.
	 */
	void onMarketBuyTurnStarted(String nickname, int remainingTime);

	/**
	 * Notify internal bus that a the market session is finished.
	 */
	void onMarketSessionFinished();

	/**
	 * Notify internal bus that a new market item has been added to the market.
	 * 
	 * @param item
	 *            added.
	 */
	// void onMarketItemAdded(Item item);

	/**
	 * Notify internal bus that a market item has been sold.
	 * 
	 * @param marketId
	 *            that has been sold.
	 * @param buyer
	 *            nickname of the player that has bought the item.
	 */
	void onMarketItemBought(String marketId, String buyer);

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
	 * Notify internal bus that a councillor has been elected.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionElectCouncillor(UpdateState updateState);

	/**
	 * Notify internal bus that a business permit tile has been acquired.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionAcquireBusinessPermitTile(UpdateState updateState);

	/**
	 * Notify internal bus that an emporium has been built with a permit tile.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionBuildEmporiumWithBusinessPermitTile(UpdateState
	// updateState);

	/**
	 * Notify internal bus that an emporium has been built with the help of the
	 * king.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionBuildEmporiumWithKingHelp(UpdateState updateState);

	/**
	 * Notify internal bus that an assistant has been engaged.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionEngageAssistant(UpdateState updateState);

	/**
	 * Notify internal bus that the visible permit tiles of a region are
	 * changed.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionChangeBusinessPermitTiles(UpdateState updateState);

	/**
	 * Notify internal bus that an assistant has been sent to elect a
	 * councillor.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionSendAssistantToElectCouncillor(UpdateState updateState);

	/**
	 * Notify internal bus that a player has bought an additional main action.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onActionPerformAdditionalMainAction(UpdateState updateState);

	/**
	 * Notify internal bus that server has respond with an error code.
	 * 
	 * @param errorCode
	 *            that identify the error. See {@link ErrorCodes} for details.
	 */
	void onActionNotValid(int errorCode);

	/**
	 * Notify internal bus that a first special reward has been earned.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onFirstSpecialRewardsEarned(UpdateState updateState);

	/**
	 * Notify internal bus that a second special reward has been earned.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onSecondSpecialRewardsEarned(UpdateState updateState);

	/**
	 * Notify internal bus that a third special reward has been earned.
	 * 
	 * @param updateState
	 *            to apply to the client game state.
	 */
	// void onThirdSpecialRewardsEarned(UpdateState updateState);

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