package main.game.spaces;

import main.game.activatables.Azione;
import main.game.players.Pedina;
import main.game.players.PuntiGiocatore;
import main.game.players.RisorseGiocatore;

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
		// restituisce la pedina
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
		// guarda se c'e' una pedina nello spazio dell'azione. Restituisce vero
		// se lo spazio e' occupato, falso altrimenti
		if (this.pedina == null)
			return true;
		else
			return false;
	}

	/**
	 * Punti Giocatore richiesti per occupare lo Spazio Azione
	 * 
	 * @return PuntiGiocatore
	 */
	@Override
	public PuntiGiocatore puntiGiocatoreRichiesti() {
		return null;
	}

	/**
	 * Risorse Giocatore richiesti per occupare lo Spazio Azione
	 * 
	 * @return RisorseGiocatore
	 */
	@Override
	public RisorseGiocatore risorseGiocatoreRichieste() {
		// TODO Auto-generated method stub
		return null;
	}

}