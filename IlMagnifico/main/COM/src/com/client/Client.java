package com.client;

import java.util.Scanner;

import com.client.rmi.RMIClient;
import com.client.socket.SocketClient;

public class Client implements IClient {

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
		// Moved into StarClient()
		//client = new RMIClient(this, "127.0.0.1", rmiPort);
		//client = new SocketClient(this, "127.0.0.1", socketPort);
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
		if (connectionType.equals(ConnectionTypes.RMI.toString())) {
			startRMIClient(RMI_PORT);
		} else if (connectionType.equals(ConnectionTypes.SOCKET.toString())) {
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
		client = new RMIClient(this, "127.0.0.1", rmiPort);
		client.connect();
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
		client = new SocketClient(this, "127.0.0.1", socketPort);
		client.connect();
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

	@Override
	public void onChatMessage(boolean privateMessage, String author, String message) {
		// TODO Auto-generated method stub

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
