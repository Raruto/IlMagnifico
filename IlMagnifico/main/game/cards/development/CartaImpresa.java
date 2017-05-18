package main.game.cards.development;

import java.util.*;

import main.game.enums.CarteImpresa;

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