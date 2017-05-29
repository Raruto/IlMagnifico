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

	public void startNewGame() {
		inizializzaPartita();

		UpdateStats update = new UpdateStats(EFasiDiGioco.InizioPartita, this.spazioAzione);
		update.setNomeGiocatore(giocatoreDiTurno.getNome());

		dispatchGameUpdate(update);
	}

	private boolean isElegible(RemotePlayer remotePlayer) {
		if (this.periodo <= 0) {
			return false;
		} else {
			return true;
		}
	}

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
		if (!isElegible(remotePlayer)) {
			throw new GameException(Errors.GAME_NOT_STARTED);
		} else {
			UpdateStats updateStats = new UpdateStats(remotePlayer, action, this.spazioAzione);
			return updateStats;
		}
	}

}
