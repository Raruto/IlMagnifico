package LorenzoIlMagnifico;

import java.io.Serializable;

import network.protocol.PlayerColors;

/**
 * 
 */
public class Giocatore implements Serializable {

	/**
	 * Generato automaticamente: "serialVersionUID" (vedi {@link Serializable})
	 */
	private static final long serialVersionUID = 3225605065408244944L;

	/**
	 * Nickname del giocatore.
	 */
	private String nome;

	/**
	 * Colore del giocatore.
	 */
	private PlayerColors color;

	/**
	 * Flag che indica se il giocatore è online
	 */
	private boolean online;

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
	 * Imposta il nickname del giocatore (utilizzato per identificare
	 * univocamente il giocatore all'interno del gioco).
	 * 
	 * @param nickname
	 *            nickname da assegnare al giocatore.
	 */
	public void setNickname(String nickname) {
		this.nome = nickname;
	}

	/**
	 * Ritorna il nickname del giocatore.
	 * 
	 * @return nickname del giocatore.
	 */
	public String getNickname() {
		return nome;
	}

	/**
	 * Imposta il colore del giocatore.
	 * 
	 * @param color
	 *            colore da assegnare al giocatore.
	 */
	public void setColor(PlayerColors color) {
		this.color = color;
	}

	/**
	 * Ritorna il colore associato al giocatore.
	 * 
	 * @return {@link PlayerColors} colore del giocatore.
	 */
	public PlayerColors getColor() {
		return this.color;
	}

	/**
	 * Imposta il flag online usato per determinare lo stato della connessione
	 * con il client associato al giocatore.
	 * 
	 * @param online
	 *            "True" imposta lo stato del giocatore come presente (online).
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	/**
	 * Ritorna lo stato della connessione con il client associato al giocatore
	 * 
	 * @return "True" se il giocatore è online.
	 */
	public boolean isOnline() {
		return this.online;
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
}