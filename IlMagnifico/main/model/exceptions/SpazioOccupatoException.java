package main.model.exceptions;

/**
 * Eccezione che si verifica quando lo spazio dove si vuole spostare il
 * famigliare e' gia'  occupato da un altro famigliare
 * 
 */
public class SpazioOccupatoException extends Exception {
	public SpazioOccupatoException() {
		super();
	}
}
