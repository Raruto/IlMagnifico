package main.network.exceptions;

import main.network.NetworkException;

/**
 * This exception is thrown if provided configuration file is not found or malformed.
 * It is built on top of {@link NetworkException}.
 */
public class InvalidConfigurationException extends NetworkException {

    /**
     * Base constructor.
     */
    public InvalidConfigurationException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the error.
     */
    public InvalidConfigurationException(String message) {
        super(message);
    }

    /**
     * Base constructor.
     * @param message of the error.
     * @param cause of the error.
     */
    public InvalidConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Base constructor.
     * @param cause of the error.
     */
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }
}