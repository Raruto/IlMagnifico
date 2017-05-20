package main.game.activatables;

import main.game.players.Giocatore;

/**
 * Elenco dei vari tipi di effetti
 */
public enum Effetti {
	AUMENTA_LEGNA_DI_UNO("aumenta_legna_uno", "nome_effetto_alternativo", 1, 0, 0, 0, 0, 0, 0);
	private String nomeEffettoPrincipale;
	private String nomeEffettoAlternativo;
	private int deltaLegna;
	private int deltaPietra;
	private int deltaServitori;
	private int deltaMonete;
	private int deltaPV;
	private int deltaPM;
	private int deltaPF;

	private Effetti(String nome, String effettoAlt, int legna, int pietra, int servitori, int monete, int pv, int pm,
			int pf) {
		this.nomeEffettoPrincipale = nome;
		this.nomeEffettoAlternativo = effettoAlt;
		this.deltaLegna = legna;
		this.deltaPietra = pietra;
		this.deltaServitori = servitori;
		this.deltaMonete = monete;
		this.deltaPV = pv;
		this.deltaPM = pm;
		this.deltaPF = pf;
	}

	public void attiva(Giocatore giocatore) {
		if (this.nomeEffettoPrincipale.equals("aumenta_legna_uno"))
			aumentaLegnaUno(giocatore);
	}

	public void aumentaLegnaUno(Giocatore giocatore) {

	}

	public String getEffettoPrincipale() {
		return this.nomeEffettoPrincipale;
	}

	public String getEffettoAlternativo() {
		return this.nomeEffettoAlternativo;
	}

}