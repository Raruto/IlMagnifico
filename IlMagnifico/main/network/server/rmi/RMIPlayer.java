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
/* package-local */ class RMIPlayer extends RemotePlayer {

	/**
	 * 
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

	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		try {
			clientInterface.notifyGameUpdate(update);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		}
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
	 *             se il cliente non � raggiungibile.
	 */
	@Override
	public void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException {
		try {
			clientInterface.notifyChatMessage(author, message, privateMessage);
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