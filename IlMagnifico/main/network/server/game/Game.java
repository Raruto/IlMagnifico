package main.network.server.game;

import main.model.Partita;
import main.network.server.RemotePlayer;
import main.util.EAzioniGiocatore;
import main.util.EFasiDiGioco;
import main.util.errors.Errors;
import main.util.errors.GameError;

public class Game extends Partita {
	/**
	 * Flag usato in {@link Room} per determinare se la partita � in corso.
	 */
	private boolean end;

	/**
	 * Riferimento alla Stanza in cui la partita � in atto.
	 */
	private Room room;

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
		update = new UpdateStats(EFasiDiGioco.MossaGiocatore, this.spazioAzione);
		update.setNomeGiocatore(giocatoreDiTurno.getNome());
		dispatchGameUpdate(update);
	}

	/**
	 * Blocca il Thread chiamante fintanto che la Partita � ancora in corso
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
	 * Metodo per verificare la possibilit� di eseguire un azione da parte di un
	 * determinato giocatore
	 * 
	 * @param remotePlayer
	 *            giocatore su cui verificare la validit� dell'azione da
	 *            eseguire
	 * @param e
	 *            (nel caso di invalidit� dell'azione che il giocatore sta
	 *            tentando di compiere) conterr� il codice associato all'errore
	 * @return true se giocatore pu� eseguire l'azione, false altrimenti
	 */
	private boolean isElegible(RemotePlayer remotePlayer, GameError e) {
		boolean elegibility = true;
		if (this.periodo <= 0) {
			e.setError(Errors.GAME_NOT_STARTED);
			elegibility = false;
		} else if (!this.giocatoreDiTurno.getNome().equals(remotePlayer.getNome())) {
			e.setError(Errors.NOT_YOUR_TURN);
			elegibility = false;
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
		GameError e = new GameError();
		if (isElegible(remotePlayer, e)) {
			UpdateStats updateStats = new UpdateStats(remotePlayer, action, this.spazioAzione);
			return updateStats;
		} else {
			throw new GameException(e.toString());
		}
	}

}
