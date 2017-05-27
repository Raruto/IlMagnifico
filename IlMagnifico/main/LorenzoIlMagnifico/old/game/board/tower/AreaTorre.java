package main.LorenzoIlMagnifico.old.game.board.tower;

import main.LorenzoIlMagnifico.old.game.cards.development.CartaSviluppo;

/**
 * Classe che definisce il concetto di Area delle Torri all'interno del
 * Tabellone di gioco, composta di: {@link TorreEdificio}, {@link TorreImpresa},
 * {@link TorrePersonaggio}, {@link TorreTerritorio}
 */
public class AreaTorre {

	/**
	 * Default constructor
	 */
	public AreaTorre() {
	}

	/**
	 * Torre delle Carte Edificio associate al periodo corrente
	 */
	private TorreEdificio torreEdificio;

	/**
	 * Torre delle Carte Impresa associate al periodo corrente
	 */
	private TorreImpresa torreImpresa;

	/**
	 * Torre delle Carte Personaggio associate al periodo corrente
	 */
	private TorrePersonaggio torrePersonaggio;

	/**
	 * Torre delle Carte Territorio associate al periodo corrente
	 */
	private TorreTerritorio torreTerritorio;

	/**
	 * Periodo corrente di gioco (1,2,3) (ad ogni incremento del periodo di
	 * gioco le torri vengono aggiornate con le carte del relativo periodo)
	 */
	private int periodoCorrente;

	public CartaSviluppo prendiCartaTorreImpresa(int piano) {
		return torreImpresa.prendiCarta(piano);
	}

	public CartaSviluppo prendiCartaTorreTerritorio(int piano) {
		return torreTerritorio.prendiCarta(piano);
	}

	public CartaSviluppo prendiCartaTorrePersonaggio(int piano) {
		return torrePersonaggio.prendiCarta(piano);
	}

	public CartaSviluppo prendiCartaTorreEdificio(int piano) {
		return torreEdificio.prendiCarta(piano);
	}

	public CartaSviluppo visualizzaCartaTorreImpresa(int piano) {
		return torreImpresa.visualizzaCarta(piano);
	}

	public CartaSviluppo visualizzaCartaTorreTerritorio(int piano) {
		return torreTerritorio.visualizzaCarta(piano);
	}

	public CartaSviluppo visualizzaCartaTorrePersonaggio(int piano) {
		return torrePersonaggio.visualizzaCarta(piano);
	}

	public CartaSviluppo visualizzaCartaTorreEdificio(int piano) {
		return torreEdificio.visualizzaCarta(piano);
	}

	/*
	 * Incrementa l'attributo periodo di 1
	 */
	public void incrementaPeriodo() {
		this.periodoCorrente++;
	}

	public TorreImpresa getCartaTorreImpresa() {
		return this.torreImpresa;
	}

	public TorreTerritorio getCartaTorreTerritorio() {
		return this.torreTerritorio;
	}
}