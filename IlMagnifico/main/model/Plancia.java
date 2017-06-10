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

	public EEffettiPermanenti[] getEffettiPermanenti() {
		ArrayList<EEffettiPermanenti> effetti = new ArrayList<EEffettiPermanenti>();

		// Effetti permanenti delle carte Territorio
		for (Territorio t : territori) {
			for (EEffettiPermanenti tEffect : t.getEffettiPermanentiCarta()) {
				effetti.add(tEffect);
			}
		}

		// Effetti permanenti delle carte Personaggio
		for (Personaggio p : personaggi) {
			for (EEffettiPermanenti pEffect : p.getEffettiPermanentiCarta()) {
				effetti.add(pEffect);
			}
		}

		// Effetti permanenti delle carte Edificio
		for (Edificio e : edifici) {
			for (EEffettiPermanenti eEffect : e.getEffettiPermanentiCarta()) {
				effetti.add(eEffect);
			}
		}

		// Effetti permanenti delle carte Impresa
		for (Impresa i : imprese) {
			for (EEffettiPermanenti iEffect : i.getEffettiPermanentiCarta()) {
				effetti.add(iEffect);
			}
		}

		return effetti.toArray(new EEffettiPermanenti[effetti.size()]);
	}
}