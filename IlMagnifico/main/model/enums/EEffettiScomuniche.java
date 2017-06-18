package main.model.enums;

public enum EEffettiScomuniche {

	RICEVI_MENO_PM(				"ricevi meno pm", 			  -1, EAzioniGioco.RiceviPM, 			  0, 0, 0, 0, 0, -1, 0, 0), 
	MENO_QUATTRO_ON_TERRITORIO( "meno quattro on territorio", 18, EAzioniGioco.PrendiTerritorio, 	  0, 0, 0, 0, 0, 0, 0, 0), 
	NO_PV_PERSONAGGIO(			"no pv personaggio", 		  -1, EAzioniGioco.PersonaggiFinePartita, 0, 0, 0, 0, 0, 0, 0, 0);

	private String nome;
	private int numeroEffetto;
	private EAzioniGioco azioneAttivazione;
	private int deltaMonete;
	private int deltaLegno;
	private int deltaPietra;
	private int deltaServitori;
	private int deltaPV;
	private int deltaPM;
	private int deltaPF;
	private int numeroprivilegiConsiglio;

	private EEffettiScomuniche(String nome, int numeroEffetto, EAzioniGioco azione, int monete, int legno, int pietra,
			int servitori, int PV, int PM, int PF, int privilegiConsiglio) {
		this.nome = nome;
		this.numeroEffetto = numeroEffetto;
		this.azioneAttivazione = azione;
		this.deltaMonete = monete;
		this.deltaLegno = legno;
		this.deltaPietra = pietra;
		this.deltaServitori = servitori;
		this.deltaPV = PV;
		this.deltaPM = PM;
		this.deltaPF = PF;
		this.numeroprivilegiConsiglio = privilegiConsiglio;
	}

	public Object[] getEffetto() {
		Object[] effetto = new Object[13];
		for (int i = 0; i < 13; i++) {
			effetto[i] = new Object();
		}
		effetto[0] = this.numeroEffetto;
		effetto[2] = this.azioneAttivazione;
		effetto[5] = this.deltaMonete;
		effetto[6] = this.deltaLegno;
		effetto[7] = this.deltaPietra;
		effetto[8] = this.deltaServitori;
		effetto[9] = this.deltaPV;
		effetto[10] = this.deltaPM;
		effetto[11] = this.deltaPF;
		effetto[12] = this.numeroprivilegiConsiglio;
		return effetto;
	}

	public String getNome() {
		return this.nome;
	}
}
