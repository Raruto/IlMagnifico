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
import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;
import main.model.enums.EFasiDiGioco;
import main.model.enums.EPunti;
import main.model.enums.ERisorse;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.PlayerColors;
import res.old.main.model.game.players.PuntiGiocatore;

public class UpdateStats implements Serializable {

	/**
	 * Serial Version UID
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
	 * Colore della pedina spostata dal giocatore.
	 */
	private EColoriPedine colorePedinaSpostata;

	/**
	 * Posizione dove il giocatore ha spostato la pedina.
	 */
	private int posizionePedinaSpostata;

	/**
	 * Privilegio/i del Consiglio scelti dal giocatore (vedi
	 * {@link ESceltePrivilegioDelConsiglio}).
	 */
	private ESceltePrivilegioDelConsiglio[] sceltePrivilegiConsiglio;

	/**
	 * True se la Chiesa � stata supportata dal giocatore.
	 */
	private boolean supportoChiesa;

	/**
	 * {@link Punti} del giocatore che ha eseguito l'azione
	 */
	// private HashMap<EPunti, Integer> puntiGiocatore;
	private Punti puntiGiocatore;

	/**
	 * {@link Risorsa} del giocatore che ha eseguito l'azione
	 */
	// private HashMap<ERisorse, Integer> risorseGiocatore;
	private Risorsa risorseGiocatore;

	/**
	 * {@link Plancia} del giocatore che ha eseguito l'azione
	 */
	private Plancia planciaGiocatore;

	/**
	 * {@link Plancia} del giocatore che ha eseguito l'azione.
	 */
	private ArrayList<Edificio> edifici;
	private ArrayList<Impresa> imprese;
	private ArrayList<Personaggio> personaggi;
	private ArrayList<Territorio> territori;

	/**
	 * Fase di gioco eseguita dal server (vedi {@link EFasiDiGioco}).
	 */
	private EFasiDiGioco faseDiGioco;

	/**
	 * Nomi dei giocatori che devono eseguire l'azione (usato anche per
	 * notificare giocatori connessi).
	 */
	private ArrayList<String> nomiGiocatori;

	/**
	 * {@link SpazioAzione} aggiornata.
	 */
	private SpazioAzione spazioAzione;

	private PlayerColors coloreGiocatore;

	private ECostiCarte[] scelteCosti;

	private int servitoriDaPagare;

	private EEffettiPermanenti[] scelteEffettiPermanenti;

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
		this.coloreGiocatore = giocatore.getColore();

		this.puntiGiocatore = giocatore.getPunti();
		// this.puntiGiocatore = new HashMap<EPunti, Integer>();
		// Punti p = giocatore.getPunti();
		// this.puntiGiocatore.put(EPunti.Fede, p.getPuntiFede());
		// this.puntiGiocatore.put(EPunti.Militare, p.getPuntiMilitari());
		// this.puntiGiocatore.put(EPunti.Vittoria, p.getPuntiVittoria());

		this.risorseGiocatore = giocatore.getRisorse();
		// this.risorseGiocatore = new HashMap<ERisorse, Integer>();
		// Risorsa r = giocatore.getRisorse();
		// this.risorseGiocatore.put(ERisorse.Legno, r.getLegno());
		// this.risorseGiocatore.put(ERisorse.Moneta, r.getMonete());
		// this.risorseGiocatore.put(ERisorse.Pietra, r.getPietre());
		// this.risorseGiocatore.put(ERisorse.Servitore, r.getServitori());

		this.planciaGiocatore = giocatore.getPlancia();
		// this.edifici = giocatore.getPlancia().getEdifici();
		// this.imprese = giocatore.getPlancia().getImprese();
		// this.personaggi = giocatore.getPlancia().getPersonaggi();
		// this.territori = giocatore.getPlancia().getTerritori();

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

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public PlayerColors getColoreGiocatore() {
		return coloreGiocatore;
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
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

	public Punti getPuntiGiocatore() {
		return this.puntiGiocatore;
	}
	/*
	 * public HashMap<EPunti, Integer> getPuntiGiocatore() { return
	 * puntiGiocatore; }
	 */

	public Risorsa getRisorseGiocatore() {
		return this.risorseGiocatore;
	}
	/*
	 * public HashMap<ERisorse, Integer> getRisorseGiocatore() { return
	 * risorseGiocatore; }
	 */

	public int getPosizioneSpostamentoPedina() {
		return this.posizionePedinaSpostata;
	}

	public int getIndiceColorePedina() {
		return this.colorePedinaSpostata.getIndiceColore();
	}

	public void spostaPedina(EColoriPedine color, int position) {
		this.colorePedinaSpostata = color;
		this.posizionePedinaSpostata = position;
	}

	public boolean getSupportoChiesa() {
		return this.supportoChiesa;
	}

	public void supportaChiesa(boolean supportoChiesa) {
		this.supportoChiesa = supportoChiesa;
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

	public void addToNomiGiocatori(String nomeGiocatore) {

		if (nomiGiocatori == null)
			nomiGiocatori = new ArrayList<String>();

		this.nomiGiocatori.add(nomeGiocatore);
	}

	public ArrayList<String> getNomiGiocatori() {
		return this.nomiGiocatori;
	}

	public ESceltePrivilegioDelConsiglio[] getSceltePrivilegiConsiglio() {
		return this.sceltePrivilegiConsiglio;
	}

	public void setSceltePrivilegiConsiglio(ESceltePrivilegioDelConsiglio[] scelte) {
		this.sceltePrivilegiConsiglio = scelte;
	}

	public ECostiCarte[] getScelteCosti() {
		return this.scelteCosti;
	}

	public void setScelteCosti(ECostiCarte[] scelteCosti) {
		this.scelteCosti = scelteCosti;
	}

	public EEffettiPermanenti[] getScelteEffettiPermanenti() {
		return this.scelteEffettiPermanenti;
	}

	public void getScelteEffettiPermanenti(EEffettiPermanenti[] scelteEffettiPermanenti) {
		this.scelteEffettiPermanenti = scelteEffettiPermanenti;
	}

	public void setServitoriDaPagare(int servitoriDaPagare) {
		this.servitoriDaPagare = servitoriDaPagare;
	}

	public int getServitoriDaPagare() {
		return this.servitoriDaPagare;
	}
}
