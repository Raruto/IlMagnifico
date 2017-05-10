package program;

public class Produttore extends Thread {

	private Risorsa risorsa;

	public Produttore(Risorsa r) {
		this.risorsa = r;
	}

	public void run() {
		while (true) {
			risorsa.riempi();
			
			/*
			risorsa.incrementa();
			
			System.out.println("(+1) : "+ risorsa.getQuantità());
			
			try {
				sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
		}
	}
}
