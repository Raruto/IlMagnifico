package modelLogicExceptions;

/**
 * Eccezione che si verifica quando nella stessa torre o area il giocatore ha
 * già posizionato un famigliare non neutro e si accinge a posizionarne un altro
 * non neutro
 */
public class SameTowerException extends Exception {
	public SameTowerException() {
		super();
	}
}
