package LorenzoIlMagnifico;

public class UtilEffetto {
	/**
	 * Aggiunge risorse al giocatore passato tra i parametri. Tra i parametri
	 * sono passate le risorse da aggiungere e, se negativi, sono quelle che il
	 * giocatore deve spendere. L'array in ingresso ha come valori
	 * rispettivamente: Giocatore e vari int che simboleggiano i delta di
	 * monete, legno, pietre, servitori, punti vittoria, punti militari e punti
	 * fede. Restituisce true se l'esito del metodo è positivo, false
	 * altrimenti.
	 * 
	 * @param
	 * @return boolean
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
	 * Controlla se il giocatore può pagare le risorse per l'aquisizione di una
	 * carta. L'array in ingresso ha come valori (dalla posizione 1 in poi):
	 * Giocatore e più int rispettivamente per monete,legno,pietre,servitori
	 * indicati nel costo della carta
	 * 
	 * @param
	 * @return boolean
	 */
	public boolean cartaAcquisibileRisorse(Object o[]) {// metodo numero 1
		Giocatore giocatore = (Giocatore) (o[1]);
		int monete = (int) (o[2]);
		int legno = (int) (o[3]);
		int pietre = (int) (o[4]);
		int servitori = (int) (o[5]);
		if (giocatore.getRisorse().getMonete() >= monete && giocatore.getRisorse().getLegno() >= legno
				&& giocatore.getRisorse().getPietre() >= pietre && giocatore.getRisorse().getServitori() >= servitori)
			return true;
		else
			return false;
	}

	/**
	 * Controlla se il giocatore può pagare i punti militari per l'aquisizione
	 * di una carta. L'array in ingresso ha come valori rispettivamente:
	 * Giocatore e la soglia dei punti militari da avere per potere pagare
	 * 
	 * @param
	 * @return boolean
	 */
	public boolean cartaAcquisibilePunti(Object o[]) {// metodo numero 2
		Giocatore giocatore = (Giocatore) (o[1]);
		int puntiMilitari = (int) (o[2]);
		if (giocatore.getPunti().getPuntiMilitari() >= puntiMilitari)
			return true;
		else
			return false;
	}

	/**Proposta primitiva del metodo per l'esecuzione del privilegio del consiglio.
	 *  Tramite l'array in ingresso è possibile indicare quanti privilegi del consiglio effettuare
	 *   ed il metodo procede da solo al controllo sulle scelte. Quando è richiesta l'interazione dell'utente è segnalato con un commento
	 * */
	public void eseguiPrivilegioDelConsiglio(Object o[]){//metodo numero 3
		Giocatore giocatore=(Giocatore) (o[1]);
		int numeroIterazioni=(int) (o[2]);
		String[] scelte=new String[numeroIterazioni];
		String scelta=new String();
		boolean controllo=false;
		for(int i=0;i<numeroIterazioni;i++){
			scelta=//richiesto che il giocatore inserisca una stringa tra le possibili 
					//scelte standard del privilegio del consiglio
		for(int j=0;j<i;j++){ //controllo sulle scelte precedenti
			if(scelta==scelte[j])
				controllo=true;
		}
		if(controllo=true){
			//viene notificato all'utente che la scelta non è valida
			controllo=false;
			i--;//ripeto la iterazione
		}
		else scelte[i]=scelta;	
		}
		for(int i=0;i<numeroIterazioni;i++){//anzichè chiamare i metodi cambia è possibile chiamare il metodo 0 di UtilEffetto
			if(scelte[i]=="monete")
				giocatore.getRisorse().cambiaMonete(2);
			if(scelte[i]=="legno&pietra"){
				giocatore.getRisorse().cambiaLegno(1);
				giocatore.getRisorse().cambiaPietre(1);
			}
			if(scelte[i]=="servitori")
				giocatore.getRisorse().cambiaServitori(2);
			if(scelte[i]=="punti militari")
				giocatore.getPunti().cambiaPuntiMilitari(2);
			if(scelte[i]=="punti fede")
				giocatore.getPunti().cambiaPuntiFede(1);
		}
	}

	/**
	 * Controlla se l'azione effetuata è sulla torre territorio ed in caso
	 * affermativo aumenta il valore del famigliare passato in ingresso di due.
	 * I valore in ingresso sono la posizione dell'azione nelle torri e la
	 * pedina mossa
	 * 
	 * @param
	 * @return
	 */
	public void aumentaDiDueAzioneTerritorio(Object o[]) {// metodo numero 4
		int posizioneTorre = (int) (o[1]);
		Famigliare famigliare = (Famigliare) (o[2]);
		if (posizioneTorre >= 0 && posizioneTorre <= 3)
			famigliare.cambiaValore(2);
	}

	/**
	 * Controlla se l'azione effetuata è sulla torre edificio ed in caso
	 * affermativo aumenta il valore del famigliare passato in ingresso di due.
	 * I valore in ingresso sono la posizione dell'azione nelle torri e la
	 * pedina mossa
	 * 
	 * @param
	 * @return
	 */
	public void aumentaDiDueAzioneEdificio(Object o[]) {// metodo numero 5
		int posizioneTorre = (int) (o[1]);
		Famigliare famigliare = (Famigliare) (o[2]);
		if (posizioneTorre >= 8 && posizioneTorre <= 11)
			famigliare.cambiaValore(2);
	}

