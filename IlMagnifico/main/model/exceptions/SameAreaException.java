package main.model.exceptions;

/**
 * Eccezione che si verifica quando nella stessa torre o area il giocatore ha
 * gia'� posizionato un famigliare non neutro e si accinge a posizionarne un altro
 * non neutro
 */
public class SameAreaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4800467706031104892L;

	public SameAreaException() {
		super();
	}
}
