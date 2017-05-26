package LorenzoIlMagnifico;

import java.util.*;

/**
 * 
 */
public class Famigliare {

	public Famigliare(Giocatore giocatoreAppartenenza, int valore, boolean neutro) {
		this.giocatoreAppartenenza = giocatoreAppartenenza;
		this.valore = valore;
		this.neutro = neutro;
	}

	/**
	 * ・il riferimento al giocatore proprietario del famigliare
	 */
	private Giocatore giocatoreAppartenenza;

	/**
	 * ・il valore del famigliare
	 */
	private int valore;

	/**
	 * se ・true allora ・il famigliare neutro, se false non ・neutro
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
	 * 
	 * @return
	 */
	public void eseguiSpostamentoTorre(int posizione) {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		if (!spazioAzione.torreLibera(i))
			return;

		if (i % 4 == 0 && valore < 1)
			return;
		else if ((i - 1) % 4 == 0 && valore < 3)
			return;
		else if ((i - 2) % 4 == 0 && valore < 5)
			return;
		else if ((i - 3) % 4 == 0 && valore < 7)
			return;

		Carta cartaTorre = spazioAzione.getPianoDellaTorre()[i].getCarta();
		if (!cartaTorre.acquisibile(giocatoreAppartenenza))
			return;
		else {
			// Quando acquisto una carta, devo pagarla. bisognerebbe scrivere un
			// metodo public void acquisizione(Giocatore giocatore)
			// all'interno di carta che attivo nel caso in cui acquisibile
			// restituisca true
		}

		// Devo ancora programmare il fatto che se ha gi・sei carte territorio
		// non pu・acquisirne altre di tipo territorio
		// Devo ancora programmare il fatto che se ha gi・sei carte edificio non
		// pu・acquisirne altre di tipo edificio

		if (cartaTorre instanceof Personaggio)
			giocatoreAppartenenza.getPlancia().getPersonaggi().add((Personaggio) cartaTorre);
		else if (cartaTorre instanceof Territorio)
			giocatoreAppartenenza.getPlancia().getTerritori().add((Territorio) cartaTorre);
		else if (cartaTorre instanceof Edificio)
			giocatoreAppartenenza.getPlancia().getEdifici().add((Edificio) cartaTorre);
		else if (cartaTorre instanceof Impresa)
			giocatoreAppartenenza.getPlancia().getImprese().add((Impresa) cartaTorre);

		cartaTorre.effettoImmediato(giocatoreAppartenenza);
		cartaTorre.effettoPermanente(giocatoreAppartenenza); // ・giusto che
																// venga
																// eseguito qui
																// l'effetto
																// permanente?
																// (cio・subito
																// dopo
																// l'acquisizione
																// della carta?)

		spazioAzione.getPianoDellaTorre()[i].setFamigliare(this);
	}

	/**
	 * Se la zona raccolto rotonda non ・libera oppure se il valore del
	 * famigliare ・inferiore ad 1 oppure se c'・gi・un altro famigliare dello
	 * stesso giocatore all'interno della zona raccolto, non ・possibile
	 * effettuare lo spostamento. In caso contrario eseguo l'effetto permanente
	 * della carte territorio se ci sono abbastanza punti militari. Pongo infine
	 * la zona raccolto rotonda uguale al famigliare (che quindi va ad
	 * occuparla)
	 * 
	 * @return
	 */
	public void eseguiSpostamentoRaccoltoRotondo() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		if (!spazioAzione.getZonaRaccoltoRotondaLibera())
			return;
		if (valore < 1)
			return;
		for (int i = 0; i < spazioAzione.getZonaRaccoltoOvale().size(); i++) {
			if (spazioAzione.getZonaRaccoltoOvale().get(i).getGiocatoreAppartenenza() == giocatoreAppartenenza)
				return;
		}

