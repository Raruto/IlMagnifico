package main.model;

import java.io.Serializable;
import java.util.*;

import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.exceptions.InvalidChoiceException;

/**
 * 
 */
public class SpazioAzione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7750262017347409763L;

	/**
	 * Default constructor
	 */
	public SpazioAzione() {
		this.pianoDellaTorre = new Torre[16];
		for (int i = 0; i < 16; i++) {
			this.pianoDellaTorre[i] = new Torre();
		}
		this.zonaRaccoltoRotonda = null;
		this.zonaRaccoltoOvale = new ArrayList<Famigliare>();
		this.zonaProduzioneRotonda = null;
		this.zonaProduzioneOvale = new ArrayList<Famigliare>();
		this.mercato = new Famigliare[4];
		this.palazzoDelConsiglio = new ArrayList<Famigliare>();
		this.valoreDadi = new int[3];
	}

	private Torre[] pianoDellaTorre;
	/**
	 * 
	 */
	private Famigliare zonaRaccoltoRotonda;

	/**
	 * 0 = NERO 1 = ARANCIONE 2 = BIANCO
	 */
	private int[] valoreDadi;

	/**
	 * 
	 */
	private ArrayList<Famigliare> zonaRaccoltoOvale;

	/**
	 * 
	 */
	private Famigliare zonaProduzioneRotonda;

	/**
	 * 
	 */
	private ArrayList<Famigliare> zonaProduzioneOvale;

	/**
	 * 
	 */
	private Famigliare[] mercato;

	/**
	 * 
	 */
	private ArrayList<Famigliare> palazzoDelConsiglio;

	private class Torre implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5793155106745004370L;

		/**
		 * 
		 */
		private Famigliare famigliarePianoDellaTorre;

		/**
		 * 
		 */
		private Carta cartaPianoDellaTorre;

		public Torre() {
		}

	}

	/**
	 * Restituisce true se il piano della torre indicato dalla posizione e'
	 * libero, false altrimenti
	 * 
	 * @param int
	 * @return boolean
	 * 
	 * 
	 */
	public boolean torreLibera(int posizione) {
		if (this.pianoDellaTorre[posizione].famigliarePianoDellaTorre == null)
			return true;
		else
			return false;
	}

	/**
	 * Restituisce true se la zona Raccolto rotonda e' libera, false altrimenti
	 * 
	 * @param
	 * @return boolean
	 * 
	 * 
	 */
	public boolean zonaRaccoltoRotondaLibera() {
		if (this.zonaRaccoltoRotonda == null)
			return true;
		else
			return false;
	}

	/**
	 * Restituisce true se la zona Produzione rotonda e' libera, false
	 * altrimenti
	 * 
	 * @param
	 * @return boolean
	 * 
	 */
	public boolean zonaProduzioneRotondaLibera() {
		if (this.zonaProduzioneRotonda == null)
			return true;
		else
			return false;
	}

	/**
	 * Ritorna true se la zona mercato alla posizione indicata in ingresso e'
	 * libera, false altrimenti
	 * 
	 * @param
	 * @return boolean
	 */
	public boolean zonaMercatoLibera(int posizione) {
		if (this.mercato[posizione] == null)
			return true;
		else
			return false;
	}

	/**
	 * Effettua l'effetto conseguente sul giocatore a seconda della posizione in
	 * ingresso. Se la posizione non e' valida il metodo non fa niente
	 * 
	 * @param Giocatore,
	 *            int
	 * @return
	 * 
	 * 
	 */
	public void eseguiEffettoMercato(Giocatore giocatore, int posizione,
			ESceltePrivilegioDelConsiglio[] sceltaPrivilegiConsiglio) throws InvalidChoiceException {
		if (posizione == 0)
			giocatore.getRisorse().cambiaMonete(5);
		else if (posizione == 1)
			giocatore.getRisorse().cambiaServitori(5);
		else if (posizione == 2) {
			giocatore.getRisorse().cambiaMonete(2);
			giocatore.getPunti().cambiaPuntiMilitari(3);
		} else if (posizione == 3) {
			if (sceltaPrivilegiConsiglio[0] == sceltaPrivilegiConsiglio[1])
				throw new InvalidChoiceException();
			for (int i = 0; i < sceltaPrivilegiConsiglio.length; i++) {
				if (sceltaPrivilegiConsiglio[i] == ESceltePrivilegioDelConsiglio.LegnoEPietra) {
					giocatore.getRisorse().cambiaLegno(1);
					giocatore.getRisorse().cambiaPietre(1);
				}
				if (sceltaPrivilegiConsiglio[i] == ESceltePrivilegioDelConsiglio.Monete)
					giocatore.getRisorse().cambiaMonete(2);
				if (sceltaPrivilegiConsiglio[i] == ESceltePrivilegioDelConsiglio.Servitori)
					giocatore.getRisorse().cambiaServitori(2);
				if (sceltaPrivilegiConsiglio[i] == ESceltePrivilegioDelConsiglio.PuntiMilitari)
					giocatore.getPunti().cambiaPuntiMilitari(2);
				if (sceltaPrivilegiConsiglio[i] == ESceltePrivilegioDelConsiglio.PuntoFede)
					giocatore.getPunti().cambiaPuntiFede(1);
			}

		}

	}

	/**
	 * Esegue l'effetto del Palazzo del consiglio sul giocatore passato in
	 * ingresso
	 * 
	 * @param
	 * @return
	 * 
	 * 
	 */
	public void eseguiEffettoPalazzoConsiglio(Giocatore giocatore, ESceltePrivilegioDelConsiglio scelta) {
		if (scelta == ESceltePrivilegioDelConsiglio.LegnoEPietra) {
			giocatore.getRisorse().cambiaLegno(1);
			giocatore.getRisorse().cambiaPietre(1);
		}
		if (scelta == ESceltePrivilegioDelConsiglio.Monete)
			giocatore.getRisorse().cambiaMonete(2);
		if (scelta == ESceltePrivilegioDelConsiglio.Servitori)
			giocatore.getRisorse().cambiaServitori(2);
		if (scelta == ESceltePrivilegioDelConsiglio.PuntiMilitari)
			giocatore.getPunti().cambiaPuntiMilitari(2);
		if (scelta == ESceltePrivilegioDelConsiglio.PuntoFede)
			giocatore.getPunti().cambiaPuntiFede(1);
		giocatore.getRisorse().cambiaMonete(1);
	}

	/**
	 * Dona al giocatore il bonus indicato sul tabellone allo spazio
	 * corrispondente
	 * 
	 * @param
	 * @return
	 */
	public void eseguiEffettoImmediatoTorre(Giocatore giocatore, int posizione) {
		Risorsa risorse = giocatore.getRisorse();
		Punti punti = giocatore.getPunti();
		if (posizione == 2)
			risorse.cambiaLegno(1);
		if (posizione == 3)
			risorse.cambiaLegno(2);
		if (posizione == 6)
			risorse.cambiaPietre(1);
		if (posizione == 7)
			risorse.cambiaPietre(2);
		if (posizione == 10)
			punti.cambiaPuntiMilitari(1);
		if (posizione == 11)
			punti.cambiaPuntiMilitari(2);
		if (posizione == 14)
			risorse.cambiaMonete(1);
		if (posizione == 15)
			risorse.cambiaMonete(2);
	}

	/**
	 * Restituisce il famigliare presente sul piano della torre alla posizione
	 * indicata in ingresso
	 * 
	 * @param int
	 * @return Famigliare
	 * 
	 * 
	 */
	public Famigliare getFamigliareTorre(int posizione) {
		return this.pianoDellaTorre[posizione].famigliarePianoDellaTorre;
	}

	/**
	 * Restituisce l'indirizzo della carta all'interno del piano della torre
	 * alla posizione indicata in ingresso
	 * 
	 * @param int
	 * @return Carta
	 * 
	 * 
	 */
	public Carta getCartaTorre(int posizione) {
		return this.pianoDellaTorre[posizione].cartaPianoDellaTorre;
	}

	/**
	 * Restituisce il famigliare presente nella zona di raccolto rotonda
	 * 
	 * @return Famigliare
	 * 
	 * 
	 */
	public Famigliare getZonaRaccoltoRotonda() {
		return this.zonaRaccoltoRotonda;
	}

	/**
	 * Restituisce il famigliare presente nella zona di produzione rotonda
	 * 
	 * @return Famigliare
	 * 
	 * 
	 */
	public Famigliare getZonaProduzioneRotonda() {
		return this.zonaProduzioneRotonda;
	}

	/**
	 * Restituisce l'ArrayList di famigliari presenti nella zona di raccolto
	 * ovale
	 * 
	 * @return ArrayList<Famigliare>
	 * 
	 * 
	 */
	public ArrayList<Famigliare> getZonaRaccoltoOvale() {
		return this.zonaRaccoltoOvale;
	}

	/**
	 * Restituisce l'ArrayList di famigliari presenti nella zona di produzione
	 * ovale
	 * 
	 * @return ArrayList<Famigliare>
	 * 
	 * 
	 */
	public ArrayList<Famigliare> getZonaProduzioneOvale() {
		return this.zonaProduzioneOvale;
	}

	/**
	 * Restituisce l'array di famigliari presenti nelle zone del mercato
	 * 
	 * @return Famigliare[]
	 * 
	 * 
	 */
	public Famigliare[] getMercato() {
		return this.mercato;
	}

	/**
	 * Restituisce l'ArrayList dei famigliari presenti nella zona del Palazzo
	 * Del Consiglio
	 * 
	 * @return ArrayList<Famigliare>
	 */
	public ArrayList<Famigliare> getPalazzoDelConsiglio() {
		return this.palazzoDelConsiglio;
	}

	/**
	 * Assegna al piano della torre indicato dall posizione in ingresso il
	 * famigliare passato in ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setFamigliareTorre(Famigliare famigliare, int posizione) {
		this.pianoDellaTorre[posizione].famigliarePianoDellaTorre = famigliare;
	}

	/**
	 * Assegna al piano della torre indicato dalla posizione in ingresso il
	 * famigliare passato in ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setCartaTorre(Carta carta, int posizione) {
		this.pianoDellaTorre[posizione].cartaPianoDellaTorre = carta;
	}

	/**
	 * Assegna alla zona di Raccolto rotonda il famigliare passato in ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setZonaRaccoltoRotonda(Famigliare famigliare) {
		this.zonaRaccoltoRotonda = famigliare;
	}

	/**
	 * Aggiunge nella zona di raccolto ovale un famigliare (il famigliare
	 * aggiunto sara' nell'ultima posizione dell'ArrayList
	 * 
	 * @param
	 * @return
	 */
	public void setZonaRaccoltoOvale(Famigliare famigliare) {
		this.zonaRaccoltoOvale.add(famigliare);
	}

	/**
	 * Assegna alla zona di produzione rotonda il famigliare passato in ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setZonaProduzioneRotonda(Famigliare famigliare) {
		this.zonaProduzioneRotonda = famigliare;
	}

	/**
	 * Aggiunge nella zona di produzione ovale un famigliare (sempre nell'ultima
	 * posizione)
	 * 
	 * @param
	 * @return
	 */
	public void setZonaProduzioneOvale(Famigliare famigliare) {
		this.zonaProduzioneOvale.add(famigliare);
	}

	/**
	 * Assegna alla zona Mercato nella posizione corrispondente il famigliare in
	 * ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setMercato(Famigliare famigliare, int posizione) {
		this.mercato[posizione] = famigliare;
	}

	/**
	 * Aggiunge nella zona del Palazzo Del Consiglio il famigliare in ingresso
	 * (sempre nell'ultima posizione)
	 * 
	 * @param
	 * @return
	 */
	public void setPalazzoDelConsiglio(Famigliare famigliare) {
		this.palazzoDelConsiglio.add(famigliare);
	}

	/**
	 * Metodo che toglie i famigliari che appartengono ad uno stesso giocatore
	 * dal palazzo del consiglio
	 */
	public ArrayList<Giocatore> getGiocatoriPalazzoDelConsiglio() {
		// creo un arraylist di giocatori che rappresenta l'ordine dei
		// famigliari senza duplicati
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		Giocatore giocatore = new Giocatore();
		for (int i = 0; i < this.palazzoDelConsiglio.size(); i++) {
			giocatore = palazzoDelConsiglio.get(i).getGiocatore();
			if (!giocatori.contains(giocatore))
				giocatori.add(giocatore);
		}
		return giocatori;
	}

	/**
	 * Cambia il valore al dado indicato in ingresso al valore indicato in
	 * ingresso
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void setValoreDadi(int valore, int dado) {
		this.valoreDadi[dado] = valore;
	}

	public int[] getValoreDadi() {
		return this.valoreDadi;
	}
}