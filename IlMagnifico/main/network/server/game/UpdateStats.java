package main.network.server.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import main.model.Edificio;
import main.model.Famigliare;
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
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.PlayerColors;

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
	private Punti puntiGiocatore;

	/**
	 * {@link Risorsa} del giocatore che ha eseguito l'azione
	 */
	private Risorsa risorseGiocatore;

	/**
	 * {@link Plancia} del giocatore che ha eseguito l'azione
	 */
	private Plancia planciaGiocatore;

	/**
	 * Famigliari del giocatore che ha eseguito l'azione (vedi
	 * {@link Famigliare}).
	 */
	private Famigliare[] famigliaGiocatore;

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
	 * Punti dei giocatori (vedi {@link Punti}).
	 */
	private HashMap<String, Punti> puntiGiocatori;

	/**
	 * Risorse dei giocatori (vedi {@link Risorsa}).
	 */
	private HashMap<String, Risorsa> risorseGiocatori;

	/**
	 * Plance dei giocatori (vedi {@link Plancia}).
	 */
	private HashMap<String, Plancia> planceGiocatori;

	/**
	 * Famigliari dei giocatori (vedi {@link Famigliare}).
	 */
	private HashMap<String, Famigliare[]> famiglieGiocatori;

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
		this.risorseGiocatore = giocatore.getRisorse();
		this.planciaGiocatore = giocatore.getPlancia();

		this.famigliaGiocatore = new Famigliare[4];
		for (int i = 0; i < 4; i++) {
			this.famigliaGiocatore[i] = giocatore.getFamigliare(i);
		}

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

	public UpdateStats(EFasiDiGioco fase, ArrayList<Giocatore> giocatori) {
		this.faseDiGioco = fase;

		this.nomiGiocatori = new ArrayList<String>();

		this.puntiGiocatori = new HashMap<String, Punti>();
		this.risorseGiocatori = new HashMap<String, Risorsa>();
		this.planceGiocatori = new HashMap<String, Plancia>();
		this.famiglieGiocatori = new HashMap<String, Famigliare[]>();

		Famigliare[] famiglia;
		String nome;
		for (Giocatore giocatore : giocatori) {
			this.nomiGiocatori.add(nome = giocatore.getNome());

			this.puntiGiocatori.put(nome, giocatore.getPunti());
			this.risorseGiocatori.put(nome, giocatore.getRisorse());
			this.planceGiocatori.put(nome, giocatore.getPlancia());
			famiglia = new Famigliare[4];
			for (int i = 0; i < 4; i++) {
				famiglia[i] = giocatore.getFamigliare(i);
			}

			this.famiglieGiocatori.put(nome, famiglia);
		}
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

	public EFasiDiGioco getAzioneServer() {
		return faseDiGioco;
	}

	public Punti getPuntiGiocatore() {
		return this.puntiGiocatore;
	}

	public Risorsa getRisorseGiocatore() {
		return this.risorseGiocatore;
	}

	public Plancia getPlanciaGiocatore() {
		return this.planciaGiocatore;
	}

	public Famigliare[] getFamigliaGiocatore() {
		return this.famigliaGiocatore;
	}

	public int getPosizioneSpostamentoPedina() {
		return this.posizionePedinaSpostata;
	}

	public void aumentaValorePedina(EColoriPedine color, int servants) {
		this.colorePedinaSpostata = color;
		this.servitoriDaPagare = servants;
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

	public int getServitoriDaPagare() {
		return this.servitoriDaPagare;
	}

	public HashMap<String, Punti> getPuntiGiocatori() {
		return this.puntiGiocatori;
	}

	public HashMap<String, Risorsa> getRisorseGiocatori() {
		return this.risorseGiocatori;
	}

	public HashMap<String, Plancia> getPlanceGiocatori() {
		return this.planceGiocatori;
	}

	public HashMap<String, Famigliare[]> getFamiglieGiocatori() {
		return this.famiglieGiocatori;
	}

}
