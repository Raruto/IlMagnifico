package main.network.server.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import main.model.Edificio;
import main.model.Giocatore;
import main.model.Impresa;
import main.model.Personaggio;
import main.model.Plancia;
import main.model.Punti;
import main.model.Risorsa;
import main.model.SpazioAzione;
import main.model.Territorio;
import main.network.protocol.PlayerColors;
import main.util.EAzioniGiocatore;
import main.util.EFasiDiGioco;
import main.util.EPunti;
import main.util.ERisorse;

public class UpdateStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6689449538127752618L;

	/**
	 * Azione eseguita dal giocatore (vedi {@link EAzioniGiocatore}).
	 */
	private EAzioniGiocatore azioneGiocatore;

	/**
	 * Nome del giocatore che ha eseguito l'azione.
	 */
	private String nomeGiocatore;

	/**
	 * {@link Punti} del giocatore che ha eseguito l'azione
	 */
	private HashMap<EPunti, Integer> puntiGiocatore;
	// private Punti puntiGiocatore;

	/**
	 * {@link Risorsa} del giocatore che ha eseguito l'azione
	 */
	private HashMap<ERisorse, Integer> risorseGiocatore;
	// private Risorsa risorseGiocatore;

	/**
	 * {@link Plancia} del giocatore che ha eseguito l'azione.
	 */
	// private Plancia planciaGiocatore;
	private ArrayList<Edificio> edifici;
	private ArrayList<Impresa> imprese;
	private ArrayList<Personaggio> personaggi;
	private ArrayList<Territorio> territori;

	/**
	 * Fase di gioco eseguita dal server (vedi {@link EFasiDiGioco}).
	 */
	private EFasiDiGioco faseDiGioco;

	/**
	 * {@link SpazioAzione} aggiornata.
	 */
	private SpazioAzione spazioAzione;

	/**
	 * Usato dal client per richiedere di svolgere una azione.
	 * 
	 * @param azioneRichiesta
	 */
	public UpdateStats(EAzioniGiocatore azioneRichiesta) {
		this.azioneGiocatore = azioneRichiesta;

	}

	/**
	 * Usato dal server per inviare evoluzione stato della partita.
	 * 
	 * @param faseDiGioco
	 */
	public UpdateStats(EFasiDiGioco faseDiGioco) {
		this.faseDiGioco = faseDiGioco;
	}

	/**
	 * Costruttore (aggiornamento dello stato del gioco, a seguito di un'azione
	 * eseguita da un client, es. posizionamento familiare).
	 * 
	 * @param giocatore
	 *            riferimento al giocatore che ha eseguito un'azione di gioco
	 *            (es. {@link Giocatore}).
	 * @param azione
	 *            azione di gioco eseguita dal giocatore (vedi
	 *            {@link EAzioniGiocatore}).
	 * @param spazioAzione
	 *            {@link SpazioAzione} aggiornata.
	 */
	public UpdateStats(Giocatore giocatore, EAzioniGiocatore azione, SpazioAzione spazioAzione) {
		this.azioneGiocatore = azione;
		this.nomeGiocatore = giocatore.getNome();

		// this.puntiGiocatore = giocatore.getPunti();
		this.puntiGiocatore = new HashMap<EPunti, Integer>();
		Punti p = giocatore.getPunti();
		this.puntiGiocatore.put(EPunti.Fede, p.getPuntiFede());
		this.puntiGiocatore.put(EPunti.Militare, p.getPuntiMilitari());
		this.puntiGiocatore.put(EPunti.Vittoria, p.getPuntiVittoria());

		// this.risorseGiocatore = giocatore.getRisorse();
		this.risorseGiocatore = new HashMap<ERisorse, Integer>();
		Risorsa r = giocatore.getRisorse();
		this.risorseGiocatore.put(ERisorse.Legno, r.getLegno());
		this.risorseGiocatore.put(ERisorse.Moneta, r.getMonete());
		this.risorseGiocatore.put(ERisorse.Pietra, r.getPietre());
		this.risorseGiocatore.put(ERisorse.Servitore, r.getServitori());

		// this.planciaGiocatore = giocatore.getPlancia();
		this.edifici = giocatore.getPlancia().getEdifici();
		this.imprese = giocatore.getPlancia().getImprese();
		this.personaggi = giocatore.getPlancia().getPersonaggi();
		this.territori = giocatore.getPlancia().getTerritori();

		this.spazioAzione = spazioAzione;
	}

	/**
	 * Costruttore (aggiornamento dello stato del gioco, evoluzione autonoma del
	 * gioco sul server, es. inizio/fine partita).
	 * 
	 * @param spazioAzione
	 *            {@link SpazioAzione} aggiornata.
	 */
	public UpdateStats(EFasiDiGioco fase, SpazioAzione spazioAzione) {
		this.faseDiGioco = fase;
		this.spazioAzione = spazioAzione;
	}

	public EAzioniGiocatore getAzioneGiocatore() {
		return azioneGiocatore;
	}

	public void setAzioneGiocatore(EAzioniGiocatore azioneGiocatore) {
		this.azioneGiocatore = azioneGiocatore;
	}

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

	/*
	 * public Punti getPuntiGiocatore() { return puntiGiocatore; }
	 * 
	 * public void setPuntiGiocatore(Punti punti) { this.puntiGiocatore = punti;
	 * }
	 */

	/*
	 * public Risorsa getRisorseGiocatore() { return risorseGiocatore; }
	 * 
	 * public void setRisorseGiocatore(Risorsa risorse) { this.risorseGiocatore
	 * = risorse; }
	 */

	/*
	 * public Plancia getPlanciaGiocatore() { return planciaGiocatore; }
	 * 
	 * public void setPlanciaGiocatore(Plancia plancia) { this.planciaGiocatore
	 * = plancia; }
	 */

	public SpazioAzione getSpazioAzione() {
		return spazioAzione;
	}

	public void setSpazioAzione(SpazioAzione spazioAzione) {
		this.spazioAzione = spazioAzione;
	}

	public EFasiDiGioco getAzioneServer() {
		return faseDiGioco;
	}

	public void setAzioneServer(EFasiDiGioco azioneServer) {
		this.faseDiGioco = azioneServer;
	}

	public HashMap<EPunti, Integer> getPuntiGiocatore() {
		return puntiGiocatore;
	}

	public HashMap<ERisorse, Integer> getRisorseGiocatore() {
		return risorseGiocatore;
	}

	public ArrayList<Edificio> getEdificiGiocatore() {
		return edifici;
	}

	public ArrayList<Impresa> getImpreseGiocatore() {
		return imprese;
	}

	public ArrayList<Personaggio> getPersonaggiGiocatore() {
		return personaggi;
	}

	public ArrayList<Territorio> getTerritoriGiocatore() {
		return territori;
	}
	/*
	 * public PlayerColors getColore() { return player.getColore(); }
	 */

}
