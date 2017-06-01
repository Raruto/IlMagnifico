package main.network.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import main.model.Giocatore;
import main.network.NetworkException;
import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.protocol.ErrorCodes;
import main.network.protocol.socket.Constants;
import main.network.server.IServer;
import main.network.server.game.GameException;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Estende {@link RemotePlayer} implementando le funzionalità di comunicazione
 * al {@link Giocatore} Client associatogli.
 */
public class SocketPlayer extends RemotePlayer implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5294571565976357669L;

	/**
	 * Interfaccia utilizzata per comunicare con il Server (es. {@link Server}).
	 */
	private final transient IServer server;

	/**
	 * Socket attraverso il quale il giocatore può comunicare con il Server e
	 * viceversa.
	 */
	private final transient Socket socket;

	/**
	 * Stream di Input per la ricezione degli oggetti serializzati dal Client.
	 */
	private final transient ObjectInputStream inputStream;

	/**
	 * Stream di Output per l'invio di oggetti serializzati al Client.
	 */
	private final transient ObjectOutputStream outputStream;

	/**
	 * MUTEX per evitare la concorrenza tra Thread durante la scrittura sul
	 * flusso di uscita del Socket.
	 */
	private static final Object OUTPUT_MUTEX = new Object();

	/**
	 * Mappa di tutti i metodi di risposta definiti sul server (vedi
	 * {@link RequestHandler}).
	 */
	private final HashMap<Object, RequestHandlerInterface> requestMap;

	/**
	 * Crea un'istanza SocketPlayer.
	 * 
	 * @param server
	 *            interfaccia del Server (es. {@link Server}).
	 * @param socket
	 *            socket usato per la comunicazione.
	 * @throws IOException
	 *             se si verifica un errore durante l'inizializzazione degli
	 *             Stream di Input e Output.
	 */
	/* package-local */ SocketPlayer(IServer server, Socket socket) throws IOException {
		this.server = server;
		this.socket = socket;
		this.outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		this.outputStream.flush();
		this.inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		requestMap = new HashMap<>();
		loadRequests();
	}

	/**
	 * Inizializza "responseMap" caricando tutti i possibili metodi di risposta
	 * (chiamati da {@link RequestHandler}).
	 */
	private void loadRequests() {
		requestMap.put(Constants.LOGIN_REQUEST, this::loginPlayer);
		requestMap.put(Constants.CHAT_MESSAGE, this::sendChatMessage);
		requestMap.put(Constants.PERFORM_GAME_ACTION, this::performGameAction);
	}

	/**
	 * Invia un messaggio sulla chat del giocatore.
	 * 
	 * @param author
	 *            nome del giocatore MITTENTE del messaggio.
	 * @param message
	 *            messaggio da inviare.
	 * @param privateMessage
	 *            True se il messaggio è privato, False se pubblico.
	 * @throws NetworkException
	 *             se il cliente non è raggiungibile.
	 */
	@Override
	public void onChatMessage(String author, String message, boolean privateMessage) throws NetworkException {

		// sendChatMessage(author, message, privateMessage);
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(Constants.CHAT_MESSAGE);
				outputStream.writeObject(author);
				outputStream.writeObject(message);
				outputStream.writeObject(privateMessage);
				outputStream.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}

	}

	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(Constants.PERFORM_GAME_ACTION);
				outputStream.writeObject(update);
				outputStream.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws NetworkException {
		// TODO Auto-generated method stub

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi "invocati" dal Client (basato su RMIServerInterface)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Prova a fare eseguire il login sul Server con il nickname fornito.
	 */
	private void loginPlayer() {
		try {
			String nickname = (String) inputStream.readObject();

			int responseCode;
			try {
				server.loginPlayer(nickname, this);
				responseCode = Constants.RESPONSE_OK;
			} catch (LoginException e) {
				System.err.println("[socket protocol] LoginException");
				responseCode = Constants.RESPONSE_PLAYER_ALREADY_EXISTS;
			}
			outputStream.writeObject(responseCode);
			outputStream.flush();

			if (responseCode != Constants.RESPONSE_PLAYER_ALREADY_EXISTS) {
				try {
					server.joinFirstAvailableRoom(this);
				} catch (JoinRoomException e) {
					// e.printStackTrace();
				}
			}

		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling client request");
		}
	}

	/**
	 * Invia un messaggio in chat ad altri giocatori o un giocatore specifico.
	 */
	private void sendChatMessage() {
		try {
			String receiver = (String) inputStream.readObject();
			String message = (String) inputStream.readObject();

			try {
				// getRoom().sendChatMessage(this, receiver, message);
				server.sendChatMessage(this, receiver, message);
			} catch (PlayerNotFound e) {
				System.err.println("[socket player] cannot dispatch message to a player that cannot be found");
			}

		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling client request");
		}
	}

	private void performGameAction() {
		try {
			UpdateStats action = (UpdateStats) inputStream.readObject();

			getRoom().performGameAction(this, action);

		} catch (GameException e) {
			handleGameExceptions(e);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {

			System.err.println("Exception while handling client request");
		}
	}

	/**
	 * Handle every exception that can be thrown while doing a main / fast
	 * action.
	 * 
	 * @param e
	 *            exception to handle.
	 */
	private void handleGameExceptions(GameException e) {
		actionNotValid(ErrorCodes.ERROR_GENERIC_SERVER_ERROR);
		/*
		 * if (e instanceof PoliticCardNotYetDrawn) {
		 * Debug.debug(DEBUG_POLITIC_CARD_NOT_YET_DRAWN, e);
		 * mSocketProtocol.actionNotValid(ErrorCodes.
		 * ERROR_POLITIC_CARD_NOT_YET_DRAWN); } else if (e instanceof
		 * MainActionNotAvailable) {
		 * Debug.debug(DEBUG_MAIN_ACTION_NOT_AVAILABLE, e);
		 * mSocketProtocol.actionNotValid(ErrorCodes.
		 * ERROR_MAIN_ACTION_NOT_AVAILABLE); } else if (e instanceof
		 * FastActionNotAvailable) {
		 * Debug.debug(DEBUG_FAST_ACTION_NOT_AVAILABLE, e);
		 * mSocketProtocol.actionNotValid(ErrorCodes.
		 * ERROR_FAST_ACTION_NOT_AVAILABLE); } else if (e instanceof
		 * NotYourTurnException) { Debug.debug(DEBUG_NOT_YOUR_TURN, e);
		 * mSocketProtocol.actionNotValid(ErrorCodes.ERROR_NOT_PLAYER_TURN); }
		 * else { handleGameLogicExceptions(e); }
		 */
	}

	/**
	 * Notify the client that an error as occurred.
	 * 
	 * @param errorCode
	 *            that identify the error. {@link ErrorCodes} for details.
	 */
	private void actionNotValid(int errorCode) {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(Constants.ACTION_NOT_VALID);
				outputStream.writeObject(errorCode);
				outputStream.flush();
			} catch (IOException e) {
				System.err.println("[socket protocol] Player is disconnected");
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Thread per la gestione dei messaggi di richiesta (CLIENT --> SERVER)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Resta in ascolto sullo Stream di Input in attesa di messaggi di richiesta
	 * dal Client.
	 */
	@Override
	public void run() {
		try {
			// noinspection InfiniteLoopStatement
			while (true) {
				Object object = inputStream.readObject();
				handleClientRequest(object);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e);
		} finally {
			closeSafely(inputStream, "I/O error occurs when closing input stream");
			closeSafely(outputStream, "I/O error occurs when closing output stream");
			closeSafely(socket, "I/O error occurs when closing socket");
		}
	}

	/**
	 * Chiude correttamente la connessione Socket.
	 * 
	 * @param closeable
	 *            oggetto che implementa l'interfaccia {@link Closeable}.
	 * @param message
	 *            messaggio da stampare nel caso si scateni un eccezione durante
	 *            il tentativo di chiusura dell'oggetto.
	 */
	private void closeSafely(Closeable closeable, String message) {
		try {
			closeable.close();
		} catch (IOException e) {
			System.err.println(message);
		}
	}

	/**
	 * Gestisce la richiesta ricevuta dal Client ed invoca il metodo
	 * associatogli nella "responseMap".
	 * 
	 * @param object
	 *            intestazione della risposta ricevuta dal server (es.
	 *            {@link Constants}).
	 */
	public void handleClientRequest(Object object) {
		RequestHandlerInterface handler = requestMap.get(object);
		if (handler != null) {
			synchronized (OUTPUT_MUTEX) {
				handler.handle();
			}
		}
	}

	/**
	 * Interfaccia utilizzata "come" l'interfaccia {@link Runnable}.
	 */
	@FunctionalInterface
	private interface RequestHandlerInterface {

		/**
		 * Gestisce la richiesta del Client.
		 */
		void handle();
	}
}
