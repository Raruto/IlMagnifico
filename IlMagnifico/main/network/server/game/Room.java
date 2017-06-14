package main.network.server.game;

import java.util.*;

import main.model.Giocatore;
import main.model.enums.EAzioniGiocatore;
import main.model.errors.Errors;
import main.network.NetworkException;
import main.network.exceptions.PlayerNotFound;
import main.network.server.game.exceptions.GameException;
import main.network.server.game.exceptions.RoomFullException;
import main.network.server.rmi.RMIPlayer;
import main.network.server.socket.SocketPlayer;
import main.util.ANSI;
import main.util.Costants;

/**
 * Classe per la gestione di una singola Stanza sul server. Ogni Stanza gestisce
 * una partita e un insieme di giocatori associati ad essa.
 */
public class Room {

	/**
	 * Flag usato per abilitare il Log sul Server.
	 */
	private final boolean LOG_ENABLED = Costants.ROOM_ENABLE_LOG;

	/**
	 * Numero identificatore della singola stanza (incrementato ogni volta che
	 * viene creata una nuova Stanza)
	 */
	private static int roomNumber = 0;

	/**
	 * Numero minimo di giocatori affinche' possa partire una partita.
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
	private static final int ROOM_WAITING_TIME = Costants.ROOM_WAITING_TIME;

	/**
	 * Costante associata ad un tempo di attesa nullo (usata per far iniziare
	 * una nuova partita automaticamente quando si raggiunge il numero massimo
	 * di giocatori gestibili).
	 */
	private static final int START_IMMEDIATELY = 0;

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
	 * Timer utilizzato per i countdown all'interno della Stanza.
	 */
	private Timer timer;

	/**
	 * Flag usato per indicare quando una Stanza e' ancora aperta (True) o
	 * chiusa (False) all'aggiunta di nuovi giocatori.
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

	/*
	 * Ritorna lo stato della Stanza.
	 * 
	 * @return canJoin
	 */
	public boolean isJoinable() {
		return canJoin;
	}

	/**
	 * Ritorna la lista dei giocatori (vedi {@link RemotePlayer}) attualmente
	 * presenti nella Stanza.
	 * 
	 * @return ArrayList<RemotePlayer>
	 */
	public ArrayList<RemotePlayer> getPlayers() {
		return this.players;
	}

	/**
	 * Aggiunge un giocatore alla Stanza .
	 * 
	 * @param player
	 *            riferimento al giocatore che da aggiungere alla Stanza (vedi
	 *            {@link RemotePlayer}).
	 * @throws RoomFullException
	 *             se la stanza e' chiusa e nessun altro giocatore puo'
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
					resetTimer();
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
	private void resetTimer() {
		if (timer != null) {
			timer.cancel();
			timer.purge();
		}
	}

	/**
	 * Fa partire il timer di conteggio per l'inizio della partita.
	 *
	 * @param waitingTime
	 *            tempo da attendere prima dell'inizio del gioco.
	 */
	private void startCountDownTimer(int waitingTime) {
		int countDownDelay = 1000, countDownPeriod = 1000;
		int countDownInterval = waitingTime;

		logToAllPlayers("Game will start in " + countDownInterval + "sec...");

		timer = new Timer();
		timer.scheduleAtFixedRate(new RoomCountDownHandler(countDownInterval, new RoomGameHandler(), null),
				countDownDelay, countDownPeriod);
	}

	/**
	 * Metodo usato per inviare un messaggio testuale di notifica, a UN SOLO
	 * giocatore. Simile a
	 * {@link #sendChatMessage(RemotePlayer, String, String)}, utile per
	 * identificare la stanza attraverso il suo {@link #roomNumber}.
	 * 
	 * @param player
	 * @param message
	 */
	private void logToPlayer(RemotePlayer player, String message) {
		try {
			player.onChatMessage(ID, message);
		} catch (NetworkException e) {
			System.err.println("PLAYER_DISCONNECTED: \"" + player.getNome() + "\"\n(" + e.getMessage() + ")");
		}
	}

	/**
	 * Metodo usato per inviare un messaggio testuale di notifica, a TUTTI
	 * giocatori. Simile a
	 * {@link #sendChatMessage(RemotePlayer, String, String)}, utile per
	 * identificare la stanza attraverso il suo {@link #roomNumber}.
	 * 
	 * @param message
	 */
	private void logToAllPlayers(String message) {
		players.stream().forEach(remotePlayer -> {
			logToPlayer(remotePlayer, message);
		});
		log(message);
	}

	/**
	 * Metodo usato per inviare un messaggio testuale di notifica, a TUTTI
	 * TRANNE UN giocatore. Simile a
	 * {@link #sendChatMessage(RemotePlayer, String, String)}, utile per
	 * identificare la stanza attraverso il suo {@link #roomNumber}.
	 * 
	 * @param expectThisPlayer
	 * @param message
	 */
	private void logToAllPlayersExceptOne(RemotePlayer expectThisPlayer, String message) {
		players.stream().forEach(remotePlayer -> {
			if (!remotePlayer.equals(expectThisPlayer))
				logToPlayer(remotePlayer, message);
		});
		log(message);
	}

