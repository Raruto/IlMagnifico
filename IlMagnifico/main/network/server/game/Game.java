package main.network.server.game;

import java.util.ArrayList;

import main.model.Partita;
import main.network.server.RemotePlayer;
import main.util.EAzioniGiocatore;

public class Game extends Partita {
	/**
	 * Flag usato in {@link Room} per determinare se la partita è in corso.
	 */
	private boolean end;

	public Game(ArrayList<RemotePlayer> players) {
		for (RemotePlayer player : players) {
			giocatori.add(player);
		}
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

	/**
	 * Metodo invocato dal client ogni volta che vuole eseguire un'azione di
	 * gioco
	 * 
	 * @param remotePlayer
	 * @param action
	 * @return {@link UpdateStats}
	 */
	public UpdateStats performGameAction(RemotePlayer remotePlayer, EAzioniGiocatore action) throws GameException {
		if (this.periodo <= 0) {
			throw new GameException("GAME_NOT_STARTED");
		} else {
			UpdateStats updateStats = new UpdateStats(remotePlayer, action, this.spazioAzione);
			return updateStats;
		}
	}

}
