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
	private ArrayList<CartaEdificio> mazzoCarteEdificio;

	/**
	 * Mazzo delle Carte Impresa associate al periodo corrente
	 */
	private ArrayList<CartaImpresa> mazzoCarteImpresa;

	/**
	 * Mazzo delle Carte Territorio associate al periodo corrente
	 */
	private ArrayList<CartaTerritorio> mazzoCarteTerritorio;

	/**
	 * Mazzo delle Carte Personaggio associate al periodo corrente
	 */
	private ArrayList<CartaPersonaggio> mazzoCartePersonaggio;

	/**
	 * Periodo corrente di gioco (1,2,3) (ad ogni incremento del periodo di
	 * gioco le liste dei mazzi vengono aggiornati con le carte del relativo
	 * periodo)
	 */
	private int periodoCorrente;

	/**
	 * Default constructor
	 */
	public Mazzo() {
		this.periodoCorrente = 1;

		mazzoCarteEdificio = new ArrayList<CartaEdificio>();
		mazzoCarteImpresa = new ArrayList<CartaImpresa>();
		mazzoCarteTerritorio = new ArrayList<CartaTerritorio>();
		mazzoCartePersonaggio = new ArrayList<CartaPersonaggio>();

		inizializzaMazzi();
	}

	/**
	 * Inizializza i mazzi sulla base del Periodo corrente
	 * 
	 * @return void
	 */
	private void inizializzaMazzi() {
		for (CarteTerritorio c : CarteTerritorio.values()) {
			if (c.getPeriodoCarta() == this.periodoCorrente)
				this.mazzoCarteTerritorio.add(0, new CartaTerritorio(c));
		}

		for (CartePersonaggio c : CartePersonaggio.values()) {
			if (c.getPeriodoCarta() == this.periodoCorrente)
				this.mazzoCartePersonaggio.add(0, new CartaPersonaggio(c));
		}
		for (CarteEdificio c : CarteEdificio.values()) {
			if (c.getPeriodoCarta() == this.periodoCorrente)
				this.mazzoCarteEdificio.add(0, new CartaEdificio(c));
		}
		for (CarteImpresa c : CarteImpresa.values()) {
			if (c.getPeriodoCarta() == this.periodoCorrente)
				this.mazzoCarteImpresa.add(0, new CartaImpresa(c));
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
			index1 = random.nextInt(this.mazzoCartePersonaggio.size());
			index2 = random.nextInt(this.mazzoCartePersonaggio.size()); //anzichè un numero generico utilizzerei la dimensione effettiva dell'arraylist
			cartaPersonaggioTemp1 = this.mazzoCartePersonaggio.get(index1);
			cartaPersonaggioTemp2 = this.mazzoCartePersonaggio.get(index2);
			this.mazzoCartePersonaggio.set(index2, cartaPersonaggioTemp1);
			this.mazzoCartePersonaggio.set(index1, cartaPersonaggioTemp2);
		}

		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.mazzoCarteTerritorio.size());
			index2 = random.nextInt(this.mazzoCarteTerritorio.size());
			cartaTerritorioTemp1 = this.mazzoCarteTerritorio.get(index1);
			cartaTerritorioTemp2 = this.mazzoCarteTerritorio.get(index2);
			this.mazzoCarteTerritorio.set(index1, cartaTerritorioTemp2);
			this.mazzoCarteTerritorio.set(index2, cartaTerritorioTemp1);
		}
		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.mazzoCarteEdificio.size());
			index2 = random.nextInt(this.mazzoCarteEdificio.size());
			cartaEdificioTemp1 = this.mazzoCarteEdificio.get(index1);
			cartaEdificioTemp2 = this.mazzoCarteEdificio.get(index2);
			this.mazzoCarteEdificio.set(index1, cartaEdificioTemp2);
			this.mazzoCarteEdificio.set(index2, cartaEdificioTemp1);
		}
		for (contatore = 0; contatore < 10; contatore++) {
			index1 = random.nextInt(this.mazzoCarteImpresa.size());
			index2 = random.nextInt(this.mazzoCarteImpresa.size());
			cartaImpresaTemp1 = this.mazzoCarteImpresa.get(index1);
			cartaImpresaTemp2 = this.mazzoCarteImpresa.get(index2);
			this.mazzoCarteImpresa.set(index2, cartaImpresaTemp1);
			this.mazzoCarteImpresa.set(index1, cartaImpresaTemp2);
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
		cartaTemporanea = this.mazzoCarteEdificio.get(0);
		this.mazzoCarteEdificio.remove(0);
		return cartaTemporanea;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Territorio
	 * 
	 * @return CartaTerritorio
	 */
	public CartaTerritorio getCartaTerritorio() {
		CartaTerritorio cartaTemporanea = new CartaTerritorio(null);
		cartaTemporanea = this.mazzoCarteTerritorio.get(0);
		this.mazzoCarteTerritorio.remove(0);
		return cartaTemporanea;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Personaggio
	 * 
	 * @return CartaPersonaggio
	 */
	public CartaPersonaggio getCartaPersonaggio() {
		CartaPersonaggio cartaTemporanea = new CartaPersonaggio(null);
		cartaTemporanea = this.mazzoCartePersonaggio.get(0);
		this.mazzoCartePersonaggio.remove(0);
		return cartaTemporanea;
	}

	/**
	 * Esegue il POP della prima carta dalla lista delle Carte Impresa
	 * 
	 * @return CartaImpresa
	 */
	public CartaImpresa getCartaImpresa() {
		CartaImpresa cartaTemporanea = new CartaImpresa(null);
		cartaTemporanea = this.mazzoCarteImpresa.get(0);
		this.mazzoCarteImpresa.remove(0);
		return cartaTemporanea;
	}

	
	/*
	 * Incrementa l'attributo periodo di 1
	 */
	public void incrementaPeriodo() {
		this.periodoCorrente++;
	}

}