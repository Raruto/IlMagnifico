package main.util;

/**
 * Classe che definisce alcune costanti del il gioco e parametri per la
 * comunicazione con il server.
 */
public class Costants {

	public static final String SERVER_ADDRESS = "127.0.0.1";
	public static final int SOCKET_PORT = 1098;
	public static final int RMI_PORT = 1099;
	public static final int MIN_ROOM_PLAYERS = 2;
	public static final int MAX_ROOM_PLAYERS = 4;

	public static final boolean ENABLE_ROOM_LOG = true;

	/**
	 * Costruttore privato.
	 */
	private Costants() {
		// Questa classe non è stata progettata per essere istanziata.
	}
}
