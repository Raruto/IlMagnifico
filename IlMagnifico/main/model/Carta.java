package main.model;

import java.util.*;

/**
 * 
 */
public abstract class Carta {

	/**
	 * Default constructor
	 */
	public Carta(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente) {
		this.nome = nome;
		this.acquisizione = acquisizione;
		this.effettoImmediato = effettoImmediato;
		this.effettoPermanente = effettoPermanente;
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
	 */
	protected ArrayList<Object[]> effettoPermanente;

	/**
	 * @param oggetto
	 * @return
	 */
	public boolean acquisibile(Giocatore giocatore) {

		for (int i = 0; i < acquisizione.size(); i++) {
			acquisizione.get(i)[1] = giocatore;
			if ((int) acquisizione.get(i)[0] == 1)
				if (!utilEffetto.cartaAcquisibileRisorse(acquisizione.get(i)))
					return false;
				else if ((int) acquisizione.get(i)[0] == 2)
					if (!utilEffetto.cartaAcquisibilePunti(acquisizione.get(i)))
						return false;
		}
		return true;
	}

	/**
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

}