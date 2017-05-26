package old.game.players;

import old.game.players.dashboard.SpazioCartaEdificio;
import old.game.players.dashboard.SpazioCartaImpresa;
import old.game.players.dashboard.SpazioCartaPersonaggio;
import old.game.players.dashboard.SpazioCartaTerritorio;

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
	private SpazioCartaEdificio[] edificio;

	/**
	 * Default constructor
	 */
	public PlanciaGiocatore() {
		this.imprese = new SpazioCartaImpresa[6];
		this.personaggio = new SpazioCartaPersonaggio[6];
		this.territorio = new SpazioCartaTerritorio[6];
		this.edificio = new SpazioCartaEdificio[6];
	}

	public SpazioCartaImpresa getSpazioCartaImpresa(int index) {
		return this.imprese[index];
	}

	public SpazioCartaPersonaggio getSpazioCartaPersonaggio(int index) {
		return this.personaggio[index];
	}

	public SpazioCartaTerritorio getSpazioCartaTerritorio(int index) {
		return this.territorio[index];
	}

	public SpazioCartaEdificio getSpazioCartaEdificio(int index) {
		return this.edificio[index];
	}

}