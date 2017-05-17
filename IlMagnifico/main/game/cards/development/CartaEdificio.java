package main.game.cards.development;

import java.util.*;

/**
 * 
 */
public enum CartaEdificio extends CartaSviluppo {
//qui vanno tutti gli elementi della enum
	ESEMPIO("esempio",0,0,0,0,0);
	private int costoLegno;
	private int costoPietra;
	private int costoServitori;
	private int costoMonete;

    private CartaEdificio(String nome,int periodo,int legno,int pietra,int servitori,int monete) {
		this.nomeCarta=nome;
		this.periodoCarta=periodo;
		this.costoLegno=legno;
		this.costoPietra=pietra;
		this.costoServitori=servitori;
		this.costoMonete=monete;

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
	public int getCostoServitori(){
		return this.costoServitori;
	}
	public int getCostoMonete(){
		return this.costoMonete;
	}


}