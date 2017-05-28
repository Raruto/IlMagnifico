package main.network.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.network.server.game.UpdateStats;

/**
 * Interfaccia remota per eseguire Invocazione a Metodi Remoti da SERVER a
 * CLIENT.
 */
public interface RMIClientInterface extends Remote {

	/**
	 * Metodo per il "debug"
	 * 
	 * @param object
	 * @throws RemoteException
	 */
	public void notify(Object object) throws RemoteException;

	/**
	 * Notify player that a new chat message has been received.
	 * 
	 * @param author
	 *            nickname of the player that sent the message.
	 * @param message
	 *            that the author has sent.
	 * @param privateMessage
	 *            if message is private, false if public.
	 * @throws RemoteException
	 *             if player is not reachable from the server.
	 */
	void notifyNewChatMessage(String author, String message, boolean privateMessage) throws RemoteException;

	/**
	 * Notifica aggiornamento stato partita
	 * 
	 * @param update
	 * @throws RemoteException
	 */
	void notifyGameUpdate(UpdateStats update) throws RemoteException;
}