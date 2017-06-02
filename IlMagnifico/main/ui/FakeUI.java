package main.ui;

import java.util.Scanner;

import main.model.enums.EAzioniGiocatore;
import main.network.client.Client;
import main.network.client.ClientException;
import main.network.protocol.ConnectionTypes;
import main.network.server.Server;
import main.network.server.ServerException;
import main.network.server.game.UpdateStats;
import main.util.Costants;

/**
 * Classe di comodo per simulare l'interazione da parte del CLIENT verso il
 * SERVER
 *
 */
public class FakeUI {
	public static Scanner scanner = new Scanner(System.in);
	public static String inText;

	static Client client;

	/**
	 * Get Singleton Client
	 * 
	 * @return {@link Client}
	 */
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

	/**
	 * Start as Client or Server.
	 * 
	 * @param args
	 */
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

	/**
	 * Start Client (RMI or Socket).
	 * 
	 * @param serverAddress
	 * @param socketPort
	 * @param rmiPort
	 */
	public static void mainClient(String serverAddress, int socketPort, int rmiPort) {

		System.out.print("[R]MI or [S]ocket? (Default: [R]): ");
		inText = scanner.nextLine().toUpperCase();

		if (inText.equals("S")) {
			inText = ConnectionTypes.SOCKET.toString();
		} else if (inText.equals("R")) {
			inText = ConnectionTypes.RMI.toString();
		}
		// Default: RMI
		else {
			inText = ConnectionTypes.RMI.toString();
			System.out.println("Connecting with RMI..");
		}

		try {
			Client client = getClient();
			client.startClient(inText, serverAddress, socketPort, rmiPort);
		} catch (ClientException e) {
			System.err.println(e.getMessage());
			System.err.println("Exiting...");
			System.exit(0);
		}

		FakeUI.login();
		FakeUI.sayHelloToPlayers();
		FakeUI.infiniteLoop();
	}

	/**
	 * Start Server (Client and Server).
	 * 
	 * @param socketPort
	 * @param rmiPort
	 */
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

	/**
	 * Login Client to Server.
	 */
	public static void login() {
		Client client = getClient();
		while (!client.isLogged()) {
			System.out.print("Choose Player Name: ");
			inText = scanner.nextLine();
			client.loginPlayer(inText);

		}

		System.out.println();
	}

	/**
	 * Hello World! to other players.
	 */
	public static void sayHelloToPlayers() {
		Client client = getClient();
		client.sendChatMessage(null, "hello!");
	}

	/**
	 * Client commmand chooser.
	 */
	public static void infiniteLoop() {
		boolean quit = false;

		while (!quit) {
			System.out.println("'q' to exit\n");
			System.out.println("Available commands: [chat], [action]");

			System.out.println(">");
			inText = scanner.nextLine();

			switch (inText.toLowerCase()) {
			case "q":
				quit = true;
				break;
			case "chat":
				FakeUI.sendChatMessages();
				break;

			case "action":
				FakeUI.performGameAction();
				break;

			default:
				break;
			}
		}
	}

	/**
	 * Client command: Game Action chooser.
	 */
	public static void performGameAction() {
		Client client = getClient();

		boolean ok = false;

		while (!ok) {
			System.out.println("'q' to quit\n");
			System.out.println("Available actions: ");
			/*
			 * for (EAzioniGiocatore act : EAzioniGiocatore.values()) {
			 * System.out.print("[" + act.toString() + "] "); }
			 */
			EAzioniGiocatore[] a = EAzioniGiocatore.values();

			for (int i = 0; i < a.length; i++) {
				System.out.print("[" + a[i].toString() + "] ");
				if (i % 7 == 0 && i != 0)
					System.out.println();
			}

			System.out.println();

			inText = scanner.nextLine().toLowerCase();

			if (inText.equals("q")) {
				ok = true;
			} else {
				for (EAzioniGiocatore act : EAzioniGiocatore.values()) {
					if (inText.equals(act.toString().toLowerCase())) {
						UpdateStats requestedAction = new UpdateStats(act);
						client.performGameAction(requestedAction);
					}
				}
			}

		}

	}

	/**
	 * Client command: send chat messages.
	 */
	public static void sendChatMessages() {
		Client client = getClient();
		String receiver = null;

		System.out.println("'q' to quit\n");
		System.out.println("Send text messages: ");

		boolean quit = false;

		while (!quit) {
			System.out.println(">");
			inText = scanner.nextLine();
			if (inText.toLowerCase().equals("q")) {
				quit = true;
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
