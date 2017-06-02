package main.model;

/**
 * 
 */
public class Punti {

	/**
	 * Default constructor
	 */
	public Punti(Giocatore giocatore) {
		this.giocatoreAppartenenza = giocatore;
	}

	/**
	 * 
	 */
	private int puntiVittoria;

	/**
	 * 
	 */
	private int puntiMilitari;

	/**
	 * 
	 */
	private int puntiFede;

	private Giocatore giocatoreAppartenenza;

	/**
	 * Applica la variazione di punti vittoria. Se il parametro in ingresso e'
	 * negativo, vengono sottratti punti vittoria
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPuntiVittoria(int variazione) {
		this.puntiVittoria = this.puntiVittoria + variazione;
	}

	/**
	 * Applica la variazione di punti millitari. Se il parametro in ingresso e'
	 * negativo vengono sottratti punti militari
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPuntiMilitari(int variazione) {
		if (this.giocatoreAppartenenza.getScomunica(0).attivaOnRiceviPM() && variazione > 0)
			this.puntiMilitari--;
		this.puntiMilitari = this.puntiMilitari + variazione;
	}

	/**
	 * Applica la variazione di punti fede. Se il parametro in ingresso e'
	 * negativo, vengono sottratti punti fede
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPuntiFede(int variazione) {
		this.puntiFede = this.puntiFede + variazione;
	}

	/**
	 * Ritorna la quantita' di punti vittoria posseduti dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPuntiVittoria() {
		return this.puntiVittoria;
	}

	/**
	 * Ritorna la quantita' di punti militari posseduta dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPuntiMilitari() {
		return this.puntiMilitari;
	}

	/**
	 * Ritorna la quantita' di punti fede posseduta dal giocatore
	 * 
	 * @param
	 * @return int
	 **/
	public int getPuntiFede() {
		return this.puntiFede;
	}

	/**
	 * Cambia la quantita' di punti fede del giocatore al valore passato in
	 * ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setPuntiFede(int puntiFede) {
		this.puntiFede = puntiFede;
	}

	public void setPuntiVittoria(int puntiVittoria) {
		this.puntiVittoria = puntiVittoria;
	}

	public void setPuntiMilitari(int puntiMilitari) {
		this.puntiMilitari = puntiMilitari;
	}
}