package main.game.cards.development;

import java.util.*;

import main.game.enums.CarteTerritorio;

/**
 * 
 */
public class CartaTerritorio extends CartaSviluppo {
	
  private CarteTerritorio carta;
   

public CarteTerritorio getCarta(){
	return this.carta;
}
public CartaTerritorio(CarteTerritorio c){
	this.carta=c;
}
}