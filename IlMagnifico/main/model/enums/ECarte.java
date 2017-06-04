package main.model.enums;

import java.util.ArrayList;

public enum ECarte {

	CAPPELLA("cappella", 1, 2, ETipiCarte.Edificio);

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
