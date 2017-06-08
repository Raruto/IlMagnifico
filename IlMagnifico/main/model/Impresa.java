package main.model;

import java.util.*;

import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;

/**
 * 
 */
public class Impresa extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320399366612792715L;

	public Impresa(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioeffettoPermanente, int periodoCarta,
			ArrayList<ECostiCarte> costi, int scelteCosti, ArrayList<EEffettiPermanenti> effettiPermanenti,
			int scelteEffettiPermanenti) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioeffettoPermanente, periodoCarta,
				costi, scelteCosti, effettiPermanenti, scelteEffettiPermanenti);
	}
}