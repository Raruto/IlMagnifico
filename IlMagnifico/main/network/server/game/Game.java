package main.network.server.game;

import java.util.ArrayList;

import main.model.Partita;
import main.network.server.RemotePlayer;
import main.util.EAzioniGiocatore;
import main.util.EFasiDiGioco;
import main.util.Errors;

public class Game extends Partita {
	/**
	 * Flag usato in {@link Room} per determinare se la partita è in corso.
	 */
	private boolean end;

	private Room room;

	public Game(ArrayList<RemotePlayer> players, Room room) {
		for (RemotePlayer player : players) {
			giocatori.add(player);
		}
		this.room = room;
		end = false;
	}

	/**
	 * Inizializza una nuova Partita nella Stanza (usato in {@link Room})
	 */
	public void startNewGame() {
		UpdateStats update;

		// TODO: decidere quali azioni vogliamo trasmettere...

		inizializzaPartita();
		update = new UpdateStats(EFasiDiGioco.InizioPartita, this.spazioAzione);
		dispatchGameUpdate(update);

		// lanciaDadi();
		update = new UpdateStats(EFasiDiGioco.InizioPeriodo, this.spazioAzione);
		dispatchGameUpdate(update);

		//
		update = new UpdateStats(EFasiDiGioco.InizioTurno, this.spazioAzione);
		dispatchGameUpdate(update);

		//
		update = new UpdateStats(EFasiDiGioco.GiocatoreDiTurno, this.spazioAzione);
		update.setNomeGiocatore(giocatoreDiTurno.getNome());
		dispatchGameUpdate(update);
	}

	/**
	 * Blocca il Thread chiamante fintanto che la Partita è ancora in corso
	 * (usato in {@link Room})
	 */
	public synchronized void waitGameEnd() {
		// Wait until game is end.
		while (!end) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Sblocca i Thread che si sono messi in attesa della fine della Partita
	 * (usato in {@link Room})
	 */
	public synchronized void endGame() {
		// Toggle game status.
		end = true;

		// Notify all about game end status.
		notifyAll();
	}

	/**
	 * Metodo per verificare la possibilità di eseguire un azione da parte di un
	 * determinato giocatore
	 * 
	 * @param remotePlayer
	 *            giocatore su cui verificare la validità dell'azione da
	 *            eseguire
	 * @param e
	 *            (nel caso di invalidità dell'azione che il giocatore sta
	 *            tentando di compiere) conterrà il codice associato all'errore
	 * @return true se giocatore può eseguire l'azione, false altrimenti
	 */
	private boolean isElegible(RemotePlayer remotePlayer, Errors e) {
		boolean elegibility = true;
		if (this.periodo <= 0) {
			e = Errors.GAME_NOT_STARTED;
			elegibility = false;
		} else {

		}
		return elegibility;
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
	public UpdateStats performGameAction(RemotePlayer remotePlayer, EAzioniGiocatore action) throws GameException {
		Errors e = Errors.NO_ERROR;
		if (isElegible(remotePlayer, e)) {
			UpdateStats updateStats = new UpdateStats(remotePlayer, action, this.spazioAzione);
			return updateStats;
		} else {
			throw new GameException(e.toString());
		}
	}

}
