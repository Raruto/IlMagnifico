package com.server;

import com.server.rmi.RMIServer;
import com.server.socket.SocketServer;

public class Server {

	/**
	 * Port where socket communication is open.
	 */
	private static final int SOCKET_PORT = 3031;

	/**
	 * Port where RMI communication is open.
	 */
	private static final int RMI_PORT = 3032;

	/**
	 * Socket server.
	 */
	private SocketServer socketServer;

	/**
	 * RMI server.
	 */
	private RMIServer rmiServer;

	/**
	 * Create a new instance of the class.
	 * 
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private Server() throws ServerException {
		socketServer = new SocketServer(this);
		rmiServer = new RMIServer(this);
	}

	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.startServer(SOCKET_PORT, RMI_PORT);
		} catch (ServerException e) {
			e.printStackTrace();
		}
		

	}

	/**
	 * Start server connections.
	 * 
	 * @param socketPort
	 *            port where start Socket connection.
	 * @param rmiPort
	 *            port where start RMI connection.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	private void startServer(int socketPort, int rmiPort) throws ServerException {
		socketServer.startServer(socketPort);
		rmiServer.startServer(rmiPort);
	}
}
