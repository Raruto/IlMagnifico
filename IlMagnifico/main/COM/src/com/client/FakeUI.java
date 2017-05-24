package com.client;

import java.util.Scanner;

/**
 * Classe di comodo per simulare l'interazione da parte del CLIENT verso il
 * SERVER
 *
 */
public class FakeUI {
	public static Scanner scanner = new Scanner(System.in);
	public static String inText;

	static Client client;

	public static Client getClient() {
		if (client == null) {
			try {
				client = new Client();
			} catch (ClientException e) {
				e.printStackTrace();
				System.err.println("Exiting...");
			}
		}
		return client;
	}

	public static void main() {

		System.out.print("Choose RMI or Socket: ");
		inText = scanner.nextLine().toUpperCase();

		try {
			Client client = getClient();
			client.startClient(inText);

			// scanner.close();

		} catch (ClientException e) {
			e.printStackTrace();
			System.err.println("Exiting...");
		}

	}

	public static void login() {
		Client client = getClient();
		while (!client.isLogged()) {
			System.out.print("Choose Player Name: ");
			inText = scanner.nextLine();
			client.loginPlayer(inText);

		}

		System.out.println();

	}

	public static void sayHelloToPlayers() {
		Client client = getClient();
		client.sendChatMessage(null, "hello!");

	}

	public static void infiniteLoop() {
		Client client = getClient();
		String receiver = null;

		System.out.println("'q' to quit\n");
		System.out.println("Send text messages: ");

		while (true) {
			System.out.println(">");
			inText = scanner.nextLine();
			if (inText.toLowerCase().equals("q")) {
				break;

			} else {

				System.out.println("to [playerName]: ");
				receiver = scanner.nextLine();
				if (receiver.length() == 0)
					receiver = null;
				client.sendChatMessage(receiver, inText);
			}
		}

	}
}
