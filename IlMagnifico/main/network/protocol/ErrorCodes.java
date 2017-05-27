package main.network.protocol;

/**
 * This class is used to define all possible error that can occur during the
 * game and the communication with the server.
 */
public class ErrorCodes {

	/**
	 * Defines of all error codes.
	 */
	public static final int ERROR_NOT_PLAYER_TURN = 404;
	public static final int ERROR_POLITIC_CARD_NOT_YET_DRAWN = 405;
	public static final int ERROR_POLITIC_CARD_ALREADY_DRAWN = 406;
	public static final int ERROR_MAIN_ACTION_NOT_AVAILABLE = 407;
	public static final int ERROR_NOT_ENOUGH_COINS = 408;
	public static final int ERROR_NO_POLITIC_CARD_CAN_SATISFY_REGION_BALCONY = 409;
	public static final int ERROR_CHAT_PLAYER_NOT_FOUND = 410;
	public static final int ERROR_CITY_NOT_FOUND = 411;
	public static final int ERROR_EMPORIUM_ALREADY_BUILT = 412;
	public static final int ERROR_NOT_ENOUGH_ASSISTANTS = 413;
	public static final int ERROR_CITY_NOT_VALID = 414;
	public static final int ERROR_FAST_ACTION_NOT_AVAILABLE = 415;
	public static final int ERROR_ROUTE_NOT_VALID = 416;
	public static final int ERROR_MARKET_ITEM_NOT_FOUND = 417;
	public static final int ERROR_MARKET_ITEM_ALREADY_ON_SALE = 418;
	public static final int ERROR_MARKET_ITEM_ALREADY_SOLD = 419;
	public static final int ERROR_MAIN_ACTION_NOT_DONE = 420;
	public static final int ERROR_GENERIC_SERVER_ERROR = 500;

	/**
	 * Costruttore privato.
	 */
	private ErrorCodes() {
		// Questa classe non è stata progettata per essere istanziata.
	}
}