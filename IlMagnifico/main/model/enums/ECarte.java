package main.model.enums;

import java.util.ArrayList;

import main.model.Carta;
import main.util.ANSI;

public enum ECarte {
	// Periodo 1
	CAPPELLA("cappella", 1, 2, ETipiCarte.Edificio, 0, 0), 
	ESATTORIA("esattoria", 1, 5, ETipiCarte.Edificio, 0,0), 
	ZECCA("zecca", 1, 5, ETipiCarte.Edificio, 0, 0), 
	TEATRO("teatro", 1, 6, ETipiCarte.Edificio, 0, 0), 
	ARCO_DI_TRIONFO("arco di trionfo", 1, 6, ETipiCarte.Edificio, 0, 0), 
	TAGLIAPIETRE("tagliapietre", 1, 3, ETipiCarte.Edificio, 0, 1), 
	RESIDENZA("residenza", 1, 1, ETipiCarte.Edificio, 0, 0), 
	FALEGNAMERIA("falegnameria", 1, 4, ETipiCarte.Edificio, 0, 1), 
	SOSTEGNO_AL_VESCOVO("sostegno al vescovo", 1, 0, ETipiCarte.Impresa, 1, 0), 
	CAMPAGNA_MILITARE("campagna militare", 1, 0, ETipiCarte.Impresa, 0, 0), 
	INNALZARE_UNA_STATUA("innalzare una statua", 1, 0, ETipiCarte.Impresa, 0, 0), 
	COSTRUIRE_LE_MURA("costruire le mura", 1, 0, ETipiCarte.Impresa, 0, 0), 
	COMBATTERE_LE_ERESIE("combattere le eresie", 1, 0, ETipiCarte.Impresa, 0, 0), 
	INGAGGIARE_RECLUTE("ingaggiare reclute", 1, 0, ETipiCarte.Impresa, 0, 0), 
	OSPITARE_I_MENDICANTI("ospitare i mendicanti", 1, 0, ETipiCarte.Impresa, 0,	0), 
	RIPARARE_LA_CHIESA("riparare la chiesa",1, 0, ETipiCarte.Impresa, 0, 0), 
	CONTADINO("contadino", 1, 0, ETipiCarte.Personaggio, 0,	0), 
	CONDOTTIERO("condottiero", 1, 0, ETipiCarte.Personaggio, 0, 0), 
	ARTIGIANO("artigiano", 1, 0, ETipiCarte.Personaggio, 0,	0), 
	DAMA("dama", 1, 0, ETipiCarte.Personaggio, 0, 0), 
	BADESSA("badessa", 1, 0, ETipiCarte.Personaggio, 0, 0), 
	CAVALIERE("cavaliere", 1, 0, ETipiCarte.Personaggio, 0,	0), 
	PREDICATORE("predicatore", 1, 0, ETipiCarte.Personaggio, 0, 0), 
	COSTRUTTORE("costruttore", 1, 0, ETipiCarte.Personaggio, 0, 0), 
	FORESTA("foresta", 1, 5, ETipiCarte.Territorio,	0, 0), 
	BOSCO("bosco", 1, 2, ETipiCarte.Territorio,	0, 0), 
	ROCCA("rocca", 1, 5, ETipiCarte.Territorio, 0, 0), 
	BORGO("borgo", 1, 3, ETipiCarte.Territorio, 0, 0), 
	CAVA_DI_GHIAIA("cava di ghiaia", 1, 4, ETipiCarte.Territorio, 0, 0), 
	MONASTERO("monastero", 1, 6, ETipiCarte.Territorio, 0, 0), 
	CITTA("citta", 1, 6, ETipiCarte.Territorio, 0, 0), 
	AVAMPOSTO_COMMERCIALE("avamposto commerciale", 1, 1,ETipiCarte.Territorio, 0, 0),
	
