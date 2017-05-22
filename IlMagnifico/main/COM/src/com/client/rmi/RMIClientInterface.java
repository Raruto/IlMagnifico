package com.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for RemoteMethodInvocation from server to client.
 */
public interface RMIClientInterface extends Remote {

//	/**
//	 * Dispatch game session to the remote player.
//	 * 
//	 * @param gameSession
//	 *            to dispatch the player.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void setGameSession(BaseGame gameSession) throws RemoteException;
//
//	/**
//	 * Notify player that a new game turn is started.
//	 * 
//	 * @param nickname
//	 *            of the player that is starting the turn.
//	 * @param seconds
//	 *            that the player has to make the actions.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyGameTurnStarted(String nickname, int seconds) throws RemoteException;
//
//	/**
//	 * Notify player of remaining seconds to make the turn actions.
//	 * 
//	 * @param remainingSeconds
//	 *            remaining time in seconds to make the actions.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyTurnCountdownUpdated(int remainingSeconds) throws RemoteException;
//
//	/**
//	 * Notify player that a politic card has been drawn.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyPoliticCardDrawn(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that requested action list is available.
//	 * 
//	 * @param actionList
//	 *            list of all available actions for the player.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyActionListReady(ActionList actionList) throws RemoteException;
//
//	/**
//	 * Notify player that someone has elected a councillor.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyCouncillorElected(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has acquired a business permit tile.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyBusinessPermitTileAcquired(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has built an emporium using a business permit
//	 * tile.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyEmporiumBuiltWithBusinessPermitTile(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has built an emporium with king helps.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyEmporiumBuiltWithKingHelp(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has engaged an assistant.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyAssistantEngaged(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has changed the business permit tiles of a
//	 * region.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyBusinessPermitTilesChanged(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has sent an assistant to elect a councillor.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyAssistantSentToElectCouncillor(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has obtained an additional main action.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyAdditionalMainActionGranted(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has earned one or more first special rewards.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyFirstSpecialRewardsEarned(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has earned one or more second special rewards.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifySecondSpecialRewardsEarned(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that someone has earned one or more third special rewards.
//	 * 
//	 * @param updateState
//	 *            bundle with updated contents.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyThirdSpecialRewardsEarned(UpdateState updateState) throws RemoteException;
//
//	/**
//	 * Notify player that market session is started.
//	 * 
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyMarketSessionStarted() throws RemoteException;
//
//	/**
//	 * Notify player that a new market turn is started.
//	 * 
//	 * @param nickname
//	 *            of the player that is starting the turn.
//	 * @param seconds
//	 *            that the player has to make the actions.
//	 * @param mode
//	 *            of the market turn.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyMarketTurnStarted(String nickname, int seconds, MarketTurn.Mode mode) throws RemoteException;
//
//	/**
//	 * Notify player that a new item has been added to the market.
//	 * 
//	 * @param item
//	 *            that has been added to the market.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyMarketItemAddedOnSale(Item item) throws RemoteException;
//
//	/**
//	 * Notify player that a market item has been bought.
//	 * 
//	 * @param nickname
//	 *            of the player that bought the item.
//	 * @param marketId
//	 *            id of the market item that has been bought.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyMarketItemBought(String nickname, String marketId) throws RemoteException;
//
//	/**
//	 * Notify player that market session is finished.
//	 * 
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyMarketSessionFinished() throws RemoteException;
//
//	/**
//	 * Notify player that a new chat message has been received.
//	 * 
//	 * @param author
//	 *            nickname of the player that sent the message.
//	 * @param message
//	 *            that the author has sent.
//	 * @param privateMessage
//	 *            if message is private, false if public.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyNewChatMessage(String author, String message, boolean privateMessage) throws RemoteException;
//
//	/**
//	 * Notify player that another player has disconnected.
//	 * 
//	 * @param nickname
//	 *            of the player that has disconnected.
//	 * @throws RemoteException
//	 *             if player is not reachable from the server.
//	 */
//	void notifyPlayerDisconnected(String nickname) throws RemoteException;
//
//	/**
//	 * Notify player that a player has built all his emporiums and the last turn
//	 * is starting.
//	 * 
//	 * @param nickname
//	 *            of the player has built all his emporiums
//	 * @throws RemoteException
//	 *             if client is not reachable.
//	 */
//	void notifyLastTurnStarted(String nickname) throws RemoteException;
//
//	/**
//	 * Notify player that the game is finished and dispatch all last update of
//	 * all players.
//	 * 
//	 * @param updateStates
//	 *            of all players (bonus and end-game rewards).
//	 * @param ranking
//	 *            list of players nickname sorted by winner to loser.
//	 * @throws RemoteException
//	 *             if client is not reachable.
//	 */
//	void notifyGameEnded(UpdateState[] updateStates, List<String> ranking) throws RemoteException;

	public void notify(String object) throws RemoteException;

	public String getPlayerName() throws RemoteException;

	public void setPlayerName(String name) throws RemoteException;

}