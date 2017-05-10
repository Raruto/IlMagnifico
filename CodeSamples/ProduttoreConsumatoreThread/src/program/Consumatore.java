package program;

public class Consumatore extends Thread {

	private Risorsa risorsa;

	public Consumatore(Risorsa r) {
		this.risorsa = r;
	}

	public void run() {
		while (true) {
			if (risorsa.decrementa()) {
				System.out.println("(-1) : "+ risorsa.getQuantità());
			} else {
				System.out.println("Risorsa esaurita");
			}
			/*
			try {
				sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/

		}
	}
}
