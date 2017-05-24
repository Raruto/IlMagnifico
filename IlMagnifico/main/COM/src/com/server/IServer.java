package com.server;

import java.io.IOException;

import com.exceptions.JoinRoomException;
import com.exceptions.LoginException;

import model.exceptions.PlayerNotFound;

/**
 * Server interface to send request to the server.
 */
public interface IServer {

	/**
	 * Login player with nickname.
	 * 
	 * @param nickname
	 *            of the player that would login.
	 * @param remotePlayer
	 *            reference to the player that made the request.
	 * @throws LoginException
	 *             if a player with this nickname already exists.
	 */
	void loginPlayer(String nickname, RemotePlayer remotePlayer) throws LoginException;

	/**
	 * Get the player associated to required nickname.
	 * 
	 * @param nickname
	 *            of the player to retrieve.
	 * @return the associated remote player if found.
	 */
	RemotePlayer getPlayer(String nickname);

	/**
	 * Join player to the first available room.
	 * 
	 * @param remotePlayer
	 *            that would join.
	 * @throws JoinRoomException
	 *             if no available room has been found.
	 */
	void joinFirstAvailableRoom(RemotePlayer remotePlayer) throws JoinRoomException;

	/**
	 * Create a new room on server.
	 * 
	 * @param remotePlayer
	 *            that made the request.
	 * @param maxPlayers
	 *            that player would like to add in the room.
	 * @throws CreateRoomException
	 *             if another player has created a new room in the meanwhile.
	 * @return configuration bundle that contains all default configurations.
	 */
	// Configuration createNewRoom(RemotePlayer remotePlayer, int maxPlayers)
	// throws CreateRoomException;

	/**
	 * Check and apply the provided configuration into the player's room.
	 * 
	 * @param room
	 *            where should be applied the configuration.
	 * @param configuration
	 *            that should be validated and applied.
	 * @throws InvalidConfigurationException
	 *             if provided configuration is not valid.
	 */
	// void applyGameConfiguration(Room room, Configuration configuration)
	// throws InvalidConfigurationException;

	public void sendChatMessage(RemotePlayer player, String receiver, String message) throws PlayerNotFound;

	public void sendChatMessage(String author, String message, boolean privateMessage) throws IOException;
}