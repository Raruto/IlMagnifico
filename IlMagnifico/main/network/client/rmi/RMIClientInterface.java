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
	 * Notifica al giocatore che è stato ricevuto un nuovo messaggio sulla chat.
	 * 
	 * @param author
	 *            nome del giocatore che ha inviato il messaggio.
	 * @param message
	 *            corpo del messaggio ricevuto.
	 * @param privateMessage
	 *            True se il messaggio è privato, False se pubblico.
	 * @throws RemoteException
	 *             se il giocatore non è raggiungibile dal server.
	 */
	void notifyChatMessage(String author, String message, boolean privateMessage) throws RemoteException;

	/**
	 * Notifica aggiornamento stato partita
	 * 
	 * @param update
	 * @throws RemoteException
	 */
	void notifyGameUpdate(UpdateStats update) throws RemoteException;

	/**
	 * Metodo per il "debug"
	 * 
	 * @param object
	 * @throws RemoteException
	 */
	public void notify(Object object) throws RemoteException;
}