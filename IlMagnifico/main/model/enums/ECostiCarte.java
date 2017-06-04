package main.model.enums;

import java.util.ArrayList;

public enum ECostiCarte {

	CAPPELLA("cappella", 0, 2, 0, 0, 0);

	private String nome;
	private int costoMonete;
	private int costoLegno;
	private int costoPietra;
	private int costoServitori;
	private int costoPM;

	private ECostiCarte(String nome, int monete, int legno, int pietra, int servitori, int PM) {
		this.nome = nome;
		this.costoMonete = monete;
		this.costoLegno = legno;
		this.costoPietra = pietra;
		this.costoServitori = servitori;
		this.costoPM = PM;
	}

	public String getNome() {
		return this.nome;
	}

	public ArrayList<Object[]> getCosti() {
		ArrayList<Object[]> costi = new ArrayList<Object[]>();
		costi.add(new Object[13]);
		for (int i = 0; i < 13; i++) {
			costi.get(0)[i] = new Object();
		}
		costi.get(0)[0] = 1;
		costi.get(0)[5] = this.costoMonete;
		costi.get(0)[6] = this.costoLegno;
		costi.get(0)[7] = this.costoPietra;
		costi.get(0)[8] = this.costoServitori;
		costi.add(new Object[13]);
		for (int i = 0; i < 13; i++) {
			costi.get(1)[i] = new Object();
		}
		costi.get(1)[0] = 2;
		costi.get(1)[10] = this.costoPM;
		return costi;
	}
}
