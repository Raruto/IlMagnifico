package main.game.activatable;

import java.util.*;

/**
 * 
 */
public abstract class Attivabile {
	protected int contatoreAttivazioni;
	protected boolean permanente;

	/**
	 * @return
	 */
	public abstract void attiva();

}