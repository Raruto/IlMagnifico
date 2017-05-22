package com.server.rmi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import com.client.rmi.RMIClient;
import com.client.rmi.RMIClientInterface;
import com.exceptions.LoginException;
import com.server.AbstractServer;
import com.server.IServer;
import com.server.RemotePlayer;
import com.server.Server;
import com.server.ServerException;

/**
 * This class is built on top of {@link AbstractServer} and let Server to
 * communicate whit RMIClients.
 */
public class RMIServer extends AbstractServer implements RMIServerInterface {

	/**
	 * Internal cache that maps all logged used with an unique session token
	 * that identify the single player. This is required in order to identify
	 * the rmi player when he is making a new request to the server.
	 */
	private final HashMap<String, String> mSessionTokens;

	//private RMIServerImplementation serverImplementation;

	/**
	 * Public constructor.
	 * 
	 * @param controller
	 *            server interface to communicate with him.
	 */
	public RMIServer(IServer controller) {
		super(controller);
		mSessionTokens = new HashMap<>();
	}

	/*
	 * public RMIServer(Server server) { super(server); try {
	 * serverImplementation = new RMIServerImplementation(); } catch
	 * (RemoteException e) { System.err.println("Errore di connessione: " +
	 * e.getMessage() + "!"); } }
	 */

	// public void startServer(int rmiPort) {
	// initializeServerRegistry(rmiPort);
	// publishRemoteServerObject(serverImplementation);
	// }
	//
	// private void initializeServerRegistry(int rmiPort) {
	// try {
	// LocateRegistry.createRegistry(rmiPort);
	// } catch (RemoteException e) {
	// System.err.println("Registry già  presente!");
	// }
	// }
	//
	// private void publishRemoteServerObject(RMIServerImplementation
	// serverImplementation) {
	// try {
	// Naming.rebind("Server", serverImplementation);
	// System.out.println("[RMI Server] OK");
	// } catch (MalformedURLException e) {
	// System.err.println("Impossibile registrare l'oggetto indicato!");
	// } catch (RemoteException e) {
	// System.err.println("Errore di connessione: " + e.getMessage() + "!");
	// }
	// }

	/**
	 * Start the RMIServer connection.
	 * 
	 * @param port
	 *            number of the port to use.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	@Override
	public void startServer(int port) throws ServerException {
		Registry registry = createOrLoadRegistry(port);
		try {
			registry.rebind("Server", this);
			UnicastRemoteObject.exportObject(this, port);
			//Debug.verbose("Server successfully initialized");
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
			//Debug.debug("RMI Registry already exists", e);
		}
		try {
			return LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			//Debug.debug("RMI Registry not found", e);
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
	private RemotePlayer getPlayer(String sessionToken) {
		return getController().getPlayer(mSessionTokens.get(sessionToken));
	}

	/**
	 * Remote method to login a new player to the server.
	 * 
	 * @param nickname
	 *            to use for login.
	 * @param player
	 *            that is trying to login.
	 * @return current session token that identify uniquely this user on
	 *         RMIServer.
	 * @throws LoginException
	 *             if provided nickname is already in use.
	 * @throws RemoteException
	 *             if server is not reachable.
	 */
	@Override
	public String loginPlayer(String nickname, RMIClientInterface player) throws IOException {
		getController().loginPlayer(nickname, new RMIPlayer(player));
		// generate new unique session token
		String sessionToken = UUID.randomUUID().toString();
		mSessionTokens.put(sessionToken, nickname);
		return sessionToken;
	}

	/**
	 * Remote method to join the player to the first available room.
	 * 
	 * @param sessionToken
	 *            of the player that is making the request.
	 * @throws JoinRoomException
	 *             if no available room is found.
	 * @throws RemoteException
	 *             if server is not reachable.
	 */
	@Override
	public void joinFirstAvailableRoom(String sessionToken) throws IOException {
		getController().joinFirstAvailableRoom(getPlayer(sessionToken));
	}

	@Override
	public void sendChatMessage(String sessionToken, String receiver, String message) throws IOException {
		// TODO Auto-generated method stub
		
	}

	private static final String SERVER_ID = "[SERVER]";

	private ArrayList<RemotePlayer> players = new ArrayList<RemotePlayer>();
	//private ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();

	@Override
	public void addClient(RMIClientInterface client) throws RemoteException {
		send(client.getPlayerName() + " has joined.");
		players.add(new RemotePlayer(client));
		//clients.add(client);
	}

	@Override
	public void send(String message) throws RemoteException {
		Iterator<RemotePlayer> itr = players.iterator();
		
		while (itr.hasNext()) {
			try {
				itr.next().getClientInterface().notify(SERVER_ID + " " + message);
			} catch (ConnectException e) {
				itr.remove();
				System.out.println("Client rimosso!");
			}
		}

		/*
		Iterator<ClientInterface> clientIterator = clients.iterator();
		while (clientIterator.hasNext()) {
			try {
				clientIterator.next().notify(SERVER_ID + " " + message);
			} catch (ConnectException e) {
				clientIterator.remove();
				System.out.println("Client rimosso!");
			}
		}
	*/
	}	



}