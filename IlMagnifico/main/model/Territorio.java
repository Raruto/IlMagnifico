package main.model;

import java.util.*;

/**
 * 
 */
public class Territorio extends Carta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6586387653218061806L;

	public Territorio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioEffettoPermanente, int periodoCarta) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioEffettoPermanente, periodoCarta);
	}

}