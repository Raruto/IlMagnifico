package main.game.spaces;

import java.util.*;

import main.game.res.PuntiGiocatore;

/**
 * 
 */
public interface Spazio {

    /**
     * @return
     */
    public boolean spazioOccupato();

    /**
     * @return
     */
    public PuntiGiocatore puntiRichiesti();

}