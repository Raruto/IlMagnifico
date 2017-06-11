package main.model.exceptions;

/**
 * Eccezione che viene sollevata quando si esegue uno spostamento su un'azione
 * della torre dove non c'e' nessuna carta
 * 
 */
public class NullCardException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5279225262835476529L;

	public NullCardException() {
		super();
	}
}
