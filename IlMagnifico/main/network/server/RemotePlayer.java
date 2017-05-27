package main.network.server;

import main.model.Giocatore;
import main.network.NetworkException;
import main.network.server.game.Room;


/**
 * Abstract extension of {@link Player}. This implementation can communicate to his referenced client.
 */
public abstract class RemotePlayer extends Giocatore {

    /**
     * Reference to room where player is joined.
     */
    private transient Room mRoom;

    /**
     * Abstract constructor.
     */
    protected RemotePlayer() {

    }

    /**
     * Set room reference.
     * @param room where player is joined.
     */
    /*package-local*/ void setRoom(Room room) {
        mRoom = room;
    }

    /**
     * Get room reference where player is joined.
     * @return the room reference if player is in a room, null otherwise.
     */
    public Room getRoom() {
        return mRoom;
    }

//    /**
//     * Dispatch base game session to the player at the beginning of the match.
//     * @param baseGame to dispatch to the player.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void dispatchGameSession(BaseGame baseGame) throws NetworkException;
//
//    /**
//     * Notify player that a new game turn is started.
//     * @param nickname of the player that is starting the turn.
//     * @param seconds that the player has to make the actions.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onGameTurnStarted(String nickname, int seconds) throws NetworkException;
//
//    /**
//     * Notify player of remaining seconds to make the turn actions.
//     * @param remainingSeconds remaining time in seconds to make the actions.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onUpdateTurnCountdown(int remainingSeconds) throws NetworkException;
//
//    /**
//     * Notify player that a politic card has been drawn.
//     * @param updateState to send to the player.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onPoliticCardDrawn(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Send action list to the player.
//     * @param actionList that should be sent.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onActionList(ActionList actionList) throws NetworkException;
//
//    /**
//     * Notify player that someone has made a main action.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onCouncillorElected(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has acquired a business permit tile.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onBusinessPermitTileAcquired(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has built an emporium using a business permit tile.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onEmporiumBuiltWithPermitTile(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has built an emporium with king helps.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onEmporiumBuiltWithKingHelp(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has engaged an assistant.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onAssistantEngaged(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has changed the business permit tiles of a region.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onBusinessPermitTilesChanged(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has sent an assistant to elect a councillor.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onAssistantSentToElectCouncillor(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has obtained an additional main action.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onAdditionalMainActionGranted(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has earned one or more first special rewards.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onFirstSpecialRewardsEarned(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has earned one or more second special rewards.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onSecondSpecialRewardsEarned(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that someone has earned one or more third special rewards.
//     * @param updateState that contains all changes to the server game state.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onThirdSpecialRewardsEarned(UpdateState updateState) throws NetworkException;
//
//    /**
//     * Notify player that market session is started.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onMarketSessionStarted() throws NetworkException;
//
//    /**
//     * Notify player that a new market turn is started.
//     * @param nickname of the player that is starting the turn.
//     * @param seconds that the player has to make the actions.
//     * @param mode of the market turn.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onMarketTurnStarted(String nickname, int seconds, MarketTurn.Mode mode) throws NetworkException;
//
//    /**
//     * Notify player that a new item has been added to the market.
//     * @param item that has been added to the market.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onMarketItemAddedOnSale(Item item) throws NetworkException;
//
//    /**
//     * Notify player that a market item has been bought.
//     * @param nickname of the player that bought the item.
//     * @param marketId id of the market item that has been bought.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onMarketItemBought(String nickname, String marketId) throws NetworkException;
//
//    /**
//     * Notify player that market session is finished.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onMarketSessionFinished() throws NetworkException;

    /**
     * Send a chat message to the player.
     * @param author nickname of the player that sent the message.
     * @param message that the author has sent.
     * @param privateMessage if message is private, false if public.
     * @throws NetworkException if client is not reachable.
     */
    public abstract void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException;

//    /**
//     * Notify player that another player has disconnected.
//     * @param nickname of the disconnected player.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onPlayerDisconnected(String nickname) throws NetworkException;
//
//    /**
//     * Notify player that a player has built all his emporiums and the last turn is starting.
//     * @param nickname of the player has built all his emporiums
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onLastTurn(String nickname) throws NetworkException;
//
//    /**
//     * Notify player that the game is finished and dispatch all last update of all players.
//     * @param updateStates of all players (bonus and end-game rewards).
//     * @param ranking list of players nickname sorted by winner to loser.
//     * @throws NetworkException if client is not reachable.
//     */
//    public abstract void onGameEnd(UpdateState[] updateStates, List<String> ranking) throws NetworkException;
}