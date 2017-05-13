package main.game.spaces;

import java.util.*;

import main.game.activatable.Azione;
import main.game.cards.development.CartaSviluppo;
import main.game.players.Pedina;
import main.game.res.PuntiGiocatore;
import main.game.spaces.dashboard.SpazioCartaSviluppo;

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
	public PuntiGiocatore puntiRichiesti() {
		// TODO Auto-generated method stub
		return null;
	}

}