package main.model;

import java.util.*;

/**
 * 
 */
public class Risorsa {

	/**
	 * Default constructor
	 */
	public Risorsa(Giocatore giocatore) {
		this.giocatoreAppartenenza = giocatore;
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

	private Giocatore giocatoreAppartenenza;

	/**
	 * Applica la variazione delle monete. Se il parametro in ingresso e'
	 * negativo, vengono sottratte monete
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaMonete(int variazione) {
		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			if (this.giocatoreAppartenenza.getScomunica(0).attivaOnRiceviMonete() && variazione > 0)
				this.monete--;
		this.monete = this.monete + variazione;
	}

	/**
	 * Applica la variazione delle unita' di legno.Se il parametro in ingresso
	 * e' negativo, vengono sottratte unita'
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaLegno(int variazione) {
		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			if (this.giocatoreAppartenenza.getScomunica(0).attivaOnRiceviPietreOLegno() && variazione > 0)
				this.legno--;
		this.legno = this.legno + variazione;
	}

	/**
	 * Applica la variazione di unita' di pietre. Se il parametro in ingresso e'
	 * negativo, vengono sottratte unita'
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPietre(int variazione) {
		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			if (this.giocatoreAppartenenza.getScomunica(0).attivaOnRiceviPietreOLegno() && variazione > 0)
				this.pietre--;
		this.pietre = this.pietre + variazione;
	}

	/**
	 * Applica la variazione di unita' di servitori. Se il parametro in ingresso
	 * e' negativo, vengono sottratte unita'
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaServitori(int variazione) {
		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			if (this.giocatoreAppartenenza.getScomunica(0).attivaOnRiceviServitori() && variazione > 0)
				this.servitori--;
		this.servitori = this.servitori + variazione;
	}

	/**
	 * Ritorna la quantita' di monete possedute dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getMonete() {
		return this.monete;
	}

	/**
	 * Ritorna la quantita' di legno posseduto dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getLegno() {

		return this.legno;
	}

	/**
	 * Ritorna la quantita' di pietre possedute dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPietre() {

		return this.pietre;
	}

	/**
	 * Ritorna la quantita' di servitori posseduti dal giocatore
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