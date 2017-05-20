package main.game.players;

import main.game.players.dashboard.SpazioCartaImpresa;
import main.game.players.dashboard.SpazioCartaPersonaggio;
import main.game.players.dashboard.SpazioCartaTerritorio;

/**
 * Classe per tenere traccia delle Carte Sviluppo associate al Giocatore
 */
public class PlanciaGiocatore {

	/**
	 * 
	 */
	private SpazioCartaImpresa[] imprese;

	/**
	 * 
	 */
	private SpazioCartaPersonaggio[] personaggio;

	/**
	 * 
	 */
	private SpazioCartaTerritorio[] territorio;

	/**
	 * 
	 */
	private SpazioCartaTerritorio[] spazio;

	/**
	 * Default constructor
	 */
	public PlanciaGiocatore() {
	}

}