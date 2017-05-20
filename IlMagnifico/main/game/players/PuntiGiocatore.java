package main.game.players;

import java.util.*;

/**
 * 
 */
public class PuntiGiocatore {

	/**
	 * "Tracciato" Punti Vittoria associati al Giocatore
	 */
	private int puntiVittoria;

	/**
	 * "Tracciato" Punti Militari associati al Giocatore
	 */
	private int puntiMilitari;

	/**
	 * "Tracciato" Punti Fede associati al Giocatore
	 */
	private int puntiFede;

	/**
	 * Default constructor
	 * 
	 * @param puntiVittoria
	 * @param puntiMilitari
	 * @param puntiFede
	 */
	public PuntiGiocatore(int puntiMilitari, int puntiVittoria, int puntiFede) {
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