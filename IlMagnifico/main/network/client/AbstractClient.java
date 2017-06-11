package main.network.client;

import main.model.enums.EAzioniGiocatore;
import main.network.NetworkException;
import main.network.server.game.UpdateStats;

/**
 * 
 * Classe che rappresenta l'astrazione necessaria per le comunicazioni col
 * server. Estendendo questa classe � possibile utilizzare qualsiasi tipo di
 * connessione (es. RMI o Socket). L'interfaccia {@link IClient} funziona come
 * controller client e callback handler.
 */
public abstract class AbstractClient {

	/**
	 * Token che identifica in modo univoco il giocatore sul Server.
	 */
	protected String sessionToken;

	/**
	 * Interfaccia utilizzata per comunicare con il client.
	 */
	private final IClient controller;

	/**
	 * Indirizzo del server.
	 */
	private final String address;

	/**
	 * Porta usata dal server per la comunicazione.
	 */
	private final int port;

	/**
	 * Costruttore astratto.
	 * 
	 * @param controller
	 *            client controller.
	 * @param address
	 *            indirizzo del server.
	 * @param port
	 *            porta del server.
	 */
	public AbstractClient(IClient controller, String address, int port) {
		this.controller = controller;
		this.address = address;
		this.port = port;
	}

	/**
	 * Ritorna l'indirizzo del server.
	 * 
	 * @return l'indirizzo del server.
	 */
	protected String getAddress() {
		return address;
	}

	/**
	 * Ritorna la porta usata dal server per la comunicazione.
	 * 
	 * @return porta del server.
	 */
	protected int getPort() {
		return port;
	}

	/**
	 * Ritorna il client controller per potere (scrivere/inviare) richieste sul
	 * (canale di comunicazione/oggetto remoto).
	 * 
	 * @return client controller (es. {@link Client}).
	 */
	protected IClient getController() {
		return controller;
	}

	/**
	 * Metodo astratto, apre una connessione con il server.
	 * 
	 * @throws ClientConnectionException
	 *             se il server non � raggiungibile o qualcosa � andato storto.
	 */
	public abstract void connect() throws ClientException;

	/**
	 * Prova ad effettuare un accesso al server con il nome fornito.
	 * 
	 * @param nickname
	 *            nome del giocatore da utilizzare per identificarsi sul server.
	 * @throws LoginException
	 *             se il nome � gi� in uso sul server.
	 * @throws NetworkException
	 *             se il server non � raggiungibile o qualcosa � andato storto.
	 */
	public abstract void sendLoginRequest(String nickname) throws NetworkException;

	/**
	 * Invia un messaggio in chat ad altri giocatori o un giocatore specifico.
	 * 
	 * @param receiver
	 *            nome del DESTINATARIO del messaggio. Se null il messaggio
	 *            verr� inviato a tutti i giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws NetworkException
	 *             se il server non � raggiungibile o qualcosa � andato storto.
	 */
	public abstract void sendChatMessage(String receiver, String message) throws NetworkException;

	/**
	 * Invia al Server la richiesta di svolgere un'azione di gioco.
	 * 
	 * @param requestedAction
	 *            oggetto {@link UpdateStats} contenete tutte le informazioni
	 *            necessarie al server per comprendere il tipo di richiesta (es.
	 *            deve contenere un {@link EAzioniGiocatore} che codifica il
	 *            tipo di azione richiesta).
	 */
	public abstract void sendGameActionRequest(UpdateStats requestedAction) throws NetworkException;
}