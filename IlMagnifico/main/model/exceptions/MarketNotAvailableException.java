package main.model.exceptions;

/**
 * Eccezione che si verifica quando un giocatore vuole posizionare un famigliare
 * in una zone Mercato ma è in possesso della carta scomunica che glielo vieta
 */
public class MarketNotAvailableException extends Exception {
	public MarketNotAvailableException() {
		super();
	}
}
