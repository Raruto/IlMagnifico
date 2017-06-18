package main.model.enums;

public enum EEffettiScomuniche {

	//Prima fila
	RICEVI_MENO_PM(				   "Receive less MP", 			 -1, EAzioniGioco.RiceviPM, 			  0, 0, 0, 0, 0, -1, 0, 0), 
	MENO_QUATTRO_ON_TERRITORIO(    "-4 on Territory", 			 18, EAzioniGioco.PrendiTerritorio, 	  0, 0, 0, 0, 0, 0, 0, 0), 
	NO_PV_PERSONAGGIO(			   "No Charachter VP", 		     -1, EAzioniGioco.PersonaggiFinePartita, 0, 0, 0, 0, 0, 0, 0, 0);/*,
	
	//;
	//Seconda fila
	RICEVI_MENO_MONETE(			   "Receive less C", 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	MENO_QUATTRO_ON_EDIFICIO(	   "-4 on Building", 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	NO_PV_IMPRESA(				   "No Venture VP", 	 	 	 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	
	//;
	//Terza fila
	RICEVI_MENO_SERVITORI(		   "Receive less Sv", 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	MENO_QUATTRO_ON_PERSONAGGIO(   "-4 on Charachter",	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	NO_PV_TERRITORIO(			   "No Territory VP", 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	
	//;
	//Quarta fila
	RICEVI_MENO_LEGNA_PIETRA(	   "Receive less W, St",	 	 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	MENO_QUATTRO_ON_IMPRESA(	   "-4 on Venture",	 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	PV_MENO_CINQUE_PV(			   "VP-5 foreach VP",	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	
	//;
	//Quinta fila
	MENO_TRE_ON_RACCOLTO(		   "-3 on Harvest",	 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	NO_MERCATO(					   "No Market",	 	 	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	PV_MENO_UNO_PM(				   "VP-1 foreach MP",	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),

	//;
	//Sesta fila
	MENO_TRE_ON_PRODUZIONE(		   "-3 on Production",	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	DIVISO_DUE_ON_INCREMENTO( 	   "Sv/2 on Increment", 	 	 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	PV_MENO_UNO_LEGNO_PIETRA_CARTE("VP-1 foreach W, St cards",	 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	
	//;
	//Settima fila
	MENO_UNO_ON_FAMIGLARI(		   "-1 on Familiars",	 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	SALTA_PRIMA_AZIONE_TURNO( 	   "Skip the first move", 		 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio),
	PV_MENO_UNO_RISERVA(	  	   "VP-1 foreach W, St, Sv, C",	 numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio);
	*/
	
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
