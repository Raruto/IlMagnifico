package com.client;

import com.NetworkException;

/**
 * 
 * Classe che rappresenta l'astrazione necessaria per le comunicazioni col
 * server. Estendendo questa classe è possibile utilizzare qualsiasi tipo di
 * connessione (es. RMI o Socket). L'interfaccia {@link IClient} funziona come
 * controller client e callback handler.
 */
public abstract class AbstractClient {

	/**
	 * Controller client.
	 */
	private final IClient controller;

	/**
	 * Indirizzo del server.
	 */
	private final String address;

	/**
	 * Porta usata dal server per la comunicazione.
	 */
	private final int port;

	/**
	 * Costruttore astratto.
	 * 
	 * @param controller
	 *            client controller.
	 * @param address
	 *            indirizzo del server.
	 * @param port
	 *            porta del server.
	 */
	public AbstractClient(IClient controller, String address, int port) {
		this.controller = controller;
		this.address = address;
		this.port = port;
	}

	/**
	 * Ritorna l'indirizzo del server.
	 * 
	 * @return l'indirizzo del server.
	 */
	protected String getAddress() {
		return address;
	}

	/**
	 * Ritorna la porta usata dal server per la comunicazione.
	 * 
	 * @return porta del server.
	 */
	protected int getPort() {
		return port;
	}

	/**
	 * Ritorna il client controller per potere (scrivere/inviare) richieste sul
	 * (canale di comunicazione/oggetto remoto).
	 * 
	 * @return client controller (es. {@link Client}).
	 */
	protected IClient getController() {
		return controller;
	}

	/**
	 * Apre una connessione con il server.
	 * 
	 * @throws ClientConnectionException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	public abstract void connect() throws ClientException;

	/**
	 * Initialize client connection. Override this only if required. This is
	 * called immediately after {@link #connect()}.
	 */
	public void initializeConnection() {

	}

	/**
	 * Provare ad effettuare un accesso al server con il nome fornito.
	 * 
	 * @param nickname
	 *            nome del giocatore da utilizzare per identificarsi sul server.
	 * @throws LoginException
	 *             se il nome fornito è già in uso sul server.
	 * @throws NetworkException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	public abstract void loginPlayer(String nickname) throws NetworkException;

	/**
	 * Try to join the first available room.
	 * 
	 * @throws JoinRoomException
	 *             if no available room where join player has been found.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public abstract void joinFirstAvailableRoom() throws NetworkException;

	/**
	 * Try to create a new room on server side.
	 * 
	 * @param maxPlayers
	 *            that should be accepted in this new room.
	 * @throws CreateRoomException
	 *             if another room has been created in the meanwhile.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 * @return configuration bundle that contains all default configurations.
	 */
	// public abstract Configuration createNewRoom(int maxPlayers) throws
	// NetworkException;

	/**
	 * Try to apply game configuration to the player room.
	 * 
	 * @param configuration
	 *            that should be applied to the room.
	 * @throws InvalidConfigurationException
	 *             if configuration is not valid.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void applyGameConfiguration(Configuration configuration)
	// throws NetworkException;

	/**
	 * Retrieve a list that contains all possible actions.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void getActionList() throws NetworkException;

	/**
	 * Draw a politic card.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void drawPoliticCard() throws NetworkException;

	/**
	 * [MAIN ACTION 1]: Elect a councillor.
	 * 
	 * @param councilor
	 *            to add to the balcony.
	 * @param region
	 *            where the balcony where insert the councillor is placed.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void electCouncillor(Councilor councilor, String region)
	// throws NetworkException;

	/**
	 * [MAIN ACTION 2]: Acquire a business permit tile.
	 * 
	 * @param politicCards
	 *            list of politic cards to satisfy the balcony.
	 * @param region
	 *            where the balcony to satisfy is placed.
	 * @param permitTileIndex
	 *            first or second permit tile index.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void acquireBusinessPermitTile(List<PoliticCard>
	// politicCards, String region, int permitTileIndex) throws
	// NetworkException;

	/**
	 * [MAIN ACTION 3]: Build an emporium with a business permit tile.
	 * 
	 * @param businessPermitTile
	 *            to use to build the emporium.
	 * @param city
	 *            where the emporium should be built.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void
	// buildEmporiumWithBusinessPermitTile(BusinessPermitTile
	// businessPermitTile, String city) throws NetworkException;

	/**
	 * [MAIN ACTION 4]: Build an emporium with the king helps.
	 * 
	 * @param politicCards
	 *            to use to satisfy the king's balcony.
	 * @param cities
	 *            which represent the track that the king should do for moving.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void buildEmporiumWithKingHelp(List<PoliticCard>
	// politicCards, List<String> cities) throws NetworkException;

	/**
	 * [FAST ACTION 1]: Engage an assistant.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void engageAssistant() throws NetworkException;

	/**
	 * [FAST ACTION 2]: Change business permit tiles of the given region.
	 * 
	 * @param region
	 *            where business permit tiles should be changed.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void changeBusinessPermitTiles(String region) throws
	// NetworkException;

	/**
	 * [FAST ACTION 3]: Send an assistant to elect a councillor in a given
	 * region's balcony.
	 * 
	 * @param councilor
	 *            to add to the balcony.
	 * @param region
	 *            where the balcony where insert the councillor is placed.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void sendAssistantElectCouncillor(Councilor councilor,
	// String region) throws NetworkException;

	/**
	 * [FAST ACTION 4]: Perform an additional main action.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void performAdditionalMainAction() throws
	// NetworkException;

	/**
	 * Sell a politic card on market.
	 * 
	 * @param politicCard
	 *            to sell on market.
	 * @param price
	 *            for that politic card.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void sellPoliticCard(PoliticCard politicCard, int price)
	// throws NetworkException;

	/**
	 * Sell a business permit tile on market.
	 * 
	 * @param businessPermitTile
	 *            to sell on market.
	 * @param price
	 *            for that business permit tile.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void sellBusinessPermitTile(BusinessPermitTile
	// businessPermitTile, int price) throws NetworkException;

	/**
	 * Sell an assistant on market.
	 * 
	 * @param price
	 *            for that assistant.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void sellAssistant(int price) throws NetworkException;

	/**
	 * Buy a market item with the given market id.
	 * 
	 * @param marketId
	 *            of the object to buy.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void buyItem(String marketId) throws NetworkException;

	/**
	 * Earn first special reward.
	 * 
	 * @param cities
	 *            where the player has built an emporium and want to retrieve
	 *            the reward.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void earnFirstSpecialRewards(List<String> cities) throws
	// NetworkException;

	/**
	 * Earn second special reward.
	 * 
	 * @param regions
	 *            list of region where the permit tiles are placed.
	 * @param indices
	 *            list of indices that represent the position of the visible
	 *            permit tiles.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void earnSecondSpecialRewards(List<String> regions,
	// List<Integer> indices) throws NetworkException;

	/**
	 * Earn third special reward.
	 * 
	 * @param businessPermitTiles
	 *            list of player's business permit tiles.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void earnThirdSpecialRewards(List<BusinessPermitTile>
	// businessPermitTiles) throws NetworkException;

	/**
	 * Send a request for ending the current turn.
	 * 
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	// public abstract void endTurn() throws NetworkException;

	/**
	 * Send a chat message to other players or a specified player.
	 * 
	 * @param receiver
	 *            nickname of the specific player if a private message, null if
	 *            should be delivered to all room players.
	 * @param message
	 *            to deliver.
	 * @throws NetworkException
	 *             if server is not reachable or something went wrong.
	 */
	public abstract void sendChatMessage(String receiver, String message) throws NetworkException;
}