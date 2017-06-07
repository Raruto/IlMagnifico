package main.model.enums;

import main.util.ANSI;

public enum EColoriPedine {
	Nera(0, ANSI.BLACK), Arancione(1, ANSI.YELLOW), Bianca(2, ANSI.WHITE), Neutrale(3, ANSI.CYAN);
	private int indiceColore;
	private String ANSICode;

	private EColoriPedine(int indice, String ANSICode) {
		this.indiceColore = indice;
		this.ANSICode = ANSICode;
	}

	public int getIndiceColore() {
		return this.indiceColore;
	}

	public String getANSICode() {
		return ANSICode;
	}

	public static String stringify() {
		EColoriPedine[] c = EColoriPedine.values();
		String s = "";

		for (int i = 0; i < c.length; i++) {
			s += "[" + i + ": " + c[i].toString() + "] ";
			if (i % 7 == 0 && i != 0)
				s += "\n";
		}
		return s;
	}

}
