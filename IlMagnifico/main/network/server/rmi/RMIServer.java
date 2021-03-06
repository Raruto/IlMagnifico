package main.network.server.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

import main.network.exceptions.LoginException;
import main.network.protocol.rmi.RMIClientInterface;
import main.network.protocol.rmi.RMIServerInterface;
import main.network.server.AbstractServer;
import main.network.server.IServer;
import main.network.server.ServerException;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;
import main.network.server.game.exceptions.GameException;
import main.network.server.game.exceptions.JoinRoomException;
import main.util.Costants;

/**
 * Estende {@link AbstractServer} per consentire di implementare la
 * comunicazione Client/Server con i {@link RMIClient}.
 */
public class RMIServer extends AbstractServer implements RMIServerInterface {

	/**
	 * Mappa di tutti i giocatori che hanno eseguito il login al quale e' stato
	 * un token di sessione univoco (necessario per identificare il giocatore
	 * remoto ogni volta che fa una richiesta verso il Server).
	 */
	protected final HashMap<String, String> sessionTokens;

	/**
	 * ID usato per identificare il server nelle comunicazioni
	 */
	private final static String RMI_ID = Costants.RMI_SERVER_ID;

	/**
	 * Costruttore.
	 * 
	 * @param controller
	 *            interfaccia del Server (es. {@link Server}).
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
			System.out.println(RMI_ID + " OK");
		} catch (RemoteException e) {
			throw new ServerException("Server interface not loaded", e);
		}
	}

	/**
	 * Crea o carica il Registry al numero di porta specificato.
	 * 
	 * @param port
	 *            numero di porta da usare.
	 * @return il registry creato o recuperato.
	 * @throws ServerException
	 *             se si verifica un errore.
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
	 * Ritorna il Giocatore Remoto associato al token di sessione fornito.
	 * 
	 * @param sessionToken
	 *            token fornito con la richiesta.
	 * @return il giocatore remoto associato.
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
	 *             se esiste gia' un altro giocatore con il nome fornito.
	 * @throws RemoteException
	 *             se il server non e' raggiungibile.
	 */
	@Override
	public String sendLoginRequest(String nickname, RMIClientInterface player) throws IOException {
		getController().loginPlayer(nickname, new RMIPlayer(player));
		// generate new unique session token
		String sessionToken = UUID.randomUUID().toString();
		sessionTokens.put(sessionToken, nickname);

		try {
			getController().joinFirstAvailableRoom(getPlayer(sessionToken));
		} catch (JoinRoomException e) {
			// e.printStackTrace();
		}
		return sessionToken;
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
	@Override
	public void sendChatMessage(String sessionToken, String receiver, String message) throws IOException {
		getController().sendChatMessage(getPlayer(sessionToken), receiver, message);
	}

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
	@Override
	public void sendGameActionRequest(String sessionToken, UpdateStats requestedAction)
			throws RemoteException, GameException {
		RemotePlayer remotePlayer = getPlayer(sessionToken);
		remotePlayer.getRoom().performGameAction(remotePlayer, requestedAction);
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws RemoteException {
		getController().send(object);
	}
}