package com;

import java.io.IOException;

/**
 * This exception is thrown when there is a communication's error between client and server.
 */
public class NetworkException extends IOException {

    /**
     * Base constructor.
     */
    public NetworkException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the error.
     */
    public NetworkException(String message) {
        super(message);
    }

    /**
     * Base constructor.
     * @param message of the error.
     * @param cause of the error.
     */
    public NetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Base constructor.
     * @param cause of the error.
     */
    public NetworkException(Throwable cause) {
        super(cause);
    }
}