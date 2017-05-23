package LorenzoIlMagnifico;

public class UtilEffetto {
	/*
	 * aggiunge risorse al giocatore passato tra i parametri.
	 * Tra i parametri sono passate le risorse da aggiungere e, se negative, sono quelle che il giocatore deve spendere
	 */
	public boolean aggiungiRisorse(Object o[]) {// metodo numero 0
		Giocatore giocatore = (Giocatore) (o[1]);
		int monete = (int) (o[2]);
		int legno = (int) (o[3]);
		int pietre = (int) (o[4]);
		int servitori = (int) (o[5]);
		int puntiVittoria = (int) (o[6]);
		int puntiMilitari = (int) (o[7]);
		int puntiFede = (int) (o[8]);

		int[] risorse = { monete, legno, pietre, servitori, puntiVittoria, puntiMilitari, puntiFede };
		int[] risorseGiocatore = { giocatore.getRisorsa().getMonete(), giocatore.getRisorsa().getLegno,
				giocatore.getRisorsa().getPietre(), giocatore.getRisorsa().getServitori,
				giocatore.getPunti().getPuntiVittoria(), giocatore.getPunti().getPuntiMilitari(),
				giocatore.getPunti().getPuntiFede() };

		for (int i = 0; i < risorse.length; i++) {
			if (risorse[i] < 0) {
				if (risorse[i] + risorseGiocatore[i] < 0)
					return false;
			}
		}

		giocatore.getRisorsa().cambiaMonete(monete);
		giocatore.getRisorsa().cambiaLegno(legno);
		giocatore.getRisorsa().cambiaPietre(pietre);
		giocatore.getRisorsa().cambiaServitori(servitori);

		giocatore.getPunti().cambiaPuntiVittoria(puntiVittoria);
		giocatore.getPunti().cambiaPuntiMilitari(puntiMilitari);
		giocatore.getPunti().cambiaPuntiFede(puntiFede);

		return true;
	}

	/*
	 * controlla se il giocatore può pagare le risorse per l'aquisizione di una carta
	 */
	public boolean cartaAquisibileRisorse(Object o[]) {// metodo numero 2
		Giocatore giocatore = (Giocatore) (o[1]);
		int monete = (int) (o[2]);
		int legno = (int) (o[3]);
		int pietre = (int) (o[4]);
		int servitori = (int) (o[5]);
		if (giocatore.getRisorsa().getMonete() >= monete && giocatore.getRisorsa().getLegno() >= legno
				&& giocatore.getRisorsa().getPietre() >= pietre && giocatore.getRisorsa().getServitori() >= servitori)
			return true;
		else
			return false;
	}
	/*
	 * controlla se il giocatore può pagare i punti militari per l'aquisizione di una carta
	 * */
	public boolean cartaAquisibilePunti(Object o[]) {// i punti militari da
														// controllare non sono
														// quelli da pagare ma
														// quelli minimi da
														// avere
		Giocatore giocatore = (Giocatore) (o[1]);
		int puntiMilitari = (int) (o[2]);
		if (giocatore.getPunti().getPuntiMilitari() >= puntiMilitari)
			return true;
		else
			return false;
	}


}