	/**
	 * Controlla se l'azione effetuata è sulla torre personaggio ed in caso
	 * affermativo aumenta il valore del famigliare passato in ingresso di due.
	 * I valore in ingresso sono la posizione dell'azione nelle torri e la
	 * pedina mossa
	 * 
	 * @param
	 * @return
	 */
	public void aumentaDiDueAzionePersonaggio(Object o[]) {// metodo numero 6
		int posizioneTorre = (int) (o[1]);
		Famigliare famigliare = (Famigliare) (o[2]);
		if (posizioneTorre >= 4 && posizioneTorre <= 7)
			famigliare.cambiaValore(2);
	}

	/**
	 * Controlla se l'azione effetuata è sulla torre impresa ed in caso
	 * affermativo aumenta il valore del famigliare passato in ingresso di due.
	 * I valore in ingresso sono la posizione dell'azione nelle torri e la
	 * pedina mossa
	 * 
	 * @param
	 * @return
	 */
	public void aumentaDiDueAzioneImpresa(Object o[]) {// metodo numero 7
		int posizioneTorre = (int) (o[1]);
		Famigliare famigliare = (Famigliare) (o[2]);
		if (posizioneTorre >= 12 && posizioneTorre <= 15)
			famigliare.cambiaValore(2);
	}

	/**
	 * Metodo per gli effetti in cui si riceve o spende risorse e si riceve uno
	 * o più privilegi del consiglio. I parametri in ingresso sono il giocatore,
	 * unità di monete, legna, pietre, servitori, punti vittoria, punti militari
	 * , punti fede ed il numero di privilegi del consiglio. Se i parametri sono
	 * negativi si procede al controllo se il giocatore può pagare le risorse.
	 * 
	 * @param
	 * @return
	 */
	public void aggiungiRisorseEPrivilegioDelConsiglio(Object o[]) {// metodo
																	// numero 8
		boolean controllo;
		Object[] parametri = new Object[3];
		controllo = aggiungiRisorse(o);
		if (controllo == true) {
			parametri[1] = o[1];
			parametri[9] = o[9];
			eseguiPrivilegioDelConsiglio(parametri);
		}
	}

	/**
	 * Metodo per gli effetti immediati che richiedono di eseguire l'azione
	 * Raccolto. I parametri passati in ingresso sono il giocatore e il valore
	 * della azioneraccolto che si va a fare. NB:al momento della scrittura non
	 * è ancora stato implementato il metodo che gestisce il raccolto, per cui
	 * tale metodo viene ipotizzato
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void eseguiEffettoImmediatoRaccolto(Object o[]) {// metodo numero 9
		Giocatore giocatore = (Giocatore) (o[1]);
		int valore = (int) (o[2]);
		giocatore.raccolto(valore);// metodo non ancora implementato. In ogni
									// caso verrà chiamato il metodo che
									// gestisce la raccolta del giocatore
	}

	/**
	 * Metodo per gli effetti immediati che richiedono di eseguire l'azione
	 * Produzione. I parametri passati in ingresso sono il giocatore e il valore
	 * della azione produzione che si va a fare. NB:al momento della scrittura
	 * non è ancora stato implementato il metodo che gestisce la produzione, per
	 * cui tale metodo viene ipotizzato
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void eseguiEffettoImmediatoProduzione(Object o[]) {// metodo numero
																// 10
		Giocatore giocatore = (Giocatore) (o[1]);
		int valore = (int) (o[2]);
		giocatore.produzione(valore);// metodo non ancora implementato. In ogni
										// caso verrà chiamato il metodo che
										// gestisce la produzione del giocatore
	}

	/**
	 * Metodo per effetti immediati dove si ricevono risorse e si effettua una
	 * azione di raccolto. I parametri passati sono rispettivamente: giocatore,
	 * unità di monete, legno, pietre, servitori, punti vittoria, punti
	 * militari, punti fede ed il valore dell'azione raccolto che si va a
	 * svolgere
	 * 
	 * @param
	 * @return
	 */
	public void aggiungiRisorseEEseguiRaccolto(Object o[]) {// metodo numero 11
		boolean controllo;
		Object[] parametri = new Object[3];
		controllo = aggiungiRisorse(o);
		if (controllo == true) {
			parametri[1] = o[1];
			parametri[2] = o[9];
			eseguiEffettoImmediatoRaccolto(parametri);
		}
	}

	/**
	 * Metodo per effetti immediati dove si ricevono risorse e si effettua una
	 * azione di produzione. I parametri passati sono rispettivamente:
	 * giocatore, unità di monete, legno, pietre, servitori, punti vittoria,
	 * punti militari, punti fede ed il valore dell'azione produzione che si va
	 * a svolgere
	 * 
	 * @param
	 * @return
	 */
	public void aggiungiRisorseEEseguiProduzione(Object o[]) {// metodo numero
																// 12
		boolean controllo;
		Object[] parametri = new Object[3];
		controllo = aggiungiRisorse(o);
		if (controllo == true) {
			parametri[1] = o[1];
			parametri[2] = o[9];
			eseguiEffettoImmediatoProduzione(parametri);
		}
	}

}
