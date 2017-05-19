package main.game.res;

/**
 * 
 */
public class RisorseGiocatore {
	private int legno;
	private int denaro;
	private int pietre;
	private int servitori;

	public RisorseGiocatore(int legno, int denaro, int pietre, int servitori) {
		this.legno = legno;
		this.denaro = denaro;
		this.pietre = pietre;
		this.servitori = servitori;
	}

	public int getLegno() {
		return legno;
	}

	public int getDenaro() {
		return denaro;
	}

	public int getPietre() {
		return pietre;
	}

	public int getServitori() {
		return servitori;
	}
	
	
}