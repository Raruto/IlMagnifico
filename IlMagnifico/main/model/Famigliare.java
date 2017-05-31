package main.model;

import java.util.*;

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
			throws FamigliareSpostatoException, SpazioOccupatoException, SameTowerException {
		int identificativoTorre = 0;
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
			if ((spazioAzione.getFamigliareTorre(i).getGiocatore().getColore() == this.giocatoreAppartenenza
					.getColore()) && (spazioAzione.getFamigliareTorre(i).getNeutralita() == false)
					&& (this.neutro == false))
				throw new SameTowerException();
		}

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

		// TODO:finire di implementare, non toccatemelo per favore che è un
		// casino

		/*
		 * if (posizione % 4 == 0 && valore < 1) // controlla alternativamente
		 * le prime ,le seconde, terze e quarte // posizioni e controlla che il
		 * valore della pedina sia abbastanza // grande. Va implementato anche
		 * il controllo sugli effetti // permanenti delle carte che modificano
		 * il valore dell'azione return false; else if ((posizione) % 4 == 1 &&
		 * valore < 3) return false; else if ((posizione) % 4 == 2 && valore <
		 * 5) return false; else if ((posizione) % 4 == 3 && valore < 7) return
		 * false;
		 */
		Carta cartaTorre = spazioAzione.getCartaTorre(posizione);

		if (!cartaTorre.acquisibile(giocatoreAppartenenza))
			return false;
		else { // Quando acquisto una carta, devo pagarla. Bisognerebbe scrivere
				// un metodo
				// public void acquisizione(Giocatore giocatore) all'interno di
				// carta che attivo nel caso in cui acquisibile
				// restituisca true

			if ((cartaTorre instanceof Personaggio)
					&& (giocatoreAppartenenza.getPlancia().getPersonaggi().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getPersonaggi().add((Personaggio) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else if ((cartaTorre instanceof Territorio)
					&& (giocatoreAppartenenza.getPlancia().getTerritori().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getTerritori().add((Territorio) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else if ((cartaTorre instanceof Edificio)
					&& (giocatoreAppartenenza.getPlancia().getEdifici().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getEdifici().add((Edificio) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else if ((cartaTorre instanceof Impresa)
					&& (giocatoreAppartenenza.getPlancia().getImprese().size() < 7)) {
				giocatoreAppartenenza.getPlancia().getImprese().add((Impresa) cartaTorre);
				spazioAzione.setCartaTorre(null, posizione);
			} else
				return false;
		}

		cartaTorre.effettoImmediato(giocatoreAppartenenza);

		spazioAzione.setFamigliareTorre(this, posizione);
		return true;
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