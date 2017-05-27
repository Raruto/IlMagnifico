package main.model.old.game.spaces;

import main.model.old.game.activatables.Azione;
import main.model.old.game.players.Pedina;
import main.model.old.game.players.PuntiGiocatore;
import main.model.old.game.players.RisorseGiocatore;

/**
 * Classe che implementa il concetto di Spazio Azione all'interno del Gioco
 */
public class SpazioAzione implements SpazioPedina {

	/**
	 * Punti Giocatore richiesti per poter piazzare il familiare nello Spazio
	 * Azione
	 */
	private PuntiGiocatore puntiGiocatoreRichiesti;

	/**
	 * Risorse Giocatore richieste per potere piazzare il familiare nello Spazio
	 * Azione
	 */
	private RisorseGiocatore risorseGiocatoreRichieste;

	/**
	 * 
	 */
	private Azione[] azione;

	/**
	 * Riferimento alla Pedina attualmente presente nello Spazio Azione
	 */
	private Pedina pedina;

	/**
	 * Default constructor
	 * 
	 * @param puntiGiocatoreRichiesti
	 * @param risorseGiocatoreRichieste
	 */
	public SpazioAzione(PuntiGiocatore puntiGiocatoreRichiesti, RisorseGiocatore risorseGiocatoreRichieste) {
		this.puntiGiocatoreRichiesti = puntiGiocatoreRichiesti;
		this.risorseGiocatoreRichieste = risorseGiocatoreRichieste;
	}

	/**
	 * @return
	 */
	public Azione visualizzaAzione() {
		// TODO implement here
		return null;
	}

	/**
	 * @param azione
	 */
	public void aggiungiAzione(Azione azione) {
		// TODO implement here
	}

	/**
	 * Riferimento alla Pedina attualmente presente nello Spazio Azione
	 * 
	 * @return Pedina
	 */
	@Override
	public Pedina visualizzaPedina() {
		return this.pedina;
	}

	/**
	 * Inserisce la Pedina nello Spazio Azione
	 * 
	 * @param Pedina
	 */
	@Override
	public void aggiungiPedina(Pedina pedina) {
		this.pedina = pedina;
	}

	/**
	 * Ritorna un valore booleano per determinare se lo Spazio Azione è occupato
	 * 
	 * @return true se occupato
	 */
	@Override
	public boolean spazioOccupato() {
		if (this.pedina == null)
			return false;
		else
			return true;
	}

	/**
	 * Punti Giocatore richiesti per occupare lo Spazio Azione
	 * 
	 * @return {@link PuntiGiocatore}
	 */
	@Override
	public PuntiGiocatore puntiGiocatoreRichiesti() {
		return this.puntiGiocatoreRichiesti;
	}

	/**
	 * Risorse Giocatore richiesti per occupare lo Spazio Azione
	 * 
	 * @return {@link RisorseGiocatore}
	 */
	@Override
	public RisorseGiocatore risorseGiocatoreRichieste() {
		return this.risorseGiocatoreRichieste;
	}

}