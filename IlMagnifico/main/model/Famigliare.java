package main.model;

import java.util.*;
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
	 * Metodo che effettua lo spostamento se la carta è acquisibile e se il
	 * valore del famigliare è sufficiente. Restituuisce true se va a buon fine,
	 * false altrimenti
	 * 
	 * @param
	 * @return
	 */
	public void eseguiSpostamentoTorre(int posizione)
			throws FamigliareSpostatoException, SpazioOccupatoException, SameTowerException, InvalidPositionException,
			InsufficientValueException, NoMoneyException, NoEnoughResourcesException, MaxCardsReachedException {
		int identificativoTorre = 0;

		if (posizione < 0 | posizione > 15)
			throw new InvalidPositionException();

		if (this.posizionato == true)
			throw new FamigliareSpostatoException();
		SpazioAzione spazioAzione = giocatoreAppartenenza.getSpazioAzione();

		if (!(spazioAzione.torreLibera(posizione)))
			throw new SpazioOccupatoException();

		identificativoTorre = posizione / 4;
		// mi dice esattamente in quale torre si trova la mia pedina:
		// 0=territorio, 1=personaggio
		// 2=edificio, 3=impresa

		for (int i = 4 * identificativoTorre; i < identificativoTorre + 4; i++) {
			// controllo che il giocatore sulla stessa torre non abbia due
			// famigliari colorati
			if ((spazioAzione.getFamigliareTorre(i).getGiocatore() == this.giocatoreAppartenenza)
					&& (spazioAzione.getFamigliareTorre(i).getNeutralita() == false) && (this.neutro == false))
				throw new SameTowerException();
		}

		if (identificativoTorre == 0 && this.giocatoreAppartenenza.getPlancia().getTerritori().size() == 6)
			throw new MaxCardsReachedException();
		if (identificativoTorre == 1 && this.giocatoreAppartenenza.getPlancia().getPersonaggi().size() == 6)
			throw new MaxCardsReachedException();

		if (identificativoTorre == 2 && this.giocatoreAppartenenza.getPlancia().getEdifici().size() == 6)
			throw new MaxCardsReachedException();

		if (identificativoTorre == 3 && this.giocatoreAppartenenza.getPlancia().getImprese().size() == 6)
			throw new MaxCardsReachedException();

		// creo un famigliare temporaneo su cui fare tutti i calcoli derivanti
		// da effetti
		Famigliare famigliareTemporaneo = new Famigliare(new Giocatore(), this.valore, this.neutro);
		famigliareTemporaneo.getGiocatore().getRisorse()
				.cambiaLegno(this.giocatoreAppartenenza.getRisorse().getLegno());
		famigliareTemporaneo.getGiocatore().getRisorse()
				.cambiaPietre(this.giocatoreAppartenenza.getRisorse().getPietre());
		famigliareTemporaneo.getGiocatore().getRisorse()
				.cambiaMonete(this.giocatoreAppartenenza.getRisorse().getMonete());
		famigliareTemporaneo.getGiocatore().getRisorse()
				.cambiaServitori(this.giocatoreAppartenenza.getRisorse().getServitori());
		famigliareTemporaneo.getGiocatore().getPunti()
				.cambiaPuntiMilitari(this.giocatoreAppartenenza.getPunti().getPuntiMilitari());
		famigliareTemporaneo.getGiocatore().getPunti()
				.cambiaPuntiFede(this.giocatoreAppartenenza.getPunti().getPuntiFede());
		famigliareTemporaneo.getGiocatore().getPunti()
				.cambiaPuntiVittoria(this.giocatoreAppartenenza.getPunti().getPuntiVittoria());
		famigliareTemporaneo.getGiocatore().setColore(this.giocatoreAppartenenza.getColore());

		// Controllo tutti gli effetti permanenti delle carte personaggio e
		// delle scomuniche
		for (int j = 0; j < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); j++) {
			if (identificativoTorre == 0)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j)
						.attivaOnPrendiTerritorio(famigliareTemporaneo);
			if (identificativoTorre == 1)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j)
						.attivaOnPrendiPersonaggio(famigliareTemporaneo);
			if (identificativoTorre == 2)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j)
						.attivaOnPrendiEdificio(famigliareTemporaneo);
			if (identificativoTorre == 3)
				this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j)
						.attivaOnPrendiImpresa(famigliareTemporaneo);
		}

		if ((famigliareTemporaneo.neutro == false) && (this.giocatoreAppartenenza.getScomunica(0) != null))
			this.giocatoreAppartenenza.getScomunica(0).attivaOnMuoviColorato(famigliareTemporaneo);

		if (this.giocatoreAppartenenza.getScomunica(1) != null) {
			if (identificativoTorre == 0)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnPrendiTerritorio(famigliareTemporaneo);
			if (identificativoTorre == 1)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnPrendiPersonaggio(famigliareTemporaneo);
			if (identificativoTorre == 2)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnPrendiEdificio(famigliareTemporaneo);
			if (identificativoTorre == 3)
				this.giocatoreAppartenenza.getScomunica(1).attivaOnPrendiImpresa(famigliareTemporaneo);
		}

		// controllo che il famigliare abbia un valore sufficiente per l'azione
		if (famigliareTemporaneo.valore < (1 + 2 * (posizione % 4)))
			throw new InsufficientValueException();

		// guardo se c'è un'altra pedina e nel caso pago 3 monete
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

		// Applico l'eventuale bonus della torre
		boolean controllo = false;
		if (posizione % 4 == 3 | posizione % 4 == 2) {
			for (int i = 0; i < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); i++) {
				if (attivaOnEffettoTorre())
					controllo = true;
			}
			if (controllo == false)
				spazioAzione.eseguiEffettoImmediatoTorre(famigliareTemporaneo.giocatoreAppartenenza, posizione);
		}

		// controllo le carte personaggio per applicare eventuali sconti
		for (int j = 0; j < this.giocatoreAppartenenza.getPlancia().getPersonaggi().size(); j++) {
			Personaggio cartaPersonaggio = this.giocatoreAppartenenza.getPlancia().getPersonaggi().get(j);
			if (identificativoTorre == 0)
				cartaPersonaggio.attivaOnPagaTerritorio(famigliareTemporaneo.giocatoreAppartenenza,
						spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 1)
				cartaPersonaggio.attivaOnPagaPersonaggio(famigliareTemporaneo.giocatoreAppartenenza,
						spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 2)
				cartaPersonaggio.attivaOnPagaEdificio(famigliareTemporaneo.giocatoreAppartenenza,
						spazioAzione.getCartaTorre(posizione));
			if (identificativoTorre == 3)
				cartaPersonaggio.attivaOnPagaImpresa(famigliareTemporaneo.giocatoreAppartenenza,
						spazioAzione.getCartaTorre(posizione));
		}

		// controllo se posso pagare la carta. Se sì, effettuo il pagamento,
		// prendo la carta, eseguo l'effetto immediato e posiziono il famigliare
		if (!spazioAzione.getCartaTorre(posizione).acquisibile(famigliareTemporaneo.giocatoreAppartenenza))
			throw new NoEnoughResourcesException();
		else {
			spazioAzione.getCartaTorre(posizione).acquisizione(giocatoreAppartenenza);
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
}