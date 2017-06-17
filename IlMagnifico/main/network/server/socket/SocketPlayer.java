package main.network.server.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

import main.model.Giocatore;
import main.network.NetworkException;
import main.network.protocol.socket.SocketConstants;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

/**
 * Estende {@link RemotePlayer} implementando le funzionalita' di comunicazione
 * al {@link Giocatore} Client associatogli.
 */
public class SocketPlayer extends RemotePlayer {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5294571565976357669L;

	/**
	 * Stream di Output per l'invio di oggetti serializzati al Client.
	 */
	private final transient ObjectOutputStream outputStream;

	/**
	 * MUTEX per evitare la concorrenza tra Thread durante la scrittura sul
	 * flusso di uscita del Socket.
	 */
	private final transient Object OUTPUT_MUTEX;

	/**
	 * Crea un'istanza SocketPlayer.
	 * 
	 * @param outputStream
	 *            stream di uscita (per l'invio di oggetti serializzati al
	 *            Client.).
	 * @param OUTPUT_MUTEX
	 *            per evitare la concorrenza tra Thread durante la scrittura sul
	 *            flusso di uscita del Socket.
	 * @throws IOException
	 *             se si verifica un errore durante l'inizializzazione dello
	 *             Stream di Output.
	 */
	SocketPlayer(ObjectOutputStream outputStream, Object OUTPUT_MUTEX) throws IOException {
		this.outputStream = outputStream;
		this.outputStream.flush();

		this.OUTPUT_MUTEX = OUTPUT_MUTEX;
	}

	/**
	 * Invia un messaggio sulla chat del giocatore.
	 * 
	 * @param author
	 *            nome del giocatore MITTENTE del messaggio.
	 * @param message
	 *            messaggio da inviare.
	 * @throws NetworkException
	 *             se il client non e' raggiungibile.
	 */
	@Override
	public void onChatMessage(String author, String message) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(SocketConstants.CHAT_MESSAGE);
				outputStream.writeObject(author);
				outputStream.writeObject(message);
				outputStream.flush();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	/**
	 * Invia al giocatore un aggiornamento sullo stato della partita.
	 * 
	 * @param update
	 *            {@link UpdateStats}
	 * 
	 * @throws NetworkException
	 *             se il client non e' raggiungibile.
	 */
	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		synchronized (OUTPUT_MUTEX) {
			try {
				outputStream.writeObject(SocketConstants.GAME_ACTION);
				outputStream.writeObject(update);
				outputStream.flush();
				outputStream.reset();
			} catch (IOException e) {
				throw new NetworkException(e);
			}
		}
	}

	/**
	 * Metodo per il "debug"
	 */
	@Override
	public void send(Object object) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
