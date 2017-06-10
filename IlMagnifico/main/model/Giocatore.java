package main.model;

import java.io.Serializable;

import main.model.enums.EEffettiPermanenti;
import main.model.enums.EColoriGiocatori;
import main.model.exceptions.NoEnoughResourcesException;

/**
 * 
 */
public class Giocatore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3040945684311418121L;

	/**
	 * Nome del giocatore.
	 */
	private String nome;

	/**
	 * Colore del giocatore.
	 */
	private EColoriGiocatori colore;

	/**
	 * 
	 */
	private Famigliare[] famiglia;

	/**
	 * 
	 */
	private Risorsa risorse;

	/**
	 * 
	 */
	private Plancia plancia;

	/**
	 * 
	 */
	private Punti punti;

	private SpazioAzione spazioAzione;

	/**
	 * 
	 */
	private Scomunica[] scomuniche;

	/**
	 * Default constructor
	 */
	public Giocatore(String nome, EColoriGiocatori colore, Risorsa risorse, Plancia plancia, Punti punti,
			SpazioAzione spazioAzione) {
		this.nome = nome;
		this.colore = colore;
		this.risorse = risorse;
		this.plancia = plancia;
		this.punti = punti;
		this.spazioAzione = spazioAzione;
		this.famiglia = new Famigliare[4];
		this.scomuniche = new Scomunica[3];
	}

	public Giocatore() {
		this.nome = null;
		this.colore = null;
		this.famiglia = new Famigliare[4];
		for (int i = 0; i < 4; i++) {
			this.famiglia[i] = new Famigliare(this, 0, false);
		}
		this.famiglia[3].setNeutralita(true);
		this.risorse = new Risorsa(this);
		this.plancia = new Plancia();
		this.punti = new Punti(this);
		this.scomuniche = new Scomunica[3];
		for (int j = 0; j < 3; j++) {
			this.scomuniche[j] = new Scomunica();
		}
		this.spazioAzione = new SpazioAzione();
		// Si potrebbe fare:
		// famiglia = null;
		// risorse = null;
		// plancia = null;
		// punti = null;
		// spazioAzione = null;
		// scomuniche = null;
		//
		// oppure:
		// inizializza(); ??
	}

	/**
	 * Imposta il nome del giocatore (utilizzato per identificare univocamente
	 * il giocatore all'interno del gioco).
	 * 
	 * @param nome
	 *            nickname da assegnare al giocatore.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSpazioAzione(SpazioAzione spazioAzione) {
		this.spazioAzione = spazioAzione;
	}

	/**
	 * Ritorna il nome del giocatore.
	 * 
	 * @return nome del giocatore.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta il colore del giocatore.
	 * 
	 * @param colore
	 *            colore da assegnare al giocatore.
	 */
	public void setColore(EColoriGiocatori colore) {
		this.colore = colore;
	}

	/**
	 * Ritorna il colore associato al giocatore.
	 * 
	 * @return {@link EColoriGiocatori} colore del giocatore.
	 */
	public EColoriGiocatori getColore() {
		return this.colore;
	}

	/**
	 * @return
	 */
	public void pagaServitore(Famigliare famigliare, int valore) throws NoEnoughResourcesException {
		int servitoriDaPagare = valore;
		if (this.scomuniche[1] != null)
			if (this.scomuniche[1].attivaOnPagaServitore())
				servitoriDaPagare = servitoriDaPagare * 2;
		if (this.risorse.getServitori() < servitoriDaPagare)
			throw new NoEnoughResourcesException();
		else {
			this.risorse.cambiaServitori(-servitoriDaPagare);
			famigliare.cambiaValore(valore);
		}
	}

	public Risorsa getRisorse() {
		return this.risorse;
	}

	public Punti getPunti() {
		return this.punti;
	}

	public SpazioAzione getSpazioAzione() {
		return this.spazioAzione;
	}

	public Plancia getPlancia() {
		return this.plancia;
	}

	/**
	 * Metodo che imposta un valore al famigliare indicato dal numero in
	 * ingresso
	 * 
	 * @param
	 * @return
	 */
	public void setValore(int famigliare, int valoreDaCambiare) {
		this.famiglia[famigliare].setValore(valoreDaCambiare);
	}

	/**
	 * Metodo che assegna una scomunica al giocatore. In ingresso vi sono il
	 * periodo della scomunica e la scounica stessa
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void setScomunica(int periodo, Scomunica scomunica) {
		this.scomuniche[periodo] = scomunica;
	}

	/**
	 * Metodo che controlla che tutti i famigliari del giocatore siano stati
	 * posizionati. Ritorna true se sono stati posizionati tutti i famigliari,
	 * false altrimenti
	 * 
	 * @param
	 * @return
	 */
	public boolean checkPosizionato() {
		for (int i = 0; i < 4; i++) {
			if (!(this.famiglia[i].getPosizionato()))
				return false;
		}
		return true;
	}

	/**
	 * Metodo che restituisce il famigliare nell'array all posizione indicata in
	 * ingresso
	 * 
	 */
	public Famigliare getFamigliare(int numeroFamigliare) {
		return this.famiglia[numeroFamigliare];
	}

	/**
	 * Metodo che restituisce la scomunica nell'array alla posizione indicata in
	 * ingresso
	 * 
	 * @param
	 * @return
	 */
	public Scomunica getScomunica(int posizioneScomunica) {
		return this.scomuniche[posizioneScomunica];
	}

	/**
	 * Metodo che implementa il Raccolto
	 * 
	 * @param
	 * @return
	 */
	public void raccolto(int valoreAzione) {
		this.risorse.cambiaLegno(1);
		this.risorse.cambiaPietre(1);
		this.risorse.cambiaServitori(1);
		Carta carta = new Carta(null, null, null, null, 0, 0, null, 0, null, 0) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2805833778341801826L;
		};
		for (int i = 0; i < getPlancia().getTerritori().size(); i++) {
			carta = getPlancia().getTerritori().get(i);
			if (valoreAzione >= carta.getValoreNecessarioEffettoPermanente())
				carta.effettoPermanente(this, null, null, null);
		}
	}

	/**
	 * Metodo che implementa la Produzione
	 * 
	 * @param
	 * @return
	 */
	public void produzione(int valoreAzione, EEffettiPermanenti effettoScelto) {
		this.risorse.cambiaMonete(2);
		this.punti.cambiaPuntiMilitari(1);
		Carta carta = new Carta(null, null, null, null, 0, 0, null, 0, null, 0) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -4625517524205122052L;
		};
		for (int i = 0; i < getPlancia().getEdifici().size(); i++) {
			carta = getPlancia().getEdifici().get(i);
			if (valoreAzione >= carta.getValoreNecessarioEffettoPermanente())
				carta.effettoPermanente(this, null, null, effettoScelto);
		}
	}

	public void calcolaPVFinali() {
		int numeroDiCarte = 0;
		int numeroRisorse = 0;
		// guadagno punti vittoria per le carte territorio

		if (!getScomunica(2).attivaOnTerritoriFinePartita() || getScomunica(2) == null) {
			numeroDiCarte = getPlancia().getTerritori().size();
			if (numeroDiCarte == 3)
				getPunti().cambiaPuntiVittoria(1);
			if (numeroDiCarte == 4)
				getPunti().cambiaPuntiVittoria(4);
			if (numeroDiCarte == 5)
				getPunti().cambiaPuntiVittoria(10);
			if (numeroDiCarte == 6)
				getPunti().cambiaPuntiVittoria(20);
		}
		// guadagno punti vittoria per le carte personaggio

		if (!getScomunica(2).attivaOnPersonaggiFinePartita() || getScomunica(2) == null) {
			numeroDiCarte = getPlancia().getPersonaggi().size();
			for (int j = 1; j <= numeroDiCarte; j++) {
				getPunti().cambiaPuntiVittoria(j);
			}
		}
		// attivo gli effetti delle carte impresa

		if (!getScomunica(2).attivaOnImpreseFinePartita() || getScomunica(2) == null) {
			for (int i = 0; i < getPlancia().getImprese().size(); i++) {
				getPlancia().getImprese().get(i).effettoPermanente(this, null, null, null);
			}
		}
		// ricevo un punto vittoria ogni 5 risorse
		numeroRisorse = getRisorse().getLegno() + getRisorse().getMonete() + getRisorse().getPietre()
				+ getRisorse().getServitori();
		getPunti().cambiaPuntiVittoria((numeroRisorse / 5));

	}
}