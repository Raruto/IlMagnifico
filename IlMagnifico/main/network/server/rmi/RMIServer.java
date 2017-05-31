package main.network.server.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;
import main.network.protocol.rmi.RMIClientInterface;
import main.network.protocol.rmi.RMIServerInterface;
import main.network.server.AbstractServer;
import main.network.server.IServer;
import main.network.server.ServerException;
import main.network.server.game.GameException;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Estende {@link AbstractServer} per consentire di implementare la
 * comunicazione Client/Server con i {@link RMIClient}.
 */
public class RMIServer extends AbstractServer implements RMIServerInterface {

	/**
	 * Internal cache that maps all logged used with an unique session token
	 * that identify the single player. This is required in order to identify
	 * the remote player when he is making a new request to the server.
	 */
	protected final HashMap<String, String> sessionTokens;

	/**
	 * Public constructor.
	 * 
	 * @param controller
	 *            server interface to communicate with him.
	 */
	public RMIServer(IServer controller) {
		super(controller);
		sessionTokens = new HashMap<>();
	}

	/**
	 * Avvia il Server per le connessioni RMI.
	 * 
	 * @param port
	 *            numero di porta da usare.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	@Override
	public void startServer(int port) throws ServerException {
		Registry registry = createOrLoadRegistry(port);
		try {
			registry.rebind("Server", this);
			UnicastRemoteObject.exportObject(this, port);
			System.out.println("[RMI Server] OK");
		} catch (RemoteException e) {
			throw new ServerException("Server interface not loaded", e);
		}
	}

	/**
	 * Create or load registry in a specified port number.
	 * 
	 * @param port
	 *            number of the port to use.
	 * @return the created or retrieved registry.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private Registry createOrLoadRegistry(int port) throws ServerException {
		try {
			return LocateRegistry.createRegistry(port);
		} catch (RemoteException e) {
			System.err.println("RMI Registry already exists");
		}
		try {
			return LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.err.println("RMI Registry not found");
		}
		throw new ServerException("Cannot initialize RMI registry");
	}

	/**
	 * Get the remote player associated to provided session token.
	 * 
	 * @param sessionToken
	 *            provided with the request.
	 * @return the remote player associated.
	 */
	protected RemotePlayer getPlayer(String sessionToken) {
		return getController().getPlayer(sessionTokens.get(sessionToken));
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati dal Client (vedi RMIServerInterface)
	/////////////////////////////////////////////////////////////////////////////////////////

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
	 *             se esiste già un altro giocatore con il nome fornito.
	 * @throws RemoteException
	 *             se il server non è raggiungibile.
	 */
	@Override
	public String loginPlayer(String nickname, RMIClientInterface player) throws IOException {
		getController().loginPlayer(nickname, new RMIPlayer(player));
		// generate new unique session token
		String sessionToken = UUID.randomUUID().toString();
		sessionTokens.put(sessionToken, nickname);

		try {
			joinFirstAvailableRoom(sessionToken);
		} catch (JoinRoomException e) {
			// e.printStackTrace();
		}

		return sessionToken;
	}

	/**
	 * Metodo remoto per aggiungere il Giocatore Remoto alla prima Stanza
	 * disponibile.
	 * 
	 * @param sessionToken
	 *            token di sessione del giocatore che sta facendo la richiesta.
	 * @throws JoinRoomException
	 *             se nessuna stanza è disponibile.
	 * @throws RemoteException
	 *             se il server non è raggiungibile.
	 */
	@Override
	public void joinFirstAvailableRoom(String sessionToken) throws IOException {
		getController().joinFirstAvailableRoom(getPlayer(sessionToken));
	}

	/**
	 * Metodo Remoto per inviare un messaggio di chat a tutti i giocatori o ad
	 * uno specifico player.
	 * 
	 * @param sessionToken
	 *            token del giocatore che sta facendo la richiesta di invio
	 *            (MITTENTE).
	 * @param receiver
	 *            nome del giocatore che dovrebbe ricevere il messaggio
	 *            (DESTINATARIO). Se null il messaggio verrà inviato a tutti i
	 *            giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws PlayerNotFound
	 *             se il ricevitore non non corrisponde a nessun giocatore
	 *             presente sul server.
	 * @throws RemoteException
	 *             se il server non è raggiungibile.
	 */
	@Override
	public void sendChatMessage(String sessionToken, String receiver, String message) throws IOException {
		getController().sendChatMessage(getPlayer(sessionToken), receiver, message);
	}

	@Override
	public void performGameAction(String sessionToken, UpdateStats requestedAction)
			throws RemoteException, GameException {
		RemotePlayer remotePlayer = getPlayer(sessionToken);
		remotePlayer.getRoom().performGameAction(remotePlayer, requestedAction);
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws RemoteException {
		// TODO Auto-generated catch block
	}

}