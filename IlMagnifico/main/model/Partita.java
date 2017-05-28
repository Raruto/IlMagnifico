package main.model;

import java.util.*;

import main.network.server.RemotePlayer;
import main.network.server.game.Room;
import main.network.server.game.UpdateStats;
import main.util.EAzioniGiocatore;

/**
 * 
 */
public class Partita {

	/**
	 * 
	 */
	protected ArrayList<Carta> mazzo;

	/**
	 * 
	 */
	protected ArrayList<Giocatore> giocatori;

	/**
	 * 
	 */
	protected SpazioAzione spazioAzione;

	/**
	 * 
	 */
	protected Scomunica[] scomuniche;

	/**
	 * 
	 */
	protected int turno;

	/**
	 * Costruttore.
	 */
	public Partita() {
		giocatori = new ArrayList<Giocatore>();
	}

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
		int contatoreMonete = 4;
		// ci vuole un modo per riempire l'array dei giocatori con le
		// informazioni reperite dalla parte di comunicazione
		Collections.shuffle(giocatori);// generazione casuale dell'ordine del
										// turno di gioco, ipotizzando che
										// l'array sia già riempito

		for (int i = 0; i < this.giocatori.size(); i++) {// inizializzo le
															// riserve dei
															// giocatori
			this.giocatori.get(i).getRisorse().cambiaLegno(2);
			this.giocatori.get(i).getRisorse().cambiaPietre(2);
			this.giocatori.get(i).getRisorse().cambiaServitori(3);
			this.giocatori.get(i).getRisorse().cambiaMonete(contatoreMonete + 1);
		}

	}

	/**
	 * @return
	 */
	public void scegliOrdine() {

		// elimino le ricorrenze nell'arraylist del Palazzo del consiglio e
		// dall'arraylist dei giocatori, poi concateno
		this.spazioAzione.eliminaRicorrenzePalazzoDelConsiglio();// ancora da
																	// finire

	}

	/**
	 * @return
	 */
	public void posizionaCarteSuTorre() {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public void turnoGiocatore() {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public void eseguiRapportoVaticano() {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public void resetPerNuovoTurno() {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public ArrayList<Giocatore> calcolaClassificaFinale() {
		// TODO implement here
		return null;
	}

	/**
	 * @return
	 */
	public void lanciaDadi() {
		// TODO implement here
		return;
	}
}