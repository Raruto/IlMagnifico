package main.model.exceptions;

/**
 * Eccezione che si verifica quando un giocatore non ha abbastanza risorse per
 * potere pagare il costo di una carta
 */
public class NoEnoughResourcesException extends Exception {
	public NoEnoughResourcesException() {
		super();
	}
}
