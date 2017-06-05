package main.model;

import java.util.*;

/**
 * 
 */
public class Personaggio extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8999414251616255020L;

	public Personaggio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioeffettoPermanente, int periodoCarta) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioeffettoPermanente, periodoCarta);
	}

}