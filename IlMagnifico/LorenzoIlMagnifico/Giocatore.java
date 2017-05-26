package LorenzoIlMagnifico;

import java.util.*;

/**
 * 
 */
public class Giocatore {

	/**
	 * Default constructor
	 */
	public Giocatore() {
	}

	/**
	 * 
	 */
	private String nome;

	/**
	 * 
	 */
	private String colore;

	/**
	 * 
	 */
	private Famigliare[] famiglia;

	/**
	 * 
	 */
	private Risorsa risorse;

	/**
	 * 
	 */
	private Plancia plancia;

	/**
	 * 
	 */
	private Punti punti;

	/**
	 * 
	 */
	private Scomunica[] scomuniche;

	/**
	 * @return
	 */
	public void eseguiEffettoScomunica() {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public void pagaServitore() {
		// TODO implement here
		return;
	}

	public Risorsa getRisorse() {
		return this.risorse;
	}

	public Punti getPunti() {
		return this.punti;
	}
}