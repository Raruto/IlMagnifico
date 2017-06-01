package main.model;

import java.util.*;

import main.util.game.EAzioniGiocatore;
import modelLogicExceptions.*;

/**
 * 
 */
public class Famigliare {

	public Famigliare(Giocatore giocatoreAppartenenza, int valore, boolean neutro) {
		this.giocatoreAppartenenza = giocatoreAppartenenza;
		this.valore = valore;
		this.neutro = neutro;
		this.posizionato = false;
	}

	/**
	 * ?Eil riferimento al giocatore proprietario del famigliare
	 */
	private Giocatore giocatoreAppartenenza;

	/**
	 * ?Eil valore del famigliare
	 */
	private int valore;

	/**
	 * True se il famigliare è già stato usato
	 */
	private boolean posizionato;

	/**
	 * se ?Etrue allora ?Eil famigliare neutro, se false non ?Eneutro
	 */
	private boolean neutro;

	/**
	 * determina la variazione del valore del famigliare
	 * 
	 * @param int
	 * @return
	 */
	public void cambiaValore(int variazione) {
		if (valore + variazione < 0)
			return;
		valore += variazione;
	}

	/**
	 * @param
	 * @return
	 */
	public Giocatore getGiocatore() {
		return this.giocatoreAppartenenza;
	}

