package com.server;

import java.io.IOException;

/**
 * This exception is thrown when there is some error during the initialization
 * of the server.
 */
public class ServerException extends IOException {

	/**
	 * Base constructor.
	 * 
	 * @param message
	 *            of the error.
	 */
	public ServerException(String message) {
		super(message);
	}

	/**
	 * Base constructor.
	 * 
	 * @param message
	 *            of the error.
	 * @param cause
	 *            of the error.
	 */
	public ServerException(String message, Throwable cause) {
		super(message, cause);
	}
}