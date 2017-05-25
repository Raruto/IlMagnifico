package network.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import network.server.AbstractServer;
import network.server.IServer;
import network.server.ServerException;

/**
 * This class is built on top of {@link AbstractServer} and let Server to
 * communicate whit SocketClient.
 */
public class SocketServer extends AbstractServer {

	/**
	 * Server socket instance.
	 */
	private ServerSocket mServerSocket;

	/**
	 * Public constructor.
	 * 
	 * @param controller
	 *            server interface to communicate with him.
	 */
	public SocketServer(IServer controller) {
		super(controller);
	}

	/**
	 * Start the SocketServer connection.
	 * 
	 * @param port
	 *            number of the port to use.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	@Override
	public void startServer(int port) throws ServerException {
		try {
			mServerSocket = new ServerSocket(port);
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
					Socket socket = mServerSocket.accept();
					//System.out.println("New socket request");
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