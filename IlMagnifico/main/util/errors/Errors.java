package main.util.errors;

/**
 * Classe che definisce alcune costanti per i messaggi di segnalazione di errori
 * durante la comunicazione Client/Server.
 */
public enum Errors {
	NO_ERROR("Everything seems to be fine!"), 
	GAME_NOT_STARTED("GAME_NOT_STARTED"), 
	NOT_YOUR_TURN("NOT_YOUR_TURN");

	private String error;

	/**
	 * Costruttore privato.
	 */
	private Errors(String error) {
		this.error = error;
	}
}
