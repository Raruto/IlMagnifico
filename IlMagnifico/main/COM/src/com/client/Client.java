package com.client;

import java.util.Scanner;

import com.NetworkException;
import com.client.rmi.RMIClient;
import com.client.socket.SocketClient;
import com.exceptions.LoginException;

public class Client implements IClient {

	/**
	 * Available Connection Types to the Server
	 */
	enum ConnectionTypes {
		RMI, SOCKET;
	}

	/**
	 * Available info about Connection Health
	 */
	enum ConnectionStatus {
		OK, KO;
	}

	/**
	 * Server Address where communications are open
	 */
	private static final String ADDRESS = "127.0.0.1";

	/**
	 * Port where socket communication is open.
	 */
	private static final int SOCKET_PORT = 1098;

	/**
	 * Port where RMI communication is open.
	 */
	private static final int RMI_PORT = 1099;

	/** 
	 * 
	 */
	private boolean isLogged;

	/**
	 * Abstract class that represent the selected client (RMI or Socket).
	 */
	private AbstractClient client;

	/**
	 * Current player's nickname.
	 */
	private String nickname;

	/**
	 * Create a new instance of the class.
	 * 
	 * @throws ClientException
	 *             if some error occurs.
	 */
	public Client() throws ClientException {
		nickname = "anonymous";
		isLogged = false;

		// Moved into StarClient()
		// client = new RMIClient(this, "127.0.0.1", rmiPort);
		// client = new SocketClient(this, "127.0.0.1", socketPort);
	}

	public static void main(String[] args) {
		FakeUI.main();
	}

	public boolean isLogged() {
		return this.isLogged;
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

		FakeUI.login();

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
		client = new RMIClient(this, ADDRESS, rmiPort);
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
		client = new SocketClient(this, ADDRESS, socketPort);
		client.connect();
	}

	/**
	 * This method is triggered by {@link AbstractUi#showLoginMenu()}.
	 * 
	 * @param nickname
	 *            to use for login session.
	 */
	public void loginPlayer(String nickname) {
		boolean success = false;
		try {
			System.out.println("Try to login user with nickname: " + nickname);
			client.loginPlayer(nickname);
			success = true;
			// joinFirstAvailableRoom();
		} catch (LoginException e) {
			System.out.println("Nickname is already in use on server");
			// mUi.showLoginErrorMessage();
		} catch (NetworkException e) {
			System.out.println("Cannot send login request");
		}

		if (success) {
			this.nickname = nickname;
			this.isLogged = true;
			System.out.println("Succesfully logged in as: " + nickname);
		}
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
