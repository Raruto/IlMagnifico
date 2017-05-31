package main.network.client.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import main.network.NetworkException;
import main.network.client.AbstractClient;
import main.network.client.ClientException;
import main.network.client.IClient;
import main.network.exceptions.*;
import main.network.protocol.ErrorCodes;
import main.network.server.game.GameException;
import main.network.server.game.UpdateStats;
import main.network.server.rmi.*;

/**
 * Classe che gestisce la connessione di rete con RMI. Estende
 * {@link AbstractClient}, implementa {@link RMIClientInterface}.
 */
public class RMIClient extends AbstractClient implements RMIClientInterface {

	/**
	 * Interfaccia remota del client che verrà memorizzata dal server per la
	 * comunicazione SERVER-->CLIENT.
	 */
	private RMIServerInterface server;

	/**
	 * Crea un'istanza RMIClient .
	 * 
	 * @param controller
	 *            client controller (es. {@link Client}).
	 * @param address
	 *            indirizzo del the server.
	 * @param port
	 *            porta del server.
	 */
	public RMIClient(IClient controller, String address, int port) {
		super(controller, address, port);
	}

	/**
	 * Apre una connessione con {@link RMIServer}.
	 * 
	 * @throws ClientException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	public void connect() throws ClientException {
		try {
			Registry registry = LocateRegistry.getRegistry(getAddress(), getPort());
			server = (RMIServerInterface) registry.lookup("Server");
			UnicastRemoteObject.exportObject(this, 0);

			System.out.println("RMI Connection established (port: " + getPort() + ")");

		} catch (RemoteException | NotBoundException e) {
			throw new ClientException(e);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati dal Client (GUI) (vedi AbstractClient)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Esegue il login del giocatore al RMIServer con il nickname fornito.
	 * 
	 * @param nickname
	 *            nome da utilizzare per identificarsi al server.
	 * @throws NetworkException
	 *             se il server non è raggiungibile.
	 */
	@Override
	public void loginPlayer(String nickname) throws NetworkException {
		try {
			sessionToken = server.loginPlayer(nickname, this);
		} catch (LoginException e) {
			throw e;
		} catch (IOException e) {
			throw new NetworkException(e);
		}
	}

	/**
	 * Invia un messaggio in chat ad altri giocatori o un giocatore specifico.
	 * 
	 * @param receiver
	 *            nome del DESTINATARIO del messaggio. Se null il messaggio
	 *            verrà inviato a tutti i giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws NetworkException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	@Override
	public void sendChatMessage(String receiver, String message) throws NetworkException {
		try {
			server.sendChatMessage(sessionToken, receiver, message);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		} catch (PlayerNotFound e) {
			getController().onActionNotValid(ErrorCodes.ERROR_CHAT_PLAYER_NOT_FOUND);
		} catch (IOException e) {
			getController().onActionNotValid(ErrorCodes.ERROR_GENERIC_SERVER_ERROR);
		}
	}

	@Override
	public void performGameAction(UpdateStats requestedAction) throws NetworkException {
		try {
			server.performGameAction(sessionToken, requestedAction);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		} catch (GameException e) {
			System.err.println("\n" + e.getMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati dal Server (vedi RMIClientInterface)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Notifica al giocatore che è stato ricevuto un nuovo messaggio sulla chat.
	 * 
	 * @param author
	 *            nome del giocatore che ha inviato il messaggio.
	 * @param message
	 *            corpo del messaggio ricevuto.
	 * @param privateMessage
	 *            True se il messaggio è privato, False se pubblico.
	 * @throws RemoteException
	 *             se il giocatore non è raggiungibile dal server.
	 */
	@Override
	public void notifyChatMessage(String author, String message, boolean privateMessage) throws RemoteException {
		getController().onChatMessage(privateMessage, author, message);
	}

	@Override
	public void notifyGameUpdate(UpdateStats update) throws RemoteException {
		getController().onGameUpdate(update);
	}

	@Override
	public void notify(Object object) throws RemoteException {
		getController().onNotify(object);
	}

}
