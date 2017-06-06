package main.model.enums;

public enum ETipiCarte {
	Territorio, Personaggio, Edificio, Impresa;

	public static String stringify() {
		ETipiCarte[] c = ETipiCarte.values();
		String s = "";

		for (int i = 0; i < c.length; i++) {
			s += "[" + c[i].toString() + "] ";
			if (i % 7 == 0 && i != 0)
				s += "\n";
		}
		return s;
	}
}
