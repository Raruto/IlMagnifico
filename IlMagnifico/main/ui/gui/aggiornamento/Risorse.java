package main.ui.gui.aggiornamento;

public class Risorse {
	
	private int monete;
	private int legno;
	private int pietre;
	private int servitori;
	
	public Risorse(int monete, int legno, int pietre, int servitori){
		this.monete = monete;
		this.legno = legno;
		this.pietre = pietre;
		this.servitori = servitori;
	}

	public int getMonete() {
		return monete;
	}

	public int getLegno() {
		return legno;
	}

	public int getPietre() {
		return pietre;
	}

	public int getServitori() {
		return servitori;
	}
	
}
