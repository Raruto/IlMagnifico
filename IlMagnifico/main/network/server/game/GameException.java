package main.network.server.game;

import java.io.IOException;

public class GameException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2511558205317566371L;

	/**
	 * Base constructor.
	 */
	public GameException() {
		super();
	}

	/**
	 * Base constructor.
	 * 
	 * @param message
	 *            of the exception.
	 */
	public GameException(String message) {
		super(message);
	}

	/**
	 * /** Base constructor.
	 * 
	 * @param message
	 *            of the exception.
	 * @param cause
	 *            of the exception.
	 */
	public GameException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Base constructor.
	 * 
	 * @param cause
	 *            of the exception.
	 */
	public GameException(Throwable cause) {
		super(cause);
	}
}
