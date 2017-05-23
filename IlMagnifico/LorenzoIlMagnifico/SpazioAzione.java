
import java.util.*;

/**
 * 
 */
public class SpazioAzione {

	/**
	 * Default constructor
	 */
	public SpazioAzione() {
	}

	/**
	 * 
	 */
	private Famigliare[] torre;

	/**
	 * 
	 */
	private Carta[] carteTorre;

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

	/**
	 * @param posizione
	 * @return
	 */
	public boolean torreLibera(int posizione) {
		// TODO implement here
		return false;
	}

	/*
	 * ritorna true se la zona del raccolto rotonda è libera, false altrimenti
	 */
	public boolean zonaRaccoltoRotondaLibera() {
		if (this.zonaRaccoltoRotonda == null)
			return true;
		else
			return false;
	}

	/**
	 * @param posizione
	 * @return
	 */
	public boolean zonaProduzioneRotondaLibera() {
		if (this.zonaProduzioneRotonda == null)
			return true;
		else
			return false;
	}

	/**
	 * @param giocatore
	 * @return
	 */
	public void eseguiEffettoMercato(Giocatore giocatore) {
		// TODO implement here
		return null;
	}

	/**
	 * @param giocatore
	 * @return
	 */
	public void eseguiEffettoPalazzoConsiglio(Giocatore giocatore) {
		// TODO implement here
		return null;
	}

}