package main.ui;

import java.util.Scanner;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.network.client.Client;
import main.network.client.ClientException;
import main.network.protocol.ConnectionTypes;
import main.network.server.Server;
import main.network.server.ServerException;
import main.network.server.game.UpdateStats;
import main.util.ANSI;
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

		boolean success = false;
		int attempts = Costants.MAX_CONNECTION_ATTEMPTS;
		int sec = Costants.CONNECTION_RETRY_SECONDS * 1000;
		while (!success && attempts > 0) {
			try {
				attempts--;
				Client client = getClient();
				client.startClient(inText, serverAddress, socketPort, rmiPort);
				success = true;
			} catch (ClientException e) {
				if (attempts > 0) {
					System.err.println(e.getMessage() + " (" + "Retry in " + sec / 1000 + " seconds" + ", " + attempts
							+ " attemps left)");
					try {
						Thread.sleep(sec);
					} catch (InterruptedException ie) {
						// TODO Auto-generated catch block
					}
				}
			}
		}

		if (success) {
			FakeUI.login();
			FakeUI.sayHelloToPlayers();
			FakeUI.infiniteLoop();
		} else {
			System.err.println("\nCannot establish a connection to the server, the program will launch a local server");

			FakeUI.mainServer(socketPort, rmiPort);
		}
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
			System.err.println(e.getMessage());
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

		EAzioniGiocatore action = chooseGameAction();

		switch (action) {
		case Mercato:
			movePawn(action);
			break;
		case Produzione:
			movePawn(action);
			break;
		case ProduzioneOvale:
			movePawn(action);
			break;
		case Raccolto:
			movePawn(action);
			break;
		case RaccoltoOvale:
			movePawn(action);
			break;
		case PalazzoConsiglio:
			movePawn(action);
			break;
		case Torre:
			movePawn(action);
			break;

		case SostegnoChiesa:
			supportChurch();
			break;

		default:
			System.out.println(ANSI.YELLOW + "Not yet implmented" + ANSI.RESET);
			break;
		}

	}

	private static void supportChurch() {
		boolean ok = false;
		while (!ok) {
			System.out.println("'q' to quit\n");
			System.out.println("Support the Church? [y/n]: ");
			inText = scanner.nextLine().toLowerCase();

			if (inText.equals("q")) {
				ok = true;
			} else if (inText.equalsIgnoreCase("y")) {
				client.supportChurch(true);
				ok=true;
			}else if (inText.equalsIgnoreCase("n")) {
				client.supportChurch(false);
				ok=true;
			}
		}

	}

	private static void movePawn(EAzioniGiocatore action) {
		EColoriPedine color = choosePawnColor();
		if (color != null) {
			Integer position = chooseANumber();
			if (position != null) {
				client.movePawn(action, color, position);
			}
		}
	}

	private static EAzioniGiocatore chooseGameAction() {
		boolean ok = false;
		while (!ok) {
			System.out.println("'q' to quit\n");
			System.out.println("Available actions: ");
			System.out.println(EAzioniGiocatore.stringify());
			inText = scanner.nextLine().toLowerCase();

			if (inText.equals("q")) {
				ok = true;
			} else {
				for (EAzioniGiocatore act : EAzioniGiocatore.values()) {
					if (inText.equals(act.toString().toLowerCase())) {
						return act;
					}
				}
			}
		}
		return null;
	}

	private static EColoriPedine choosePawnColor() {
		boolean ok = false;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.println("Choose a Pawn Color: ");
			System.out.println(EColoriPedine.stringify());
			inText = scanner.nextLine().toLowerCase();

			if (inText.equals("q")) {
				ok = true;
			} else {
				for (EColoriPedine col : EColoriPedine.values()) {
					if (inText.equals(col.toString().toLowerCase())) {
						return col;
					}
				}
			}
		}
		return null;
	}

	private static Integer chooseANumber() {
		boolean ok = false;
		int number;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.println("Choose a Number: ");
			inText = scanner.nextLine().toLowerCase();

			if (inText.equals("q")) {
				ok = true;
			} else {
				try {
					number = Integer.parseInt(inText);
					return number;
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
			}
		}
		return null;
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
