package main.game.spaces.dashboard;

import java.util.*;

import main.game.cards.development.CartaSviluppo;

/**
 * 
 */
public interface SpazioCartaSviluppo {
    /**
     * @return
     */
    public CartaSviluppo visualizzaCarta() ;

    /**
     * @param carta 
     * @return
     */
    public void aggiungiCarta(CartaSviluppo carta) ;

}