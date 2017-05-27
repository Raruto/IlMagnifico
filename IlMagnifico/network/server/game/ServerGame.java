package network.server.game;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import LorenzoIlMagnifico.Partita;

/**
 * This class represent the main instance of a single game. It will be hosted by
 * the server room and it contains every information. It can be initialized by
 * {@link Builder} class and filled by {@link Configurator} singleton instance.
 * The map and other settings can be configured by the administrator of the game
 * (the creator of the room).
 */
/* package-local */ class ServerGame extends Partita {

//	/**
//	 * Game specific parameters.
//	 */
//	private static final int INITIAL_EMPORIUMS = 10;
//	private static final int INITIAL_POLITIC_CARDS = 6;
//	private static final int ELECTION_COUNCILLOR_COINS_REWARD = 4;
//	private static final int ENGAGE_ASSISTANT_COINS_TO_PAY = 3;
//	private static final int ASSISTANTS_TO_RELEASE_TO_CHANGE_BPT = 1;
//	private static final int ASSISTANTS_TO_SEND_TO_ELECT_COUNCILLOR = 1;
//	private static final int ASSISTANTS_TO_PAY_TO_MAKE_ADDITIONAL_MAIN_ACTION = 3;
//	private static final int COINS_TO_PAY_PER_LINK = 2;
//	private static final int VICTORY_POINTS_FOR_LAST_EMPORIUM = 3;
//
//	/**
//	 * Politic cards deck.
//	 */
//	private final RecyclerCardDeck<PoliticCard> mPoliticCards;
//
//	/**
//	 * Available Bonus cards.
//	 */
//	private HashMap<String, Reward> mBonusCards;
//
//	/**
//	 * Private game constructor. It can be instantiated only by {@link Builder
//	 * class}.
//	 */
//	private ServerGame() {
//		mPoliticCards = new RecyclerCardDeck<>();
//	}
//
//	/**
//	 * Draw a politic card from the deck.
//	 * 
//	 * @param player
//	 *            that is drawing the politic card.
//	 * @return an update state to send to all clients.
//	 */
//	/* package-local */ UpdateState drawPoliticCard(Player player) {
//		PoliticCard politicCard = mPoliticCards.takeLast();
//		player.addPoliticCard(politicCard);
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.addPoliticCard(politicCard);
//		return updateState;
//	}
//
//	/**
//	 * Elect a councillor for a given region board.
//	 * 
//	 * @param player
//	 *            of the player that is going to elect the councillor.
//	 * @param councillor
//	 *            that player is going to elect.
//	 * @param region
//	 *            where the player want to elect the councillor.
//	 * @param addCoins
//	 *            if this action should add coins to player.
//	 * @return an update state to send to all clients.
//	 */
//	/* package-local */ UpdateState electCouncillor(Player player, Councilor councillor, String region,
//			boolean addCoins) {
//		addCouncilor(councillor, region);
//		if (addCoins) {
//			player.addCoins(ELECTION_COUNCILLOR_COINS_REWARD);
//		}
//		// create response for clients
//		UpdateState updateState = new UpdateState(player.getNickname());
//		if (addCoins) {
//			updateState.addCoins(ELECTION_COUNCILLOR_COINS_REWARD);
//		}
//		updateState.changeCouncillor(councillor, region);
//		return updateState;
//	}
//
//	/**
//	 * Acquire a business permit tile.
//	 * 
//	 * @param player
//	 *            that is going to acquire the tile.
//	 * @param politicCards
//	 *            that the player is trying to use to satisfy the balcony.
//	 * @param region
//	 *            where the balcony is placed.
//	 * @param index
//	 *            of the permit tile to acquire.
//	 * @return an update state to send to all clients.
//	 * @throws BalconyUnsatisfiable
//	 *             if none of the given politic cards can satisfy this balcony.
//	 * @throws NotEnoughCoins
//	 *             if player has no enough coins to satisfy this balcony.
//	 */
//	/* package-local */ UpdateState acquireBusinessPermitTile(Player player, GameTurn turn,
//			List<PoliticCard> politicCards, String region, int index) throws LogicException {
//		List<PoliticCard> usedPoliticCards = satisfyBalcony(region, politicCards);
//		int coinsToPay = getCoinsToPayToSatisfyBalcony(usedPoliticCards);
//		player.payCoins(coinsToPay);
//		player.discardPoliticCards(usedPoliticCards);
//		BusinessPermitTile takenBusinessPermitTile = takeBusinessPermitTile(region, index);
//		player.addBusinessPermitTile(takenBusinessPermitTile);
//		mPoliticCards.recycle(usedPoliticCards);
//		// create response for clients
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.addBusinessPermitTile(takenBusinessPermitTile);
//		updateState.addReward(takenBusinessPermitTile);
//		updateState.removeCoins(coinsToPay);
//		updateState.removePoliticCards(usedPoliticCards);
//		updateState.changeBusinessPermitTileAtIndex(getBusinessPermitTile(region, index), region, index);
//		applyReward(player, turn, updateState, takenBusinessPermitTile);
//		return updateState;
//	}
//
//	/**
//	 * Build an emporium on the given city with a business permit tile.
//	 * 
//	 * @param player
//	 *            that is going to build the emporium.
//	 * @param businessPermitTile
//	 *            used to build the emporium.
//	 * @param cityName
//	 *            name of the city where build the emporium
//	 * @return an update state to send to all clients.
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
//	 */
//	/* package-local */ UpdateState buildEmporium(Player player, GameTurn turn, BusinessPermitTile businessPermitTile,
//			String cityName) throws LogicException {
//		City city = getCity(cityName);
//		businessPermitTile.validateCity(city);
//		int assistants = city.getAssistantCountToBuildEmporium(player.getNickname());
//		player.payAssistants(assistants);
//		player.useBusinessPermitTile(businessPermitTile);
//		List<Reward> rewards = new ArrayList<>();
//		rewards.add(city.buildEmporium(player)); // exception will never be
//													// thrown because already
//													// checked.
//		rewards.addAll(getBuildEmporiumRewards(player, city));
//		int victoryPoints = getExtraVictoryPoints(player);
//		player.addVictoryPoints(victoryPoints);
//		// create response for clients
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.addEmporium(cityName);
//		updateState.addRewards(rewards);
//		updateState.removeAssistants(assistants);
//		updateState.removeBusinessPermitTile(businessPermitTile);
//		updateState.addVictoryPoints(victoryPoints);
//		applyRewards(player, turn, rewards, updateState);
//		applyBonus(player, city, updateState);
//		return updateState;
//	}
//
//	/**
//	 * Build an emporium on the given city with the help of the king.
//	 * 
//	 * @param player
//	 *            that is going to build the emporium.
//	 * @param politicCards
//	 *            that the player is trying to use to satisfy the balcony.
//	 * @param cities
//	 *            ordered list of cities which represent the king route. The
//	 *            first city must be the king city.
//	 * @return an update state to send to all clients.
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
//	 */
//	/* package-local */ UpdateState buildEmporium(Player player, GameTurn turn, List<PoliticCard> politicCards,
//			List<String> cities) throws LogicException {
//		List<PoliticCard> usedPoliticCards = satisfyBalcony(AbstractBoard.KING_REGION, politicCards);
//		int coinsToPay = getCoinsToPayToSatisfyBalcony(usedPoliticCards);
//		coinsToPay += getCoinsToPayToMoveKing(cities);
//		String cityWhereBuildEmporium = cities.get(cities.size() - 1);
//		City city = getCity(cityWhereBuildEmporium);
//		int assistants = city.getAssistantCountToBuildEmporium(player.getNickname());
//		if (player.hasEnoughAssistants(assistants)) {
//			player.payCoins(coinsToPay);
//		}
//		player.payAssistants(assistants);
//		List<Reward> rewards = new ArrayList<>();
//		rewards.add(city.buildEmporium(player));
//		rewards.addAll(getBuildEmporiumRewards(player, city));
//		moveKingTo(city);
//		int victoryPoints = getExtraVictoryPoints(player);
//		player.addVictoryPoints(victoryPoints);
//		// create response for clients
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.removeCoins(coinsToPay);
//		updateState.addEmporium(cityWhereBuildEmporium);
//		updateState.addRewards(rewards);
//		updateState.changeKingCity(city.getName());
//		updateState.addVictoryPoints(victoryPoints);
//		applyRewards(player, turn, rewards, updateState);
//		applyBonus(player, city, updateState);
//		return updateState;
//	}
//
//	/**
//	 * Collect all rewards related to all city directly connected with provided
//	 * one where the same player has already built an emporium.
//	 * 
//	 * @param player
//	 *            that has built an emporium.
//	 * @param city
//	 *            where the player has built the emporium.
//	 * @return the list populated of all rewards.
//	 */
//	private List<Reward> getBuildEmporiumRewards(Player player, City city) {
//		return getLinkedCities(city).stream().filter(linkedCity -> linkedCity.hasPlayerAnEmporium(player))
//				.map(City::getReward).collect(Collectors.toCollection(ArrayList::new));
//	}
//
//	/**
//	 * After building an emporium this method check if the player has built all
//	 * emporiums and return the number of victory points he has gained if
//	 * verified.
//	 * 
//	 * @param player
//	 *            that has built an emporium
//	 * @return {@link #VICTORY_POINTS_FOR_LAST_EMPORIUM} if player has built all
//	 *         his emporiums, 0 otherwise.
//	 */
//	private int getExtraVictoryPoints(Player player) {
//		return player.getEmporiums() == 0 ? VICTORY_POINTS_FOR_LAST_EMPORIUM : 0;
//	}
//
//	/**
//	 * Get bonus if player has build an emporium in all cities with the same
//	 * color of in all cities of the same region.
//	 * 
//	 * @param player
//	 *            that has built the emporium.
//	 * @param city
//	 *            where player has built the emporium.
//	 * @return a list of bonus if conditions are verified.
//	 */
//	private Map<String, Reward> getBonus(Player player, City city) {
//		Map<String, Reward> rewards = new HashMap<>();
//		checkTypeBonus(rewards, player, city);
//		checkRegionBonus(rewards, player, city);
//		return rewards;
//	}
//
//	/**
//	 * Check and add all related city-type bonus after building an emporium.
//	 * 
//	 * @param rewards
//	 *            map where all earned bonus should be added.
//	 * @param player
//	 *            that has built the emporium.
//	 * @param city
//	 *            where the player has built the emporium.
//	 */
//	private void checkTypeBonus(Map<String, Reward> rewards, Player player, City city) {
//		String cityType = city.getType();
//		if (!cityType.equals(City.TYPE_KING)) {
//			// iterate all cities of this type
//			for (City c : getAllCitiesByType(cityType)) {
//				if (!c.hasPlayerAnEmporium(player)) {
//					return;
//				}
//			}
//			rewards.put(cityType, mBonusCards.get(cityType));
//		}
//	}
//
//	/**
//	 * Check and add all related region bonus after building an emporium.
//	 * 
//	 * @param rewards
//	 *            map where all earned bonus should be added.
//	 * @param player
//	 *            that has built the emporium.
//	 * @param city
//	 *            where the player has built the emporium.
//	 */
//	private void checkRegionBonus(Map<String, Reward> rewards, Player player, City city) {
//		RegionBoard region = getCityRegion(city);
//		for (City c : region.getCities().values()) {
//			if (!c.hasPlayerAnEmporium(player)) {
//				return;
//			}
//		}
//		rewards.put(region.getRegionType(), mBonusCards.get(region.getRegionType()));
//	}
//
//	/**
//	 * Apply all bonus earned while building an emporium to the player. It will
//	 * add a reward king card for every bonus card earned. If there are no king
//	 * reward cards left, he will gets only the bonus cards.
//	 * 
//	 * @param player
//	 *            that has built the emporium.
//	 * @param city
//	 *            where the player has built the emporium.
//	 * @param updateState
//	 *            to update.
//	 */
//	private void applyBonus(Player player, City city, UpdateState updateState) {
//		Map<String, Reward> bonus = getBonus(player, city);
//		player.addBonus(bonus);
//		updateState.addBonus(bonus);
//		// for every bonus card, take a king reward if found
//		for (int i = 0; i < bonus.size(); i++) {
//			Reward kingReward = mKingBoard.takeKingReward();
//			if (kingReward != null) {
//				player.addKingReward(kingReward);
//				updateState.addKingReward(kingReward);
//			} else {
//				return;
//			}
//		}
//	}
//
//	/**
//	 * Apply a list of rewards to the player. Also the updateState will be
//	 * updated.
//	 * 
//	 * @param player
//	 *            that is earning the reward.
//	 * @param turn
//	 *            current player turn.
//	 * @param updateState
//	 *            that should be updated.
//	 * @param rewards
//	 *            list of rewards that the player is earning.
//	 */
//	private void applyRewards(Player player, GameTurn turn, List<Reward> rewards, UpdateState updateState) {
//		int assistants = 0;
//		int victoryPoints = 0;
//		int nobilityStep = 0;
//		int coins = 0;
//		int politicCards = 0;
//		int mainActions = 0;
//		for (Reward reward : rewards) {
//			if (reward != null) {
//				assistants += reward.getAssistantCount();
//				victoryPoints += reward.getVictoryPointCount();
//				nobilityStep += reward.getNobilityStepCount();
//				coins += reward.getCoinsCount();
//				politicCards += reward.getPoliticCardCount();
//				mainActions += reward.getMainActionCount();
//			}
//		}
//		applyReward(player, turn, updateState,
//				new Reward(assistants, victoryPoints, nobilityStep, coins, politicCards, mainActions));
//	}
//
//	/**
//	 * Apply a single reward to a player. Also the updateState will be updated.
//	 * 
//	 * @param player
//	 *            that is earning the reward.
//	 * @param turn
//	 *            current player turn.
//	 * @param updateState
//	 *            that should be updated.
//	 * @param reward
//	 *            that the player is earning.
//	 */
//	private void applyReward(Player player, GameTurn turn, UpdateState updateState, Reward reward) {
//		int assistants = reward.getAssistantCount();
//		int victoryPoints = reward.getVictoryPointCount();
//		int nobilityStep = reward.getNobilityStepCount();
//		int coins = reward.getCoinsCount();
//		int politicCards = reward.getPoliticCardCount();
//		int mainActions = reward.getMainActionCount();
//		if (nobilityStep > 0) {
//			NobilityReward nobilityReward = mKingBoard.getNobilityTrackReward(player.getNobilityTrackPosition());
//			if (nobilityReward != null) {
//				assistants += nobilityReward.getAssistantCount();
//				victoryPoints += nobilityReward.getVictoryPointCount();
//				coins += nobilityReward.getCoinsCount();
//				politicCards += nobilityReward.getPoliticCardCount();
//				mainActions += nobilityReward.getMainActionCount();
//				turn.setNobilityReward(nobilityReward);
//				updateState.setNobilityReward(nobilityReward);
//			}
//		}
//		// update server player instance
//		player.addAssistants(assistants);
//		updateState.addAssistants(assistants);
//		player.addVictoryPoints(victoryPoints);
//		updateState.addVictoryPoints(victoryPoints);
//		player.moveNobilityTrack(nobilityStep);
//		updateState.addNobilitySteps(nobilityStep);
//		player.addCoins(coins);
//		updateState.addCoins(coins);
//		for (int i = 0; i < politicCards; i++) {
//			PoliticCard politicCard = mPoliticCards.takeLast();
//			player.addPoliticCard(politicCard);
//			updateState.addPoliticCard(politicCard);
//		}
//		updateState.addMainAction(mainActions);
//		for (int i = 0; i < mainActions; i++) {
//			turn.addAdditionalMainAction();
//		}
//	}
//
//	/**
//	 * Calculate how many coins the player has to pay to move the king along a
//	 * route.
//	 * 
//	 * @param cities
//	 *            list that represent the route that the king has to follow.
//	 * @return the number of coins that the player has to pay to move the king
//	 *         along the provided route.
//	 * @throws RouteNotValid
//	 *             if the provided route is not valid (a city not exists or two
//	 *             cities are not directly linked).
//	 */
//	private int getCoinsToPayToMoveKing(List<String> cities) throws RouteNotValid {
//		try {
//			City city = getKingCity();
//			// check if start city is king city
//			int index = 0;
//			if (!cities.isEmpty() && cities.get(index).equals(city.getName())) {
//				while (index < cities.size()) {
//					String city1 = cities.get(index);
//					index += 1;
//					String city2 = cities.size() > index ? cities.get(index) : null;
//					validateCities(city1, city2);
//				}
//				return (cities.size() - 1) * COINS_TO_PAY_PER_LINK;
//			}
//			throw new RouteNotValid();
//		} catch (CityNotFound e) {
//			throw new RouteNotValid(e);
//		}
//	}
//
//	/**
//	 * Validate two cities: it will check if the who cities are directly linked.
//	 * 
//	 * @param city1
//	 *            to check.
//	 * @param city2
//	 *            to check.
//	 * @throws RouteNotValid
//	 *             if the second city is not directly linked to the first one.
//	 */
//	private void validateCities(String city1, String city2) throws RouteNotValid {
//		if (city2 != null && !areCitiesLinked(city1, city2)) {
//			throw new RouteNotValid(String.format("%s is not linked to %s", city1, city2));
//		}
//	}
//
//	/**
//	 * Engage an assistant paying ENGAGE_ASSISTANT_COINS_TO_PAY coins.
//	 * 
//	 * @param player
//	 *            that is going to build the emporium.
//	 * @return an update state to send to all clients.
//	 * @throws NotEnoughCoins
//	 *             if player has no enough coins to engage the assistant.
//	 */
//	/* package-local */ UpdateState engageAssistant(Player player) throws LogicException {
//		player.payCoins(ENGAGE_ASSISTANT_COINS_TO_PAY);
//		player.addAssistants(1);
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.addAssistants(1);
//		updateState.removeCoins(ENGAGE_ASSISTANT_COINS_TO_PAY);
//		return updateState;
//	}
//
//	/**
//	 * Change visible business permit tiles.
//	 * 
//	 * @param player
//	 *            that is changing the business permit tiles.
//	 * @param region
//	 *            where the business permit tiles should be changed.
//	 * @return an update state to send to all clients.
//	 * @throws NotEnoughAssistants
//	 *             if player as no enough assistant to make the action.
//	 */
//	/* package-local */ UpdateState changeBusinessPermitTiles(Player player, String region) throws NotEnoughAssistants {
//		player.payAssistants(ASSISTANTS_TO_RELEASE_TO_CHANGE_BPT);
//		RegionBoard regionBoard = (RegionBoard) getAbstractBoard(region);
//		regionBoard.changeVisibleBusinessTiles();
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.removeAssistants(ASSISTANTS_TO_RELEASE_TO_CHANGE_BPT);
//		updateState.changeBusinessPermitTileAtIndex(regionBoard.getFirstVisibleTile(), region,
//				RegionBoard.INDEX_FIRST_BPT);
//		updateState.changeBusinessPermitTileAtIndex(regionBoard.getSecondVisibleTile(), region,
//				RegionBoard.INDEX_SECOND_BPT);
//		return updateState;
//	}
//
//	/**
//	 * Send an assistant to elect a councillor.
//	 * 
//	 * @param player
//	 *            that is sending the assistant.
//	 * @param councillor
//	 *            that should be elected.
//	 * @param region
//	 *            where the councillor should be added.
//	 * @return an update state to send to all clients.
//	 * @throws NotEnoughAssistants
//	 *             if player as no enough assistant to make the action.
//	 */
//	/* package-local */ UpdateState sendAssistantToElectCouncillor(Player player, Councilor councillor, String region)
//			throws NotEnoughAssistants {
//		player.payAssistants(ASSISTANTS_TO_SEND_TO_ELECT_COUNCILLOR);
//		UpdateState updateState = electCouncillor(player, councillor, region, false);
//		updateState.removeAssistants(ASSISTANTS_TO_SEND_TO_ELECT_COUNCILLOR);
//		return updateState;
//	}
//
//	/**
//	 * Perform an additional main action.
//	 * 
//	 * @param player
//	 *            that is trying to perform an additional main action.
//	 * @return an update state to send to all clients.
//	 * @throws NotEnoughAssistants
//	 *             if player as no enough assistant to make the action.
//	 */
//	/* package-local */ UpdateState performAdditionalMainAction(Player player) throws NotEnoughAssistants {
//		player.payAssistants(ASSISTANTS_TO_PAY_TO_MAKE_ADDITIONAL_MAIN_ACTION);
//		UpdateState updateState = new UpdateState(player.getNickname());
//		updateState.removeAssistants(ASSISTANTS_TO_PAY_TO_MAKE_ADDITIONAL_MAIN_ACTION);
//		updateState.addMainAction(1);
//		return updateState;
//	}
//
//	/**
//	 * Apply all bonus of all players and create a list of update states to send
//	 * to clients.
//	 * 
//	 * @return a list of update states of every player.
//	 */
//	/* package-local */ UpdateState[] applyEndGameBonus() {
//		Map<String, UpdateState> updateStates = new HashMap<>();
//		for (Player player : mPlayers.values()) {
//			UpdateState updateState = new UpdateState(player.getNickname());
//			applyBonusAndKingRewards(player, updateState);
//			updateStates.put(player.getNickname(), updateState);
//		}
//		applyNobilityTrackPositionRewards(updateStates);
//		applyMostPermitTileBonus(updateStates);
//		return updateStates.values().toArray(new UpdateState[updateStates.size()]);
//	}
//
//	/**
//	 * On the end of the game, all bonus cards and all king rewards owned by the
//	 * player can be obtained.
//	 * 
//	 * @param player
//	 *            that can obtains his bonus and his king rewards.
//	 * @param updateState
//	 *            to update with all victory points owned by the player.
//	 */
//	private void applyBonusAndKingRewards(Player player, UpdateState updateState) {
//		int victoryPoints = 0;
//		for (Reward kingReward : player.getKingRewards()) {
//			victoryPoints += kingReward.getVictoryPointCount();
//		}
//		for (Reward bonusCard : player.getBonusCards().values()) {
//			victoryPoints += bonusCard.getVictoryPointCount();
//		}
//		player.addVictoryPoints(victoryPoints);
//		updateState.addVictoryPoints(victoryPoints);
//	}
//
//	/**
//	 * On the end of the game: "the player furthest ahead on the nobility track
//	 * earns 5 victory points, and the player in the second place earn 2 victory
//	 * points. If more than one player is tied for first place on the nobility
//	 * track, they all earn 5 victory points each and there are no points for
//	 * second place. If multiple players are tied for second place, they all
//	 * earn 2 victory points".
//	 * 
//	 * @param updateStates
//	 *            map that contains the update state of every player and should
//	 *            be updated.
//	 */
//	private void applyNobilityTrackPositionRewards(Map<String, UpdateState> updateStates) {
//		List<Player> players = new ArrayList<>(mPlayers.values());
//		Collections.sort(players, (player1,
//				player2) -> Integer.compare(player1.getNobilityTrackPosition(), player2.getNobilityTrackPosition())
//						* -1);
//		int lastPlayerPosition = 0;
//		boolean firstBonus = true;
//		for (int i = 0; i < players.size(); i++) {
//			Player player = players.get(i);
//			if (i == 0) {
//				// the first player gets 5 victory points
//				player.addVictoryPoints(5);
//				updateStates.get(player.getNickname()).addVictoryPoints(5);
//			} else if (player.getNobilityTrackPosition() == lastPlayerPosition) {
//				// the other players check if their position is the same of the
//				// last player
//				int victoryPoints;
//				if (firstBonus) {
//					victoryPoints = 5;
//				} else {
//					victoryPoints = 2;
//				}
//				player.addVictoryPoints(victoryPoints);
//				updateStates.get(player.getNickname()).addVictoryPoints(victoryPoints);
//			} else {
//				if (firstBonus && i == 1) {
//					firstBonus = false;
//					player.addVictoryPoints(2);
//					updateStates.get(player.getNickname()).addVictoryPoints(2);
//				} else {
//					return;
//				}
//			}
//			lastPlayerPosition = player.getNobilityTrackPosition();
//		}
//	}
//
//	/**
//	 * On the end of game: "the player with the most permit tiles receives 3
//	 * victory points".
//	 * 
//	 * @param updateStates
//	 *            map that contains the update state of every player and should
//	 *            be updated.
//	 */
//	private void applyMostPermitTileBonus(Map<String, UpdateState> updateStates) {
//		List<Player> players = new ArrayList<>(mPlayers.values());
//		Collections.sort(players, (player1, player2) -> Integer.compare(player1.getBusinessPermitTiles().size(),
//				player2.getBusinessPermitTiles().size()) * -1);
//		int maxPermitTile = 0;
//		for (int i = 0; i < players.size(); i++) {
//			Player player = players.get(i);
//			int permitTileCount = player.getBusinessPermitTiles().size();
//			if (i == 0 || permitTileCount == maxPermitTile) {
//				player.addVictoryPoints(3);
//				updateStates.get(player.getNickname()).addVictoryPoints(3);
//			} else {
//				return;
//			}
//			maxPermitTile = permitTileCount;
//		}
//	}
//
//	/**
//	 * Generate a player ranking: "The player with the most victory points is
//	 * the winner. In the event of a draw, the player who holds the most
//	 * assistants and politics cards is the winner.
//	 * 
//	 * @return a sorted list of players nicknames sorted by victory points.
//	 */
//	/* package-local */ List<String> generateRanking() {
//		List<Player> players = new ArrayList<>(mPlayers.values());
//		Collections.sort(players, (player1, player2) -> {
//			int comparator = Integer.compare(player1.getVictoryPoints(), player2.getVictoryPoints()) * -1;
//			if (comparator == 0) {
//				comparator = Integer.compare(player1.getAssistants() + player1.getPoliticCards().size(),
//						player2.getAssistants() + player2.getPoliticCards().size()) * -1;
//			}
//			return comparator;
//		});
//		return players.stream().map(Player::getNickname).collect(Collectors.toList());
//	}
//
//	/**
//	 * Satisfy balcony with provided politic cards.
//	 * 
//	 * @param politicCards
//	 *            given politic cards to satisfy this balcony.
//	 * @return a list with politic cards used to satisfy this balcony.
//	 * @throws BalconyUnsatisfiable
//	 *             if none of the given politic cards can satisfy this balcony.
//	 */
//	private List<PoliticCard> satisfyBalcony(String region, List<PoliticCard> politicCards)
//			throws BalconyUnsatisfiable {
//		return getAbstractBoard(region).satisfyBalcony(politicCards);
//	}
//
//	/**
//	 * Take the business permit tile of the provided region at the provided
//	 * index. Another business permit tile is auto extracted from the region's
//	 * deck and placed where the permit tile has be taken.
//	 * 
//	 * @param region
//	 *            where the business permit tile is placed.
//	 * @param index
//	 *            of the permit tile to take (Must be 1 or 2).
//	 * @return the desired business permit tile.
//	 * @throws IllegalArgumentException
//	 *             if index is not 1 nor 2.
//	 */
//	private BusinessPermitTile takeBusinessPermitTile(String region, int index) {
//		RegionBoard regionBoard = (RegionBoard) getAbstractBoard(region);
//		switch (index) {
//		case 1:
//			return regionBoard.takeFirstVisibleTile();
//		case 2:
//			return regionBoard.takeSecondVisibleTile();
//		default:
//			throw new IllegalArgumentException("Index must be 1 or 2");
//		}
//	}
//
//	/**
//	 * Get the business permit tile of the provided region at the provided
//	 * index.
//	 * 
//	 * @param region
//	 *            where the business permit tile is placed.
//	 * @param index
//	 *            of the permit tile to get (Must be 1 or 2).
//	 * @return the desired business permit tile.
//	 * @throws IllegalArgumentException
//	 *             if index is not 1 nor 2.
//	 */
//	private BusinessPermitTile getBusinessPermitTile(String region, int index) {
//		RegionBoard regionBoard = (RegionBoard) getAbstractBoard(region);
//		switch (index) {
//		case 1:
//			return regionBoard.getFirstVisibleTile();
//		case 2:
//			return regionBoard.getSecondVisibleTile();
//		default:
//			throw new IllegalArgumentException("Index must be 1 or 2");
//		}
//	}
//
//	/**
//	 * Calculate how many coins the player has to pay to satisfy the balcony
//	 * with the provided cards.
//	 * 
//	 * @param politicCards
//	 *            used to satisfy a balcony.
//	 * @return the number of coins he has to pay to satisfy the balcony
//	 *         according to game rules.
//	 */
//	private int getCoinsToPayToSatisfyBalcony(List<PoliticCard> politicCards) {
//		int coins;
//		switch (politicCards.size()) {
//		case 1:
//			coins = 10;
//			break;
//		case 2:
//			coins = 7;
//			break;
//		case 3:
//			coins = 4;
//			break;
//		default:
//			coins = 0;
//			break;
//		}
//		for (PoliticCard politicCard : politicCards) {
//			if (politicCard.isJolly()) {
//				coins += 1;
//			}
//		}
//		return coins;
//	}
//
//	/**
//	 * Special reward 1: "Obtain the bonus of a reward token from a city where
//	 * you have an emporium. You cannot choose one of the tokens which advance
//	 * you along the nobility track".
//	 * 
//	 * @param player
//	 *            that want to earn a special reward.
//	 * @param turn
//	 *            current player turn.
//	 * @param cities
//	 *            where the reward token are placed.
//	 * @return the update state bundle that contains all items changed.
//	 * @throws CityNotFound
//	 *             if one of the provided city is not found on the game state.
//	 * @throws CityNotValid
//	 *             if a city is provided more than one time, if the player has
//	 *             no emporium in one city or if the reward of that city let
//	 *             player to move along the nobility track.
//	 */
//	/* package-local */ UpdateState earnFirstSpecialRewards(Player player, GameTurn turn, List<String> cities)
//			throws LogicException {
//		// validate cities:
//		List<Reward> rewards = new ArrayList<>();
//		Set<String> citySet = new HashSet<>();
//		for (String cityName : cities) {
//			City city = getCity(cityName);
//			Reward reward = city.getReward();
//			if (reward == null || reward.getNobilityStepCount() > 0 || !city.hasPlayerAnEmporium(player)) {
//				throw new CityNotValid();
//			}
//			rewards.add(reward);
//			citySet.add(cityName);
//		}
//		if (citySet.size() != cities.size()) {
//			throw new CityNotValid();
//		}
//		UpdateState updateState = new UpdateState(player.getNickname());
//		applyRewards(player, turn, rewards, updateState);
//		return updateState;
//	}
//
//	/**
//	 * Special reward 2: "You can take a face up building permit tile without
//	 * paying the cost".
//	 * 
//	 * @param player
//	 *            that want to earn a special reward.
//	 * @param turn
//	 *            current player turn.
//	 * @param regions
//	 *            where the player want to take a business permit tile.
//	 * @param indices
//	 *            related to the same index region that identify the permit tile
//	 *            that the player wants.
//	 * @return the update state bundle that contains all items changed.
//	 */
//	/* package-local */ UpdateState earnSecondSpecialRewards(Player player, GameTurn turn, List<String> regions,
//			List<Integer> indices) {
//		List<BusinessPermitTile> businessPermitTiles = new ArrayList<>();
//		for (int i = 0; i < regions.size(); i++) {
//			businessPermitTiles.add(takeBusinessPermitTile(regions.get(i), indices.get(i)));
//		}
//		player.addBusinessPermitTiles(businessPermitTiles);
//		UpdateState updateState = new UpdateState(player.getNickname());
//		for (BusinessPermitTile businessPermitTile : businessPermitTiles) {
//			player.addBusinessPermitTile(businessPermitTile);
//			updateState.addBusinessPermitTile(businessPermitTile);
//			applyReward(player, turn, updateState, businessPermitTile);
//		}
//		for (int i = 0; i < regions.size(); i++) {
//			BusinessPermitTile businessPermitTile = getBusinessPermitTile(regions.get(i), indices.get(i));
//			updateState.changeBusinessPermitTileAtIndex(businessPermitTile, regions.get(i), indices.get(i));
//		}
//		return updateState;
//	}
//
//	/**
//	 * Special reward 3: "You receive the bonus of one of the business permit
//	 * tiles which you previously bought (also a face down tile)".
//	 * 
//	 * @param player
//	 *            that want to earn a special reward.
//	 * @param turn
//	 *            current player turn.
//	 * @param businessPermitTiles
//	 *            list of player business permit tiles that he want to re use to
//	 *            get the related reward.
//	 * @return the update state bundle that contains all items changed.
//	 */
//	/* package-local */ UpdateState earnThirdSpecialRewards(Player player, GameTurn turn,
//			List<BusinessPermitTile> businessPermitTiles) {
//		UpdateState updateState = new UpdateState(player.getNickname());
//		businessPermitTiles.stream()
//				.filter(businessPermitTile -> player.getBusinessPermitTiles().contains(businessPermitTile)
//						|| player.getUsedBusinessPermitTiles().contains(businessPermitTile))
//				.forEach(businessPermitTile -> applyReward(player, turn, updateState, businessPermitTile));
//		return updateState;
//	}
//
//	/**
//	 * Internal builder class. This is the only way to instantiate a ServerGame
//	 * instance.
//	 */
//	/* package-local */ static class Builder {
//
//		/**
//		 * Color palette generated at runtime based on players count.
//		 */
//		private Color[] mColorPalette;
//
//		/**
//		 * List of players.
//		 */
//		private List<RemotePlayer> mPlayers;
//
//		/**
//		 * Left region board.
//		 */
//		private RegionBoard mLeftRegionBoard;
//
//		/**
//		 * Center region board.
//		 */
//		private RegionBoard mCenterRegionBoard;
//
//		/**
//		 * Right region board.
//		 */
//		private RegionBoard mRightRegionBoard;
//
//		/**
//		 * List of all politic cards.
//		 */
//		private List<PoliticCard> mPoliticCards;
//
//		/**
//		 * List of councilors.
//		 */
//		private ArrayList<Councilor> mCouncilors;
//
//		/**
//		 * List of business permit tiles.
//		 */
//		private ArrayList<BusinessPermitTile> mBusinessPermitTiles;
//
//		/**
//		 * Map of the nobility track.
//		 */
//		private HashMap<Integer, NobilityReward> mNobilityTrack;
//
//		/**
//		 * List of bonus cards.
//		 */
//		private HashMap<String, Reward> mBonusCards;
//
//		/**
//		 * List of reward king cards.
//		 */
//		private ArrayList<Reward> mRewardKingCards;
//
//		/**
//		 * Create a Builder instance.
//		 * 
//		 * @param players
//		 *            list of all players of the match.
//		 */
//		/* package-local */ Builder(List<RemotePlayer> players) {
//			mPlayers = players;
//		}
//
//		/**
//		 * Set left region board.
//		 * 
//		 * @param leftRegionBoard
//		 *            region board to set at left position.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setLeftRegion(RegionBoard leftRegionBoard) {
//			mLeftRegionBoard = leftRegionBoard;
//			return this;
//		}
//
//		/**
//		 * Set left region board.
//		 * 
//		 * @param centerRegionBoard
//		 *            region board to set at center position.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setCenterRegion(RegionBoard centerRegionBoard) {
//			mCenterRegionBoard = centerRegionBoard;
//			return this;
//		}
//
//		/**
//		 * Set left region board.
//		 * 
//		 * @param rightRegionBoard
//		 *            region board to set at right position.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setRightRegion(RegionBoard rightRegionBoard) {
//			mRightRegionBoard = rightRegionBoard;
//			return this;
//		}
//
//		/**
//		 * Set politic cards list.
//		 * 
//		 * @param politicCards
//		 *            list of politic cards to set.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setPoliticCards(List<PoliticCard> politicCards) {
//			mPoliticCards = new ArrayList<>(politicCards);
//			return this;
//		}
//
//		/**
//		 * Set councilors list.
//		 * 
//		 * @param councilors
//		 *            list of councilors to add.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setCouncilors(List<Councilor> councilors) {
//			mCouncilors = new ArrayList<>(councilors);
//			Collections.shuffle(mCouncilors);
//			return this;
//		}
//
//		/**
//		 * Set business permit tiles to add.
//		 * 
//		 * @param businessPermitTiles
//		 *            list of business permit tiles to add.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setBusinessPermitTiles(List<BusinessPermitTile> businessPermitTiles) {
//			mBusinessPermitTiles = new ArrayList<>(businessPermitTiles);
//			return this;
//		}
//
//		/**
//		 * Set nobility track map.
//		 * 
//		 * @param nobilityTrack
//		 *            map to set as nobility track.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setNobilityTrack(Map<Integer, NobilityReward> nobilityTrack) {
//			mNobilityTrack = new HashMap<>(nobilityTrack);
//			return this;
//		}
//
//		/**
//		 * Set bonus cards list.
//		 * 
//		 * @param bonusCards
//		 *            list of bonus cards to set.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setBonusCards(Map<String, Reward> bonusCards) {
//			mBonusCards = new HashMap<>(bonusCards);
//			return this;
//		}
//
//		/**
//		 * Set reward king cards.
//		 * 
//		 * @param rewardKingCards
//		 *            list of cards to set.
//		 * @return the builder instance.
//		 */
//		/* package-local */ Builder setRewardKingCards(List<Reward> rewardKingCards) {
//			mRewardKingCards = new ArrayList<>(rewardKingCards);
//			return this;
//		}
//
//		/**
//		 * Build a server game instance with provided data.
//		 * 
//		 * @return the built instance.
//		 */
//		/* package-local */ ServerGame build() {
//			int regionTiles = (int) Math.ceil((double) mBusinessPermitTiles.size() / 3);
//			ServerGame serverGame = new ServerGame();
//			serverGame.mLeftRegionBoard = mLeftRegionBoard;
//			serverGame.mLeftRegionBoard.fillBalcony(mCouncilors);
//			serverGame.mLeftRegionBoard.addBusinessPermitTiles(mBusinessPermitTiles.subList(0, regionTiles));
//			serverGame.mCenterRegionBoard = mCenterRegionBoard;
//			serverGame.mCenterRegionBoard.fillBalcony(mCouncilors);
//			serverGame.mCenterRegionBoard
//					.addBusinessPermitTiles(mBusinessPermitTiles.subList(regionTiles, 2 * regionTiles));
//			serverGame.mRightRegionBoard = mRightRegionBoard;
//			serverGame.mRightRegionBoard.fillBalcony(mCouncilors);
//			serverGame.mRightRegionBoard
//					.addBusinessPermitTiles(mBusinessPermitTiles.subList(2 * regionTiles, mBusinessPermitTiles.size()));
//			serverGame.mKingBoard = new KingBoard(mRewardKingCards, mNobilityTrack);
//			serverGame.mKingBoard.fillBalcony(mCouncilors);
//			serverGame.mCouncilors = mCouncilors;
//			serverGame.mPoliticCards.add(mPoliticCards, true);
//			serverGame.mBonusCards = mBonusCards;
//			mColorPalette = generateColorPalette(mPlayers.size());
//			for (int i = 0; i < mPlayers.size(); i++) {
//				Player player = mPlayers.get(i);
//				setupPlayer(i, player, serverGame);
//				serverGame.mPlayers.put(player.getNickname(), player);
//			}
//			serverGame.generateLinkedCityMap();
//			return serverGame;
//		}
//
//		/**
//		 * Setup player state in base at his turn's position.
//		 * 
//		 * @param position
//		 *            in the cycle of the turns.
//		 * @param player
//		 *            reference to the remote player.
//		 * @param serverGame
//		 *            game instance where player should be added.
//		 */
//		private void setupPlayer(int position, Player player, ServerGame serverGame) {
//			player.setColor(mColorPalette[position]);
//			player.addEmporiums(INITIAL_EMPORIUMS);
//			player.addCoins(10 + position);
//			player.addAssistants(position + 1);
//			for (int i = 0; i < INITIAL_POLITIC_CARDS; i++) {
//				player.addPoliticCard(serverGame.mPoliticCards.takeLast());
//			}
//		}
//
//		/**
//		 * Generate a valid color palette base on players count. It will ensure
//		 * different color for every player.
//		 * 
//		 * @param size
//		 *            number of players.
//		 * @return the generated color palette.
//		 */
//		private Color[] generateColorPalette(int size) {
//			Color[] colors = new Color[size];
//			for (int i = 0; i < size; i++) {
//				colors[i] = Color.getHSBColor((float) i / (float) size, 0.85f, 1.0f);
//			}
//			return colors;
//		}
//	}
}