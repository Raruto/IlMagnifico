package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public class CartaImpresa extends CartaSviluppo {

	private CarteImpresa carta;

	public CartaImpresa(CarteImpresa cartaNuova){
		this.carta=cartaNuova;
	}
	public CarteImpresa getCarta(){
		return this.carta;
	}
}