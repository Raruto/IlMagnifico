package main.game.activatable;

import main.game.players.Giocatore;

/**
 * 
 */
public enum Azioni {
	ESEMPIO("nome_azione", 0, 0, 0, 0);
	private String nome;
	private int deltaLegna;
	private int deltaPietra;
	private int deltaServitori;
	private int deltaMonete;

	private Azioni(String nome, int legna, int pietra, int servitori, int monete) {
		this.nome = nome;
		this.deltaLegna = legna;
		this.deltaPietra = pietra;
		this.deltaServitori = servitori;
		this.deltaMonete = monete;
	}

	public void attiva(Giocatore giocatore) {
		// guardo il nome dell'azione per capire cosa devo fare
	}
}