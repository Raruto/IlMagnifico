package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public enum CartaTerritorio extends CartaSviluppo {
	//qui vanno tutti gli elementi della enum
	ESEMPIO("esempio",0);


    private CartaTerritorio(String nome,int periodo) {
    	this.nomeCarta=nome;
    	this.periodoCarta=periodo;
    }
    
    public void smistaEffettiImmediati(String nomeCarta){
    	//mi baso sul nome della carta per riconoscere l'effetto
    	if(nomeCarta.equals("esempio"))
    		esempioImmediato();
    }
    
    public void smistaEffettiPermanenti(String nomeCarta){
    	if(nomeCarta.equals("esempio"))
    		esempioPermanente();
    	
    }

}