		// per attivare le carte territorio ・necessario oltre ai punti militari
		// anche un certo valore del dado del famigliare sullo spazio azione. Si
		// potrebbe creare un metodo public boolean attivabile(Famigliare
		// famigliare) all'interno di Territorio
		int puntiMilitari = giocatoreAppartenenza.getPunti().getPuntiMilitari();
		for (int i = 0; i < giocatoreAppartenenza.getPlancia().getTerritori().size(); i++) {
			if (i == 0)
				giocatoreAppartenenza.getPlancia().getTerritori().get(0).effettoPermanente(giocatoreAppartenenza);
			if (i == 1)
				giocatoreAppartenenza.getPlancia().getTerritori().get(1).effettoPermanente(giocatoreAppartenenza);
			if (i == 2 && puntiMilitari >= 3)
				giocatoreAppartenenza.getPlancia().getTerritori().get(2).effettoPermanente(giocatoreAppartenenza);
			if (i == 3 && puntiMilitari >= 7)
				giocatoreAppartenenza.getPlancia().getTerritori().get(3).effettoPermanente(giocatoreAppartenenza);
			if (i == 4 && puntiMilitari >= 12)
				giocatoreAppartenenza.getPlancia().getTerritori().get(4).effettoPermanente(giocatoreAppartenenza);
			if (i == 5 && puntiMilitari >= 18)
				giocatoreAppartenenza.getPlancia().getTerritori().get(5).effettoPermanente(giocatoreAppartenenza);
		}
		spazioAzione.setZonaRaccoltoRotonda(this);
	}

	/**
	 * Se il famigliare ha un valore diminuito di 3 inferiore a 1 oppure se
	 * nella zona raccolto esiste un altro famigliare dello stesso giocatore,
	 * allora non ・ possibile effettuare lo spostamento, in caso contrario il
	 * valore del famigliare viene effettivamente diminuito di 3, attiva
	 * l'effetto permanente delle carte territorio in base ai punti militari in
	 * suo possesso, viene aggiunto alla zona raccolto ovale
	 * 
	 * @return
	 */
	public void eseguiSpostamentoRaccoltoOvale() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		if (valore - 3 < 1)
			return;
		if (!spazioAzione.zonaRaccoltoRotondaLibera()) {
			if (spazioAzione.getZonaRaccoltoRotonda().getGiocatoreAppartenenza() == giocatoreAppartenenza)
				return;
		}
		for (int i = 0; i < spazioAzione.getZonaRaccoltoOvale().size(); i++) {
			if (spazioAzione.getZonaRaccoltoOvale().get(i).getGiocatoreAppartenenza() == giocatoreAppartenenza)
				return;
		}

		valore -= 3;

		// per attivare le carte territorio ・necessario oltre ai punti militari
		// anche un certo valore del dado del famigliare sullo spazio azione. Si
		// potrebbe creare un metodo public boolean attivabile(Famigliare
		// famigliare) all'interno di Territorio
		int puntiMilitari = giocatoreAppartenenza.getPunti().getPuntiMilitari();
		for (int i = 0; i < giocatoreAppartenenza.getPlancia().getTerritori().size(); i++) {
			if (i == 0)
				giocatoreAppartenenza.getPlancia().getTerritori().get(0).effettoPermanente(giocatoreAppartenenza);
			if (i == 1)
				giocatoreAppartenenza.getPlancia().getTerritori().get(1).effettoPermanente(giocatoreAppartenenza);
			if (i == 2 && puntiMilitari >= 3)
				giocatoreAppartenenza.getPlancia().getTerritori().get(2).effettoPermanente(giocatoreAppartenenza);
			if (i == 3 && puntiMilitari >= 7)
				giocatoreAppartenenza.getPlancia().getTerritori().get(3).effettoPermanente(giocatoreAppartenenza);
			if (i == 4 && puntiMilitari >= 12)
				giocatoreAppartenenza.getPlancia().getTerritori().get(4).effettoPermanente(giocatoreAppartenenza);
			if (i == 5 && puntiMilitari >= 18)
				giocatoreAppartenenza.getPlancia().getTerritori().get(5).effettoPermanente(giocatoreAppartenenza);
		}
		spazioAzione.setZonaRaccoltoOvale.add(this);

	}

	/**
	 * @return
	 */
	public void eseguiSpostamentoProduzioneRotondo() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void eseguiSpostamentoProduzioneOvale() {
		// TODO implement here
		return null;
	}

	/**
	 * se il valore ・inferiore ad 1 oppure se quella posizione del mercato ・gi・
	 * occupata, allora non ・possibile effettuare lo spostamento. In caso
	 * contrario effettuo lo spostamento ed eseguo sul giocatore l'effetto del
	 * mercato
	 * 
	 * @return
	 */
	public void eseguiSpostamentoMercato(int i) {
		if (i < 0 || i > 3)
			return;
		if (valore < 1)
			return;
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		if (!spazioAzione.zonaMercatoLibera(i))
			return;
		spazioAzione.getMercato()[i] = this;
		spazioAzione.eseguiEffettoMercato(giocatoreAppartenenza, i);
	}

	/**
	 * se il valore ・inferiore a 1 allora non ・possibile effettuare lo
	 * spostamento. In caso contrario aggiungo il famigliare al palazzo del
	 * consiglio ed eseguo l'effetto del palazzo del consiglio sul giocatore
	 * 
	 * @return
	 */
	public void eseguiSpostamentoPalazzoConsiglio() {
		if (valore < 1)
			return;
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		spazioAzione.getPalazzoDelConsiglio().add(this);
		spazioAzione.eseguiEffettoPalazzoConsiglio(giocatoreAppartenenza);
	}

}