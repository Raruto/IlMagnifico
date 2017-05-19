package main.game.res;

import java.util.*;

/**
 * 
 */
public class Dado {

	private int dadoBianco;
	private int dadoArancione;
	private int dadoNero;

	/**
	 * Default constructor
	 */
	public Dado() {
		this.dadoBianco = 0;
		this.dadoArancione = 0;
		this.dadoNero = 0;
	}

	/**
	 * Lancia tutti i dadi
	 */
	public void lancia() {
		dadoBianco = generaNumeroRandom(6);
		dadoArancione = generaNumeroRandom(6);
		dadoNero = generaNumeroRandom(6);
	}

	/**
	 * @param numeroFacce
	 */
	private int generaNumeroRandom(int numeroFacce) {
		return new Random().nextInt(numeroFacce - 1) + 1;
	}

	public int getDadoBianco() {
		return dadoBianco;
	}

	public int getDadoArancione() {
		return dadoArancione;
	}

	public int getDadoNero() {
		return dadoNero;
	}
}