package main.model.enums;

import java.util.ArrayList;

import main.model.Carta;
import main.util.ANSI;

public enum ECarte {
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
	AVAMPOSTO_COMMERCIALE("avamposto commerciale", 1, 1,ETipiCarte.Territorio, 0, 0);

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
				for (ECarte card : ca) {
					if (card.getNome().equals(cards.get(i).getNome())) {
						ecarte.add(card);
					}
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
			s += String.format("%-35s%-15s%-10s%-30s\n", "[i: nome] ", "tipo: ", "periodo: ", "costo: ");
		// s += ANSI.RESET;
		String desc = "";
		for (int i = ca.length-1; i >=0 ; i--){
			for (ECostiCarte cost : ECostiCarte.values()) {
				if (ca[i].nome.equals(cost.getNome())) {
					desc = cost.getDescrizione();
				}
			}
			s += String.format("%-35s%-15s%-10s%-30s", "[" + (i + inc) + ": " + ca[i].nome + "] ",
					ca[i].tipoCarta.toString(), ca[i].periodo, desc);
			// if (i % 7 == 0 && i != 0)
			s += "\n";
			desc = "";
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
		s += String.format("%-35s%-15s%-10s%-30s\n\n", "[i: nome] ", "tipo: ", "periodo: ", "costo: ");
		// s += ANSI.RESET;
		String desc = "";
		for (int i = 0; i < ca.length; i++) {
			for (ECostiCarte cost : co) {
				if (ca[i].nome.equals(cost.getNome())) {
					desc = cost.getDescrizione();
				}
			}
			s += String.format("%-35s%-15s%-10s%-30s", "[" + (i + inc) + ": " + ca[i].nome + "] ",
					ca[i].tipoCarta.toString(), ca[i].periodo, desc);
			// if (i % 7 == 0 && i != 0)
			s += "\n";
			desc = "";
		}
		return s;
	}

	public static String stringify(boolean startsWithZero) {
		return stringify(null, startsWithZero);
	}

	public static String stringify() {
		return stringify(null, true);
	}
}
