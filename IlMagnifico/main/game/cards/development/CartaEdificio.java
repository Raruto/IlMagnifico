package main.game.cards.development;

import java.util.*;

import main.game.enums.CarteEdificio;

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