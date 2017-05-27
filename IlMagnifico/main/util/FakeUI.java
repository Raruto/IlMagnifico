package main.util;

import java.util.Scanner;

import main.network.Costants;
import main.network.client.Client;
import main.network.client.ClientException;
import main.network.protocol.ConnectionTypes;
import main.network.server.Server;
import main.network.server.ServerException;

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

	public static void main(String[] args) {
		String serverAddress = Costants.SERVER_ADDRESS;
		int socketPort = Costants.SOCKET_PORT, rmiPort = Costants.RMI_PORT;

		// Check if arguments were passed in
		if (args.length != 0) {
			try {
				serverAddress = args[0];
				socketPort = Integer.parseInt(args[1]);
				rmiPort = Integer.parseInt(args[0]);
			} catch (Exception e) {
				System.out.println("Proper usage is: [\"serverAddress\" socketPort rmiPort]");
				System.exit(0);
			}
		}

		System.out.print("Start as [C]lient or [S]erver? (Default: [C]): ");
		inText = scanner.nextLine().toUpperCase();

		if (inText.equals("S")) {
			FakeUI.mainServer(socketPort, rmiPort);
		} else if (inText.equals("C")) {
			FakeUI.mainClient(serverAddress, socketPort, rmiPort);
		}
		// Default: Client
		else {
			System.out.println("Starting as Client..");
			FakeUI.mainClient(serverAddress, socketPort, rmiPort);
		}
	}

	public static void mainClient(String serverAddress, int socketPort, int rmiPort) {

		System.out.print("[RMI] or [Socket]? (Default: [RMI]): ");
		inText = scanner.nextLine().toUpperCase();

		// Dafult: RMI
		if (!inText.equals(ConnectionTypes.RMI.toString()) && !inText.equals(ConnectionTypes.SOCKET.toString())) {
			System.out.println("Connecting with RMI..");
			inText = ConnectionTypes.RMI.toString();
		}

		try {
			Client client = getClient();
			client.startClient(inText, serverAddress, socketPort, rmiPort);
		} catch (ClientException e) {
			e.printStackTrace();
			System.err.println("Exiting...");
			System.exit(0);
		}

		FakeUI.login();
		FakeUI.sayHelloToPlayers();
		FakeUI.infiniteLoop();
	}

	private static void mainServer(int socketPort, int rmiPort) {
		try {
			Server server = new Server();
			server.startServer(socketPort, rmiPort);

			System.out.print("\nServer listening at: ");
			System.out.println("127.0.0.1" + " (rmi: " + rmiPort + ", socket: " + socketPort + ")");
			System.out.println();

		} catch (ServerException e) {
			e.printStackTrace();
			System.err.println("Exiting...");
			System.exit(0);
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
				receiver = scanner.nextLine().trim();
				if (receiver.length() == 0)
					receiver = null;
				client.sendChatMessage(receiver, inText);
			}
		}

	}
}
