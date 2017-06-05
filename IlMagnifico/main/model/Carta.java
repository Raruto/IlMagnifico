package main.model;

import java.util.*;

import main.model.enums.EAzioniGioco;

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
		this.utilEffetto = new UtilEffetto();
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
				if (utilEffetto.cartaAcquisibileRisorse(acquisizione.get(i))) {
					controlloRisorse = true;
					acquisizione.get(i)[0] = 0;
				} else if ((int) acquisizione.get(i)[0] == 2)
					if (utilEffetto.cartaAcquisibilePunti(acquisizione.get(i))) {
						controlloPunti = true;
						acquisizione.get(i)[0] = 0;
					}
		}
		if (controlloRisorse | controlloPunti)
			// se almeno con i punti o con le risorse posso pagare, allora la
			// carta e' acquisibile
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
	public void effettoImmediato(Giocatore giocatore, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < effettoImmediato.size(); i++) {
			effettoImmediato.get(i)[1] = giocatore;
			effettoImmediato.get(i)[3] = famigliare;
			effettoImmediato.get(i)[4] = carta;
			/*
			 * if ((int) effettoImmediato.get(i)[0] == 0)
			 * utilEffetto.aggiungiRisorse(effettoImmediato.get(i)); else if
			 * ((int) effettoImmediato.get(i)[0] == 3)
			 * utilEffetto.eseguiPrivilegioDelConsiglio(effettoImmediato.get(i))
			 * ;
			 */
			attivaEffettoSingolo(this.effettoImmediato.get(i));
		}
	}

	/**
	 * Metodo che attiva l'effetto permanente della carta
	 * 
	 * @param Object
	 * @return
	 */
	public void effettoPermanente(Giocatore giocatore, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < effettoPermanente.size(); i++) {
			effettoPermanente.get(i)[1] = giocatore;
			effettoPermanente.get(i)[3] = famigliare;
			effettoPermanente.get(i)[4] = carta;
			attivaEffettoSingolo(effettoPermanente.get(i));
			/*
			 * if ((int) effettoPermanente.get(i)[0] == 0)
			 * utilEffetto.aggiungiRisorse(effettoPermanente.get(i)); else if
			 * ((int) effettoPermanente.get(i)[0] == 3)
			 * utilEffetto.eseguiPrivilegioDelConsiglio(effettoImmediato.get(i))
			 * ;
			 */
		}
	}

	public boolean Attivabile(int valoreAzione) {
		// gli passo un valore, non il
		// famigliare, perche' devo
		// potere variare il valore
		// a seconda dei bonus e
		// malus ricevuti con
		// effetti vari
		if (valoreAzione >= this.valoreNecessarioEffettoPermanente)
			return true;
		else
			return false;
	}

	/**
	 * Metodo che restituisce il periodo della carta
	 * 
	 * @param
	 * @return
	 */
	public int getPeriodoCarta() {
		return this.periodoCarta;
	}

	/**
	 * Metodo che effettua l'acquisto da parte di un giocatore di una carta
	 * 
	 */
	public void acquisizione(Giocatore giocatore) {
		// TODO:se il giocatore puo' pagare in un solo modo non ci sono
		// problemi,
		// ma se puo' pagare in tutti e due i modi deve potere decidere
		for (int i = 0; i < acquisizione.size(); i++) {
			if ((int) (acquisizione.get(i)[0]) == 0) {
				utilEffetto.aggiungiRisorse(acquisizione.get(i));
				return;
			}
		}
	}

	/**
	 * Metodo che verifica che un effetto possa essere attivato e lo attiva
	 * 
	 * @param
	 * @return
	 */
	public void attivaOnAzione(Giocatore giocatore, EAzioniGioco azione, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < this.effettoPermanente.size(); i++) {
			this.effettoPermanente.get(i)[1] = giocatore;
			this.effettoPermanente.get(i)[3] = famigliare;
			this.effettoPermanente.get(i)[4] = carta;
			if ((EAzioniGioco) (this.effettoPermanente.get(i)[2]) == azione)
				attivaEffettoSingolo(this.effettoPermanente.get(i));
		}
	}

	/**
	 * Metodo che, dato un array di Object in ingresso, va ad eseguire l'effetto
	 * corrispondente con i parametri codificati nell'array di Object
	 */
	public void attivaEffettoSingolo(Object[] parametri) {
		if ((int) parametri[0] == 0)
			utilEffetto.aggiungiRisorse(parametri);
		else if ((int) parametri[0] == 3)
			utilEffetto.eseguiPrivilegioDelConsiglio(parametri);
		// TODO:finire di elencare i metodi possibili
	}

	/**
	 * Metodo che controlla che uno degli effetti permanenti si attivi sul bonus
	 * percepito dalla azione della torre. Ritorna true se tale effetto esiste,
	 * false altrimenti
	 * 
	 * @param
	 * @return
	 * 
	 */
	public boolean attivaOnEffettoTorre() {
		for (int i = 0; i < this.effettoPermanente.size(); i++) {
			if ((EAzioniGioco) (this.effettoPermanente.get(i)[2]) == EAzioniGioco.EffettoTorre)
				return true;
		}
		return false;
	}

	/**
	 * Metodo che restituisce il valore dell'azione necessario per attivare
	 * l'effetto permanente della carta, se c'e'
	 * 
	 * @return
	 */
	public int getValoreNecessarioEffettoPermanente() {
		return this.valoreNecessarioEffettoPermanente;
	}
}