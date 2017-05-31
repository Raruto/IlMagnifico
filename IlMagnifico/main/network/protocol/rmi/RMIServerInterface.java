package main.network.protocol.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;
import main.network.server.game.GameException;
import main.network.server.game.UpdateStats;

/**
 * Remote interface for RemoteMethodInvocation from client to server.
 */
public interface RMIServerInterface extends Remote {

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
	String loginPlayer(String nickname, RMIClientInterface player) throws IOException;

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
	void joinFirstAvailableRoom(String sessionToken) throws IOException;

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
	void sendChatMessage(String sessionToken, String receiver, String message) throws IOException;

	void performGameAction(String sessionToken, UpdateStats requestedAction) throws RemoteException, GameException;

	/**
	 * Metodo per il "debug"
	 * 
	 * @param object
	 * @throws RemoteException
	 */
	public void send(Object object) throws RemoteException;
}
