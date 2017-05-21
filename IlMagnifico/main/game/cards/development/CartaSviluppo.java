package main.game.cards.development;

import java.util.*;

import main.game.activatables.Effetto;
import main.game.players.PuntiGiocatore;
import main.game.players.RiservaGiocatore;
import main.game.players.RisorseGiocatore;

/**
 * Classe che definisce il concetto di CartaSviluppo
 */
public class CartaSviluppo {

	/**
	 * Nome della Carta Sviluppo
	 */
	protected String nome;

	/**
	 * Periodo di gioco in cui viene utilizzata la Carta Sviluppo
	 */
	protected int periodo;

	/**
	 * Costo che il Giocatore deve pagare per poter acquisire la Carta Sviluppo
	 */
	protected RiservaGiocatore costo;
	/*
	 * Quando le carte richiedono di pagare punti militari è necessario avere un
	 * tot di punti militari, indicati sulla carta
	 */
	protected int sogliaPuntiMilitari;

	/**
	 * ArrayList contenente tutti gli Effetti Immediati associati alla Carta.
	 * Gli Effetti Immediati (non necessariamente tutti) vengono Attivati
	 * nell'istante che il giocatore prende la Carta dalla Torre corrispondente.
	 */
	protected ArrayList<Effetto> effettiImmediati;

	/**
	 * ArrayList contenente tutti gli Effetti Permanenti associati alla Carta
	 * Gli Effetti Permanenti (sotto determinate condizioni) vengono Attivati
	 * dopo che il giocatore a inserito la Carta nella Plancia Giocatore.
	 */
	protected ArrayList<Effetto> effettiPermanenti;

	/**
	 * Class Constructor
	 * 
	 * @param nome
	 * @param periodo
	 * @param costo
	 * @param effettiImmediati
	 * @param effettiPermanenti
	 */
	public CartaSviluppo(String nome, int periodo, RiservaGiocatore costo, int sogliaPuntiMilitari, ArrayList<Effetto> effettiImmediati,
			ArrayList<Effetto> effettiPermanenti) {
		this.nome = nome;
		this.periodo = periodo;
		this.costo = costo;
		this.sogliaPuntiMilitari=sogliaPuntiMilitari;
		this.effettiImmediati = effettiImmediati;
		this.effettiPermanenti = effettiPermanenti;
	}

	/**
	 * Ritorna un ArrayList con contenente tutti gli Effetti Immediati associati
	 * alla Carta Sviluppo
	 * 
	 * @return {@link Effetto}
	 */
	public ArrayList<Effetto> getEffettiImmediati() {
		return this.effettiImmediati;
	}

	/**
	 * Ritorna un ArrayList con contenente tutti gli Effetti Permanenti
	 * associati alla Carta Sviluppo
	 * 
	 * @return {@link Effetto}
	 */
	public ArrayList<Effetto> getEffettiPermanenti() {
		return this.effettiPermanenti;
	}

	/**
	 * Ritorna un oggetto RiservaGiocatore contenete le informazioni relative al
	 * Costo associato alla CartaSviluppo (il Giocatore che abbia intenzione di
	 * prendere la carta dallo Spazio Torre deve prima assicurarsi di poter
	 * sostenere il relativo costo)
	 * 
	 * @return {@link RiservaGiocatore}
	 */
	public RiservaGiocatore getCosto() {
		return this.costo;
	}

	/*
	 * Confronta la riserva del giocatore con i costi della carta. Ritorna true
	 * se il giocatore puo pagare
	 */
	public boolean soddisfacimentoCosti(RiservaGiocatore riserva) {
		if (confrontaRisorse(riserva.getRisorseGiocatore(), this.costo.getRisorseGiocatore())
				| confrontaPunti(riserva.getPuntiGiocatore(), this.costo.getPuntiGiocatore()))
			return true;
		else
			return false;
	}

	/*
	 * Ritorna true se il giocatore puo pagare i costi in risorse della carta
	 */
	public boolean confrontaRisorse(RisorseGiocatore risorse, RisorseGiocatore risorseCosto) {
		if (risorse.getLegni() >= risorseCosto.getLegni() && risorse.getMonete() >= risorseCosto.getMonete()
				&& risorseCosto.getPietre() >= risorseCosto.getPietre()
				&& risorse.getServitori() >= risorseCosto.getServitori())
			return true;
		else
			return false;
	}

	/*
	 * Ritorna true se il giocatore puo pagare i costi in punti della carta
	 */
	public boolean confrontaPunti(PuntiGiocatore punti, PuntiGiocatore puntiCosto) {
		// dalle carte ho visto che nei costi non ci sono mai costi in punti che
		// non siano militari.
		// Inoltre, quando si deve pagare punti militari, c'è anche una soglia
		// maggiore di punti da superare per potere pagare
		if (punti.getPuntiMilitari() >= puntiCosto.getPuntiMilitari()
				&& punti.getPuntiMilitari() >= this.sogliaPuntiMilitari)
			return true;
		else
			return false;

	}
}