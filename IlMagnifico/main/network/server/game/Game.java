package main.network.server.game;

import java.util.HashMap;

import main.model.Giocatore;
import main.model.Partita;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.EFasiDiGioco;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.errors.Errors;
import main.model.errors.GameError;
import main.model.exceptions.FamiliarAlreadyUsedException;
import main.model.exceptions.InsufficientValueException;
import main.model.exceptions.InvalidChoiceException;
import main.model.exceptions.InvalidPositionException;
import main.model.exceptions.MarketNotAvailableException;
import main.model.exceptions.MaxCardsReachedException;
import main.model.exceptions.NoEnoughResourcesException;
import main.model.exceptions.NoMoneyException;
import main.model.exceptions.NullCardException;
import main.model.exceptions.SameAreaException;
import main.model.exceptions.SpazioOccupatoException;
import main.network.server.game.exceptions.GameException;

public class Game extends Partita {

	/**
	 * Riferimento alla Stanza in cui la partita e' in atto.
	 */
	private Room room;

	/*
	 * Mappa di tutte le intestazioni dei metodi per la gestione delle richieste
	 * dei client.
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
	 * Inizializza "responseMap" caricando tutti i possibili metodi di risposta
	 * (chiamati da {@link ResponseHandler}).
	 */
	private void loadResponses() {
		responseMap.put(EAzioniGiocatore.Mercato, this::onMarket);
		responseMap.put(EAzioniGiocatore.PalazzoConsiglio, this::onCouncilPalace);
		responseMap.put(EAzioniGiocatore.Produzione, this::onProductionRound);
		responseMap.put(EAzioniGiocatore.Raccolto, this::onHarvestRound);
		responseMap.put(EAzioniGiocatore.RaccoltoOvale, this::onHarvestOval);
		responseMap.put(EAzioniGiocatore.ProduzioneOvale, this::onProductionOval);
		responseMap.put(EAzioniGiocatore.Torre, this::onTower);
		responseMap.put(EAzioniGiocatore.Famigliare, this::onPayServant);
	}

	/**
	 * Inizializza una nuova Partita nella Stanza (usato in {@link Room})
	 */
	public void startNewGame() {
		UpdateStats update;

		// TODO: decidere quali azioni vogliamo trasmettere...

		inizializzaPartita();
		update = new UpdateStats(EFasiDiGioco.InizioPartita, this.giocatori);
		dispatchGameUpdate(update);

		update = new UpdateStats(EFasiDiGioco.InizioPeriodo);
		dispatchGameUpdate(update);

		// turnazione
		posizionaCarteSuTorre();
		lanciaDadi();
		update = new UpdateStats(EFasiDiGioco.InizioTurno, this.giocatori, this.spazioAzione);
		dispatchGameUpdate(update);

		//
		update = new UpdateStats(EFasiDiGioco.MossaGiocatore, this.spazioAzione);
		update.setNomeGiocatore(giocatoreDiTurno.getNome());
		dispatchGameUpdate(update);
	}

	/**
	 * Blocca il Thread chiamante fintanto che la Partita e' ancora in corso
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
	 * Ad ogni chiamata del metodo fa progredire lo stato della partita (es.
	 * passando al giocatore successivo) inviando una notifica sullo stato della
	 * partita (es. fine periodo, fine partita...)
	 */
	private void andvanceInGameLogic(EAzioniGiocatore azione) {
		UpdateStats update;
		if (azione != EAzioniGiocatore.Famigliare) {
			if (!isGiroDiTurniTerminato()) {
				avanzaDiTurno();
				update = new UpdateStats(EFasiDiGioco.MossaGiocatore, this.spazioAzione);
				update.setNomeGiocatore(giocatoreDiTurno.getNome());
				dispatchGameUpdate(update);

			} else {

				// terminaGiroDiTurni();
				resetPerNuovoTurno();
				update = new UpdateStats(EFasiDiGioco.FineTurno, this.spazioAzione);
				dispatchGameUpdate(update);

				if (!isPeriodoTerminato()) {
					// avanzaGiroDiTurni();
					posizionaCarteSuTorre();
					lanciaDadi();
					update = new UpdateStats(EFasiDiGioco.InizioTurno, this.giocatori, this.spazioAzione);
					dispatchGameUpdate(update);
				} else {

					// terminaPeriodo();
					update = new UpdateStats(EFasiDiGioco.FinePeriodo, this.spazioAzione);
					dispatchGameUpdate(update);

					if (!isPartitaFinita()) {
						// avanzaPeriodo();
						update = new UpdateStats(EFasiDiGioco.InizioPeriodo, this.spazioAzione);
						dispatchGameUpdate(update);
					} else {

						// terminaPartita();
						update = new UpdateStats(EFasiDiGioco.FinePartita, this.spazioAzione);
						dispatchGameUpdate(update);
					}
				}
			}
		}
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
	 * Metodo interno usato per il Log sul Server (abilitato da: LOG_ENABLED in
	 * {@link Room})
	 * 
	 * @param message
	 */
	public void log(String message) {
		room.log(message);
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
			// Tenta di eseguire l'azione richiesta dal giocatore
			UpdateStats update = handleResponse(remotePlayer, requestedAction);
			dispatchGameUpdate(update);

			// Se tutto va a buon fine (azione valida = non scatena nessuna
			// eccezione), fa avanzare lo stato interno della partita
			// (es. notifico al prossimo giocatore che e' il suo turno).
			andvanceInGameLogic(update.getAzioneGiocatore());
		} else {
			throw new GameException(e.toString());
		}
	}

