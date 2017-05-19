package main.game.spaces;

import java.util.*;

import main.game.players.Pedina;

/**
 * 
 */
public interface SpazioPedina extends Spazio {

	/**
	 * @return
	 */
	public Pedina visualizzaPedina();

	/**
	 * @param pedina
	 * @return
	 */
	public void aggiungiPedina(Pedina pedina);

}