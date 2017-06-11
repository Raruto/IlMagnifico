package main.model.exceptions;

/**
 * Eccezione che si verifica quando il giocatore vuole posizionare un famigliare
 * in una posizione non valida
 * 
 */
public class InvalidPositionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2152027157032204464L;

	public InvalidPositionException() {
		super();
	}
}
