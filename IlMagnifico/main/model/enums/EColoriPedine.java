package main.model.enums;

import main.model.Famigliare;
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

	public static String stringify(int[] values) {
		EColoriPedine[] c = EColoriPedine.values();
		String s = "";

		for (int i = 0; i < c.length; i++) {
			s += "[" + i + ": " + c[i].getANSICode() + "♜"
					+ ANSI.RESET /* .toString() */ + " = " + values[c[i].getIndiceColore()] + "] ";
			if (i % 7 == 0 && i != 0)
				s += "\n";
		}
		return s;
	}

	public static String stringify(Famigliare[] family, boolean startsWithZero) {
		EColoriPedine[] c = EColoriPedine.values();
		String s = "";

		int inc = 0;
		if (!startsWithZero)
			inc = 1;

		for (int i = 0; i < c.length; i++) {
			if (family == null || !family[i].getPosizionato()) {
				s += "[" + (i + inc) + ": " + c[i].getANSICode() + "♜"
						+ ANSI.RESET /* .toString() */ + "] ";
				if (i % 7 == 0 && i != 0)
					s += "\n";
			}
		}
		return s;
	}

	public static String stringify() {
		return stringify(null, true);
	}

}
