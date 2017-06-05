package main.model;

import java.io.Serializable;
import java.util.*;

import main.model.enums.EAzioniGioco;
import main.model.exceptions.*;

/**
 * 
 */
public class Famigliare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7874494906323451581L;

	public Famigliare(Giocatore giocatoreAppartenenza, int valore, boolean neutro) {
		this.giocatoreAppartenenza = giocatoreAppartenenza;
		this.valore = valore;
		this.neutro = neutro;
		this.posizionato = false;
	}

	/**
	 * Riferimento al giocatore proprietario del famigliare
	 */
	private Giocatore giocatoreAppartenenza;

	/**
	 * Valore del famigliare
	 */
	private int valore;

	/**
	 * True se il famigliare e' gia' stato usato
	 */
	private boolean posizionato;

	/**
	 * se True allora il famigliare e' neutro, se false non e' neutro
	 */
	private boolean neutro;

	/**
	 * determina la variazione del valore del famigliare
	 * 
	 * @param int
	 * @return
	 */
	public void cambiaValore(int variazione) {
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
			SameAreaException, InvalidPositionException, InsufficientValueException, NoMoneyException,
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

		// guardo se c'e' un'altra pedina e nel caso pago 3 monete
		controlloAltroFamigliareNellaTorre(identificativoTorre, famigliareTemporaneo, spazioAzione);

		// Applico l'eventuale bonus della torre
		controlloEffettoImmediatoTorre(posizione, famigliareTemporaneo, spazioAzione);

		// controllo le carte personaggio per applicare eventuali sconti
		controlloScontiEffettoPermanente(identificativoTorre, famigliareTemporaneo, spazioAzione, posizione);

		// controllo se posso pagare la carta. Se posso, effettuo il pagamento,
		// prendo la carta, eseguo l'effetto immediato e posiziono il famigliare
		if (!spazioAzione.getCartaTorre(posizione).acquisibile(famigliareTemporaneo.giocatoreAppartenenza))
			throw new NoEnoughResourcesException();
		else {
			spazioAzione.getCartaTorre(posizione).acquisizione(famigliareTemporaneo.giocatoreAppartenenza);

			// devo applicare tutte le modifiche al mio giocatore di partenza
			mergeFamigliari(famigliareTemporaneo);

			prendiCartaDallaTorre(identificativoTorre, spazioAzione, posizione);
			spazioAzione.setFamigliareTorre(this, posizione);
			this.posizionato = true;
		}
	}

	/**
	 * Metodo che controlla che sulla stessa torre non ci siano due famigliari
	 * dello stesso colore
	 * 
	 * @param
	 * @return
	 */
	public void controlloSameTower(int identificativoTorre, SpazioAzione spazioAzione) throws SameAreaException {
		for (int i = 4 * identificativoTorre; i < identificativoTorre + 4; i++) {
			// controllo che il giocatore sulla stessa torre non abbia due
			// famigliari colorati
			if ((spazioAzione.getFamigliareTorre(i).getGiocatore() == this.giocatoreAppartenenza)
					&& (spazioAzione.getFamigliareTorre(i).getNeutralita() == false) && (this.neutro == false))
				throw new SameAreaException();
		}
	}

	/**
	 * Metodo che controlla che un giocatore non abbia gia' il massimo numero di
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
	 * Metodo che controlla i malus e i bonus derivanti da effetti permanenti
	 * delle carte quando eseguo uno spostamento su una torre
	 * 
	 * @param
	 * @return
	 */
	public void controlloMalusEffettiPermanentiTorre(int identificativoTorre, Famigliare famigliare) {

		if (identificativoTorre == 0)
			controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.PrendiTerritorio);
		if (identificativoTorre == 1)
			controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.PrendiPersonaggio);
		if (identificativoTorre == 2)
			controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.PrendiEdificio);
		if (identificativoTorre == 3)
			controlloEffettiPermanentiOnFamigliare(famigliare, EAzioniGioco.PrendiImpresa);

	}

	/**
	 * Metodo che scorre le carte personaggio del giocatore ed applica gli
	 * effetti che modificano il valore del famigliare quando viene eseguita una
	 * determinata azione
	 * 
	 * @param
	 * @return
	 */
	public void controlloEffettiPermanentiOnFamigliare(Famigliare famigliare, EAzioniGioco azione) {
		for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
			this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(i).attivaOnAzione(null, azione, famigliare,
					null);
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
			this.giocatoreAppartenenza.getScomunica(0).attivaOnAzione(null, EAzioniGioco.MuoviColorato, famigliare,
					null);

		if (this.giocatoreAppartenenza.getScomunica(1) != null) {
			if (identificativoTorre == 0)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGioco.PrendiTerritorio,
						famigliare, null);
			if (identificativoTorre == 1)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGioco.PrendiPersonaggio,
						famigliare, null);
			if (identificativoTorre == 2)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGioco.PrendiEdificio, famigliare,
						null);
			if (identificativoTorre == 3)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnAzione(null, EAzioniGioco.PrendiImpresa, famigliare,
						null);
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
	public void controlloAltroFamigliareNellaTorre(int identificativoTorre, Famigliare famigliare,
			SpazioAzione spazioAzione) throws NoMoneyException {
		for (int i = 4 * identificativoTorre; i < identificativoTorre + 4; i++) {
			if (spazioAzione.torreLibera(i) == false) {
				if (famigliare.giocatoreAppartenenza.getRisorse().getMonete() < 3)
					throw new NoMoneyException();
				else {
					famigliare.giocatoreAppartenenza.getRisorse().cambiaMonete(-3);
					break;
				}
			}
		}
	}

	/**
	 * Metodo che controlla che sulla posizione dove viene posizionato il
	 * famigliare ci sia un bonus. Se c'e' e si puo' attivare lo attiva
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
	 * Metodo che controlla se ci sono degli effetti permanenti che danno sconto
	 * sulla carta che si vuole prendere. Se ci sono, li applica.
	 * 
	 * @param
	 * @return
	 */
	public void controlloScontiEffettoPermanente(int identificativoTorre, Famigliare famigliare,
			SpazioAzione spazioAzione, int posizione) {
		for (int j = 0; j < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); j++) {
			Personaggio cartaPersonaggio = this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j);
			if (identificativoTorre == 0)
				cartaPersonaggio.attivaOnAzione(famigliare.giocatoreAppartenenza, EAzioniGioco.PagaTerritorio, null,
						spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 1)
				cartaPersonaggio.attivaOnAzione(famigliare.giocatoreAppartenenza, EAzioniGioco.PagaPersonaggio, null,
						spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 2)
				cartaPersonaggio.attivaOnAzione(famigliare.giocatoreAppartenenza, EAzioniGioco.PagaEdificio, null,
						spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 3)
				cartaPersonaggio.attivaOnAzione(famigliare.giocatoreAppartenenza, EAzioniGioco.PagaImpresa, null,
						spazioAzione.getCartaTorre(posizione));
		}
	}

	/**
	 * Metodo che appplica al famigliare corrente le modifiche che sono state
	 * fatte al clone del famigliare.
	 * 
	 * @param
	 * @return
	 */
	public void mergeFamigliari(Famigliare cloneFamigliare) {
		this.giocatoreAppartenenza.getPunti()
				.setPuntiFede(cloneFamigliare.giocatoreAppartenenza.getPunti().getPuntiFede());
		this.giocatoreAppartenenza.getPunti()
				.setPuntiMilitari(cloneFamigliare.giocatoreAppartenenza.getPunti().getPuntiMilitari());
		this.giocatoreAppartenenza.getPunti()
				.setPuntiVittoria(cloneFamigliare.giocatoreAppartenenza.getPunti().getPuntiVittoria());
		this.giocatoreAppartenenza.getRisorse().setLegno(cloneFamigliare.giocatoreAppartenenza.getRisorse().getLegno());
		this.giocatoreAppartenenza.getRisorse()
				.setMonete(cloneFamigliare.giocatoreAppartenenza.getRisorse().getMonete());
		this.giocatoreAppartenenza.getRisorse()
				.setPietre(cloneFamigliare.giocatoreAppartenenza.getRisorse().getPietre());
		this.giocatoreAppartenenza.getRisorse()
				.setServitori(cloneFamigliare.giocatoreAppartenenza.getRisorse().getServitori());
		this.valore = cloneFamigliare.valore;
	}

	/**
	 * Metodo che prende una carta nella torre alla posizione indicata e la
	 * inserisce nella plancia del giocatore.
	 * 
	 * @param
	 * @return
	 */
	public void prendiCartaDallaTorre(int identificativoTorre, SpazioAzione spazioAzione, int posizione) {
		if (identificativoTorre == 0) {
			this.giocatoreAppartenenza.getPlancia()
					.aggiungiTerritorio((Territorio) (spazioAzione.getCartaTorre(posizione)));
			spazioAzione.setCartaTorre(null, posizione);
			this.giocatoreAppartenenza.getPlancia().getTerritori().get(0).effettoImmediato(giocatoreAppartenenza, this,
					null);
		}
		if (identificativoTorre == 1) {
			this.giocatoreAppartenenza.getPlancia()
					.aggiungiPersonaggio((Personaggio) (spazioAzione.getCartaTorre(posizione)));
			spazioAzione.setCartaTorre(null, posizione);
			this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(0).effettoImmediato(giocatoreAppartenenza, this,
					null);
		}
		if (identificativoTorre == 2) {
			this.giocatoreAppartenenza.getPlancia()
					.aggiungiEdificio((Edificio) (spazioAzione.getCartaTorre(posizione)));
			spazioAzione.setCartaTorre(null, posizione);
			this.giocatoreAppartenenza.getPlancia().getEdifici().get(0).effettoImmediato(giocatoreAppartenenza, this,
					null);
		}
		if (identificativoTorre == 3) {
			this.giocatoreAppartenenza.getPlancia().aggiungiImpresa((Impresa) (spazioAzione.getCartaTorre(posizione)));
			spazioAzione.setCartaTorre(null, posizione);
			this.giocatoreAppartenenza.getPlancia().getImprese().get(0).effettoImmediato(giocatoreAppartenenza, this,
					null);
		}
	}

	/**
	 * Metodo che effettua lo spostamento sulla zona del raccolto rotonda se le
	 * condizioni sono rispettate.
	 * 
	 * @return
	 */
	public void eseguiSpostamentoRaccoltoRotondo()
			throws SpazioOccupatoException, InsufficientValueException, FamigliareSpostatoException {
		if (this.posizionato == true)
			throw new FamigliareSpostatoException();

		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		// controllo se l'area e' occupata
		if (!(spazioAzione.zonaRaccoltoRotondaLibera()))
			throw new SpazioOccupatoException();

		// creo un clone del mio famigliare
		Famigliare famigliareTemporaneo = clonaFamigliare();

		// applico gli effetti permanenti delle carte e gli effetti delle
		// scomuniche
		controlloEffettiPermanentiOnFamigliare(famigliareTemporaneo, EAzioniGioco.Raccolto);

		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			this.giocatoreAppartenenza.getScomunica(0).attivaOnAzione(null, EAzioniGioco.Raccolto, famigliareTemporaneo,
					null);

		// guardo se ho abbastanza valore con la pedina
		if (famigliareTemporaneo.valore < 1)
			throw new InsufficientValueException();
		else {
			mergeFamigliari(famigliareTemporaneo);
			spazioAzione.setZonaRaccoltoRotonda(this);
			this.posizionato = true;
			this.giocatoreAppartenenza.raccolto(this.valore);
		}
	}

	/**
	 * Metodo che esegue lo spostamento sulla zona di raccolto ovale se le
	 * condizioni sono rispettate.
	 * 
	 * @return
	 */
	public void eseguiSpostamentoRaccoltoOvale()
			throws FamigliareSpostatoException, SameAreaException, InsufficientValueException {
		if (this.posizionato == true)
			throw new FamigliareSpostatoException();
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		Famigliare famigliareTemporaneo = new Famigliare(null, 0, false);
		// controllo che non ci sia un famigliare dello stesso colore
		for (int i = 0; i < spazioAzione.getZonaRaccoltoOvale().size(); i++) {
			famigliareTemporaneo = spazioAzione.getZonaRaccoltoOvale().get(i);
			if (famigliareTemporaneo.giocatoreAppartenenza == this.giocatoreAppartenenza
					&& famigliareTemporaneo.getNeutralita() == false && this.neutro == false)
				throw new SameAreaException();
		}

		famigliareTemporaneo = clonaFamigliare();

		// applico gli effetti permanenti delle carte e gli effetti delle
		// scomuniche
		controlloEffettiPermanentiOnFamigliare(famigliareTemporaneo, EAzioniGioco.Raccolto);
		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			this.giocatoreAppartenenza.getScomunica(0).attivaOnAzione(null, EAzioniGioco.Raccolto, famigliareTemporaneo,
					null);

		// applico il malus della zona ovale
		famigliareTemporaneo.cambiaValore(-3);
		// guardo se il famigliare ha abbastanza valore
		if (famigliareTemporaneo.valore < 1)
			throw new InsufficientValueException();
		else {
			mergeFamigliari(famigliareTemporaneo);
			spazioAzione.setZonaRaccoltoOvale(this);
			this.posizionato = true;
			this.giocatoreAppartenenza.raccolto(this.valore);
		}

	}

	/**
	 * Metodo che esegue lo spostamento del famigliare sulla zona di produzione
	 * rotonda se le condizioni sono rispettate.
	 * 
	 * @return
	 */
	public void eseguiSpostamentoProduzioneRotondo()
			throws SpazioOccupatoException, InsufficientValueException, FamigliareSpostatoException {
		if (this.posizionato == true)
			throw new FamigliareSpostatoException();

		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		// controllo se l'area e' occupata
		if (!(spazioAzione.zonaProduzioneRotondaLibera()))
			throw new SpazioOccupatoException();

		// creo un clone del mio famigliare
		Famigliare famigliareTemporaneo = clonaFamigliare();

		// applico gli effetti permanenti delle carte e gli effetti delle
		// scomuniche
		controlloEffettiPermanentiOnFamigliare(famigliareTemporaneo, EAzioniGioco.Produzione);

		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			this.giocatoreAppartenenza.getScomunica(0).attivaOnAzione(null, EAzioniGioco.Produzione,
					famigliareTemporaneo, null);

		// guardo se ho abbastanza valore con la pedina
		if (famigliareTemporaneo.valore < 1)
			throw new InsufficientValueException();
		else {
			mergeFamigliari(famigliareTemporaneo);
			spazioAzione.setZonaProduzioneRotonda(this);
			this.posizionato = true;
			this.giocatoreAppartenenza.produzione(this.valore);
		}
	}

	/**
	 * Metodo che esegue lo spostamento nella zona di produzione ovale Se le
	 * condizioni sono rispettate.
	 * 
	 * @return
	 */
	public void eseguiSpostamentoProduzioneOvale()
			throws FamigliareSpostatoException, SameAreaException, InsufficientValueException {
		if (this.posizionato == true)
			throw new FamigliareSpostatoException();
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		Famigliare famigliareTemporaneo = new Famigliare(null, 0, false);
		// controllo che non ci sia un famigliare dello stesso colore
		for (int i = 0; i < spazioAzione.getZonaProduzioneOvale().size(); i++) {
			famigliareTemporaneo = spazioAzione.getZonaProduzioneOvale().get(i);
			if (famigliareTemporaneo.giocatoreAppartenenza == this.giocatoreAppartenenza
					&& famigliareTemporaneo.getNeutralita() == false && this.neutro == false)
				throw new SameAreaException();
		}

		famigliareTemporaneo = clonaFamigliare();

		// applico gli effetti permanenti delle carte e gli effetti delle
		// scomuniche
		controlloEffettiPermanentiOnFamigliare(famigliareTemporaneo, EAzioniGioco.Produzione);
		if (this.giocatoreAppartenenza.getScomunica(0) != null)
			this.giocatoreAppartenenza.getScomunica(0).attivaOnAzione(null, EAzioniGioco.Produzione,
					famigliareTemporaneo, null);

		// applico il malus della zona ovale
		famigliareTemporaneo.cambiaValore(-3);
		// guardo se il famigliare ha abbastanza valore
		if (famigliareTemporaneo.valore < 1)
			throw new InsufficientValueException();
		else {
			mergeFamigliari(famigliareTemporaneo);
			spazioAzione.setZonaProduzioneOvale(this);
			this.posizionato = true;
			this.giocatoreAppartenenza.produzione(this.valore);

		}
	}

	/**
	 * Metodo che esegue lo spostamento nella zona del mercato se le condizioni
	 * sono rispettate.
	 * 
	 * @param
	 * @return
	 */
	public void eseguiSpostamentoMercato(int posizione) throws InvalidPositionException, FamigliareSpostatoException,
			SpazioOccupatoException, MarketNotAvailableException, InsufficientValueException {
		if (posizione < 0 || posizione > 3)
			throw new InvalidPositionException();

		if (this.posizionato == true)
			throw new FamigliareSpostatoException();

		SpazioAzione spazioAzione = this.giocatoreAppartenenza.getSpazioAzione();
		if (spazioAzione.zonaMercatoLibera(posizione) == false)
			throw new SpazioOccupatoException();

		if (this.giocatoreAppartenenza.getScomunica(1) != null)
			if (this.giocatoreAppartenenza.getScomunica(1).attivaOnMercato())
				throw new MarketNotAvailableException();

		if (valore < 1)
			throw new InsufficientValueException();

		spazioAzione.setMercato(this, posizione);
		this.posizionato = true;
		spazioAzione.eseguiEffettoMercato(giocatoreAppartenenza, posizione);
	}

	/**
	 * Metodo che esegue lo spostamento nella zona del palazzo del consiglio se
	 * le condizioni sono rispettate.
	 * 
	 * @return
	 */
	public void eseguiSpostamentoPalazzoConsiglio() throws FamigliareSpostatoException, InsufficientValueException {

		if (this.posizionato == true)
			throw new FamigliareSpostatoException();

		if (valore < 1)
			throw new InsufficientValueException();

		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();
		spazioAzione.setPalazzoDelConsiglio(this);
		this.posizionato = true;
		spazioAzione.eseguiEffettoPalazzoConsiglio(giocatoreAppartenenza);
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

	public void setNeutralita(boolean neutro) {
		this.neutro = neutro;
	}

	public void setPosizionato(boolean posizionato) {
		this.posizionato = posizionato;
	}

	public int getValore() {
		return this.valore;
	}
}