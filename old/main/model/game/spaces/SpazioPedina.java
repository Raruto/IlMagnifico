package main.model.game.spaces;

import main.model.game.players.Pedina;

/**
 * Interfaccia che estende il concetto di Spazio per lo Spazio Pedina
 * all'interno del Gioco
 */
public interface SpazioPedina extends Spazio {

	/**
	 * Riferimento alla Pedina attualmente presente nello Spazio Pedina
	 * 
	 * @return Pedina
	 */
	public Pedina visualizzaPedina();

	/**
	 * Inserisce la Pedina nello Spazio Pedina
	 * 
	 * @param Pedina
	 */
	public void aggiungiPedina(Pedina pedina);

}