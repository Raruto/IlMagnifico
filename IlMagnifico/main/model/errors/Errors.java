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
	GAME_ENDED("GAME_ENDED"),
	FAMILIAR_ALREADY_USED("FAMILIAR_ALREADY_USED"),
	INSUFFICIENT_FAMILIAR_VALUE("INSUFFICIENT_FAMILIAR_VALUE"),
	INVALID_POSTITION("INVALID_POSITION"),
	MARKET_NOT_AVAILABLE("MARKET_NOT_AVAILABLE"),
	MAX_CARDS("MAX_NUMBER_OF_CARDS_REACHED"),
	NO_ENOUGH_RESOURCES("INSUFFICIENT_RESOURCES"),
	NO_MONEY_EXCEPTION("INSUFFICIENT_MONEY_TO_PAY"),
	NULL_CARD_EXCEPTION("NO_CARD_ON_POSITION"),
	SAME_AREA("FAMILY_MAMBER_ALREADY_ON_AREA"),
	SPACE_TAKEN("SPACE_ALREADY_TAKEN"),
	INVALID_CHOICE("INVALID_CHOICE");

	private String error;

	/**
	 * Costruttore privato.
	 */
	private Errors(String error) {
		this.error = error;
	}
	
	public String getDescription(){
		return this.error;
	}
}
