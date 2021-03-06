package main.network;

import java.io.IOException;

/**
 * Eccezione scatenata quando sussiste un errore di comunicazione tra client e
 * server.
 */
public class NetworkException extends IOException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3569320576419655791L;

	/**
	 * Costruttore.
	 */
	public NetworkException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public NetworkException(String message) {
		super(message);
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 * @param cause
	 *            causa dell'errore.
	 */
	public NetworkException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'errore.
	 */
	public NetworkException(Throwable cause) {
		super(cause);
	}
}