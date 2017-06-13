package main.model.enums;

public enum EEffettiPermanenti {

	CAPPELLA("cappella", 0, EAzioniGioco.Raccolto, -1, 0, 0, 0, 0, 0, 1, 0,"-1C, +1CP"),
	ESATTORIA("esattoria",15,EAzioniGioco.Produzione,0,0,0,0,0,0,0,0,""),
	ZECCA("zecca",16,EAzioniGioco.Produzione,0,0,0,0,0,0,0,0,""),
	TEATRO("teatro",13,EAzioniGioco.Produzione,0,0,0,0,0,0,0,0,""),
	ARCO_DI_TRIONFO("arco di trionfo",14,EAzioniGioco.Produzione,0,0,0,0,0,0,0,0,""),
	TAGLIAPIETRE1("tagliapietre",0,EAzioniGioco.Produzione,3,0,-1,0,0,0,0,0,"+3C, -1St"),
	TAGLIAPIETRE2("tagliapietre",0,EAzioniGioco.Produzione,5,0,-2,0,0,0,0,0,"+5C, -2St"),
	RESIDENZA1( "residenza",0,EAzioniGioco.Produzione,-1,0,0,0,0,0,0,0,"-1C"),
	RESIDENZA2( "residenza",3,EAzioniGioco.Produzione,0,0,0,0,0,0,0,1,"+1CP"),
	FALEGNAMERIA1("falegnameria",0,EAzioniGioco.Produzione,3,-1,0,0,0,0,0,0,"+3C, -1W"),
	FALEGNAMERIA2("falegnameria",0,EAzioniGioco.Produzione,5,-2,0,0,0,0,0,0,"+5C, -2W"),
	SOSTEGNO_AL_VESCOVO("sostegno al vescovo",0,EAzioniGioco.FinePartita,0,0,0,0,1,0,0,0,"+1VP"),
	CAMPAGNA_MILITARE("campagna militare",0,EAzioniGioco.FinePartita,0,0,0,0,5,0,0,0,"+5VP"),
	INNALZARE_UNA_STATUA("innalzare una statua",0,EAzioniGioco.FinePartita,0,0,0,0,4,0,0,0,"+4VP"), 
	COSTRUIRE_LE_MURA("costruire le mura",0,EAzioniGioco.FinePartita,0,0,0,0,3,0,0,0,"+3VP"), 
	COMBATTERE_LE_ERESIE("combattere le eresie",0,EAzioniGioco.FinePartita,0,0,0,0,5,0,0,0,"+5VP"), 
	INGAGGIARE_RECLUTE("ingaggiare reclute",0,EAzioniGioco.FinePartita,0,0,0,0,4,0,0,0,"+4VP"), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti", 0,EAzioniGioco.FinePartita,0,0,0,0,4,0,0,0,"+4VP"), 
	RIPARARE_LA_CHIESA("riparare la chiesa", 0,EAzioniGioco.FinePartita,0,0,0,0,5,0,0,0,"+5VP"), 
	CONTADINO("contadino", 4,EAzioniGioco.Raccolto,0,0,0,0,0,0,0,0,""), 
	CONDOTTIERO("condottiero",4,EAzioniGioco.PrendiTerritorio,0,0,0,0,0,0,0,0,""), 
	ARTIGIANO("artigiano",4,EAzioniGioco.Produzione,0,0,0,0,0,0,0,0,""), 
	DAMA1("dama",4,EAzioniGioco.PrendiPersonaggio,0,0,0,0,0,0,0,0,""),
	DAMA2("dama",23,EAzioniGioco.PagaPersonaggio,0,0,0,0,0,0,0,0,""), 
	BADESSA2("badessa",-1,null,0,0,0,0,0,0,0,0,""),
	CAVALIERE("cavaliere",4,EAzioniGioco.PrendiImpresa,0,0,0,0,0,0,0,0,""),
	PREDICATORE( "predicatore",-1,EAzioniGioco.EffettoTorre,0,0,0,0,0,0,0,0,""), 
	COSTRUTTORE1("costruttore",4,EAzioniGioco.PrendiEdificio,0,0,0,0,0,0,0,0,""), 
	COSTRUTTORE2("costruttore",24,EAzioniGioco.PagaEdificio,0,0,0,0,0,0,0,0,""), 
	FORESTA("foresta",0,EAzioniGioco.Raccolto,0,3,0,0,0,0,0,0,"+3W"), 
	BOSCO("bosco",0,EAzioniGioco.Raccolto,0,1,0,0,0,0,0,0,"+1W"), 
	ROCCA("rocca",0,EAzioniGioco.Raccolto,0,0,1,0,0,2,0,0,"+1St"), 
	BORGO("borgo",0,EAzioniGioco.Raccolto,1,0,0,1,0,0,0,0,"+1C, +1Sv"), 
	CAVA_DI_GHIAIA("cava di ghiaia", 0,EAzioniGioco.Raccolto,0,0,2,0,0,0,0,0,"+1W"), 
	MONASTERO("monastero",0,EAzioniGioco.Raccolto,0,0,1,0,0,0,1,0,"+1W, +1FP"), 
	CITTA("citta",3,EAzioniGioco.Raccolto,0,0,0,0,0,0,0,0,""), 
	AVAMPOSTO_COMMERCIALE("avamposto commerciale",0,EAzioniGioco.Raccolto,1,0,0,0,0,0,0,0,"+1C");

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
