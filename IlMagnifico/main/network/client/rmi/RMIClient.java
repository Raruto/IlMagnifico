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
	 * Token che identifica in modo univoco il giocatore sul RMIServer.
	 */
	private String sessionToken;

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
	 * Try to join the first available room.
	 * 
	 * @throws JoinRoomException
	 *             if no available room where join player has been found.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	@Override
	public void joinFirstAvailableRoom() throws NetworkException {
		try {
			server.joinFirstAvailableRoom(sessionToken);
		} catch (JoinRoomException e) {
			throw e;
		} catch (IOException e) {
			throw new NetworkException(e);
		}
	}

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

	/**
	 * Notify player that a new chat message has been received.
	 * 
	 * @param author
	 *            nickname of the player that sent the message.
	 * @param message
	 *            that the author has sent.
	 * @param privateMessage
	 *            if message is private, false if public.
	 * @throws RemoteException
	 *             if player is not reachable from the server.
	 */
	@Override
	public void notifyNewChatMessage(String author, String message, boolean privateMessage) throws RemoteException {
		getController().onChatMessage(privateMessage, author, message);
	}

	@Override
	public void notify(String object) throws RemoteException {
		System.out.println(object);
	}

}
