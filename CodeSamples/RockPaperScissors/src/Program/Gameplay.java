package Program;

import java.util.Scanner;

public class Gameplay extends Thread {

	private Scanner scanner;

	private Handsigns playerSign;
	private Handsigns cpuSign;

	private final String winner1 = "You Win!", winner2 = "You lose!", drawn = "You Drawn!";
	private String roundWinner;
	
	private int playerWinsCounter, cpuWinsCounter;
	private int RoundsToWin;

	public Gameplay(int minGameRounds) {
		scanner = new Scanner(System.in);

		playerSign = null;
		cpuSign = null;

		playerWinsCounter = 0;
		cpuWinsCounter = 0;
		RoundsToWin = minGameRounds;

	}

	public void run() {
		while (true) {
			this.play();
		}
	}

	public void play() {
		playerSign = submitPlayerSign();
		cpuSign = submitCpuSign();

		roundWinner = whoWinsTheRound(playerSign, cpuSign);
		System.out.println(roundWinner + "\n");
		

		updateWinsStandings(roundWinner);
		System.out.println("You: " + playerWinsCounter + " - CPU: " + cpuWinsCounter);

		if (checkForGameWin()) {
			System.out.println(whoWinsTheGame() + " the game..." + "\n");
			
			playerWinsCounter = 0;
			cpuWinsCounter = 0;
		}
	}

	private Handsigns submitPlayerSign() {
		String handsign;

		do {
			System.out.println("Rock, Paper or Scissors?");
			System.out.print("Player: ");
			handsign = scanner.nextLine().toUpperCase();

		} while (!Handsigns.isValidHandsign(handsign));

		return Handsigns.valueOf(handsign);
	}

	private Handsigns submitCpuSign() {
		Handsigns handsign = Handsigns.getRandomSign();
		System.out.println("CPU: " + handsign.capitalizeFirstStringLetter());

		return handsign;
	}

	private String whoWinsTheRound(Handsigns sign1, Handsigns sign2) {
		if (sign1.equals(Handsigns.ROCK)) {
			if (sign2.equals(Handsigns.PAPER))
				return winner2;
			else if (sign2.equals(Handsigns.SCISSORS))
				return winner1;
			else if (sign2.equals(Handsigns.ROCK))
				return drawn;
		} else if (sign1.equals(Handsigns.PAPER)) {
			if (sign2.equals(Handsigns.PAPER))
				return drawn;
			else if (sign2.equals(Handsigns.SCISSORS))
				return winner2;
			else if (sign2.equals(Handsigns.ROCK))
				return winner1;
		} else if (sign1.equals(Handsigns.SCISSORS)) {
			if (sign2.equals(Handsigns.PAPER))
				return winner1;
			else if (sign2.equals(Handsigns.SCISSORS))
				return drawn;
			else if (sign2.equals(Handsigns.ROCK))
				return winner2;
		}
		return "Error";
	}

	private void updateWinsStandings(String roundWinnerResult) {
		if (roundWinnerResult.equals(winner1))
			playerWinsCounter++;
		else if (roundWinnerResult.equals(winner2))
			cpuWinsCounter++;

	}

	private boolean checkForGameWin() {
		return (playerWinsCounter == cpuWinsCounter + RoundsToWin)
				|| (cpuWinsCounter == playerWinsCounter + RoundsToWin);
	}

	private String whoWinsTheGame() {
		if (playerWinsCounter >= cpuWinsCounter + RoundsToWin)
			return winner1;
		else if (cpuWinsCounter >= playerWinsCounter + RoundsToWin)
			return winner2;
		else
			return "Errore";
	}
}
