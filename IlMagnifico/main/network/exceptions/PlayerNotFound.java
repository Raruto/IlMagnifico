package main.network.exceptions;

import main.network.NetworkException;

/**
 * Eccezione scatenata quando non viene trovato un giocatore.
 */
public class PlayerNotFound extends NetworkException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 526510107978150087L;

	/**
	 * Costruttore.
	 */
	public PlayerNotFound() {
		super();
	}
}