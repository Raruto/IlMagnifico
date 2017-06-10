package main.network.client;

import java.rmi.RemoteException;
import java.util.HashMap;

import main.model.Famigliare;
import main.model.Plancia;
import main.model.Punti;
import main.model.Risorsa;
import main.model.SpazioAzione;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.ECarte;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.EFasiDiGioco;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.errors.Errors;
import main.network.NetworkException;
import main.network.client.rmi.RMIClient;
import main.network.client.socket.SocketClient;
import main.network.exceptions.LoginException;
import main.network.protocol.ConnectionTypes;
import main.network.server.game.Game;
import main.network.server.game.UpdateStats;
import main.ui.FakeUI;
import main.util.ANSI;
import main.util.Costants;

/**
 * 
 * Client del gioco "Lorenzo Il Magnifico" della "CranioCreations".
 *
 */
public class Client implements IClient {
	/**
	 * Indirizzo Server sui cui le comunicazioni sono aperte.
	 */
	private static final String SERVER_ADDRESS = Costants.SERVER_ADDRESS;

	/**
	 * Porta in cui e' aperta la comunicazione Socket.
	 */
	private static final int SERVER_SOCKET_PORT = Costants.SOCKET_PORT;

	/**
	 * Porta in cui e' aperta la comunicazione RMI.
	 */
	private static final int SERVER_RMI_PORT = Costants.RMI_PORT;

	/**
	 * Flag per determinare stato della connessione.
	 */
	private boolean isLogged;

	/**
	 * Classe astratta che rappresenta il client selezionato (RMI o Socket).
	 */
	private AbstractClient client;

	/**
	 * Nome del giocatore corrente.
	 */
	private String nickname;

	/*
	 * Mappa di tutte le intestazioni dei metodi per la gestione delle risposte
	 * del server.
	 */
	private final HashMap<Object, ResponseHandler> responseMap;

	/**
	 * {@link SpazioAzione} aggiornato all'ultimo aggiornamento ricevuto dal
	 * Server (vedi {@link UpdateStats}).
	 */
	private SpazioAzione board;

	/**
	 * Plance dei giocatori ("Nome","Plancia") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Plancia}).
	 */
	private HashMap<String, Plancia> playersDashboards;

	/**
	 * Famigliari dei giocatori ("Nome","Famigliare[]") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Famigliare}).
	 */
	private HashMap<String, Famigliare[]> playersFamilies;

	/**
	 * Risorse dei giocatori ("Nome","Risorsa") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Risorsa}).
	 */
	private HashMap<String, Risorsa> playersResources;

	/**
	 * Risorse dei giocatori ("Nome","Punti") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Punti}).
	 */
	private HashMap<String, Punti> playersPoints;

	/**
	 * Nome del giocatore attualmente di turno, aggiornato all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link UpdateStats}).
	 */
	private String playerTurn;

	/**
	 * Flag usato per abilitare il Log sul Server.
	 */
	private final boolean LOG_ENABLED = Costants.CLIENT_ENABLE_LOG;

	/**
	 * Crea una nuova istanza della classe.
	 * 
	 * @throws ClientException
	 *             se si verifica un errore.
	 */
	public Client() throws ClientException {
		nickname = "anonymous";
		isLogged = false;

		playersDashboards = new HashMap<>();
		playersFamilies = new HashMap<>();
		playersResources = new HashMap<>();
		playersPoints = new HashMap<>();

		responseMap = new HashMap<>();
		loadResponses();
	}

	/**
	 * Inizializza "responseMap" caricando tutti i possibili metodi di risposta
	 * (chiamati da {@link ResponseHandler}).
	 */
	private void loadResponses() {
		responseMap.put(EFasiDiGioco.InizioPartita, this::onGameStarted);
		responseMap.put(EFasiDiGioco.InizioPeriodo, this::onPeriodStarted);
		responseMap.put(EFasiDiGioco.InizioTurno, this::onTurnStarted);
		responseMap.put(EFasiDiGioco.FineTurno, this::onTurnEnd);
		responseMap.put(EFasiDiGioco.FinePeriodo, this::onPeriodEnd);
		responseMap.put(EFasiDiGioco.FinePartita, this::onGameEnd);

		responseMap.put(EFasiDiGioco.MossaGiocatore, this::onPlayerMove);
		responseMap.put(EFasiDiGioco.SostegnoChiesa, this::onChurchSupport);

		responseMap.put(EAzioniGiocatore.Mercato, this::onMarket);
		responseMap.put(EAzioniGiocatore.PalazzoConsiglio, this::onCouncilPalace);
		responseMap.put(EAzioniGiocatore.Produzione, this::onProductionRound);
		responseMap.put(EAzioniGiocatore.Raccolto, this::onHarvestRound);
		responseMap.put(EAzioniGiocatore.Torre, this::onTower);
		responseMap.put(EAzioniGiocatore.RaccoltoOvale, this::onHarvestOval);
		responseMap.put(EAzioniGiocatore.ProduzioneOvale, this::onProductionOval);
		responseMap.put(EAzioniGiocatore.Famigliare, this::onPayServant);

	}

