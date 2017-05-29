package main.network.server;

import java.util.ArrayList;
import java.util.HashMap;
import main.model.Edificio;
import main.model.Giocatore;
import main.model.Impresa;
import main.model.Personaggio;
import main.model.Punti;
import main.model.Risorsa;
import main.model.Territorio;
import main.network.NetworkException;
import main.network.protocol.PlayerColors;
import main.network.server.game.Room;
import main.network.server.game.UpdateStats;
import main.util.EPunti;
import main.util.ERisorse;

/**
 * Abstract extension of {@link Player}. This implementation can communicate to
 * his referenced client.
 */
public abstract class RemotePlayer extends Giocatore {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7157051737050661369L;

	/**
	 * Flag che indica se il giocatore � online
	 */
	private boolean isOnline;

	/**
	 * Reference to room where player is joined.
	 */
	private transient Room mRoom;

	/**
	 * Abstract constructor.
	 */
	protected RemotePlayer() {

	}

	/**
	 * Set room reference.
	 * 
	 * @param room
	 *            where player is joined.
	 */
	/* package-local */ void setRoom(Room room) {
		mRoom = room;
	}

	/**
	 * Get room reference where player is joined.
	 * 
	 * @return the room reference if player is in a room, null otherwise.
	 */
	public Room getRoom() {
		return mRoom;
	}


	/**
	 * Imposta il flag online usato per determinare lo stato della connessione
	 * con il client associato al giocatore.
	 * 
	 * @param online
	 *            "True" imposta lo stato del giocatore come presente (online).
	 */
	public void setOnline(boolean online) {
		this.isOnline = online;
	}

	/**
	 * Ritorna lo stato della connessione con il client associato al giocatore
	 * 
	 * @return "True" se il giocatore � online.
	 */
	public boolean isOnline() {
		return this.isOnline;
	}

	// /**
	// * Notify player that a new game turn is started.
	// * @param nickname of the player that is starting the turn.
	// * @param seconds that the player has to make the actions.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onGameTurnStarted(String nickname, int seconds)
	// throws NetworkException;
	//
	// /**
	// * Notify player of remaining seconds to make the turn actions.
	// * @param remainingSeconds remaining time in seconds to make the actions.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onUpdateTurnCountdown(int remainingSeconds) throws
	// NetworkException;
	//
	// /**
	// * Notify player that a politic card has been drawn.
	// * @param updateState to send to the player.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onPoliticCardDrawn(UpdateState updateState) throws
	// NetworkException;
	//
	// /**
	// * Send action list to the player.
	// * @param actionList that should be sent.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onActionList(ActionList actionList) throws
	// NetworkException;
	//

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
	public abstract void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException;

	public abstract void onGameUpdate(UpdateStats update) throws NetworkException;

	public abstract void send(Object object) throws NetworkException;

	// /**
	// * Notify player that another player has disconnected.
	// * @param nickname of the disconnected player.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onPlayerDisconnected(String nickname) throws
	// NetworkException;
	//
	// /**
	// * Notify player that a player has built all his emporiums and the last
	// turn is starting.
	// * @param nickname of the player has built all his emporiums
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onLastTurn(String nickname) throws NetworkException;
	//
	// /**
	// * Notify player that the game is finished and dispatch all last update of
	// all players.
	// * @param updateStates of all players (bonus and end-game rewards).
	// * @param ranking list of players nickname sorted by winner to loser.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onGameEnd(UpdateState[] updateStates, List<String>
	// ranking) throws NetworkException;
}