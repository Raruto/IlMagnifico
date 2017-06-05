package main.model;

import java.io.Serializable;
import java.util.*;

/**
 * 
 */
public class Plancia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187091442351902574L;

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

	public ArrayList<Personaggio> getPersonaggi() {
		return personaggi;
	}

	public ArrayList<Territorio> getTerritori() {
		return territori;
	}

	public ArrayList<Edificio> getEdifici() {
		return edifici;
	}

	public ArrayList<Impresa> getImprese() {
		return this.imprese;
	}

}