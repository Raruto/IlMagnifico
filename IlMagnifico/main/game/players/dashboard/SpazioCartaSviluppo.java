package main.game.players.dashboard;

import main.game.cards.development.CartaSviluppo;

/**
 * Classe contenitore per una singola Carta Sviluppo all'interno della Plancia
 * Giocatore
 */
public interface SpazioCartaSviluppo {
	public CartaSviluppo visualizzaCarta();

	/**
	 * @param carta
	 * @return
	 */
	public void aggiungiCarta(CartaSviluppo carta);

}