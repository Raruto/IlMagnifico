package contocorrente;

public class Famiglia {

	/*
	 * Nella famiglia Giambietti non ci sono grossi problemi economici. Infatti
	 * il padre, Enea, di professione artista free-lance, ogni tanto riesce a
	 * vendere un’opera e rimpinguare cosi` il conto. Gli altri membri della
	 * famiglia, cioe` la moglie Luisa ed il figlio Taddeo si limitano a
	 * spendere, usando il proprio bancomat, tutto quello che trovano sul conto.
	 * A proposito, il conto non puo` andare in rosso. Si implementino in Java 3
	 * processi che simulano la famiglia Giambietti.
	 */
	public static void main(String Args[]) {
		Conto c = new Conto();
		Marito p = new Marito(c);
		Cons m = new Cons(1000, 4000, c);
		Cons f = new Cons(300, 3000, c);
		p.start();
		m.start();
		f.start();
	}
}