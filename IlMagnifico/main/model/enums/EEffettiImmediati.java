package main.model.enums;

public enum EEffettiImmediati {

	// Periodo 1
	CAPPELLA(				"cappella", 			0,0,0,0,0,0,0,1,0, "+1FP"),
	ESATTORIA(				"esattoria",			0,0,0,0,0,5,0,0,0, "+5VP"),
	ZECCA(					"zecca",				0,0,0,0,0,5,0,0,0, "+5VP"),
	TEATRO(					"teatro",				0,0,0,0,0,6,0,0,0, "+6VP"),
	ARCO_DI_TRIONFO(		"arco di trionfo",		0,0,0,0,0,6,0,0,0, "+6VP"),
	TAGLIAPIETRE(			"tagliapietre",			0,0,0,0,0,2,0,0,0, "+2VP"),
	RESIDENZA( 				"residenza",			0,0,0,0,0,1,0,0,0, "+1VP"),
	FALEGNAMERIA(			"falegnameria",			0,0,0,0,0,3,0,0,0, "+3VP"),
	SOSTEGNO_AL_VESCOVO(	"sostegno al vescovo",	0,0,0,0,0,0,0,3,0, "+3FP"),
	CAMPAGNA_MILITARE(		"campagna militare",	0,3,0,0,0,0,0,0,0, "+3C"),
	INNALZARE_UNA_STATUA(	"innalzare una statua",	3,0,0,0,0,0,0,0,0, ""),		//2 Privilegi del consiglio diversi tra loro
	COSTRUIRE_LE_MURA1(		"costruire le mura",	0,0,0,0,0,0,2,0,0, "+2MP"),
	COSTRUIRE_LE_MURA2(		"costruire le mura",	0,0,0,0,0,0,0,0,1, "+1CP"), 
	COMBATTERE_LE_ERESIE(	"combattere le eresie",	0,0,0,0,0,0,0,2,0, "+2FP"), 
	INGAGGIARE_RECLUTE(		"ingaggiare reclute",	0,0,0,0,0,0,5,0,0, "+5MP"), 
	OSPITARE_I_MENDICANTI(	"ospitare i mendicanti",0,0,0,0,4,0,0,0,0, "+4Sv"), 
	RIPARARE_LA_CHIESA(		"riparare la chiesa",	0,0,0,0,0,0,0,1,0, "+1FP"), 
	CONTADINO(				"contadino",			-1,0,0,0,0,0,0,0,0,""), 
	CONDOTTIERO(			"condottiero",			0,0,0,0,0,0,2,0,0, "+2MP"),	//3 Punti Militari
	ARTIGIANO(				"artigiano",			-1,0,0,0,0,0,0,0,0,""), 
	DAMA(					"dama",					-1,0,0,0,0,0,0,0,0,""), 
	BADESSA1(				"badessa",				0,0,0,0,0,0,0,1,0, "+1FP"), 
	BADESSA2(				"badessa",				-1,0,0,0,0,0,0,0,0,""),		//Non implementato?
	CAVALIERE(				"cavaliere",			3,0,0,0,0,0,0,0,0, ""),		//1 Privilegio del consiglio
	PREDICATORE(			"predicatore",			0,0,0,0,0,0,0,4,0, "+4FP"),
	COSTRUTTORE(			"costruttore",			-1,0,0,0,0,0,0,0,0,""), 
	FORESTA(				"foresta",				0,0,1,0,0,0,0,0,0, "+1W"), 
	BOSCO(					"bosco",				0,0,1,0,0,0,0,0,0, "+1W"), 
	ROCCA(					"rocca",				-1,0,0,0,0,0,0,0,0,""),
	BORGO(					"borgo",				-1,0,0,0,0,0,0,0,0,""), 
	CAVA_DI_GHIAIA(			"cava di ghiaia",		0,0,0,2,0,0,0,0,0, "+2St"), 
	MONASTERO(				"monastero",			0,0,0,0,1,0,2,0,0, "+1Sv, +2MP"), 
	CITTA(					"citta",				0,3,0,0,0,0,0,0,0, "+3C"), 
	AVAMPOSTO_COMMERCIALE(	"avamposto commerciale",-1,0,0,0,0,0,0,0,0,""),
	
