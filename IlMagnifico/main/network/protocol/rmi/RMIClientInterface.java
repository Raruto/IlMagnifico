package main.network.protocol.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import main.network.server.game.UpdateStats;

/**
 * Interfaccia remota per eseguire Invocazione a Metodi Remoti da SERVER a
 * CLIENT.
 */
public interface RMIClientInterface extends Remote {

	/**
	 * Notifica al giocatore che e' stato ricevuto un nuovo messaggio sulla chat.
	 * 
	 * @param author
	 *            nome del giocatore che ha inviato il messaggio.
	 * @param message
	 *            corpo del messaggio ricevuto.
	 * @throws RemoteException
	 *             se il giocatore non e' raggiungibile dal server.
	 */
	void notifyChatMessage(String author, String message) throws RemoteException;

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