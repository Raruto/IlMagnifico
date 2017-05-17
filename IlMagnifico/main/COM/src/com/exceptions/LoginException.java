package com.exceptions;

import com.NetworkException;

/**
 * This exception is thrown when another player with the same nickname is already logged.
 * It is built on top of {@link NetworkException}.
 */
public class LoginException extends NetworkException {

    /**
     * Base constructor.
     */
    public LoginException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the error.
     */
    public LoginException(String message) {
        super(message);
    }

    /**
     * Base constructor.
     * @param message of the error.
     * @param cause of the error.
     */
    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Base constructor.
     * @param cause of the error.
     */
    public LoginException(Throwable cause) {
        super(cause);
    }
}