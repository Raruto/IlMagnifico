package main.game.players;

/**
 * Classe per la gestione delle Risorse Giocatore
 */
public class RisorseGiocatore {
	private int legno;
	private int monete;
	private int pietre;
	private int servitori;

	public RisorseGiocatore(int legno, int monete, int pietre, int servitori) {
		this.legno = legno;
		this.monete = monete;
		this.pietre = pietre;
		this.servitori = servitori;
	}

	public int getLegno() {
		return legno;
	}

	public int getMonete() {
		return monete;
	}

	public int getPietre() {
		return pietre;
	}

	public int getServitori() {
		return servitori;
	}
	
	
}