	//;
	// Periodo 2
	MERCATO(				"mercato",					0, 0, 0, 0, 0, 3, 0, 0, 0, "+3VP"),
	FORTEZZA(				"fortezza", 				0, 0, 0, 0, 0, 8, 0, 0, 0, "+8VP"),
	GILDA_DEI_COSTRUTTORI(	"gilda dei costruttori",	0, 0, 0, 0, 0, 4, 0, 0, 0, "+4VP"),
	BATTISTERO(			"battistero",				0, 0, 0, 0, 0, 2, 0, 1, 0, "+2VP, +1FP"),
	TESORERIA(				"tesoreria",				0, 0, 0, 0, 0, 4, 0, 0, 0, "+4VP"),
	CASERMA(				"caserma",					0, 0, 0, 0, 0, 3, 0, 0, 0, "+3VP"),
	GILDA_DEGLI_SCULTORI(	"gilda degli scultori",		0, 0, 0, 0, 0, 6, 0, 0, 0, "+6VP"),
	GILDA_DEI_PITTORI(		"gilda dei pittori",		0, 0, 0, 0, 0, 5, 0, 0, 0, "+5VP"),
	COSTRUIRE_I_BASTIONI1(	"costruire i bastioni",	0, 0, 0, 0, 0, 0, 3, 0, 0, "+3MP"),
	COSTRUIRE_I_BASTIONI2(	"costruire i bastioni",	0, 0, 0, 0, 0, 0, 0, 0, 1, "+1CP"),
	ACCOGLIERE_GLI_STRANIERI("accogliere gli stranieri",0, 0, 0, 0, 5, 0, 0, 0, 0, "+5Sv"), 
	INGAGGIARE_SOLDATI(		"ingaggiare soldati",		0, 0, 0, 0, 0, 0, 6, 0, 0, "+6MP"), 
	SUPPORTO_AL_RE1(		"supporto al re", 			0, 5, 0, 0, 0, 0, 0, 0, 0, "+5C"), 
	SUPPORTO_AL_RE2(		"supporto al re", 			0, 0, 0, 0, 0, 0, 0, 0, 1, "+1CP"),
	CROCIATA(				"crociata", 				0, 5, 0, 0, 0, 0, 0, 1, 0, "+5C, +1FP"), 
	SOSTEGNO_AL_CARDINALE(	"sostegno al cardinale", 	0, 0, 0, 0, 0, 0, 0, 3, 0, "+3FP"), 
	SCAVARE_CANALIZZAZIONI(	"scavare canalizzazioni", 	8, 0, 0, 0, 0, 0, 0, 0, 0,"Level 4 Production"),		
	RIPARARE_ABBAZIA(		"riparare abbazia", 		0, 0, 0, 0, 0, 0, 0, 2, 0, "+2FP"),
	FATTORE(				"fattore", 					-1, 0, 0, 0, 0, 0, 0, 0, 0,""),
	MESSO_REALE(			"messo reale", 				3, 0, 0, 0, 0, 0, 0, 0, 3, "+3CP"),	 
	ARCHITETTO(				"architetto", 				-1, 0, 0, 0, 0, 0, 0, 0, 0,""), 	//Non implementato?
	CAPITANO(				"capitano", 				-1, 0, 0, 0, 0, 0, 0, 0, 0,""), 	//Non implementato?
	MECENATE(				"mecenate", 				-1, 0, 0, 0, 0, 0, 0, 0, 0,""), 	//Non implementato?
	STUDIOSO(				"studioso", 				-1, 0, 0, 0, 0, 0, 0, 0, 0,""), 
	EROE(					"eroe", 					-1, 0, 0, 0, 0, 0, 0, 0, 0,""), 	//Non implementato? 
	MESSO_PAPALE(			"messo papale", 			0, 0, 0, 0, 0, 0, 0, 3, 0, "+3FP"),
	MINIERA_ORO(			"miniera oro", 				0, 1, 0, 0, 0, 0, 0, 0, 0, "+1C"),
	MANIERO(				"maniero", 					-1, 0, 0, 0, 0, 0, 0, 0, 0,""),
	EREMO(					"eremo", 					0, 0, 0, 0, 0, 0, 0, 1, 0, "+1FP"),
	VILLAGGIO_MINERARIO(	"villaggio minerario", 		0, 0, 0, 1, 2, 0, 0, 0, 0, "+1St, +2Sv"),
	VILLAGGIO_MONTANO(		"villaggio montano", 		0, 0, 0, 0, 1, 0, 0, 0, 0, "+1Sv"),
	CAVA_DI_PIETRA(			"cava di pietra", 			0, 0, 1, 0, 0, 0, 0, 0, 0, "+1W"),
	DUCATO(					"ducato", 					0, 4, 0, 0, 0, 0, 0, 0, 0, "+4C"),
	POSSEDIMENTO(			"possedimento", 			0, 0, 1, 0, 2, 0, 0, 0, 0, "+1W, +2Sv"),

