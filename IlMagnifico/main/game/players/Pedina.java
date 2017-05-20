package main.game.players;

import java.util.*;

/**
 * 
 */
public class Pedina {

	/**
	 * Valore attuale della pedina
	 */
	private int valore;
	/**
	 * Possessore della pedina
	 */
	private Giocatore possessore;

	/**
	 * Default constructor
	 */
	public Pedina(int valore, Giocatore possessore) {
		this.valore = valore;
		this.possessore = possessore;
	}

	public Giocatore getGiocatore() {
		return possessore;
	}

	public void setGiocatore(Giocatore possessore) {
		this.possessore = possessore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public int getValore() {
		return this.valore;
	}
}