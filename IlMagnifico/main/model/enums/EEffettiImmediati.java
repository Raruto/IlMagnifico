package main.model.enums;

public enum EEffettiImmediati {

	CAPPELLA("cappella", 0, 0, 0, 0, 0, 0, 0, 1, 0),
	ESATTORIA("esattoria",0,0,0,0,0,5,0,0,0),
	ZECCA("zecca",0,0,0,0,0,5,0,0,0),
	TEATRO("teatro",0,0,0,0,0,6,0,0,0),
	ARCO_DI_TRIONFO("arco di trionfo",0,0,0,0,0,6,0,0,0),
	TAGLIAPIETRE("tagliapietre",0,0,0,0,0,2,0,0,0),
	RESIDENZA( "residenza",0,0,0,0,0,1,0,0,0),
	FALEGNAMERIA("falegnameria",0,0,0,0,0,3,0,0,0),
	SOSTEGNO_AL_VESCOVO("sostegno al vescovo",0,0,0,0,0,0,0,3,0),
	CAMPAGNA_MILITARE("campagna militare",0,3,0,0,0,0,0,0,0),
	INNALZARE_UNA_STATUA("innalzare una statua",3,0,0,0,0,0,0,0,0), 
	COSTRUIRE_LE_MURA1("costruire le mura",0,0,0,0,0,0,2,0,0),
	COSTRUIRE_LE_MURA2("costruire le mura",0,0,0,0,0,0,0,0,1), 
	COMBATTERE_LE_ERESIE("combattere le eresie",0,0,0,0,0,0,0,2,0), 
	INGAGGIARE_RECLUTE("ingaggiare reclute",0,0,0,0,0,0,5,0,0), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti",0,0,0,0,4,0,0,0,0), 
	RIPARARE_LA_CHIESA("riparare la chiesa",0,0,0,0,0,0,0,1,0), 
	CONTADINO("contadino",-1,0,0,0,0,0,0,0,0), 
	CONDOTTIERO("condottiero",0,0,0,0,0,0,2,0,0), 
	ARTIGIANO("artigiano",-1,0,0,0,0,0,0,0,0), 
	DAMA("dama",-1,0,0,0,0,0,0,0,0), 
	BADESSA1("badessa",0,0,0,0,0,0,0,1,0), 
	BADESSA2("badessa",24,0,0,0,0,0,0,0,0),
	CAVALIERE("cavaliere",3,0,0,0,0,0,0,0,0), 
	PREDICATORE( "predicatore",0,0,0,0,0,0,0,4,0),
	COSTRUTTORE("costruttore",-1,0,0,0,0,0,0,0,0), 
	FORESTA("foresta",0,0,1,0,0,0,0,0,0), 
	BOSCO("bosco",0,0,1,0,0,0,0,0,0), 
	ROCCA("rocca",-1,0,0,0,0,0,0,0,0),
	BORGO("borgo",-1,0,0,0,0,0,0,0,0), 
	CAVA_DI_GHIAIA("cava di ghiaia",0,0,0,2,0,0,0,0,0), 
	MONASTERO("monastero",0,0,0,0,1,0,2,0,0), 
	CITTA("citta",0,3,0,0,0,0,0,0,0), 
	AVAMPOSTO_COMMERCIALE("avamposto commerciale", -1,0,0,0,0,0,0,0,0);




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

	private EEffettiImmediati(String nome, int numeroEffetto, int monete, int legno, int pietra, int servitori, int PV,
			int PM, int PF, int privilegiConsiglio) {
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
}
