package main.LorenzoIlMagnifico.old.game.players;

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

	/**
	 * Ritorna il giocatore associato alla pedina
	 * 
	 * @return {@link Giocatore}
	 */
	public Giocatore getGiocatore() {
		return possessore;
	}

	/* Lo toglierei, una pedina nasce vive e muore con un giocatore */
	/*
	 * public void setGiocatore(Giocatore possessore) { this.possessore =
	 * possessore; }
	 */

	/**
	 * Imposta il valore della Pedina
	 * 
	 * @param valore
	 */
	public void setValore(int valore) {
		this.valore = valore;
	}

	/**
	 * Ritorna il valore della pedina
	 * 
	 * @return {@link Integer} valore Pedina
	 */
	public int getValore() {
		return this.valore;
	}
}