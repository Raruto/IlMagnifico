package main.game.players;

import java.util.*;

/**
 * 
 */
public class FamigliaGiocatore {

	/**
	 * Pedina Arancione associata al giocatore
	 */
	private Pedina pedinaArancione;

	/**
	 * Pedina Bianca associata al giocatore
	 */
	private Pedina pedinaBianca;

	/**
	 * Pedina Nera associata al giocatore
	 */
	private Pedina pedinaNera;

	/**
	 * Pedina Neutrale associata al giocatore
	 */
	private Pedina pedinaNeutrale;

	/**
	 * Default constructor
	 * 
	 * @param pedinaArancione
	 * @param pedinaNeutrale
	 * @param pedinaNera
	 * @param pedinaBianca
	 * @param giocatore
	 */
	public FamigliaGiocatore(int pedinaArancione, int pedinaBianca, int pedinaNera, int pedinaNeutrale,
			Giocatore giocatore) {
		this.pedinaArancione = new Pedina(pedinaArancione, giocatore);
		this.pedinaBianca = new Pedina(pedinaBianca, giocatore);
		this.pedinaNera = new Pedina(pedinaNera, giocatore);
		this.pedinaNeutrale = new Pedina(pedinaNeutrale, giocatore);
	}

	/**
	 * Ritorna il RIFERIMENTO all'oggetto PedinaArancione
	 * 
	 * @return {@link Pedina}
	 */
	public Pedina getPedinaArancione() {
		return pedinaArancione;
	}

	/**
	 * Ritorna il riferimento all'oggetto PedinaBianca
	 * 
	 * @return {@link Pedina}
	 */
	public Pedina getPedinaBianca() {
		return pedinaBianca;
	}

	/**
	 * Ritorna il RIFERIMENTO all'oggetto PedinaNera
	 * 
	 * @return {@link Pedina}
	 */
	public Pedina getPedinaNera() {
		return pedinaNera;
	}

	/**
	 * Ritorna il RIFERIMENTO all'oggetto PedinaNeutrale
	 * 
	 * @return {@link Pedina}
	 */
	public Pedina getPedinaNeutrale() {
		return pedinaNeutrale;
	}

	/**
	 * Imposta il VALORE dell'oggetto PedinaArancione
	 * 
	 * @param valoreArancione
	 */
	public void setValorePedinaArancione(int valoreArancione) {
		this.pedinaArancione.setValore(valoreArancione);
	}

	/**
	 * Imposta il VALORE dell'oggetto PedinaBianca
	 * 
	 * @param valoreBianca
	 */
	public void setValorePedinaBianca(int valoreBianca) {
		this.pedinaBianca.setValore(valoreBianca);
	}

	/**
	 * Imposta il VALORE dell'oggetto PedinaNera
	 * 
	 * @param valoreNera
	 */
	public void setValorePedinaNera(int valoreNera) {
		this.pedinaNera.setValore(valoreNera);
	}

	/**
	 * Imposta il VALORE dell'oggetto PedinaNeutrale
	 * 
	 * @param valoreNeutrale
	 */
	public void setValorePedinaNeutrale(int valoreNeutrale) {
		this.pedinaNeutrale.setValore(valoreNeutrale);
	}

}