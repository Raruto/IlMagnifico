package main.network.client;

import java.rmi.RemoteException;

import main.model.errors.Errors;
import main.network.server.game.UpdateStats;

/**
 * Interfaccia usata come client controller in {@link AbstractClient} (notifiche
 * dal server verso il client stesso).
 */
public interface IClient {

	void onGameStarted(UpdateStats update);

	void onChurchSupport(UpdateStats update);

	void onTurnEnd(UpdateStats update);

	void onPeriodEnd(UpdateStats update);

	void onGameEnd(UpdateStats update);

	void onPlayerMove(UpdateStats update);

	void onTurnStarted(UpdateStats update);

	void onPeriodStarted(UpdateStats update);

	/**
	 * Notifica che è arrivato un nuovo messaggio dalla chat.
	 * 
	 * @param privateMessage
	 *            "True" se il messaggio è privato, "False" se pubblico.
	 * @param author
	 *            autore del messaggio.
	 * @param message
	 *            corpo del messaggio.
	 */
	void onChatMessage(boolean privateMessage, String author, String message);

	void onGameUpdate(UpdateStats update);

	/**
	 * Notifica che il server ha risposto con un codice di errore.
	 * 
	 * @param errorCode
	 *            codice che identifica l'errore (vedi {@link Errors}).
	 */
	void onActionNotValid(String errorCode);

	/**
	 * Metodo per il "debug"
	 */
	void onNotify(Object object) throws RemoteException;
}