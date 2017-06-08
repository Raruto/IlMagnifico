package main.model;

import java.util.*;

import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;

/**
 * 
 */
public class Edificio extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1278971448848435026L;

	public Edificio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioeffettoPermanente, int periodoCarta,
			ECostiCarte[] costi, int scelteCosti, EEffettiPermanenti[] effettiPermanenti, int scelteEffettiPermanenti) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioeffettoPermanente, periodoCarta,
				costi, scelteCosti, effettiPermanenti, scelteEffettiPermanenti);
	}

}