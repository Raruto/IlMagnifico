package contocorrente;

public class Cons extends Thread {
	Conto ilSuoConto;
	int maxSpesa;
	int tempo;

	Cons(int m, int t, Conto c) {
		ilSuoConto = c;
		maxSpesa = m;
		tempo = t;
	}

	public void run() {
		while (true) {
			if (!ilSuoConto.ritira((int) (Math.random() * maxSpesa)))
				System.out.println("Non riesco a tirar fuori i soldi!");
			else
				System.out.println("Riesco a tirar fuori i soldi!");
			try {
				sleep(tempo);
			} catch (InterruptedException ie) {
				System.out.println("ERRORE!");
			}
		}
	}
}