package main.network.server;

import java.util.HashMap;

import main.network.server.game.RemotePlayer;

/**
 * Classe che rappresenta l'astrazione necessaria per le comunicazioni del
 * server. Estendendo questa classe è possibile utilizzare qualsiasi tipo di
 * connessione (es. RMI o Socket). L'interfaccia {@link IServer} funziona come
 * controller server e callback handler.
 */
public abstract class AbstractServer {

	/**
	 * Interfaccia utilizzata per comunicare con il server.
	 */
	private final IServer controller;

	/**
	 * Internal cache that maps all logged used with an unique session token
	 * that identify the single player. This is required in order to identify
	 * the remote player when he is making a new request to the server.
	 */
	protected final HashMap<String, String> sessionTokens;

	/**
	 * Costruttore astratto.
	 * 
	 * @param controller
	 *            server controller.
	 */
	public AbstractServer(IServer controller) {
		this.controller = controller;
		sessionTokens = new HashMap<>();
	}

	/**
	 * Ritorna il server controller per potere (scrivere/inviare) richieste sul
	 * (canale di comunicazione/oggetto remoto).
	 * 
	 * @return server controller (es. {@link Server}).
	 */
	protected IServer getController() {
		return this.controller;
	}

	/**
	 * Get the remote player associated to provided session token.
	 * 
	 * @param sessionToken
	 *            provided with the request.
	 * @return the remote player associated.
	 */
	protected RemotePlayer getPlayer(String sessionToken) {
		return controller.getPlayer(sessionTokens.get(sessionToken));
	}

	/**
	 * Metodo astratto, avvia tutte le connessioni disponibili (es. RMI/Server).
	 * 
	 * @param port
	 *            numero di porta da usare.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	public abstract void startServer(int port) throws ServerException;
}