package network.server.game;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import network.NetworkException;
import network.server.RemotePlayer;

import network.exceptions.PlayerNotFound;

/**
 * Classe per la gestione di una singola Stanza sul server. Ogni Stanza gestisce
 * una partita e un insieme di giocatori associati ad essa.
 */
public class Room {

	/**
	 * Numero identificatore della singola stanza (incrementato ogni volta che
	 * viene creata una nuova Stanza)
	 */
	private static int roomNumber = 0;

	/**
	 * Debug constants.
	 */
	private static final String DEBUG_PLAYER_DISCONNECTED = "[room] player disconnected";

	/**
	 * Numero minimo di giocatori affinchè possa partire una partita.
	 */
	private int minPlayers = 2;

	/**
	 * Numero massimo di giocatori gestibili da una partita.
	 */
	private int maxPlayers = 4;

	/**
	 * Tempo di attesa di altri giocatori all'interno della Stanza prima che una
	 * nuova partita inizi automaticamente.
	 */
	private static final long ROOM_WAITING_TIME = 20 * 1000L;

	/**
	 * Costante associata ad un tempo di attesa nullo (usata per far iniziare
	 * una nuova partita automaticamente quando si raggiunge il numero massimo
	 * di giocatori gestibili).
	 */
	private static final long START_IMMEDIATELY = 0L;

	/**
	 * MUTEX per evitare la concorrenza tra giocatori durante l'accesso alla
	 * Stanza.
	 */
	private static final Object ROOM_MUTEX = new Object();

	/**
	 * Lista di giocatori che sono stati aggiunti alla Stanza (vedi
	 * {@link RemotePlayer}).
	 */
	private final ArrayList<RemotePlayer> players;

	/**
	 * Timer utilizzato per i countdown.
	 */
	private Timer timer;

	/**
	 * Flag usato per indicare quando una Stanza è ancora aperta (True) o chiusa
	 * (False) all'aggiunta di nuovi giocatori.
	 */
	private boolean canJoin;

	/**
	 * Time to wait for player's move.
	 */
	private int mWaitingTime;

	/**
	 * Instance of admin configuration.
	 */
	// private Configuration mConfiguration;

	/**
	 * Complete match state.
	 */
	// private ServerGame mGame;

	/**
	 * Semaphore used to wait main thread for game configuration.
	 */
	private CountDownLatch mStartLatch;

	/**
	 * Current game turn.
	 */
	// private Turn mTurn;

	/**
	 * Market session that contains everything about item's that every player is
	 * selling or buying.
	 */
	// private MarketSession mMarketSession;

	/**
	 * Costruttore.
	 * 
	 * @param player
	 *            riferimento al giocatore che ha creato questa stanza (vedi
	 *            {@link RemotePlayer}).
	 * 
	 * @param maxPlayers
	 *            numero massimo di giocatori per questa stanza.
	 * @param minPlayers
	 *            numero minimo di giocatori per questa stanza.
	 */
	public Room(RemotePlayer player, int maxPlayers, int minPlayers) {

		roomNumber++;

		if (maxPlayers < this.maxPlayers)
			this.maxPlayers = maxPlayers;
		if (this.minPlayers > minPlayers)
			this.minPlayers = minPlayers;

		players = new ArrayList<>();
		players.add(player);

		canJoin = true;

		mStartLatch = new CountDownLatch(1);

	}

	/**
	 * Ritorna il numero identificatore della singola Stanza
	 * 
	 * @return roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Aggiunge un giocatore alla Stanza .
	 * 
	 * @param player
	 *            riferimento al giocatore che da aggiugere alla Stanza (vedi
	 *            {@link RemotePlayer}).
	 * @throws RoomFullException
	 *             se la stanza è chiusa e nessun altro giocatore può
	 *             partecipare.
	 */
	public void joinPlayer(RemotePlayer player) throws RoomFullException {
		synchronized (ROOM_MUTEX) {
			if (canJoin) {
				players.add(player);

				String message = "Succesfully joined " + player.getNome() + " to room #" + getRoomNumber() + "!";
				try {
					player.onChatMessage("ROOM", message, true);
				} catch (NetworkException e) {
					e.printStackTrace();
				}
				System.out.println(message);

				if (players.size() == maxPlayers) {
					canJoin = false;
					cancelTimer();
					startCountDownTimer(START_IMMEDIATELY);
				} else if (players.size() == minPlayers) {
					startCountDownTimer(ROOM_WAITING_TIME);
				}
			} else {
				throw new RoomFullException();
			}
		}

	}

