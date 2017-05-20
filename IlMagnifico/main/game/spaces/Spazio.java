package main.game.spaces;

import main.game.players.PuntiGiocatore;

/**
 * Interfaccia che definisce il concetto di Spazio all'interno del Gioco
 */
public interface Spazio {

	/**
	 * @return true se lo spazio è occupato
	 */
	public boolean spazioOccupato();

	/**
	 * Punti Giocatore richiesti per occupare lo Spazio
	 * 
	 * @return PuntiGiocatore
	 */
	public PuntiGiocatore puntiRichiesti();

}