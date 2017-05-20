package main.game.players.dashboard;

import main.game.cards.development.CartaSviluppo;

/**
 * Interfaccia contenitore per una singola Carta Sviluppo
 */
public interface SpazioCartaSviluppo {
	public CartaSviluppo visualizzaCarta();

	/**
	 * @param carta
	 */
	public void aggiungiCarta(CartaSviluppo carta);

}