package main.model.enums;

import java.util.ArrayList;

public enum ECostiCarte {

	// Periodo 1
	CAPPELLA(			  "cappella", 			  0, 2, 0, 0, 0, 0, "2W"), 
	ESATTORIA(			  "esattoria", 			  0, 3, 1, 0, 0, 0, "3W, 1St"), 
	ZECCA(				  "zecca", 				  0, 1, 3, 0, 0, 0, "1W, 3St"), 
	TEATRO(				  "teatro", 			  2, 2, 0, 0, 0, 0, "2C, 2W"), 
	ARCO_DI_TRIONFO(	  "arco di trionfo", 	  2, 0, 2, 0, 0, 0, "2C, 2St"), 
	TAGLIAPIETRE(		  "tagliapietre", 		  1, 0, 2, 0, 0, 0, "1C, 2St"), 
	RESIDENZA(			  "residenza", 			  0, 0, 2, 0, 0, 0, "2St"), 
	FALEGNAMERIA(		  "falegnameria", 		  1, 2, 0, 0, 0, 0, "1C, 2W"), 
	SOSTEGNO_AL_VESCOVO1( "sostegno al vescovo",  2, 1, 1, 0, 0, 0, "2C, 1W, 1St"), 
	SOSTEGNO_AL_VESCOVO2( "sostegno al vescovo",  0, 0, 0, 0, 2, 4, "4MP? -> -2"), 
	CAMPAGNA_MILITARE(	  "campagna militare",    0, 0, 0, 0, 2, 3, "3MP? -> -2"),
	INNALZARE_UNA_STATUA( "innalzare una statua", 0, 2, 2, 0, 0, 0, "2W, 2St"), 
	COSTRUIRE_LE_MURA(	  "costruire le mura",    0, 0, 3, 0, 0, 0, "3St"), 
	COMBATTERE_LE_ERESIE( "combattere le eresie", 0, 0, 0, 0, 3, 5, "5MP? -> -3"), 
	INGAGGIARE_RECLUTE(	  "ingaggiare reclute",   4, 0, 0, 0, 0, 0, "4C"), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti",0, 3, 0, 0, 0, 0, "3W"), 
	RIPARARE_LA_CHIESA(	  "riparare la chiesa",   1, 1, 1, 0, 0, 0, "1C, 1W, 1St"), 
	CONTADINO(			  "contadino", 			  3, 0, 0, 0, 0, 0, "3C"), 
	CONDOTTIERO(		  "condottiero", 		  2, 0, 0, 0, 0, 0, "2C"), 
	ARTIGIANO(			  "artigiano", 			  3, 0, 0, 0, 0, 0, "3C"), 
	DAMA(				  "dama", 				  4, 0, 0, 0, 0, 0, "4C"), 
	BADESSA(			  "badessa", 			  3, 0, 0, 0, 0, 0, "3C"), 
	CAVALIERE(			  "cavaliere", 			  2, 0, 0, 0, 0, 0, "2C"), 
	PREDICATORE(		  "predicatore", 		  2, 0, 0, 0, 0, 0, "2C"), 
	COSTRUTTORE(		  "costruttore", 		  4, 0, 0, 0, 0, 0, "4C"), 
	FORESTA(			  "foresta", 			  0, 0, 0, 0, 0, 0, ""), 
	BOSCO(				  "bosco", 				  0, 0, 0, 0, 0, 0, ""), 
	ROCCA(				  "rocca", 				  0, 0, 0, 0, 0, 0, ""), 
	BORGO(				  "borgo", 				  0, 0, 0, 0, 0, 0, ""), 
	CAVA_DI_GHIAIA(		  "cava di ghiaia", 	  0, 0, 0, 0, 0, 0, ""), 
	MONASTERO(			  "monastero", 			  0, 0, 0, 0, 0, 0, ""), 
	CITTA(				  "citta", 				  0, 0, 0, 0, 0, 0, ""), 
	AVAMPOSTO_COMMERCIALE("avamposto commerciale",0, 0, 0, 0, 0, 0, ""),
	
