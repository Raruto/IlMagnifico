package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public class CartaTerritorio extends CartaSviluppo {

	private CarteTerritorio carta;

	public CarteTerritorio getCarta() {
		return this.carta;
	}

	public CartaTerritorio(CarteTerritorio c) {
		this.carta = c;
		this.nome = c.getNomeCarta();
		this.periodo = c.getPeriodoCarta();
		this.costo = new CostoCartaSviluppo(c.getLegna(), c.getPietra(), c.getServitori(), c.getMonete(), c.getPM(),
				c.getPV(), c.getPF());
	}

}
