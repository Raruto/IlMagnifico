package main.model;

import java.util.*;

/**
 * 
 */
public class Famigliare {

	public Famigliare(Giocatore giocatoreAppartenenza, int valore, boolean neutro) {
		this.giocatoreAppartenenza = giocatoreAppartenenza;
		this.valore = valore;
		this.neutro = neutro;
		this.posizionato = false;
	}

	/**
	 * ?Eil riferimento al giocatore proprietario del famigliare
	 */
	private Giocatore giocatoreAppartenenza;

	/**
	 * ?Eil valore del famigliare
	 */
	private int valore;

	private boolean posizionato;

	/**
	 * se ?Etrue allora ?Eil famigliare neutro, se false non ?Eneutro
	 */
	private boolean neutro;

	/**
	 * determina la variazione del valore del famigliare
	 * 
	 * @param int
	 * @return
	 */
	public void cambiaValore(int variazione) {
		if (valore + variazione < 0)
			return;
		valore += variazione;
	}

	/**
	 * @param
	 * @return
	 */
	public Giocatore getGiocatore() {
		return this.giocatoreAppartenenza;
	}

	/**
	 * Metodo che effettua lo spostamento se la carta è acquisibile e se il
	 * valore del famigliare è sufficiente. Restituuisce true se va a buon fine,
	 * false altrimenti
	 * 
	 * @param
	 * @return
	 */
	public boolean eseguiSpostamentoTorre(int posizione) {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.torreLibera(posizione)))
			return false;

		if (posizione % 4 == 0 && valore < 1)
			// controlla alternativamente le prime ,le seconde, terze e quarte
			// posizioni e controlla che il valore della pedina sia abbastanza
			// // grande
			return false;
		else if ((posizione) % 4 == 1 && valore < 3)
			return false;
		else if ((posizione) % 4 == 2 && valore < 5)
			return false;
		else if ((posizione) % 4 == 3 && valore < 7)
			return false;

		Carta cartaTorre = spazioAzione.getCartaTorre(posizione);

		if (!cartaTorre.acquisibile(giocatoreAppartenenza))
			return false;
		else { // Quando acquisto una carta, devo pagarla. Bisognerebbe scrivere
				// un metodo

			// public void acquisizione(Giocatore giocatore) all'interno di
			// carta che attivo nel caso in cui acquisibile
			// restituisca true

			if ((cartaTorre instanceof Personaggio)
					&& (giocatoreAppartenenza.getPlancia().getPersonaggi().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getPersonaggi().add((Personaggio) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else if ((cartaTorre instanceof Territorio)
					&& (giocatoreAppartenenza.getPlancia().getTerritori().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getTerritori().add((Territorio) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else if ((cartaTorre instanceof Edificio)
					&& (giocatoreAppartenenza.getPlancia().getEdifici().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getEdifici().add((Edificio) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else if ((cartaTorre instanceof Impresa)
					&& (giocatoreAppartenenza.getPlancia().getImprese().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getImprese().add((Impresa) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else
				return false;
		}

		cartaTorre.effettoImmediato(giocatoreAppartenenza);

		spazioAzione.setFamigliareTorre(this, posizione);
		return true;
	}

	/**
	 * Metodo che effettua lo spostamento sulla zona del raccolto rotonda se le
	 * condizioni sono rispettate. Restituisce true se va a buon fine, false se
	 * le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoRaccoltoRotondo() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.zonaRaccoltoRotondaLibera()))
			return false;
		if (valore < 1)
			return false;

		for (int i = 0; i < giocatoreAppartenenza.getPlancia().getTerritori().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getTerritori().get(i).effettoPermanente(giocatoreAppartenenza);
		}

		spazioAzione.setZonaRaccoltoRotonda(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento sulla zona di raccolto ovale se le
	 * condizioni sono rispettate. Restituisce true se va a buon fine, flase se
	 * le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoRaccoltoOvale() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (valore - 3 < 1)
			return false;

		for (int i = 0; i < spazioAzione.getZonaRaccoltoOvale().size(); i++) {
			if ((spazioAzione.getZonaRaccoltoOvale().get(i).getGiocatore() == giocatoreAppartenenza)
					&& (spazioAzione.getZonaRaccoltoOvale().get(i).getNeutralita() == false))
				return false;
		}

		valore -= 3;

		for (int i = 0; i < giocatoreAppartenenza.getPlancia().getTerritori().size(); i++) {
			giocatoreAppartenenza.getPlancia().getTerritori().get(i).effettoPermanente(giocatoreAppartenenza);
		}

		spazioAzione.getZonaRaccoltoOvale().add(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento del famigliare sulla zona di produzione
	 * rotonda se le condizioni sono rispettate. Restituisce true se va a buon
	 * fine, false se le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoProduzioneRotondo() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.zonaProduzioneRotondaLibera()))
			return false;
		if (valore < 1)
			return false;

		for (int i = 0; i < giocatoreAppartenenza.getPlancia().getEdifici().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getEdifici().get(i).effettoPermanente(giocatoreAppartenenza);
		}

		spazioAzione.setZonaProduzioneRotonda(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento nella zona di produzione ovale Se le
	 * condizioni sono rispettate. Restituisce true se va a buon fine, false se
	 * le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoProduzioneOvale() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (valore - 3 < 1)
			return false;

		for (int i = 0; i < spazioAzione.getZonaProduzioneOvale().size(); i++) {
			if ((spazioAzione.getZonaProduzioneOvale().get(i).getGiocatore() == giocatoreAppartenenza)
					&& (spazioAzione.getZonaProduzioneOvale().get(i).getNeutralita() == false))
				return false;
		}

		valore -= 3;
		for (int i = 0; i < giocatoreAppartenenza.getPlancia().getEdifici().size(); i++) {
			giocatoreAppartenenza.getPlancia().getEdifici().get(i).effettoPermanente(giocatoreAppartenenza);
		}

		spazioAzione.getZonaProduzioneOvale().add(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento nella zona del mercato se le condizioni
	 * sono rispettate. Restituisce true se va a buon fine, false altrimenti
	 * 
	 * @param
	 * @return
	 */
	public boolean eseguiSpostamentoMercato(int posizione) {
		if (posizione < 0 || posizione > 3)
			return false;
		if (valore < 1)
			return false;
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		if (!spazioAzione.zonaMercatoLibera(posizione))
			return false;
		spazioAzione.getMercato()[posizione] = this;
		spazioAzione.eseguiEffettoMercato(giocatoreAppartenenza, posizione);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento nella zona del palazzo del consiglio se
	 * le condizioni sono rispettate. Restituisce true se va a buon fine, false
	 * altrimenti
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoPalazzoConsiglio() {

		if (valore < 1)
			return false;

		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		spazioAzione.setPalazzoDelConsiglio(this);
		spazioAzione.eseguiEffettoPalazzoConsiglio(giocatoreAppartenenza);
		return true;
	}

	public boolean getNeutralita() {
		return this.neutro;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public boolean getPosizionato() {
		return this.posizionato;
	}
}