	//;
	// Periodo 2
	MERCATO("mercato", 2, 3, ETipiCarte.Edificio, 0, 0),
	FORTEZZA("fortezza", 2, 6, ETipiCarte.Edificio, 0, 0),
	GILDA_DEI_COSTRUTTORI("gilda dei costruttori", 2, 4, ETipiCarte.Edificio, 0, 0),
	BATTISTERO("battistero", 2, 2, ETipiCarte.Edificio, 0, 0),
	TESORERIA("tesoreria", 2, 3, ETipiCarte.Edificio, 0, 1),
	CASERMA("caserma", 2, 1, ETipiCarte.Edificio, 0, 0),
	GILDA_DEGLI_SCULTORI("gilda degli scultori", 2, 5, ETipiCarte.Edificio, 0, 1),
	GILDA_DEI_PITTORI("gilda dei pittori", 2, 4, ETipiCarte.Edificio, 0, 1),
	COSTRUIRE_I_BASTIONI("costrutire i bastioni", 2, 0, ETipiCarte.Impresa, 0, 0),
	ACCOGLIERE_GLI_STRANIERI("accogliere gli stranieri", 2, 0, ETipiCarte.Impresa, 0, 0), 
	INGAGGIARE_SOLDATI("ingaggiare soldati", 2, 0, ETipiCarte.Impresa, 0, 0), 
	SUPPORTO_AL_RE("supporto al re", 2, 0, ETipiCarte.Impresa, 0, 0), 
	CROCIATA("crociata", 1, 0, ETipiCarte.Impresa, 0, 0), 
	SOSTEGNO_AL_CARDINALE("sostegno al cardinale", 2, 0, ETipiCarte.Impresa, 1, 0), 
	SCAVARE_CANALIZZAZIONI("scavare canalizzazioni", 2, 0, ETipiCarte.Impresa, 0, 0), 
	RIPARARE_ABBAZIA("riparare abbazia", 2, 0, ETipiCarte.Impresa, 0, 0),
	FATTORE("fattore", 2, 0, ETipiCarte.Personaggio, 0,	0),
	MESSO_REALE("messo reale", 2, 0, ETipiCarte.Personaggio, 0,	0), 
	ARCHITETTO("architetto", 2, 0, ETipiCarte.Personaggio, 0, 0),	
	CAPITANO("capitano", 2, 0, ETipiCarte.Personaggio, 0, 0),
	MECENATE("mecenate", 2, 0, ETipiCarte.Personaggio, 0, 0), 
	STUDIOSO("studioso", 2, 0, ETipiCarte.Personaggio, 0, 0), 
	EROE("eroe", 2, 0, ETipiCarte.Personaggio, 0, 0), 
	MESSO_PAPALE("messo papale", 2, 0, ETipiCarte.Personaggio, 0, 0),
	MINIERA_ORO("miniera oro", 2, 1, ETipiCarte.Territorio,	0, 0),
	MANIERO("maniero", 2, 5, ETipiCarte.Territorio,	0, 0),
	EREMO("eremo", 2, 2, ETipiCarte.Territorio,	0, 0),
	VILLAGGIO_MINERARIO("villaggio minerario", 2, 4, ETipiCarte.Territorio,	0, 0),
	VILLAGGIO_MONTANO("villaggio montano", 2, 3, ETipiCarte.Territorio,	0, 0),
	CAVA_DI_PIETRA("cava di pietra", 2, 3, ETipiCarte.Territorio, 0, 0),
	DUCATO("ducato", 2, 6, ETipiCarte.Territorio, 0, 0),
	POSSEDIMENTO("possedimento", 2, 4, ETipiCarte.Territorio, 0, 0),

