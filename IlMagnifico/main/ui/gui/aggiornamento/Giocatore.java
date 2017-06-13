package main.ui.gui.aggiornamento;

import java.util.ArrayList;

public class Giocatore {
	
	private String nome;
	private String colore;
	private Punti punti;
	private Risorse risorse;
	private boolean[] scomuniche;
	private ArrayList<Famigliare> famigliariPlancia;
	private ArrayList<String> carteTerritorio;
	private ArrayList<String> carteEdificio;
	private ArrayList<String> carteImprese;
	private ArrayList<String> cartePersonaggio;
	
	public Giocatore(String nome, String colore, Punti punti, Risorse risorse, boolean[] scomuniche,
			ArrayList<Famigliare> famigliariPlancia, ArrayList<String> carteTerritorio, ArrayList<String> carteEdificio,
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

	public Punti getPunti() {
		return punti;
	}

	public Risorse getRisorse() {
		return risorse;
	}

	public boolean[] getScomuniche() {
		return scomuniche;
	}

	public ArrayList<Famigliare> getFamigliariPlancia() {
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
