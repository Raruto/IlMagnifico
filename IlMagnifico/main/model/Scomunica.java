package main.model;

import java.util.*;

import main.model.enums.EAzioniGiocatore;

/**
 * 
 */
public class Scomunica {

	/**
	 * Default constructor
	 */
	public Scomunica() {
		this.utilEffetto = new UtilEffetto();
		this.nome = new String();
		this.effetto = new ArrayList<Object[]>();
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

	/**
	 * Metodo che controlla se l'effetto della scomunica influisce sull'azione
	 * Mercato
	 * 
	 * @param
	 * @return
	 */
	public boolean attivaOnMercato() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGiocatore) (effetto.get(i)[2]) == EAzioniGiocatore.Mercato)
				return true;
		}
		return false;
	}

	public boolean attivaOnInizioTurno() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGiocatore) (effetto.get(i)[2]) == EAzioniGiocatore.InizioTurno)
				return true;
		}
		return false;
	}

	public boolean attivaOnPersonaggiFinePartita() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGiocatore) (effetto.get(i)[2]) == EAzioniGiocatore.PersonaggiFinePartita)
				return true;
		}
		return false;
	}

	public boolean attivaOnTerritoriFinePartita() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGiocatore) (effetto.get(i)[2]) == EAzioniGiocatore.TerritoriFinePartita)
				return true;
		}
		return false;
	}

	public boolean attivaOnImpreseFinePartita() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGiocatore) (effetto.get(i)[2]) == EAzioniGiocatore.ImpreseFinePartita)
				return true;
		}
		return false;
	}
}