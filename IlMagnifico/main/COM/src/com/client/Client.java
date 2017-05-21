package com.client;

import java.util.Scanner;

import com.client.rmi.RMIClient;
import com.client.socket.SocketClient;

public class Client {

	/**
	 * Available Connection Types to the Server
	 */
	enum ConnectionTypes {
		RMI, SOCKET;
	}

	/**
	 * Port where socket communication is open.
	 */
	private static final int SOCKET_PORT = 1098;

	/**
	 * Port where RMI communication is open.
	 */
	private static final int RMI_PORT = 1099;

	private AbstractClient client;

	/**
	 * Create a new instance of the class.
	 * 
	 * @throws ClientException
	 *             if some error occurs.
	 */
	public Client() throws ClientException {
		// socketServer = new SocketServer(this);
		// rmiServer = new RMIServer(this);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inText;

		System.out.print("Choose RMI or Socket: ");
		inText = scanner.nextLine().toUpperCase();

		try {
			Client client = new Client();
			client.startClient(inText);
			scanner.close();

		} catch (ClientException e) {
			e.printStackTrace();
			System.err.println("Exiting...");
		}
	}

	/**
	 * Start Client connections.
	 * 
	 * @param connectionType
	 *            string name of the choosed connection type
	 * @throws ClientException
	 *             if some error occurs.
	 */
	public void startClient(String connectionType) throws ClientException {
		if (connectionType.equals(ConnectionTypes.RMI)) {
			startRMIClient(RMI_PORT);
		} else if (connectionType.equals(ConnectionTypes.SOCKET)) {
			startSocketClient(SOCKET_PORT);
		} else {
			throw new ClientException(new Throwable("Uknown Connection Type"));
		}
	}

	/**
	 * Start RMIClient connection.
	 * 
	 * @param rmiPort
	 *            port where start RMI connection.
	 * @throws ClientException
	 *             if some error occurs.
	 */
	private void startRMIClient(int rmiPort) throws ClientException {
		System.out.println("Starting RMI Connection...");
		client = new RMIClient();
		client.startClient();
	}

	/**
	 * Start SocketClient connection.
	 * 
	 * @param sockePort
	 *            port where start Socket connection.
	 * @throws ClientException
	 *             if some error occurs.
	 */
	private void startSocketClient(int socketPort) throws ClientException {
		System.out.println("Starting Socket Connection...");
		client = new SocketClient();
		client.startClient();
	}

}
