package main.game.res;

import java.util.*;

/**
 * 
 */
public class PuntiGiocatore {

	/**
	 * 
	 */
	private int puntiVittoria;

	/**
	 * 
	 */
	private int puntiMilitari;

	/**
	 * 
	 */
	private int puntiFede;

	/**
	 * Default constructor
	 */
	public PuntiGiocatore(int puntiVittoria, int puntiMilitari, int puntiFede) {
		this.puntiVittoria = puntiVittoria;
		this.puntiMilitari = puntiMilitari;
		this.puntiFede = puntiFede;
	}

	public int getPuntiVittoria() {
		return puntiVittoria;
	}

	public int getPuntiMilitari() {
		return puntiMilitari;
	}

	public int getPuntiFede() {
		return puntiFede;
	}

}