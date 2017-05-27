package main.model;

import java.util.*;

/**
 * 
 */
public class Plancia {

	/**
	 * Default constructor
	 */
	public Plancia() {
		personaggi = new ArrayList<Personaggio>();
		territori = new ArrayList<Territorio>();
		edifici = new ArrayList<Edificio>();
		imprese = new ArrayList<Impresa>();
	}

	/**
	 * 
	 */
	private ArrayList<Personaggio> personaggi;

	/**
	 * 
	 */
	private ArrayList<Territorio> territori;

	/**
	 * 
	 */
	private ArrayList<Edificio> edifici;

	/**
	 * 
	 */
	private ArrayList<Impresa> imprese;

	/**
	 * @param Personaggio
	 * @return
	 */
	public void aggiungiPersonaggio(Personaggio nuovoPersonaggio) {
		personaggi.add(nuovoPersonaggio);
	}

	/**
	 * @param Territorio
	 * @return
	 */
	public void aggiungiTerritorio(Territorio nuovoTerritorio) {
		territori.add(nuovoTerritorio);
	}

	/**
	 * @param Edificio
	 * @return
	 */
	public void aggiungiEdificio(Edificio nuovoEdificio) {
		edifici.add(nuovoEdificio);
	}

	/**
	 * @param Impresa
	 * @return
	 */
	public void aggiungiImpresa(Impresa nuovaImpresa) {
		imprese.add(nuovaImpresa);
	}

}