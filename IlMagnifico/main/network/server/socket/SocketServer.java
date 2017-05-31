package main.network.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import main.network.server.AbstractServer;
import main.network.server.IServer;
import main.network.server.ServerException;

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
			System.out.println("[SOCKET Server] OK");
			new RequestHandler().start();
		} catch (IOException e) {
			throw new ServerException("I/O exception occurs while starting Socket server", e);
		}
	}

	/**
	 * This class is used to listen for new socket clients asynchronously.
	 */
	private class RequestHandler extends Thread {

		/**
		 * Loop that listen for new clients and initialize their handlers.
		 */
		@Override
		public void run() {
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					// System.out.println("New socket request");
					SocketPlayer socketPlayer = new SocketPlayer(getController(), socket);
					new Thread(socketPlayer).start();
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
}