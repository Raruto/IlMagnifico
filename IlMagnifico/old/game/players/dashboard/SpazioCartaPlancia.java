package old.game.players.dashboard;

import old.game.cards.development.CartaSviluppo;
import old.game.players.PuntiGiocatore;
import old.game.players.RisorseGiocatore;

/**
 * Classe contenitore per una singola Carta Sviluppo all'interno della Plancia
 * Giocatore, implementa SpazioCartaSviluppo
 */
public class SpazioCartaPlancia implements SpazioCartaSviluppo {

	/**
	 * Default constructor
	 */
	public SpazioCartaPlancia() {
	}

	/**
	 * Riferimento alla Carta Sviluppo attualmente presente nello Spazio Carta
	 * Plancia
	 */
	protected CartaSviluppo carta;

	/**
	 * Punti Giocatore richiesti per poter aggiungere una carta nello Spazio
	 * Carta Plancia
	 */
	private PuntiGiocatore puntiGiocatoreRichiesti;

	/**
	 * Risorse Giocatore richieste per poter aggiungere una carta nello Spazio
	 * Carta Plancia
	 */
	private RisorseGiocatore risorseGiocatoreRichieste;

	/**
	 * Riferimento alla Carta Sviluppo attualmente presente nello Spazio Carta
	 * Plancia
	 * 
	 * @return {@link CartaSviluppo}
	 */
	public CartaSviluppo visualizzaCarta() {
		return this.carta;
	}

	/**
	 * Inserisce la Carta Sviluppo nello Spazio Carta Plancia
	 * 
	 * @param carta
	 * 
	 */
	public void aggiungiCarta(CartaSviluppo carta) {
		this.carta = carta;
	}

	/**
	 * Ritorna un valore booleano per determinare se lo Spazio Carta Sviluppo �
	 * occupato
	 * 
	 * @return true se occupato
	 */
	@Override
	public boolean spazioOccupato() {
		if (this.carta == null)
			return false;
		else
			return true;
	}

	/**
	 * Punti Giocatore richiesti per occupare lo Spazio Carta Sviluppo
	 * 
	 * @return {@link PuntiGiocatore}
	 */
	@Override
	public PuntiGiocatore puntiGiocatoreRichiesti() {
		return this.puntiGiocatoreRichiesti;
	}

	/**
	 * Risorse Giocatore richiesti per occupare lo Spazio Carta Sviluppo
	 * 
	 * @return {@link RisorseGiocatore}
	 */
	@Override
	public RisorseGiocatore risorseGiocatoreRichieste() {
		return this.risorseGiocatoreRichieste;
	}

}