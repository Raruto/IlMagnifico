package main.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum ESceltePrivilegioDelConsiglio {
	LegnoEPietra("1 Legno e 1 Pietra"), 
	Servitori("2 Servitori"), 
	Monete("2 Monete"), 
	PuntiMilitari("2 Punti Militari"), 
	PuntoFede("1 Punto Fede");

	private String description;

	private ESceltePrivilegioDelConsiglio(String description) {
		this.description = description;
	}

	public static String stringify() {
		return stringify(new ArrayList<ESceltePrivilegioDelConsiglio>());
	}

	public static String stringify(List<ESceltePrivilegioDelConsiglio> hidedElements) {
		ESceltePrivilegioDelConsiglio[] p = ESceltePrivilegioDelConsiglio.values();
		String s = "";

		for (int i = 0; i < p.length; i++) {
			if (!hidedElements.contains(p[i])) {
				s += "[" + i + ": " + p[i].getDescription() + "] ";
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