	/**
	 * Cancel scheduled timer if set.
	 */
	private void cancelTimer() {
		if (timer != null) {
			timer.cancel();
			timer.purge();
		}
	}

	/**
	 * Start the count down timer.
	 *
	 * @param waitingTime
	 *            time to wait until the start of the game.
	 */
	private void startCountDownTimer(long waitingTime) {
		timer = new Timer();
		timer.schedule(new RoomGameHandler(), waitingTime);

		int delay = 1000, period = 1000;
		interval = (int) waitingTime / 1000;

		notifyAllPlayers("Game will start in " + interval + "sec ...");

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				notifyAllPlayers(String.valueOf(setInterval()));
			}
		}, delay, period);

	}

	private void notifyAllPlayers(String message) {
		players.stream().forEach(remotePlayer -> {
			try {
				remotePlayer.onChatMessage("ROOM", message, false);
			} catch (NetworkException e) {
				e.printStackTrace();
			}
		});
	}

	static int interval;

	private final int setInterval() {
		if (interval == 1)
			timer.cancel();
		return --interval;
	}

	/**
	 * // * Configure internal game state. This method is called by the admin
	 * during // * a client to server request. // * // * @param configuration //
	 * * bundle with all configured data from admin player. //
	 */
	// public void configureGame(Configuration configuration) {
	// mWaitingTime = configuration.getWaitingTime();
	// mConfiguration = configuration;
	// Debug.debug("[ROOM] configuration ready");
	// mStartLatch.countDown();
	// }
	//
	// /**
	// * [GAME ACTION #1] Draw politic card.
	// *
	// * @param player
	// * that would draw the card.
	// * @throws PoliticCardAlreadyDrawn
	// * if politic card has been already drawn in this turn.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void drawPoliticCard(RemotePlayer player) throws LogicException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)) {
	// if (!((GameTurn) mTurn).getActionList().isPoliticCardDrawn()) {
	// UpdateState updateState = mGame.drawPoliticCard(player);
	// ((GameTurn) mTurn).setPoliticCardDrawn();
	// mPlayers.forEach(p -> notifyPlayerPoliticCardDrawn(p, updateState));
	// return;
	// }
	// throw new PoliticCardAlreadyDrawn();
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Notify player that a politic card has been drawn by someone.
	// *
	// * @param player
	// * that should be notified of the action.
	// * @param updateState
	// * to send to the player that should be notified.
	// */
	// private void notifyPlayerPoliticCardDrawn(RemotePlayer player,
	// UpdateState updateState) {
	// try {
	// player.onPoliticCardDrawn(updateState);
	// } catch (NetworkException e) {
	// Debug.error("Player is disconnected", e);
	// }
	// }
	//
	// /**
	// * Retrieve a list of all available actions that the player can do.
	// *
	// * @param player
	// * that would get the action list.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void sendActionList(RemotePlayer player) throws
	// NotYourTurnException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)) {
	// try {
	// player.onActionList(((GameTurn) mTurn).getActionList());
	// } catch (NetworkException e) {
	// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Check if player can make a main action.
	// *
	// * @param player
	// * that has made the request.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws MainActionNotAvailable
	// * if player has no main action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// private void checkIfPlayerCanMakeMainAction(RemotePlayer player) throws
	// LogicException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)) {
	// ActionList actionList = ((GameTurn) mTurn).getActionList();
	// if (actionList.isPoliticCardDrawn()) {
	// if (actionList.getMainActionCount() > 0) {
	// return;
	// }
	// throw new MainActionNotAvailable();
	// }
	// throw new PoliticCardNotYetDrawn();
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Check if player can make a fast action.
	// *
	// * @param player
	// * that has made the request.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws FastActionNotAvailable
	// * if player has no fast action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// private void checkIfPlayerCanMakeFastAction(RemotePlayer player) throws
	// LogicException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)) {
	// ActionList actionList = ((GameTurn) mTurn).getActionList();
	// if (actionList.isPoliticCardDrawn()) {
	// if (actionList.getFastActionCount() > 0) {
	// return;
	// }
	// throw new FastActionNotAvailable();
	// }
	// throw new PoliticCardNotYetDrawn();
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * [MAIN ACTION #1] Elect a councillor.
	// *
	// * @param player
	// * that has made the request.
	// * @param councilor
	// * to add to the region balcony.
	// * @param region
	// * where the balcony to satisfy is located.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws MainActionNotAvailable
	// * if player has no main action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void electCouncillor(RemotePlayer player, Councilor councilor,
	// String region) throws LogicException {
	// checkIfPlayerCanMakeMainAction(player);
	// UpdateState updateState = mGame.electCouncillor(player, councilor,
	// region, true);
	// ((GameTurn) mTurn).onMainActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onCouncillorElected(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [MAIN ACTION #2] Acquire a business permit tile.
	// *
	// * @param player
	// * that has made the request.
	// * @param politicCards
	// * list of politic cards to use to satisfy the balcony.
	// * @param region
	// * where the balcony to satisfy is placed.
	// * @param permitTileIndex
	// * index of the permit tile to acquire.
	// * @throws BalconyUnsatisfiable
	// * if none of the given politic cards can satisfy this balcony.
	// * @throws NotEnoughCoins
	// * if player has no enough coins to satisfy this balcony.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws MainActionNotAvailable
	// * if player has no main action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void acquireBusinessPermitTiles(RemotePlayer player,
	// List<PoliticCard> politicCards, String region,
	// int permitTileIndex) throws LogicException {
	// checkIfPlayerCanMakeMainAction(player);
	// UpdateState updateState = mGame.acquireBusinessPermitTile(player,
	// (GameTurn) mTurn, politicCards, region,
	// permitTileIndex);
	// ((GameTurn) mTurn).onMainActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onBusinessPermitTileAcquired(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [MAIN ACTION #3] Build an emporium with given business permit tile on
	// * provided city.
	// *
	// * @param player
	// * that has made the request.
	// * @param businessPermitTile
	// * to use to build the emporium.
	// * @param city
	// * where the emporium should be built.
	// * @throws CityNotFound
	// * if city is not found.
	// * @throws CityNotValid
	// * if provided business permit tile cannot be used to build an
	// * emporium on this city.
	// * @throws EmporiumAlreadyBuilt
	// * if player has already built an emporium on this city.
	// * @throws NotEnoughAssistants
	// * if player has no enough assistants for building this
	// * emporium.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws MainActionNotAvailable
	// * if player has no main action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void buildEmporiumWithBusinessPermitTile(RemotePlayer player,
	// BusinessPermitTile businessPermitTile,
	// String city) throws LogicException {
	// checkIfPlayerCanMakeMainAction(player);
	// UpdateState updateState = mGame.buildEmporium(player, (GameTurn) mTurn,
	// businessPermitTile, city);
	// ((GameTurn) mTurn).onMainActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onEmporiumBuiltWithPermitTile(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [MAIN ACTION #4] Build an emporium with the help of the king.
	// *
	// * @param player
	// * that has made the request.
	// * @param politicCards
	// * list of politic cards to use to satisfy the king's balcony.
	// * @param kingMoves
	// * list of linked cities which represent the king's movements.
	// * @throws BalconyUnsatisfiable
	// * if none of the given politic cards can satisfy this balcony.
	// * @throws NotEnoughCoins
	// * if player has no enough coins to satisfy this balcony and
	// * move the king.
	// * @throws NotEnoughAssistants
	// * if player has no enough assistants for building this
	// * emporium.
	// * @throws CityNotFound
	// * if a city is not recognized or cannot find current king city.
	// * @throws RouteNotValid
	// * is provided route is not valid or king is not in initial
	// * city.
	// * @throws EmporiumAlreadyBuilt
	// * if player has already built an emporium on this city.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws MainActionNotAvailable
	// * if player has no main action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void buildEmporiumWithKingHelp(RemotePlayer player,
	// List<PoliticCard> politicCards, List<String> kingMoves)
	// throws LogicException {
	// checkIfPlayerCanMakeMainAction(player);
	// UpdateState updateState = mGame.buildEmporium(player, (GameTurn) mTurn,
	// politicCards, kingMoves);
	// ((GameTurn) mTurn).onMainActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onEmporiumBuiltWithKingHelp(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [FAST ACTION #1] Engage an assistant.
	// *
	// * @param player
	// * that has made the request.
	// * @throws NotEnoughCoins
	// * if player has no enough coins to engage the assistant.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws FastActionNotAvailable
	// * if player has no fast action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void engageAssistant(RemotePlayer player) throws LogicException {
	// checkIfPlayerCanMakeFastAction(player);
	// UpdateState updateState = mGame.engageAssistant(player);
	// ((GameTurn) mTurn).onFastActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onAssistantEngaged(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [FAST ACTION #2] Change business permit tiles.
	// *
	// * @param player
	// * that has made the request.
	// * @param region
	// * where the business permit tiles should be changed.
	// * @throws NotEnoughAssistants
	// * if player has no enough assistants.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws FastActionNotAvailable
	// * if player has no fast action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void changeBusinessPermitTiles(RemotePlayer player, String region)
	// throws LogicException {
	// checkIfPlayerCanMakeFastAction(player);
	// UpdateState updateState = mGame.changeBusinessPermitTiles(player,
	// region);
	// ((GameTurn) mTurn).onFastActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onBusinessPermitTilesChanged(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [FAST ACTION #3] Send assistant to elect a councillor.
	// *
	// * @param player
	// * that has made the request.
	// * @param councilor
	// * that should be elected.
	// * @param region
	// * where the councillor should be added.
	// * @throws NotEnoughAssistants
	// * if player has no enough assistants.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws FastActionNotAvailable
	// * if player has no fast action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void sendAssistantToElectCouncillor(RemotePlayer player, Councilor
	// councilor, String region)
	// throws LogicException {
	// checkIfPlayerCanMakeFastAction(player);
	// UpdateState updateState = mGame.sendAssistantToElectCouncillor(player,
	// councilor, region);
	// ((GameTurn) mTurn).onFastActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onAssistantSentToElectCouncillor(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * [FAST ACTION #4] Perform an additional main action.
	// *
	// * @param player
	// * that has made the request.
	// * @throws NotEnoughAssistants
	// * if player has no enough assistants.
	// * @throws PoliticCardNotYetDrawn
	// * if player should draw a politic card before.
	// * @throws FastActionNotAvailable
	// * if player has no fast action available.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void performAdditionalMainAction(RemotePlayer player) throws
	// LogicException {
	// checkIfPlayerCanMakeFastAction(player);
	// UpdateState updateState = mGame.performAdditionalMainAction(player);
	// ((GameTurn) mTurn).addAdditionalMainAction();
	// ((GameTurn) mTurn).onFastActionDone();
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onAdditionalMainActionGranted(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// }
	//
	// /**
	// * Special reward 1: "Obtain the bonus of a reward token from a city where
	// * you have an emporium. You cannot choose one of the tokens which advance
	// * you along the nobility track".
	// *
	// * @param player
	// * that want to earn a special reward.
	// * @param cities
	// * where the reward token are placed.
	// * @throws CityNotFound
	// * if one of the provided city is not found on the game state.
	// * @throws CityNotValid
	// * if a city is provided more than one time, if the player has
	// * no emporium in one city or if the reward of that city let
	// * player to move along the nobility track.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void earnFirstSpecialRewards(RemotePlayer player, List<String>
	// cities) throws LogicException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)
	// && ((GameTurn) mTurn).getFirstSpecialBonusCount() > 0) {
	// UpdateState updateState = mGame.earnFirstSpecialRewards(player,
	// (GameTurn) mTurn, cities);
	// ((GameTurn) mTurn).decrementFirstSpecialRewards(cities.size());
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onFirstSpecialRewardsEarned(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Special reward 2: "You can take a face up building permit tile without
	// * paying the cost".
	// *
	// * @param player
	// * that want to earn a special reward.
	// * @param regions
	// * where the player want to take a business permit tile.
	// * @param indices
	// * related to the same index region that identify the permit tile
	// * that the player wants.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void earnSecondSpecialRewards(RemotePlayer player, List<String>
	// regions, List<Integer> indices)
	// throws NotYourTurnException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)
	// && ((GameTurn) mTurn).getSecondSpecialBonusCount() > 0) {
	// UpdateState updateState = mGame.earnSecondSpecialRewards(player,
	// (GameTurn) mTurn, regions, indices);
	// ((GameTurn) mTurn).decrementSecondSpecialRewards(regions.size());
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onSecondSpecialRewardsEarned(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Special reward 3: "You receive the bonus of one of the business permit
	// * tiles which you previously bought (also a face down tile)".
	// *
	// * @param player
	// * that want to earn a special reward.
	// * @param businessPermitTiles
	// * list of player business permit tiles that he want to re use to
	// * get the related reward.
	// * @throws NotYourTurnException
	// * if is not current game turn of the player.
	// */
	// public void earnThirdSpecialRewards(RemotePlayer player,
	// List<BusinessPermitTile> businessPermitTiles)
	// throws NotYourTurnException {
	// if (mTurn instanceof GameTurn && mTurn.isCurrentPlayer(player)
	// && ((GameTurn) mTurn).getThirdSpecialBonusCount() > 0) {
	// UpdateState updateState = mGame.earnThirdSpecialRewards(player,
	// (GameTurn) mTurn, businessPermitTiles);
	// ((GameTurn)
	// mTurn).decrementThirdSpecialRewards(businessPermitTiles.size());
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onThirdSpecialRewardsEarned(updateState);
	// } catch (NetworkException e) {
	// Debug.debug(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Force stop current player turn.
	// *
	// * @param player
	// * that is making the request.
	// * @throws NotYourTurnException
	// * if the player that is making the request is not the current
	// * player.
	// */
	// public void endTurn(RemotePlayer player) throws NotYourTurnException {
	// if (mTurn.isCurrentPlayer(player)) {
	// mTurn.stopCountDown();
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Sell a politic card over the market.
	// *
	// * @param player
	// * that is selling the card.
	// * @param politicCard
	// * that should be sold.
	// * @param coins
	// * that the player would get to sell the card.
	// * @throws ItemNotFound
	// * if the provided item is not found on player repository.
	// * @throws ItemAlreadyOnSale
	// * if the provided item is already on sale.
	// * @throws NotYourTurnException
	// * if the player that is making the request is not the current
	// * player.
	// */
	// public void sellPoliticCard(RemotePlayer player, PoliticCard politicCard,
	// int coins) throws LogicException {
	// if (mTurn instanceof MarketTurn && ((MarketTurn) mTurn).getMode() ==
	// MarketTurn.Mode.SELL
	// && mTurn.isCurrentPlayer(player)) {
	// Item item = mMarketSession.sellPoliticCard(player, politicCard, coins);
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onMarketItemAddedOnSale(item);
	// } catch (NetworkException e) {
	// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Sell a business permit tile over the market.
	// *
	// * @param player
	// * that is selling the card.
	// * @param permitTile
	// * that should be sold.
	// * @param coins
	// * that the player would get to sell the card.
	// * @throws ItemNotFound
	// * if the provided item is not found on player repository.
	// * @throws ItemAlreadyOnSale
	// * if the provided item is already on sale.
	// * @throws NotYourTurnException
	// * if the player that is making the request is not the current
	// * player.
	// */
	// public void sellBusinessPermitTile(RemotePlayer player,
	// BusinessPermitTile permitTile, int coins)
	// throws LogicException {
	// if (mTurn instanceof MarketTurn && ((MarketTurn) mTurn).getMode() ==
	// MarketTurn.Mode.SELL
	// && mTurn.isCurrentPlayer(player)) {
	// Item item = mMarketSession.sellBusinessPermitTile(player, permitTile,
	// coins);
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onMarketItemAddedOnSale(item);
	// } catch (NetworkException e) {
	// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Sell an assistant over the market.
	// *
	// * @param player
	// * that is selling the assistant.
	// * @param coins
	// * that the player would get to sell the assistant.
	// * @throws ItemNotFound
	// * if the provided item is not found on player repository.
	// * @throws ItemAlreadyOnSale
	// * if the provided item is already on sale.
	// * @throws NotYourTurnException
	// * if the player that is making the request is not the current
	// * player.
	// */
	// public void sellAssistant(RemotePlayer player, int coins) throws
	// LogicException {
	// if (mTurn instanceof MarketTurn && ((MarketTurn) mTurn).getMode() ==
	// MarketTurn.Mode.SELL
	// && mTurn.isCurrentPlayer(player)) {
	// Item item = mMarketSession.sellAssistant(player, coins);
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onMarketItemAddedOnSale(item);
	// } catch (NetworkException e) {
	// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }
	//
	// /**
	// * Buy a market item.
	// *
	// * @param player
	// * that is buying the item.
	// * @param marketId
	// * id of the item the player would buy.
	// * @throws ItemNotFound
	// * if the player that is making the request is not the current
	// * player.
	// * @throws ItemAlreadySold
	// * if the player that is making the request is not the current
	// * player.
	// * @throws NotEnoughCoins
	// * if the player that is making the request is not the current
	// * player.
	// * @throws NotYourTurnException
	// * if the player that is making the request is not the current
	// * player.
	// */
	// public void buyItem(RemotePlayer player, String marketId) throws
	// LogicException {
	// if (mTurn instanceof MarketTurn && ((MarketTurn) mTurn).getMode() ==
	// MarketTurn.Mode.BUY
	// && mTurn.isCurrentPlayer(player)) {
	// mMarketSession.buyItem(player, marketId, mGame);
	// for (RemotePlayer remotePlayer : mPlayers) {
	// try {
	// remotePlayer.onMarketItemBought(player.getNickname(), marketId);
	// } catch (NetworkException e) {
	// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
	// }
	// }
	// return;
	// }
	// throw new NotYourTurnException();
	// }

	/**
	 * Send a chat message to all players or to a specific player.
	 * 
	 * @param player
	 *            that is sending the message.
	 * @param receiver
	 *            nickname of the player that should receive the message. If
	 *            null the message will be dispatched to all players.
	 * @param message
	 *            to send.
	 * @throws PlayerNotFound
	 *             if the receiver is not null and not match any players in the
	 *             room.
	 */
	public void sendChatMessage(RemotePlayer player, String receiver, String message) throws PlayerNotFound {
		if (receiver != null) {
			for (RemotePlayer remotePlayer : players) {
				if (receiver.equals(remotePlayer.getNome())) {
					sendChatMessage(remotePlayer, player.getNome(), message, true);
					return;
				}
			}
			throw new PlayerNotFound();
		} else {
			players.stream().filter(remotePlayer -> remotePlayer != player)
					.forEach(remotePlayer -> sendChatMessage(remotePlayer, player.getNome(), message, false));
		}
	}

	/**
	 * Send a chat message safely to a player.
	 * 
	 * @param player
	 *            that should receive the message.
	 * @param author
	 *            nickname of the player that sent the message.
	 * @param message
	 *            body that should be sent.
	 * @param privateMessage
	 *            if message is private, false if public.
	 */
	private void sendChatMessage(RemotePlayer player, String author, String message, boolean privateMessage) {
		try {
			player.onChatMessage(author, message, privateMessage);
		} catch (NetworkException e) {
			System.err.println("PLAYER_DISCONNECTED");
			// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		}
	}

	/**
	 * This class represent the game logic to execute during a match.
	 */
	private class RoomGameHandler
			extends TimerTask /* implements Turn.TurnCallback */ {

		/**
		 * Called by Timer when time is expired.
		 */
		@Override
		public void run() {
			notifyAllPlayers("Game started!");

			System.out.println("[ROOM] Starting room thread, closing room");
			closeRoomSafely();
			// Debug.verbose("[ROOM] Creating game session");
			// createGameSession();
			// Debug.verbose("[ROOM] Room closed, %d players", mPlayers.size());
			// dispatchGameSession();
			// Debug.verbose("[ROOM] Game dispatched. Starting with logic.");
			while (true) {
				// RemotePlayer player = startGameSession();
				// if (player == null) {
				// startMarketSession();
				// } else {
				// doLastTurnSession(player);
				// break;
				// }
			}
			// Debug.verbose("[ROOM] Game is finished, handling the end of the
			// match.");
			// handleEndOfTheMatch();
			// Debug.verbose("[ROOM] Room game finished.");
		}

		//
		/**
		 * Close the room avoiding to block in deadlock the other players that
		 * are trying to join at this time.
		 */
		private void closeRoomSafely() {
			synchronized (ROOM_MUTEX) {
				canJoin = false;
			}
		}
		//
		// /**
		// * Create game session from configuration.
		// */
		// private void createGameSession() {
		// try {
		// mStartLatch.await();
		// } catch (InterruptedException e) {
		// Thread.currentThread().interrupt();
		// Debug.critical(e);
		// }
		// mGame = Configurator.getGameInstance(mPlayers, mConfiguration);
		// }
		//
		// /**
		// * Wait until the admin has finished to setup the game session, than
		// it
		// * will dispatch a small copy of it to all players of the room. This
		// * copy will not contain cards information that should stay only on
		// the
		// * server for security reasons.
		// */
		// private void dispatchGameSession() {
		// Debug.verbose("[ROOM] Game ready, dispatching base game to all
		// players");
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.dispatchGameSession(mGame);
		// } catch (NetworkException e) {
		// Debug.debug("Player has disconnected", e);
		// }
		// }
		// }
		//
		// /**
		// * Start standard turn of every player.
		// *
		// * @return a player reference is one of the players has finished his
		// * emporiums after the turn.
		// */
		// private RemotePlayer startGameSession() {
		// Debug.verbose("[ROOM] Stating game session");
		// for (RemotePlayer player : mPlayers) {
		// if (player.isOnline()) {
		// Debug.verbose("[ROOM] Stating %s game turn", player.getNickname());
		// startPlayerTurnSession(player);
		// if (player.getEmporiums() == 0) {
		// Debug.verbose("[ROOM] Player %s has finished his emporiums!",
		// player.getNickname());
		// return player;
		// }
		// } else {
		// Debug.verbose("[ROOM] Skipping game turn of offline player %s",
		// player.getNickname());
		// }
		// }
		// return null;
		// }
		//
		// /**
		// * Iterate all players that not matches with the provided one and
		// start
		// * their last turn.
		// *
		// * @param skipPlayer
		// * player that finished his emporiums and should not be
		// * called.
		// */
		// private void doLastTurnSession(RemotePlayer skipPlayer) {
		// Debug.verbose("[ROOM] Starting last turn session");
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.onLastTurn(skipPlayer.getNickname());
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// }
		// }
		// int skipIndex = mPlayers.indexOf(skipPlayer);
		// for (int i = 1; i < mPlayers.size(); i++) {
		// if (i != skipIndex) {
		// int fixedIndex = (i + skipIndex) % mPlayers.size();
		// startPlayerTurnSession(mPlayers.get(fixedIndex));
		// }
		// }
		// }
		//
		// /**
		// * Start market turn of every player.
		// */
		// private void startMarketSession() {
		// Debug.verbose("[ROOM] Stating market session");
		// mMarketSession = new MarketSession();
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.onMarketSessionStarted();
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// }
		// }
		// for (RemotePlayer player : mPlayers) {
		// Debug.verbose("[ROOM] Stating %s market selling turn",
		// player.getNickname());
		// startPlayerMarketSession(player, MarketTurn.Mode.SELL);
		// }
		// List<Integer> playerIndices = generateRandomPlayerIndices();
		// for (int playerIndex : playerIndices) {
		// if (!mMarketSession.isMarketFinished()) {
		// RemotePlayer player = mPlayers.get(playerIndex);
		// Debug.verbose("[ROOM] Stating %s market buying turn",
		// player.getNickname());
		// startPlayerMarketSession(player, MarketTurn.Mode.BUY);
		// } else {
		// break;
		// }
		// }
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.onMarketSessionFinished();
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// }
		// }
		// }
		//
		// /**
		// * Generate random player indices.
		// *
		// * @return a list composed by random player indices.
		// */
		// private List<Integer> generateRandomPlayerIndices() {
		// List<Integer> indices = new ArrayList<>();
		// for (int i = 0; i < mPlayers.size(); i++) {
		// indices.add(i);
		// }
		// Collections.shuffle(indices);
		// return indices;
		// }
		//
		// /**
		// * Send a notification to the player to start the turn and block the
		// * thread until the turn ends.
		// *
		// * @param player
		// * that should start his turn.
		// */
		// private void startPlayerTurnSession(RemotePlayer player) {
		// mTurn = new GameTurn(player, this);
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.onGameTurnStarted(player.getNickname(), mWaitingTime);
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// }
		// }
		// mTurn.startCountDown(mWaitingTime);
		// }
		//
		// /**
		// * Send a notification to the player to start the market turn and
		// block
		// * the thread until the turn ends.
		// *
		// * @param player
		// * that should start his turn.
		// * @param marketMode
		// * mode of the market turn.
		// */
		// private void startPlayerMarketSession(RemotePlayer player,
		// MarketTurn.Mode marketMode) {
		// if (!player.isOnline()) {
		// Debug.verbose("[ROOM] Skipping market turn of offline player %s",
		// player.getNickname());
		// return;
		// }
		// mTurn = new MarketTurn(player, marketMode, this);
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.onMarketTurnStarted(player.getNickname(), mWaitingTime,
		// marketMode);
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// }
		// }
		// mTurn.startCountDown(mWaitingTime);
		// }
		//
		// /**
		// * Apply bonus of every player, calculate the ranking and dispatch it
		// to
		// * all players.
		// */
		// private void handleEndOfTheMatch() {
		// UpdateState[] updateStates = mGame.applyEndGameBonus();
		// List<String> nicknames = mGame.generateRanking();
		// for (RemotePlayer remotePlayer : mPlayers) {
		// try {
		// remotePlayer.onGameEnd(updateStates, nicknames);
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// }
		// }
		// }
		//
		// /**
		// * Callback method of the turn class. This should send a notification
		// to
		// * the provided player to notify that the countdown is changed.
		// *
		// * @param player
		// * that is playing the turn.
		// * @param remainingTime
		// * remaining time to wait in seconds.
		// */
		// @Override
		// public void onUpdateCountdown(RemotePlayer player, int remainingTime)
		// {
		// try {
		// player.onUpdateTurnCountdown(remainingTime);
		// player.setOnline(true);
		// } catch (NetworkException e) {
		// Debug.error(DEBUG_PLAYER_DISCONNECTED, e);
		// player.setOnline(false);
		// }
		// if (remainingTime <= 0 && !player.isOnline()) {
		// Debug.debug("[room] notifying all players that player %s is offline",
		// player.getNickname());
		// notifyPlayerDisconnected(player.getNickname());
		// }
		// }
		//
		// /**
		// * Notify all players that player has disconnected.
		// *
		// * @param nickname
		// * of the player that has disconnected.
		// */
		// private void notifyPlayerDisconnected(String nickname) {
		// mPlayers.stream().filter(remotePlayer ->
		// !remotePlayer.getNickname().equals(nickname))
		// .forEach(remotePlayer -> {
		// try {
		// remotePlayer.onPlayerDisconnected(nickname);
		// } catch (NetworkException e) {
		// Debug.debug(String.format("[ROOM] Cannot notify player that player %s
		// is
		// disconnected",
		// nickname), e);
		// }
		// });
		// }
	}
}