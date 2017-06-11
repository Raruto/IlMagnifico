package main.model.enums;

public enum EEffettiImmediati {

	CAPPELLA("cappella", 0, 0, 0, 0, 0, 0, 0, 1, 0,"+1PF"),
	ESATTORIA("esattoria",0,0,0,0,0,5,0,0,0,"+5PV"),
	ZECCA("zecca",0,0,0,0,0,5,0,0,0,"+5PV"),
	TEATRO("teatro",0,0,0,0,0,6,0,0,0,"+6PV"),
	ARCO_DI_TRIONFO("arco di trionfo",0,0,0,0,0,6,0,0,0,"+6PV"),
	TAGLIAPIETRE("tagliapietre",0,0,0,0,0,2,0,0,0,"+2PV"),
	RESIDENZA( "residenza",0,0,0,0,0,1,0,0,0,"+1PV"),
	FALEGNAMERIA("falegnameria",0,0,0,0,0,3,0,0,0,"+3PV"),
	SOSTEGNO_AL_VESCOVO("sostegno al vescovo",0,0,0,0,0,0,0,3,0,"+3PF"),
	CAMPAGNA_MILITARE("campagna militare",0,3,0,0,0,0,0,0,0,"+3M"),
	INNALZARE_UNA_STATUA("innalzare una statua",3,0,0,0,0,0,0,0,0,""), 
	COSTRUIRE_LE_MURA1("costruire le mura",0,0,0,0,0,0,2,0,0,"+2PM"),
	COSTRUIRE_LE_MURA2("costruire le mura",0,0,0,0,0,0,0,0,1,"+1PDC"), 
	COMBATTERE_LE_ERESIE("combattere le eresie",0,0,0,0,0,0,0,2,0,"+2PF"), 
	INGAGGIARE_RECLUTE("ingaggiare reclute",0,0,0,0,0,0,5,0,0,"+5PM"), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti",0,0,0,0,4,0,0,0,0,"+4S"), 
	RIPARARE_LA_CHIESA("riparare la chiesa",0,0,0,0,0,0,0,1,0,"+1PF"), 
	CONTADINO("contadino",-1,0,0,0,0,0,0,0,0,""), 
	CONDOTTIERO("condottiero",0,0,0,0,0,0,2,0,0,"+2PM"), 
	ARTIGIANO("artigiano",-1,0,0,0,0,0,0,0,0,""), 
	DAMA("dama",-1,0,0,0,0,0,0,0,0,""), 
	BADESSA1("badessa",0,0,0,0,0,0,0,1,0,"+1PF"), 
	BADESSA2("badessa",-1,0,0,0,0,0,0,0,0,""),
	CAVALIERE("cavaliere",3,0,0,0,0,0,0,0,0,""), 
	PREDICATORE( "predicatore",0,0,0,0,0,0,0,4,0,"+4PF"),
	COSTRUTTORE("costruttore",-1,0,0,0,0,0,0,0,0,""), 
	FORESTA("foresta",0,0,1,0,0,0,0,0,0,"+1L"), 
	BOSCO("bosco",0,0,1,0,0,0,0,0,0,"+1L"), 
	ROCCA("rocca",-1,0,0,0,0,0,0,0,0,""),
	BORGO("borgo",-1,0,0,0,0,0,0,0,0,""), 
	CAVA_DI_GHIAIA("cava di ghiaia",0,0,0,2,0,0,0,0,0,"+2P"), 
	MONASTERO("monastero",0,0,0,0,1,0,2,0,0,"+1S, +2PM"), 
	CITTA("citta",0,3,0,0,0,0,0,0,0,"+3M"), 
	AVAMPOSTO_COMMERCIALE("avamposto commerciale", -1,0,0,0,0,0,0,0,0,"");

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
