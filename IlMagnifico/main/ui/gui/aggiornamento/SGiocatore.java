package main.ui.gui.aggiornamento;

import java.util.ArrayList;

public class SGiocatore {
	
	private String nome;
	private String colore;
	private SPunti punti;
	private SRisorse risorse;
	private boolean[] scomuniche;
	private ArrayList<SFamigliare> famigliariPlancia;
	private ArrayList<String> carteTerritorio;
	private ArrayList<String> carteEdificio;
	private ArrayList<String> carteImprese;
	private ArrayList<String> cartePersonaggio;
	
	public SGiocatore(String nome, String colore, SPunti punti, SRisorse risorse, boolean[] scomuniche,
			ArrayList<SFamigliare> famigliariPlancia, ArrayList<String> carteTerritorio, ArrayList<String> carteEdificio,
			ArrayList<String> carteImprese, ArrayList<String> cartePersonaggio) {
		this.nome = nome;
		this.colore = colore;
		this.punti = punti;
		this.risorse = risorse;
		this.scomuniche = scomuniche;
		this.famigliariPlancia = famigliariPlancia;
		this.carteTerritorio = carteTerritorio;
		this.carteEdificio = carteEdificio;
		this.carteImprese = carteImprese;
		this.cartePersonaggio = cartePersonaggio;
	}

	public String getNome() {
		return nome;
	}

	public String getColore() {
		return colore;
	}

	public SPunti getPunti() {
		return punti;
	}

	public SRisorse getRisorse() {
		return risorse;
	}

	public boolean[] getScomuniche() {
		return scomuniche;
	}

	public ArrayList<SFamigliare> getFamigliariPlancia() {
		return famigliariPlancia;
	}

	public ArrayList<String> getCarteTerritorio() {
		return carteTerritorio;
	}

	public ArrayList<String> getCarteEdificio() {
		return carteEdificio;
	}

	public ArrayList<String> getCarteImprese() {
		return carteImprese;
	}

	public ArrayList<String> getCartePersonaggio() {
		return cartePersonaggio;
	}
	
	
	
	
	
}
