package main.game;

import java.util.*;

import main.game.board.Tabellone;
import main.game.board.vatican.Vaticano;
import main.game.cards.Mazzo;
import main.game.handlers.GestorePartita;
import main.game.players.Giocatore;
import main.game.res.Dado;

/**
 * 
 */
public class Partita {

	/**
	 * Default constructor
	 */
	public Partita() {
	}

	private ArrayList<Giocatore> giocatori;
	private Tabellone tabellone;
	private Mazzo mazzo;
	private Dado dado;
	private GestorePartita gestorePartita;
	private Vaticano vaticano;
	private int periodo;
	private ArrayList<Giocatore> ordineTurno;
	private int turno;

	/**
	 * @return
	 */
	public void run() {
		// TODO implement here
		return;
	}

	/**
	 * @return
	 */
	public void preparaPartita() {
		
		//ipotizzo che siano già passati tutti i dati per la realizzazione della lista di giocatori
		this.periodo=1;
		this.turno=1;
		this.tabellone=new Tabellone();
		this.mazzo=new Mazzo();
		this.dado=new Dado();
		this.giocatori=new ArrayList<Giocatore>();
		this.ordineTurno=new ArrayList<Giocatore>();
		return;
	}
	
	public void Periodo(){
		//si devono svolgere tutte le azioni svolte all'interno di un periodo: mescolo il mazzo secondo le carte che mi servono nel periodo corrispondente
		this.periodo++;
		this.mazzo.setPeriodo(this.periodo);
		this.mazzo.mescolaMazzo();
		giroDiTurni();
		giroDiTurni();
		rapportoVaticano();
	}
	
	public void giroDiTurni(){
		//devo far fare il turno ad ogni giocatore secondo l'ordine di turno e alla fine decidere l'ordine del turno successivo. Devo sempre prima prendere le carte dal mazzo e posizionarle nel tabellone e alla fine del giro di turni togliere dal tbellone quelle rimanenti
	int contatore;
	int contatore2;
	ArrayList<Giocatore> ordineTurniTemporaneo=new ArrayList<Giocatore>();
	//posiziono le carte sul tabellone
	for(contatore=0;contatore<5;contatore++){
		this.tabellone.torri.torreImpresa.setCartaPiano(this.mazzo.getCartaImpresa, contatore);
		this.tabellone.torri.torreTerritorio.setCartaPiano(this.mazzo.getCartaTerritorio, contatore);
		this.tabellone.torri.torrePersonaggio.setCartaPiano(this.mazzo.getCartaPersonaggio, contatore);
		this.tabellone.torri.torreEdificio.setCartaPiano(this.mazzo.getCartaEdificio, contatore);
	}
	//faccio lanciare i dadi
	this.dado.lancia();
	//ad ogni giocatore metto i valori del dado alle corrispondenti pedine
	for(contatore=0;contatore<=this.giocatori.size();contatore++){
		this.ordineTurno.get(contatore).setValoriPedine(this.dado.getDadoBianco,this.getDadoArancione,this.getDadoNero);
		
	}
	//faccio fare i turni ai giocatori
	for(contatore=0;contatore<=this.giocatori.size();contatore++){
		turnoGiocatore(this.ordineTurno.get(contatore));
		
	}

	//calcolo l'ordine dei turni per il giro successivo
	ordineTurniTemporaneo=this.tabellone.palazzoConsiglio.getOrdineTurnoSuccessivo();
    for(contatore=0;contatore<=this.tabellone.)
		
	}

	/**
	 * @return
	 */
	public void finePartita() {
		// TODO implement here
		return;
	}

}