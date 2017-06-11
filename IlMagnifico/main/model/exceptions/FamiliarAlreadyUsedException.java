package main.model.exceptions;

/**
 * Eccezione che verifica che il famigliare selezionato e' gia' stato posizionato
 * sul tabellone in precendenza nello stesso turno
 * 
 */
public class FamiliarAlreadyUsedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -814577438166400020L;

	public FamiliarAlreadyUsedException() {
		super();
	}

}
