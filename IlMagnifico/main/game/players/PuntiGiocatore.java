package main.game.players;

/**
 * Classe per la gestione dei Punti Giocatore
 */
public class PuntiGiocatore {

	/**
	 * "Tracciato" Punti Vittoria associati al Giocatore
	 */
	private int puntiVittoria;

	/**
	 * "Tracciato" Punti Militari associati al Giocatore
	 */
	private int puntiMilitari;

	/**
	 * "Tracciato" Punti Fede associati al Giocatore
	 */
	private int puntiFede;

	/**
	 * Default constructor
	 * 
	 * @param puntiVittoria
	 * @param puntiMilitari
	 * @param puntiFede
	 */
	public PuntiGiocatore(int puntiFede, int puntiMilitari, int puntiVittoria) {
		this.puntiFede = puntiFede;
		this.puntiVittoria = puntiVittoria;
		this.puntiMilitari = puntiMilitari;
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