	//;
	// Periodo 2
	MERCATO(				 "mercato", 				0, 2, 1, 0, 0, 0, "2W, 1St"),
	FORTEZZA(				 "fortezza", 				2, 2, 2, 0, 0, 0, "2C, 2W, 2St"),
	GILDA_DEI_COSTRUTTORI(	 "gilda dei costruttori", 	0, 1, 2, 0, 0, 0, "1W, 2St"),
	BATTISTERO(				 "battistero", 				0, 0, 3, 0, 0, 0, "3St"),
	TESORERIA(				 "tesoreria", 				0, 3, 0, 0, 0, 0, "3W"),
	CASERMA(				 "caserma", 				0, 1, 1, 0, 0, 0, "1W, 1St"),
	GILDA_DEGLI_SCULTORI(	 "gilda degli scultori", 	0, 0, 4, 0, 0, 0, "4St"),
	GILDA_DEI_PITTORI(		 "gilda dei pittori", 		0, 4, 0, 0, 0, 0, "4W"),
	COSTRUIRE_I_BASTIONI(	 "costruire i bastioni", 	0, 0, 4, 0, 0, 0, "4St"),
	ACCOGLIERE_GLI_STRANIERI("accogliere gli stranieri",0, 4, 0, 0, 0, 0, "4W"), 
	INGAGGIARE_SOLDATI(		 "ingaggiare soldati", 		6, 0, 0, 0, 0, 0, "6C"), 
	SUPPORTO_AL_RE(			 "supporto al re", 			0, 0, 0, 0, 3, 6, "6MP? -> -3"), 
	CROCIATA(				 "crociata", 				0, 0, 0, 0, 4, 8, "8MP -> -4"), 
	SOSTEGNO_AL_CARDINALE1(	 "sostegno al cardinale", 	0, 0, 0, 0, 4, 7, "7MP? -> -4"), 
	SOSTEGNO_AL_CARDINALE2(	 "sostegno al cardinale", 	3, 2, 2, 0, 0, 0, "2W, 3C, 2St"),
	SCAVARE_CANALIZZAZIONI(	 "scavare canalizzazioni", 	3, 0, 0, 2, 0, 0, "2Sv, 3C"), 
	RIPARARE_ABBAZIA(		 "riparare abbazia", 		2, 2, 2, 0, 0, 0, "2C, 2W, 2St"),
	FATTORE(				 "fattore", 				4, 0, 0, 0, 0, 0, "4C"),
	MESSO_REALE(			 "messo reale", 			5, 0, 0, 0, 0, 0, "5C"), 
	ARCHITETTO(				 "architetto", 				4, 0, 0, 0, 0, 0, "4C"),	
	CAPITANO(				 "capitano", 				4, 0, 0, 0, 0, 0, "4C"),
	MECENATE(				 "mecenate", 				3, 0, 0, 0, 0, 0, "3C"), 
	STUDIOSO(				 "studioso", 				4, 0, 0, 0, 0, 0, "4C"), 
	EROE(					 "eroe", 					4, 0, 0, 0, 0, 0, "4C"), 
	MESSO_PAPALE(			 "messo papale", 			5, 0, 0, 0, 0, 0, "5C"),
	MINIERA_ORO(			 "miniera oro", 			0, 0, 0, 0, 0, 0, ""),
	MANIERO(				 "maniero", 				0, 0, 0, 0, 0, 0, ""),
	EREMO(					 "eremo", 					0, 0, 0, 0, 0, 0, ""),
	VILLAGGIO_MINERARIO(	 "villaggio minerario", 	0, 0, 0, 0, 0, 0, ""),
	VILLAGGIO_MONTANO(		 "villaggio montano", 		0, 0, 0, 0, 0, 0, ""),
	CAVA_DI_PIETRA(			 "cava di pietra", 			0, 0, 0, 0, 0, 0, ""),
	DUCATO(					 "ducato", 					0, 0, 0, 0, 0, 0, ""),
	POSSEDIMENTO(			 "possedimento", 			0, 0, 0, 0, 0, 0, ""),

