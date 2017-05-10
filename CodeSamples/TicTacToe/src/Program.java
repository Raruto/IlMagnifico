import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// Per maggiori informazioni:
		// http://www.coderslexicon.com/a-beginner-tic-tac-toe-class-for-java/

		// Create game and initialize it.
		// First player will be 'x'
		TicTacToe game = new TicTacToe();

		Scanner s = new Scanner(System.in);
		int riga, colonna;
		boolean ok;

		while (!game.checkForWin()) {
			
			game.printBoard();
			
			do {
				System.out.print("("+ game.getCurrentPlayerMark() +") " + "row: ");
				riga = s.nextInt();
				System.out.print("("+ game.getCurrentPlayerMark() +") " + "column: ");
				colonna = s.nextInt();

				// These values are based on a zero index array, so you may need
				// to simply take in a row 1 and subtract 1 from it if you want
				// that.
				ok = game.placeMark(riga-1, colonna-1);
				
				if(!ok){
					game.printBoard();
					System.out.println("Illegal move, retry.");					
				}
			} while (!ok);

			// Lets print the board
			game.printBoard();

			// Did we have a winner?
			if (game.checkForWin()) {
				System.out.println("We have a winner! Congrats!");
				System.exit(0);
			} else if (game.isBoardFull()) {
				System.out.println("Appears we have a draw!");
				System.exit(0);
			}

			// No winner or draw, switch players.
			game.changePlayer();

			// Repeat steps again for placing mark and checking game status...
		}

		s.close();
	}
}
