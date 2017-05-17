package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public enum CartaPersonaggio extends CartaSviluppo {
    CAVALIERE("cavaliere",1,2);
	private int costoMonetePersonaggio;
	
	private CartaPersonaggio(String nome,int periodo,int costoMonete){
	this.nomeCarta=nome;
	this.periodoCarta=periodo;
	this.costoMonetePersonaggio=costoMonete;
	}
	
	public void smistaEffettiImmediati(String nomeCarta){
		//mi baso sul nome della carta per riconoscere quale effetto applicare
		if(nomeCarta.equals("cavaliere"))
			privilegioDelConsiglio();
		
	}
	
	public void smistaEffettiPermanenti(String nomeCarta){
		if(nomeCarta.equals("cavaliere"))
		  aumentaImpresaDiDue();
	}
	
	public int getCostoMonete(){
		return this.costoMonetePersonaggio;
	}
	
}