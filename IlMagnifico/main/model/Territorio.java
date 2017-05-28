package main.model;

import java.util.*;

/**
 * 
 */
public class Territorio extends Carta {

	public Territorio(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessarioEffettoPermanente) {
		super(nome, acquisizione, effettoImmediato, effettoPermanente, valoreNecessarioEffettoPermanente);
	}

}