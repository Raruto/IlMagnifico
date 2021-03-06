package main.network.server.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import main.model.errors.Errors;
import main.network.exceptions.LoginException;
import main.network.exceptions.PlayerNotFound;
import main.network.protocol.socket.SocketConstants;
import main.network.server.AbstractServer;
import main.network.server.IServer;
import main.network.server.ServerException;
import main.network.server.game.UpdateStats;
import main.network.server.game.exceptions.GameException;
import main.network.server.game.exceptions.JoinRoomException;
import main.util.Costants;

/**
 * Estende {@link AbstractServer} per consentire di implementare la
 * comunicazione Client/Server con i {@link SocketClient}.
 */
public class SocketServer extends AbstractServer {

	/**
	 * Socket del Server.
	 */
	private ServerSocket serverSocket;

	/**
	 *  ID usato per identificare il server nelle comunicazioni
	 */
	private final static String SOCKET_ID = Costants.SOCKET_SERVER_ID;

	/**
	 * Costruttore.
	 * 
	 * @param controller
	 *            interfaccia del Server (es. {@link Server}).
	 */
	public SocketServer(IServer controller) {
		super(controller);
	}

	/**
	 * Avvia il Server per le connessioni Socket.
	 * 
	 * @param port
	 *            numero di porta da usare.
	 * @throws ServerException
	 *             se si verifica un errore.
	 */
	@Override
	public void startServer(int port) throws ServerException {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println(SOCKET_ID + " OK");
			new SocketRequestListener().start();
		} catch (IOException e) {
			throw new ServerException("I/O exception occurs while starting Socket server", e);
		}
	}

	/**
	 * Thread per la ricezione e gestione di nuove richieste di connessione
	 * tramite Socket.
	 */
	private class SocketRequestListener extends Thread {

		/**
		 * Loop che attende nuovi Client e inizializza i relativi gestori.
		 */
		@Override
		public void run() {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					// System.out.println("New socket request");
					new RequestHandler(getController(), socket).start();
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

	/**
	 * Thread per la gestione delle richieste Client.
	 */
	public static class RequestHandler extends Thread {

		/**
		 * Interfaccia utilizzata per comunicare con il Server (es.
		 * {@link Server}).
		 */
		private final transient IServer server;

		/**
		 * Socket attraverso il quale il giocatore puo' comunicare con il Server
		 * e viceversa.
		 */
		private final transient Socket socket;

		/**
		 * Stream di Input per la ricezione degli oggetti serializzati dal
		 * Client.
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
		 * Giocatore Remoto associato al Thread.
		 */
		private SocketPlayer socketPlayer;

		/**
		 * Mappa di tutti i metodi di risposta definiti sul server (vedi
		 * {@link SocketRequestListener}).
		 */
		private final HashMap<Object, RequestHandlerInterface> requestMap;

		/**
		 * Costruttore.
		 * 
		 * @param controller
		 *            interfaccia del Server (es. {@link Server}).
		 * @param socket
		 *            socket usato per la comunicazione.
		 * @throws IOException
		 */
		public RequestHandler(IServer controller, Socket socket) throws IOException {
			this.server = controller;
			this.socket = socket;
			this.outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			this.outputStream.flush();
			this.inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

			requestMap = new HashMap<>();
			loadRequests();
		}

		/**
		 * Inizializza "responseMap" caricando tutti i possibili metodi di
		 * risposta (chiamati da {@link SocketRequestListener}).
		 */
		private void loadRequests() {
			requestMap.put(SocketConstants.LOGIN_REQUEST, this::sendLoginRequest);
			requestMap.put(SocketConstants.CHAT_MESSAGE, this::sendChatMessage);
			requestMap.put(SocketConstants.GAME_ACTION, this::sendGameActionRequest);
		}

		/////////////////////////////////////////////////////////////////////////////////////////
		// Thread per la gestione dei messaggi di richiesta (CLIENT --> SERVER)
		/////////////////////////////////////////////////////////////////////////////////////////

		/**
		 * Associa un {@link SocketPlayer} al Client e resta in ascolto sullo
		 * Stream di Input in attesa di messaggi di richiesta.
		 */
		@Override
		public void run() {
			try {
				this.socketPlayer = new SocketPlayer(outputStream, OUTPUT_MUTEX);
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Chiude correttamente la connessione Socket.
		 * 
		 * @param closeable
		 *            oggetto che implementa l'interfaccia {@link Closeable}.
		 * @param message
		 *            messaggio da stampare nel caso si scateni un eccezione
		 *            durante il tentativo di chiusura dell'oggetto.
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
		 *            {@link SocketConstants}).
		 */
		private void handleClientRequest(Object object) {
			RequestHandlerInterface handler = requestMap.get(object);
			if (handler != null) {
				synchronized (OUTPUT_MUTEX) {
					handler.handle();
				}
			}
		}

		/////////////////////////////////////////////////////////////////////////////////////////
		// Metodi "invocati" dal Client (basato su RMIServerInterface)
		/////////////////////////////////////////////////////////////////////////////////////////

		/**
		 * Prova a fare eseguire il login sul Server con il nickname fornito.
		 */
		private void sendLoginRequest() {
			try {
				String nickname = (String) inputStream.readObject();

				int responseCode;
				try {
					server.loginPlayer(nickname, this.socketPlayer);
					responseCode = SocketConstants.RESPONSE_OK;
				} catch (LoginException e) {
					System.err.println(SOCKET_ID + " LoginException");
					responseCode = SocketConstants.RESPONSE_PLAYER_ALREADY_EXISTS;
				}
				outputStream.writeObject(responseCode);
				outputStream.flush();

				if (responseCode != SocketConstants.RESPONSE_PLAYER_ALREADY_EXISTS) {
					try {
						server.joinFirstAvailableRoom(this.socketPlayer);
					} catch (JoinRoomException e) {
						//e.printStackTrace();
					}
				}

			} catch (ClassNotFoundException | ClassCastException | IOException e) {
				System.err.println("Exception while handling client request");
			}
		}

		/**
		 * Invia un messaggio in chat ad altri giocatori o un giocatore
		 * specifico.
		 */
		private void sendChatMessage() {
			try {
				String receiver = (String) inputStream.readObject();
				String message = (String) inputStream.readObject();
				try {
					server.sendChatMessage(this.socketPlayer, receiver, message);
				} catch (PlayerNotFound e) {
					System.err.println(SOCKET_ID + " cannot dispatch message to a player that cannot be found");
				}
			} catch (ClassNotFoundException | ClassCastException | IOException e) {
				System.err.println("Exception while handling client request");
			}
		}

		/**
		 * Invia una una richiesta di esecuzione di un'azione di gioco.
		 */
		private void sendGameActionRequest() {
			try {
				UpdateStats action = (UpdateStats) inputStream.readObject();
				this.socketPlayer.getRoom().performGameAction(this.socketPlayer, action);
			} catch (GameException e) {
				notifyActionNotValid(e.getMessage());
			} catch (ClassNotFoundException | ClassCastException | IOException e) {
				System.err.println("Exception while handling client request");
			}
		}

		/**
		 * Notifica al Client che si � verificato un errore.
		 * 
		 * @param errorCode
		 *            codice d'errore che lo identifica (vedi {@link Errors}).
		 */
		private void notifyActionNotValid(String errorCode) {
			synchronized (OUTPUT_MUTEX) {
				try {
					outputStream.writeObject(SocketConstants.ACTION_NOT_VALID);
					outputStream.writeObject(errorCode);
					outputStream.flush();
				} catch (IOException e) {
					System.err.println(SOCKET_ID + " Player is disconnected");
				}
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