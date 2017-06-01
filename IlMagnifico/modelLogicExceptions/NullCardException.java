package modelLogicExceptions;

/**
 * Eccezione che viene sollevata quando si esegue uno spostamento su un'azione
 * della torre dove non c'è nessuna carta
 * 
 */
public class NullCardException extends Exception {
	public NullCardException() {
		super();
	}
}
