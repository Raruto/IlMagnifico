package main.model.exceptions;

/**
 * Eccezione che si verifica quando un giocatore posiziona il famigliare in una
 * orre dove e' presente un altro famigliare ma non ha abbastanza le tre monete
 * da pagare al banco
 */
public class NoMoneyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533979576070023941L;

	public NoMoneyException() {
		super();
	}
}
