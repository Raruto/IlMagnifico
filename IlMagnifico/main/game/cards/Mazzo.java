package main.game.cards;

import java.util.*;

import main.game.cards.development.CartaEdificio;
import main.game.cards.development.CartaImpresa;
import main.game.cards.development.CartaPersonaggio;
import main.game.cards.development.CartaTerritorio;


/**
 * 
 */
public class Mazzo {

    /**
     * Default constructor
     */
    public Mazzo() {
    }

    /**
     * 
     */
    private ArrayList<CartaTerritorio> carteTerritorio;

    /**
     * 
     */
    private ArrayList<CartaPersonaggio> cartePersonaggio;

    /**
     * 
     */
    private ArrayList<CartaEdificio> carteEdificio;

    /**
     * 
     */
    private ArrayList<CartaImpresa> carteImpresa;
    
    /**
     * *
     * */
    private int periodo;



    /**
     * @return
     */
    public void mescolaMazzo() {
        // Riempie gli ArrayList con le carte dei periodi corrispondenti e mischia gli elementi all'interno degli ArrayList
    	//scorro l'enum in cerca delle carte che mi servono, del tipo e del periodo corrispondente
        return;
    }

    /**
     * @return
     */
    public CartaEdificio getCartaEdificio() {
        //prende la prima carta dalla lista delle carte edificio e la restituisce, eliminandola dalla lista d'origine
    	CartaEdificio cartaTemporanea=new CartaEdificio();
    	cartaTemporanea=this.carteEdificio.get(0);
    	this.carteEdificio.remove(0);
        return cartaTemporanea;
    }

    /**
     * @return
     */
    public CartaTerritorio getCartaTerritorio() {
        // stessa cosa del metodo sopra indicato ma con le carte territorio
    	CartaTerritorio cartaTemporanea=new CartaTerritorio();
    	crtaTemporanea=this.carteTerritorio.get(0);
    	this.carteTerritorio.remove(0);
        return cartaTemporanea;
    }

    /**
     * @return
     */
    public CartaPersonaggio getCartaPersonaggio() {
        //stessa cosa dl metodo sopra indicato ma con le carte personaggio
    	CartaPersonaggio cartaTemporanea=new CartaPersonaggio();
    	crtaTemporanea=this.cartePersonaggio.get(0);
    	this.cartePersonaggio.remove(0);
        return cartaTemporanea;
    }

    /**
     * @return
     */
    public CartaImpresa getCartaImpresa() {
        // TODO implement here
    	CartaImpresa cartaTemporanea=new CartaImpresa();
    	crtaTemporanea=this.carteImpresa.get(0);
    	this.carteImpresa.remove(0);
        return cartaTemporanea;
    }
    
    public void setPeriodo(int periodoNuovo){
    	this.periodo=periodoNuovo;
    }
    
    public void incrementaPeriodo(){
    	this.periodo++;
    }

}