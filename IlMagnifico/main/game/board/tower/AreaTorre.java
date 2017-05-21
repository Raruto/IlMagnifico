package main.game.board.tower;

import main.game.cards.development.CartaSviluppo;

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

	public CartaSviluppo getCartaTorreImpresa(int piano) {
		return torreImpresa.prendiCarta(piano);
	}

	public CartaSviluppo getCartaTorreTerritorio(int piano) {
		return torreTerritorio.prendiCarta(piano);
	}

	public CartaSviluppo getCartaTorrePersonaggio(int piano) {
		return torrePersonaggio.prendiCarta(piano);
	}

	public CartaSviluppo getCartaTorreEdificio(int piano) {
		return torreEdificio.prendiCarta(piano);
	}

	/*
	 * Incrementa l'attributo periodo di 1
	 */
	public void incrementaPeriodo() {
		this.periodoCorrente++;
	}
}