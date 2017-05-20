package main.game.cards;

import java.util.*;

import main.game.cards.development.*;

/**
 * 
 */
public class Mazzo {

	/**
	 * Mazzo delle Carte Edificio associate al periodo corrente
	 */
	private ArrayList<CartaEdificio> carteEdificio;

	/**
	 * Mazzo delle Carte Impresa associate al periodo corrente
	 */
	private ArrayList<CartaImpresa> carteImpresa;

	/**
	 * Mazzo delle Carte Territorio associate al periodo corrente
	 */
	private ArrayList<CartaTerritorio> carteTerritorio;

	/**
	 * Mazzo delle Carte Personaggio associate al periodo corrente
	 */
	private ArrayList<CartaPersonaggio> cartePersonaggio;

	/**
	 * Periodo corrente di gioco (1,2,3) (ad ogni incremento del periodo di
	 * gioco le liste dei mazzi vengono aggiornati con le carte del relativo
	 * periodo)
	 */
	private int periodo;

	/**
	 * Default constructor
	 */
	public Mazzo() {
		this.periodo = 1;

		carteEdificio = new ArrayList<CartaEdificio>();
		carteImpresa = new ArrayList<CartaImpresa>();
		carteTerritorio = new ArrayList<CartaTerritorio>();
		cartePersonaggio = new ArrayList<CartaPersonaggio>();

		inizializzaMazzi();
	}

	/**
	 * Inizializza i mazzi sulla base del Periodo corrente
	 * 
	 * @return void
	 */
	private void inizializzaMazzi() {
		for (CarteTerritorio c : CarteTerritorio.values()) {
			if (c.getPeriodoCarta() == this.periodo)
				this.carteTerritorio.add(0, new CartaTerritorio(c));
		}

		for (CartePersonaggio c : CartePersonaggio.values()) {
			if (c.getPeriodoCarta() == this.periodo)
				this.cartePersonaggio.add(0, new CartaPersonaggio(c));
		}
		for (CarteEdificio c : CarteEdificio.values()) {
			if (c.getPeriodoCarta() == this.periodo)
				this.carteEdificio.add(0, new CartaEdificio(c));
		}
		for (CarteImpresa c : CarteImpresa.values()) {
			if (c.getPeriodoCarta() == this.periodo)
				this.carteImpresa.add(0, new CartaImpresa(c));
		}

	}

	/**
	 * Riempie gli ArrayList con le carte dei periodi corrispondenti e mischia
	 * gli elementi all'interno degli ArrayList inizializzo delle variabili che
	 * mi serviranno per il mescolamento delle carte
	 * 
	 * @return void
	 */
	public void mescolaMazzo() {
		CartaTerritorio cartaTerritorioTemp1 = new CartaTerritorio(null);
		CartaTerritorio cartaTerritorioTemp2 = new CartaTerritorio(null);
		CartaPersonaggio cartaPersonaggioTemp1 = new CartaPersonaggio(null);
		CartaPersonaggio cartaPersonaggioTemp2 = new CartaPersonaggio(null);
		CartaEdificio cartaEdificioTemp1 = new CartaEdificio(null);
		CartaEdificio cartaEdificioTemp2 = new CartaEdificio(null);
		CartaImpresa cartaImpresaTemp1 = new CartaImpresa(null);
		CartaImpresa cartaImpresaTemp2 = new CartaImpresa(null);
		int index1;
		int index2;
		int contatore;
		Random random = new Random();

		inizializzaMazzi();

		// mischio i mazzetti dei tipi di carte prendendo sempre due oggetti
		// dell'ArrayList e scambiandoli
		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.cartePersonaggio.size());
			index2 = random.nextInt(this.cartePersonaggio.size()); //anzichè un numero generico utilizzerei la dimensione effettiva dell'arraylist
			cartaPersonaggioTemp1 = this.cartePersonaggio.get(index1);
			cartaPersonaggioTemp2 = this.cartePersonaggio.get(index2);
			this.cartePersonaggio.set(index2, cartaPersonaggioTemp1);
			this.cartePersonaggio.set(index1, cartaPersonaggioTemp2);
		}

		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.carteTerritorio.size());
			index2 = random.nextInt(this.carteTerritorio.size());
			cartaTerritorioTemp1 = this.carteTerritorio.get(index1);
			cartaTerritorioTemp2 = this.carteTerritorio.get(index2);
			this.carteTerritorio.set(index1, cartaTerritorioTemp2);
			this.carteTerritorio.set(index2, cartaTerritorioTemp1);
		}
		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.carteEdificio.size());
			index2 = random.nextInt(this.carteEdificio.size());
			cartaEdificioTemp1 = this.carteEdificio.get(index1);
			cartaEdificioTemp2 = this.carteEdificio.get(index2);
			this.carteEdificio.set(index1, cartaEdificioTemp2);
			this.carteEdificio.set(index2, cartaEdificioTemp1);
		}
		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.carteImpresa.size());
			index2 = random.nextInt(this.carteImpresa.size());
			cartaImpresaTemp1 = this.carteImpresa.get(index1);
			cartaImpresaTemp2 = this.carteImpresa.get(index2);
			this.carteImpresa.set(index2, cartaImpresaTemp1);
			this.carteImpresa.set(index1, cartaImpresaTemp2);
		}
		return;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Edificio
	 * 
	 * @return CartaEdificio
	 */
	public CartaEdificio getCartaEdificio() {
		CartaEdificio cartaTemporanea = new CartaEdificio(null);
		cartaTemporanea = this.carteEdificio.get(0);
		this.carteEdificio.remove(0);
		return cartaTemporanea;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Territorio
	 * 
	 * @return CartaTerritorio
	 */
	public CartaTerritorio getCartaTerritorio() {
		CartaTerritorio cartaTemporanea = new CartaTerritorio(null);
		cartaTemporanea = this.carteTerritorio.get(0);
		this.carteTerritorio.remove(0);
		return cartaTemporanea;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Personaggio
	 * 
	 * @return CartaPersonaggio
	 */
	public CartaPersonaggio getCartaPersonaggio() {
		CartaPersonaggio cartaTemporanea = new CartaPersonaggio(null);
		cartaTemporanea = this.cartePersonaggio.get(0);
		this.cartePersonaggio.remove(0);
		return cartaTemporanea;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Impresa
	 * 
	 * @return CartaImpresa
	 */
	public CartaImpresa getCartaImpresa() {
		CartaImpresa cartaTemporanea = new CartaImpresa(null);
		cartaTemporanea = this.carteImpresa.get(0);
		this.carteImpresa.remove(0);
		return cartaTemporanea;
	}

	
	/*
	 * Incrementa l'attributo periodo di 1
	 */
	public void incrementaPeriodo() {
		this.periodo++;
	}

}