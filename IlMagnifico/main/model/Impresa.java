package main.model;

import java.util.*;

/**
 * 
 */
public class Impresa extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320399366612792715L;

	public Impresa(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioeffettoPermanente, int periodoCarta) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioeffettoPermanente, periodoCarta);
	}
}