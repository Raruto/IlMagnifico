package main.network.exceptions;

import main.network.NetworkException;

/**
 * This exception is used to identify an error while joining another existing room, for example no room has been found
 * or the room is full. It is built on top of {@link NetworkException}.
 */
public class JoinRoomException extends NetworkException {

    /**
     * Base constructor.
     */
    public JoinRoomException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the error.
     */
    public JoinRoomException(String message) {
        super(message);
    }

    /**
     * Base constructor.
     * @param message of the error.
     * @param cause of the error.
     */
    public JoinRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Base constructor.
     * @param cause of the error.
     */
    public JoinRoomException(Throwable cause) {
        super(cause);
    }
}