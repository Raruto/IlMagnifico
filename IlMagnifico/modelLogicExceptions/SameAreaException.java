package modelLogicExceptions;

/**
 * Eccezione che si verifica quando nella stessa torre o area il giocatore ha
 * già posizionato un famigliare non neutro e si accinge a posizionarne un altro
 * non neutro
 */
public class SameAreaException extends Exception {
	public SameAreaException() {
		super();
	}
}
