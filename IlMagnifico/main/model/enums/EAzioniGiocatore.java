package main.model.enums;

import java.util.ArrayList;

public enum EAzioniGiocatore {

	Produzione, ProduzioneOvale, RaccoltoOvale, Raccolto, Mercato, PalazzoConsiglio, Torre, SostegnoChiesa,AumentaValoreFamigliare;

	public static String stringify() {
		return stringify(new ArrayList<EAzioniGiocatore>());
	}

	public static String stringify(ArrayList<EAzioniGiocatore> hidedElements) {
		EAzioniGiocatore[] a = EAzioniGiocatore.values();
		String s = "";

		for (int i = 0; i < a.length; i++) {
			if (!hidedElements.contains(a[i])) {
				s += "[" + a[i].toString() + "] ";
				if (i % 7 == 0 && i != 0)
					s += "\n";
			}
		}
		return s;
	}

}
