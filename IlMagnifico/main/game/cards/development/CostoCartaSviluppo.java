package main.game.cards.development;

import java.util.*;

import main.game.res.PuntiGiocatore;
import main.game.res.RisorseGiocatore;

/**
 * 
 */
public class CostoCartaSviluppo {


    /**
     * Default constructor
     */
    public CostoCartaSviluppo(int legna,int pietra,int servitori,int monete,int PM,int PV,int PF) {
    	this.risorse=new RisorseGiocatore(legna,monete,pietra,servitori);
    	this.punti=new PuntiGiocatore(PM,PV,PF);
    }

    /**
     * 
     */
    private RisorseGiocatore risorse;

    /**
     * 
     */
    private PuntiGiocatore punti;

}