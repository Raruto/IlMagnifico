package contocorrente;

public class Conto {
	private int saldo;

	Conto() {
		saldo = 10;
	}

	public synchronized void deposita(int x) {
		saldo += x;
	}

	public synchronized boolean ritira(int x) {
		if (saldo < x)
			return false;
		else {
			saldo -= x;
			return true;
		}
	}
}