	//;
	// Periodo 3
	PALAZZO(				 "palazzo", 				3, 3, 1, 0, 0, 0, "3C, 3W, 1St"),
	BANCA(					 "banca", 					3, 1, 3, 0, 0, 0, "3C, 1W, 3St"),
	GIARDINO(				 "giardino", 				0, 4, 2, 2, 0, 0, "2Sv, 4W, 2St"),
	FIERA(					 "fiera", 					4, 3, 0, 0, 0, 0, "4C, 3W"),
	CASTELLETTO(			 "castelletto", 			2, 2, 4, 0, 0, 0, "2C, 2W, 4St"),
	ACCADEMIA_MILITARE(		 "accademia militare", 		0, 2, 2, 1, 0, 0, "1Sv, 2W, 2St"),
	BASILICA(				 "basilica", 				0, 1, 4, 0, 0, 0, "1W, 4St"),
	CATTEDRALE(				 "cattedrale", 				0, 4, 4, 0, 0, 0, "4W, 4St"),
	CONQUISTA_MILITARE(		 "conquista militare", 		0, 0, 0, 0, 6, 12,"12MP? -> -6"),
	RIPARARE_LA_CATTEDRALE(	 "riparare la cattedrale", 	3, 3, 3, 0, 0, 0, "3C, 3W, 3St"),
	GUERRA_SANTA(			 "guerra santa", 			0, 0, 0, 0, 8, 15,"15MP? -> -8"),
	INGAGGIARE_MERCENARI(	 "ingaggiare mercenari", 	8, 0, 0, 0, 0, 0, "8C"),
	COSTRUIRE_LE_TORRI(		 "costruire le torri", 		0, 0, 6, 0, 0, 0, "6St"),
	COMMISSIONARE_ARTE_SACRA("commissionare arte sacra",0, 6, 0, 0, 0, 0, "6W"),
	SOSTEGNO_AL_PAPA1(		 "sostegno al papa", 		0, 0, 0, 0, 5, 10,"10MP? -> -5"),
	SOSTEGNO_AL_PAPA2(		 "sostegno al papa", 		4, 3, 3, 0, 0, 0, "3W, 4C, 3St"),
	MIGLIORARE_LE_STRADE(	 "migliorare le strade", 	4, 0, 0, 3, 0, 0, "3Sv, 4C"),	
	GOVERNATORE(			 "governatore", 			6, 0, 0, 0, 0, 0, "6C"),
	CARDINALE(				 "cardinale", 				4, 0, 0, 0, 0, 0, "4C"),
	GENERALE(				 "generale", 				5, 0, 0, 0, 0, 0, "5C"),
	CORTIGIANA(				 "cortigiana", 				7, 0, 0, 0, 0, 0, "7C"),
	VESCOVO(				 "vescovo", 				5, 0, 0, 0, 0, 0, "5C"),
	AMBASCIATORE(			 "ambasciatore", 			6, 0, 0, 0, 0, 0, "6C"),
	ARALDO(					 "araldo", 					6, 0, 0, 0, 0, 0, "6C"),
	NOBILE(					 "nobile", 					6, 0, 0, 0, 0, 0, "6C"),	
	CITTA_MERCANTILE(		 "citta mercantile", 		0, 0, 0, 0, 0, 0, ""),
	TENUTA(					 "tenuta", 					0, 0, 0, 0, 0, 0, ""),
	CITTA_FORTIFICATA(		 "citta fortificata", 		0, 0, 0, 0, 0, 0, ""),
	CASTELLO(				 "castello", 				0, 0, 0, 0, 0, 0, ""),
	SANTUARIO(				 "santuario", 				0, 0, 0, 0, 0, 0, ""),
	PROVINCIA(				 "provincia", 				0, 0, 0, 0, 0, 0, ""),
	CAVA_DI_MARMO(			 "cava di marmo", 			0, 0, 0, 0, 0, 0, ""),
	COLONIA(				 "colonia", 				0, 0, 0, 0, 0, 0, "");
	
	
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
