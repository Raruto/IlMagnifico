package main.network.exceptions;

import main.network.NetworkException;

/**
 * This exception is used to identify an error while creating a game room due to a concurrent creation of a new room
 * on the server. Every player should be joined to an existing room, they can create a new room only if no other room
 * has been found. It is built on top of {@link NetworkException}.
 */
public class CreateRoomException extends NetworkException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1547291686732122169L;

	/**
     * Base constructor.
     */
    public CreateRoomException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the error.
     */
    public CreateRoomException(String message) {
        super(message);
    }

    /**
     * Base constructor.
     * @param message of the error.
     * @param cause of the error.
     */
    public CreateRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Base constructor.
     * @param cause of the error.
     */
    public CreateRoomException(Throwable cause) {
        super(cause);
    }
}