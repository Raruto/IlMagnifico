package com.server.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.client.rmi.RMIClientInterface;
import com.exceptions.LoginException;
import com.server.AbstractServer;
import com.server.IServer;
import com.server.RemotePlayer;
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
	private final HashMap<String, String> sessionTokens;

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
	private RemotePlayer getPlayer(String sessionToken) {
		return getController().getPlayer(sessionTokens.get(sessionToken));
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
		sessionTokens.put(sessionToken, nickname);
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

	/**
	 * Remote method to send a chat message to all players or to a specific
	 * player.
	 * 
	 * @param sessionToken
	 *            of the player that is making the request.
	 * @param receiver
	 *            nickname of the player that should receive the message. If
	 *            null the message will be dispatched to all players.
	 * @param message
	 *            to send.
	 * @throws PlayerNotFound
	 *             if the receiver is not null and not match any players in the
	 *             room.
	 * @throws RemoteException
	 *             if server is not reachable.
	 */
	@Override
	public void sendChatMessage(String sessionToken, String receiver, String message) throws IOException {
		RemotePlayer remotePlayer = getPlayer(sessionToken);

		// getController().sendChatMessage(remotePlayer.getNickname(), message,
		// false);
		getController().sendChatMessage(remotePlayer, receiver, message);
		// getController().sloginPlayer(nickname, new RMIPlayer(player));

		// remotePlayer.getRoom().sendChatMessage(remotePlayer, receiver,
		// message);

		/*
		 * Iterator<RemotePlayer> itr = players.iterator();
		 * 
		 * while (itr.hasNext()) { try {
		 * itr.next().getClientInterface().notify("[SERVER]" + " " + message); }
		 * catch (ConnectException e) { itr.remove();
		 * System.out.println("Client rimosso!"); } }
		 */

	}

	private ArrayList<RemotePlayer> players = new ArrayList<RemotePlayer>();

	// @Override
	// public void addClient(RMIClientInterface client) throws RemoteException {
	// send(client.getPlayerName() + " has joined.");
	// players.add(new RemotePlayer(client));
	// // clients.add(client);
	// }

	@Override
	public void send(String message) throws RemoteException {

		try {
			getController().sendChatMessage(null, message, false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Iterator<RemotePlayer> itr = players.iterator();
		 * 
		 * while (itr.hasNext()) { try {
		 * itr.next()getClientInterface().notify("[SERVER]" + " " + message); }
		 * catch (ConnectException e) { itr.remove();
		 * System.out.println("Client rimosso!"); } }
		 */

		/*
		 * Iterator<ClientInterface> clientIterator = clients.iterator(); while
		 * (clientIterator.hasNext()) { try {
		 * clientIterator.next().notify(SERVER_ID + " " + message); } catch
		 * (ConnectException e) { clientIterator.remove();
		 * System.out.println("Client rimosso!"); } }
		 */
	}

}