package main.network.exceptions;

import main.network.NetworkException;

/**
 * Eccezione scatenata quando un altro giocatore con lo stesso nome si è già
 * identificato sul server (ha già eseguito il login con lo stesso nickname).
 * Basata su {@link NetworkException}.
 */
public class LoginException extends NetworkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3289587206631280854L;

	/**
	 * Base constructor.
	 */
	public LoginException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public LoginException(String message) {
		super(message);
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 * @param cause
	 *            causa dell'eccezione.
	 */
	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'eccezione.
	 */
	public LoginException(Throwable cause) {
		super(cause);
	}
}