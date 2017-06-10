package main.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum ECostiCarte {

	CAPPELLA("cappella", 0, 2, 0, 0, 0, 0,"paga: 2 legna"), 
	ESATTORIA("esattoria", 0, 3, 1, 0, 0, 0,"paga: 3 legna, 1 pietra"), 
	ZECCA("zecca", 0, 1, 3, 0, 0, 0,"paga: 1 legna, 3 pietra"), 
	TEATRO("teatro", 2, 2, 0, 0, 0, 0,"paga: 2 monete, 2 legna"), 
	ARCO_DI_TRIONFO("arco di trionfo", 2, 0, 2, 0, 0, 0,"paga: 2 monete, 2 pietra"), 
	TAGLIAPIETRE("tagliapietre", 1, 0, 2, 0, 0, 0,"paga: 1 monete, 2 pietra"), 
	RESIDENZA("residenza", 0, 0, 2, 0, 0, 0,"paga: 2 pietra"), 
	FALEGNAMERIA("falegnameria", 1, 2, 0, 0, 0, 0,"paga: 1 monete, 2 legna"), 
	SOSTEGNO_AL_VESCOVO("sostegno al vescovo", 2, 1, 1, 0, 2, 4,"paga: 2 monete, 1 legna, 1 pietra oppure se hai 4 Punti vittoria pagane 2"), 
	CAMPAGNA_MILITARE("campagna militare", 0, 0, 0, 0, 2, 3,"paga: se hai 3 punti militari pagane 2"),
	INNALZARE_UNA_STATUA("innalzare una statua", 0, 2, 2, 0, 0, 0,"paga: 2 legna, 2 pietra"), 
	COSTRUIRE_LE_MURA("costruire le mura", 0, 0, 3, 0, 0, 0,"paga: 3 pietre"), 
	COMBATTERE_LE_ERESIE("combattere le eresie", 0, 0, 0, 0, 3, 5,"paga: se hai 5 punti militari pagane 3"), 
	INGAGGIARE_RECLUTE("ingaggiare reclute", 4, 0, 0, 0, 0, 0,"paga: 4 monete"), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti", 0, 3, 0, 0, 0, 0,"paga: 3 legna"), 
	RIPARARE_LA_CHIESA("riparare la chiesa", 1, 1, 1, 0, 0,	0,"paga: 1 monete, 1 legna, 1 pietra"), 
	CONTADINO("contadino", 3, 0, 0, 0, 0, 0,"paga: 3 monete"), 
	CONDOTTIERO("condottiero", 2, 0, 0, 0, 0, 0,"paga: 2 monete"), 
	ARTIGIANO("artigiano", 3, 0, 0, 0, 0, 0,"paga: 3 monete"), 
	DAMA("dama", 4, 0, 0, 0, 0, 0,"paga: 4 monete"), 
	BADESSA("badessa", 3, 0, 0, 0, 0, 0,"paga: 3 monete"), 
	CAVALIERE("cavaliere", 2, 0, 0, 0, 0, 0,"paga: 2 monete"), 
	PREDICATORE("predicatore", 2, 0, 0, 0, 0, 0,"paga: 2 monete"), 
	COSTRUTTORE("costruttore", 4, 0, 0, 0, 0, 0,"paga: 4 monete"), 
	FORESTA("foresta", 0, 0, 0, 0, 0, 0,""), 
	BOSCO("bosco", 0, 0, 0, 0, 0, 0,""), 
	ROCCA("rocca", 0, 0, 0, 0, 0, 0,""), 
	BORGO("borgo", 0, 0, 0, 0, 0, 0,""), 
	CAVA_DI_GHIAIA("cava di ghiaia", 0, 0, 0, 0, 0,	0,""), 
	MONASTERO("monastero", 0, 0, 0, 0, 0, 0,""), 
	CITTA("citta", 0, 0, 0, 0, 0, 0,""), 
	AVAMPOSTO_COMMERCIALE("avamposto commerciale", 0, 0, 0, 0, 0, 0,"");

	private String nome;
	private int costoMonete;
	private int costoLegno;
	private int costoPietra;
	private int costoServitori;
	private int costoPM;
	private int sogliaPuntiMilitari;
	private String descrizione;

	private ECostiCarte(String nome, int monete, int legno, int pietra, int servitori, int PM,int sogliaPuntiMilitari,String descrizione) {
		this.nome = nome;
		this.costoMonete = monete;
		this.costoLegno = legno;
		this.costoPietra = pietra;
		this.costoServitori = servitori;
		this.costoPM = PM;
		this.sogliaPuntiMilitari=sogliaPuntiMilitari;
		this.descrizione=descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public ArrayList<Object[]> getCosti() {
		ArrayList<Object[]> costi = new ArrayList<Object[]>();
		costi.add(new Object[14]);
		if (this.costoMonete > 0 || this.costoLegno > 0 || this.costoPietra > 0 || this.costoServitori > 0) {
			for (int i = 0; i < 14; i++) {
				costi.get(0)[i] = new Object();
			}
			costi.get(0)[0] = 1;
			costi.get(0)[5] = this.costoMonete;
			costi.get(0)[6] = this.costoLegno;
			costi.get(0)[7] = this.costoPietra;
			costi.get(0)[8] = this.costoServitori;
			costi.get(0)[9] = 0;
			costi.get(0)[10] = 0;
			costi.get(0)[11] = 0;
			costi.get(0)[13]=0;
		}
		if (this.costoMonete == 0 && this.costoLegno == 0 && this.costoPietra == 0 && this.costoServitori == 0 && this.costoPM==0) {
			for (int i = 0; i < 14; i++) {
				costi.get(0)[i] = new Object();
			}
			costi.get(0)[0] = 1;
			costi.get(0)[5] = this.costoMonete;
			costi.get(0)[6] = this.costoLegno;
			costi.get(0)[7] = this.costoPietra;
			costi.get(0)[8] = this.costoServitori;
			costi.get(0)[9] = 0;
			costi.get(0)[10] = 0;
			costi.get(0)[11] = 0;
			costi.get(0)[13]=0;
		}
		if (this.costoPM > 0) {
			costi.add(new Object[14]);
			for (int i = 0; i < 14; i++) {
				costi.get(1)[i] = new Object();
			}
			costi.get(1)[0] = 2;
			costi.get(1)[10] = this.costoPM;
			costi.get(1)[5] = 0;
			costi.get(1)[6] = 0;
			costi.get(1)[7] = 0;
			costi.get(1)[8] = 0;
			costi.get(1)[9] = 0;
			costi.get(1)[10] = 0;
			costi.get(1)[11] = 0;
			costi.get(1)[13]=this.sogliaPuntiMilitari;
		}
		return costi;
	}
	
	public static String stringify(ArrayList<ECostiCarte> costs){
		String s = "";

		for (int i = 0; i < costs.size(); i++) {
			s += "[" +i +": "+ costs.get(i).getNome() + "] ";
			if (i % 7 == 0 && i != 0)
				s += "\n";
		}
		return s;
	}

	public String getDescrizione(){
		return this.descrizione;
	}
}
