package main.model.exceptions;

/**
 * Eccezione che si verifica quando il valore del famigliare non e' sufficiente
 * per compiere una determinata azione
 */
public class InsufficientValueException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2320702365379945054L;

	public InsufficientValueException() {
		super();
	}
}
