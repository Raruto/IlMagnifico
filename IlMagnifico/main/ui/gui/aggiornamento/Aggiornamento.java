package main.ui.gui.aggiornamento;

import java.util.ArrayList;

public class Aggiornamento {

	private int turno;
	private ArrayList<SGiocatore> giocatori;
	private SFamigliare[] torre;
	private SFamigliare[] mercato;
	private SFamigliare raccoltoRotondo;
	private ArrayList<SFamigliare> raccoltoOvale;
	private SFamigliare produzioneRotondo;
	private ArrayList<SFamigliare> produzioneOvale;
	private ArrayList<SFamigliare> palazzoConsiglio;
	private String[] carteScomunica;
	private String[] carteSviluppoTorre;

	public Aggiornamento(int turno, ArrayList<SGiocatore> giocatori, SFamigliare[] torre, SFamigliare[] mercato,
			SFamigliare raccoltoRotondo, ArrayList<SFamigliare> raccoltoOvale, SFamigliare produzioneRotondo,
			ArrayList<SFamigliare> produzioneOvale, ArrayList<SFamigliare> palazzoConsiglio, String[] carteScomunica,
			String[] carteSviluppoTorre) {
		this.turno = turno;
		this.giocatori = giocatori;
		this.torre = torre;
		this.mercato = mercato;
		this.raccoltoRotondo = raccoltoRotondo;
		this.raccoltoOvale = raccoltoOvale;
		this.produzioneRotondo = produzioneRotondo;
		this.produzioneOvale = produzioneOvale;
		this.palazzoConsiglio = palazzoConsiglio;
		this.carteScomunica = carteScomunica;
		this.carteSviluppoTorre = carteSviluppoTorre;
	}

	public int getTurno() {
		return turno;
	}

	public ArrayList<SGiocatore> getGiocatori() {
		return giocatori;
	}

	public SFamigliare[] getTorre() {
		return torre;
	}

	public SFamigliare[] getMercato() {
		return mercato;
	}

	public SFamigliare getRaccoltoRotondo() {
		return raccoltoRotondo;
	}

	public ArrayList<SFamigliare> getRaccoltoOvale() {
		return raccoltoOvale;
	}

	public SFamigliare getProduzioneRotondo() {
		return produzioneRotondo;
	}

	public ArrayList<SFamigliare> getProduzioneOvale() {
		return produzioneOvale;
	}

	public ArrayList<SFamigliare> getPalazzoConsiglio() {
		return palazzoConsiglio;
	}

	public String[] getCarteScomunica() {
		return carteScomunica;
	}

	public String[] getCarteSviluppoTorre() {
		return carteSviluppoTorre;
	}

}
