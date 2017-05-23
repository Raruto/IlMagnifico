package com.client;

import java.util.Scanner;

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
}
