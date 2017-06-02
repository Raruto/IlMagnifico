package main.network.server.game;

import main.model.Giocatore;
import main.network.NetworkException;

/**
 * Classe Astratta che estende {@link Giocatore} aggiungendo le funzionalit� di
 * comunicazione al {@link Giocatore} Client associatogli.
 */
public abstract class RemotePlayer extends Giocatore {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7157051737050661369L;

	/**
	 * Flag che indica se il giocatore � online
	 */
	private boolean isOnline;

	/**
	 * Reference to room where player is joined.
	 */
	private transient Room mRoom;

	/**
	 * Costruttore Astratto.
	 */
	protected RemotePlayer() {
		super();
	}

	/**
	 * Imposta il riferimento alla Stanza associata al Giocatore.
	 * 
	 * @param room
	 *            stanza dove il giocatore � aggiunto.
	 */
	public void setRoom(Room room) {
		mRoom = room;
	}

	/**
	 * Ritorna il riferimento alla Stanza dove il Giocatore � stato aggiunto.
	 * 
	 * @return se il giocatore � in una stanza, il riferimento della stanza,
	 *         altrimenti null.
	 */
	public Room getRoom() {
		return mRoom;
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
	 * @return "True" se il giocatore � online.
	 */
	public boolean isOnline() {
		return this.isOnline;
	}

	/**
	 * Invia un messaggio sulla chat del giocatore.
	 * 
	 * @param author
	 *            nome del giocatore MITTENTE del messaggio.
	 * @param message
	 *            messaggio da inviare.
	 * @param privateMessage
	 *            True se il messaggio � privato, False se pubblico.
	 * @throws NetworkException
	 *             se il client non � raggiungibile.
	 */
	public abstract void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException;

	/**
	 * Invia al giocatore un aggiornamento sullo stato della partita.
	 * 
	 * @param update {@link UpdateStats}
	 * @throws NetworkException
	 *             se il client non � raggiungibile.
	 */
	public abstract void onGameUpdate(UpdateStats update) throws NetworkException;

	/**
	 * Metodo per il "debug"
	 */
	public abstract void send(Object object) throws NetworkException;
}