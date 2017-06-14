package main.network.server.rmi;

import java.rmi.RemoteException;

import main.model.Giocatore;
import main.network.NetworkException;
import main.network.protocol.rmi.RMIClientInterface;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Estende {@link RemotePlayer} implementando le funzionalit� di comunicazione
 * al {@link Giocatore} Client associatogli.
 */
public class RMIPlayer extends RemotePlayer {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -211182477118050303L;

	/**
	 * Interfaccia Remota per invocare i metodi presenti sul {@link RMIClient}.
	 */
	private transient RMIClientInterface clientInterface;

	/**
	 * Crea una nuova istanza RMIPlayer.
	 * 
	 * @param playerInterface
	 *            interfaccia remota per notificare le risposte al client.
	 */
	RMIPlayer(RMIClientInterface playerInterface) {
		clientInterface = playerInterface;
	}

	/**
	 * Invia un messaggio sulla chat del giocatore.
	 * 
	 * @param author
	 *            nome del giocatore MITTENTE del messaggio.
	 * @param message
	 *            messaggio da inviare.
	 * @throws NetworkException
	 *             se il client non e' raggiungibile.
	 */
	@Override
	public void onChatMessage(String author, String message) throws NetworkException {
		try {
			clientInterface.notifyChatMessage(author, message);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
	}

	/*
	 * Invia al giocatore un aggiornamento sullo stato della partita.
	 * 
	 * @param update {@link UpdateStats}
	 * 
	 * @throws NetworkException se il client non � raggiungibile.
	 */
	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		try {
			clientInterface.notifyGameUpdate(update);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws NetworkException {
		try {
			clientInterface.notify(object);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
	}
}