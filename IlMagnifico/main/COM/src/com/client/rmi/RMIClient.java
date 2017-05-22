package com.client.rmi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import com.NetworkException;
import com.client.AbstractClient;
import com.client.ClientException;
import com.client.IClient;
import com.exceptions.*;
import com.protocol.ErrorCodes;
import com.server.rmi.*;

import model.exceptions.PlayerNotFound;

/**
 * This class is the implementation of {@link AbstractClient} class. It manages
 * the network connection with RMI.
 */
public class RMIClient extends AbstractClient implements RMIClientInterface {

	/**
	 * Remote interface of the client that will be cached by the server for
	 * server to client communication.
	 */
	private RMIServerInterface server;

	/**
	 * Cached session token that uniquely identify the player on the RMIServer.
	 */
	private String mSessionToken;

	// private RMIClientImplementation client;
	// private RMIClientInterface remoteRef;

	/**
	 * Create a RMI client instance.
	 * 
	 * @param controller
	 *            client controller.
	 * @param address
	 *            of the server.
	 * @param port
	 *            of the server.
	 */
	public RMIClient(IClient controller, String address, int port) {
		super(controller, address, port);
		// client = new RMIClientImplementation();
	}

	/**
	 * Open a connection with {@link RMIServer}.
	 * 
	 * @throws ClientException
	 *             if server is not reachable or something went wrong.
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
		/*
		 * try {
		 * 
		 * getRemoteServerObject();
		 * 
		 * publishRemoteClientObject();
		 * 
		 * System.out.println("RMI Connection established (port: " +
		 * this.getPort() + ")");
		 * 
		 * Scanner scanner = new Scanner(System.in);
		 * 
		 * System.out.print("Insert Player Name: "); String name =
		 * scanner.nextLine();
		 * 
		 * loginPlayer(name);
		 * 
		 * boolean active = true; while (active) {
		 * System.out.print("Inserire un messaggio:"); String message =
		 * scanner.nextLine(); server.send(message); } scanner.close();
		 * 
		 * } catch (MalformedURLException e) {
		 * System.err.println("URL non trovato!"); } catch (RemoteException e) {
		 * System.err.println("Errore di connessione: " + e.getMessage() + "!");
		 * } catch (NotBoundException e) {
		 * System.err.println("Il riferimento passato non è associato a nulla!"
		 * ); } catch (NetworkException e) {
		 * System.err.println("Errore di connessione: " + e.getMessage() + "!");
		 * }
		 */
	}

	/*
	 * private void getRemoteServerObject() throws MalformedURLException,
	 * RemoteException, NotBoundException { // Ottengo il riferimento remoto
	 * associato alla stringa passata // (contiene l'host target e
	 * l'identificativo dell'oggetto // sull'host). server =
	 * (RMIServerInterface) Naming.lookup("//localhost/Server"); }
	 * 
	 * private void publishRemoteClientObject() throws RemoteException { //
	 * Tuttavia, dato che ClientImplementation non estende la classe //
	 * UnicastRemoteObject, devo creare un riferimento remoto // all'oggetto col
	 * metodo UnicastRemoteObject.exportObject che // prende come parametri
	 * l'oggetto da esportare e la porta da // utilizzare per la connessione.
	 * Con 0 la porta viene scelta // automaticamente. // Altrimenti avrebbe
	 * tentato di serializzare l'oggetto e di // passarlo come copia al server.
	 * // In questo caso non devo associare un identificativo // all'oggetto (in
	 * quanto il riferimento remoto verrà  passato // al server). remoteRef =
	 * (RMIClientInterface) UnicastRemoteObject.exportObject(client, 0); }
	 */
	@Override
	public void loginPlayer(String nickname) throws NetworkException {

		try {
			mSessionToken = server.loginPlayer(nickname, this);
		} catch (LoginException e) {
			throw e;
		} catch (IOException e) {
			throw new NetworkException(e);
		}
		/*
		 * try { client.setPlayerName(nickname); server.addClient(remoteRef); }
		 * catch (RemoteException e) { e.printStackTrace(); }
		 */
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
			server.joinFirstAvailableRoom(mSessionToken);
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
			server.sendChatMessage(mSessionToken, receiver, message);
		} catch (RemoteException e) {
			throw new NetworkException(e);
		} catch (PlayerNotFound e) {
			getController().onActionNotValid(ErrorCodes.ERROR_CHAT_PLAYER_NOT_FOUND);
		} catch (IOException e) {
			getController().onActionNotValid(ErrorCodes.ERROR_GENERIC_SERVER_ERROR);
		}
	}

	private String playerName;

	@Override
	public void notify(String object) throws RemoteException {
		// System.out.println("Ho ricevuto il messaggio: " + object);
		System.out.println(object);
	}

	@Override
	public String getPlayerName() throws RemoteException {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) throws RemoteException {
		this.playerName = playerName;
	}

}
