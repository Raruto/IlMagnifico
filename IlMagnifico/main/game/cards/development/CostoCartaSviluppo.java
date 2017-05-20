package main.game.cards.development;

import main.game.players.PuntiGiocatore;
import main.game.players.RisorseGiocatore;

/**
 * Classe che definisce il concetto di Costo di una Carta
 */
public class CostoCartaSviluppo {

	/**
	 * Costo RisorseGiocatore
	 */
	private RisorseGiocatore risorse;

	/**
	 * Costo PuntiGiocatore
	 */
	private PuntiGiocatore punti;

	/**
	 * Default constructor
	 */
	public CostoCartaSviluppo(RisorseGiocatore risorse, PuntiGiocatore punti) {
		this.risorse = risorse;
		this.punti = punti;
	}
	
	/* NB sette parametri sono tanti!!! */
	/*
 	public CostoCartaSviluppo(int legna, int pietra, int servitori, int monete, int PM, int PV, int PF) {
		this.risorse = new RisorseGiocatore(legna, monete, pietra, servitori);
		this.punti = new PuntiGiocatore(PM, PV, PF);
	}
	*/
	
	public RisorseGiocatore getCostoRisorseGiocatore() {
		return this.risorse;
	}

	public PuntiGiocatore getCostoPuntiGiocatore() {
		return this.punti;
	}
	
}