package main.model;

import java.io.Serializable;

import main.network.protocol.PlayerColors;

/**
 * 
 */
public class Giocatore implements Serializable {

	/**
	 * Nome del giocatore.
	 */
	private String nome;

	/**
	 * Colore del giocatore.
	 */
	private PlayerColors colore;

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

	private SpazioAzione spazioAzione;

	/**
	 * 
	 */
	private Scomunica[] scomuniche;

	/**
	 * Default constructor
	 */
	public Giocatore(String nome, PlayerColors colore, Risorsa risorse, Plancia plancia, Punti punti,
			SpazioAzione spazioAzione) {
		this.nome = nome;
		this.colore = colore;
		this.risorse = risorse;
		this.plancia = plancia;
		this.punti = punti;
		this.spazioAzione = spazioAzione;
		this.famiglia = new Famigliare[4];
		this.scomuniche = new Scomunica[3];
	}

	public Giocatore() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Imposta il nome del giocatore (utilizzato per identificare univocamente
	 * il giocatore all'interno del gioco).
	 * 
	 * @param nome
	 *            nickname da assegnare al giocatore.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Ritorna il nome del giocatore.
	 * 
	 * @return nome del giocatore.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il colore del giocatore.
	 * 
	 * @param colore
	 *            colore da assegnare al giocatore.
	 */
	public void setColore(PlayerColors colore) {
		this.colore = colore;
	}

	/**
	 * Ritorna il colore associato al giocatore.
	 * 
	 * @return {@link PlayerColors} colore del giocatore.
	 */
	public PlayerColors getColore() {
		return this.colore;
	}

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

	public void raccolto(int valore) {
		// TODO Auto-generated method stub

	}

	public void produzione(int valore) {
		// TODO Auto-generated method stub

	}

	public SpazioAzione getSpazioAzione() {
		return this.spazioAzione;
	}

	public Plancia getPlancia() {
		return this.plancia;
	}

	/**
	 * Metodo che imposta un valore al famigliare indicato dal numero in
	 * ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setValore(int famigliare, int valoreDaCambiare) {
		this.famiglia[famigliare].setValore(valoreDaCambiare);
	}

	/**
	 * Metodo che assegna una scomunica al giocatore. In ingresso vi sono il
	 * periodo della scomunica e la scounica stessa
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void setScomunica(int periodo, Scomunica scomunica) {
		this.scomuniche[periodo] = scomunica;
	}

	/**
	 * Metodo che controlla che tutti i famigliari del giocatore siano stati
	 * posizionati. Ritorna true se sono stati posizionati tutti i famigliari,
	 * false altrimenti
	 * 
	 * @param
	 * @return
	 */
	public boolean checkPosizionato() {
		for (int i = 0; i < 4; i++) {
			if (!(this.famiglia[i].getPosizionato()))
				return false;
		}
		return true;
	}
}