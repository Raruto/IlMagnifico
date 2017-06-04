package main.network.server.game.exceptions;

import main.network.NetworkException;

/**
 * Eccezione scatenata durante la creazione di una stanza (ad esempio a causa
 * della creazione simultanea di una nuova stanza sul server, se non vi erano
 * altre stanza disponibili). Basato su {@link NetworkException}.
 */
public class CreateRoomException extends NetworkException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1547291686732122169L;

	/**
	 * Costruttore.
	 */
	public CreateRoomException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public CreateRoomException(String message) {
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
	public CreateRoomException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'errore.
	 */
	public CreateRoomException(Throwable cause) {
		super(cause);
	}
}