package main.model;

import java.io.Serializable;
import java.util.*;

import main.model.enums.EAzioniGioco;

/**
 * 
 */
public class Scomunica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4068077334736726930L;

	/**
	 * 
	 */
	private String nome;

	private UtilEffetto utilEffetto;

	/**
	 * 
	 */
	transient private ArrayList<Object[]> effetto;

	private int periodo;

	/**
	 * Default constructor
	 */
	public Scomunica() {
		this.utilEffetto = new UtilEffetto();
		this.nome = new String();
		this.effetto = new ArrayList<Object[]>();
	}

	public Scomunica(String nome, int periodo, ArrayList<Object[]> effetto) {
		this.nome = nome;
		this.periodo = periodo;
		this.effetto = effetto;
	}

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
		try {
			if ((int) parametri[0] == 18)
				utilEffetto.decrementaDiQuattroAzione(parametri);
			else if ((int) parametri[0] == 19)
				utilEffetto.decrementaDiTreAzione(parametri);
			else if ((int) parametri[0] == 20)
				utilEffetto.decrementaDiUnoAzione(parametri);
			else if ((int) parametri[0] == 21)
				utilEffetto.perdiPvxcinquePV(parametri);
			else if ((int) parametri[0] == 22)
				utilEffetto.perdiPvxPM(parametri);
			else if ((int) parametri[0] == 26)
				utilEffetto.perdiPVxRisorse(parametri);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void attivaOnAzione(Giocatore giocatore, EAzioniGioco azione, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < this.effetto.size(); i++) {
			this.effetto.get(i)[1] = giocatore;
			this.effetto.get(i)[3] = famigliare;
			this.effetto.get(i)[4] = carta;
			if ((EAzioniGioco) (this.effetto.get(i)[2]) == azione)
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
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.Mercato)
				return true;
		}
		return false;
	}

	public boolean attivaOnInizioTurno() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.InizioTurno)
				return true;
		}
		return false;
	}

	public boolean attivaOnPersonaggiFinePartita() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.PersonaggiFinePartita)
				return true;
		}
		return false;
	}

	public boolean attivaOnTerritoriFinePartita() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.TerritoriFinePartita)
				return true;
		}
		return false;
	}

	public boolean attivaOnImpreseFinePartita() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.ImpreseFinePartita)
				return true;
		}
		return false;
	}

	public boolean attivaOnRiceviMonete() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.RiceviMonete)
				return true;
		}
		return false;
	}

	public boolean attivaOnRiceviPietreOLegno() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.RiceviPietreOLegno)
				return true;
		}
		return false;
	}

	public boolean attivaOnRiceviPM() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.RiceviPM)
				return true;
		}
		return false;
	}

	public boolean attivaOnRiceviServitori() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.RiceviServitori)
				return true;
		}
		return false;
	}

	public boolean attivaOnPagaServitore() {
		for (int i = 0; i < effetto.size(); i++) {
			if ((EAzioniGioco) (effetto.get(i)[2]) == EAzioniGioco.PagaServitori)
				return true;
		}
		return false;
	}

	public String getNome() {
		return this.nome;
	}

	public int getPeriodo() {
		return this.periodo;
	}
}