package main.model;

import java.io.Serializable;

import main.network.protocol.PlayerColors;

/**
 * 
 */
public class Giocatore implements Serializable {

	/**
	 * Generato automaticamente: "serialVersionUID" (vedi {@link Serializable})
	 */
	private static final long serialVersionUID = 3225605065408244944L;

	/**
	 * Nome del giocatore.
	 */
	private String nome;

	/**
	 * Colore del giocatore.
	 */
	private PlayerColors colore;

	/**
	 * Flag che indica se il giocatore è online
	 */
	private boolean isOnline;

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
	 * Default constructor
	 */
	public Giocatore() {
	}

	/**
	 * Imposta il nome del giocatore (utilizzato per identificare
	 * univocamente il giocatore all'interno del gioco).
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
	 * Imposta il flag online usato per determinare lo stato della connessione
	 * con il client associato al giocatore.
	 * 
	 * @param online
	 *            "True" imposta lo stato del giocatore come presente (online).
	 */
	public void setOnline(boolean online) {
		this.isOnline = online;
	}

	/**
	 * Ritorna lo stato della connessione con il client associato al giocatore
	 * 
	 * @return "True" se il giocatore è online.
	 */
	public boolean isOnline() {
		return this.isOnline;
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
}