package main.game.board.tower;

import main.game.cards.development.CartaSviluppo;
import main.game.spaces.Spazio;

public class Torre {
	private SpazioTorre[] piani;

	public void setCarta(CartaSviluppo carta) {

	}

	public void setCartaPiano(CartaSviluppo carta, int piano) {

	}

	public CartaSviluppo prendiCarta(int piano) {
		return this.piani[piano].prendiCarta();
	}

}
