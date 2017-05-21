package main.game.board.tower;

import main.game.cards.development.CartaSviluppo;

public class Torre {
	private SpazioTorre[] piani;

	public void setCarta(CartaSviluppo carta) {

	}

	public void setCartaPiano(CartaSviluppo carta, int piano) {

	}

	public CartaSviluppo prendiCarta(int piano) {
		CartaSviluppo carta = popCarta(this.piani[piano]);

		pushCarta(piani[piano]);

		return carta;
	}

	private CartaSviluppo popCarta(SpazioTorre spazio) {
		CartaSviluppo carta = spazio.visualizzaCarta();
		spazio.aggiungiCarta(null);
		return carta;

	}

	private void pushCarta(SpazioTorre spazio) {
		if (spazio.visualizzaCarta() != null) {
			// TODO: implementare (giocatore prende una carta dalla torre,
			// bisogna sostituire la carta dal piano solo se non c'è più nessuna
			// carta sul piano...)
		}
	}

}
