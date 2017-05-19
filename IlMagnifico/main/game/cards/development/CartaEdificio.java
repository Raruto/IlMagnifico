package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public class CartaEdificio extends CartaSviluppo {
private CarteEdificio carta;

public CartaEdificio(CarteEdificio cartaNuova){
	this.carta=cartaNuova;
}
public CarteEdificio getCarta(){
	return this.carta;
}
}