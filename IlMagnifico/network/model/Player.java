package network.model;

import java.awt.Color;

import network.protocol.PlayerColors;

public class Player {
	/**
	 * Nickname del giocatore.
	 */
	private String nickname;

	/**
	 * Colore del giocatore.
	 */
	private Color color;

	/**
	 * Flag che indica se il giocatore è online
	 */
	private boolean online;

	/**
	 * Imposta il nickname del giocatore (utilizzato per identificare
	 * univocamente il giocatore all'interno del gioco).
	 * 
	 * @param nickname
	 *            nickname da assegnare al giocatore.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Ritorna il nickname del giocatore.
	 * 
	 * @return nickname del giocatore.
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Imposta il colore del giocatore.
	 * 
	 * @param color
	 *            colore da assegnare al giocatore.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Ritorna il colore associato al giocatore.
	 * 
	 * @return {@link PlayerColors} colore del giocatore.
	 */
	public Color getColor() {
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

}
