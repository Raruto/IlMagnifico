package main.network.server;

import main.network.NetworkException;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.server.game.RemotePlayer;
import main.network.server.game.exceptions.CreateRoomException;
import main.network.server.game.exceptions.JoinRoomException;

/**
 * Interfaccia usata come server controller in {@link AbstractServer} (invio di
 * richieste al server da parte del client).
 */
public interface IServer {

	/**
	 * Login del giocatore tramite nickname.
	 * 
	 * @param nickname
	 *            nome con cui il giocatore vorrebbe essere identificato sul
	 *            server.
	 * @param player
	 *            riferimento al giocatore che ha effettuato la richiesta (es.
	 *            {@link RMIPlayer}, {@link SocketPlayer}).
	 * @throws LoginException
	 *             se esiste già un altro giocatore con il nome fornito.
	 */
	void loginPlayer(String nickname, RemotePlayer remotePlayer) throws LoginException;

	/**
	 * Ritorna il giocatore associato al nome richiesto.
	 * 
	 * @param nickname
	 *            nome associato al giocatore.
	 * @return il giocatore remoto associato (se trovato).
	 */
	RemotePlayer getPlayer(String nickname);

	/**
	 * Aggiunge il giocatore alla prima Stanza disponibile.
	 * 
	 * @param remotePlayer
	 *            giocatore remoto da aggiungere.
	 * @throws JoinRoomException
	 *             se non è stata trovata alcuna stanza disponibile.
	 */
	void joinFirstAvailableRoom(RemotePlayer remotePlayer) throws JoinRoomException;

	/**
	 * Crea una nuova Stanza sul server.
	 * 
	 * @param remotePlayer
	 *            giocatore remoto che ha fatto la richiesta.
	 * @param maxPlayers
	 *            numero massimo di giocatori che stanza dovrebbe gestire.
	 * @throws CreateRoomException
	 *             se nel frattempo un altro giocatore ha creato una nuova
	 *             stanza.
	 */
	void createNewRoom(RemotePlayer remotePlayer, int maxPlayers, int minPlayers) throws CreateRoomException;

	/**
	 * Invia un messaggio di chat a tutti i giocatori o un giocatore specifico.
	 * 
	 * @param player
	 *            MITTENTE del messaggio.
	 * @param receiver
	 *            nome del DESTINATARIO del messaggio. Se null il messaggio
	 *            verrà inviato a tutti i giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws PlayerNotFound
	 *             se il ricevitore non non corrisponde a nessun giocatore
	 *             presente sul server.
	 */
	void sendChatMessage(RemotePlayer player, String receiver, String message) throws PlayerNotFound;

	/**
	 * Metodo per il "debug"
	 */
	void send(Object object) throws NetworkException;
}