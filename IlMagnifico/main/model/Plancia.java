package main.model;

import java.io.Serializable;
import java.util.*;

import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;

/**
 * 
 */
public class Plancia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187091442351902574L;

	/**
	 * Default constructor
	 */
	public Plancia() {
		personaggi = new ArrayList<Personaggio>();
		territori = new ArrayList<Territorio>();
		edifici = new ArrayList<Edificio>();
		imprese = new ArrayList<Impresa>();
	}

	/**
	 * 
	 */
	private ArrayList<Personaggio> personaggi;

	/**
	 * 
	 */
	private ArrayList<Territorio> territori;

	/**
	 * 
	 */
	private ArrayList<Edificio> edifici;

	/**
	 * 
	 */
	private ArrayList<Impresa> imprese;

	/**
	 * @param Personaggio
	 * @return
	 */
	public void aggiungiPersonaggio(Personaggio nuovoPersonaggio) {
		personaggi.add(nuovoPersonaggio);
	}

	/**
	 * @param Territorio
	 * @return
	 */
	public void aggiungiTerritorio(Territorio nuovoTerritorio) {
		territori.add(nuovoTerritorio);
	}

	/**
	 * @param Edificio
	 * @return
	 */
	public void aggiungiEdificio(Edificio nuovoEdificio) {
		edifici.add(nuovoEdificio);
	}

	/**
	 * @param Impresa
	 * @return
	 */
	public void aggiungiImpresa(Impresa nuovaImpresa) {
		imprese.add(nuovaImpresa);
	}

	public ArrayList<Personaggio> getPersonaggi() {
		return personaggi;
	}

	public ArrayList<Territorio> getTerritori() {
		return territori;
	}

	public ArrayList<Edificio> getEdifici() {
		return edifici;
	}

	public ArrayList<Impresa> getImprese() {
		return this.imprese;
	}

	/**
	 * Ritorna tutti gli Effetti permanenti delle Carte presenti all'interno
	 * della Plancia Giocatore.
	 * 
	 * @return {@link EEffettiPermanenti}[]
	 */
	public EEffettiPermanenti[] getEffettiPermanenti() {
		ArrayList<EEffettiPermanenti> effetti = new ArrayList<EEffettiPermanenti>();

		// Effetti permanenti delle carte Territorio
		for (EEffettiPermanenti tEffects : getEffettiPermanenti()) {
			if (tEffects != null)
				effetti.add(tEffects);
		}

		// Effetti permanenti delle carte Personaggio
		for (EEffettiPermanenti pEffects : getEffettiPermanentiPersonaggi()) {
			if (pEffects != null)
				effetti.add(pEffects);
		}

		// Effetti permanenti delle carte Edificio
		for (EEffettiPermanenti eEffects : getEffettiPermanentiEdifici()) {
			if (eEffects != null)
				effetti.add(eEffects);
		}

		// Effetti permanenti delle carte Impresa
		for (EEffettiPermanenti iEffects : getEffettiPermanentiImprese()) {
			if (iEffects != null)
				effetti.add(iEffects);
		}

		return effetti.toArray(new EEffettiPermanenti[effetti.size()]);
	}

	/* Ritorna gli Effetti Permanenti delle carte Territorio */
	public EEffettiPermanenti[] getEffettiPermanentiTerritori() {
		ArrayList<EEffettiPermanenti> effetti = new ArrayList<EEffettiPermanenti>();

		for (Territorio t : territori) {
			for (EEffettiPermanenti tEffect : t.getEffettiPermanentiCarta()) {
				if (tEffect != null)
					effetti.add(tEffect);
			}
		}

		return effetti.toArray(new EEffettiPermanenti[effetti.size()]);
	}

	/* Ritorna gli Effetti Permanenti delle carte Personaggio */
	public EEffettiPermanenti[] getEffettiPermanentiPersonaggi() {
		ArrayList<EEffettiPermanenti> effetti = new ArrayList<EEffettiPermanenti>();

		for (Personaggio p : personaggi) {
			for (EEffettiPermanenti pEffect : p.getEffettiPermanentiCarta()) {
				if (pEffect != null)
					effetti.add(pEffect);
			}
		}

		return effetti.toArray(new EEffettiPermanenti[effetti.size()]);
	}

	/*
	 * Ritorna gli Effetti Permanenti delle carte Edificio
	 */
	public EEffettiPermanenti[] getEffettiPermanentiEdifici() {
		ArrayList<EEffettiPermanenti> effetti = new ArrayList<EEffettiPermanenti>();

		for (Edificio e : edifici) {
			for (EEffettiPermanenti eEffect : e.getEffettiPermanentiCarta()) {
				if (eEffect != null)
					effetti.add(eEffect);
			}
		}

		return effetti.toArray(new EEffettiPermanenti[effetti.size()]);
	}

	/* Ritorna gli Effetti Permanenti delle carte Impresa */
	public EEffettiPermanenti[] getEffettiPermanentiImprese() {
		ArrayList<EEffettiPermanenti> effetti = new ArrayList<EEffettiPermanenti>();

		for (Impresa i : imprese) {
			for (EEffettiPermanenti iEffect : i.getEffettiPermanentiCarta()) {
				if (iEffect != null)
					effetti.add(iEffect);
			}
		}

		return effetti.toArray(new EEffettiPermanenti[effetti.size()]);
	}
}