package main.network.client;

import java.io.IOException;

/**
 * Eccezione scatenata quando si verifica un errore durante la connessione
 * con il server.
 */
public class ClientException extends IOException {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5902506250056087761L;
	
	/**
	 * Costruttore.
	 * 
	 * @param cause
	 *            causa dell'eccezione.
	 */
	public ClientException(Throwable cause) {
		super(cause);
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 * @param cause
	 *            causa dell'errore.
	 */
	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}
}