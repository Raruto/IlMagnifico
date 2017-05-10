package contocorrente;

public class Marito extends Thread {
	private Conto ilMioConto;

	Marito(Conto c) {
		ilMioConto = c;
	}

	public void run() {
		while (true) {
			ilMioConto.deposita((int) (Math.random() * 1000));
			try {
				sleep(6000);
			} catch (InterruptedException ie) {
				System.out.println("ERRORE!");
			}
		}
	}
}