package LorenzoIlMagnifico;

import java.util.*;

/**
 * 
 */
public class Punti {

	/**
	 * Default constructor
	 */
	public Punti() {
	}

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
	 * Applica la variazione di punti vittoria. Se il parametro in ingresso è
	 * negativo, vengono sottratti punti vittoria
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPuntiVittoria(int variazione) {
		this.puntiVittoria = this.puntiVittoria + variazione;
	}

	/**
	 * Applica la variazione di punti millitari. Se il parametro in ingresso è
	 * negativo vengono sottratti punti militari
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPuntiMilitari(int variazione) {
		this.puntiMilitari = this.puntiMilitari + variazione;
	}

	/**
	 * Applica la variazione di punti fede. Se il parametro in ingresso è
	 * negativo, vengono sottratti punti fede
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPuntiFede(int variazione) {
		this.puntiFede = this.puntiFede + variazione;
	}

	/**
	 * Ritorna la quantità di punti vittoria posseduti dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPuntiVittoria() {
		return this.puntiVittoria;
	}

	/**
	 * Ritorna la quantità di punti militari posseduta dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPuntiMilitari() {
		return this.puntiMilitari;
	}

	/**
	 * Ritorna la quantità di punti fede posseduta dal giocatore
	 * 
	 * @param
	 * @return int
	 **/
	public int getPuntiFede() {
		return this.puntiFede;
	}

	/**
	 * Cambia la quantità di punti fede del giocatore al valore passato in
	 * ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setPuntiFede(int puntiFede) {
		this.puntiFede = puntiFede;
	}
}