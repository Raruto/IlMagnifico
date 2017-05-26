package LorenzoIlMagnifico;

import java.util.*;

/**
 * 
 */
public class Partita {

	/**
	 * Default constructor
	 */
	public Partita() {
	}

	/**
	 * 
	 */
	private ArrayList<Carta> mazzo;

	/**
	 * 
	 */
	private ArrayList<Giocatore> giocatori;

	/**
	 * 
	 */
	private SpazioAzione spazioAzione;

	/**
	 * 
	 */
	private Scomunica[] scomuniche;

	/**
	 * 
	 */
	private int turno;

	/**
	 * Metodo che mischia il mazzo senza distinguere le carte per periodo e per
	 * tipo. //Il riconoscere le carte da prendere per il relativo periodo e per
	 * la relativa torre vengono lasciate al metodo posizionaCartaSuTorre
	 * 
	 * @return
	 */
	public void mescolaMazzo() {
		Collections.shuffle(mazzo);
	}

	/**
	 * Metodo per riempire l'array delle tessere scomunica
	 * 
	 * @return
	 */
	public void scegliScomunica() {
		for (int i = 0; i < 3; i++) {
			int periodo = i + 1;
			int indice;
			ArrayList<Scomunica> temporaneo = new ArrayList<Scomunica>();
			Random random = new Random();
			// da file o database si prendono le carte scomunica del periodo
			// corrispondente (indicato dalla variabile periodo). Con tali carte
			// riempio un ArrayList temporaneo. Genero un numero casuale che sia
			// compreso tra 0 e il numero di elementi nell'ArrayList tramite il
			// metodo random.nextInt(). Si va poi a prendere la scomunica
			// corrispondente all'indice generato casualmente.
			// NB:0<=indice<temporaneo.size(), non ci deovrebbero essere
			// problemi
			indice = random.nextInt(temporaneo.size());
			this.scomuniche[i] = temporaneo.get(indice);
		}
	}

	/**
	 * @return
	 */
	public void inizializzaGiocatori() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void scegliOrdine() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void posizionaCarteSuTorre() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void turnoGiocatore() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void eseguiRapportoVaticano() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void resetPerNuovoTurno() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public ArrayList<Giocatore> calcolaClassificaFinale() {
		// TODO implement here
		return null;
	}

}