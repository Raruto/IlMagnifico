package main.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import main.model.Carta;
import main.model.Edificio;
import main.model.Famigliare;
import main.model.Impresa;
import main.model.Personaggio;
import main.model.Plancia;
import main.model.Punti;
import main.model.Risorsa;
import main.model.SpazioAzione;
import main.model.Territorio;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.ECarte;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.ETipiCarte;
import main.network.client.Client;
import main.network.client.ClientException;
import main.network.protocol.ConnectionTypes;
import main.network.server.Server;
import main.network.server.ServerException;
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
	 * Client command chooser.
	 */
	public static void infiniteLoop() {
		boolean quit = false;

		while (!quit) {
			System.out.println("'q' to exit\n");
			System.out.println("Available commands: [chat], [action], [board], [dash], [cards]");

			System.out.println(">");
			inText = scanner.nextLine();

			switch (inText.toLowerCase()) {
			case "q":
				System.out.println("Sure to close connection with server? [y/n]");
				if (scanner.nextLine().equalsIgnoreCase("y"))
					quit = true;
				// TODO: gestire terminazione corretta del programma!
				System.exit(0);
				break;
			case "chat":
				FakeUI.sendChatMessages();
				break;

			case "action":
				FakeUI.performGameAction();
				break;

			case "board":
				FakeUI.printBoard();
				break;

			case "dash":
				FakeUI.printDashBoard();
				break;

			case "cards":
				FakeUI.printCards(true, true);
				break;

			default:
				break;
			}
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Command: [chat]
	/////////////////////////////////////////////////////////////////////////////////////////
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

	/////////////////////////////////////////////////////////////////////////////////////////
	// Command: [action]
	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Client command: Game Action chooser.
	 */
	public static void performGameAction() {
		EAzioniGiocatore action;
		boolean quit = false;

		while (!quit) {
			try {
				action = chooseGameAction();
				handleGameAction(action);
			} catch (QuitException e) {
				quit = true;
			}
		}
	}

	private static void handleGameAction(EAzioniGiocatore action) throws QuitException {
		switch (action) {
		case Mercato:
			handleMarket();
			break;
		case Produzione:
			handleProduction();
			break;
		case ProduzioneOvale:
			handleOvalProduction();
			break;
		case Raccolto:
			handleHarvest();
			break;
		case RaccoltoOvale:
			handleOvalHarvest();
			break;
		case PalazzoConsiglio:
			handleCouncilPalace();
			break;
		case Torre:
			handleTowers();
			break;
		case SostegnoChiesa:
			handleChurchSupport();
			break;
		case Famigliare:
			handleFamiliar();
			break;
		default:
			System.out.println(ANSI.YELLOW + "Not yet implemented" + ANSI.RESET);
			break;
		}
	}

	private static void handleOvalProduction() throws QuitException {
		try {
			movePawn(EAzioniGiocatore.ProduzioneOvale, 0);
			// quit = true;
		} catch (QuitException e) {
		}
		throw new QuitException();
	}

	private static void handleOvalHarvest() throws QuitException {
		try {
			movePawn(EAzioniGiocatore.RaccoltoOvale, 0);
			// quit = true;
		} catch (QuitException e) {
		}
		throw new QuitException();
	}

	private static void handleFamiliar() throws QuitException {
		try {
			printDices(true, true);

			// System.out.println(Costants.ROW_SEPARATOR);

			// printPawns(false, true);
			EColoriPedine color = choosePawnColor();
			if (color != null) {
				incrementPawn(color);
				// quit = true;
			}
		} catch (QuitException e) {
		}
		throw new QuitException();

	}

	private static void handleChurchSupport() throws QuitException {
		try {
			supportChurch();
			// quit = true;
		} catch (QuitException e) {
		}
		throw new QuitException();
	}

	private static void handleTowers() throws QuitException {

		boolean nestedQuit = false;
		Integer nestedPosition;

		while (!nestedQuit) {
			// printTowerArea(true, true);
			nestedPosition = chooseTowerArea();
			if (nestedPosition != null) {
				try {
					ECostiCarte[] costs = chooseCardCost(nestedPosition);

					if (costs == null) {
						movePawn(EAzioniGiocatore.Torre, nestedPosition, true);
					} else {
						EColoriPedine color = choosePawnColor();

						if (color != null) {
							client.movePawn(EAzioniGiocatore.Torre, color, nestedPosition, costs);
						}

					}

					// quit = true;
					nestedQuit = true;
				} catch (QuitException e) {
					nestedQuit = false;
				}
			} else
				nestedQuit = true;
		}
		throw new QuitException();
	}

	private static void handleCouncilPalace() throws QuitException {

		printPointsAndResources(true, false);
		printPawns(true, false);

		printCouncilArea(true, true);

		try {
			movePawn(EAzioniGiocatore.PalazzoConsiglio, 0);
			// quit = true;
		} catch (QuitException e) {
		}
		throw new QuitException();

	}

	private static void handleHarvest() throws QuitException {
		printPointsAndResources(true, false);
		printPawns(true, false);

		boolean nestedQuit = false;

		while (!nestedQuit) {
			printHarvestArea(true, true);
			EAzioniGiocatore nestedAction = chooseHarvestArea();
			if (nestedAction != null) {
				try {
					movePawn(nestedAction, 0);
					// quit = true;
					nestedQuit = true;
				} catch (QuitException e) {
					nestedQuit = false;
				}
			} else
				nestedQuit = true;
		}
		throw new QuitException();
	}

	private static void handleProduction() throws QuitException {

		printPointsAndResources(true, false);
		printPawns(true, false);

		boolean nestedQuit = false;

		while (!nestedQuit) {
			printProductionArea(true, true);
			EAzioniGiocatore nestedAction = chooseProductionArea();
			if (nestedAction != null) {
				try {
					movePawn(nestedAction, 0);
					// quit = true;
					nestedQuit = true;
				} catch (QuitException e) {
					nestedQuit = false;
				}
			} else
				nestedQuit = true;
		}
		throw new QuitException();
	}

	private static void handleMarket() throws QuitException {
		ESceltePrivilegioDelConsiglio[] privileges = new ESceltePrivilegioDelConsiglio[] { null, null };

		printPointsAndResources(true, false);
		printPawns(true, false);

		Integer nestedPosition;
		boolean nestedQuit = false;

		while (!nestedQuit) {
			printMarketArea(true, true);
			nestedPosition = chooseMarketArea();
			if (nestedPosition != null) {
				try {
					// movePawn(action, nestedPosition, privilege);
					EColoriPedine color = choosePawnColor();

					if (color != null) {
						if (nestedPosition == 3) {
							privileges[0] = chooseCouncilPrivilege(new ArrayList<ESceltePrivilegioDelConsiglio>());
							privileges[1] = chooseCouncilPrivilege(Arrays.asList(privileges));
						}
						client.movePawn(EAzioniGiocatore.Mercato, color, nestedPosition, privileges);
					}

					nestedQuit = true;
					// quit = true;
				} catch (QuitException e) {
					nestedQuit = false;
				}
			} else
				nestedQuit = true;
		}
		throw new QuitException();
	}

	private static void incrementPawn(EColoriPedine color) throws QuitException {
		Integer value = numberChooser("Add Servants: [0..*]");
		if (value != null) {
			client.incrementPawnValue(color, value);
		}
	}

	private static ECostiCarte[] chooseCardCost(int position) throws QuitException {
		Client client = getClient();
		SpazioAzione board = client.getGameBoard();
		ArrayList<ECostiCarte> costs;
		List<ECostiCarte> choosed = new ArrayList<ECostiCarte>();

		int number;

		if (board.getCartaTorre(position) != null) {
			int choices = board.getCartaTorre(position).getNumeroScelteCosti();

			if (choices > 0) {
				costs = board.getCartaTorre(position).getCostiCarta();
				int nCosts = costs.size();

				boolean ok = false;

				while (!ok && choices > 0) {
					// System.out.println("'q' to quit\n");

					System.out.println(Costants.ROW_SEPARATOR);
					printCardInfo(board.getCartaTorre(position));

					System.out.println("Available costs options: " + costs.size());
					System.out.print("\n (" + (nCosts - choices - 1) + " choices left) Choose a cost #: [1..*] ");

					inText = scanner.nextLine();
					if (inText.equals("q")) {
						throw new QuitException();
					} else {
						try {
							number = Integer.parseInt(inText);

							if (number <= 0 || number > costs.size())
								throw new NumberFormatException();

							choosed.add(costs.get(number - 1));
							choices--;
							/*
							 * for (ECostiCarte cost : ECostiCarte.values()) {
							 * if (number == cost.ordinal()) {
							 * choosed.add(cost); choices--; } }
							 */
						} catch (NumberFormatException e) {
							/*
							 * for (ECostiCarte cost : ECostiCarte.values()) {
							 * if (inText.equalsIgnoreCase(cost.toString())) {
							 * choosed.add(cost); choices--; } }
							 */
						}
					}
				}
			}
		}
		return choosed.toArray(new ECostiCarte[choosed.size()]);
	}

	private static EAzioniGiocatore chooseGameAction() throws QuitException {
		ArrayList<EAzioniGiocatore> hidedElements = new ArrayList<EAzioniGiocatore>();
		hidedElements.add(EAzioniGiocatore.ProduzioneOvale);
		hidedElements.add(EAzioniGiocatore.RaccoltoOvale);

		boolean ok = false;

		while (!ok) {
			printPlayerTurn(false);
			System.out.println(", 'q' to quit\n");
			System.out.println("Available actions: ");
			System.out.println(EAzioniGiocatore.stringify(hidedElements));
			inText = scanner.nextLine();

			if (inText.equals("q")) {
				throw new QuitException();
			} else {
				for (EAzioniGiocatore act : EAzioniGiocatore.values()) {
					if (inText.equalsIgnoreCase(act.toString())) {
						return act;
					}
				}
			}
		}
		return null;
	}

	private static EAzioniGiocatore chooseProductionArea() {
		boolean ok = false;
		int number;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.print("Choose a Production area: [1] [2] ");

			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				ok = true;
			} else {
				try {
					number = Integer.parseInt(inText);
					if (number == 1) {
						return EAzioniGiocatore.Produzione;
					} else if (number == 2) {
						return EAzioniGiocatore.ProduzioneOvale;
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
			}
		}
		return null;
	}

	private static EAzioniGiocatore chooseHarvestArea() {
		boolean ok = false;
		int number;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.print("Choose a Harvest area: [1] [2] ");

			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				ok = true;
			} else {
				try {
					number = Integer.parseInt(inText);
					if (number == 1) {
						return EAzioniGiocatore.Raccolto;
					} else if (number == 2) {
						return EAzioniGiocatore.RaccoltoOvale;
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
			}
		}
		return null;
	}

	private static Integer chooseMarketArea() {
		boolean ok = false;
		int number;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.print("Choose a Market area: [1..4] ");

			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				ok = true;
			} else {
				try {
					number = Integer.parseInt(inText);
					return number - 1;
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}
			}
		}
		return null;
	}

	private static ESceltePrivilegioDelConsiglio chooseCouncilPrivilege(List<ESceltePrivilegioDelConsiglio> hided)
			throws QuitException {
		boolean ok = false;
		int number;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.println("Choose a privilege: ");
			System.out.println(ESceltePrivilegioDelConsiglio.stringify(hided));
			inText = scanner.nextLine();

			if (inText.equals("q")) {
				throw new QuitException();
			} else {
				try {
					number = Integer.parseInt(inText);
					for (ESceltePrivilegioDelConsiglio priv : ESceltePrivilegioDelConsiglio.values()) {
						if (number == priv.ordinal())
							return priv;
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					for (ESceltePrivilegioDelConsiglio priv : ESceltePrivilegioDelConsiglio.values()) {
						if (inText.equalsIgnoreCase(priv.toString())) {
							return priv;
						}
					}
				}
			}
		}
		return null;
	}

	private static Integer chooseTowerArea() {
		Integer floor;
		int number;
		boolean ok = false;
		boolean nestedQuit = false;
		while (!ok) {

			printPointsAndResources(true, false);
			printPawns(true, false);

			printTowerArea(true, true);

			// System.out.println("'q' to quit\n");
			System.out.print("Choose a Tower area: ");
			System.out.print(ETipiCarte.stringify(false) + " ");

			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				ok = true;
			} else {
				try {
					number = Integer.parseInt(inText);
					number--;
					for (ETipiCarte car : ETipiCarte.values()) {
						if (number == car.ordinal()) {
							while (!nestedQuit) {
								try {
									floor = chooseTowerFloor(car);
									// System.out.println();
									// printCardInfo(getClient().getGameBoard().getCartaTorre(floor));
									return floor;
								} catch (QuitException e) {
									nestedQuit = true;
								}
							}
							nestedQuit = false;

						}
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception

					for (ETipiCarte car : ETipiCarte.values()) {
						if (inText.equalsIgnoreCase(car.toString())) {
							while (!nestedQuit) {
								try {
									floor = chooseTowerFloor(car);
									return floor;
								} catch (QuitException e1) {
									nestedQuit = true;
								}
							}
							nestedQuit = false;
						}
					}

				}

			}
		}
		return null;
	}

	private static Integer chooseTowerFloor(ETipiCarte tower) throws QuitException {
		boolean ok = false;
		int number;

		printTowerCards(tower);

		while (!ok) {

			// System.out.println("'q' to quit\n");
			System.out.print("Choose a floor: [1..4] ");

			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				// ok = true;
				throw new QuitException();
			} else {
				try {
					number = Integer.parseInt(inText);
					number = getConvertedTowerFloor(tower, number);
					return number;
				} catch (NumberFormatException e) {
					System.out.println("Insert a valid number");
				}
			}
		}
		return null;
	}

	private static void supportChurch() throws QuitException {
		boolean ok = false;
		while (!ok) {
			System.out.println("'q' to quit\n");
			System.out.println("Support the Church? [y/n]: ");
			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				// ok = true;
				throw new QuitException();
			} else if (inText.equalsIgnoreCase("y")) {
				client.supportChurch(true);
				ok = true;
			} else if (inText.equalsIgnoreCase("n")) {
				client.supportChurch(false);
				ok = true;
			}
		}
	}

	// private static void movePawn(EAzioniGiocatore action, Integer position,
	// ESceltePrivilegioDelConsiglio[] privileges)
	// throws QuitException {
	// EColoriPedine color = choosePawnColor();
	// if (color != null) {
	// client.movePawn(action, color, position, privileges);
	// }
	// }

	private static void movePawn(EAzioniGiocatore action, Integer position, boolean printPawns) throws QuitException {
		if (printPawns)
			printPawns(true, true);
		movePawn(action, position);
	}

	private static void movePawn(EAzioniGiocatore action, Integer position) throws QuitException {
		EColoriPedine color = choosePawnColor();
		if (color != null) {
			client.movePawn(action, color, position);
		}
	}

	private static void movePawn(EAzioniGiocatore action, EColoriPedine color, Integer position,
			ECostiCarte[] choosedCosts) {

	}

	private static EColoriPedine choosePawnColor() throws QuitException {
		int number;
		boolean ok = false;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.print("Choose a Pawn Color: ");
			System.out.print(EColoriPedine.stringify(false) + " ");
			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				throw new QuitException();
			} else {
				try {
					number = Integer.parseInt(inText);
					number--;
					for (EColoriPedine col : EColoriPedine.values()) {
						if (number == col.ordinal()) {
							return col;
						}
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					for (EColoriPedine col : EColoriPedine.values()) {
						if (inText.equalsIgnoreCase(col.toString())) {
							return col;
						}
					}
				}
			}
		}
		return null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Command: [board]
	/////////////////////////////////////////////////////////////////////////////////////////

	public static void printBoard() {
		printDices(true, false);
		printTowerArea(true, false);
		printProductionArea(true, false);
		printHarvestArea(true, false);
		printMarketArea(true, false);
		printCouncilArea(true, true);
	}

	public static void printPlayerTurn(boolean printAndAddNewLine) {
		Client client = getClient();
		try {
			if (client.getNickname().equals(client.getPlayerTurn()))
				System.out.print(ANSI.BACKGROUND_GREEN + "E' il tuo turno");
			else if (client.getPlayerTurn() != null)
				System.out.print(ANSI.BACKGROUND_RED + "E' il turno di: " + client.getPlayerTurn());
			else
				System.out.print(ANSI.BACKGROUND_RED + "In attesa di altri giocatori");

			if (printAndAddNewLine)
				System.out.println(ANSI.RESET);
			else
				System.out.print(ANSI.RESET);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
	}

	private static void printDices(boolean printSep1, boolean printSep2) {
		Client client = getClient();
		SpazioAzione board = client.getGameBoard();

		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			int[] dadi = board.getValoreDadi();
			System.out.print(ANSI.YELLOW);
			System.out.format("%-27s", "Dadi: ");
			System.out.print(ANSI.RESET);
			System.out.format(" " + " %-13s", "Nero = " + dadi[0]);
			System.out.format(" " + " %-18s", "Arancione = " + dadi[1]);
			System.out.format(" " + " %-15s", "Bianco = " + dadi[2]);
			System.out.println();

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printPawns(boolean printSep1, boolean printSep2) {
		try {

			int[] familyValues = getFamilyValues();

			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			System.out.print(ANSI.YELLOW);
			System.out.format("%-30s", "Famigliari: ");
			System.out.print(ANSI.RESET);
			System.out.format(getStringifiedPawn(EColoriPedine.Nera) + "%-17s", " = " + familyValues[0]);
			System.out.format(getStringifiedPawn(EColoriPedine.Arancione) + "%-17s", " = " + familyValues[1]);
			System.out.format(getStringifiedPawn(EColoriPedine.Bianca) + "%-15s", " = " + familyValues[2]);
			System.out.format(getStringifiedPawn(EColoriPedine.Neutrale) + "%-15s", " = " + familyValues[3]);
			System.out.println();

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printTowerArea(boolean printSep1, boolean printSep2) {
		try {
			int pad = 30;

			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			System.out.format(
					ANSI.YELLOW + "     %-" + pad + "s%-" + pad + "s%-" + pad + "s%-" + pad + "s\n" + ANSI.RESET,
					"Territorio: ", "Personaggio: ", "Edificio: ", "Impresa: ");

			for (int i = 3; i >= 0; i--) {
				System.out.print("     ");

				// Territorio
				System.out.print(getFloor(i, pad));

				// Personaggio
				System.out.print(getFloor(i + 4, pad));

				// Edificio
				System.out.print(getFloor(i + 8, pad));

				// Impresa
				System.out.print(getFloor(i + 12, pad));

				System.out.println();
			}

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printProductionArea(boolean printSep1, boolean printSep2) {
		Client client = getClient();
		SpazioAzione board = client.getGameBoard();
		Famigliare zona1;
		ArrayList<Famigliare> zona2;

		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			System.out.println(ANSI.YELLOW + "Produzione: " + ANSI.RESET);

			System.out.print("Zona 1: ");
			zona1 = board.getZonaProduzioneRotonda();
			if (zona1 != null)
				System.out.println(getStringifiedPawnAndPlayer(zona1));
			else
				System.out.println(null + "");

			System.out.print("Zona 2: ");
			zona2 = board.getZonaProduzioneOvale();
			for (int i = 0; i < zona2.size(); i++) {
				System.out.print(getStringifiedPawnAndPlayer(zona2.get(i)) + ", ");
			}
			System.out.println();

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printHarvestArea(boolean printSep1, boolean printSep2) {
		Client client = getClient();
		SpazioAzione board = client.getGameBoard();

		Famigliare zona1;
		ArrayList<Famigliare> zona2;

		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			System.out.println(ANSI.YELLOW + "Raccolto: " + ANSI.RESET);

			System.out.print("Zona 1: ");
			zona1 = board.getZonaRaccoltoRotonda();
			if (zona1 != null)
				System.out.println(getStringifiedPawnAndPlayer(zona1));
			else
				System.out.println(null + "");

			System.out.print("Zona 2: ");
			zona2 = board.getZonaRaccoltoOvale();
			for (int i = 0; i < zona2.size(); i++) {
				System.out.print(getStringifiedPawnAndPlayer(zona2.get(i)) + ", ");
			}
			System.out.println();

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printMarketArea(boolean printSep1, boolean printSep2) {
		Client client = getClient();
		SpazioAzione board = client.getGameBoard();

		Famigliare[] zona;

		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			System.out.println(ANSI.YELLOW + "Mercato: " + ANSI.RESET);

			zona = board.getMercato();

			for (int i = 0; i < zona.length; i++) {
				if (zona[i] != null)
					System.out.print(getStringifiedPawnAndPlayer(zona[i]) + ", ");
				else
					System.out.print(null + ", ");
			}
			System.out.println();

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printCouncilArea(boolean printSep1, boolean printSep2) {
		Client client = getClient();
		SpazioAzione board = client.getGameBoard();

		ArrayList<Famigliare> zona;

		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			System.out.println(ANSI.YELLOW + "Palazzo del Consiglio: " + ANSI.RESET);

			zona = board.getPalazzoDelConsiglio();

			for (int i = 0; i < zona.size(); i++) {
				if (zona.get(i) != null)
					System.out.print(getStringifiedPawnAndPlayer(zona.get(i)) + ", ");
				else
					System.out.print(null + ", ");
			}
			System.out.println();

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Command: [dash]
	/////////////////////////////////////////////////////////////////////////////////////////

	public static void printDashBoard() {
		printPointsAndResources(true, false);
		printDashboardsCards(true, true);

		System.out.print("Also print other dashes? [y/n] ");

		inText = scanner.nextLine();

		if (inText.equalsIgnoreCase("y"))
			printOtherPlayersDashInfo();
	}

	private static void printOtherPlayersDashInfo() {
		try {
			HashMap<String, Plancia> playersDashboards = getClient().getPlayersDashboards();

			for (String key : playersDashboards.keySet()) {
				if (!key.equals(getClient().getNickname())) {
					System.out.println("\nPlancia del Giocatore: \"" + key + "\"");
					printPointsAndResources(key, true, false);
					printDashboardsCards(key, true, true);
				}
			}
		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printDashboardsCards(boolean printSep1, boolean printSep2) {
		printDashboardsCards(client.getNickname(), printSep1, printSep2);
	}

	private static void printDashboardsCards(String nickname, boolean printSep1, boolean printSep2) {
		Client client = getClient();
		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			Plancia dash = client.getPlayersDashboards().get(nickname);
			if (dash != null) {
				ArrayList<Territorio> territori = dash.getTerritori();
				ArrayList<Personaggio> personaggi = dash.getPersonaggi();
				ArrayList<Edificio> edifici = dash.getEdifici();
				ArrayList<Impresa> imprese = dash.getImprese();

				System.out.print(ANSI.YELLOW + "Territori: " + ANSI.RESET);
				for (Territorio ter : territori) {
					System.out.print(ter.getNome() + ", ");
				}
				System.out.println();
				System.out.print(ANSI.YELLOW + "Personaggi: " + ANSI.RESET);
				for (Personaggio per : personaggi) {
					System.out.print(per.getNome() + ", ");
				}
				System.out.println();
				System.out.print(ANSI.YELLOW + "Edifici: " + ANSI.RESET);
				for (Edificio edi : edifici) {
					System.out.print(edi.getNome() + ", ");
				}
				System.out.println();
				System.out.print(ANSI.YELLOW + "Imprese: " + ANSI.RESET);
				for (Impresa imp : imprese) {
					System.out.print(imp.getNome() + ", ");
				}
				System.out.println();
			} else {
				System.out.println(ANSI.YELLOW + "Territori: " + ANSI.RESET);
				System.out.println(ANSI.YELLOW + "Personaggi: " + ANSI.RESET);
				System.out.println(ANSI.YELLOW + "Edifici: " + ANSI.RESET);
				System.out.println(ANSI.YELLOW + "Imprese: " + ANSI.RESET);
			}
			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);

		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	private static void printPointsAndResources(boolean printSep1, boolean printSep2) {
		printPointsAndResources(getClient().getNickname(), printSep1, printSep2);
	}

	private static void printPointsAndResources(String nickname, boolean printSep1, boolean printSep2) {
		Client client = getClient();
		try {
			if (printSep1)
				System.out.println(Costants.ROW_SEPARATOR);

			Punti points = client.getPlayersPoints().get(nickname);
			Risorsa resources = client.getPlayersResources().get(nickname);

			if (points != null && resources != null) {
				System.out.print(ANSI.YELLOW);
				System.out.format("%-18s", "Risorse e Punti: ");
				System.out.print(ANSI.RESET);
				System.out.format("Legno%-7s", " = " + resources.getLegno());
				System.out.format("Monete%-7s", " = " + resources.getMonete());
				System.out.format("Pietre%-7s", " = " + resources.getPietre());
				System.out.format("Servitori%-7s", " = " + resources.getServitori());

				System.out.format("PuntiFede%-7s", " = " + points.getPuntiFede());
				System.out.format("PuntiMilitari%-7s", " = " + points.getPuntiMilitari());
				System.out.format("PuntiVittoria%-7s", " = " + points.getPuntiVittoria());
				System.out.println();
			}

			if (printSep2)
				System.out.println(Costants.ROW_SEPARATOR);

		} catch (NullPointerException e) {
			System.err.println("EXCPETION:" + e.getMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Command: [cards]
	/////////////////////////////////////////////////////////////////////////////////////////

	public static void printCards(boolean printSep1, boolean printSep2) {
		if (printSep1)
			System.out.println(Costants.ROW_SEPARATOR);
		System.out.println(ECarte.stringify(false));

		if (printSep2)
			System.out.println(Costants.ROW_SEPARATOR);
	}

	private static void printTowerCards(ETipiCarte tower) {
		try {
			System.out.println();
			ArrayList<Carta> ecards = new ArrayList<Carta>();
			for (int i = 1; i <= 4; i++) {
				ecards.add((getClient().getGameBoard().getCartaTorre(getConvertedTowerFloor(tower, i))));
			}
			System.out.print(ECarte.stringify(ecards, false, true));
			System.out.println(Costants.ROW_SEPARATOR);
		} catch (NumberFormatException e) {
		}
	}

	private static void printCardInfo(Carta card) {
		String s = "";
		String desc = "";

		for (ECostiCarte cost : card.getCostiCarta()) {
			desc += cost.getDescrizione() + ", ";
		}

		s += String.format("%-25s%-15s%-30s", "[" + card.getNome() + "] ", "Periodo: " + card.getPeriodoCarta(),
				"Costi: " + desc);
		System.out.println(ANSI.YELLOW + "Selected: " + ANSI.RESET + s);

	}

	/////////////////////////////////////////////////////////////////////////////////////////
	// Various
	/////////////////////////////////////////////////////////////////////////////////////////

	private static String getFloor(int i, int pad) {
		String pawn = getStringifiedTowerPawnAndPlayer(i);
		String row = ((i % 4) + 1) + ": ";
		String cell = row + pawn + " " + getStringifiedTowerFloor(i);
		if (pawn != "") {
			cell = cell + ANSI.RESET;
		}

		int diff = pad - cell.length();
		if (pawn != "")
			diff = diff + 19;
		if (diff < 0)
			diff = 0;
		cell = cell + getSpaces(diff);
		return cell;
	}

	private static String getSpaces(int spaces) {
		String s = "";
		for (int i = 0; i < spaces; i++) {
			s += " ";
		}
		return s;
	}

	private static String getStringifiedTowerFloor(int floor) {
		SpazioAzione board = getClient().getGameBoard();

		if (board.getCartaTorre(floor) != null)
			return board.getCartaTorre(floor).getNome();
		else
			return "";
	}

	private static String getStringifiedTowerPawnAndPlayer(int floor) {
		SpazioAzione board = getClient().getGameBoard();
		Famigliare fam = board.getFamigliareTorre(floor);
		if (fam != null) {
			return getStringifiedPawnAndPlayer(fam);
		}
		return "";
	}

	private static String getStringifiedPawn(EColoriPedine color) {
		return color.getANSICode() + "♜" + ANSI.RESET;
	}

	private static String getStringifiedPawnAndPlayer(Famigliare fam) {
		String ANSIPawn = "";
		if (fam != null)
			ANSIPawn = fam.getGiocatore().getColore().getANSIBackground() + fam.getColoreFamigliare().getANSICode();

		return ANSIPawn + "♜" + " " + fam.getGiocatore().getNome() + ANSI.RESET;
	}

	private static int[] getFamilyValues() throws NullPointerException {
		int[] familyValues = new int[4];
		SpazioAzione board = client.getGameBoard();
		try {
			Famigliare[] family = client.getPlayersFamilies().get(client.getNickname());
			for (int i = 0; i < family.length; i++) {
				familyValues[i] = family[i].getValore();
			}
		} catch (NullPointerException e) {
			int[] dadi = board.getValoreDadi();
			for (int i = 0; i < dadi.length; i++) {
				familyValues[i] = dadi[i];
			}
			familyValues[3] = 0;
		}
		return familyValues;
	}

	private static Integer getConvertedTowerFloor(ETipiCarte tower, int number) throws NumberFormatException {
		if (number < 1 || number > 4)
			throw new NumberFormatException();

		switch (tower) {
		case Territorio:
			// [0..3]
			return number - 1;
		case Personaggio:
			// [4..7]
			return (number + 4) - 1;
		case Edificio:
			// [8..11]
			return (number + 8) - 1;
		case Impresa:
			// [12..15]
			return (number + 12) - 1;
		}
		throw new NumberFormatException();
	}

	private static Integer numberChooser(String description) throws QuitException {
		int number;
		boolean ok = false;
		while (!ok) {
			// System.out.println("'q' to quit\n");
			System.out.println(description);
			inText = scanner.nextLine();

			if (inText.equalsIgnoreCase("q")) {
				throw new QuitException();
			} else {
				try {
					number = Integer.parseInt(inText);
					if (number < 0) {
						throw new NumberFormatException();
					} else {
						return number;
					}
				} catch (NumberFormatException e) {
					System.out.println("Insert a valid number");
				}
			}
		}
		return null;
	}

}
