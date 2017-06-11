package main.model.exceptions;

/**
 * Eccezione che si verifica quando un giocatore vuole posizionare un famigliare
 * in una zone Mercato ma e' in possesso della carta scomunica che glielo vieta
 */
public class MarketNotAvailableException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4868465011834193815L;

	public MarketNotAvailableException() {
		super();
	}
}
