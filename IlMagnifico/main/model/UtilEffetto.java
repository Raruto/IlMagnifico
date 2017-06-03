package main.model;

public class UtilEffetto {
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
		Giocatore giocatore = (Giocatore) (o[1]);
		int numeroIterazioni = (int) (o[2]);
		String[] scelte = new String[numeroIterazioni];
		String scelta = new String();
		boolean controllo = false;
		for (int i = 0; i < numeroIterazioni; i++) {
			// TODO: finire di implementare
			/* scelta= */
			// richiesto che il giocatore inserisca una stringa tra le possibili
			// scelte standard del privilegio del consiglio

			for (int j = 0; j < i; j++) { // controllo sulle scelte precedenti
				if (scelta == scelte[j])
					controllo = true;
			}
			if (controllo = true) {
				// viene notificato all'utente che la scelta non e' valida
				controllo = false;
				i--;// ripeto la iterazione
			} else
				scelte[i] = scelta;
		}
		for (int i = 0; i < numeroIterazioni; i++) {// anziche' chiamare i
													// metodi cambia e'
													// possibile chiamare il
													// metodo 0 di UtilEffetto
			if (scelte[i] == "monete")
				giocatore.getRisorse().cambiaMonete(2);
			if (scelte[i] == "legno&pietra") {
				giocatore.getRisorse().cambiaLegno(1);
				giocatore.getRisorse().cambiaPietre(1);
			}
			if (scelte[i] == "servitori")
				giocatore.getRisorse().cambiaServitori(2);
			if (scelte[i] == "punti militari")
				giocatore.getPunti().cambiaPuntiMilitari(2);
			if (scelte[i] == "punti fede")
				giocatore.getPunti().cambiaPuntiFede(1);
		}
	}

	/**
	 * Controlla se l'azione effetuata e' sulla torre territorio ed in caso
	 * affermativo aumenta il valore del famigliare passato in ingresso di due.
	 * I valore in ingresso sono la posizione dell'azione nelle torri e la
	 * pedina mossa
	 * 
	 * @param
	 * @return
	 */
	public void aumentaDiDueAzione(Object o[]) {// metodo numero 4
		Famigliare famigliare = (Famigliare) (o[3]);
		famigliare.cambiaValore(2);
	}

	/**
	 * Metodo per gli effetti immediati che richiedono di eseguire l'azione
	 * Raccolto. I parametri passati in ingresso sono il giocatore e il valore
	 * della azioneraccolto che si va a fare. NB:al momento della scrittura non
	 * e' ancora stato implementato il metodo che gestisce il raccolto, per cui
	 * tale metodo viene ipotizzato
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void eseguiEffettoImmediatoRaccolto(Object o[]) {// metodo numero 5
		Giocatore giocatore = (Giocatore) (o[1]);
		int valore = (int) (o[2]);
		giocatore.raccolto(valore);// metodo non ancora implementato. In ogni
									// caso verra' chiamato il metodo che
									// gestisce la raccolta del giocatore
	}

	/**
	 * Metodo per gli effetti immediati che richiedono di eseguire l'azione
	 * Produzione. I parametri passati in ingresso sono il giocatore e il valore
	 * della azione produzione che si va a fare. NB:al momento della scrittura
	 * non e' ancora stato implementato il metodo che gestisce la produzione,
	 * per cui tale metodo viene ipotizzato
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void eseguiEffettoImmediatoProduzione(Object o[]) {// metodo numero 6

		Giocatore giocatore = (Giocatore) (o[1]);
		int valore = (int) (o[2]);
		giocatore.produzione(valore);// metodo non ancora implementato. In ogni
										// caso verra' chiamato il metodo
										// che
										// gestisce la produzione del giocatore
	}

}
