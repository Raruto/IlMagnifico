package main.game.players.dashboard;

import main.game.cards.development.CartaSviluppo;
import main.game.players.PuntiGiocatore;
import main.game.players.RisorseGiocatore;

/**
 * Classe contenitore per una singola Carta Sviluppo all'interno della Plancia
 * Giocatore, implementa SpazioCartaSviluppo
 */
public class SpazioCartaPlancia implements SpazioCartaSviluppo {

	/**
	 * Default constructor
	 */
	public SpazioCartaPlancia() {
	}

	/**
	 * 
	 */
	private CartaSviluppo carta;

	private PuntiGiocatore puntiGiocatoreRichiesti;
	private RisorseGiocatore risorseGiocatoreRichieste;

	/**
	 * @return
	 */
	public CartaSviluppo visualizzaCarta() {
		return this.carta;
	}

	/**
	 * @param carta
	 * @return
	 */
	public void aggiungiCarta(CartaSviluppo carta) {
		this.carta = carta;
	}

	@Override
	public boolean spazioOccupato() {
		if (this.carta == null)
			return false;
		else
			return true;
	}

	@Override
	public PuntiGiocatore puntiGiocatoreRichiesti() {
		return this.puntiGiocatoreRichiesti;
	}

	@Override
	public RisorseGiocatore risorseGiocatoreRichieste() {
		return this.risorseGiocatoreRichieste;
	}

}