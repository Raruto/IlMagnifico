package com.protocol.socket;

/**
 * This class represent all constants used by the socket protocol for communication.
 * See {@link ClientProtocol} and {@link ServerProtocol} for detail about the protocol.
 */
/*package-local*/ class ProtocolConstants {

    /**
     * Client request headers.
     */
    /*package-local*/ static final String LOGIN_REQUEST = "loginRequest";
    /*package-local*/ static final String JOIN_ROOM_REQUEST = "joinRequest";
    /*package-local*/ static final String CREATE_ROOM_REQUEST = "createRoomRequest";
    /*package-local*/ static final String APPLY_CONFIGURATION_REQUEST = "applyConfigurationRequest";
    /*package-local*/ static final String GET_ACTION_LIST = "getActionList";
    /*package-local*/ static final String DRAW_POLITIC_CARD = "drawPoliticCard";
    /*package-local*/ static final String ELECT_COUNCILLOR = "electCouncillor";
    /*package-local*/ static final String ACQUIRE_BUSINESS_PERMIT_TILE = "acquireBusinessPermitTile";
    /*package-local*/ static final String BUILD_EMPORIUM_WITH_BUSINESS_PERMIT_TILE = "buildEmporiumWithBusinessPermitTile";
    /*package-local*/ static final String BUILD_EMPORIUM_WITH_KING_HELP = "buildEmporiumWithKingHelp";
    /*package-local*/ static final String ENGAGE_ASSISTANT = "engageAssistant";
    /*package-local*/ static final String CHANGE_BUSINESS_PERMIT_TILES = "changeBusinessPermitTiles";
    /*package-local*/ static final String SEND_ASSISTANT_TO_ELECT_COUNCILLOR = "sendAssistantToElectCouncillor";
    /*package-local*/ static final String PERFORM_ADDITIONAL_MAIN_ACTION = "performAdditionalMainAction";
    /*package-local*/ static final String EARN_FIRST_SPECIAL_REWARDS = "earnFirstSpecialRewards";
    /*package-local*/ static final String EARN_SECOND_SPECIAL_REWARDS = "earnSecondSpecialRewards";
    /*package-local*/ static final String EARN_THIRD_SPECIAL_REWARDS = "earnThirdSpecialRewards";
    /*package-local*/ static final String SELL_POLITIC_CARD = "sellPoliticCard";
    /*package-local*/ static final String SELL_BUSINESS_PERMIT_TILE = "sellBusinessPermitTile";
    /*package-local*/ static final String SELL_ASSISTANT = "sellAssistant";
    /*package-local*/ static final String BUY_ITEM = "buyItem";
    /*package-local*/ static final String END_TURN = "endTurn";
    /*package-local*/ static final String CHAT_MESSAGE = "chatMessage";

    /**
     * Server response headers.
     */
    /*package-local*/ static final String ACTION_NOT_VALID = "actionNotValid";
    /*package-local*/ static final String GAME_STARTED = "gameStarted";
    /*package-local*/ static final String GAME_TURN_STARTED = "gameTurnStarted";
    /*package-local*/ static final String COUNTDOWN_UPDATED = "countdownUpdated";
    /*package-local*/ static final String POLITIC_CARD_DRAWN = "politicCardDrawn";
    /*package-local*/ static final String ACTION_LIST = "actionList";
    /*package-local*/ static final String COUNCILLOR_ELECTED = "councillorElected";
    /*package-local*/ static final String BUSINESS_PERMIT_TILE_ACQUIRED = "businessPermitTileAcquired";
    /*package-local*/ static final String EMPORIUM_BUILT_WITH_BUSINESS_PERMIT_TILE = "emporiumBuiltWithBusinessPermitTile";
    /*package-local*/ static final String EMPORIUM_BUILT_WITH_KING_HELP = "emporiumBuiltWithKingHelp";
    /*package-local*/ static final String ASSISTANT_ENGAGED = "assistantEngaged";
    /*package-local*/ static final String BUSINESS_PERMIT_TILES_CHANGED = "businessPermitTilesChanged";
    /*package-local*/ static final String ASSISTANT_SENT_TO_ELECT_COUNCILLOR = "assistantSentToElectCouncillor";
    /*package-local*/ static final String ADDITIONAL_MAIN_ACTION_GRANTED = "additionalMainActionGranted";
    /*package-local*/ static final String FIRST_SPECIAL_REWARD_EARNED = "firstSpecialRewardEarned";
    /*package-local*/ static final String SECOND_SPECIAL_REWARD_EARNED = "secondSpecialRewardEarned";
    /*package-local*/ static final String THIRD_SPECIAL_REWARD_EARNED = "thirdSpecialRewardEarned";
    /*package-local*/ static final String MARKET_SESSION_STARTED = "marketSessionStarted";
    /*package-local*/ static final String MARKET_TURN_STARTED = "marketTurnStarted";
    /*package-local*/ static final String MARKET_ITEM_ADDED_ON_SALE = "marketItemAddedOnSale";
    /*package-local*/ static final String MARKET_ITEM_BOUGHT = "marketItemBought";
    /*package-local*/ static final String MARKET_SESSION_FINISHED = "marketSessionFinished";
    /*package-local*/ static final String PLAYER_DISCONNECTED = "playerDisconnected";
    /*package-local*/ static final String LAST_TURN_STARTED = "lastTurnStarted";
    /*package-local*/ static final String GAME_ENDED = "gameEnded";

    /**
     * Server response codes for base actions.
     */
    /*package-local*/ static final int RESPONSE_OK = 200;
    /*package-local*/ static final int RESPONSE_PLAYER_ALREADY_EXISTS = 401;
    /*package-local*/ static final int RESPONSE_NO_ROOM_AVAILABLE = 402;
    /*package-local*/ static final int RESPONSE_PLAYER_FORCE_JOINED = 403;
    /*package-local*/ static final int RESPONSE_CONFIGURATION_NOT_VALID = 418;

    /**
     * Private constructor. This class has not been designed to be instantiated.
     */
    private ProtocolConstants() {
        // hide constructor
    }
}