	/**
	 * Metodo statico per eseguire il client.
	 * 
	 * @param args
	 *            parametri per la connessione.
	 */
	public static void main(String[] args) {
		String serverAddress = SERVER_ADDRESS;
		int socketPort = SERVER_SOCKET_PORT, rmiPort = SERVER_RMI_PORT;

		// Check if arguments were passed in
		if (args.length != 0) {
			try {
				serverAddress = args[0];
				socketPort = Integer.parseInt(args[1]);
				rmiPort = Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("Proper usage is: [\"serverAddress\" socketPort rmiPort]");
				System.exit(0);
			}
		}

		// Debugging purpose
		try {
			FakeUI.mainClient(serverAddress, socketPort, rmiPort);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * "True" se il giocatore ha portato a termine correttamente la fase di
	 * login presso il server.
	 * 
	 * @return boolean isLogged
	 */
	public boolean isLogged() {
		return this.isLogged;
	}

	/**
	 * Nome scelto dal giocatore durante la fase di login e approvato dal
	 * server.
	 * 
	 * @return String nickname
	 */
	public String getNickname() {
		return this.nickname;
	}

	/**
	 * Avvia connessioni client.
	 * 
	 * @param connectionType
	 *            nome del tipo di connessione scelta
	 * @param serverAddress
	 *            indirizzo Server sui cui le comunicazioni sono aperte
	 * @param socketPort
	 *            porta in cui e' aperta la comunicazione Socket.
	 * @param rmiPort
	 *            porta in cui e' aperta la comunicazione RMI.
	 * @throws ClientException
	 *             se si verifica un errore.
	 */
	public void startClient(String connectionType, String serverAddress, int socketPort, int rmiPort)
			throws ClientException {
		if (connectionType.equals(ConnectionTypes.RMI.toString())) {
			startRMIClient(serverAddress, rmiPort);
		} else if (connectionType.equals(ConnectionTypes.SOCKET.toString())) {
			startSocketClient(serverAddress, socketPort);
		} else {
			throw new ClientException(new Throwable("Uknown Connection Type"));
		}
	}

	/**
	 * Avvia la connessione RMI.
	 *
	 * @param serverAddress
	 *            indirizzo del Server su cui avviare la connessione.
	 * @param rmiPort
	 *            porta in cui e' aperta la comunicazione RMI.
	 * 
	 * @throws ClientException
	 *             se si verifica un errore.
	 */
	private void startRMIClient(String serverAddress, int rmiPort) throws ClientException {
		System.out.println("Starting RMI Connection...");
		client = new RMIClient(this, serverAddress, rmiPort);
		client.connect();

		System.out.println();
	}

	/**
	 * Avvia la connessione Socket.
	 * 
	 * @param serverAddress
	 *            indirizzo del Server su cui avviare la connessione.
	 * @param sockePort
	 *            porta dove avviare la connessione.
	 * @throws ClientException
	 *             se si verifica un errore.
	 */
	private void startSocketClient(String serverAddress, int socketPort) throws ClientException {
		System.out.println("Starting Socket Connection...");
		client = new SocketClient(this, serverAddress, socketPort);
		client.connect();

		System.out.println();
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// "Getters" (per verificare lo stato del Client, in Locale).
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Ritorna lo {@link SpazioAzione} aggiornato all'ultimo aggiornamento
	 * ricevuto dal Server (vedi {@link UpdateStats}).
	 */
	public SpazioAzione getGameBoard() {
		return this.board;
	}

	/**
	 * Ritorna le Plance dei giocatori ("Nome","Plancia") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Plancia}).
	 */
	public HashMap<String, Plancia> getPlayersDashboards() {
		return this.playersDashboards;
	}

	/**
	 * Ritorna i Famigliari dei giocatori ("Nome","Famigliare[]") aggiornate
	 * all'ultimo aggiornamento ricevuto dal Server (vedi {@link Famigliare}).
	 */
	public HashMap<String, Famigliare[]> getPlayersFamilies() {
		return this.playersFamilies;
	}

	/**
	 * Ritorna le Risorse dei giocatori ("Nome","Risorsa") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Risorsa}).
	 */
	public HashMap<String, Risorsa> getPlayersResources() {
		return this.playersResources;
	}

	/**
	 * Ritorna le Risorse dei giocatori ("Nome","Punti") aggiornate all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link Punti}).
	 */
	public HashMap<String, Punti> getPlayersPoints() {
		return this.playersPoints;
	}

	/**
	 * Ritorna il Nome del giocatore attualmente di turno, aggiornato all'ultimo
	 * aggiornamento ricevuto dal Server (vedi {@link UpdateStats}).
	 */
	public String getPlayerTurn() {
		return playerTurn;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// "Senders" (per l'invio di informazioni verso il Server, in Remoto).
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo per effettuare il login presso il Server.
	 * 
	 * @param nickname
	 *            nickname da usare per il login presso il Server.
	 */
	public void loginPlayer(String nickname) {
		boolean success = false;
		try {
			System.out.println("Try to login user with nickname: " + nickname);
			client.sendLoginRequest(nickname);
			success = true;
		} catch (LoginException e) {
			System.out.println("Nickname is already in use on server");
		} catch (NetworkException e) {
			System.err.println(e.getMessage());
		}

		if (success) {
			this.nickname = nickname;
			this.isLogged = true;
			System.out.println("Logged in as: " + nickname);
		}
	}

	/**
	 * Callback per inviare un messaggio sulla chat.
	 * 
	 * @param nickname
	 *            del ricevitore del ricevitore se e' un messaggio privato,
	 *            altrimenti null.
	 * @param messaggio
	 *            da inviare.
	 */
	public void sendChatMessage(String receiver, String message) {
		try {
			client.sendChatMessage(receiver, message);
		} catch (NetworkException e) {
			System.err.println("Cannot send chat message request");
		}
	}

	/**
	 * Callback per inviare una richiesta da parte del giocatore per svolgere
	 * una "azione giocatore" (vedi {@link EAzioniGiocatore}).
	 * 
	 * @param requestedAction
	 */
	public void performGameAction(UpdateStats requestedAction) {
		try {
			client.sendGameActionRequest(requestedAction);
		} catch (NetworkException e) {
			System.err.println("Cannot perform action request");
		}
	}

	/**
	 * Invia una richiesta di spostamento di una pedina sopra una zona Mercato
	 * che richiede la scelta di privilegi del consiglio (Area 4).
	 * 
	 * @param action
	 *            tipicamente {@link EAzioniGiocatore#Mercato}.
	 * @param color
	 *            colore della pedina da spostare (vedi {@link EColoriPedine}).
	 * @param position
	 *            posizione all'interno della zona selezionata (per l'Area 4 del
	 *            mercato position == 3).
	 * @param choosedPrivileges
	 *            array di {@link ESceltePrivilegioDelConsiglio} contenenti i
	 *            privilegi del consiglio scelti.
	 */
	public void movePawn(EAzioniGiocatore action, EColoriPedine color, Integer position,
			ESceltePrivilegioDelConsiglio[] choosedPrivileges) {
		UpdateStats requestedAction = new UpdateStats(action);
		requestedAction.spostaPedina(color, position);
		requestedAction.setSceltePrivilegiConsiglio(choosedPrivileges);
		performGameAction(requestedAction);
	}

	/**
	 * Invia una richiesta di spostamento di una pedina sopra una zona Torre che
	 * richiede la scelta di un costo opzionale (es.
	 * {@link ECarte#SOSTEGNO_AL_VESCOVO}).
	 * 
	 * @param action
	 *            tipicamente {@link EAzioniGiocatore#Torre}.
	 * @param color
	 *            colore della pedina da spostare (vedi {@link EColoriPedine}).
	 * @param position
	 *            posizione all'interno della zona selezionata (le torri vanno
	 *            da 0 a 15, [0..3] = Territorio, [4..7] = Personaggio, [8..11]
	 *            = Edificio, [12..15] = Impresa), per esempio: (position == 1)
	 *            è il Secondo Piano della Torre Territorio.
	 * @param choosedCosts
	 *            array di {@link ECostiCarte} contenenti i costi scelti (nel
	 *            qual caso ci siano, per esempio
	 *            {@link ECarte#SOSTEGNO_AL_VESCOVO} ha due costi possibili:
	 *            {@link ECostiCarte#SOSTEGNO_AL_VESCOVO1},
	 *            {@link ECostiCarte#SOSTEGNO_AL_VESCOVO2}).
	 */
	public void movePawn(EAzioniGiocatore action, EColoriPedine color, Integer position, ECostiCarte[] choosedCosts) {
		UpdateStats requestedAction = new UpdateStats(action);
		requestedAction.spostaPedina(color, position);
		requestedAction.setScelteCosti(choosedCosts);
		;
		performGameAction(requestedAction);
	}

	/**
	 * Invia una richiesta di spostamento di una pedina sopra una zona Torre che
	 * NON richiede la scelta di un costo opzionale (es. {@link ECarte#BOSCO}).
	 * 
	 * @param action
	 *            tipicamente {@link EAzioniGiocatore#Torre}.
	 * @param color
	 *            colore della pedina da spostare (vedi {@link EColoriPedine}).
	 * @param position
	 *            posizione all'interno della zona selezionata (le torri vanno
	 *            da 0 a 15, [0..3] = Territorio, [4..7] = Personaggio, [8..11]
	 *            = Edificio, [12..15] = Impresa), per esempio: (position == 1)
	 *            è il Secondo Piano della Torre Territorio.
	 */
	public void movePawn(EAzioniGiocatore action, EColoriPedine color, int position) {
		UpdateStats requestedAction = new UpdateStats(action);
		requestedAction.spostaPedina(color, position);
		performGameAction(requestedAction);
	}

	/**
	 * Invia una richiesta di supportare o meno il Vaticano ().
	 * 
	 * @param isSupported
	 *            se True il Vaticano sarà supportato dal giocatore, False
	 *            altrimenti.
	 */
	public void supportChurch(boolean isSupported) {
		UpdateStats requestedAction = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		requestedAction.supportaChiesa(isSupported);
	}

	/**
	 * Invia una richiesta di aumentare il valore di una Pedina pagando con i
	 * servitori posseduti.
	 * 
	 * @param color
	 *            colore della pedina selezionata (vedi {@link EColoriPedine}).
	 * @param servants
	 *            numero di servitori spesi per aumentare il valore della pedina
	 *            (1 Servitore = +1).
	 */
	public void incrementPawnValue(EColoriPedine color, int servants) {
		UpdateStats requestedAction = new UpdateStats(EAzioniGiocatore.Famigliare);
		requestedAction.aumentaValorePedina(color, servants);
		performGameAction(requestedAction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati sul Client Controller (vedi RMIClient, SocketClient)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Notifica che e' arrivato un nuovo messaggio dalla chat.
	 * 
	 * @param privateMessage
	 *            "True" se il messaggio e' privato, "False" se pubblico.
	 * @param author
	 *            autore del messaggio.
	 * @param message
	 *            corpo del messaggio.
	 */
	@Override
	public void onChatMessage(boolean privateMessage, String author, String message) {
		// if (privateMessage) {
		if (author.equals(Costants.ROOM))
			System.out.println(Costants.ROOM_ID + " " + message);
		else
			System.out.println("[" + author + "]" + " " + message);
		// }
		// mUi.showChatMessage(privateMessage, author, message);
	}

	/**
	 * Metodo invocato dal Server ogni qualvolta si presenta un errore (es.
	 * azione illegale) a seguito di una richiesta del giocatore (vedi
	 * {@link Client#performGameAction(UpdateStats)})
	 * 
	 * @param errorCode
	 *            stringa contenenti informazioni sull'errore (vedi
	 *            {@link Errors}).
	 */
	@Override
	public void onActionNotValid(String errorCode) {
		try {
			System.err.println("\n" + Errors.valueOf(errorCode).toString());
		} catch (RuntimeException e) {
			// In casi estremi!
			System.err.println("\nUnknown error: " + errorCode);
		}
	}

	/**
	 * Metodo invocato dal Server ogni qualvolta l'azione richiesta dal
	 * giocatore è stata accettata (vedi
	 * {@link Client#performGameAction(UpdateStats)}) oppure si è verificato
	 * un'avanzamento nello stato della logica della partita (vedi
	 * {@link Game}).
	 * 
	 * @param update
	 *            oggetto aggiornamento contenente tutte le informazioni
	 *            relative all'avanzamento della partita (vedi
	 *            {@link UpdateStats}).
	 */
	@Override
	public void onGameUpdate(UpdateStats update) {
		String playerName = update.getNomeGiocatore();

		// update local game copy
		this.board = update.getSpazioAzione();

		if (update.getPlanciaGiocatore() != null)
			this.playersDashboards.put(playerName, update.getPlanciaGiocatore());
		if (update.getFamigliaGiocatore() != null)
			this.playersFamilies.put(playerName, update.getFamigliaGiocatore());
		if (update.getRisorseGiocatore() != null)
			this.playersResources.put(playerName, update.getRisorseGiocatore());
		if (update.getPuntiGiocatore() != null)
			this.playersPoints.put(playerName, update.getPuntiGiocatore());

		// handle server response
		if (update.getAzioneGiocatore() != null) {
			log(playerName, "ACTION: " + update.getAzioneGiocatore().toString());
		} else if (update.getAzioneServer() != null) {
			log(Costants.GAME_ID, "ACTION: " + update.getAzioneServer().toString());
		}
		handleResponse(update);
	}

	@Override
	public void onChurchSupport(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	public void onMarket(UpdateStats update) {
		// TODO
	}

	public void onPayServant(UpdateStats update) {
		// TODO
	}

	public void onTower(UpdateStats update) {
		// TODO
	}

	public void onCouncilPalace(UpdateStats update) {
		// TODO
	}

	public void onHarvestRound(UpdateStats update) {
		// TODO
	}

	public void onProductionRound(UpdateStats update) {
		// TODO
	}

	public void onHarvestOval(UpdateStats update) {
		// TODO
	}

	public void onProductionOval(UpdateStats update) {
		// TODO
	}

	@Override
	public void onTurnEnd(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPeriodEnd(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameEnd(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerMove(UpdateStats update) {
		// if (update.getNomeGiocatore() != null)

		this.playerTurn = update.getNomeGiocatore();
		FakeUI.printPlayerTurn(true);
	}

	@Override
	public void onTurnStarted(UpdateStats update) {
		if (update.getFamiglieGiocatori() != null)
			this.playersFamilies = update.getFamiglieGiocatori();

		int[] dadi = update.getSpazioAzione().getValoreDadi();
		String s = "";
		for (int i = 0; i < dadi.length; i++) {
			if (i == 0)
				s = "Nero";
			else if (i == 1)
				s = "Arancione";
			else if (i == 2)
				s = "Bianco";
			else if (i == 3)
				s = "Neutrale";
			System.out.println(ANSI.YELLOW + "Dado " + s + ": " + dadi[i] + ANSI.RESET);
		}

	}

	@Override
	public void onPeriodStarted(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameStarted(UpdateStats update) {
		if (update.getRisorseGiocatori() != null)
			this.playersResources = update.getRisorseGiocatori();
		if (update.getPuntiGiocatori() != null)
			this.playersPoints = update.getPuntiGiocatori();
		if (update.getFamiglieGiocatori() != null)
			this.playersFamilies = update.getFamiglieGiocatori();
		if (update.getPlanceGiocatori() != null)
			this.playersDashboards = update.getPlanceGiocatori();

		System.out.print("(" + update.getNomiGiocatori().size() + "G): ");
		for (String s : update.getNomiGiocatori()) {
			System.out.print(s + ", ");
		}
		System.out.println();

	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void onNotify(Object object) throws RemoteException {
		System.out.println(object.toString());
	}

	/*
	 * Metodo interno usato per il Log sul Client (abilitato da: LOG_ENABLED)
	 * 
	 * @param message
	 */
	public void log(String author, String message) {
		if (LOG_ENABLED) {
			String id;
			if (author.contains("[") && author.contains("]"))
				id = author;
			else
				id = "[" + author.toUpperCase() + "]";
			System.out.println(id + " " + message);
		}
	}

	/**
	 * Gestisce la risposta ricevuta dal Server ed invoca il metodo associatogli
	 * nella "responseMap".
	 * 
	 * @param update
	 *            risposta ricevuta dal server (es. {@link UpdateStats}).
	 */
	public void handleResponse(UpdateStats update) {
		ResponseHandler handler = null;
		EAzioniGiocatore azione = update.getAzioneGiocatore();
		EFasiDiGioco fase = update.getAzioneServer();

		if (azione != null)
			handler = responseMap.get(azione);
		else if (fase != null)
			handler = responseMap.get(fase);

		if (handler != null) {
			handler.handle(update);
		}
	}

	/**
	 * Interfaccia utilizzata "come" l'interfaccia {@link Runnable}.
	 */
	@FunctionalInterface
	private interface ResponseHandler {

		/**
		 * Gestisce la risposta del Server.
		 * 
		 * @param update
		 *            (vedi {@link UpdateStats}).
		 */
		void handle(UpdateStats update);
	}
}