	//;
	// Periodo 3
	PALAZZO(					"palazzo",					0, 0, 0, 0, 0, 9, 0, 0, 0, "+9VP"),
	BANCA(						"banca",					0, 0, 0, 0, 0, 7, 0, 0, 0, "+7VP"),
	GIARDINO(					"giardino",					0, 0, 0, 0, 0, 10, 0, 0, 0,"+10VP"),
	FIERA(						"fiera",					0, 0, 0, 0, 0, 8, 0, 0, 0, "+8VP"),
	CASTELLETTO(				"castelletto",				0, 0, 0, 0, 0, 9, 0, 0, 0, "+9VP"),
	ACCADEMIA_MILITARE(			"accademia militare",		0, 0, 0, 0, 0, 7, 0, 0, 0, "+7VP"),
	BASILICA(					"basilica",					0, 0, 0, 0, 0, 5, 0, 1, 0, "+5VP, +1FP"),
	CATTEDRALE(				"cattedrale",				0, 0, 0, 0, 0, 7, 0, 3, 0, "+7VP, +3FP"),
	CONQUISTA_MILITARE(		"conquista militare",		0, 3, 3, 3, 0, 0, 0, 0, 0, "+3C, +3W, +3St"),
	RIPARARE_LA_CATTEDRALE(		"riparare la cattedrale", 	-1, 0, 0, 0, 0, 0, 0, 0, 0,""), 	//Non implementato?
	GUERRA_SANTA(				"guerra santa",				0, 0, 0, 0, 0, 0, 0, 4, 0, "+4FP"),
	INGAGGIARE_MERCENARI(		"ingaggiare mercenari",		0, 0, 0, 0, 0, 0, 7, 0, 0, "+7MP"),
	COSTRUIRE_LE_TORRI1(		"costruire le torri",		0, 0, 0, 0, 0, 0, 4, 0, 0, "+4MP"),
	COSTRUIRE_LE_TORRI2(		"costruire le torri",		0, 0, 0, 0, 0, 0, 0, 0, 1, "+1CP"),
	COMMISSIONARE_ARTE_SACRA(	"commissionare arte sacra",	0, 0, 0, 0, 0, 0, 0, 3, 0, "+3FP"),
	SOSTEGNO_AL_PAPA(			"sostegno al papa",			0, 0, 0, 0, 0, 0, 0, 2, 0, "+2FP"),
	MIGLIORARE_LE_STRADE(		"migliorare le strade",		7, 0, 0, 0, 0, 0, 0, 0, 0,"Level 3 Production"), 	
	GOVERNATORE(				"governatore",				9, 0, 0, 0, 0, 0, 0, 0, 0,"2VPxBuilding"), 	
	CARDINALE1(					"cardinale",				6, 0, 0, 0, 0, 0, 0, 0, 0,"Level 4 Harvest"), 
	CARDINALE2(					"cardinale",				0, 0, 0, 0, 0, 0, 0, 1, 0,"+1FP"), 		
	GENERALE(					"generale",					17, 0, 0, 0, 0, 0, 0, 0, 0,"1VPx2MP"), 	
	CORTIGIANA(					"cortigiana",				10, 0, 0, 0, 0, 0, 0, 0, 0,"2VPxCharacter"), 	
	VESCOVO1(					"vescovo",					8, 0, 0, 0, 0, 0, 0, 0, 0,"Level 4 Production"), 	
	VESCOVO2(					"vescovo",					0, 0, 0, 0, 0, 0, 0, 1, 0,"+1FP"), 	
	AMBASCIATORE(				"ambasciatore",				3, 0, 0, 0, 0, 0, 0, 0, 0,"+1CP"), 	
	ARALDO(						"araldo",					11, 0, 0, 0, 0, 0, 0, 0, 0,"2VPxVenture"), 	
	NOBILE(						"nobile", 					12, 0, 0, 0, 0, 0, 0, 0, 0,"2VPxTerritory"), 		
	CITTA_MERCANTILE(			"citta mercantile", 		0, 1, 0, 0, 1, 0, 0, 0, 0, "+1C,+1Sv"),
	TENUTA(					"tenuta", 					0, 0, 1, 0, 0, 1, 0, 0, 0, "+1VP, +1W"),
	CITTA_FORTIFICATA(			"citta fortificata", 		0, 0, 0, 0, 1, 0, 2, 0, 0, "+2MP, +1Sv"),
	CASTELLO(					"castello", 				0, 2, 0, 0, 0, 2, 0, 0, 0, "+2VP, +2C"),
	SANTUARIO(					"santuario", 				0, 0, 0, 0, 0, 0, 0, 1, 0, "+1FP"),
	PROVINCIA1(					"provincia", 				3, 0, 0, 0, 0, 0, 0, 0, 1, "+1CP"),
	PROVINCIA2(					"provincia", 				0, 0, 0, 1, 0, 0, 0, 0, 0, "+1St"),
	CAVA_DI_MARMO(				"cava di marmo", 			0, 0, 0, 0, 0, 3, 0, 0, 0, "+3VP"),
	COLONIA(					"colonia", 					0, 0, 0, 0, 0, 0, 2, 0, 0, "+2MP");


	private String nome;
	private int numeroEffetto;
	private int deltaMonete;
	private int deltaLegno;
	private int deltaPietra;
	private int deltaServitori;
	private int deltaPV;
	private int deltaPM;
	private int deltaPF;
	private int numeroprivilegiConsiglio;
	private String descrizione;

	private EEffettiImmediati(String nome, int numeroEffetto, int monete, int legno, int pietra, int servitori, int PV,
			int PM, int PF, int privilegiConsiglio, String descrizione) {
		this.nome = nome;
		this.numeroEffetto = numeroEffetto;
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

	public String getNome() {
		return this.nome;
	}

	public Object[] getEffetto() {
		Object[] effetto = new Object[13];
		for (int i = 0; i < 13; i++) {
			effetto[i] = new Object();
		}
		effetto[0] = this.numeroEffetto;
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

	public String getDescrizione() {
		return this.descrizione;
	}

}
