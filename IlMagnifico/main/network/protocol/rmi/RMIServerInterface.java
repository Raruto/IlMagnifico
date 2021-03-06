package main.network.protocol.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import main.network.exceptions.LoginException;
import main.network.server.game.UpdateStats;
import main.network.server.game.exceptions.GameException;

/**
 * Interfaccia remota per eseguire Invocazione a Metodi Remoti da CLIENT a
 * SERVER.
 */
public interface RMIServerInterface extends Remote {

	/**
	 * Metodo remoto per il login di un nuovo Giocatore sul Server.
	 * 
	 * @param nickname
	 *            nome con cui il giocatore vorrebbe essere identificato sul
	 *            server.
	 * @param player
	 *            riferimento al giocatore che ha effettuato la richiesta (es.
	 *            {@link RMIPlayer}).
	 * @return token di sessione che identifica in modo univoco l'utente sul
	 *         Server.
	 * @throws LoginException
	 *             se esiste gia' un altro giocatore con il nome fornito.
	 * @throws RemoteException
	 *             se il server non e' raggiungibile.
	 */
	String sendLoginRequest(String nickname, RMIClientInterface player) throws IOException;

	/**
	 * Metodo Remoto per inviare un messaggio di chat a tutti i giocatori o ad
	 * uno specifico player.
	 * 
	 * @param sessionToken
	 *            token del giocatore che sta facendo la richiesta di invio
	 *            (MITTENTE).
	 * @param receiver
	 *            nome del giocatore che dovrebbe ricevere il messaggio
	 *            (DESTINATARIO). Se null il messaggio verra' inviato a tutti i
	 *            giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws PlayerNotFound
	 *             se il destinatario non non corrisponde a nessun giocatore
	 *             presente sul server.
	 * @throws RemoteException
	 *             se il server non e' raggiungibile.
	 */
	void sendChatMessage(String sessionToken, String receiver, String message) throws IOException;

	/**
	 * Metodo Remoto per inviare una richiesta di esecuzione di un'azione di
	 * gioco.
	 * 
	 * @param sessionToken
	 *            token del giocatore che sta facendo la richiesta.
	 * @param requestedAction
	 *            richiesta del giocatore (vedi {@link UpdateStats}).
	 * @throws RemoteException
	 *             se il server non e' raggiungibile.
	 * @throws GameException
	 *             se il giocatore sta tentando di eseguire un azione illegale.
	 */
	void sendGameActionRequest(String sessionToken, UpdateStats requestedAction) throws RemoteException, GameException;

	/**
	 * Metodo per il "debug"
	 * 
	 * @param object
	 * @throws RemoteException
	 */
	public void send(Object object) throws RemoteException;
}
