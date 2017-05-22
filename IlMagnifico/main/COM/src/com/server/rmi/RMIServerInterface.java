package com.server.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.client.rmi.RMIClient;
import com.client.rmi.RMIClientInterface;
import com.exceptions.JoinRoomException;
import com.exceptions.LoginException;

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

//	/**
//	 * Remote method to create a new room on the server.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param maxPlayers
//	 *            that the player want to add in this room.
//	 * @throws CreateRoomException
//	 *             if another room has been created in the meanwhile on the
//	 *             server.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 * @return configuration bundle that contains all default configurations.
//	 */
//	Configuration createNewRoom(String sessionToken, int maxPlayers) throws IOException;
//
//	/**
//	 * Remote method to apply the provided configuration on the server.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param configuration
//	 *            that should be applied to the room.
//	 * @throws InvalidConfigurationException
//	 *             if configuration is not valid.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void applyGameConfiguration(String sessionToken, Configuration configuration) throws IOException;
//
//	/**
//	 * Remote method to draw a politic card from the deck.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @throws PoliticCardAlreadyDrawn
//	 *             if politic card has been already drawn in this turn.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void drawPoliticCard(String sessionToken) throws IOException;
//
//	/**
//	 * Remote method to retrieve current player action list.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void getActionList(String sessionToken) throws IOException;
//
//	/**
//	 * Remote method to elect a councillor.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param councilor
//	 *            to add to the region balcony.
//	 * @param region
//	 *            where the balcony to satisfy is located.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws MainActionNotAvailable
//	 *             if player has no main action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void electCouncillor(String sessionToken, Councilor councilor, String region) throws IOException;
//
//	/**
//	 * Remote method to acquire a business permit tile.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param politicCards
//	 *            list of politic cards to use to satisfy the balcony.
//	 * @param region
//	 *            where the balcony to satisfy is placed.
//	 * @param permitTileIndex
//	 *            index of the permit tile to acquire.
//	 * @throws BalconyUnsatisfiable
//	 *             if none of the given politic cards can satisfy this balcony.
//	 * @throws NotEnoughCoins
//	 *             if player has no enough coins to satisfy this balcony.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws MainActionNotAvailable
//	 *             if player has no main action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void acquireBusinessPermitTile(String sessionToken, List<PoliticCard> politicCards, String region,
//			int permitTileIndex) throws IOException;
//
//	/**
//	 * Remote method to build an emporium using a business permit tile.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param businessPermitTile
//	 *            to use to build the emporium.
//	 * @param city
//	 *            where the emporium should be built.
//	 * @throws CityNotFound
//	 *             if city is not found.
//	 * @throws CityNotValid
//	 *             if provided business permit tile cannot be used to build an
//	 *             emporium on this city.
//	 * @throws EmporiumAlreadyBuilt
//	 *             if player has already built an emporium on this city.
//	 * @throws NotEnoughAssistants
//	 *             if player has no enough assistants for building this
//	 *             emporium.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws MainActionNotAvailable
//	 *             if player has no main action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void buildEmporiumWithBusinessPermitTile(String sessionToken, BusinessPermitTile businessPermitTile, String city)
//			throws IOException;
//
//	/**
//	 * Remote method to build an emporium with the help of the king.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param politicCards
//	 *            list of politic cards to use to satisfy the king's balcony.
//	 * @param cities
//	 *            list of linked cities which represent the king's movements.
//	 * @throws BalconyUnsatisfiable
//	 *             if none of the given politic cards can satisfy this balcony.
//	 * @throws NotEnoughCoins
//	 *             if player has no enough coins to satisfy this balcony and
//	 *             move the king.
//	 * @throws NotEnoughAssistants
//	 *             if player has no enough assistants for building this
//	 *             emporium.
//	 * @throws CityNotFound
//	 *             if a city is not recognized or cannot find current king city.
//	 * @throws RouteNotValid
//	 *             is provided route is not valid or king is not in initial
//	 *             city.
//	 * @throws EmporiumAlreadyBuilt
//	 *             if player has already built an emporium on this city.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws MainActionNotAvailable
//	 *             if player has no main action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void buildEmporiumWithKingHelp(String sessionToken, List<PoliticCard> politicCards, List<String> cities)
//			throws IOException;
//
//	/**
//	 * Remote method to engage an assistant.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @throws NotEnoughCoins
//	 *             if player has no enough coins to engage the assistant.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws FastActionNotAvailable
//	 *             if player has no fast action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void engageAssistant(String sessionToken) throws IOException;
//
//	/**
//	 * Remote method to change business permit tiles of a region.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param region
//	 *            where the business permit tiles should be changed.
//	 * @throws NotEnoughAssistants
//	 *             if player has no enough assistants.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws FastActionNotAvailable
//	 *             if player has no fast action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void changeBusinessPermitTiles(String sessionToken, String region) throws IOException;
//
//	/**
//	 * Remote method to send an assistant to elect a councillor.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param councilor
//	 *            that should be elected.
//	 * @param region
//	 *            where the councillor should be added.
//	 * @throws NotEnoughAssistants
//	 *             if player has no enough assistants.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws FastActionNotAvailable
//	 *             if player has no fast action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void sendAssistantElectCouncillor(String sessionToken, Councilor councilor, String region) throws IOException;
//
//	/**
//	 * Remote method to perform an additional main action.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @throws NotEnoughAssistants
//	 *             if player has no enough assistants.
//	 * @throws PoliticCardNotYetDrawn
//	 *             if player should draw a politic card before.
//	 * @throws FastActionNotAvailable
//	 *             if player has no fast action available.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void performAdditionalMainAction(String sessionToken) throws IOException;
//
//	/**
//	 * Earn first special reward.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param cities
//	 *            where the player has built an emporium and want to retrieve
//	 *            the reward.
//	 * @throws CityNotFound
//	 *             if one of the provided city is not found on the game state.
//	 * @throws CityNotValid
//	 *             if a city is provided more than one time, if the player has
//	 *             no emporium in one city or if the reward of that city let
//	 *             player to move along the nobility track.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void earnFirstSpecialRewards(String sessionToken, List<String> cities) throws IOException;
//
//	/**
//	 * Earn second special reward.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param regions
//	 *            list of region where the permit tiles are placed.
//	 * @param indices
//	 *            list of indices that represent the position of the visible
//	 *            permit tiles.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void earnSecondSpecialRewards(String sessionToken, List<String> regions, List<Integer> indices) throws IOException;
//
//	/**
//	 * Earn third special reward.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param businessPermitTiles
//	 *            list of player's business permit tiles.
//	 * @throws NotYourTurnException
//	 *             if is not current game turn of the player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void earnThirdSpecialRewards(String sessionToken, List<BusinessPermitTile> businessPermitTiles) throws IOException;
//
//	/**
//	 * Remote method to sell a politic card over the market.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param politicCard
//	 *            that should be sold.
//	 * @param price
//	 *            that the player would get to sell the card.
//	 * @throws ItemNotFound
//	 *             if the provided item is not found on player repository.
//	 * @throws ItemAlreadyOnSale
//	 *             if the provided item is already on sale.
//	 * @throws NotYourTurnException
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void sellPoliticCard(String sessionToken, PoliticCard politicCard, int price) throws IOException;
//
//	/**
//	 * Remote method to sell a business permit tile over the market.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param businessPermitTile
//	 *            that should be sold.
//	 * @param price
//	 *            that the player would get to sell the card.
//	 * @throws ItemNotFound
//	 *             if the provided item is not found on player repository.
//	 * @throws ItemAlreadyOnSale
//	 *             if the provided item is already on sale.
//	 * @throws NotYourTurnException
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void sellBusinessPermitTile(String sessionToken, BusinessPermitTile businessPermitTile, int price)
//			throws IOException;
//
//	/**
//	 * Remote method to sell an assistant over the market.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param price
//	 *            that the player would get to sell the assistant.
//	 * @throws ItemNotFound
//	 *             if the provided item is not found on player repository.
//	 * @throws ItemAlreadyOnSale
//	 *             if the provided item is already on sale.
//	 * @throws NotYourTurnException
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void sellAssistant(String sessionToken, int price) throws IOException;
//
//	/**
//	 * Remote method to buy an item of the market.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @param marketId
//	 *            id of the item the player would buy.
//	 * @throws ItemNotFound
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws ItemAlreadySold
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws NotEnoughCoins
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws NotYourTurnException
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void buyItem(String sessionToken, String marketId) throws IOException;
//
//	/**
//	 * Remote method to end player turn.
//	 * 
//	 * @param sessionToken
//	 *            of the player that is making the request.
//	 * @throws NotYourTurnException
//	 *             if the player that is making the request is not the current
//	 *             player.
//	 * @throws RemoteException
//	 *             if server is not reachable.
//	 */
//	void endTurn(String sessionToken) throws IOException;

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

	public void addClient(RMIClientInterface client) throws RemoteException;

	public void send(String message) throws RemoteException;

	//public String loginPlayer(String nickname, RMIClient rmiClient) throws LoginException;
}
