package main.network.server.rmi;

import java.rmi.RemoteException;

import main.network.NetworkException;
import main.network.client.rmi.RMIClientInterface;
import main.network.server.RemotePlayer;
import main.network.server.game.UpdateStats;

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

	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		try {
			clientInterface.notifyGameUpdate(update);
		} catch (RemoteException e) {
			throw new NetworkException(e);
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
		try {
			clientInterface.notifyNewChatMessage(author, message, privateMessage);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
	}
	
	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws NetworkException {
		try {
			clientInterface.notify(object);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
	}


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