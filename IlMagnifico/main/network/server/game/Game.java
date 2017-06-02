package main.network.server.game;

import java.util.HashMap;

import main.model.Giocatore;
import main.model.Partita;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.EFasiDiGioco;
import main.model.errors.Errors;
import main.model.errors.GameError;
import main.network.server.game.exceptions.GameException;

public class Game extends Partita {

	/**
	 * Riferimento alla Stanza in cui la partita è in atto.
	 */
	private Room room;

	/*
	 * Map of all defined server responses headers.
	 */
	private final HashMap<Object, ResponseHandler> responseMap;

	/**
	 * Costruttore.
	 * 
	 * @param room
	 */
	public Game(Room room) {
		// Inizializza array Giocatori (ereditato da Partita)
		for (RemotePlayer player : room.getPlayers()) {
			giocatori.add(player);
		}
		// Salva riferimento alla Stanza (usato in "dispatchGameUpdate()")
		this.room = room;

		responseMap = new HashMap<>();
		loadResponses();
	}

	/**
	 * Load all possible responses and associate an handler.
	 */
	private void loadResponses() {
		responseMap.put(EAzioniGiocatore.Mercato, this::onMarket);
		responseMap.put(EAzioniGiocatore.PalazzoConsiglio, this::onCouncilPalace);
		responseMap.put(EAzioniGiocatore.Produzione, this::onProduction);
		responseMap.put(EAzioniGiocatore.Raccolto, this::onHarvest);
		responseMap.put(EAzioniGiocatore.Torre, this::onTower);
	}

	/**
	 * Inizializza una nuova Partita nella Stanza (usato in {@link Room})
	 */
	public void startNewGame() {
		UpdateStats update;

		// TODO: decidere quali azioni vogliamo trasmettere...

		inizializzaPartita();
		update = new UpdateStats(EFasiDiGioco.InizioPartita);

		for (Giocatore giocatore : this.giocatori) {
			update.addToNomiGiocatori(giocatore.getNome());
		}
		dispatchGameUpdate(update);

		update = new UpdateStats(EFasiDiGioco.InizioPeriodo);
		dispatchGameUpdate(update);

		// turnazione();
		update = new UpdateStats(EFasiDiGioco.InizioTurno, this.spazioAzione);
		dispatchGameUpdate(update);

		//
		update = new UpdateStats(EFasiDiGioco.MossaGiocatore, this.spazioAzione);
		update.setNomeGiocatore(giocatoreDiTurno.getNome());
		dispatchGameUpdate(update);
	}

	/**
	 * Blocca il Thread chiamante fintanto che la Partita è ancora in corso
	 * (usato in {@link Room})
	 */
	public synchronized void waitGameEnd() {
		while (!isPartitaFinita()) {
			try {
				wait(); // Wait until game is end.
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Sblocca i Thread che si sono messi in attesa della fine della Partita
	 * (usato in {@link Room})
	 */
	public synchronized void endGame() {
		terminaPartita(); // Toggle game status.
		notifyAll(); // Notify all about game end status.
	}

	/**
	 * Metodo invocato dalla partita per notificare un avanzamento autonomo
	 * dello stato della partita (es. fine periodo, rapporto vaticano...)
	 * 
	 * @param update
	 */
	private void dispatchGameUpdate(UpdateStats update) {
		room.dispatchGameUpdate(update);
	}

	/**
	 * Metodo invocato dal client ogni volta che vuole eseguire un'azione di
	 * gioco
	 * 
	 * @param remotePlayer
	 * @param action
	 * @return {@link UpdateStats}
	 */
	public void performGameAction(RemotePlayer remotePlayer, UpdateStats requestedAction) throws GameException {
		GameError e = new GameError();
		if (isElegible(remotePlayer, e)) {
			try {
				UpdateStats update;
				update = handleResponse(remotePlayer, requestedAction);
				dispatchGameUpdate(update);
				
				UpdateStats u = new UpdateStats(EFasiDiGioco.MossaGiocatore, this.spazioAzione);
				u.setNomeGiocatore(this.giocatoreDelTurnoSuccessivo(remotePlayer).getNome());
				dispatchGameUpdate(u);
				
			} catch (Exception e2) {
				e.setError(Errors.GENERIC_ERROR);
				throw new GameException(e.toString());
			}
		} else {
			throw new GameException(e.toString());
		}
	}

	private UpdateStats onMarket(RemotePlayer remotePlayer, UpdateStats update) {
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onCouncilPalace(RemotePlayer remotePlayer, UpdateStats update) {
		// TODO: implement here
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onProduction(RemotePlayer remotePlayer, UpdateStats update) {
		// TODO: implement here
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onHarvest(RemotePlayer remotePlayer, UpdateStats update) {
		// TODO: implement here
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onTower(RemotePlayer remotePlayer, UpdateStats update) {
		// TODO: implement here
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	/**
	 * Handle the server response and execute the defined method.
	 * 
	 * @param object
	 *            response header from server.
	 */
	public UpdateStats handleResponse(RemotePlayer remotePlayer, UpdateStats update) {
		ResponseHandler handler = null;
		EAzioniGiocatore azione = update.getAzioneGiocatore();

		if (azione != null)
			handler = responseMap.get(azione);

		if (handler != null) {
			return handler.handle(remotePlayer, update);
		}

		return null;
	}

	/**
	 * This interface is used like {@link Runnable} interface.
	 */
	@FunctionalInterface
	private interface ResponseHandler {

		/**
		 * Handle the server response.
		 */
		UpdateStats handle(RemotePlayer remotePlayer, UpdateStats update);
	}
}