	//;
	// Periodo 3
	PALAZZO("palazzo", 3, 6, ETipiCarte.Edificio, 0, 0),
	BANCA("banca", 3, 2, ETipiCarte.Edificio, 0, 0),
	GIARDINO("giardino", 3, 1, ETipiCarte.Edificio, 0, 0),
	FIERA("fiera", 3, 4, ETipiCarte.Edificio, 0, 0),
	CASTELLETTO("castelletto", 3, 5, ETipiCarte.Edificio, 0, 0),
	ACCADEMIA_MILITARE("accademia militare", 3, 3, ETipiCarte.Edificio, 0, 0),
	BASILICA("basilica", 3, 1, ETipiCarte.Edificio, 0, 1),
	CATTEDRALE("cattedrale", 3, 2, ETipiCarte.Edificio, 0, 0),
	CONQUISTA_MILITARE("conquista militare", 3, 0, ETipiCarte.Impresa, 0, 0),
	RIPARARE_LA_CATTEDRALE("riparare la cattedrale", 3, 0, ETipiCarte.Impresa, 0, 0),
	GUERRA_SANTA("guerra santa", 3, 0, ETipiCarte.Impresa, 0, 0),
	INGAGGIARE_MERCENARI("ingaggiare mercenari", 3, 0, ETipiCarte.Impresa, 0, 0),
	COSTRUIRE_LE_TORRI("costruire le torri", 3, 0, ETipiCarte.Impresa, 0, 0),
	COMMISSIONARE_ARTE_SACRA("commissionare arte sacra", 3, 0, ETipiCarte.Impresa, 0, 0),
	SOSTEGNO_AL_PAPA("sostegno al papa", 3, 0, ETipiCarte.Impresa, 1, 0),
	MIGLIORARE_LE_STRADE("migliorare le strade", 3, 0, ETipiCarte.Impresa, 0, 0),	
	GOVERNATORE("governatore", 3, 0, ETipiCarte.Personaggio, 0,	0),
	CARDINALE("cardinale", 3, 0, ETipiCarte.Personaggio, 0,	0),
	GENERALE("generale", 3, 0, ETipiCarte.Personaggio, 0, 0),
	CORTIGIANA("cortigiana", 3, 0, ETipiCarte.Personaggio, 0, 0),
	VESCOVO("vescovo", 3, 0, ETipiCarte.Personaggio, 0,	0),
	AMBASCIATORE("ambasciatore", 3, 0, ETipiCarte.Personaggio, 0, 0),
	ARALDO("araldo", 3, 0, ETipiCarte.Personaggio, 0, 0),
	NOBILE("nobile", 3, 0, ETipiCarte.Personaggio, 0, 0),	
	CITTA_MERCANTILE("citta mercantile", 3, 1, ETipiCarte.Territorio, 0, 0),
	TENUTA("tenuta", 3, 3, ETipiCarte.Territorio, 0, 0),
	CITTA_FORTIFICATA("citta fortificata", 3, 2, ETipiCarte.Territorio,	0, 0),
	CASTELLO("castello", 3, 4, ETipiCarte.Territorio, 0, 0),
	SANTUARIO("santuario", 3, 1, ETipiCarte.Territorio,	0, 0),
	PROVINCIA("provincia", 3, 6, ETipiCarte.Territorio,	0, 0),
	CAVA_DI_MARMO("cava di marmo", 3, 2, ETipiCarte.Territorio,	0, 0),
	COLONIA("colonia", 3, 5, ETipiCarte.Territorio,	0, 0);
	
	
	private String nome;
	private int periodo;
	private int valoreNecessarioAttivazione;
	private ArrayList<Object[]> costi;
	private ArrayList<Object[]> effettiImmediati;
	private ArrayList<Object[]> effettiPermanenti;
	private ETipiCarte tipoCarta;
	private int numScelteCosti;
	private int numScelteEffPermanenti;

	private ECarte(String nome, int periodo, int valoreNecessario, ETipiCarte tipo, int numScelteCosti,
			int numScelteEffPermanenti) {
		this.nome = nome;
		this.periodo = periodo;
		this.valoreNecessarioAttivazione = valoreNecessario;
		this.tipoCarta = tipo;
		this.costi = new ArrayList<Object[]>();
		inizializzaCosti();
		this.effettiImmediati = new ArrayList<Object[]>();
		inizializzaEffettiImmediati();
		this.effettiPermanenti = new ArrayList<Object[]>();
		inizializzaEffettiPermanenti();
		this.numScelteCosti = numScelteCosti;
		this.numScelteEffPermanenti = numScelteEffPermanenti;
	}

