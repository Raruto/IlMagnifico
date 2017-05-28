package main.network.server;

import java.rmi.RemoteException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.security.jgss.ExtendedGSSContext;

import main.model.Carta;
import main.model.Edificio;
import main.model.Giocatore;
import main.model.Impresa;
import main.model.Personaggio;
import main.model.Plancia;
import main.model.Punti;
import main.model.Risorsa;
import main.model.Territorio;
import main.network.NetworkException;
import main.network.protocol.PlayerColors;
import main.network.server.game.Room;
import main.network.server.game.UpdateStats;
import main.util.ECarte;
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
	 * Riferimento al giocatore associato al giocatore remoto.
	 */
	private Giocatore player;

	/**
	 * Reference to room where player is joined.
	 */
	private transient Room mRoom;

	/**
	 * Abstract constructor.
	 */
	protected RemotePlayer() {

	}

	public Giocatore getPlayer() {
		return player;
	}

	public void setPlayer(Giocatore player) {
		this.player = player;
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

	public HashMap<EPunti, Integer> getPuntiGiocatore() {
		HashMap<EPunti, Integer> punti = new HashMap<EPunti, Integer>();
		Punti p = player.getPunti();
		punti.put(EPunti.Fede, p.getPuntiFede());
		punti.put(EPunti.Militare, p.getPuntiMilitari());
		punti.put(EPunti.Vittoria, p.getPuntiVittoria());
		return punti;
	}

	public HashMap<ERisorse, Integer> getRisorseGiocatore() {
		HashMap<ERisorse, Integer> risorse = new HashMap<ERisorse, Integer>();
		Risorsa r = player.getRisorse();
		risorse.put(ERisorse.Legno, r.getLegno());
		risorse.put(ERisorse.Moneta, r.getMonete());
		risorse.put(ERisorse.Pietra, r.getPietre());
		risorse.put(ERisorse.Servitore, r.getServitori());
		return risorse;
	}

	public ArrayList<Edificio> getEdificiGiocatore() {
		return player.getPlancia().getEdifici();
	}

	public ArrayList<Impresa> getImpreseGiocatore() {
		return player.getPlancia().getImprese();
	}

	public ArrayList<Personaggio> getPersonaggiGiocatore() {
		return player.getPlancia().getPersonaggi();
	}

	public ArrayList<Territorio> getTerritoriGiocatore() {
		return player.getPlancia().getTerritori();
	}

	public PlayerColors getColore() {
		return player.getColore();
	}

	// /**
	// * Dispatch base game session to the player at the beginning of the match.
	// * @param baseGame to dispatch to the player.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void dispatchGameSession(BaseGame baseGame) throws
	// NetworkException;
	//
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
	// /**
	// * Notify player that someone has made a main action.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onCouncillorElected(UpdateState updateState) throws
	// NetworkException;
	//
	// /**
	// * Notify player that someone has acquired a business permit tile.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onBusinessPermitTileAcquired(UpdateState
	// updateState) throws NetworkException;
	//
	// /**
	// * Notify player that someone has built an emporium using a business
	// permit tile.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onEmporiumBuiltWithPermitTile(UpdateState
	// updateState) throws NetworkException;
	//
	// /**
	// * Notify player that someone has built an emporium with king helps.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onEmporiumBuiltWithKingHelp(UpdateState updateState)
	// throws NetworkException;
	//
	// /**
	// * Notify player that someone has engaged an assistant.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onAssistantEngaged(UpdateState updateState) throws
	// NetworkException;
	//
	// /**
	// * Notify player that someone has changed the business permit tiles of a
	// region.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onBusinessPermitTilesChanged(UpdateState
	// updateState) throws NetworkException;
	//
	// /**
	// * Notify player that someone has sent an assistant to elect a councillor.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onAssistantSentToElectCouncillor(UpdateState
	// updateState) throws NetworkException;
	//
	// /**
	// * Notify player that someone has obtained an additional main action.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onAdditionalMainActionGranted(UpdateState
	// updateState) throws NetworkException;
	//
	// /**
	// * Notify player that someone has earned one or more first special
	// rewards.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onFirstSpecialRewardsEarned(UpdateState updateState)
	// throws NetworkException;
	//
	// /**
	// * Notify player that someone has earned one or more second special
	// rewards.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onSecondSpecialRewardsEarned(UpdateState
	// updateState) throws NetworkException;
	//
	// /**
	// * Notify player that someone has earned one or more third special
	// rewards.
	// * @param updateState that contains all changes to the server game state.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onThirdSpecialRewardsEarned(UpdateState updateState)
	// throws NetworkException;
	//
	// /**
	// * Notify player that market session is started.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onMarketSessionStarted() throws NetworkException;
	//
	// /**
	// * Notify player that a new market turn is started.
	// * @param nickname of the player that is starting the turn.
	// * @param seconds that the player has to make the actions.
	// * @param mode of the market turn.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onMarketTurnStarted(String nickname, int seconds,
	// MarketTurn.Mode mode) throws NetworkException;
	//
	// /**
	// * Notify player that a new item has been added to the market.
	// * @param item that has been added to the market.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onMarketItemAddedOnSale(Item item) throws
	// NetworkException;
	//
	// /**
	// * Notify player that a market item has been bought.
	// * @param nickname of the player that bought the item.
	// * @param marketId id of the market item that has been bought.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onMarketItemBought(String nickname, String marketId)
	// throws NetworkException;
	//
	// /**
	// * Notify player that market session is finished.
	// * @throws NetworkException if client is not reachable.
	// */
	// public abstract void onMarketSessionFinished() throws NetworkException;

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