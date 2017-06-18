package main.model.enums;

public enum EEffettiPermanenti {

	// Periodo 1
	CAPPELLA(				"cappella", 				0, EAzioniGioco.Raccolto, 		  -1,0,0,0,0,0,1,0,"-1C, +1CP"),
	ESATTORIA(				"esattoria",				15,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	ZECCA(					"zecca",					16,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	TEATRO(					"teatro",					13,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	ARCO_DI_TRIONFO(		"arco di trionfo",			14,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	TAGLIAPIETRE1(			"tagliapietre",				0,EAzioniGioco.Produzione,		  3,0,-1,0,0,0,0,0,"-1St, +3C"),
	TAGLIAPIETRE2(			"tagliapietre",				0,EAzioniGioco.Produzione,		  5,0,-2,0,0,0,0,0,"-2St, +5C"),
	RESIDENZA1(				"residenza",				0,EAzioniGioco.Produzione,		  -1,0,0,0,0,0,0,0,"-1C"),
	RESIDENZA2(				"residenza",				3,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,1, "+1CP"),
	FALEGNAMERIA1(			"falegnameria",				0,EAzioniGioco.Produzione,		  3,-1,0,0,0,0,0,0,"-1W, +3C"),
	FALEGNAMERIA2(			"falegnameria",				0,EAzioniGioco.Produzione,		  5,-2,0,0,0,0,0,0,"-2W, +5C"),
	SOSTEGNO_AL_VESCOVO(	"sostegno al vescovo",		0,EAzioniGioco.FinePartita,		  0,0,0,0,1,0,0,0, "+1VP"),
	CAMPAGNA_MILITARE(		"campagna militare",		0,EAzioniGioco.FinePartita,		  0,0,0,0,5,0,0,0, "+5VP"),
	INNALZARE_UNA_STATUA(	"innalzare una statua",		0,EAzioniGioco.FinePartita,		  0,0,0,0,4,0,0,0, "+4VP"), 
	COSTRUIRE_LE_MURA(		"costruire le mura",		0,EAzioniGioco.FinePartita,		  0,0,0,0,3,0,0,0, "+3VP"), 
	COMBATTERE_LE_ERESIE(	"combattere le eresie",		0,EAzioniGioco.FinePartita,		  0,0,0,0,5,0,0,0, "+5VP"), 
	INGAGGIARE_RECLUTE(		"ingaggiare reclute",		0,EAzioniGioco.FinePartita,		  0,0,0,0,4,0,0,0, "+4VP"), 
	OSPITARE_I_MENDICANTI(	"ospitare i mendicanti", 	0,EAzioniGioco.FinePartita,		  0,0,0,0,4,0,0,0, "+4VP"), 
	RIPARARE_LA_CHIESA(		"riparare la chiesa", 		0,EAzioniGioco.FinePartita,		  0,0,0,0,5,0,0,0, "+5VP"), 
	CONTADINO(				"contadino", 				4,EAzioniGioco.Raccolto,		  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	CONDOTTIERO(			"condottiero",				4,EAzioniGioco.PrendiTerritorio,  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	ARTIGIANO(				"artigiano",				4,EAzioniGioco.Produzione,		  0,0,0,0,0,0,0,0, ""),					//Non implementato? 
	DAMA1(					"dama",						4,EAzioniGioco.PrendiPersonaggio, 0,0,0,0,0,0,0,0, ""),					//Non implementato?
	DAMA2(					"dama",						23,EAzioniGioco.PagaPersonaggio,  0,0,0,0,0,0,0,0, ""),					//Non implementato? 
	BADESSA2(				"badessa",					-1,null,						  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	CAVALIERE(				"cavaliere",				4,EAzioniGioco.PrendiImpresa,	  0,0,0,0,0,0,0,0, ""),					//Non implementato?
	PREDICATORE(			"predicatore",				-1,EAzioniGioco.EffettoTorre,	  0,0,0,0,0,0,0,0, ""),					//Non implementato? 
	COSTRUTTORE1(			"costruttore",				4,EAzioniGioco.PrendiEdificio,	  0,0,0,0,0,0,0,0, ""),					//Non implementato? 
	COSTRUTTORE2(			"costruttore",				24,EAzioniGioco.PagaEdificio,	  0,0,0,0,0,0,0,0, ""),					//Non implementato? 
	FORESTA(				"foresta",					0,EAzioniGioco.Raccolto,		  0,3,0,0,0,0,0,0, "+3W"), 
	BOSCO(					"bosco",					0,EAzioniGioco.Raccolto,		  0,1,0,0,0,0,0,0, "+1W"), 
	ROCCA(					"rocca",					0,EAzioniGioco.Raccolto,		  0,0,1,0,0,2,0,0, "+1St, +2MP"), 
	BORGO(					"borgo",					0,EAzioniGioco.Raccolto,		  1,0,0,1,0,0,0,0, "+1C, +1Sv"), 
	CAVA_DI_GHIAIA(			"cava di ghiaia",			0,EAzioniGioco.Raccolto,		  0,0,2,0,0,0,0,0, "+2W"), 
	MONASTERO(				"monastero",				0,EAzioniGioco.Raccolto,		  0,0,1,0,0,0,1,0, "+1St, +1FP"), 
	CITTA(					"citta",					3,EAzioniGioco.Raccolto,		  0,0,0,0,0,0,0,0, ""),					/*Non implementato?*/ 
	AVAMPOSTO_COMMERCIALE(	"avamposto commerciale",	0,EAzioniGioco.Raccolto,		  1,0,0,0,0,0,0,0,"+1C");/*,
	
	//;
	// Periodo 2
	MERCATO(				 "mercato", 				0, EAzioniGioco.Produzione, -3, 2, 2, 0, 0, 0, 0, 0, "-3C, +2W, +2St"),
	FORTEZZA(				 "fortezza", 				0, EAzioniGioco.Produzione, 0, 0, 0, 0, 2, 2, 0, 0, "+2MP, +2VP"),
	GILDA_DEI_COSTRUTTORI(	 "gilda dei costruttori", 	0, EAzioniGioco.Produzione, 0, -1, -1, -1, 6, 0, 0, 0, "-1W, -1St, -1Sv, +6VP"),
	BATTISTERO(				 "battistero", 				0, EAzioniGioco.Produzione, 2, 0, 0, 0, 2, 0, -1, 0, "-1FP, +2C, +2VP"),
	TESORERIA1(				 "tesoreria", 				0, EAzioniGioco.Produzione, -1, 0, 0, 0, 3, 0, 0, 0, "-1C, +3VP"),
	TESORERIA2(				 "tesoreria", 				0, EAzioniGioco.Produzione, -2, 0, 0, 0, 5, 0, 0, 0, "-2C, +5VP"),
	CASERMA(				 "caserma", 				0, EAzioniGioco.Produzione, 0, 0, 0, -1, 0, 3, 0, 0, "-1Sv, +3MP"),
	GILDA_DEGLI_SCULTORI1(	 "gilda degli scultori", 	0, EAzioniGioco.Produzione, 0, 0, -1, 0, 3, 0, 0, 0, "-1St, +3VP"),
	GILDA_DEGLI_SCULTORI2(	 "gilda degli scultori", 	0, EAzioniGioco.Produzione, 0, 0, -3, 0, 7, 0, 0, 0, "-3St, +7VP"),
	GILDA_DEI_PITTORI1(		 "gilda dei pittori", 		0, EAzioniGioco.Produzione, 0, -1, 0, 0, 3, 0, 0, 0, "-1W, +3VP"),
	GILDA_DEI_PITTORI2(		 "gilda dei pittori", 		0, EAzioniGioco.Produzione, 0, -3, 0, 0, 7, 0, 0, 0, "-3W, +7VP"),
	COSTRUIRE_I_BASTIONI(	 "costrutire i bastioni", 	0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 2, 0, 0, 0, "+2VP"),
	ACCOGLIERE_GLI_STRANIERI("accogliere gli stranieri",0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 4, 0, 0, 0, "+4VP"), 
	INGAGGIARE_SOLDATI(		 "ingaggiare soldati", 		0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 5, 0, 0, 0, "+5VP"), 
	SUPPORTO_AL_RE(			 "supporto al re", 			0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 3, 0, 0, 0, "+3VP"), 
	CROCIATA(				 "crociata", 				0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 5, 0, 0, 0, "+5VP"), 
	SOSTEGNO_AL_CARDINALE(	 "sostegno al cardinale", 	0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 4, 0, 0, 0, "+4VP"), 
	SCAVARE_CANALIZZAZIONI(	 "scavare canalizzazioni", 	0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 5, 0, 0, 0, "+5VP"), 
	RIPARARE_ABBAZIA(		 "riparare abbazia", 		0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 6, 0, 0, 0, "+6VP"),
	FATTORE(				 "fattore", 				5, EAzioniGioco.Raccolto, 0, 0, 0, 0, 0, 0, 0, 0, "+3 on Production"),
	MESSO_REALE(			 "messo reale", 			-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""), 
	ARCHITETTO(				 "architetto", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),	
	CAPITANO(				 "capitano", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	MECENATE(				 "mecenate", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""), 
	STUDIOSO(				 "studioso", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""), 
	EROE(					 "eroe", 					-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""), 
	MESSO_PAPALE(			 "messo papale", 			-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	MINIERA_ORO(			 "miniera oro", 			0, EAzioniGioco.Raccolto, 2, 0, 0, 0, 0, 0, 0, 0, "+2C"),
	MANIERO(				 "maniero", 				0, EAzioniGioco.Raccolto, 0, 0, 0, 2, 0, 2, 0, 0, "+2Sv, +2MP"),
	EREMO(					 "eremo", 					0, EAzioniGioco.Raccolto, 0, 0, 0, 0, 0, 0, 1, 0, "+1FP"),
	VILLAGGIO_MINERARIO(	 "villaggio minerario", 	0, EAzioniGioco.Raccolto, 0, 0, 2, 1, 0, 0, 0, 0, "+2St, +1Sv"),
	VILLAGGIO_MONTANO(		 "villaggio montano", 		0, EAzioniGioco.Raccolto, 0, 2, 0, 0, 0, 1, 0, 0, "+2W, +1MP"),
	CAVA_DI_PIETRA(			 "cava di pietra", 			0, EAzioniGioco.Raccolto, 0, 0, 3, 0, 0, 0, 0, 0, "+3W"),
	DUCATO(					 "ducato", 					0, EAzioniGioco.Raccolto, 1, 2, 1, 0, 0, 0, 0, 0, "+1C, +2W, +1St"),
	POSSEDIMENTO(			 "possedimento", 			0, EAzioniGioco.Raccolto, 1, 2, 0, 0, 0, 0, 0, 0, "+1C, +2W"),

	//;
	// Periodo 3
	PALAZZO(				 "palazzo", 				0, EAzioniGioco.Produzione, -1, 0, 0, 2, 4, 0, 0, 0, "-1C, +2Sv, +4VP"),
	BANCA(					 "banca", 					0, EAzioniGioco.Produzione, 5, 0, 0, 0, 0, 0, 0, 0, "+5C"),
	GIARDINO(				 "giardino", 				0, EAzioniGioco.Produzione, 0, 0, 0, 0, 3, 0, 0, 0, "+3VP"),
	FIERA(					 "fiera", 					0, EAzioniGioco.Produzione, -4, 3, 3, 0, 0, 0, 0, 0, "-4C, +3W, +3St"),
	CASTELLETTO1(			 "castelletto", 			0, EAzioniGioco.Produzione, 0, 0, 0, 0, 2, 0, 0, 0, "+2VP"),
	CASTELLETTO2(			 "castelletto", 			3, EAzioniGioco.Produzione, 0, 0, 0, 0, 0, 0, 0, 1, "+1CP"),
	ACCADEMIA_MILITARE(		 "accademia militare", 		0, EAzioniGioco.Produzione, 0, 0, 0, -1, 1, 3, 0, 0, "-1Sv, +1VP, +3MP"),
	BASILICA(				 "basilica", 				0, EAzioniGioco.Produzione, 0, -1, 0, 0, 0, 0, 2, 0, "-1W, +2FP"),
	BASILICA(				 "basilica", 				0, EAzioniGioco.Produzione, 0, 0, -1, 0, 0, 0, 2, 0, "-1St, +2FP"),
	CATTEDRALE(				 "cattedrale",				0, EAzioniGioco.Produzione, 0, 0, 0, 0, 1, 0, 0, 0, "+1VP"),
	CONQUISTA_MILITARE(		 "conquista militare", 		0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 7, 0, 0, 0, "+7VP"),
	RIPARARE_LA_CATTEDRALE(	 "riparare la cattedrale", 	0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 5, 0, 0, 0, "+5VP"),
	GUERRA_SANTA(			 "guerra santa", 			0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 8, 0, 0, 0, "+8VP"),
	INGAGGIARE_MERCENARI(	 "ingaggiare mercenari", 	0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 6, 0, 0, 0, "+6VP"),
	COSTRUIRE_LE_TORRI(		 "costruire le torri", 		0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 4, 0, 0, 0, "+4VP"),
	COMMISSIONARE_ARTE_SACRA("commissionare arte sacra",0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 3, 0, 0, 0, "+3VP"),
	SOSTEGNO_AL_PAPA(		 "sostegno al papa", 		0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 10, 0, 0, 0, "+10VP"),
	MIGLIORARE_LE_STRADE(	 "migliorare le strade", 	0, EAzioniGioco.FinePartita, 0, 0, 0, 0, 5, 0, 0, 0, "+5VP"),	
	GOVERNATORE(			 "governatore", 			-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	CARDINALE(				 "cardinale", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	GENERALE(				 "generale", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	CORTIGIANA(				 "cortigiana", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	VESCOVO(				 "vescovo", 				-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	AMBASCIATORE(			 "ambasciatore", 			-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	ARALDO(					 "araldo", 					-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),
	NOBILE(					 "nobile", 					-1, null, 0, 0, 0, 0, 0, 0, 0, 0, ""),	
	CITTA_MERCANTILE(		 "citta mercantile", 		0, EAzioniGioco.Raccolto, 3, 0, 0, 0, 0, 0, 0, 0, "+3C"),
	TENUTA(					 "tenuta", 					0, EAzioniGioco.Raccolto, 0, 2, 0, 0, 2, 0, 0, 0, "+2W, +2C"),
	CITTA_FORTIFICATA(		 "citta fortificata", 		0, EAzioniGioco.Raccolto, 0, 0, 0, 2, 0, 1, 0, 0, "+2Sv, +1MP"),
	CASTELLO(				 "castello", 				0, EAzioniGioco.Raccolto, 0, 0, 0, 1, 0, 3, 0, 0, "+1Sv, +3MP"),
	SANTUARIO(				 "santuario", 				0, EAzioniGioco.Raccolto, 1, 0, 0, 0, 0, 0, 1, 0, "+1C, +1FP"),
	PROVINCIA(				 "provincia", 				0, EAzioniGioco.Raccolto, 0, 0, 1, 0, 4, 0, 0, 0, "+1St, +4VP"),
	CAVA_DI_MARMO(			 "cava di marmo", 			0, EAzioniGioco.Raccolto, 0, 0, 2, 0, 1, 0, 0, 0, "+2St, +1VP"),
	COLONIA(				 "colonia", 				0, EAzioniGioco.Raccolto, 0, 1, 0, 0, 4, 0, 0, 0, "+1W, +4VP");
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
