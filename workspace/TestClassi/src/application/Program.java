package application;

import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		System.out.println("Inizio Esecuzione...");

		LaMiaPrimaClasse lmpc = new LaMiaPrimaClasse("Descrizione della mia prima classe");

		lmpc.Stampa(5);

		lmpc.Echo();
		
		System.out.println("Termino Esecuzione...");
		System.exit(0);
	}
}

class LaMiaPrimaClasse {
	private String testo;
	private Scanner scanner; 

	public LaMiaPrimaClasse(String s) {
		this.testo = s;
		this.scanner = new Scanner(System.in);
	}

	public void Stampa() {
		System.out.println(this.testo);
	}
    
	public void Stampa(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print( i+1 +") ");
			this.Stampa();
		}
	}
	
	public void Echo(){
		System.out.print("Parlami di te: ");
		System.out.println("Echoed: " + scanner.nextLine());
	}
}
