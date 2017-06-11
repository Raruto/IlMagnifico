package main.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum ECostiCarte {

	CAPPELLA("cappella", 0, 2, 0, 0, 0, 0,"2L"), 
	ESATTORIA("esattoria", 0, 3, 1, 0, 0, 0,"3L, 1P"), 
	ZECCA("zecca", 0, 1, 3, 0, 0, 0,"1L, 3P"), 
	TEATRO("teatro", 2, 2, 0, 0, 0, 0,"2M, 2L"), 
	ARCO_DI_TRIONFO("arco di trionfo", 2, 0, 2, 0, 0, 0,"2M, 2P"), 
	TAGLIAPIETRE("tagliapietre", 1, 0, 2, 0, 0, 0,"1M, 2P"), 
	RESIDENZA("residenza", 0, 0, 2, 0, 0, 0,"2P"), 
	FALEGNAMERIA("falegnameria", 1, 2, 0, 0, 0, 0,"1M, 2L"), 
	SOSTEGNO_AL_VESCOVO1("sostegno al vescovo", 2, 1, 1, 0, 0, 0,"2M, 1L, 1P"), 
	SOSTEGNO_AL_VESCOVO2("sostegno al vescovo", 0, 0, 0, 0, 2, 4,"4PV? -> -2"), 
	CAMPAGNA_MILITARE("campagna militare", 0, 0, 0, 0, 2, 3,"3PM? -> -2"),
	INNALZARE_UNA_STATUA("innalzare una statua", 0, 2, 2, 0, 0, 0,"2L, 2P"), 
	COSTRUIRE_LE_MURA("costruire le mura", 0, 0, 3, 0, 0, 0,"3P"), 
	COMBATTERE_LE_ERESIE("combattere le eresie", 0, 0, 0, 0, 3, 5,"5PM? -> -3"), 
	INGAGGIARE_RECLUTE("ingaggiare reclute", 4, 0, 0, 0, 0, 0,"4M"), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti", 0, 3, 0, 0, 0, 0,"3L"), 
	RIPARARE_LA_CHIESA("riparare la chiesa", 1, 1, 1, 0, 0,	0,"1M, 1L, 1P"), 
	CONTADINO("contadino", 3, 0, 0, 0, 0, 0,"3M"), 
	CONDOTTIERO("condottiero", 2, 0, 0, 0, 0, 0,"2M"), 
	ARTIGIANO("artigiano", 3, 0, 0, 0, 0, 0,"3M"), 
	DAMA("dama", 4, 0, 0, 0, 0, 0,"4M"), 
	BADESSA("badessa", 3, 0, 0, 0, 0, 0,"3M"), 
	CAVALIERE("cavaliere", 2, 0, 0, 0, 0, 0,"2M"), 
	PREDICATORE("predicatore", 2, 0, 0, 0, 0, 0,"2M"), 
	COSTRUTTORE("costruttore", 4, 0, 0, 0, 0, 0,"4M"), 
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

	public Object[] getCosto() {
		Object[] costi = new Object[14];
		if (this.costoMonete > 0 || this.costoLegno > 0 || this.costoPietra > 0 || this.costoServitori > 0) {
			for (int i = 0; i < 14; i++) {
				costi[i] = new Object();
			}
			costi[0] = 1;
			costi[5] = this.costoMonete;
			costi[6] = this.costoLegno;
			costi[7] = this.costoPietra;
			costi[8] = this.costoServitori;
			costi[9] = 0;
			costi[10] = 0;
			costi[11] = 0;
			costi[13]=0;
		}else
		if (this.costoMonete == 0 && this.costoLegno == 0 && this.costoPietra == 0 && this.costoServitori == 0 && this.costoPM==0) {
			for (int i = 0; i < 14; i++) {
				costi[i] = new Object();
			}
			costi[0] = 1;
			costi[5] = this.costoMonete;
			costi[6] = this.costoLegno;
			costi[7] = this.costoPietra;
			costi[8] = this.costoServitori;
			costi[9] = 0;
			costi[10] = 0;
			costi[11] = 0;
			costi[13]=0;
		}
		else
		if (this.costoPM > 0) {
			
			for (int i = 0; i < 14; i++) {
				costi[i] = new Object();
			}
			costi[0] = 2;
			costi[10] = this.costoPM;
			costi[5] = 0;
			costi[6] = 0;
			costi[7] = 0;
			costi[8] = 0;
			costi[9] = 0;
			costi[10] = 0;
			costi[11] = 0;
			costi[13]=this.sogliaPuntiMilitari;
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
