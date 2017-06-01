package main.model;

import java.util.*;

import main.util.game.EAzioniGiocatore;

/**
 * 
 */
public class Scomunica {

	/**
	 * Default constructor
	 */
	public Scomunica() {
		this.utilEffetto = new UtilEffetto();
	}

	/**
	 * 
	 */
	private String nome;

	private UtilEffetto utilEffetto;

	/**
	 * 
	 */
	private ArrayList<Object[]> effetto;

	/**
	 * @param giocatore
	 * @return
	 */
	public void eseguiEffettoScomunica(Giocatore giocatore, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < effetto.size(); i++) {
			effetto.get(i)[1] = giocatore;
			effetto.get(i)[3] = famigliare;
			effetto.get(i)[4] = carta;
			attivaEffettoSingolo(effetto.get(i));
		}
	}

	public void attivaEffettoSingolo(Object[] parametri) {
		if ((int) parametri[0] == 0)
			utilEffetto.aggiungiRisorse(parametri);
		else if ((int) parametri[0] == 3)
			utilEffetto.eseguiPrivilegioDelConsiglio(parametri);
		// TODO:finire di elencare i metodi possibili
	}

	public void attivaOnAzione(Giocatore giocatore, EAzioniGiocatore azione, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < this.effetto.size(); i++) {
			this.effetto.get(i)[1] = giocatore;
			this.effetto.get(i)[3] = famigliare;
			this.effetto.get(i)[4] = carta;
			if ((EAzioniGiocatore) (this.effetto.get(i)[2]) == azione)
				attivaEffettoSingolo(this.effetto.get(i));
		}
	}

}