package main.game.board.tower;

import main.game.cards.development.CartaSviluppo;
import main.game.players.Pedina;
import main.game.players.PuntiGiocatore;
import main.game.players.RisorseGiocatore;
import main.game.players.dashboard.SpazioCartaSviluppo;
import main.game.spaces.SpazioPedina;

/**
 * 
 */
public class SpazioTorre implements SpazioPedina, SpazioCartaSviluppo {

	/**
	 * Default constructor
	 */
	public SpazioTorre() {
	}

	/**
	 * 
	 */
	private CartaSviluppo carta;

	/**
	 * 
	 */
	private Pedina pedina;

	/**
	 * @return
	 */
	public CartaSviluppo visualizzaCarta() {
		// TODO implement here
		return null;
	}

	/**
	 * @param carta
	 * @return
	 */
	public void aggiungiCarta(CartaSviluppo carta) {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public Pedina visualizzaPedina() {
		// TODO implement here
		return null;
	}

	/**
	 * @param pedina
	 * @return
	 */
	public void aggiungiPedina(Pedina pedina) {
		// TODO implement here
		return;
	}

	@Override
	public boolean spazioOccupato() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PuntiGiocatore puntiGiocatoreRichiesti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RisorseGiocatore risorseGiocatoreRichieste() {
		// TODO Auto-generated method stub
		return null;
	}

	public CartaSviluppo prendiCarta() {
		CartaSviluppo carta = this.popCarta();
		pushCarta();
		return carta;
	}

	private CartaSviluppo popCarta() {
		CartaSviluppo carta = this.carta;
		this.carta = null;
		return carta;

	}

	private void pushCarta() {
		if (this != null) {
			// TODO: implementare (giocatore prende una carta dalla torre,
			// bisogna sostituire la carta dal piano solo se non c'è più nessuna
			// carta sul piano...)
		}
	}

}