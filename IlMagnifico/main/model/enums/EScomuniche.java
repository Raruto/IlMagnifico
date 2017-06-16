package main.model.enums;

import java.util.ArrayList;

public enum EScomuniche {
	RICEVI_MENO_PM("Receive less MP", 1),
	MENO_QUATTRO_ON_TERRITORIO("-4 on Territory",2),
	NO_PV_PERSONAGGIO("No Charachter VP",3);

	private String nome;
	private int periodo;
	private ArrayList<Object[]> effetto;

	private EScomuniche(String nome, int periodo) {
		this.nome = nome;
		this.periodo = periodo;

		this.effetto = new ArrayList<Object[]>();
		inizializzaEffetto();
	}

	public void inizializzaEffetto() {
		for (EEffettiScomuniche e : EEffettiScomuniche.values()) {
			if (this.nome.equals(e.getNome())) {
				this.effetto.add(e.getEffetto());
			}
		}

	}

	public int getPeriodo() {
		return this.periodo;
	}

	public String getNome() {
		return this.nome;
	}

	public ArrayList<Object[]> getEffetto() {
		return this.effetto;
	}
}
