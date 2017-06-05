package main.model.enums;

import java.util.ArrayList;

public enum EScomuniche {
	RICEVI_MENO_PM("ricevi meno pm", 1, null),
	MENO_QUATTRO_ON_TERRITORIO("meno quattro on territorio",2,null),
	NO_PV_PERSONAGGIO("no pv personaggio",3,null);

	private String nome;
	private int periodo;
	private ArrayList<Object[]> effetto;

	private EScomuniche(String nome, int periodo, ArrayList<Object[]> effetto) {
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
