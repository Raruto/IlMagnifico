package main.model.exceptions;

/**
 * Eccezione che si verifica quando voglio prendere una carta ma ho gia'� 6 carte
 * nella plancia di quel tipo della carta
 * 
 */
public class MaxCardsReachedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7478794672273112488L;

	public MaxCardsReachedException() {
		super();
	}
}
