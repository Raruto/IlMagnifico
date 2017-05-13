package main.game.spaces;

import java.util.*;

import main.game.activatable.Azione;
import main.game.players.Pedina;
import main.game.res.PuntiGiocatore;

/**
 * 
 */
public class SpazioAzione implements SpazioPedina{

    /**
     * Default constructor
     */
    public SpazioAzione() {
    }

    /**
     * 
     */
    private Azione[] azione;
    private Pedina pedina;



    /**
     * @return
     */
    public Azione visualizzaAzione() {
        // TODO implement here
        return null;
    }

    /**
     * @param azione
     */
    public void aggiungiAzione(Azione azione) {
        // TODO implement here
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