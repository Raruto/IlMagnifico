package main.model.errors;

/**
 * Classe che definisce alcune costanti per i messaggi di segnalazione di errori
 * durante la comunicazione Client/Server.
 */
public enum Errors {
	NO_ERROR("Everything seems to be fine!"), 
	GENERIC_ERROR("Generic error, mainly used for testing."),
	GAME_NOT_STARTED("GAME_NOT_STARTED"), 
	NOT_YOUR_TURN("NOT_YOUR_TURN"),
	GAME_ENDED("GAME_ENDED");

	private String error;

	/**
	 * Costruttore privato.
	 */
	private Errors(String error) {
		this.error = error;
	}
}
