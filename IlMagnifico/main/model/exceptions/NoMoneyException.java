package main.model.exceptions;

/**
 * Eccezione che si verifica quando un giocatore posiziona il famigliare in una
 * orre dove e' presente un altro famigliare ma non ha abbastanza le tre monete
 * da pagare al banco
 */
public class NoMoneyException extends Exception {
	public NoMoneyException() {
		super();
	}
}
