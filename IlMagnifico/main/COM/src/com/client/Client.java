package com.client;

import java.util.Scanner;

import com.client.rmi.RMIClient;
import com.client.socket.SocketClient;
import com.server.Server;
import com.server.ServerException;

public class Client {

	/**
	 * Command line Messages
	 */
	private static final String CMD_CHOOSE_RMI_CONNECTION = "RMI";
	private static final String CMD_CHOOSE_SOCKET_CONNECTION = "SOCKET";

	/**
	 * Ports where communication is open.
	 */
	private static final int SOCKET_PORT = 1098;
	private static final int RMI_PORT = 1099;

	private AbstractClient client;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inText;

		System.out.println("Choose RMI or Socket:");
		inText = scanner.nextLine().toUpperCase();

		Client client = new Client();
		if (inText.equals(CMD_CHOOSE_RMI_CONNECTION)) {
			client.startRMIClient(RMI_PORT);
		} else if (inText.equals(CMD_CHOOSE_SOCKET_CONNECTION)) {
			client.startSocketClient(SOCKET_PORT);
		} else {
			System.out.println("Exiting...");
			System.exit(0);
		}
		scanner.close();

	}

	private void startRMIClient(int rmiPort) {
		System.out.println("Starting RMI Connection...");
		try {
			client = new RMIClient();
			client.connect();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	private void startSocketClient(int socketPort) {
		System.out.println("Starting Socket Connection...");
		try {
			client = new SocketClient();
			client.connect();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
