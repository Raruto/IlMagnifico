package model.exceptions;

import java.io.IOException;

/**
 * This exception is used as a base of all possible logic and market exception.
 */
public class LogicException extends IOException {

    /**
     * Base constructor.
     */
    public LogicException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the exception.
     */
    public LogicException(String message) {
        super(message);
    }

    /**
     * /**
     * Base constructor.
     * @param message of the exception.
     * @param cause of the exception.
     */
    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Base constructor.
     * @param cause of the exception.
     */
    public LogicException(Throwable cause) {
        super(cause);
    }
}
