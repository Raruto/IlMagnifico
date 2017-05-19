package main.game.spaces;

import java.util.*;

import main.game.activatables.Azione;
import main.game.players.Pedina;
import main.game.players.PuntiGiocatore;

/**
 * 
 */
public class SpazioAzione implements SpazioPedina {

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
		// restituisce la pedina
		return this.pedina;
	}

	/**
	 * @param pedina
	 * @return
	 */
	public void aggiungiPedina(Pedina pedina) {
		// mette una pedina nello spazio azione corrispondente
		this.pedina = pedina;
	}

	@Override
	public boolean spazioOccupato() {
		// guarda se c'e' una pedina nello spazio dell'azione. Restituisce vero
		// se lo spazio e' occupato, falso altrimenti
		if (this.pedina == null)
			return true;
		else
			return false;
	}

	@Override
	public PuntiGiocatore puntiRichiesti() {
		// punti richiesti da parte del giocatore per posizionare la pedina nel
		// corrispondente spazio azione
		return null;
	}

}