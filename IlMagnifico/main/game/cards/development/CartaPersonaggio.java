package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public class CartaPersonaggio extends CartaSviluppo {

	private CartePersonaggio carta;
	
	public CartaPersonaggio(CartePersonaggio cartaNuova){
		this.carta=cartaNuova;
	}
	public CartePersonaggio getCarta(){
		return this.carta;
	}
}