package main.game.cards.development;

import java.util.*;

import main.game.activatables.Effetto;
import main.game.players.RiservaGiocatore;

/**
 * Classe che definisce il concetto di CartaSviluppo
 */
public abstract class CartaSviluppo {

	/**
	 * Nome della Carta Sviluppo
	 */
	protected String nome;

	/**
	 * Periodo di gioco in cui viene utilizzata la Carta Sviluppo
	 */
	protected int periodo;

	/**
	 * Costo che il Giocatore deve pagare per poter acquisire la Carta Sviluppo
	 */
	protected RiservaGiocatore costo;

	/**
	 * ArrayList contenente tutti gli Effetti Immediati associati alla Carta.
	 * Gli Effetti Immediati (non necessariamente tutti) vengono Attivati
	 * nell'istante che il giocatore prende la Carta dalla Torre corrispondente.
	 */
	protected ArrayList<Effetto> effettiImmediati;

	/**
	 * ArrayList contenente tutti gli Effetti Permanenti associati alla Carta
	 * Gli Effetti Permanenti (sotto determinate condizioni) vengono Attivati
	 * dopo che il giocatore a inserito la Carta nella Plancia Giocatore.
	 */
	protected ArrayList<Effetto> effettiPermanenti;

	/**
	 * Abstract Class Constructor
	 * 
	 * @param nome
	 * @param periodo
	 * @param costo
	 */
	public CartaSviluppo(String nome, int periodo, RiservaGiocatore costo) {
		this.nome = nome;
		this.periodo = periodo;
		this.costo = costo;
	}

	/**
	 * Ritorna un ArrayList con contenente tutti gli Effetti Immediati associati
	 * alla Carta Sviluppo
	 * 
	 * @return {@link Effetto}
	 */
	public ArrayList<Effetto> getEffettiImmediati() {
		return this.effettiImmediati;
	}

	/**
	 * Ritorna un ArrayList con contenente tutti gli Effetti Permanenti
	 * associati alla Carta Sviluppo
	 * 
	 * @return {@link Effetto}
	 */
	public ArrayList<Effetto> getEffettiPermanenti() {
		return this.effettiPermanenti;
	}

}