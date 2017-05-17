package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public enum CartaImpresa extends CartaSviluppo {
//qui vanno tutti gli elementi della enum
	ESEMPIO("esempio",0,0,0,0,0,0);
	private int costoLegno;
	private int costoPietra;
	private int costoServitori;
	private int costoMonete;
	private int costoPuntiMilitari;
    /**
     * Default constructor
     */
    private CartaImpresa(String nome,int periodo,int legno,int pietra, int servitori,int monete,int PM) {
    	this.nomeCarta=nome;
    	this.periodoCarta=periodo;
    	this.costoLegno=legno;
    	this.costoPietra=legno;
    	this.costoServitori=servitori;
    	this.costoMonete=monete;
    	this.costoPuntiMilitari=PM;
    }
    
	public smistaEffettiImmediati(String nomeCarta){
		//mi baso sul nome della carta per riconoscerne l'effetto
		if(nomeCarta.equals("esempio"))
			esempio();
	}
	
	public smistaEffettiPermanenti(String nomeCarta){
		if(nomeCarta.equals("esempio"))
			esempio();
	}
	public int getCostoLegno(){
		return this.costoLegno;
	}
	public int getCostoPietra(){
		return this.costoPietra;
	}
	public int getCostoSevitori(){
		return this.costoServitori;
	}
	public int getcostoMonete(){
		return this.costoMonete;
	}
	public int getCostoPuntiMilitari(){
		return this.costoPuntiMilitari;
	}

}