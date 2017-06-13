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
	public static final int MAX_CONNECTION_ATTEMPTS = 2;
	public static final int CONNECTION_RETRY_SECONDS = 0;

	// Comunicazione
	public static final String SERVER = "SERVER";
	public static final String ROOM = "ROOM";
	public static final String GAME = "GAME";

	// Server
	public static final String SOCKET_SERVER_ID = ANSI.WHITE + "[Socket " + SERVER.substring(0, 1).toUpperCase()
			+ SERVER.substring(1).toLowerCase() + "]" + ANSI.RESET;
	public static final String RMI_SERVER_ID = ANSI.WHITE + "[RMI " + SERVER.substring(0, 1).toUpperCase()
			+ SERVER.substring(1).toLowerCase() + "]" + ANSI.RESET;

	// Stanza
	public static final int ROOM_MIN_PLAYERS = 2;
	public static final int ROOM_MAX_PLAYERS = 4;
	public static final int ROOM_WAITING_TIME = 1;
	public static final boolean ROOM_ENABLE_LOG = true;

	// Gioco
	public static final String GAME_ID = ANSI.CYAN + "[" + GAME + "]" + ANSI.RESET;
	public static final String ROOM_ID = ANSI.YELLOW + "[" + ROOM + "]" + ANSI.RESET;

	// CLI
	public static final String ROW_SEPARATOR = "-----------------------------------------------------------------------------------------------------------------------------------";
	public static final boolean CLIENT_ENABLE_LOG = true;

	// GUI
	public static final String PATH_RESOURCES = "/main/ui/gui/res";
	public static final String FOLDER_BASE = "/base";
	public static final String FOLDER_EXCOMM_CARDS = "/cards/excomm";
	public static final String FOLDER_DEV_CARDS = "/cards/dev";

	/**
	 * Costruttore privato.
	 */
	private Costants() {
		// Questa classe non � stata progettata per essere istanziata.
	}
}
