package main.model;

import java.util.*;

import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;

/**
 * 
 */
public class Personaggio extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8999414251616255020L;

	public Personaggio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioeffettoPermanente, int periodoCarta,
			ECostiCarte[] costi, int scelteCosti, EEffettiPermanenti[] effettiPermanenti, int scelteEffettiPermanenti) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioeffettoPermanente, periodoCarta,
				costi, scelteCosti, effettiPermanenti, scelteEffettiPermanenti);
	}

}