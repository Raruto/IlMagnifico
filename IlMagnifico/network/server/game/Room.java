package network.server.game;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import LorenzoIlMagnifico.Partita;
import network.NetworkException;
import network.server.RemotePlayer;

import network.exceptions.PlayerNotFound;
import network.protocol.PlayerColors;

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
	 * Timer utilizzato per il countdown di inizio partita.
	 */
	private Timer gameTimer;

	/**
	 * Timer utilizzato per i countdown (notifica ai giocatori).
	 */
	private Timer countDownTimer;

	/**
	 * Flag usato per indicare quando una Stanza è ancora aperta (True) o chiusa
	 * (False) all'aggiunta di nuovi giocatori.
	 */
	private boolean canJoin;

	/**
	 * Stato completo della partita.
	 */
	private Game game;

	/**
	 * ID usato per identificare la stanza nelle comunicazioni
	 */
	private static final String ID = "ROOM";

	/**
	 * Costruttore.
	 * 
	 * @param player
	 *            riferimento al giocatore che ha creato questa stanza (vedi
	 *            {@link RemotePlayer}).
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

		// mStartLatch = new CountDownLatch(1);

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

				String message = "Succesfully joined " + player.getNome() + " to room #" + roomNumber + "!";
				logToPlayer(player, message);
				System.out.println(message);

				if (players.size() == maxPlayers) {
					canJoin = false;
					resetTimers();
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
	 * Resetta il timer se programmato.
	 */
	private void resetTimers() {
		if (countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer.purge();
		}
		if (gameTimer != null) {
			gameTimer.cancel();
			gameTimer.purge();
		}

	}

	/**
	 * Fa partire il timer di conteggio per l'inizio della partita.
	 *
	 * @param waitingTime
	 *            tempo da attendere prima dell'inizio del gioco.
	 */
	private void startCountDownTimer(long waitingTime) {
		int countDownDelay = 1000, countDownPeriod = 1000;
		int countDownInterval = (int) waitingTime / 1000;

		logToAllPlayers("Game will start in " + countDownInterval + "sec...");

		// Gestore Partita
		gameTimer = new Timer();
		gameTimer.schedule(new RoomGameHandler(this, game), waitingTime);

		countDownTimer = new Timer();
		// Gestore notifica Countdown
		countDownTimer.scheduleAtFixedRate(new RoomCountDownHandler(countDownInterval), countDownDelay,
				countDownPeriod);
	}

	private void logToPlayer(RemotePlayer player, String message, boolean privateMessage) {
		try {
			player.onChatMessage(ID, message, privateMessage);
		} catch (NetworkException e) {
			e.printStackTrace();
		}
	}

	private void logToPlayer(RemotePlayer player, String message) {
		logToPlayer(player, message, true);
	}

	private void logToAllPlayers(String message) {
		players.stream().forEach(remotePlayer -> {
			logToPlayer(remotePlayer, message, false);
		});
	}

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
	private class RoomGameHandler extends TimerTask {

		private final String ID = "ROOM#" + roomNumber;
		private final boolean LOG_ENABLED = true;

		private Room room;
		private Game game;

		public RoomGameHandler(Room room, Game game) {
			this.room = room;
			this.game = game;
		}

		/**
		 * Called by Timer when time is expired.
		 */
		@Override
		public void run() {
			initializeRoomHandler();

			game.waitGameEnd();

			cleanRoomHandler();
		}

		private void initializeRoomHandler() {
			logToAllPlayers("Game started!");

			log("Starting room thread, closing room");
			closeRoomSafely();

			log("Creating game session");
			createGameSession();

			log("Room closed, " + players.size() + " players in");
		}

		private void cleanRoomHandler() {
			log("Deleting game session");
			cleanGameSession();

			log("Ending room thread, opening room");
			openRoomSafely();
		}

		/**
		 * Close the room avoiding to block in deadlock the other players that
		 * are trying to join at this time.
		 */
		private void closeRoomSafely() {
			synchronized (ROOM_MUTEX) {
				canJoin = false;
			}
		}

		/**
		 * Open the room avoiding to block in deadlock the other players that
		 * are trying to join at this time.
		 */
		private void openRoomSafely() {
			synchronized (ROOM_MUTEX) {
				canJoin = true;
			}
		}

		/**
		 * Create game session from configuration.
		 */
		private void createGameSession() {
			game = new Game();
			/*
			 * try { mStartLatch.await(); } catch (InterruptedException e) {
			 * Thread.currentThread().interrupt(); Debug.critical(e); } mGame =
			 * Configurator.getGameInstance(mPlayers, mConfiguration);
			 */

		}

		private void cleanGameSession() {
			game = null;
		}

		private void log(String message) {
			if (LOG_ENABLED)
				System.out.println("[" + ID + "] " + message);
		}

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

	/**
	 * Classe per la gestione di countdown e relativa notifica ai giocatori
	 */
	private class RoomCountDownHandler extends TimerTask {

		private int interval = 0;

		public RoomCountDownHandler(int interval) {
			this.interval = interval;
		}

		@Override
		public void run() {
			logToAllPlayers(String.valueOf(setInterval()));
		}

		private final int setInterval() {
			if (interval == 1)
				countDownTimer.cancel();
			return --interval;
		}

	}
}