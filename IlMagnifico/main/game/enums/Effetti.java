package main.game.enums;

import main.game.players.Giocatore;

/**
 * 
 */
public enum Effetti {
	//elenco dei vari tipi di effetti
AUMENTALEGNAUNO("aumenta_legna_uno",1,0,0,0,0,0,0);
	private String nomeEffetto;
	private int deltaLegna;
	private int deltaPietra;
	private int deltaServitori;
	private int deltaMonete;
	private int deltaPV;
	private int deltaPM;
	private int deltaPF;
	
private Effetti(String nome,int legna,int pietra,int servitori,int monete,int pv,int pm,int pf){
	this.nomeEffetto=nome;
	this.deltaLegna=legna;
	this.deltaPietra=pietra;
	this.deltaServitori=servitori;
	this.deltaMonete=monete;
	this.deltaPV=pv;
	this.deltaPM=pm;
	this.deltaPF=pf;
}

public void attiva(Giocatore giocatore){
	if(this.nomeEffetto.equals("aumenta_legna_uno"))
		aumentaLegnaUno(giocatore);
}

public void aumentaLegnaUno(Giocatore giocatore){
	
}
	
}