package main.ui.gui.aggiornamento;

import java.util.ArrayList;

public class Aggiornamento {

	private int turno;
	private ArrayList<Giocatore> giocatori;
	private Famigliare[] torre;
	private Famigliare[] mercato;
	private Famigliare raccoltoRotondo;
	private ArrayList<Famigliare> raccoltoOvale;
	private Famigliare produzioneRotondo;
	private ArrayList<Famigliare> produzioneOvale;
	private ArrayList<Famigliare> palazzoConsiglio;
	private String[] carteScomunica;
	private String[] carteSviluppoTorre;

	public Aggiornamento(int turno, ArrayList<Giocatore> giocatori, Famigliare[] torre, Famigliare[] mercato,
			Famigliare raccoltoRotondo, ArrayList<Famigliare> raccoltoOvale, Famigliare produzioneRotondo,
			ArrayList<Famigliare> produzioneOvale, ArrayList<Famigliare> palazzoConsiglio, String[] carteScomunica,
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

	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}

	public Famigliare[] getTorre() {
		return torre;
	}

	public Famigliare[] getMercato() {
		return mercato;
	}

	public Famigliare getRaccoltoRotondo() {
		return raccoltoRotondo;
	}

	public ArrayList<Famigliare> getRaccoltoOvale() {
		return raccoltoOvale;
	}

	public Famigliare getProduzioneRotondo() {
		return produzioneRotondo;
	}

	public ArrayList<Famigliare> getProduzioneOvale() {
		return produzioneOvale;
	}

	public ArrayList<Famigliare> getPalazzoConsiglio() {
		return palazzoConsiglio;
	}

	public String[] getCarteScomunica() {
		return carteScomunica;
	}

	public String[] getCarteSviluppoTorre() {
		return carteSviluppoTorre;
	}

}