	/**
	 * Metodo che effettua lo spostamento su una zona della torre se tutte le
	 * condizioni sono rispettate
	 * 
	 * @param
	 * @return
	 */
	public void eseguiSpostamentoTorre(int posizione) throws FamigliareSpostatoException, SpazioOccupatoException,
			SameTowerException, InvalidPositionException, InsufficientValueException, NoMoneyException,
			NoEnoughResourcesException, MaxCardsReachedException, NullCardException {
		int identificativoTorre = 0;

		if (posizione < 0 | posizione > 15)
			throw new InvalidPositionException();

		if (this.posizionato == true)
			throw new FamigliareSpostatoException();

		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.torreLibera(posizione)))
			throw new SpazioOccupatoException();

		if (spazioAzione.getCartaTorre(posizione) == null)
			throw new NullCardException();

		identificativoTorre = posizione / 4;
		// mi dice esattamente in quale torre si trova la mia pedina:
		// 0=territorio, 1=personaggio
		// 2=edificio, 3=impresa

		controlloSameTower(identificativoTorre, spazioAzione);

		controlloMaxCardsReached(identificativoTorre);

		// creo un famigliare temporaneo su cui fare tutti i calcoli derivanti
		// da effetti
		Famigliare famigliareTemporaneo = clonaFamigliare();

		// Controllo tutti gli effetti permanenti delle carte personaggio e
		// delle scomuniche
		controlloMalusEffettiPermanentiTorre(identificativoTorre, famigliareTemporaneo);
		controlloMalusScomunicheTorre(identificativoTorre, famigliareTemporaneo);

		// controllo che il famigliare abbia un valore sufficiente per l'azione
		if (famigliareTemporaneo.valore < (1 + 2 * (posizione % 4)))
			throw new InsufficientValueException();

		// guardo se c'è un'altra pedina e nel caso pago 3 monete
		controlloDelleTreMonete(identificativoTorre, famigliareTemporaneo, spazioAzione);

		// Applico l'eventuale bonus della torre
		controlloEffettoImmediatoTorre(posizione, famigliareTemporaneo, spazioAzione);

		// controllo le carte personaggio per applicare eventuali sconti
		for (int j = 0; j < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); j++) {
			Personaggio cartaPersonaggio = this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j);
			if (identificativoTorre == 0)
				cartaPersonaggio.attivaOnAzione(famigliareTemporaneo.giocatoreAppartenenza,
						EAzioniGiocatore.PagaTerritorio, null, spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 1)
				cartaPersonaggio.attivaOnAzione(famigliareTemporaneo.giocatoreAppartenenza,
						EAzioniGiocatore.PagaPersonaggio, null, spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 2)
				cartaPersonaggio.attivaOnAzione(famigliareTemporaneo.giocatoreAppartenenza,
						EAzioniGiocatore.PagaEdificio, null, spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 3)
				cartaPersonaggio.attivaOnAzione(famigliareTemporaneo.giocatoreAppartenenza,
						EAzioniGiocatore.PagaImpresa, null, spazioAzione.getCartaTorre(posizione));
		}

		// controllo se posso pagare la carta. Se sì, effettuo il pagamento,
		// prendo la carta, eseguo l'effetto immediato e posiziono il famigliare
		if (!spazioAzione.getCartaTorre(posizione).acquisibile(famigliareTemporaneo.giocatoreAppartenenza))
			throw new NoEnoughResourcesException();
		else {
			spazioAzione.getCartaTorre(posizione).acquisizione(famigliareTemporaneo.giocatoreAppartenenza);

			// devo applicare tutte le modifiche al mio giocatore di partenza
			this.giocatoreAppartenenza.getPunti()
					.setPuntiFede(famigliareTemporaneo.giocatoreAppartenenza.getPunti().getPuntiFede());
			this.giocatoreAppartenenza.getPunti()
					.setPuntiMilitari(famigliareTemporaneo.giocatoreAppartenenza.getPunti().getPuntiMilitari());
			this.giocatoreAppartenenza.getPunti()
					.setPuntiVittoria(famigliareTemporaneo.giocatoreAppartenenza.getPunti().getPuntiVittoria());
			this.giocatoreAppartenenza.getRisorse()
					.setLegno(famigliareTemporaneo.giocatoreAppartenenza.getRisorse().getLegno());
			this.giocatoreAppartenenza.getRisorse()
					.setMonete(famigliareTemporaneo.giocatoreAppartenenza.getRisorse().getMonete());
			this.giocatoreAppartenenza.getRisorse()
					.setPietre(famigliareTemporaneo.giocatoreAppartenenza.getRisorse().getPietre());
			this.giocatoreAppartenenza.getRisorse()
					.setServitori(famigliareTemporaneo.giocatoreAppartenenza.getRisorse().getServitori());

			if (identificativoTorre == 0) {
				this.giocatoreAppartenenza.getPlancia()
						.aggiungiTerritorio((Territorio) (spazioAzione.getCartaTorre(posizione)));
				spazioAzione.setCartaTorre(null, posizione);
				this.giocatoreAppartenenza.getPlancia().getTerritori().get(0).effettoImmediato(giocatoreAppartenenza,
						this, null);
			}
			if (identificativoTorre == 1) {
				this.giocatoreAppartenenza.getPlancia()
						.aggiungiPersonaggio((Personaggio) (spazioAzione.getCartaTorre(posizione)));
				spazioAzione.setCartaTorre(null, posizione);
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(0).effettoImmediato(giocatoreAppartenenza,
						this, null);
			}
			if (identificativoTorre == 2) {
				this.giocatoreAppartenenza.getPlancia()
						.aggiungiEdificio((Edificio) (spazioAzione.getCartaTorre(posizione)));
				spazioAzione.setCartaTorre(null, posizione);
				this.giocatoreAppartenenza.getPlancia().getEdifici().get(0).effettoImmediato(giocatoreAppartenenza,
						this, null);
			}
			if (identificativoTorre == 3) {
				this.giocatoreAppartenenza.getPlancia()
						.aggiungiImpresa((Impresa) (spazioAzione.getCartaTorre(posizione)));
				spazioAzione.setCartaTorre(null, posizione);
				this.giocatoreAppartenenza.getPlancia().getImprese().get(0).effettoImmediato(giocatoreAppartenenza,
						this, null);
			}
			spazioAzione.setFamigliareTorre(this, posizione);
		}
	}

	/**
	 * Metodo che controlla che sulla stessa torre non ci siano due famigliari
	 * dello stesso colore
	 * 
	 * @param
	 * @return
	 */
	public void controlloSameTower(int identificativoTorre, SpazioAzione spazioAzione) throws SameTowerException {
		for (int i = 4 * identificativoTorre; i < identificativoTorre + 4; i++) {
			// controllo che il giocatore sulla stessa torre non abbia due
			// famigliari colorati
			if ((spazioAzione.getFamigliareTorre(i).getGiocatore() == this.giocatoreAppartenenza)
					&& (spazioAzione.getFamigliareTorre(i).getNeutralita() == false) && (this.neutro == false))
				throw new SameTowerException();
		}
	}

	/**
	 * Metodo che controlla che un giocatore non abbia già il massimo numero di
	 * carte del tipo della carta che vuole prendere
	 * 
	 * @param
	 * @return
	 */
	public void controlloMaxCardsReached(int identificativoTorre) throws MaxCardsReachedException {
		if (identificativoTorre == 0 && this.giocatoreAppartenenza.getPlancia().getTerritori().size() == 6)
			throw new MaxCardsReachedException();
		if (identificativoTorre == 1 && this.giocatoreAppartenenza.getPlancia().getPersonaggi().size() == 6)
			throw new MaxCardsReachedException();
		if (identificativoTorre == 2 && this.giocatoreAppartenenza.getPlancia().getEdifici().size() == 6)
			throw new MaxCardsReachedException();
		if (identificativoTorre == 3 && this.giocatoreAppartenenza.getPlancia().getImprese().size() == 6)
			throw new MaxCardsReachedException();
	}

	/**
	 * Metodo che crea una copia del famigliare
	 * 
	 * @param
	 * @return
	 */
	public Famigliare clonaFamigliare() {
		Famigliare famigliareTemporaneo = new Famigliare(new Giocatore(), this.valore, this.neutro);
		famigliareTemporaneo.getGiocatore().getRisorse().setLegno(this.giocatoreAppartenenza.getRisorse().getLegno());
		famigliareTemporaneo.getGiocatore().getRisorse().setPietre(this.giocatoreAppartenenza.getRisorse().getPietre());
		famigliareTemporaneo.getGiocatore().getRisorse().setMonete(this.giocatoreAppartenenza.getRisorse().getMonete());
		famigliareTemporaneo.getGiocatore().getRisorse()
				.setServitori(this.giocatoreAppartenenza.getRisorse().getServitori());
		famigliareTemporaneo.getGiocatore().getPunti()
				.setPuntiMilitari(this.giocatoreAppartenenza.getPunti().getPuntiMilitari());
		famigliareTemporaneo.getGiocatore().getPunti()
				.setPuntiFede(this.giocatoreAppartenenza.getPunti().getPuntiFede());
		famigliareTemporaneo.getGiocatore().getPunti()
				.setPuntiVittoria(this.giocatoreAppartenenza.getPunti().getPuntiVittoria());
		famigliareTemporaneo.getGiocatore().setColore(this.giocatoreAppartenenza.getColore());
		return famigliareTemporaneo;
	}

	/**
	 * Metodo che controlla i malus derivanti da effetti permanenti delle carte
	 * quando eseguo uno spostamento su una torre
	 * 
	 * @param
	 * @return
	 */
	public void controlloMalusEffettiPermanentiTorre(int identificativoTorre, Famigliare famigliare) {
		for (int j = 0; j < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); j++) {
			if (identificativoTorre == 0)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j).attivaOnAzione(null,
						EAzioniGiocatore.PrendiTerritorio, famigliare, null);
			if (identificativoTorre == 1)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j).attivaOnAzione(null,
						EAzioniGiocatore.PrendiPersonaggio, famigliare, null);
			if (identificativoTorre == 2)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j).attivaOnAzione(null,
						EAzioniGiocatore.PrendiEdificio, famigliare, null);
			if (identificativoTorre == 3)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j).attivaOnAzione(null,
						EAzioniGiocatore.PrendiImpresa, famigliare, null);
		}
	}

	/**
	 * Metodo che controlla i malus derivanti dalle tessere scomunica in
	 * possesso del giocatore del famigliare
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void controlloMalusScomunicheTorre(int identificativoTorre, Famigliare famigliare) {
		if ((famigliare.neutro == false) && (this.giocatoreAppartenenza.getScomunica(0) != null))
			this.giocatoreAppartenenza.getScomunica(0).attivaOnAzione(null, EAzioniGiocatore.MuoviColorato, famigliare,
					null);

		if (this.giocatoreAppartenenza.getScomunica(1) != null) {
			if (identificativoTorre == 0)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGiocatore.PrendiTerritorio,
						famigliare, null);
			if (identificativoTorre == 1)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGiocatore.PrendiPersonaggio,
						famigliare, null);
			if (identificativoTorre == 2)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGiocatore.PrendiEdificio,
						famigliare, null);
			if (identificativoTorre == 3)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGiocatore.PrendiImpresa,
						famigliare, null);
		}
	}

	/**
	 * Metodo che controlla che il giocatore abbia le tre monete da pagare nel
	 * caso in cui nella stessa torre sia presente un altro famigliare
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void controlloDelleTreMonete(int identificativoTorre, Famigliare famigliareTemporaneo,
			SpazioAzione spazioAzione) throws NoMoneyException {
		for (int i = 4 * identificativoTorre; i < identificativoTorre + 4; i++) {
			if (spazioAzione.torreLibera(i) == false) {
				if (famigliareTemporaneo.giocatoreAppartenenza.getRisorse().getMonete() < 3)
					throw new NoMoneyException();
				else {
					famigliareTemporaneo.giocatoreAppartenenza.getRisorse().cambiaMonete(-3);
					break;
				}
			}
		}
	}

	/**
	 * Metodo che controlla che sulla posizione dove viene posizionato il
	 * famigliare ci sia un bonus. Se c'è lo attiva
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void controlloEffettoImmediatoTorre(int posizione, Famigliare famigliareTemporaneo,
			SpazioAzione spazioAzione) {
		boolean controllo = false;
		if (posizione % 4 == 3 | posizione % 4 == 2) {
			for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
				if (this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(i).attivaOnEffettoTorre())
					controllo = true;
			}
			if (controllo == false)
				spazioAzione.eseguiEffettoImmediatoTorre(famigliareTemporaneo.giocatoreAppartenenza, posizione);
		}
	}

	/**
	 * Metodo che effettua lo spostamento sulla zona del raccolto rotonda se le
	 * condizioni sono rispettate. Restituisce true se va a buon fine, false se
	 * le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoRaccoltoRotondo() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.zonaRaccoltoRotondaLibera()))
			return false;
		if (valore < 1)
			return false;

		for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(i).attivaOnRaccolto(this.giocatoreAppartenenza);
		}
		Raccolto(this.giocatoreAppartenenza, valore);

		spazioAzione.setZonaRaccoltoRotonda(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento sulla zona di raccolto ovale se le
	 * condizioni sono rispettate. Restituisce true se va a buon fine, flase se
	 * le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoRaccoltoOvale() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (valore - 3 < 1)
			return false;

		for (int i = 0; i < spazioAzione.getZonaRaccoltoOvale().size(); i++) {
			if ((spazioAzione.getZonaRaccoltoOvale().get(i).getGiocatore() == giocatoreAppartenenza)
					&& (spazioAzione.getZonaRaccoltoOvale().get(i).getNeutralita() == false))
				return false;
		}

		valore -= 3;

		for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(i).attivaOnRaccolto(this.giocatoreAppartenenza);
		}
		Raccolto(this.giocatoreAppartenenza, valore);

		spazioAzione.getZonaRaccoltoOvale().add(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento del famigliare sulla zona di produzione
	 * rotonda se le condizioni sono rispettate. Restituisce true se va a buon
	 * fine, false se le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoProduzioneRotondo() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.zonaProduzioneRotondaLibera()))
			return false;
		if (valore < 1)
			return false;

		for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(i)
					.attivaOnProduzione(this.giocatoreAppartenenza);
		}
		Produzione(this.giocatoreAppartenenza, this.valore);

		spazioAzione.setZonaProduzioneRotonda(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento nella zona di produzione ovale Se le
	 * condizioni sono rispettate. Restituisce true se va a buon fine, false se
	 * le condizioni non sono rispettate
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoProduzioneOvale() {
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (valore - 3 < 1)
			return false;

		for (int i = 0; i < spazioAzione.getZonaProduzioneOvale().size(); i++) {
			if ((spazioAzione.getZonaProduzioneOvale().get(i).getGiocatore() == giocatoreAppartenenza)
					&& (spazioAzione.getZonaProduzioneOvale().get(i).getNeutralita() == false))
				return false;
		}

		valore -= 3;
		for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(i)
					.attivaOnProduzione(this.giocatoreAppartenenza);
		}
		Produzione(this.giocatoreAppartenenza, valore);

		spazioAzione.getZonaProduzioneOvale().add(this);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento nella zona del mercato se le condizioni
	 * sono rispettate. Restituisce true se va a buon fine, false altrimenti
	 * 
	 * @param
	 * @return
	 */
	public boolean eseguiSpostamentoMercato(int posizione) {
		if (posizione < 0 || posizione > 3)
			return false;
		if (valore < 1)
			return false;
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		if (!spazioAzione.zonaMercatoLibera(posizione))
			return false;
		spazioAzione.getMercato()[posizione] = this;
		spazioAzione.eseguiEffettoMercato(giocatoreAppartenenza, posizione);
		return true;
	}

	/**
	 * Metodo che esegue lo spostamento nella zona del palazzo del consiglio se
	 * le condizioni sono rispettate. Restituisce true se va a buon fine, false
	 * altrimenti
	 * 
	 * @return
	 */
	public boolean eseguiSpostamentoPalazzoConsiglio() {

		if (valore < 1)
			return false;

		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		spazioAzione.setPalazzoDelConsiglio(this);
		spazioAzione.eseguiEffettoPalazzoConsiglio(giocatoreAppartenenza);
		return true;
	}

	public boolean getNeutralita() {
		return this.neutro;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public boolean getPosizionato() {
		return this.posizionato;
	}

	/**
	 * 
	 * */
	public void Raccolto(Giocatore giocatore, int valore) {
		for (int i = 0; i < giocatore.getPlancia().getTerritori().size(); i++) {
			if (giocatore.getPlancia().getTerritori().get(i).Attivabile(valore))
				giocatore.getPlancia().getTerritori().get(i).effettoPermanente(giocatore);
		}
	}

	/**
	 * 
	 * */
	public void Produzione(Giocatore giocatore, int valore) {
		for (int i = 0; i < giocatore.getPlancia().getEdifici().size(); i++) {
			if (giocatore.getPlancia().getEdifici().get(i).Attivabile(valore))
				giocatore.getPlancia().getEdifici().get(i).effettoPermanente(giocatore);
		}
	}

	public void setNeutralita(boolean neutro) {
		this.neutro = neutro;
	}
}