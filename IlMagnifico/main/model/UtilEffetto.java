package main.model;

import java.io.Serializable;

import main.model.enums.EAzioniGioco;

public class UtilEffetto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350024466665562089L;

	/**
	 * Aggiunge risorse al giocatore passato tra i parametri. Tra i parametri
	 * sono passate le risorse da aggiungere e, se negativi, sono quelle che il
	 * giocatore deve spendere.Restituisce true se l'esito del metodo e'
	 * positivo, false altrimenti.
	 * 
	 * @param
	 * @return boolean
	 */
	public boolean aggiungiRisorse(Object o[]) {// metodo numero 0
		Giocatore giocatore = (Giocatore) (o[1]);
		int monete = (int) (o[5]);
		int legno = (int) (o[6]);
		int pietre = (int) (o[7]);
		int servitori = (int) (o[8]);
		int puntiVittoria = (int) (o[9]);
		int puntiMilitari = (int) (o[10]);
		int puntiFede = (int) (o[11]);

		int[] risorse = { monete, legno, pietre, servitori, puntiVittoria, puntiMilitari, puntiFede };
		int[] risorseGiocatore = { giocatore.getRisorse().getMonete(), giocatore.getRisorse().getLegno(),
				giocatore.getRisorse().getPietre(), giocatore.getRisorse().getServitori(),
				giocatore.getPunti().getPuntiVittoria(), giocatore.getPunti().getPuntiMilitari(),
				giocatore.getPunti().getPuntiFede() };

		for (int i = 0; i < risorse.length; i++) {
			if (risorse[i] < 0) {
				if (risorse[i] + risorseGiocatore[i] < 0)
					return false;
			}
		}

		giocatore.getRisorse().cambiaMonete(monete);
		giocatore.getRisorse().cambiaLegno(legno);
		giocatore.getRisorse().cambiaPietre(pietre);
		giocatore.getRisorse().cambiaServitori(servitori);

		giocatore.getPunti().cambiaPuntiVittoria(puntiVittoria);
		giocatore.getPunti().cambiaPuntiMilitari(puntiMilitari);
		giocatore.getPunti().cambiaPuntiFede(puntiFede);

		return true;
	}

	/**
	 * Controlla se il giocatore puo' pagare le risorse per l'aquisizione di una
	 * carta.
	 * 
	 * @param
	 * @return boolean
	 */
	public boolean cartaAcquisibileRisorse(Object o[]) {// metodo numero 1
		Giocatore giocatore = (Giocatore) (o[1]);
		int monete = (int) (o[5]);
		int legno = (int) (o[6]);
		int pietre = (int) (o[7]);
		int servitori = (int) (o[8]);
		if (giocatore.getRisorse().getMonete() >= monete && giocatore.getRisorse().getLegno() >= legno
				&& giocatore.getRisorse().getPietre() >= pietre && giocatore.getRisorse().getServitori() >= servitori)
			return true;
		else
			return false;
	}

	/**
	 * Controlla se il giocatore puo' pagare i punti militari per l'aquisizione
	 * di una carta.
	 * 
	 * @param
	 * @return boolean
	 */
	public boolean cartaAcquisibilePunti(Object o[]) {// metodo numero 2
		Giocatore giocatore = (Giocatore) (o[1]);
		int puntiMilitari = (int) (o[10]);
		if (giocatore.getPunti().getPuntiMilitari() >= puntiMilitari)
			return true;
		else
			return false;
	}

	/**
	 * Proposta primitiva del metodo per l'esecuzione del privilegio del
	 * consiglio. Tramite l'array in ingresso e' possibile indicare quanti
	 * privilegi del consiglio effettuare ed il metodo procede da solo al
	 * controllo sulle scelte. Quando e' richiesta l'interazione dell'utente e'
	 * segnalato con un commento
	 */
	public void eseguiPrivilegioDelConsiglio(Object o[]) {// metodo numero 3
		/*
		 * Giocatore giocatore = (Giocatore) (o[1]); int numeroIterazioni =
		 * (int) (o[12]); String[] scelte = new String[numeroIterazioni]; String
		 * scelta = new String(); boolean controllo = false; for (int i = 0; i <
		 * numeroIterazioni; i++) { // TODO: finire di implementare /* scelta=
		 */
		// richiesto che il giocatore inserisca una stringa tra le possibili
		// scelte standard del privilegio del consiglio

		/*
		 * for (int j = 0; j < i; j++) { // controllo sulle scelte precedenti if
		 * (scelta == scelte[j]) controllo = true; } if (controllo = true) { //
		 * viene notificato all'utente che la scelta non e' valida controllo =
		 * false; i--;// ripeto la iterazione } else scelte[i] = scelta; } for
		 * (int i = 0; i < numeroIterazioni; i++) {// anziche' chiamare i //
		 * metodi cambia e' // possibile chiamare il // metodo 0 di UtilEffetto
		 * if (scelte[i] == "monete") giocatore.getRisorse().cambiaMonete(2); if
		 * (scelte[i] == "legno&pietra") {
		 * giocatore.getRisorse().cambiaLegno(1);
		 * giocatore.getRisorse().cambiaPietre(1); } if (scelte[i] ==
		 * "servitori") giocatore.getRisorse().cambiaServitori(2); if (scelte[i]
		 * == "punti militari") giocatore.getPunti().cambiaPuntiMilitari(2); if
		 * (scelte[i] == "punti fede") giocatore.getPunti().cambiaPuntiFede(1);
		 * }
		 */
	}

	/**
	 * 
	 * 
	 * @param
	 * @return
	 */
	public void aumentaDiDueAzione(Object o[]) {// metodo numero 4
		cambiaValoreAzione(2, (Famigliare) (o[3]));
	}

	/**
	 * @param
	 * @return
	 */
	public void aumentaDiTreAzione(Object o[]) {// metodo numero 5
		cambiaValoreAzione(3, (Famigliare) (o[3]));
	}

	/**
	 * Metodo per gli effetti immediati che richiedono di eseguire l'azione
	 * Raccolto di valore 4
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void eseguiEffettoImmediatoRaccolto(Object o[]) {// metodo numero 6
		Famigliare famigliare = new Famigliare((Giocatore) o[1], 4, false);
		famigliare.controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.Raccolto);
		if (famigliare.getGiocatore().getScomunica(0) != null)
			famigliare.getGiocatore().getScomunica(0).attivaOnAzione(null, EAzioniGioco.Raccolto, famigliare, null);
		if (famigliare.getValore() > 0)
			famigliare.getGiocatore().raccolto(famigliare.getValore());

	}

	/**
	 * Metodo per gli effetti immediati che richiedono di eseguire l'azione
	 * Produzione di valore 3
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void eseguiEffettoImmediatoProduzioneValoreTre(Object o[]) {// metodo
																		// numero
																		// 7
		Famigliare famigliare = new Famigliare((Giocatore) o[1], 3, false);
		famigliare.controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.Produzione);
		if (famigliare.getGiocatore().getScomunica(0) != null)
			famigliare.getGiocatore().getScomunica(0).attivaOnAzione(null, EAzioniGioco.Produzione, famigliare, null);
		if (famigliare.getValore() > 0)
			famigliare.getGiocatore().produzione(famigliare.getValore());

	}

	/**
	 * Metodo per gli effetti immediati che richiedono di effettuare l'azione di
	 * Produzione di valore 4
	 */
	public void eseguiEffettoImmediatoProduzioneValoreQuattro(Object o[]) {// metodo
																			// numero
																			// 8
		Famigliare famigliare = new Famigliare((Giocatore) o[1], 4, false);
		famigliare.controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.Produzione);
		if (famigliare.getGiocatore().getScomunica(0) != null)
			famigliare.getGiocatore().getScomunica(0).attivaOnAzione(null, EAzioniGioco.Produzione, famigliare, null);
		if (famigliare.getValore() > 0)
			famigliare.getGiocatore().produzione(famigliare.getValore());

	}

	/**
	 * Effetto che aggiunge al giocatore due punti vittoria per ogni carta
	 * edificio in suo possesso
	 */
	public void duePVxedificio(Object o[]) {// metodo numero 9
		Giocatore giocatore = (Giocatore) (o[1]);
		moltiplicatorePuntiVittoria(giocatore, 2, new Edificio(null, null, null, null, 0, 0));
	}

	/**
	 * Effetto che aggiunge al giocatore due punti vittoria per ogni carta
	 * personaggio in suo possesso
	 * 
	 * @param
	 */
	public void duePVxpersonaggio(Object o[]) {// metodo numero 10
		Giocatore giocatore = (Giocatore) (o[1]);
		moltiplicatorePuntiVittoria(giocatore, 2, new Personaggio(null, null, null, null, 0, 0));
	}

	/**
	 * Effetto che aggiunge al giocatore due punti vittoria per ogni carta
	 * impresa in suo possesso
	 * 
	 * @param
	 */
	public void duePVximpresa(Object o[]) {// metodo numero 11
		Giocatore giocatore = (Giocatore) (o[1]);
		moltiplicatorePuntiVittoria(giocatore, 2, new Impresa(null, null, null, null, 0, 0));
	}

	/**
	 * Effetto che aggiunge al giocatore due punti vittoria per ogni carta
	 * territorio in suo possesso
	 * 
	 * @param
	 */
	public void duePVxterritorio(Object o[]) {// metodo numero 12
		Giocatore giocatore = (Giocatore) (o[1]);
		moltiplicatorePuntiVittoria(giocatore, 2, new Territorio(null, null, null, null, 0, 0));
	}

	/**
	 * Effetto che aggiunge al giocatore un punto vittoria per ogni carta
	 * personaggio in suo possesso
	 * 
	 * @param
	 */
	public void unPVxpersonaggio(Object o[]) {// metodo numero 13
		Giocatore giocatore = (Giocatore) (o[1]);
		moltiplicatorePuntiVittoria(giocatore, 1, new Personaggio(null, null, null, null, 0, 0));
	}

	/**
	 * Effetto che aggiunge al giocatore un punto vittoria per ogni carta
	 * impresa in suo possesso
	 * 
	 * @param
	 */
	public void unPVximpresa(Object o[]) {// metodo numero 14
		Giocatore giocatore = (Giocatore) (o[1]);
		moltiplicatorePuntiVittoria(giocatore, 1, new Impresa(null, null, null, null, 0, 0));
	}

	/**
	 * Effetto che aggiunge al giocatore una moneta per ogni carta territorio in
	 * suo possesso
	 * 
	 * @param
	 */
	public void unaMonetaxterritorio(Object o[]) {// metodo numero 15
		Giocatore giocatore = (Giocatore) (o[1]);
		giocatore.getRisorse().cambiaMonete(giocatore.getPlancia().getTerritori().size());
	}

	/**
	 * Effetto che aggiunge al giocatore una moneta per ogni carta edificio in
	 * suo possesso
	 * 
	 * @param
	 */
	public void unaMonetaxedificio(Object o[]) {// metodo numero 16
		Giocatore giocatore = (Giocatore) (o[1]);
		giocatore.getRisorse().cambiaMonete(giocatore.getPlancia().getEdifici().size());
	}

	/**
	 * Effetto che aggiunge al giocatore un punto vittoria per ogni due punti
	 * militare in suo possesso
	 * 
	 * @param
	 */
	public void unPVxduePM(Object o[]) {// metodo numero 17
		Giocatore giocatore = (Giocatore) (o[1]);
		giocatore.getPunti().cambiaPuntiVittoria(giocatore.getPunti().getPuntiMilitari() / 2);
	}

	/**
	 * @param
	 */
	public void decrementaDiQuattroAzione(Object o[]) {// metodo numero 18
		cambiaValoreAzione(-4, (Famigliare) (o[3]));
	}

	/**
	 * @param
	 */
	public void decrementaDiTreAzione(Object o[]) {// metodo numero 19
		cambiaValoreAzione(-3, (Famigliare) (o[3]));
	}

	/**
	 * @param
	 */
	public void decrementaDiUnoAzione(Object o[]) {// metodo numero 20
		cambiaValoreAzione(-1, (Famigliare) (o[3]));
	}

	/**
	 * Metodo che implementa la scomunica dove il giocatore perde un punto
	 * vittoria per ogni 5 punti vittoria in suo possesso
	 * 
	 * @param
	 */
	public void perdiPvxcinquePV(Object o[]) {// metodo numero 21
		Giocatore giocatore = (Giocatore) (o[1]);
		giocatore.getPunti().cambiaPuntiVittoria(-(giocatore.getPunti().getPuntiVittoria() / 5));
	}

	/**
	 * Metodo che implementa la scomunica dove il giocatore perde un punto
	 * vittoria per ogni punto militare in suo possesso
	 * 
	 * @param
	 */
	public void perdiPvxPM(Object o[]) {// metodo numero 22
		Giocatore giocatore = (Giocatore) (o[1]);
		giocatore.getPunti().cambiaPuntiVittoria(-(giocatore.getPunti().getPuntiMilitari()));
	}

	public void scontoUnaMoneta(Object o[]) {// metodo numero 23
		// TODO:implementare
	}

	public void scontoLegnoEPietra(Object o[]) {// metodo numero 24
		// TODO:implementare
	}

	public void prendiCartaSenzaFamigliareQuattro(Object o[]) {// metodo numero
																// 25

	}

	/**
	 * Metodo che implementa la scomunica dove il giocatore perde un punto
	 * vittoria per ogni risorsa in proprio possesso
	 */
	public void perdiPVxRisorse(Object o[]) {//metodo numero 26
		Giocatore giocatore = (Giocatore) (o[1]);
		Risorsa risorse = giocatore.getRisorse();
		giocatore.getPunti().cambiaPuntiVittoria(
				-(risorse.getLegno() + risorse.getMonete() + risorse.getPietre() + risorse.getServitori()));
	}

	/**
	 * Metodo che supporta tutti gli altri metodi che danno una somma di punti
	 * vittoria in base al numero di carta possedute dal giocatore
	 * 
	 * @param
	 * @return
	 */
	public void moltiplicatorePuntiVittoria(Giocatore giocatore, int PVdaMoltiplicare, Carta cartaDaMoltiplicare) {
		if (cartaDaMoltiplicare instanceof Territorio)
			giocatore.getPunti().cambiaPuntiVittoria(PVdaMoltiplicare * giocatore.getPlancia().getTerritori().size());
		if (cartaDaMoltiplicare instanceof Personaggio)
			giocatore.getPunti().cambiaPuntiVittoria(PVdaMoltiplicare * giocatore.getPlancia().getPersonaggi().size());
		if (cartaDaMoltiplicare instanceof Edificio)
			giocatore.getPunti().cambiaPuntiVittoria(PVdaMoltiplicare * giocatore.getPlancia().getEdifici().size());
		if (cartaDaMoltiplicare instanceof Impresa)
			giocatore.getPunti().cambiaPuntiVittoria(PVdaMoltiplicare * giocatore.getPlancia().getImprese().size());
	}

	/**
	 * Metodo che supporta tutti gli effetti in cui viene cambiato il valore
	 * dell'azione
	 */
	public void cambiaValoreAzione(int variazione, Famigliare famigliare) {
		famigliare.cambiaValore(variazione);
	}
}
