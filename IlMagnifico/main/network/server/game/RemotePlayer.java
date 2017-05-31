package main.network.server.game;

import main.model.Giocatore;
import main.network.NetworkException;

/**
 * Abstract extension of {@link Player}. This implementation can communicate to
 * his referenced client.
 */
public abstract class RemotePlayer extends Giocatore {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7157051737050661369L;

	/**
	 * Flag che indica se il giocatore è online
	 */
	private boolean isOnline;

	/**
	 * Reference to room where player is joined.
	 */
	private transient Room mRoom;

	/**
	 * Abstract constructor.
	 */
	protected RemotePlayer() {
		super();
	}

	/**
	 * Set room reference.
	 * 
	 * @param room
	 *            where player is joined.
	 */
	public void setRoom(Room room) {
		mRoom = room;
	}

	/**
	 * Get room reference where player is joined.
	 * 
	 * @return the room reference if player is in a room, null otherwise.
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
	 * @return "True" se il giocatore ï¿½ online.
	 */
	public boolean isOnline() {
		return this.isOnline;
	}

	/**
	 * Send a chat message to the player.
	 * 
	 * @param author
	 *            nickname of the player that sent the message.
	 * @param message
	 *            that the author has sent.
	 * @param privateMessage
	 *            if message is private, false if public.
	 * @throws NetworkException
	 *             if client is not reachable.
	 */
	public abstract void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException;

	public abstract void onGameUpdate(UpdateStats update) throws NetworkException;

	public abstract void send(Object object) throws NetworkException;
}