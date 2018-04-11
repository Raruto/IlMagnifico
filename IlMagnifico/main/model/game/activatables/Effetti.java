package main.model.game.activatables;

import main.model.game.players.Giocatore;

/**
 * Elenco dei vari tipi di effetti
 */
public enum Effetti {
	AUMENTA_LEGNA_DI_UNO("aumenta_legna_uno", "aumenta_legna_uno", 1, 0, 0, 0, 0, 0, 0, false, 1);
	private String categoriaEffetto;
	private String nomeEffetto;
	private int deltaLegna;
	private int deltaMonete;
	private int deltaPietra;
	private int deltaServitori;
	private int deltaPuntiFede;
	private int deltaPuntiMilitari;
	private int deltaPuntiVittoria;

	// indica se � possibile attivare l'effetto insieme ad altri dello stesso
	// tipo (es: PRIVILEGIO_DEL_CONSIGLIO_1&2 � un effetto che permetta di
	// scegliere tra PRIVILEGIO_DEL_CONSIGLIO_1 e/o PRIVILEGIO_DEL_CONSIGLIO_2
	// se cumulabile = true --> AND (posso attivarli entrambi!)
	// se cumulabile = false --> XOR (posso attivarne solo uno!)
	private boolean cumulabile;

	// se cumulabile = false --> XOR (NON posso attivarli entrambi!)
	// visto che salviamo effetti composti in un array di effetti bisogna potere
	// discriminare su quale dei due attivare 
	// se sottoGruppo=1 --> PRIVILEGIO_DEL_CONSIGLIO_1
	// se sottoGruppo=2 --> PRIVILEGIO_DEL_CONSIGLIO_2
	private int sottoGruppo;

	private Effetti(String categoriaEffetto, String nomeEffetto, int legna, int monete, int pietra, int servitori,
			int puntiFede, int puntiMilitari, int puntiVittoria, boolean cumulabile, int sottoGruppo) {
		this.categoriaEffetto = categoriaEffetto;
		this.nomeEffetto = nomeEffetto;
		this.deltaLegna = legna;
		this.deltaPietra = pietra;
		this.deltaServitori = servitori;
		this.deltaMonete = monete;
		this.deltaPuntiVittoria = puntiVittoria;
		this.deltaPuntiMilitari = puntiMilitari;
		this.deltaPuntiFede = puntiFede;

		this.cumulabile = cumulabile;
		this.sottoGruppo = sottoGruppo;
	}

	public void attiva(Giocatore giocatore) {
		if (this.categoriaEffetto.equals("aumenta_legna_uno"))
			aumentaLegnaUno(giocatore);
	}

	public void aumentaLegnaUno(Giocatore giocatore) {

	}

	public String getCategoriaEffetto() {
		return this.categoriaEffetto;
	}

	public String getNomeEffetto() {
		return this.nomeEffetto;
	}

	public int getDeltaLegna() {
		return deltaLegna;
	}

	public int getDeltaPietra() {
		return deltaPietra;
	}

	public int getDeltaServitori() {
		return deltaServitori;
	}

	public int getDeltaMonete() {
		return deltaMonete;
	}

	public int getDeltaPV() {
		return deltaPuntiVittoria;
	}

	public int getDeltaPM() {
		return deltaPuntiMilitari;
	}

	public int getDeltaPF() {
		return deltaPuntiFede;
	}

	public boolean isCumulabile() {
		return cumulabile;
	}

	public int getSottoGruppo() {
		return sottoGruppo;
	}

}