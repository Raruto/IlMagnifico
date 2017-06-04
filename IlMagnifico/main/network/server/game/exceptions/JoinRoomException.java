package main.network.server.game.exceptions;

import main.network.NetworkException;

/**
 * Eccezione scatenata durante l'accesso ad stanza esistente (ad esempio non è
 * stata trovata alcuna stanza o la stanza è piena). Basata su
 * {@link NetworkException}.
 */
public class JoinRoomException extends NetworkException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8674697838871392712L;

	/**
	 * Costruttore.
	 */
	public JoinRoomException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public JoinRoomException(String message) {
		super(message);
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messagggio di erore.
	 * @param cause
	 *            causa dell'errore.
	 */
	public JoinRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'errore.
	 */
	public JoinRoomException(Throwable cause) {
		super(cause);
	}
}