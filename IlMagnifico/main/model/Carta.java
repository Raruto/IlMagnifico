package main.model;

import java.util.*;

import main.util.game.EAzioniGiocatore;

/**
 * 
 */
public abstract class Carta {

	/**
	 * Default constructor
	 */
	public Carta(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessario, int periodo) {
		this.nome = nome;
		this.acquisizione = acquisizione;
		this.effettoImmediato = effettoImmediato;
		this.effettoPermanente = effettoPermanente;
		this.valoreNecessarioEffettoPermanente = valoreNecessario;
		this.periodoCarta = periodo;
	}

	/**
	 * 
	 */
	protected String nome;

	/**
	 * attributo aggiunto per fare funzionare i metodi
	 */
	private UtilEffetto utilEffetto = new UtilEffetto();
	/**
	 * 
	 */
	protected ArrayList<Object[]> acquisizione;

	/**
	 * 
	 */
	protected ArrayList<Object[]> effettoImmediato;

	/**
	 * 
	 * */
	protected int valoreNecessarioEffettoPermanente;

	/**
	 * Periodo associato alla carta
	 */
	protected int periodoCarta;

	/**
	 * 
	 */
	protected ArrayList<Object[]> effettoPermanente;

	/**
	 * Metodo che controlla se il giocatore ha abbastanza punti militari o
	 * risorse per potere pagare il costo della carta
	 * 
	 * @param oggetto
	 * @return
	 */
	public boolean acquisibile(Giocatore giocatore) {
		// devo controllare che
		// posso pagare o con le
		// risorse o con i punti

		boolean controlloRisorse = false;
		boolean controlloPunti = false;
		for (int i = 0; i < acquisizione.size(); i++) {
			acquisizione.get(i)[1] = giocatore;
			if ((int) acquisizione.get(i)[0] == 1)
				if (utilEffetto.cartaAcquisibileRisorse(acquisizione.get(i)))
					controlloRisorse = true;
				else if ((int) acquisizione.get(i)[0] == 2)
					if (utilEffetto.cartaAcquisibilePunti(acquisizione.get(i)))
						controlloPunti = true;
		}
		if (controlloRisorse | controlloPunti)
			// se almeno con i punti o con le risorse posso pagare, allora la
			// carta è acquisibile
			return true;
		else
			return false;
	}

	/**
	 * Metodo che attiva l'effetto immediato della carta
	 * 
	 * @param Object
	 * @return
	 */
	public void effettoImmediato(Giocatore giocatore) {
		for (int i = 0; i < effettoImmediato.size(); i++) {
			effettoImmediato.get(i)[1] = giocatore;
			if ((int) effettoImmediato.get(i)[0] == 0)
				utilEffetto.aggiungiRisorse(effettoImmediato.get(i));
			else if ((int) effettoImmediato.get(i)[0] == 3)
				utilEffetto.eseguiPrivilegioDelConsiglio(effettoImmediato.get(i));
		}
	}

	/**
	 * Metodo che attiva l'effetto permanente della carta
	 * 
	 * @param Object
	 * @return
	 */
	public void effettoPermanente(Giocatore giocatore) {
		for (int i = 0; i < effettoPermanente.size(); i++) {
			effettoPermanente.get(i)[1] = giocatore;
			if ((int) effettoPermanente.get(i)[0] == 0)
				utilEffetto.aggiungiRisorse(effettoPermanente.get(i));
			else if ((int) effettoPermanente.get(i)[0] == 3)
				utilEffetto.eseguiPrivilegioDelConsiglio(effettoImmediato.get(i));
		}
	}

	public boolean Attivabile(int valoreAzione) { // gli passo un valore, non il
													// famigliare, perchﾃｨ devo
													// potere variare il valore
													// a seconda dei bonus e
													// malus ricevuti con
													// effetti vari
		if (valoreAzione >= this.valoreNecessarioEffettoPermanente)
			return true;
		else
			return false;
	}

	/***/
	public int getPeriodoCarta() {
		return this.periodoCarta;
	}

	/**
	 * Metodo che controlla se l'effetto permanente si attiva quando il
	 * giocatore esegue un'azione raccolto
	 * 
	 * @param
	 * @return
	 */
	public void attivaOnRaccolto(Giocatore giocatore) {
		// tra i parametri delle carte in posizione 2 ci sarà l'azione a cui
		// corrisponde l'attivazione.
		// Qui controllo che il primo effetto si attivi sul raccolto e se sì lo
		// attivo, altrimenti non faccio niente
		if ((EAzioniGiocatore) (this.effettoPermanente.get(0)[2]) == EAzioniGiocatore.Raccolto)
			effettoPermanente(giocatore);
	}

	/**
	 * Metodo che controlla se l'effetto permanente si attiva quando viene
	 * eseguita un'azione produzione dal giocatore
	 * 
	 * @param
	 * @return
	 */
	public void attivaOnProduzione(Giocatore giocatore) {
		if ((EAzioniGiocatore) (this.effettoPermanente.get(0)[2]) == EAzioniGiocatore.Produzione)
			effettoPermanente(giocatore);
	}

	/***/
	public void acquisizione(Giocatore giocatore) {
		// TODO:se il giocatore può pagare in un solo modo non ci sono problemi,
		// ma se può pagare in tutti e due i modi deve potere decidere

	}

}