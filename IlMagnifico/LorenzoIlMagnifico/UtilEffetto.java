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
		int[] risorseGiocatore = { giocatore.getRisorse().getMonete(), giocatore.getRisorse().getLegno,
				giocatore.getRisorse().getPietre(), giocatore.getRisorse().getServitori,
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
	public boolean cartaAquisibileRisorse(Object o[]) {// metodo numero 1
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
	public boolean cartaAquisibilePunti(Object o[]) {// metodo numero 2
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
	public void eseguiPrivilegioDelConsiglio(Object o[]){
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
}
