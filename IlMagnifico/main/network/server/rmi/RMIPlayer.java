package main.network.server.rmi;

import java.rmi.RemoteException;

import main.network.NetworkException;
import main.network.protocol.rmi.RMIClientInterface;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Extension of {@link RemotePlayer}. This implementation can communicate to his
 * referenced client.
 */
/* package-local */ class RMIPlayer extends RemotePlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -211182477118050303L;
	
	/**
	 * Remote interface to invoke method on {@link RMIClient}.
	 */
	private transient RMIClientInterface clientInterface;

	/**
	 * Create a new instance of a RMIPlayer.
	 * 
	 * @param playerInterface
	 *            remote interface to send response to client.
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