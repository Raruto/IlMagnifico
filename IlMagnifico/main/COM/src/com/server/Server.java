package com.server;

import com.server.rmi.RMIServer;
import com.server.socket.SocketServer;

public class Server {

	/**
	 * Port where socket communication is open.
	 */
	private static final int SOCKET_PORT = 1098;

	/**
	 * Port where RMI communication is open.
	 */
	private static final int RMI_PORT = 1099;

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
		startRMIServer(rmiPort);
		startSocketServer(socketPort);
	}

	private void startRMIServer(int rmiPort) throws ServerException {
		System.out.println("Starting RMI Server...");
		rmiServer.startServer(rmiPort);
	}

	private void startSocketServer(int socketPort) throws ServerException {
		System.out.println("Starting Socket Server...");
		socketServer.startServer(socketPort);
	}
}
