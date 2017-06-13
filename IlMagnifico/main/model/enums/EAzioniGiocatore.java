package main.model.enums;

import java.util.ArrayList;

public enum EAzioniGiocatore {

	Produzione("Production"), ProduzioneOvale("OvalProduction"), RaccoltoOvale("OvalHarvest"), Raccolto("Harvest"), Mercato("Market"), PalazzoConsiglio("CouncilPalace"), Torre("Tower"), SostegnoChiesa("ChurchSupport"), Famigliare("Familiar");

	private String name;
	
	private EAzioniGiocatore(String name){
		this.name=name;
	}
	public static String stringify() {
		return stringify(new ArrayList<EAzioniGiocatore>());
	}

	public static String stringify(ArrayList<EAzioniGiocatore> hidedElements) {
		EAzioniGiocatore[] a = EAzioniGiocatore.values();
		String s = "";

		for (int i = 0; i < a.length; i++) {
			if (!hidedElements.contains(a[i])) {
				s += "[" + a[i].toString() + "] ";
				if (i % 8 == 0 && i != 0)
					s += "\n";
			}
		}
		return s;
	}

	public String toString() {
		return this.name;
	}
}
