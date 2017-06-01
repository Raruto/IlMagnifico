package main.model;

import java.util.*;

/**
 * 
 */
public class Risorsa {

	/**
	 * Default constructor
	 */
	public Risorsa() {
	}

	/**
	 * 
	 */
	private int monete;

	/**
	 * 
	 */
	private int legno;

	/**
	 * 
	 */
	private int pietre;

	/**
	 * 
	 */
	private int servitori;

	/**
	 * Applica la variazione delle monete. Se il parametro in ingresso ﾃｨ
	 * negativo, vengono sottratte monete
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaMonete(int variazione) {
		this.monete = this.monete + variazione;
	}

	/**
	 * Applica la variazione delle unitﾃ� di legno.Se il parametro in ingresso
	 * ﾃｨ negativo, vengono sottratte unitﾃ�
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaLegno(int variazione) {
		this.legno = this.legno + variazione;
	}

	/**
	 * Applica la variazione di unitﾃ� di pietre. Se il parametro in ingresso ﾃｨ
	 * negativo, vengono sottratte unitﾃ�
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPietre(int variazione) {
		this.pietre = this.pietre + variazione;
	}

	/**
	 * Applica la variazione di unitﾃ� di servitori. Se il parametro in ingresso
	 * ﾃｨ negativo, vengono sottratte unitﾃ�
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaServitori(int variazione) {
		this.servitori = this.servitori + variazione;
	}

	/**
	 * Ritorna la quantitﾃ� di monete possedute dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getMonete() {
		return this.monete;
	}

	/**
	 * Ritorna la quantitﾃ� di legno posseduto dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getLegno() {
		return this.legno;
	}

	/**
	 * Ritorna la quantitﾃ� di pietre possedute dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPietre() {
		return this.pietre;
	}

	/**
	 * Ritorna la quantitﾃ� di servitori posseduti dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getServitori() {
		return this.servitori;
	}

	public void setMonete(int monete) {
		this.monete = monete;
	}

	public void setLegno(int legno) {
		this.legno = legno;
	}

	public void setPietre(int pietre) {
		this.pietre = pietre;
	}

	public void setServitori(int servitori) {
		this.servitori = servitori;
	}
}