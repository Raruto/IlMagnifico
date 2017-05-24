package com.client;

import com.NetworkException;
import com.client.rmi.RMIClient;
import com.client.socket.SocketClient;
import com.exceptions.LoginException;

/**
 * 
 * Client del gioco "Lorenzo Il Magnifico" della "CranioCreations".
 *
 */
public class Client implements IClient {

	/**
	 * Tipi di connessione disponibili al server.
	 */
	enum ConnectionTypes {
		RMI, SOCKET;
	}

	/**
	 * Indirizzo Server sui cui le comunicazioni sono aperte.
	 */
	private static final String SERVER_ADDRESS = "127.0.0.1";

	/**
	 * Porta in cui è aperta la comunicazione Socket.
	 */
	private static final int SERVER_SOCKET_PORT = 1098;

	/**
	 * Porta in cui è aperta la comunicazione RMI.
	 */
	private static final int SERVER_RMI_PORT = 1099;

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

	/**
	 * Crea una nuova istanza della classe.
	 * 
	 * @throws ClientException
	 *             se si verifica un errore.
	 */
	public Client() throws ClientException {
		nickname = "anonymous";
		isLogged = false;
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
		else {
			try {
				FakeUI.main(serverAddress, socketPort, rmiPort);
				FakeUI.login();
				FakeUI.sayHelloToPlayers();
				FakeUI.infiniteLoop();

			} catch (Exception e) {
				e.printStackTrace();
			}
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
			System.out.println("Cannot send login request");
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

	@Override
	public void onTurnStarted(String nickname, int remainingTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketSessionStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketSellTurnStarted(String nickname, int remainingTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketBuyTurnStarted(String nickname, int remainingTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketSessionFinished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarketItemBought(String marketId, String buyer) {
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

	@Override
	public void onPlayerDisconnected(String nickname) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLastTurnStarted(String nickname) {
		// TODO Auto-generated method stub

	}

}
