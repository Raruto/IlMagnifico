package main.model.enums;

public enum ETipiCarte {
	Territorio("Territory"), Personaggio("Charachter"), Edificio("Building"), Impresa("Venture");

	String name;

	private ETipiCarte(String name) {
		this.name = name;
	}

	public static String stringify(boolean startsWithZero) {
		ETipiCarte[] c = ETipiCarte.values();
		String s = "";

		int inc = 0;
		if (!startsWithZero)
			inc = 1;

		for (int i = 0; i < c.length; i++) {
			s += "[" + (i + inc) + ": " + c[i].toString() + "] ";
			if (i % 7 == 0 && i != 0)
				s += "\n";
		}
		return s;
	}

	public static String stringify() {
		return stringify(true);
	}

	public String toString() {
		return this.name;
	};

}
