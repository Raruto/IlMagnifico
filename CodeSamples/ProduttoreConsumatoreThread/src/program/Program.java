package program;

public class Program {

	public static void main(String[] args) {
		Risorsa r = new Risorsa();
		Produttore p2 = new Produttore(r);
		Produttore p1 = new Produttore(r);
		Consumatore c1 = new Consumatore(r);
		Consumatore c2 = new Consumatore(r);
		
		p1.start();
		c1.start();
		//p2.start();
		c2.start();
	}
}
