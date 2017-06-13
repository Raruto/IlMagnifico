package main.ui.gui.aggiornamento;

public class Famigliare {
	
	private int valore;
	private int numero;
	private String colore;
	private String giocatoreAppartenenza;
	
	public Famigliare(int valore, int numero, String colore, String giocatoreAppartenenza){
		this.valore = valore;
		this.numero = numero;
		this.colore = colore;
		this.giocatoreAppartenenza = giocatoreAppartenenza;
	}

	public int getValore() {
		return valore;
	}

	public int getNumero() {
		return numero;
	}

	public String getColore() {
		return colore;
	}

	public String getGiocatoreAppartenenza() {
		return giocatoreAppartenenza;
	}
	
}
