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
	private Punti puntiGiocatore;

	/**
	 * {@link Risorsa} del giocatore che ha eseguito l'azione
	 */
	private Risorsa risorseGiocatore;

	/**
	 * {@link Plancia} del giocatore che ha eseguito l'azione.
	 */
	private Plancia planciaGiocatore;

	/**
	 * Fase di gioco eseguita dal server (vedi {@link EFasiDiGioco}).
	 */
	private EFasiDiGioco faseDiGioco;

	/**
	 * {@link SpazioAzione} aggiornata.
	 */
	private SpazioAzione spazioAzione;

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
		this.puntiGiocatore = giocatore.getPunti();
		this.risorseGiocatore = giocatore.getRisorse();
		this.planciaGiocatore = giocatore.getPlancia();
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

	public Punti getPuntiGiocatore() {
		return puntiGiocatore;
	}

	public void setPuntiGiocatore(Punti punti) {
		this.puntiGiocatore = punti;
	}

	public Risorsa getRisorseGiocatore() {
		return risorseGiocatore;
	}

	public void setRisorseGiocatore(Risorsa risorse) {
		this.risorseGiocatore = risorse;
	}

	public Plancia getPlanciaGiocatore() {
		return planciaGiocatore;
	}

	public void setPlanciaGiocatore(Plancia plancia) {
		this.planciaGiocatore = plancia;
	}

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

	/*
	 * public HashMap<EPunti, Integer> getPuntiGiocatore() { HashMap<EPunti,
	 * Integer> punti = new HashMap<EPunti, Integer>(); Punti p =
	 * player.getPunti(); punti.put(EPunti.Fede, p.getPuntiFede());
	 * punti.put(EPunti.Militare, p.getPuntiMilitari());
	 * punti.put(EPunti.Vittoria, p.getPuntiVittoria()); return punti; }
	 * 
	 * public HashMap<ERisorse, Integer> getRisorseGiocatore() {
	 * HashMap<ERisorse, Integer> risorse = new HashMap<ERisorse, Integer>();
	 * Risorsa r = player.getRisorse(); risorse.put(ERisorse.Legno,
	 * r.getLegno()); risorse.put(ERisorse.Moneta, r.getMonete());
	 * risorse.put(ERisorse.Pietra, r.getPietre());
	 * risorse.put(ERisorse.Servitore, r.getServitori()); return risorse; }
	 * 
	 * public ArrayList<Edificio> getEdificiGiocatore() { return
	 * player.getPlancia().getEdifici(); }
	 * 
	 * public ArrayList<Impresa> getImpreseGiocatore() { return
	 * player.getPlancia().getImprese(); }
	 * 
	 * public ArrayList<Personaggio> getPersonaggiGiocatore() { return
	 * player.getPlancia().getPersonaggi(); }
	 * 
	 * public ArrayList<Territorio> getTerritoriGiocatore() { return
	 * player.getPlancia().getTerritori(); }
	 * 
	 * public PlayerColors getColore() { return player.getColore(); }
	 */

}
