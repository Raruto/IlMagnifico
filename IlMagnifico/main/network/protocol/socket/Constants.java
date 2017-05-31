package main.network.protocol.socket;

/**
 * This class represent all constants used by the socket protocol for
 * communication. See {@link ClientProtocol} and {@link ServerProtocol} for
 * detail about the protocol.
 */
public class Constants {

	/**
	 * Client request headers.
	 */
	public static final String LOGIN_REQUEST = "loginRequest";
	public static final String JOIN_ROOM_REQUEST = "joinRequest";
	public static final String CREATE_ROOM_REQUEST = "createRoomRequest";
	public static final String APPLY_CONFIGURATION_REQUEST = "applyConfigurationRequest";
	public static final String GET_ACTION_LIST = "getActionList";
	public static final String DRAW_POLITIC_CARD = "drawPoliticCard";
	public static final String ELECT_COUNCILLOR = "electCouncillor";
	public static final String ACQUIRE_BUSINESS_PERMIT_TILE = "acquireBusinessPermitTile";
	public static final String BUILD_EMPORIUM_WITH_BUSINESS_PERMIT_TILE = "buildEmporiumWithBusinessPermitTile";
	public static final String BUILD_EMPORIUM_WITH_KING_HELP = "buildEmporiumWithKingHelp";
	public static final String ENGAGE_ASSISTANT = "engageAssistant";
	public static final String CHANGE_BUSINESS_PERMIT_TILES = "changeBusinessPermitTiles";
	public static final String SEND_ASSISTANT_TO_ELECT_COUNCILLOR = "sendAssistantToElectCouncillor";
	public static final String PERFORM_ADDITIONAL_MAIN_ACTION = "performAdditionalMainAction";
	public static final String EARN_FIRST_SPECIAL_REWARDS = "earnFirstSpecialRewards";
	public static final String EARN_SECOND_SPECIAL_REWARDS = "earnSecondSpecialRewards";
	public static final String EARN_THIRD_SPECIAL_REWARDS = "earnThirdSpecialRewards";
	public static final String SELL_POLITIC_CARD = "sellPoliticCard";
	public static final String SELL_BUSINESS_PERMIT_TILE = "sellBusinessPermitTile";
	public static final String SELL_ASSISTANT = "sellAssistant";
	public static final String BUY_ITEM = "buyItem";
	public static final String END_TURN = "endTurn";
	public static final String CHAT_MESSAGE = "chatMessage";

	/**
	 * Server response headers.
	 */
	public static final String ACTION_NOT_VALID = "actionNotValid";
	public static final String GAME_STARTED = "gameStarted";
	public static final String GAME_TURN_STARTED = "gameTurnStarted";
	public static final String COUNTDOWN_UPDATED = "countdownUpdated";
	public static final String POLITIC_CARD_DRAWN = "politicCardDrawn";
	public static final String ACTION_LIST = "actionList";
	public static final String COUNCILLOR_ELECTED = "councillorElected";
	public static final String BUSINESS_PERMIT_TILE_ACQUIRED = "businessPermitTileAcquired";
	public static final String EMPORIUM_BUILT_WITH_BUSINESS_PERMIT_TILE = "emporiumBuiltWithBusinessPermitTile";
	public static final String EMPORIUM_BUILT_WITH_KING_HELP = "emporiumBuiltWithKingHelp";
	public static final String ASSISTANT_ENGAGED = "assistantEngaged";
	public static final String BUSINESS_PERMIT_TILES_CHANGED = "businessPermitTilesChanged";
	public static final String ASSISTANT_SENT_TO_ELECT_COUNCILLOR = "assistantSentToElectCouncillor";
	public static final String ADDITIONAL_MAIN_ACTION_GRANTED = "additionalMainActionGranted";
	public static final String FIRST_SPECIAL_REWARD_EARNED = "firstSpecialRewardEarned";
	public static final String SECOND_SPECIAL_REWARD_EARNED = "secondSpecialRewardEarned";
	public static final String THIRD_SPECIAL_REWARD_EARNED = "thirdSpecialRewardEarned";
	public static final String MARKET_SESSION_STARTED = "marketSessionStarted";
	public static final String MARKET_TURN_STARTED = "marketTurnStarted";
	public static final String MARKET_ITEM_ADDED_ON_SALE = "marketItemAddedOnSale";
	public static final String MARKET_ITEM_BOUGHT = "marketItemBought";
	public static final String MARKET_SESSION_FINISHED = "marketSessionFinished";
	public static final String PLAYER_DISCONNECTED = "playerDisconnected";
	public static final String LAST_TURN_STARTED = "lastTurnStarted";
	public static final String GAME_ENDED = "gameEnded";

	/**
	 * Server response codes for base actions.
	 */
	public static final int RESPONSE_OK = 200;
	public static final int RESPONSE_PLAYER_ALREADY_EXISTS = 401;
	public static final int RESPONSE_NO_ROOM_AVAILABLE = 402;
	public static final int RESPONSE_PLAYER_FORCE_JOINED = 403;
	public static final int RESPONSE_CONFIGURATION_NOT_VALID = 418;

	/**
	 * Private constructor. This class has not been designed to be instantiated.
	 */
	private Constants() {
		// hide constructor
	}
}