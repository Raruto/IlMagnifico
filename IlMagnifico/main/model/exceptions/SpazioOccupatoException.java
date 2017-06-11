package main.model.exceptions;

/**
 * Eccezione che si verifica quando lo spazio dove si vuole spostare il
 * famigliare e' gia'� occupato da un altro famigliare
 * 
 */
public class SpazioOccupatoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6059934300191052092L;

	public SpazioOccupatoException() {
		super();
	}
}
