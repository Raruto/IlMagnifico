package main.LorenzoIlMagnifico.old.game.players.dashboard;

import main.LorenzoIlMagnifico.old.game.cards.development.CartaSviluppo;
import main.LorenzoIlMagnifico.old.game.spaces.Spazio;

/**
 * Interfaccia contenitore per una singola Carta Sviluppo, estende Spazio
 */
public interface SpazioCartaSviluppo extends Spazio {
	/**
	 * Riferimento alla Carta Sviluppo attualmente presente nello Spazio Carta
	 * Sviluppo
	 * 
	 * @return {@link CartaSviluppo}
	 */
	public CartaSviluppo visualizzaCarta();

	/**
	 * Inserisce la Carta Sviluppo nello Spazio Carta Sviluppo
	 * 
	 * @param CartaSviluppo
	 */
	public void aggiungiCarta(CartaSviluppo carta);

}