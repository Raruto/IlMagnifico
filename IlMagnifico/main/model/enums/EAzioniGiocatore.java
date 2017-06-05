package main.model.enums;

public enum EAzioniGiocatore {
	// ho inserito le costanti che servono per
	// controllare le attivazioni degli effetti
	// delle carte. Se qua ti danno fastidio si può
	// fare un altro enum
	Produzione, ProduzioneOvale, RaccoltoOvale, Raccolto, Mercato, PalazzoConsiglio, Torre, 
	PrendiTerritorio, PrendiPersonaggio, PrendiEdificio, PrendiImpresa, MuoviColorato, EffettoTorre, 
	PagaTerritorio, PagaPersonaggio, PagaEdificio, PagaImpresa, InizioTurno, FinePartita,
	PersonaggiFinePartita, TerritoriFinePartita, ImpreseFinePartita, 
	RiceviMonete, RiceviPietreOLegno, RiceviPM, RiceviServitori, PagaServitori;
	
	public static String stringify(){
		EAzioniGiocatore[] a = EAzioniGiocatore.values();
		String s="";

		for (int i = 0; i < a.length; i++) {
			s+="[" + a[i].toString() + "] ";
			if (i % 7 == 0 && i != 0)
				s+="\n";
		}
		return s;		
	}
}
