package com.protocol.socket;

import java.util.List;

import com.exceptions.CreateRoomException;
import com.exceptions.JoinRoomException;
import com.exceptions.LoginException;

/**
 * This interface is used as callback for communication from the socket protocol to the server.
 */
public interface ServerSocketProtocolInt {

    /**
     * Try to login the player into server with the given nickname.
     * @param nickname to use.
     * @throws LoginException if another player with the same nickname is already logged.
     */
    void loginPlayer(String nickname) throws LoginException;

    /**
     * Try to join an existing room.
     * @throws JoinRoomException if no available room is found.
     */
    void joinRoom() throws JoinRoomException;

    /**
     * Try to create a room on the server.
     * @param maxPlayers to set.
     * @return a configuration bundle that contains all default configurations.
     * @throws CreateRoomException if another room has been created in the meanwhile.
     */
    //Configuration createRoom(int maxPlayers) throws CreateRoomException;

    /**
     * Try to apply the provided configuration.
     * @param configuration to set.
     * @throws InvalidConfigurationException if the configuration is not valid.
     */
    //void applyConfiguration(Configuration configuration) throws InvalidConfigurationException;

    /**
     * Draw a politic card.
     */
    void drawPoliticCard();

    /**
     * Ask for the action list.
     */
    void sendActionList();

    /**
     * Elect a councillor.
     * @param councilor to elect.
     * @param region where it should be elected.
     */
    //void electCouncillor(Councilor councilor, String region);

    /**
     * Acquire a business permit tile.
     * @param politicCards to use to satisfy the balcony.
     * @param region where the permit tile is placed.
     * @param permitTileIndex index of the permit tile to take.
     */
    //void acquireBusinessPermitTile(List<PoliticCard> politicCards, String region, int permitTileIndex);

    /**
     * Build an emporium with a business permit tile.
     * @param businessPermitTile to use.
     * @param city where the emporium should be built.
     */
    //void buildEmporiumWithBusinessPermitTile(BusinessPermitTile businessPermitTile, String city);

    /**
     * Build an emporium with the help of the king.
     * @param politicCards to use to satisfy the king's balcony.
     * @param cities that represent the cities where the king should move along side.
     */
    //void buildEmporiumWithKingHelp(List<PoliticCard> politicCards, List<String> cities);

    /**
     * Engage an assistant.
     */
    void engageAssistant();

    /**
     * Change the visible permit tiles of a region.
     * @param region where the visible permit tiles should be changed.
     */
    void changeBusinessPermitTiles(String region);

    /**
     * Send an assistant to elect a councillor in the given region.
     * @param councilor to elect.
     * @param region where the councillor should be elected.
     */
    //void sendAssistantElectCouncillor(Councilor councilor, String region);

    /**
     * Buy an additional main action.
     */
    void performAdditionalMainAction();

    /**
     * Earn a first special rewards.
     * @param cities where the player want to takes the reward.
     */
    void earnFirstSpecialRewards(List<String> cities);

    /**
     * Earn a second special rewards.
     * @param regions where the player want to take the visible permit tile.
     * @param indices of the permit tiles that the player want to take.
     */
    void earnSecondSpecialRewards(List<String> regions, List<Integer> indices);

    /**
     * Earn a third special rewards.
     * @param businessPermitTiles list of permit tiles from which the player want to take the rewards.
     */
    //void earnThirdSpecialRewards(List<BusinessPermitTile> businessPermitTiles);

    /**
     * Sell a politic card on the market.
     * @param politicCard to sell.
     * @param price to set.
     */
    //void sellPoliticCard(PoliticCard politicCard, int price);

    /**
     * Sell a business permit tile on the market.
     * @param businessPermitTile to sell.
     * @param price to set.
     */
    //void sellBusinessPermitTile(BusinessPermitTile businessPermitTile, int price);

    /**
     * Sell an assistant on the market.
     * @param price to set.
     */
    void sellAssistant(int price);

    /**
     * Buy an item from the market.
     * @param marketId that identify the item to buy.
     */
    void buyItem(String marketId);

    /**
     * End turn.
     */
    void endTurn();

    /**
     * Dispatch a chat message to the receiver or to all players.
     * @param receiver nickname of the receiver, null if message is public.
     * @param message body of the message.
     */
    void sendChatMessage(String receiver, String message);
}