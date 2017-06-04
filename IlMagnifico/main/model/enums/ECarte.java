package main.model.enums;

import java.util.ArrayList;

public enum ECarte {

	CAPPELLA("cappella", 1, 2, ETipiCarte.Edificio), ESATTORIA("esattoria", 1, 5, ETipiCarte.Edificio), ZECCA("zecca",
			1, 5, ETipiCarte.Edificio), TEATRO("teatro", 1, 6, ETipiCarte.Edificio), ARCO_DI_TRIONFO("arco di trionfo",
					1, 6, ETipiCarte.Edificio), TAGLIAPIETRE("tagliapietre", 1, 3, ETipiCarte.Edificio), RESIDENZA(
							"residenza", 1, 1, ETipiCarte.Edificio), FALEGNAMERIA("falegnameria", 1, 4,
									ETipiCarte.Edificio), SOSTEGNO_AL_VESCOVO("sostegno al vescovo", 1, 0,
											ETipiCarte.Impresa), CAMPAGNA_MILITARE("campagna militare", 1, 0,
													ETipiCarte.Impresa), INNALZARE_UNA_STATUA("innalzare una statua", 1,
															0,
															ETipiCarte.Impresa), COSTRUIRE_LE_MURA("costruire le mura",
																	1, 0, ETipiCarte.Impresa), COMBATTERE_LE_ERESIE(
																			"combattere le eresie", 1, 0,
																			ETipiCarte.Impresa), INGAGGIARE_RECLUTE(
																					"ingaggiare reclute", 1, 0,
																					ETipiCarte.Impresa), OSPITARE_I_MENDICANTI(
																							"ospitare i mendicanti", 1,
																							0,
																							ETipiCarte.Impresa), RIPARARE_LA_CHIESA(
																									"riparare la chiesa",
																									1, 0,
																									ETipiCarte.Impresa), CONTADINO(
																											"contadino",
																											1, 0,
																											ETipiCarte.Personaggio), CONDOTTIERO(
																													"condottiero",
																													1,
																													0,
																													ETipiCarte.Personaggio), ARTIGIANO(
																															"artigiano",
																															1,
																															0,
																															ETipiCarte.Personaggio), DAMA(
																																	"dama",
																																	1,
																																	0,
																																	ETipiCarte.Personaggio), BADESSA(
																																			"badessa",
																																			1,
																																			0,
																																			ETipiCarte.Personaggio), CAVALIERE(
																																					"cavaliere",
																																					1,
																																					0,
																																					ETipiCarte.Personaggio), PREDICATORE(
																																							"predicatore",
																																							1,
																																							0,
																																							ETipiCarte.Personaggio), COSTRUTTORE(
																																									"costruttore",
																																									1,
																																									0,
																																									ETipiCarte.Personaggio), FORESTA(
																																											"foresta",
																																											1,
																																											5,
																																											ETipiCarte.Territorio), BOSCO(
																																													"bosco",
																																													1,
																																													2,
																																													ETipiCarte.Territorio), ROCCA(
																																															"rocca",
																																															1,
																																															5,
																																															ETipiCarte.Territorio), BORGO(
																																																	"borgo",
																																																	1,
																																																	3,
																																																	ETipiCarte.Territorio), CAVA_DI_GHIAIA(
																																																			"cava di ghiaia",
																																																			1,
																																																			4,
																																																			ETipiCarte.Territorio), MONASTERO(
																																																					"monastero",
																																																					1,
																																																					6,
																																																					ETipiCarte.Territorio), CITTA(
																																																							"citta",
																																																							1,
																																																							6,
																																																							ETipiCarte.Territorio), AVAMPOSTO_COMMERCIALE(
																																																									"avamposto commerciale",
																																																									1,
																																																									1,
																																																									ETipiCarte.Territorio);

	private String nome;
	private int periodo;
	private int valoreNecessarioAttivazione;
	private ArrayList<Object[]> costi;
	private ArrayList<Object[]> effettiImmediati;
	private ArrayList<Object[]> effettiPermanenti;
	private ETipiCarte tipoCarta;

	private ECarte(String nome, int periodo, int valoreNecessario, ETipiCarte tipo) {
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
	}

	public void inizializzaCosti() {
		for (ECostiCarte e : ECostiCarte.values()) {
			if (this.nome.equals(e.getNome())) {
				this.costi = e.getCosti();
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
}
