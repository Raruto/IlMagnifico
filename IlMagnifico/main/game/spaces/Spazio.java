package main.game.spaces;

import java.util.*;

import main.game.players.PuntiGiocatore;

/**
 * 
 */
public interface Spazio {

	/**
	 * @return
	 */
	public boolean spazioOccupato();

	/**
	 * @return
	 */
	public PuntiGiocatore puntiRichiesti();

}