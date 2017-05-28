package main.network.server.game;

import java.util.*;

import main.model.Partita;
import main.network.NetworkException;
import main.network.exceptions.PlayerNotFound;
import main.network.server.RemotePlayer;
import main.util.Costants;
import main.util.EAzioniGiocatore;

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
	 * Numero minimo di giocatori affinch� possa partire una partita.
	 */
	private int minPlayers = Costants.ROOM_MIN_PLAYERS;

	/**
	 * Numero massimo di giocatori gestibili da una partita.
	 */
	private int maxPlayers = Costants.ROOM_MAX_PLAYERS;

	/**
	 * Tempo di attesa di altri giocatori all'interno della Stanza prima che una
	 * nuova partita inizi automaticamente.
	 */
	private static final long ROOM_WAITING_TIME = Costants.ROOM_WAITING_TIME * 1000L;

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
	 * Flag usato per indicare quando una Stanza � ancora aperta (True) o chiusa
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
	 *             se la stanza � chiusa e nessun altro giocatore pu�
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
		gameTimer.schedule(new RoomGameHandler(), waitingTime);

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
		}
	}

	public void performGameAction(RemotePlayer remotePlayer, EAzioniGiocatore act) {
		UpdateStats updateState = game.performGameAction(remotePlayer, act);
		players.stream().forEach(p -> {
			try {
				p.onGameUpdate(updateState);
			} catch (NetworkException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	/**
	 * This class represent the game logic to execute during a match.
	 */
	private class RoomGameHandler extends TimerTask {

		/**
		 * ID usato per identificare la stanza nei log del Server.
		 */
		private final String ID = "ROOM#" + roomNumber;

		/**
		 * Flag usato per abilitare il Log sul Server.
		 */
		private final boolean LOG_ENABLED = Costants.ROOM_ENABLE_LOG;

		/**
		 * Metodo chiamato dal {@link TimerTask} quando scade il timer.
		 */
		@Override
		public void run() {
			initializeRoomHandler();

			Room.this.game.waitGameEnd();

			cleanRoomHandler();
		}

		/**
		 * Inizializza una nuova Partita chiudendo l'accesso alla Stanza a nuovi
		 * Client.
		 */
		private void initializeRoomHandler() {
			logToAllPlayers("Game started!");

			log("Starting room thread, closing room");
			synchronized (ROOM_MUTEX) {
				canJoin = false;
			}

			log("Creating game session");
			Room.this.game = new Game(Room.this.players);

			log("Room closed, " + players.size() + " players in");
		}

		/**
		 * Rimuove la Partita associata alla Stanza riaprendo l'accesso alla
		 * Stanza a nuovi Client.
		 */
		private void cleanRoomHandler() {
			log("Deleting game session");
			Room.this.game = null;

			log("Ending room thread, opening room");
			synchronized (ROOM_MUTEX) {
				canJoin = true;
			}
		}

		/**
		 * Metodo interno usato per il Log sul Server (abilitato da:
		 * LOG_ENABLED)
		 * 
		 * @param message
		 */
		private void log(String message) {
			if (LOG_ENABLED)
				System.out.println("[" + ID + "] " + message);
		}
	}

	/**
	 * Classe per la gestione di countdown e relativa notifica ai giocatori
	 */
	private class RoomCountDownHandler extends TimerTask {

		/**
		 * Valore attuale del countdown.
		 */
		private int interval;

		/**
		 * Costruttore.
		 * 
		 * @param interval
		 *            valore a cui inizializzare il countdown.
		 */
		public RoomCountDownHandler(int interval) {
			this.interval = interval;
		}

		/*
		 * Metodo chiamato dal {@link TimerTask}.
		 */
		@Override
		public void run() {
			logToAllPlayers(String.valueOf(setInterval()));
		}

		/**
		 * Decrementa il contatore.
		 * 
		 * @return interval valore attuale del countdown
		 */
		private final int setInterval() {
			if (interval == 1)
				countDownTimer.cancel();
			return --interval;
		}
	}

}