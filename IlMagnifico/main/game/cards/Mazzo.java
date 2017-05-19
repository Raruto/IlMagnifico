package main.game.cards;

import java.util.*;

import main.game.cards.development.CartaEdificio;
import main.game.cards.development.CartaImpresa;
import main.game.cards.development.CartaPersonaggio;
import main.game.cards.development.CartaTerritorio;
import main.game.cards.development.CarteEdificio;
import main.game.cards.development.CarteImpresa;
import main.game.cards.development.CartePersonaggio;
import main.game.cards.development.CarteTerritorio;


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
        // Riempie gli ArrayList con le carte dei periodi corrispondenti e 
    	// mischia gli elementi all'interno degli ArrayList
    	// inizializzo delle variabili che mi serviranno per il mescolamento delle carte
    	CartaTerritorio cartaTerritorioTemp1=new CartaTerritorio(null);
    	CartaTerritorio cartaTerritorioTemp2=new CartaTerritorio(null);
    	CartaPersonaggio cartaPersonaggioTemp1=new CartaPersonaggio(null);
    	CartaPersonaggio cartaPersonaggioTemp2=new CartaPersonaggio(null);
    	CartaEdificio cartaEdificioTemp1=new CartaEdificio(null);
    	CartaEdificio cartaEdificioTemp2=new CartaEdificio(null);
    	CartaImpresa cartaImpresaTemp1=new CartaImpresa(null);
    	CartaImpresa cartaImpresaTemp2=new CartaImpresa(null);
    	int index1;
    	int index2;
    	int contatore;
    	Random random=new Random();
    	

    	//scorro l'enum in cerca delle carte che mi servono, del tipo e del periodo corrispondente
    	for(CarteTerritorio c : CarteTerritorio.values()){
    		if(c.getPeriodoCarta()==this.periodo)
    			this.carteTerritorio.add(0, new CartaTerritorio(c));
    	}
    	
    	for(CartePersonaggio c : CartePersonaggio.values()){
    		if(c.getPeriodoCarta()==this.periodo)
    			this.cartePersonaggio.add(0, new CartaPersonaggio(c));
    	}
    	for(CarteEdificio c : CarteEdificio.values()){
    		if(c.getPeriodoCarta()==this.periodo)
    			this.carteEdificio.add(0, new CartaEdificio(c));
    	}
    	for(CarteImpresa c : CarteImpresa.values()){
    		if(c.getPeriodoCarta()==this.periodo)
    			this.carteImpresa.add(0, new CartaImpresa(c));
    	}
    		
    	
    	//mischio i mazzetti dei tipi di carte prendendo sempre due oggetti dell'ArrayList e scambiandoli
    	for(contatore=0;contatore<10;contatore++){
    		index1=random.nextInt(9);
    		index2=random.nextInt(9);
    		cartaPersonaggioTemp1=this.cartePersonaggio.get(index1);
    		cartaPersonaggioTemp2=this.cartePersonaggio.get(index2);
    		this.cartePersonaggio.set(index2, cartaPersonaggioTemp1);
    		this.cartePersonaggio.set(index1, cartaPersonaggioTemp2);
    	}
    	
    	for(contatore=0;contatore<10;contatore++){
    		index1=random.nextInt(9);
    		index2=random.nextInt(9);
    		cartaTerritorioTemp1=this.carteTerritorio.get(index1);
    		cartaTerritorioTemp2=this.carteTerritorio.get(index2);
    		this.carteTerritorio.set(index1, cartaTerritorioTemp2);
    		this.carteTerritorio.set(index2, cartaTerritorioTemp1);
    	}
    	for(contatore=0;contatore<10;contatore++){
    		index1=random.nextInt(9);
    		index2=random.nextInt(9);
    		cartaEdificioTemp1=this.carteEdificio.get(index1);
    		cartaEdificioTemp2=this.carteEdificio.get(index2);
    		this.carteEdificio.set(index1, cartaEdificioTemp2);
    		this.carteEdificio.set(index2, cartaEdificioTemp1);
    	}
    	for(contatore=0;contatore<10;contatore++){
    		index1=random.nextInt(9);
    		index2=random.nextInt(9);
    		cartaImpresaTemp1=this.carteImpresa.get(index1);
    		cartaImpresaTemp2=this.carteImpresa.get(index2);
    		this.carteImpresa.set(index2, cartaImpresaTemp1);
    		this.carteImpresa.set(index1, cartaImpresaTemp2);
    	}
        return;
    }

    /**
     * Esegue il POP della prima carta dalla lista delle Carte Edificio 
     * @return CartaEdificio
     */
    public CartaEdificio getCartaEdificio() {
    	CartaEdificio cartaTemporanea=new CartaEdificio(null);
    	cartaTemporanea=this.carteEdificio.get(0);
    	this.carteEdificio.remove(0);
        return cartaTemporanea;
    }

    /**
     * Esegue il POP della prima carta dalla lista delle Carte Territorio 
     * @return CartaTerritorio
     */
    public CartaTerritorio getCartaTerritorio() {
    	CartaTerritorio cartaTemporanea=new CartaTerritorio(null);
    	cartaTemporanea=this.carteTerritorio.get(0);
    	this.carteTerritorio.remove(0);
        return cartaTemporanea;
    }

    /**
     * Esegue il POP della prima carta dalla lista delle Carte Personaggio 
     * @return CartaPersonaggio
     */
    public CartaPersonaggio getCartaPersonaggio() {
    	CartaPersonaggio cartaTemporanea=new CartaPersonaggio(null);
    	cartaTemporanea=this.cartePersonaggio.get(0);
    	this.cartePersonaggio.remove(0);
        return cartaTemporanea;
    }

    /**
     * Esegue il POP della prima carta dalla lista delle Carte Impresa 
     * @return CartaImpresa
     */
    public CartaImpresa getCartaImpresa() {
    	CartaImpresa cartaTemporanea=new CartaImpresa(null);
    	cartaTemporanea=this.carteImpresa.get(0);
    	this.carteImpresa.remove(0);
        return cartaTemporanea;
    }
    
    public void setPeriodo(int periodoNuovo){
    	this.periodo=periodoNuovo;
    }
    


}