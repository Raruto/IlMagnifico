package main.network.server.game.exceptions;

/**
 * Eccezione scatenata quando un giocatore sta tentando di entrare in una stanza
 * piena o chiusa. Utilizzata solo dal server del gioco.
 */
public class RoomFullException extends Exception {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2915973657497743818L;

	/**
	 * Costruttore.
	 */
	public RoomFullException() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param message
	 *            messaggio di errore.
	 */
	public RoomFullException(String message) {
		super(message);
	}
}