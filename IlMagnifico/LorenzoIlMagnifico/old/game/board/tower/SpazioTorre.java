package LorenzoIlMagnifico.old.game.board.tower;

import LorenzoIlMagnifico.old.game.cards.development.CartaSviluppo;
import LorenzoIlMagnifico.old.game.players.Pedina;
import LorenzoIlMagnifico.old.game.players.PuntiGiocatore;
import LorenzoIlMagnifico.old.game.players.RisorseGiocatore;
import LorenzoIlMagnifico.old.game.players.dashboard.SpazioCartaSviluppo;
import LorenzoIlMagnifico.old.game.spaces.SpazioPedina;

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
		CartaSviluppo carta = this.carta;
		this.carta = null;
		return carta;
	}

	public void posizionaCarta(CartaSviluppo carta) {
		if (this.carta == null) {
			this.carta = carta;
		}
	}
}