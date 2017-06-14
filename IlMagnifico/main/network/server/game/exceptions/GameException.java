package main.network.server.game.exceptions;

import java.io.IOException;

import main.model.errors.Errors;

/**
 * Eccezione scatenata quando un giocatore tenta di eseguire un'azione di gioco
 * illegale presso il server. Scatenata solo dal server del gioco (usata lato
 * client per identificare la causa dell'eccezione). Basata su {@link IOException}.
 */
public class GameException extends IOException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -2511558205317566371L;

	/**
	 * Costruttore.
	 */
	public GameException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public GameException(String message) {
		super(message);
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore (vedi {@link Errors}).
	 * @param cause
	 *            causa dell'errore.
	 */
	public GameException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'errore.
	 */
	public GameException(Throwable cause) {
		super(cause);
	}
}
