package main.model.enums;

import java.util.ArrayList;

public enum EScomuniche {
	RICEVI_MENO_PM(				"Receive less MP", 	"scomunica 1_1", 1), 
	MENO_QUATTRO_ON_TERRITORIO(	"-4 on Territory", 	"scomunica 1_2", 2), 
	NO_PV_PERSONAGGIO(			"No Charachter VP", "scomunica 1_3", 3);

	private String nome;
	private int periodo;
	private String nomeFile;
	private ArrayList<Object[]> effetto;

	private EScomuniche(String nome, String nomeFile, int periodo) {
		this.nome = nome;
		this.periodo = periodo;
		this.nomeFile = nomeFile;
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

	public String getNomeFile() {
		return this.nomeFile;
	}
}
