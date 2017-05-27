package main.network.exceptions;

import main.network.exceptions.LogicException;

/**
 * This exception is thrown when a player is not found.
 */
public class PlayerNotFound extends LogicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 526510107978150087L;

	/**
	 * Base constructor.
	 */
	public PlayerNotFound() {
		super();
	}
}
