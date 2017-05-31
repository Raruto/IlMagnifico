package main.network.client;

import java.rmi.RemoteException;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.client.rmi.RMIClient;
import main.network.client.socket.SocketClient;
import main.network.exceptions.LoginException;
import main.network.server.game.UpdateStats;
import main.ui.FakeUI;
import main.util.Costants;
import main.util.EAzioniGiocatore;
import main.util.EFasiDiGioco;
import main.util.network.ConnectionTypes;

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
	 * Porta in cui è aperta la comunicazione Socket.
	 */
	private static final int SERVER_SOCKET_PORT = Costants.SOCKET_PORT;

	/**
	 * Porta in cui è aperta la comunicazione RMI.
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
	 * Map of all defined server responses headers.
	 */
	private final HashMap<Object, ResponseHandler> responseMap;

	/**
	 * Crea una nuova istanza della classe.
	 * 
	 * @throws ClientException
	 *             se si verifica un errore.
	 */
	public Client() throws ClientException {
		nickname = "anonymous";
		isLogged = false;

		responseMap = new HashMap<>();
		loadResponses();
	}

	/**
	 * Load all possible responses and associate an handler.
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

		// responseMap.put(EAzioniGiocatore.Mercato, this::);
		// responseMap.put(EAzioniGiocatore.PalazzoConsiglio, this::);
		// responseMap.put(EAzioniGiocatore.Produzione, this::);
		// responseMap.put(EAzioniGiocatore.Raccolto, this::);
		// responseMap.put(EAzioniGiocatore.Torre, this::);
	}

	/**
	 * Metodo statico per eseguire il client.
	 * 
	 * @param args
	 *            parametri per la connessione (TODO: FINIRE DI IMPLEMENTARE).
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
			// e.printStackTrace();
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
	 * Nome scelto dal giocatore durante la fase di login e approvato dal server
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
	 *            porta in cui è aperta la comunicazione Socket.
	 * @param rmiPort
	 *            porta in cui è aperta la comunicazione RMI.
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
	 *            porta in cui è aperta la comunicazione RMI.
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

	/**
	 * This method is triggered by {@link AbstractUi#showLoginMenu()}.
	 * 
	 * @param nickname
	 *            to use for login session.
	 */
	public void loginPlayer(String nickname) {
		boolean success = false;
		try {
			System.out.println("Try to login user with nickname: " + nickname);
			client.loginPlayer(nickname);
			success = true;
			// joinFirstAvailableRoom();
		} catch (LoginException e) {
			System.out.println("Nickname is already in use on server");
			// mUi.showLoginErrorMessage();
		} catch (NetworkException e) {
			e.printStackTrace();
			//System.out.println("Cannot send login request: " + e.getMessage());
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
	 *            del ricevitore del ricevitore se è un messaggio privato,
	 *            altrimenti null.
	 * @param messaggio
	 *            da inviare.
	 */
	// @Override
	public void sendChatMessage(String receiver, String message) {
		try {
			client.sendChatMessage(receiver, message);
		} catch (NetworkException e) {
			System.err.println("Cannot send chat message request");
		}
	}

	public void performGameAction(UpdateStats requestedAction) {
		try {
			client.performGameAction(requestedAction);
		} catch (NetworkException e) {
			System.err.println("Cannot perform action request");
		}
	}

	@Override
	public void onGameUpdate(UpdateStats update) {
		if (update.getAzioneGiocatore() != null) {
			// if (update.getNomeGiocatore() != null)
			System.out.println("[" + update.getNomeGiocatore().toUpperCase() + "]" + " ACTION: "
					+ update.getAzioneGiocatore().toString());
		} else if (update.getAzioneServer() != null) {
			System.out.println("[GAME]" + " ACTION: " + update.getAzioneServer().toString());
		}
		handleResponse(update);

	}

	@Override
	public void onChurchSupport(UpdateStats update) {
		// TODO Auto-generated method stub

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
		System.out.println("E' il turno di: " + update.getNomeGiocatore());
	}

	@Override
	public void onTurnStarted(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPeriodStarted(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameStarted(UpdateStats update) {
		System.out.print("(" + update.getNomiGiocatori().size() + "G): ");
		for (String s : update.getNomiGiocatori()) {
			System.out.print(s + ", ");
		}
		System.out.println();

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati sul Client Controller (vedi RMIClient, SocketClient)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Notifica che è arrivato un nuovo messaggio dalla chat.
	 * 
	 * @param privateMessage
	 *            "True" se il messaggio è privato, "False" se pubblico.
	 * @param author
	 *            autore del messaggio.
	 * @param message
	 *            corpo del messaggio.
	 */
	@Override
	public void onChatMessage(boolean privateMessage, String author, String message) {
		// if (privateMessage) {
		System.out.println("[" + author + "]" + " " + message);
		// }
		// mUi.showChatMessage(privateMessage, author, message);
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void onNotify(Object object) throws RemoteException {
		System.out.println(object.toString());
	}

	/**
	 * Handle the server response and execute the defined method.
	 * 
	 * @param object
	 *            response header from server.
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
	 * This interface is used like {@link Runnable} interface.
	 */
	@FunctionalInterface
	private interface ResponseHandler {

		/**
		 * Handle the server response.
		 */
		void handle(UpdateStats update);
	}

	/////////// OLD //////////////
	@Override
	public void onTurnStarted(String nickname, int remainingTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTurnUpdateCountdown(int remainingTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActionNotValid(int errorCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerDisconnected(String nickname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLastTurnStarted(String nickname) {
		// TODO Auto-generated method stub

	}
}
