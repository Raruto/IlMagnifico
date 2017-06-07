package main.model.exceptions;

/**
 * Eccezione che verifica che il famigliare selezionato e' gia' stato posizionato
 * sul tabellone in precendenza nello stesso turno
 * 
 */
public class FamiliarAlreadyUsedException extends Exception {
	public FamiliarAlreadyUsedException() {
		super();
	}

}
