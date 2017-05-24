
import java.util.*;

/**
 * 
 */
public class Risorsa {

	/**
	 * Default constructor
	 */
	public Risorsa() {
	}

	/**
	 * 
	 */
	private int monete;

	/**
	 * 
	 */
	private int legno;

	/**
	 * 
	 */
	private int pietre;

	/**
	 * 
	 */
	private int servitori;

	/**
	 * Applica la variazione delle monete. Se il parametro in ingresso è
	 * negativo, vengono sottratte monete
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaMonete(int variazione) {
		this.monete = this.monete + variazione;
	}

	/**
	 * Applica la variazione delle unità di legno.Se il parametro in ingresso è
	 * negativo, vengono sottratte unità
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaLegno(int variazione) {
		this.legno = this.legno + variazione;
	}

	/**
	 * Applica la variazione di unità di pietre. Se il parametro in ingresso è
	 * negativo, vengono sottratte unità
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaPietre(int variazione) {
		this.pietre = this.pietre + variazione;
	}

	/**
	 * Applica la variazione di unità di servitori. Se il parametro in ingresso
	 * è negativo, vengono sottratte unità
	 * 
	 * @param variazione
	 * @return
	 */
	public void cambiaServitori(int variazione) {
		this.servitori = this.servitori + variazione;
	}

	/**
	 * Ritorna la quantità di monete possedute dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getMonete() {
		return this.monete;
	}

	/**
	 * Ritorna la quantità di legno posseduto dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getLegno() {
		return this.legno;
	}

	/**
	 * Ritorna la quantità di pietre possedute dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getPietre() {
		return this.pietre;
	}

	/**
	 * Ritorna la quantità di servitori posseduti dal giocatore
	 * 
	 * @param
	 * @return int
	 */
	public int getServitori() {
		return this.servitori;
	}
}