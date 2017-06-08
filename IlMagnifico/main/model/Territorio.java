package main.model;

import java.util.*;

import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;

/**
 * 
 */
public class Territorio extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6586387653218061806L;

	public Territorio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioEffettoPermanente, int periodoCarta,
			ArrayList<ECostiCarte> costi, int scelteCosti, ArrayList<EEffettiPermanenti> effettiPermanenti,
			int scelteEffettiPermanenti) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioEffettoPermanente, periodoCarta,
				costi, scelteCosti, effettiPermanenti, scelteEffettiPermanenti);
	}

}