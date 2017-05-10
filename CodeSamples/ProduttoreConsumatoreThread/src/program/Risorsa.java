package program;

public class Risorsa {
	final int maxQuantità = 40;
	private int quantità;

	public Risorsa() {
		quantità = maxQuantità;
	}

	public synchronized void incrementa() {
		this.quantità++;
	}

	public synchronized void riempi() {
		while (quantità != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.quantità = maxQuantità;
		System.out.println("Risorsa riempita");
		notifyAll();
	}

	public synchronized boolean decrementa() {

		while (quantità <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (quantità > 0) {
			this.quantità--;
			System.out.println("Risorsa presa");
			notifyAll();
			return true;
		} else
			return false;
	}

	public int getQuantità() {
		return this.quantità;
	}

}