	private UpdateStats onMarket(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.getFamigliare(update.getIndiceColorePedina()).eseguiSpostamentoMercato(
					update.getPosizioneSpostamentoPedina(), update.getSceltePrivilegiConsiglio());
		} catch (InsufficientValueException e) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (MarketNotAvailableException e1) {
			throw new GameException(Errors.MARKET_NOT_AVAILABLE.toString());
		} catch (SpazioOccupatoException e2) {
			throw new GameException(Errors.SPACE_TAKEN.toString());
		} catch (FamiliarAlreadyUsedException e3) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		} catch (InvalidPositionException e4) {
			throw new GameException(Errors.INVALID_POSTITION.toString());
		} catch (InvalidChoiceException e5) {
			throw new GameException(Errors.INVALID_CHOICE.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onPayServant(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.pagaServitore(remotePlayer.getFamigliare(update.getIndiceColorePedina()),
					update.getServitoriDaPagare());
		} catch (NoEnoughResourcesException e) {
			throw new GameException(Errors.NO_ENOUGH_RESOURCES.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onCouncilPalace(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.getFamigliare(update.getIndiceColorePedina())
					.eseguiSpostamentoPalazzoConsiglio(update.getSceltePrivilegiConsiglio()[0]);
			// nell'array restituito da update prendo solamente il primo
			// elemento
		} catch (InsufficientValueException e) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (FamiliarAlreadyUsedException e1) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onProductionRound(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			// qua ci va messa l'azione scelta dal giocatore come parametro
			remotePlayer.getFamigliare(update.getIndiceColorePedina()).eseguiSpostamentoProduzioneRotondo(null);
		} catch (FamiliarAlreadyUsedException e) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		} catch (InsufficientValueException e1) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (SpazioOccupatoException e2) {
			throw new GameException(Errors.SPACE_TAKEN.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onHarvestRound(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.getFamigliare(update.getIndiceColorePedina()).eseguiSpostamentoRaccoltoRotondo();
		} catch (FamiliarAlreadyUsedException e) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		} catch (InsufficientValueException e1) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (SpazioOccupatoException e2) {
			throw new GameException(Errors.SPACE_TAKEN.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onHarvestOval(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.getFamigliare(update.getIndiceColorePedina()).eseguiSpostamentoRaccoltoOvale();
		} catch (InsufficientValueException e) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (SameAreaException e1) {
			throw new GameException(Errors.SAME_AREA.toString());
		} catch (FamiliarAlreadyUsedException e) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onProductionOval(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.getFamigliare(update.getIndiceColorePedina()).eseguiSpostamentoProduzioneOvale(
					update.getScelteEffettiPermanenti() != null ? update.getScelteEffettiPermanenti()[0] : null);
		} catch (InsufficientValueException e) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (SameAreaException e1) {
			throw new GameException(Errors.SAME_AREA.toString());
		} catch (FamiliarAlreadyUsedException e2) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	private UpdateStats onTower(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
		try {
			remotePlayer.getFamigliare(update.getIndiceColorePedina())
					.eseguiSpostamentoTorre(update.getPosizioneSpostamentoPedina(), update.getScelteCosti());
		} catch (InsufficientValueException e) {
			throw new GameException(Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
		} catch (NullCardException e1) {
			throw new GameException(Errors.NULL_CARD_EXCEPTION.toString());
		} catch (MaxCardsReachedException e2) {
			throw new GameException(Errors.MAX_CARDS.toString());
		} catch (NoEnoughResourcesException e3) {
			throw new GameException(Errors.NO_ENOUGH_RESOURCES.toString());
		} catch (NoMoneyException e4) {
			throw new GameException(Errors.NO_MONEY_EXCEPTION.toString());
		} catch (InvalidPositionException e5) {
			throw new GameException(Errors.INVALID_POSTITION.toString());
		} catch (SameAreaException e6) {
			throw new GameException(Errors.SAME_AREA.toString());
		} catch (SpazioOccupatoException e7) {
			throw new GameException(Errors.SPACE_TAKEN.toString());
		} catch (FamiliarAlreadyUsedException e8) {
			throw new GameException(Errors.FAMILIAR_ALREADY_USED.toString());
		}
		return new UpdateStats(remotePlayer, update.getAzioneGiocatore(), this.spazioAzione);
	}

	/**
	 * Gestisce la richiesta ricevuta dal Client ed invoca il metodo
	 * associatogli nella "responseMap".
	 * 
	 * @param remotePlayer
	 *            giocatore che ha effettuato la richiesta.
	 * @param update
	 *            richiesta ricevuta dal client (es. {@link UpdateStats}).
	 */
	public UpdateStats handleResponse(RemotePlayer remotePlayer, UpdateStats update) throws GameException {
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
	 * Interfaccia utilizzata "come" l'interfaccia {@link Runnable}.
	 */
	@FunctionalInterface
	private interface ResponseHandler {

		/**
		 * Gestisce la richiesta del Client.
		 * 
		 * @param remotePlayer
		 *            (vedi {@link RemotePlayer}).
		 * @param update
		 *            (vedi {@link UpdateStats}).
		 */
		UpdateStats handle(RemotePlayer remotePlayer, UpdateStats update) throws GameException;
	}
}
