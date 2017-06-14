package main.network.protocol.socket;

import main.network.protocol.rmi.RMIClientInterface;
import main.network.protocol.rmi.RMIServerInterface;

/**
 * Classe che contiente tutte le costanti utilizzate dal protocollo per la
 * comunicazione tramite socket. Basato su {@link RMIClientInterface} e
 * {@link RMIServerInterface} per i "dettagli" sul protocollo.
 */
public class SocketConstants {

	// Intestazioni richieste (Client).
	public static final String LOGIN_REQUEST = "loginRequest";
	public static final String GAME_ACTION = "gameAction";
	public static final String CHAT_MESSAGE = "chatMessage";

	// Intestazioni risposte (Server).
	public static final String ACTION_NOT_VALID = "actionNotValid";

	// Codici di risposta del server.
	public static final int RESPONSE_OK = 200;
	public static final int RESPONSE_PLAYER_ALREADY_EXISTS = 401;

	/**
	 * Costruttore privato.
	 */
	private SocketConstants() {
		// Questa classe non e' stata progettata per essere istanziata.
	}
}