	public void inizializzaCosti() {
		for (ECostiCarte e : ECostiCarte.values()) {
			if (this.nome.equals(e.getNome())) {
				this.costi.add(e.getCosto());
			}
		}
	}

	public void inizializzaEffettiImmediati() {
		for (EEffettiImmediati e : EEffettiImmediati.values()) {
			if (this.nome.equals(e.getNome())) {
				this.effettiImmediati.add(e.getEffetto());
			}
		}
	}

	public void inizializzaEffettiPermanenti() {
		for (EEffettiPermanenti e : EEffettiPermanenti.values()) {
			if (this.nome.equals(e.getNome())) {
				this.effettiPermanenti.add(e.getEffetto());
			}
		}
	}

	public String getNome() {
		return this.nome;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public int getvaloreNecessarioAttivazione() {
		return this.valoreNecessarioAttivazione;
	}

	public ArrayList<Object[]> getCosti() {
		return this.costi;
	}

	public ArrayList<Object[]> getEffettiImmediati() {
		return this.effettiImmediati;
	}

	public ArrayList<Object[]> getEffettiPermanenti() {
		return this.effettiPermanenti;
	}

	public ETipiCarte getTipoCarta() {
		return this.tipoCarta;
	}

	public int getNumScelteCosti() {
		return this.numScelteCosti;
	}

	public int getNumScelteEffPermanenti() {
		return this.numScelteEffPermanenti;
	}

	public ArrayList<ECostiCarte> getCostiCarta() {
		ArrayList<ECostiCarte> arrayCosti = new ArrayList<ECostiCarte>();
		for (int i = 0; i < ECostiCarte.values().length; i++) {
			if (ECostiCarte.values()[i].getNome().equals(this.nome)) {
				arrayCosti.add(ECostiCarte.values()[i]);
			}
		}
		return arrayCosti;
	}

	public ArrayList<EEffettiPermanenti> getEffettiCarta() {
		ArrayList<EEffettiPermanenti> arrayCosti = new ArrayList<EEffettiPermanenti>();
		for (int i = 0; i < EEffettiPermanenti.values().length; i++) {
			if (EEffettiPermanenti.values()[i].getNome().equals(this.nome)) {
				arrayCosti.add(EEffettiPermanenti.values()[i]);
			}
		}
		return arrayCosti;
	}

	public static String stringify(ArrayList<Carta> cards, boolean startsWithZero, boolean printHeader) {
		ECarte[] ca = ECarte.values();

		if (cards != null) {
			ArrayList<ECarte> ecarte = new ArrayList<ECarte>();
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i) != null) {
					for (ECarte card : ca) {
						if (card.getNome().equals(cards.get(i).getNome())) {
							ecarte.add(card);
							break;
						}
					}
				} else {
					ecarte.add(null);
				}
			}
			ca = ecarte.toArray(new ECarte[ecarte.size()]);
		}
		String s = "";

		int inc = 0;
		if (!startsWithZero)
			inc = 1;

		// s += ANSI.YELLOW;
		if (printHeader)
			s += String.format("%-35s%-12s%-10s%-29s%-27s%-28s\n", "[i: name] ", "type: ", "period: ", "cost: ",
					"imm. effect: ", "perm. effect: ");
		// s += ANSI.RESET;
		String desc = "";
		String iEffects = "";
		String pEffects = "";

		for (int i = ca.length - 1; i >= 0; i--) {
			if (ca[i] != null) {
				desc = "";
				for (ECostiCarte cost : ECostiCarte.values()) {
					if (ca[i].nome.equals(cost.getNome()) && cost.getDescrizione() != "") {
						desc += "(" + cost.getDescrizione() + "), ";
					}
				}
				iEffects = "";
				for (EEffettiImmediati iEff : EEffettiImmediati.values()) {
					if (ca[i].nome.equals(iEff.getNome()) && iEff.getDescrizione() != "") {
						iEffects += "(" + iEff.getDescrizione() + "), ";
					}
				}
				pEffects = "";
				for (EEffettiPermanenti pEff : EEffettiPermanenti.values()) {
					if (ca[i].nome.equals(pEff.getNome()) && pEff.getDescrizione() != "") {
						pEffects += "(" + pEff.getDescrizione() + "), ";
					}
				}

				s += String.format("%-35s%-12s%-10s%-29s%-27s%-28s", "[" + (i + inc) + ": " + ca[i].nome + "] ",
						ca[i].tipoCarta.toString(), ca[i].periodo, desc, iEffects, pEffects);
				// if (i % 7 == 0 && i != 0)
				s += "\n";

				desc = "";
			}
		}
		return s;
	}

	public static String stringify(ArrayList<ECostiCarte> costs, boolean startsWithZero) {
		ECarte[] ca = ECarte.values();

		ECostiCarte[] co = ECostiCarte.values();

		if (costs != null) {
			ArrayList<ECarte> ecarte = new ArrayList<ECarte>();
			for (int i = 0; i < costs.size(); i++) {
				for (ECarte card : ca) {
					if (card.nome.equals(costs.get(i).getNome())) {
						ecarte.add(card);
					}
				}
			}
			ca = ecarte.toArray(new ECarte[ecarte.size()]);
			co = costs.toArray(new ECostiCarte[costs.size()]);
		}
		String s = "";

		int inc = 0;
		if (!startsWithZero)
			inc = 1;

		// s += ANSI.YELLOW;
		s += String.format("%-35s%-12s%-10s%-29s%-27s%-28s\n", "[i: name] ", "type: ", "period: ", "cost: ",
				"imm. effect: ", "perm. effect: ");
		// s += ANSI.RESET;
		String desc = "";
		String iEffects = "";
		String pEffects = "";
		for (int i = 0; i < ca.length; i++) {
			if (ca[i] != null) {
				desc = "";
				for (ECostiCarte cost : co) {
					if (ca[i].nome.equals(cost.getNome()) && cost.getDescrizione() != "") {
						desc += "(" + cost.getDescrizione() + "), ";
					}
				}
				iEffects = "";
				for (EEffettiImmediati iEff : EEffettiImmediati.values()) {
					if (ca[i].nome.equals(iEff.getNome()) && iEff.getDescrizione() != "") {
						iEffects += "(" + iEff.getDescrizione() + "), ";
					}
				}
				pEffects = "";
				for (EEffettiPermanenti pEff : EEffettiPermanenti.values()) {
					if (ca[i].nome.equals(pEff.getNome()) && pEff.getDescrizione() != "") {
						pEffects += "(" + pEff.getDescrizione() + "), ";
					}
				}

				s += String.format("%-35s%-12s%-10s%-29s%-27s%-28s", "[" + (i + inc) + ": " + ca[i].nome + "] ",
						ca[i].tipoCarta.toString(), ca[i].periodo, desc, iEffects, pEffects);
				// if (i % 7 == 0 && i != 0)
				s += "\n";

				desc = "";
			}
		}
		return s;
	}

	public static String stringify(boolean startsWithZero) {
		return stringify(null, startsWithZero);
	}

	public static String stringify() {
		return stringify(null, true);
	}

	public static String printLegend() {
		return ANSI.YELLOW + "Legend:" + ANSI.RESET + "\nW = wood, C = coin, St = stone, Sv = servant\nFP = faith point, MP = military point, VP = victory point\nCP = council privilege\n\n"+ANSI.YELLOW + "Examples:" + ANSI.RESET +"\n(4VP? -> -2) \t= only if you have 4VP then you can decrease VP of 2 units\n(+1Sv, +2MP) \t= gain 1Sv and 2MP\n(+2MP), (+1CP) \t= choose to earn 2MP or 1CP";
	}
}
