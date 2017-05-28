package main.util;

/**
 * Classe che definisce alcune costanti del il gioco e parametri per la
 * comunicazione con il server.
 */
public class Costants {
	// Connessione
	public static final String SERVER_ADDRESS = "127.0.0.1";
	public static final int SOCKET_PORT = 1098;
	public static final int RMI_PORT = 1099;
	
	// Stanza
	public static final int ROOM_MIN_PLAYERS = 2;
	public static final int ROOM_MAX_PLAYERS = 4;
	public static final int ROOM_WAITING_TIME = 5;
	public static final boolean ROOM_ENABLE_LOG = true;

	/**
	 * Costruttore privato.
	 */
	private Costants() {
		// Questa classe non è stata progettata per essere istanziata.
	}
}
