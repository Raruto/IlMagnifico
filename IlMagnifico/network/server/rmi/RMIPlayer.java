package network.server.rmi;

import java.rmi.RemoteException;
import network.NetworkException;
import network.client.rmi.RMIClientInterface;
import network.server.RemotePlayer;

/**
 * Extension of {@link RemotePlayer}. This implementation can communicate to his
 * referenced client.
 */
/* package-local */ class RMIPlayer extends RemotePlayer {

	/**
	 * Remote interface to invoke method on {@link RMIClient}.
	 */
	private transient RMIClientInterface clientInterface;

	/**
	 * Create a new instance of a RMIPlayer.
	 * 
	 * @param playerInterface
	 *            remote interface to send response to client.
	 */
	RMIPlayer(RMIClientInterface playerInterface) {
		clientInterface = playerInterface;
	}

	// /**
	// * Dispatch base game session to the player at the beginning of the match.
	// *
	// * @param baseGame
	// * to dispatch to the player.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void dispatchGameSession(BaseGame baseGame) throws
	// NetworkException {
	// try {
	// mPlayerInterface.setGameSession(baseGame);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that a new game turn is started.
	// *
	// * @param nickname
	// * of the player that is starting the turn.
	// * @param seconds
	// * that the player has to make the actions.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onGameTurnStarted(String nickname, int seconds) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyGameTurnStarted(nickname, seconds);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player of remaining seconds to make the turn actions.
	// *
	// * @param remainingSeconds
	// * remaining time in seconds to make the actions.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onUpdateTurnCountdown(int remainingSeconds) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyTurnCountdownUpdated(remainingSeconds);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that a politic card has been drawn.
	// *
	// * @param updateState
	// * to send to the player.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onPoliticCardDrawn(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyPoliticCardDrawn(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Send action list to the player.
	// *
	// * @param actionList
	// * that should be sent.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onActionList(ActionList actionList) throws NetworkException {
	// try {
	// mPlayerInterface.notifyActionListReady(actionList);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has elected a councillor.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onCouncillorElected(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyCouncillorElected(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has acquired a business permit tile.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onBusinessPermitTileAcquired(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyBusinessPermitTileAcquired(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has built an emporium using a business
	// permit
	// * tile.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onEmporiumBuiltWithPermitTile(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyEmporiumBuiltWithBusinessPermitTile(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has built an emporium with king helps.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onEmporiumBuiltWithKingHelp(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyEmporiumBuiltWithKingHelp(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has engaged an assistant.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onAssistantEngaged(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyAssistantEngaged(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has changed the business permit tiles of a
	// * region.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onBusinessPermitTilesChanged(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyBusinessPermitTilesChanged(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has sent an assistant to elect a councillor.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onAssistantSentToElectCouncillor(UpdateState updateState)
	// throws NetworkException {
	// try {
	// mPlayerInterface.notifyAssistantSentToElectCouncillor(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has obtained an additional main action.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onAdditionalMainActionGranted(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyAdditionalMainActionGranted(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has earned one or more first special
	// rewards.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onFirstSpecialRewardsEarned(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyFirstSpecialRewardsEarned(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has earned one or more second special
	// rewards.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onSecondSpecialRewardsEarned(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifySecondSpecialRewardsEarned(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that someone has earned one or more third special
	// rewards.
	// *
	// * @param updateState
	// * that contains all changes to the server game state.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onThirdSpecialRewardsEarned(UpdateState updateState) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyThirdSpecialRewardsEarned(updateState);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that market session is started.
	// *
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onMarketSessionStarted() throws NetworkException {
	// try {
	// mPlayerInterface.notifyMarketSessionStarted();
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that a new market turn is started.
	// *
	// * @param nickname
	// * of the player that is starting the turn.
	// * @param seconds
	// * that the player has to make the actions.
	// * @param mode
	// * of the market turn.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onMarketTurnStarted(String nickname, int seconds,
	// MarketTurn.Mode mode) throws NetworkException {
	// try {
	// mPlayerInterface.notifyMarketTurnStarted(nickname, seconds, mode);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that a new item has been added to the market.
	// *
	// * @param item
	// * that has been added to the market.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onMarketItemAddedOnSale(Item item) throws NetworkException {
	// try {
	// mPlayerInterface.notifyMarketItemAddedOnSale(item);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that a market item has been bought.
	// *
	// * @param nickname
	// * of the player that bought the item.
	// * @param marketId
	// * id of the market item that has been bought.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onMarketItemBought(String nickname, String marketId) throws
	// NetworkException {
	// try {
	// mPlayerInterface.notifyMarketItemBought(nickname, marketId);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that market session is finished.
	// *
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onMarketSessionFinished() throws NetworkException {
	// try {
	// mPlayerInterface.notifyMarketSessionFinished();
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }

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
		try {
			clientInterface.notifyNewChatMessage(author, message, privateMessage);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
	}

	// /**
	// * Notify player that another player has disconnected.
	// *
	// * @param nickname
	// * of the disconnected player.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onPlayerDisconnected(String nickname) throws NetworkException
	// {
	// try {
	// mPlayerInterface.notifyPlayerDisconnected(nickname);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that a player has built all his emporiums and the last
	// turn
	// * is starting.
	// *
	// * @param nickname
	// * of the player has built all his emporiums
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onLastTurn(String nickname) throws NetworkException {
	// try {
	// mPlayerInterface.notifyLastTurnStarted(nickname);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
	//
	// /**
	// * Notify player that the game is finished and dispatch all last update of
	// * all players.
	// *
	// * @param updateStates
	// * of all players (bonus and end-game rewards).
	// * @param ranking
	// * list of players nickname sorted by winner to loser.
	// * @throws NetworkException
	// * if client is not reachable.
	// */
	// @Override
	// public void onGameEnd(UpdateState[] updateStates, List<String> ranking)
	// throws NetworkException {
	// try {
	// mPlayerInterface.notifyGameEnded(updateStates, ranking);
	// } catch (RemoteException e) {
	// throw new NetworkException(e);
	// }
	// }
}