	/**
	 * Invia un messaggio di chat a tutti i giocatori o un giocatore specifico.
	 * 
	 * @param player
	 *            MITTENTE del messaggio.
	 * @param receiver
	 *            nome del DESTINATARIO del messaggio. Se null il messaggio
	 *            verra' inviato a tutti i giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws PlayerNotFound
	 *             se il destinatario non non corrisponde a nessun giocatore
	 *             presente sul server.
	 */
	public void sendChatMessage(RemotePlayer player, String receiver, String message) throws PlayerNotFound {
		if (receiver != null) {
			for (RemotePlayer remotePlayer : players) {
				if (receiver.equals(remotePlayer.getNome())) {
					try {
						remotePlayer.onChatMessage(player.getNome(), message);
					} catch (NetworkException e) {
						logToAllPlayersExceptOne(player, "PLAYER_DISCONNECTED: \"" + player.getNome() + "\"");
					}
					return;
				}
			}
			throw new PlayerNotFound();
		} else {
			players.stream().filter(remotePlayer -> remotePlayer != player).forEach(remotePlayer -> {
				try {
					remotePlayer.onChatMessage(player.getNome(), message);
				} catch (NetworkException e) {
					logToAllPlayersExceptOne(player, "PLAYER_DISCONNECTED: \"" + player.getNome() + "\"");
				}
			});
		}
	}

	/* public */ void dispatchGameUpdate(UpdateStats update) {
		players.stream().forEach(p -> {
			try {
				p.onGameUpdate(update);
			} catch (NetworkException e) {
				logToAllPlayersExceptOne(p, ANSI.YELLOW + "PLAYER_DISCONNECTED: \"" + p.getNome() + "\"" + ANSI.RESET);
			}
		});
	}

	/**
	 * Metodo invocato dai Client ogni qualvolta vogliano eseguire un'azione di
	 * gioco presso il server.
	 * 
	 * @param remotePlayer
	 *            oggetto {@link RemotePlayer} (es. {@link RMIPlayer} oppure
	 *            {@link SocketPlayer}) che rappresenta il giocatore (vedi
	 *            {@link Giocatore}) che sta effettuando la richiesta di
	 *            svolgere l'azione di gioco.
	 * @param requestedAction
	 *            oggetto {@link UpdateStats} contenete tutte le informazioni
	 *            necessarie al server per comprendere il tipo di richiesta (es.
	 *            deve contenere un {@link EAzioniGiocatore} che codifica il
	 *            tipo di azione richiesta).
	 * @throws GameException
	 *             nel qual caso il giocatore stesse tentando di eseguire
	 *             un'azione di gioco illegale presso il server (vedi
	 *             {@link GameException} e {@link Errors} per maggiori
	 *             informazioni a riguardo delle possibili azioni illegali).
	 */
	public void performGameAction(RemotePlayer remotePlayer, UpdateStats requestedAction) throws GameException {
		try {
			game.performGameAction(remotePlayer, requestedAction);
		} catch (NullPointerException e) {
			if (game == null)
				throw new GameException(Errors.GAME_NOT_STARTED.toString());
			else
				e.printStackTrace();
		}
	}

	/**
	 * Metodo interno usato per il Log sul Server (abilitato da: LOG_ENABLED)
	 * 
	 * @param message
	 */
	public void log(String message) {
		if (LOG_ENABLED)
			System.out.println("[" + ID + "#" + roomNumber + "] " + message);
	}

	/**
	 * Classe per la gestione in "mutua esclusione" della Partita all'interno
	 * della Stanza (una Partita alla volta per Stanza).
	 */
	private class RoomGameHandler implements Runnable {
		/**
		 * Metodo chiamato dal {@link TimerTask} quando scade il timer.
		 */
		@Override
		public void run() {
			initializeRoomHandler();

			Room.this.game.startNewGame();
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

			Room.this.game = new Game(Room.this);

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
	}

	/**
	 * Classe per la gestione di countdown e relativa notifica ai giocatori
	 */
	private class RoomCountDownHandler extends TimerTask {

		/**
		 * Thread da eseguire allo scadere del countdown.
		 */
		private Runnable task;

		/**
		 * Valore attuale del countdown.
		 */
		private int interval;

		/**
		 * Giocatore a cui notificare il countdown (se null verra' notificato a
		 * tutti i giocatori della Stanza).
		 */
		private RemotePlayer player;

		/**
		 * Costruttore.
		 * 
		 * @param interval
		 *            valore a cui inizializzare il countdown.
		 * @param runnable
		 *            thread da eseguire allo scadere del countdown.
		 * @param player
		 *            {@link RemotePlayer} a cui notificare il countdown (se
		 *            null verra' notificato a tutti i giocatori della Stanza).
		 */
		public RoomCountDownHandler(int interval, Runnable runnable, RemotePlayer player) {
			this.interval = interval;
			this.task = runnable;
			this.player = player;
		}

		/**
		 * Metodo chiamato dal {@link TimerTask}.
		 */
		@Override
		public void run() {
			int interval = setInterval();
			if (this.player == null) {
				logToAllPlayers(String.valueOf(interval));
			} else {
				logToPlayer(this.player, String.valueOf(interval));
			}

			if (this.interval == 0) {
				Thread t = new Thread(task);
				t.start();
			}
		}

		/**
		 * Decrementa il contatore.
		 * 
		 * @return interval valore attuale del countdown
		 */
		private final int setInterval() {
			if (interval == 1)
				timer.cancel();
			return --interval;
		}
	}

}