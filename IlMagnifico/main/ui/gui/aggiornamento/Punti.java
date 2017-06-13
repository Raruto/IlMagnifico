package main.ui.gui.aggiornamento;

public class Punti {
	
	private int puntiVittoria;
	private int puntiMilitari;
	private int puntiFede;
	
	public Punti(int puntiVittoria, int puntiMilitari, int puntiFede){
		this.puntiVittoria = puntiVittoria;
		this.puntiMilitari = puntiMilitari;
		this.puntiFede = puntiFede;
	}
	
	public int getPuntiVittoria() {
		return puntiVittoria;
	}
	
	public int getPuntiMilitari() {
		return puntiMilitari;
	}
	
	public int getPuntiFede() {
		return puntiFede;
	}
	
}
