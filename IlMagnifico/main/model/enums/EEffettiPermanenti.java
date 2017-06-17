package main.model.enums;

public enum EEffettiPermanenti {

	// Periodo 1
	CAPPELLA(				"cappella", 				0, EAzioniGioco.Raccolto, 		  -1, 0, 0, 0, 0, 0, 1, 0,"-1C, +1CP"),
	ESATTORIA(				"esattoria",				15,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	ZECCA(					"zecca",					16,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	TEATRO(					"teatro",					13,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	ARCO_DI_TRIONFO(		"arco di trionfo",			14,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	TAGLIAPIETRE1(			"tagliapietre",				0,EAzioniGioco.Produzione,		  3,0,-1,0,0,0,0,0,"-1St, +3C"),
	TAGLIAPIETRE2(			"tagliapietre",				0,EAzioniGioco.Produzione,		  5,0,-2,0,0,0,0,0,"-2St, +5C"),
	RESIDENZA1(				"residenza",				0,EAzioniGioco.Produzione,		  -1,0,0,0,0,0,0,0,"-1C"),
	RESIDENZA2(				"residenza",				3,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,1,"+1CP"),
	FALEGNAMERIA1(			"falegnameria",				0,EAzioniGioco.Produzione,		  3,-1,0,0,0,0,0,0,"-1W, +3C"),
	FALEGNAMERIA2(			"falegnameria",				0,EAzioniGioco.Produzione,		  5,-2,0,0,0,0,0,0,"-2W, +5C"),
	SOSTEGNO_AL_VESCOVO(	"sostegno al vescovo",		0,EAzioniGioco.FinePartita,		  0,0,0,0,1,0,0,0,"+1VP"),
	CAMPAGNA_MILITARE(		"campagna militare",		0,EAzioniGioco.FinePartita,		  0,0,0,0,5,0,0,0,"+5VP"),
	INNALZARE_UNA_STATUA(	"innalzare una statua",		0,EAzioniGioco.FinePartita,		  0,0,0,0,4,0,0,0,"+4VP"), 
	COSTRUIRE_LE_MURA(		"costruire le mura",		0,EAzioniGioco.FinePartita,		  0,0,0,0,3,0,0,0,"+3VP"), 
	COMBATTERE_LE_ERESIE(	"combattere le eresie",		0,EAzioniGioco.FinePartita,		  0,0,0,0,5,0,0,0,"+5VP"), 
	INGAGGIARE_RECLUTE(		"ingaggiare reclute",		0,EAzioniGioco.FinePartita,		  0,0,0,0,4,0,0,0,"+4VP"), 
	OSPITARE_I_MENDICANTI(	"ospitare i mendicanti", 	0,EAzioniGioco.FinePartita,		  0,0,0,0,4,0,0,0,"+4VP"), 
	RIPARARE_LA_CHIESA(		"riparare la chiesa", 		0,EAzioniGioco.FinePartita,		  0,0,0,0,5,0,0,0,"+5VP"), 
	CONTADINO(				"contadino", 				4,EAzioniGioco.Raccolto,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	CONDOTTIERO(			"condottiero",				4,EAzioniGioco.PrendiTerritorio,  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	ARTIGIANO(				"artigiano",				4,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/ 
	DAMA1(					"dama",						4,EAzioniGioco.PrendiPersonaggio, 0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	DAMA2(					"dama",						23,EAzioniGioco.PagaPersonaggio,  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/ 
	BADESSA2(				"badessa",					-1,null,						  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	CAVALIERE(				"cavaliere",				4,EAzioniGioco.PrendiImpresa,	  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/
	PREDICATORE(			"predicatore",				-1,EAzioniGioco.EffettoTorre,	  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/ 
	COSTRUTTORE1(			"costruttore",				4,EAzioniGioco.PrendiEdificio,	  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/ 
	COSTRUTTORE2(			"costruttore",				24,EAzioniGioco.PagaEdificio,	  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/ 
	FORESTA(				"foresta",					0,EAzioniGioco.Raccolto,		  0,3,0,0,0,0,0,0,"+3W"), 
	BOSCO(					"bosco",					0,EAzioniGioco.Raccolto,		  0,1,0,0,0,0,0,0,"+1W"), 
	ROCCA(					"rocca",					0,EAzioniGioco.Raccolto,		  0,0,1,0,0,2,0,0,"+1St, +2MP"), 
	BORGO(					"borgo",					0,EAzioniGioco.Raccolto,		  1,0,0,1,0,0,0,0,"+1C, +1Sv"), 
	CAVA_DI_GHIAIA(			"cava di ghiaia",			0,EAzioniGioco.Raccolto,		  0,0,2,0,0,0,0,0,"+2W"), 
	MONASTERO(				"monastero",				0,EAzioniGioco.Raccolto,		  0,0,1,0,0,0,1,0,"+1St, +1FP"), 
	CITTA(					"citta",					3,EAzioniGioco.Raccolto,		  0,0,0,0,0,0,0,0,""), 					/*Non implementato?*/ 
	AVAMPOSTO_COMMERCIALE(	"avamposto commerciale",	0,EAzioniGioco.Raccolto,		  1,0,0,0,0,0,0,0,"+1C"),
	
	//;
	// Periodo 2
	MERCATO(				 "mercato", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	FORTEZZA(				 "fortezza", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	GILDA_DEI_COSTRUTTORI(	 "gilda dei costruttori", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	BATTISTERO(				 "battistero", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	TESORERIA(				 "tesoreria", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CASERMA(				 "caserma", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	GILDA_DEGLI_SCULTORI(	 "gilda degli scultori", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	GILDA_DEI_PITTORI(		 "gilda dei pittori", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	COSTRUIRE_I_BASTIONI(	 "costrutire i bastioni", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	ACCOGLIERE_GLI_STRANIERI("accogliere gli stranieri",numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	INGAGGIARE_SOLDATI(		 "ingaggiare soldati", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	SUPPORTO_AL_RE(			 "supporto al re", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	CROCIATA(				 "crociata", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	SOSTEGNO_AL_CARDINALE1(	 "sostegno al cardinale", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	SOSTEGNO_AL_CARDINALE2(	 "sostegno al cardinale", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	SCAVARE_CANALIZZAZIONI(	 "scavare canalizzazioni", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	RIPARARE_ABBAZIA(		 "riparare abbazia", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	FATTORE(				 "fattore", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	MESSO_REALE(			 "messo reale", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	ARCHITETTO(				 "architetto", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),	
	CAPITANO(				 "capitano", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	MECENATE(				 "mecenate", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	STUDIOSO(				 "studioso", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	EROE(					 "eroe", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"), 
	MESSO_PAPALE(			 "messo papale", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	MINIERA_ORO(			 "miniera oro", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	MANIERO(				 "maniero", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	EREMO(					 "eremo", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	VILLAGGIO_MINERARIO(	 "villaggio minerario", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	VILLAGGIO_MONTANO(		 "villaggio montano", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CAVA_DI_PIETRA(			 "cava di pietra", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	DUCATO(					 "ducato", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	POSSEDIMENTO(			 "possedimento", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),

	//;
	// Periodo 3
	PALAZZO(				 "palazzo", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	BANCA(					 "banca", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	GIARDINO(				 "giardino", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	FIERA(					 "fiera", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CASTELLETTO(			 "castelletto", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	ACCADEMIA_MILITARE(		 "accademia militare", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	BASILICA(				 "basilica", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CATTEDRALE(				 "cattedrale",				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CONQUISTA_MILITARE(		 "conquista militare", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	RIPARARE_LA_CATTEDRALE(	 "riparare la cattedrale", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	GUERRA_SANTA(			 "guerra santa", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	INGAGGIARE_MERCENARI(	 "ingaggiare mercenari", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	COSTRUIRE_LE_TORRI(		 "costruire le torri", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	COMMISSIONARE_ARTE_SACRA("commissionare arte sacra",numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	SOSTEGNO_AL_PAPA1(		 "sostegno al papa", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	SOSTEGNO_AL_PAPA2(		 "sostegno al papa", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	MIGLIORARE_LE_STRADE(	 "migliorare le strade", 	numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),	
	GOVERNATORE(			 "governatore", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CARDINALE(				 "cardinale", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	GENERALE(				 "generale", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CORTIGIANA(				 "cortigiana", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	VESCOVO(				 "vescovo", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	AMBASCIATORE(			 "ambasciatore", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	ARALDO(					 "araldo", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	NOBILE(					 "nobile", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),	
	CITTA_MERCANTILE(		 "citta mercantile", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	TENUTA(					 "tenuta", 					numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CITTA_FORTIFICATA(		 "citta fortificata", 		numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CASTELLO(				 "castello", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	SANTUARIO(				 "santuario", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	PROVINCIA(				 "provincia", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	CAVA_DI_MARMO(			 "cava di marmo", 			numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione"),
	COLONIA(				 "colonia", 				numeroEffetto, EAzioniGioco, monete, legno, pietra, servitori, PV, PM, PF, privilegiConsiglio, "descrizione");

	
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
	private String descrizione;

	private EEffettiPermanenti(String nome, int numeroEffetto, EAzioniGioco azione, int monete, int legno,
			int pietra, int servitori, int PV, int PM, int PF, int privilegiConsiglio, String descrizione) {
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
		this.descrizione = descrizione;
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
	
	public String getDescrizione() {
		return this.descrizione;
	}

}
