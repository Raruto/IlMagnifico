package main.network.server.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import main.model.Famigliare;
import main.model.Giocatore;
import main.model.Plancia;
import main.model.Punti;
import main.model.Risorsa;
import main.model.Scomunica;
import main.model.SpazioAzione;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;
import main.model.enums.EFasiDiGioco;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.EColoriGiocatori;

public class UpdateStats implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6689449538127752618L;

	/**
	 * Azione eseguita dal giocatore (vedi {@link EAzioniGiocatore}).
	 * (tipicamente settato lato Client per la Richiesta di svolgimento di
	 * un'azione di gioco e lato Server per la notifica per la notifica agli
	 * altri giocatori su quale azione è stata svolta).
	 */
	private EAzioniGiocatore azioneGiocatore;

	/**
	 * Nome del giocatore che ha eseguito l'azione. (tipicamente settato lato
	 * Server per la notifica per la notifica agli altri giocatori su chi ha
	 * svolto l'azione).
	 */
	private String nomeGiocatore;

	/**
	 * Colore della pedina spostata dal giocatore. (tipicamente settato lato
	 * Client per la richiesta di svolgimento di un'azione di gioco da parte di
	 * un giocatore).
	 */
	private EColoriPedine colorePedinaSpostata;

	/**
	 * Posizione dove il giocatore ha spostato la pedina. (tipicamente settato
	 * lato Client per la richiesta di svolgimento di un'azione di gioco da
	 * parte di un giocatore).
	 */
	private int posizionePedinaSpostata;

	/**
	 * Privilegio/i del Consiglio scelti dal giocatore (vedi
	 * {@link ESceltePrivilegioDelConsiglio}). (tipicamente settato lato Client
	 * per la richiesta di svolgimento di un'azione di gioco da parte di un
	 * giocatore, ad esempio durante lo svolgimento di una azione "Mercato"
	 * all'interno della "Zona 4").
	 */
	private ESceltePrivilegioDelConsiglio[] sceltePrivilegiConsiglio;

	/**
	 * Costi della carta scelti dal giocatore (vedi {@link ECostiCarte}).
	 * (tipicamente settato lato Client durante lo svolgimento di un'azione di
	 * gioco di tipo "Torre", la scelta va effettuata solamente nel caso la
	 * carta contenga 2 o più costi opzionali, negli altri casi viene
	 * automaticamente attivato l'unico costo disponibile per la carta
	 * selezionata).
	 */
	private ECostiCarte[] scelteCosti;

	/**
	 * Effetti delle carte da attivare della plancia giocatore (vedi
	 * {@link EEffettiPermanenti}). (tipicamente settato lato Client durante lo
	 * svolgimento di un'azione di gioco di tipo "Produzione" o "Raccolto", la
	 * scelta va effettuata solamente nel caso la carta).
	 */
	private EEffettiPermanenti[] scelteEffettiPermanenti;

	/**
	 * Numero di servitori che il giocatore è intenzionato a spendere per
	 * aumentare il valore di un suo famigliare (tipicamente settato lato
	 * Client).
	 */
	private int servitoriDaPagare;

	/**
	 * True se la Chiesa e' stata supportata dal giocatore. (tipicamente settato
	 * lato Client per la richiesta di supporto della chiesa e lato Server per
	 * la notifica agli altri giocatori).
	 */
	private boolean supportoChiesa;

	/**
	 * {@link Punti} del giocatore che ha eseguito l'azione. (tipicamente
	 * settato lato Server per la notifica agli altri giocatori
	 * dell'aggiornamento dei punti del giocatore che ha svolto l'azione di
	 * gioco presso il server).
	 */
	private Punti puntiGiocatore;

	/**
	 * {@link Risorsa} del giocatore che ha eseguito l'azione. (tipicamente
	 * settato lato Server per la notifica per la notifica agli altri giocatori
	 * dell'aggiornamento delle risorse del giocatore che ha svolto l'azione di
	 * gioco presso il server).
	 */
	private Risorsa risorseGiocatore;

	/**
	 * {@link Plancia} del giocatore che ha eseguito l'azione. (tipicamente
	 * settato lato Server per la notifica agli altri giocatori
	 * dell'aggiornamento delle carte presenti nella plancia del giocatore che
	 * ha svolto l'azione di gioco presso il server).
	 */
	private Plancia planciaGiocatore;

	/**
	 * Famigliari del giocatore che ha eseguito l'azione (vedi
	 * {@link Famigliare}). (tipicamente settato lato Server per la notifica
	 * agli altri giocatori delle posizione dei famigliari del giocatore che ha
	 * svolto l'azione di gioco presso il server).
	 */
	private Famigliare[] famigliaGiocatore;

	/**
	 * Scomuniche del giocatore che ha eseguito l'azione (vedi
	 * {@link Scomunica}). (tipicamente settato lato Server per la notifica
	 * agli altri giocatori delle scomuniche dei famigliari del giocatore che ha
	 * svolto l'azione di gioco presso il server).
	 */
	private Scomunica[] scomunicheGiocatore;

	/**
	 * Fase di gioco eseguita dal server (vedi {@link EFasiDiGioco}).
	 * (tipicamente settato lato Server per la notifica agli altri giocatori
	 * dell'avanzamento dello stato interno della partita).
	 */
	private EFasiDiGioco faseDiGioco;

	/**
	 * Nomi dei giocatori che devono eseguire l'azione (usato anche per
	 * notificare giocatori connessi). (tipicamente settato lato Server per la
	 * notifica agli altri giocatori).
	 */
	private ArrayList<String> nomiGiocatori;

	/**
	 * Punti dei giocatori (vedi {@link Punti}). (tipicamente settato lato
	 * Server).
	 */
	private HashMap<String, Punti> puntiGiocatori;

	/**
	 * Risorse dei giocatori (vedi {@link Risorsa}). (tipicamente settato lato
	 * Server).
	 */
	private HashMap<String, Risorsa> risorseGiocatori;

	/**
	 * Plance dei giocatori (vedi {@link Plancia}). (tipicamente settato lato
	 * Server).
	 */
	private HashMap<String, Plancia> planceGiocatori;

	/**
	 * Famigliari dei giocatori (vedi {@link Famigliare}). (tipicamente settato
	 * lato Server).
	 */
	private HashMap<String, Famigliare[]> famiglieGiocatori;

	/**
	 * Scomuniche dei giocatori (vedi {@link Scomunica}). (tipicamente settato
	 * lato Server).
	 */
	private HashMap<String, Scomunica[]> scomunicheGiocatori;

	/**
	 * {@link SpazioAzione} aggiornato (tipicamente settato lato Server per la
	 * notifica agli altri giocatori).
	 */
	private SpazioAzione spazioAzione;

	/**
	 * {@link EColoriGiocatori} del giocatore che ha effettuato l'azione
	 * (tipicamente settato lato Server per la notifica agli altri giocatori).
	 */
	private EColoriGiocatori coloreGiocatore;

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
	 *            {@link SpazioAzione} aggiornato.
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
			// this.famigliaGiocatore[i].getGiocatore().getColore();
		}

		this.scomunicheGiocatore = new Scomunica[3];
		for (int i = 0; i < 3; i++) {
			this.scomunicheGiocatore[i] = giocatore.getScomunica(i);
		}

		this.spazioAzione = spazioAzione;
	}

	/**
	 * Costruttore (aggiornamento dello stato del gioco, evoluzione autonoma del
	 * gioco sul server, es. inizio/fine partita).
	 * 
	 * @param spazioAzione
	 *            {@link SpazioAzione} aggiornato.
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
		this.scomunicheGiocatori = new HashMap<String, Scomunica[]>();

		String nome;

		Famigliare[] famiglia;
		Scomunica[] scomuniche;
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

			scomuniche = new Scomunica[3];
			for (int i = 0; i < 3; i++) {
				scomuniche[i] = giocatore.getScomunica(i);
			}
			this.scomunicheGiocatori.put(nome, scomuniche);
		}
	}

	/**
	 * Costruttore (aggiornamento dello stato del gioco, evoluzione autonoma del
	 * gioco sul server, es. inizio/fine partita).
	 * 
	 * @param fase
	 * @param giocatori
	 * @param spazioAzione
	 *            {@link SpazioAzione} aggiornato.
	 */
	public UpdateStats(EFasiDiGioco fase, ArrayList<Giocatore> giocatori, SpazioAzione spazioAzione) {
		this(fase, giocatori);
		this.spazioAzione = spazioAzione;
	}

	/**
	 * Ritorna l'azione eseguita/richiesta dal giocatore (vedi
	 * {@link EAzioniGiocatore}). (tipicamente settato lato Client per la
	 * Richiesta di svolgimento di un'azione di gioco e lato Server per la
	 * notifica per la notifica agli altri giocatori su quale azione è stata
	 * svolta).
	 */
	public EAzioniGiocatore getAzioneGiocatore() {
		return azioneGiocatore;
	}

	/**
	 * Ritorna il nome del giocatore che ha eseguito/richiesto l'azione.
	 * (tipicamente settato lato Server per la notifica per la notifica agli
	 * altri giocatori su chi ha svolto l'azione).
	 */
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	/**
	 * Ritorna il colore ({@link EColoriGiocatori}) del giocatore che ha
	 * effettuato/richiesto l'azione (tipicamente settato lato Server per la
	 * notifica agli altri giocatori).
	 */
	public EColoriGiocatori getColoreGiocatore() {
		return coloreGiocatore;
	}

	/**
	 * Ritorna lo {@link SpazioAzione} aggiornato (tipicamente settato lato
	 * Server per la notifica agli altri giocatori).
	 */
	public SpazioAzione getSpazioAzione() {
		return spazioAzione;
	}

	/**
	 * Ritorna la fase di gioco eseguita dal server (vedi {@link EFasiDiGioco}).
	 * (tipicamente settato lato Server per la notifica agli altri giocatori
	 * dell'avanzamento dello stato interno della partita).
	 */
	public EFasiDiGioco getAzioneServer() {
		return faseDiGioco;
	}

	/**
	 * Ritorna i {@link Punti} del giocatore che ha eseguito/richiesto l'azione.
	 * (tipicamente settato lato Server per la notifica agli altri giocatori
	 * dell'aggiornamento dei punti del giocatore che ha svolto l'azione di
	 * gioco presso il server).
	 */
	public Punti getPuntiGiocatore() {
		return this.puntiGiocatore;
	}

	/**
	 * Ritorna le {@link Risorsa} del giocatore che ha eseguito/richiesto
	 * l'azione. (tipicamente settato lato Server per la notifica per la
	 * notifica agli altri giocatori dell'aggiornamento delle risorse del
	 * giocatore che ha svolto l'azione di gioco presso il server).
	 */
	public Risorsa getRisorseGiocatore() {
		return this.risorseGiocatore;
	}

	/**
	 * Ritorna la {@link Plancia} del giocatore che ha eseguito/richiesto
	 * l'azione. (tipicamente settato lato Server per la notifica agli altri
	 * giocatori dell'aggiornamento delle carte presenti nella plancia del
	 * giocatore che ha svolto l'azione di gioco presso il server).
	 */
	public Plancia getPlanciaGiocatore() {
		return this.planciaGiocatore;
	}

	/**
	 * Ritorna i Famigliari del giocatore che ha eseguito/richiesto l'azione
	 * (vedi {@link Famigliare}). (tipicamente settato lato Server per la
	 * notifica agli altri giocatori delle posizione dei famigliari del
	 * giocatore che ha svolto l'azione di gioco presso il server).
	 */
	public Famigliare[] getFamigliaGiocatore() {
		return this.famigliaGiocatore;
	}

	/**
	 * Ritorna le Scomuniche del giocatore che ha eseguito/richiesto l'azione
	 * (vedi {@link Scomunica}). (tipicamente settato lato Server per la
	 * notifica agli altri giocatori delle scomuniche del
	 * giocatore che ha svolto l'azione di gioco presso il server).
	 */
	public Scomunica[] getScomunicheGiocatore() {
		return this.scomunicheGiocatore;
	}

	/**
	 * Ritorna True se la Chiesa e' stata supportata dal giocatore. (tipicamente
	 * settato lato Client per la richiesta di supporto della chiesa e lato
	 * Server per la notifica agli altri giocatori).
	 */
	public boolean getSupportoChiesa() {
		return this.supportoChiesa;
	}

	/**
	 * Ritorna la posizione dove il giocatore ha spostato la pedina.
	 * (tipicamente settato lato Client per la richiesta di svolgimento di
	 * un'azione di gioco da parte di un giocatore).
	 */
	public int getPosizioneSpostamentoPedina() {
		return this.posizionePedinaSpostata;
	}

	/**
	 * Ritorna i nomi dei giocatori che devono eseguire l'azione (usato anche
	 * per notificare giocatori connessi). (tipicamente settato lato Server per
	 * la notifica agli altri giocatori).
	 */
	public ArrayList<String> getNomiGiocatori() {
		return this.nomiGiocatori;
	}

	/**
	 * Ritorna i/il privilegio/i del Consiglio scelti dal giocatore (vedi
	 * {@link ESceltePrivilegioDelConsiglio}). (tipicamente settato lato Client
	 * per la richiesta di svolgimento di un'azione di gioco da parte di un
	 * giocatore, ad esempio durante lo svolgimento di una azione "Mercato"
	 * all'interno della "Zona 4").
	 */
	public ESceltePrivilegioDelConsiglio[] getSceltePrivilegiConsiglio() {
		return this.sceltePrivilegiConsiglio;
	}

	/**
	 * Ritorna i costi della carta scelti dal giocatore (vedi
	 * {@link ECostiCarte}). (tipicamente settato lato Client durante lo
	 * svolgimento di un'azione di gioco di tipo "Torre", la scelta va
	 * effettuata solamente nel caso la carta contenga 2 o più costi opzionali,
	 * negli altri casi viene automaticamente attivato l'unico costo disponibile
	 * per la carta selezionata).
	 */
	public ECostiCarte[] getScelteCosti() {
		return this.scelteCosti;
	}

	/**
	 * Ritorna gli effetti delle carte da attivare della plancia giocatore (vedi
	 * {@link EEffettiPermanenti}). (tipicamente settato lato Client durante lo
	 * svolgimento di un'azione di gioco di tipo "Produzione" o "Raccolto", la
	 * scelta va effettuata solamente nel caso la carta).
	 */
	public EEffettiPermanenti[] getScelteEffettiPermanenti() {
		return this.scelteEffettiPermanenti;
	}

	/**
	 * Ritorna il numero di servitori che il giocatore è intenzionato a spendere
	 * per aumentare il valore di un suo famigliare (tipicamente settato lato
	 * Client).
	 */
	public int getServitoriDaPagare() {
		return this.servitoriDaPagare;
	}

	/**
	 * Ritorna i punti dei giocatori <"Nome","Punti"> (vedi {@link Punti}).
	 * (tipicamente settato lato Server).
	 */
	public HashMap<String, Punti> getPuntiGiocatori() {
		return this.puntiGiocatori;
	}

	/**
	 * Ritorna le risorse dei giocatori <"Nome","Risorsa"> (vedi
	 * {@link Risorsa}). (tipicamente settato lato Server).
	 */
	public HashMap<String, Risorsa> getRisorseGiocatori() {
		return this.risorseGiocatori;
	}

	/**
	 * Ritorna le plance dei giocatori <"Nome","Plancia"> (vedi
	 * {@link Plancia}). (tipicamente settato lato Server).
	 */
	public HashMap<String, Plancia> getPlanceGiocatori() {
		return this.planceGiocatori;
	}

	/**
	 * Ritorna i famigliari dei giocatori <"Nome","Famigliare[]"> (vedi
	 * {@link Famigliare}). (tipicamente settato lato Server).
	 */
	public HashMap<String, Famigliare[]> getFamiglieGiocatori() {
		return this.famiglieGiocatori;
	}

	/**
	 * Ritorna le scomuniche dei giocatori <"Nome","Scomunica[]"> (vedi
	 * {@link Scomunica}). (tipicamente settato lato Server).
	 */
	public HashMap<String, Scomunica[]> getScomunicheGiocatori() {
		return this.scomunicheGiocatori;
	}

	///////////////////////////////////////////////////////////////////
	// TODO: verificare quale dei seguenti metodi è possbile cancellare
	///////////////////////////////////////////////////////////////////

	public void setColorePedina(EColoriPedine colore) {
		this.colorePedinaSpostata = colore;
	}

	public void setSceltaConsiglio(ESceltePrivilegioDelConsiglio scelta) {
		this.sceltePrivilegiConsiglio = new ESceltePrivilegioDelConsiglio[1];
		this.sceltePrivilegiConsiglio[0] = scelta;
	}

	public void setServitoriDaPagare(int servitori) {
		this.servitoriDaPagare = servitori;
	}

	public void setScelteEffettiPermanenti(EEffettiPermanenti[] scelteEffettiPermanenti) {
		this.scelteEffettiPermanenti = scelteEffettiPermanenti;
	}

	public void setSceltePrivilegiConsiglio(ESceltePrivilegioDelConsiglio[] scelte) {
		this.sceltePrivilegiConsiglio = scelte;
	}

	public void setScelteCosti(ECostiCarte[] scelteCosti) {
		this.scelteCosti = scelteCosti;
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

	public void supportaChiesa(boolean supportoChiesa) {
		this.supportoChiesa = supportoChiesa;
	}

	public void addToNomiGiocatori(String nomeGiocatore) {

		if (nomiGiocatori == null)
			nomiGiocatori = new ArrayList<String>();

		this.nomiGiocatori.add(nomeGiocatore);
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

}
