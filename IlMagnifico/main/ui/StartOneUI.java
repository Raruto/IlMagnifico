package main.ui;

import java.util.Scanner;

import main.ui.cli.CLI;
import main.ui.gui.GUI;
import main.util.Costants;

public class StartOneUI {
	private static String inText;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Start [C]LI or [G]UI? (Default: [C]): ");
		inText = scanner.nextLine().toUpperCase();

		if (inText.equals("G")) {
			GUI.main(args);
		} else if (inText.equals("C")) {
			CLI.mainClient(Costants.SERVER_ADDRESS, Costants.SOCKET_PORT, Costants.RMI_PORT);
		}
		// Default: Client
		else {
			System.out.println("Starting CLI..");
			CLI.mainClient(Costants.SERVER_ADDRESS, Costants.SOCKET_PORT, Costants.RMI_PORT);
		}
	}
}
