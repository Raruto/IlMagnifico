package main.game.spaces;

import main.game.players.PuntiGiocatore;
import main.game.players.RisorseGiocatore;

/**
 * Interfaccia che definisce il concetto di Spazio all'interno del Gioco
 */
public interface Spazio {

	/**
	 * Ritorna un valore booleano per determinare se lo Spazio è occupato
	 * 
	 * @return true se occupato
	 */
	public boolean spazioOccupato();

	/**
	 * Punti Giocatore richiesti per occupare lo Spazio
	 * 
	 * @return PuntiGiocatore
	 */
	public PuntiGiocatore puntiGiocatoreRichiesti();

	/**
	 * Risorse Giocatore richieste per occupare lo Spazio
	 * 
	 * @return RisorseGiocatore
	 */
	public RisorseGiocatore risorseGiocatoreRichieste();

}