package main.model;

import java.util.*;

/**
 * 
 */
public class Edificio extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1278971448848435026L;

	public Edificio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioeffettoPermanente, int periodoCarta) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioeffettoPermanente, periodoCarta);
	}

}