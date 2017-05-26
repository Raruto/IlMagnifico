package LorenzoIlMagnifico;

import java.util.*;

/**
 * 
 */
public class SpazioAzione {

	/**
	 * Default constructor
	 */
	public SpazioAzione() {
		this.pianoDellaTorre = new Torre[16];
		this.zonaRaccoltoRotonda = new Famigliare();
		this.zonaRaccoltoOvale = new ArrayList<Famigliare>();
		this.zonaProduzioneRotonda = new Famigliare();
		this.zonaProduzioneOvale = new ArrayList<Famigliare>();
		this.mercato = new Famigliare[4];
		this.palazzoDelConsiglio = new ArrayList<Famigliare>();
	}

	private Torre[] pianoDellaTorre;
	/**
	 * 
	 */
	private Famigliare zonaRaccoltoRotonda;

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

	private class Torre {
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
	 * Restituisce true se il piano della torre indicato dalla posizione è
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
	 * Restituisce true se la zona Raccolto rotonda è libera, false altrimenti
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
	 * Restituisce true se la zona Produzione rotonda è libera, false altrimenti
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
	 * Ritorna true se la zona mercato alla posizione indicata in ingresso è
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

	/**Effettua l'effetto conseguente sul giocatore a seconda della posizione in ingresso. Se la posizione non è valida il metodo non fa niente
	 * 
	 * @param Giocatore, int
	 * @return
	 * 
	 * 
	 */
	public void eseguiEffettoMercato(Giocatore giocatore,int posizione) {
				if(posizione==0)
					giocatore.getRisorse().cambiaMonete(5);
				else if(posizione==1)
					giocatore.getRisorse().cambiaServitori(5);
				else if(posizione==2){
					giocatore.getRisorse().cambiaMonete(2);
					giocatore.getPunti().cambiaPuntiMilitari(3);
				}
				else if(posizione==3){
					//qua ci vuole il metodo che permette al giocatore di ottenere un doppio privilegio del consiglio
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
	public void eseguiEffettoPalazzoConsiglio(Giocatore giocatore) {
		// metodo per fare scegliere al giocatore l'effetto del privilegio del
		// consiglio
		return;
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
	 * aggiunto sarà nell'ultima posizione dell'ArrayList
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
	public void eliminaRicorrenzePalazzoDelConsiglio() {
		// elimino le ricorrenze nell'arraylist del Palazzo del consiglio e
		// dall'arraylist dei giocatori, poi concateno
		Famigliare famigliare = new Famigliare(null, 0, false);
		int j = 0;
		for (int i = 0; i < this.palazzoDelConsiglio.size(); i++) {
			// con tale metodo in teoria
			// si tolgono le ricorrenze
			// di famigliari dello stesso giocatore nel palazzo del consiglio
			j = i + 1;
			famigliare = this.palazzoDelConsiglio.get(i);
			while ((this.palazzoDelConsiglio.get(j).getGiocatore() != famigliare.getGiocatore())
					&& (j < this.palazzoDelConsiglio.size()))
				j++;
			this.palazzoDelConsiglio.remove(j);
			eliminaRicorrenzePalazzoDelConsiglio();
			break;
		}
	}
}