package main.network.exceptions;

import java.io.IOException;

/**
 * Eccezione usata come base di tutte le altre eccezioni possibili.
 */
public class LogicException extends IOException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 682103833853624348L;

	/**
	 * Costruttore.
	 */
	public LogicException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public LogicException(String message) {
		super(message);
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            massaggio di errore.
	 * @param cause
	 *            causa dell'errore.
	 */
	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'errore.
	 */
	public LogicException(Throwable cause) {
		super(cause);
	}
}