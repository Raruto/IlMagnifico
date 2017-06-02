package main.model.exceptions;

/**
 * Eccezione che verifica che il famigliare selezionato e' gia' stato posizionato
 * sul tabellone in precendenza nello stesso turno
 * 
 */
public class FamigliareSpostatoException extends Exception {
	public FamigliareSpostatoException() {
		super();
	}

}
