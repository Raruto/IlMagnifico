package network.server;

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
	 * Costruttore astratto.
	 * 
	 * @param controller
	 *            server controller.
	 */
	public AbstractServer(IServer controller) {
		this.controller = controller;
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
	 * Metodo astratto, avvia tutte le connessioni disponibili (es. RMI/Server).
	 * 
	 * @param port
	 *            numero di porta da usare.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	public abstract void startServer(int port) throws ServerException;
}