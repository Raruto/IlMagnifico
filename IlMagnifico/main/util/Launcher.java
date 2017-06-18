package main.util;

import java.util.Scanner;

import main.network.StartOneServer;
import main.ui.StartOneUI;

public class Launcher {
	private static String inText;
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// NEWEST
		System.out.print("Start as [C]lient or [S]erver? (Default: [C]): ");
		inText = scanner.nextLine().toUpperCase();

		if (inText.equals("S")) {
			StartOneServer.main(args);
		} else if (inText.equals("C")) {
			StartOneUI.main(args);
		}
		// Default: Client
		else {
			System.out.println("Starting as Client..");
			StartOneUI.main(args);
		}

		// NEW
		// CLI.main(args);

		// OLD (still working..)
		// StartOneServer.main(args);
		// StartOneClient.main(args);
	}
}