package main.network.client;

import main.network.NetworkException;
import main.util.EAzioniGiocatore;

/**
 * 
 * Classe che rappresenta l'astrazione necessaria per le comunicazioni col
 * server. Estendendo questa classe è possibile utilizzare qualsiasi tipo di
 * connessione (es. RMI o Socket). L'interfaccia {@link IClient} funziona come
 * controller client e callback handler.
 */
public abstract class AbstractClient {

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
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	public abstract void connect() throws ClientException;

	/**
	 * Initialize client connection. Override this only if required. This is
	 * called immediately after {@link #connect()}.
	 */
	public void initializeConnection() {

	}

	/**
	 * Provare ad effettuare un accesso al server con il nome fornito.
	 * 
	 * @param nickname
	 *            nome del giocatore da utilizzare per identificarsi sul server.
	 * @throws LoginException
	 *             se il nome fornito è già in uso sul server.
	 * @throws NetworkException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	public abstract void loginPlayer(String nickname) throws NetworkException;

	/**
	 * Try to join the first available room.
	 * 
	 * @throws JoinRoomException
	 *             if no available room where join player has been found.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public abstract void joinFirstAvailableRoom() throws NetworkException;

	/**
	 * Retrieve a list that contains all possible actions.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void getActionList() throws NetworkException;

	/**
	 * Draw a politic card.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void drawPoliticCard() throws NetworkException;

	/**
	 * Send a request for ending the current turn.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void endTurn() throws NetworkException;

	/**
	 * Send a chat message to other players or a specified player.
	 * 
	 * @param receiver
	 *            nickname of the specific player if a private message, null if
	 *            should be delivered to all room players.
	 * @param message
	 *            to deliver.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public abstract void sendChatMessage(String receiver, String message) throws NetworkException;

	public abstract void performGameAction(EAzioniGiocatore act) throws NetworkException;
}