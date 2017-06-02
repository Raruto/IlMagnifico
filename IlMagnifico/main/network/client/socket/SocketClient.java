package main.network.client.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import main.network.NetworkException;
import main.network.client.AbstractClient;
import main.network.client.ClientException;
import main.network.client.IClient;
import main.network.exceptions.LoginException;
import main.network.protocol.socket.SocketConstants;
import main.network.server.game.UpdateStats;

/**
 * Classe che gestisce la connessione di rete con Socket. Estende
 * {@link AbstractClient}.
 */
public class SocketClient extends AbstractClient {

	/**
	 * Socket del Client.
	 */
	private Socket socketClient;

	/**
	 * Stream di Input per la ricezione degli oggetti serializzati dal Server.
	 */
	private ObjectInputStream inputStream;

	/**
	 * Stream di Output per l'invio di oggetti serializzati al Server.
	 */
	private ObjectOutputStream outputStream;

	/**
	 * MUTEX per evitare la concorrenza tra Thread durante la scrittura sul
	 * flusso di uscita del Socket.
	 */
	private static final Object OUTPUT_MUTEX = new Object();

	/**
	 * Mappa di tutti i metodi di risposta definiti sul server (vedi
	 * {@link ResponseHandler}).
	 */
	private final HashMap<Object, ResponseHandlerInterface> responseMap;

	/**
	 * Crea un'istanza SocketClient.
	 * 
	 * @param controller
	 *            client controller (es. {@link Client}).
	 * @param address
	 *            indirizzo del the server.
	 * @param port
	 *            porta del server.
	 */
	public SocketClient(IClient controller, String address, int port) {
		super(controller, address, port);
		responseMap = new HashMap<>();
	}

	/**
	 * Apre una connessione con {@link SocketServer} e inizializza il
	 * {@link ClientProtocol}.
	 * 
	 * @throws ClientException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	@Override
	public void connect() throws ClientException {
		try {
			socketClient = new Socket(getAddress(), getPort());

			System.out.println("Socket Connection established (port: " + this.getPort() + ")");

			outputStream = new ObjectOutputStream(new BufferedOutputStream(socketClient.getOutputStream()));
			outputStream.flush();
			inputStream = new ObjectInputStream(new BufferedInputStream(socketClient.getInputStream()));

		} catch (IOException e) {
			throw new ClientException(e);
		}

		loadResponses();
	}

	/**
	 * Inizializza "responseMap" caricando tutti i possibili metodi di risposta
	 * (chiamati da {@link ResponseHandler}).
	 */
	private void loadResponses() {
		responseMap.put(SocketConstants.CHAT_MESSAGE, this::notifyChatMessage);
		responseMap.put(SocketConstants.ACTION_NOT_VALID, this::notifyActionNotValid);
		responseMap.put(SocketConstants.GAME_ACTION, this::notifyGameUpdate);
	}

	/**
	 * Avvia un nuovo Thread che rimane in attesa di messaggi sul flusso di
	 * ingresso del socket e li elabora secondo il Protocollo definito.
	 */
	private void startResponseHandler() {
		ResponseHandler responseHandler = new ResponseHandler();
		responseHandler.start();
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi invocati dal Client (GUI) (vedi AbstractClient)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Esegue il login del giocatore al SocketServer con il nickname fornito.
	 * 
	 * @param nickname
	 *            nome da utilizzare per identificarsi al server.
	 * @throws NetworkException
	 *             se il server non è raggiungibile.
	 */
	@Override
	public void sendLoginRequest(String nickname) throws NetworkException {
		int responseCode;
		try {
			outputStream.writeObject(SocketConstants.LOGIN_REQUEST);
			outputStream.writeObject(nickname);
			outputStream.flush();

			responseCode = (int) inputStream.readObject();
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			throw new NetworkException(e);
		}
		if (responseCode == SocketConstants.RESPONSE_PLAYER_ALREADY_EXISTS) {
			throw new LoginException();
		} else {
			startResponseHandler();
		}
	}

	/**
	 * Invia un messaggio in chat ad altri giocatori o un giocatore specifico.
	 * 
	 * @param receiver
	 *            nome del DESTINATARIO del messaggio. Se null il messaggio
	 *            verrà inviato a tutti i giocatori.
	 * @param message
	 *            messaggio da inviare.
	 * @throws NetworkException
	 *             se il server non è raggiungibile o qualcosa è andato storto.
	 */
	@Override
	public void sendChatMessage(String receiver, String message) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(SocketConstants.CHAT_MESSAGE);
				outputStream.writeObject(receiver);
				outputStream.writeObject(message);
				outputStream.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	@Override
	public void sendGameActionRequest(UpdateStats requestedAction) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(SocketConstants.GAME_ACTION);
				outputStream.writeObject(requestedAction);
				outputStream.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Metodi "invocati" dal Server (basato su RMIClientInterface)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Notifica al giocatore che è stato ricevuto un nuovo messaggio sulla chat.
	 */
	private void notifyChatMessage() {
		try {
			String author = (String) inputStream.readObject();
			String message = (String) inputStream.readObject();
			boolean privateMessage = (boolean) inputStream.readObject();
			getController().onChatMessage(privateMessage, author, message);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling server message");
		}
	}

	private void notifyGameUpdate() {
		try {
			UpdateStats update = (UpdateStats) inputStream.readObject();
			getController().onGameUpdate(update);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling server message");
		}
	}

	private void notifyActionNotValid() {
		try {
			String errorCode = (String) inputStream.readObject();
			getController().onActionNotValid(errorCode);
		} catch (ClassNotFoundException | ClassCastException | IOException e) {
			System.err.println("Exception while handling server message");
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	// Thread per la gestione dei messaggi di risposta (SERVER --> CLIENT)
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Thread per la ricezione e gestione dei messaggi in ingresso dal Server.
	 */
	private class ResponseHandler extends Thread {

		/**
		 * Loop che attende messaggi di risposta dal Server e invocando i metodi
		 * appropriati.
		 */
		@Override
		public void run() {
			while (true) {
				boolean quit = false;
				try {
					Object object = inputStream.readObject();
					handleResponse(object);
				} catch (ClassNotFoundException | IOException e) {
					System.err.println("Cannot read server response");
					quit = true;
				}
				if (quit) {
					break;
				}
			}
			closeSafely(inputStream, "I/O error occurs when closing input stream");
			closeSafely(outputStream, "I/O error occurs when closing output stream");
			closeSafely(socketClient, "I/O error occurs when closing socket");
		}

		/**
		 * Gestisce la risposta ricevuta dal Server ed invoca il metodo
		 * associatogli nella "responseMap".
		 * 
		 * @param object
		 *            intestazione della risposta ricevuta dal server (es.
		 *            {@link SocketConstants}).
		 */
		private void handleResponse(Object object) {
			ResponseHandlerInterface handler = responseMap.get(object);
			if (handler != null) {
				handler.handle();
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
	}

	/**
	 * Interfaccia utilizzata "come" l'interfaccia {@link Runnable}.
	 */
	@FunctionalInterface
	private interface ResponseHandlerInterface {

		/**
		 * Gestisce la risposta del Server.
		 */
		void handle();
	}

}