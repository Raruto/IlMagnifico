package main.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum ESceltePrivilegioDelConsiglio {
	LegnoEPietra("1 Wood and 1 Stone"), Servitori("2 Servants"), Monete("2 Coins"), PuntiMilitari(
			"2 Military Points"), PuntoFede("1 Faith Points");

	private String description;

	private ESceltePrivilegioDelConsiglio(String description) {
		this.description = description;
	}

	public static String stringify() {
		return stringify(new ArrayList<ESceltePrivilegioDelConsiglio>(),true);
	}

	public static String stringify(List<ESceltePrivilegioDelConsiglio> hidedElements, boolean StartFromZero) {
		ESceltePrivilegioDelConsiglio[] p = ESceltePrivilegioDelConsiglio.values();
		String s = "";

		int inc = 0;
		if (!StartFromZero)
			inc = 1;

		for (int i = 0; i < p.length; i++) {
			if (!hidedElements.contains(p[i])) {
				s += "[" + (i + inc) + ": " + p[i].getDescription() + "] ";
				if (i % 7 == 0 && i != 0)
					s += "\n";
			}
		}
		return s;
	}

	public String getDescription() {
		return this.description;
	}
}
