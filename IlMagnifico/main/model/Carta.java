package main.model;

import java.io.Serializable;
import java.util.*;

import main.model.enums.EAzioniGioco;
import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;
import main.model.exceptions.NoEnoughResourcesException;

/**
 * 
 */
public abstract class Carta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7463494507592883793L;

	/**
	 * Default constructor
	 */
	public Carta(String nome, ArrayList<Object[]> acquisizione, ArrayList<Object[]> effettoImmediato,
			ArrayList<Object[]> effettoPermanente, int valoreNecessario, int periodo, ArrayList<ECostiCarte> costi,
			int scelteCosti, ArrayList<EEffettiPermanenti> effettiPermanenti, int scelteEffettiPermanenti) {
		this.nome = nome;
		this.acquisizione = acquisizione;
		this.effettoImmediato = effettoImmediato;
		this.effettoPermanente = effettoPermanente;
		this.valoreNecessarioEffettoPermanente = valoreNecessario;
		this.periodoCarta = periodo;
		this.utilEffetto = new UtilEffetto();
		this.costiDellaCartaComunicazione = costi;
		this.numeroScelteCostiComunicazione = scelteCosti;
		this.effettiPermanentiDelleCarteComunicazione = effettiPermanenti;
		this.numeroScelteEffettiPermanentiComunicazione = scelteEffettiPermanenti;
	}

	/**
	 * 
	 */
	protected String nome;

	/**
	 * attributo aggiunto per fare funzionare i metodi
	 */
	private UtilEffetto utilEffetto = new UtilEffetto();
	/**
	 * 
	 */
	protected transient ArrayList<Object[]> acquisizione;

	/**
	 * 
	 */
	protected transient ArrayList<Object[]> effettoImmediato;

	/**
	 * 
	 * */
	protected int valoreNecessarioEffettoPermanente;

	/**
	 * Periodo associato alla carta
	 */
	protected int periodoCarta;

	/**
	 * enum utilizzato per selezionare una opzione tramite la parte di
	 * comunicazione
	 */
	protected ArrayList<ECostiCarte> costiDellaCartaComunicazione;

	/**
	 * intero che indica il numero di scelte da fare sui metodi di pagamento
	 */
	protected int numeroScelteCostiComunicazione;

	/**
	 * enum utilizzato per selezionare una opzione tramite la parte di
	 * comunicazione
	 */
	protected ArrayList<EEffettiPermanenti> effettiPermanentiDelleCarteComunicazione;

	/**
	 * intero che indica il numero di scelte da fare sugli effetti permanenti
	 */
	protected int numeroScelteEffettiPermanentiComunicazione;

	/**
	 * 
	 */
	protected transient ArrayList<Object[]> effettoPermanente;

	/**
	 * Metodo che controlla se il giocatore ha abbastanza punti militari o
	 * risorse per potere pagare il costo della carta
	 * 
	 * @param oggetto
	 * @return
	 */
	public boolean acquisibile(Giocatore giocatore) {
		// devo controllare che
		// posso pagare o con le
		// risorse o con i punti

		boolean controlloRisorse = false;
		boolean controlloPunti = false;
		for (int i = 0; i < acquisizione.size(); i++) {
			acquisizione.get(i)[1] = giocatore;
			if (acquisizione.get(i)[0] != null)
				if ((int) acquisizione.get(i)[0] == 1)
					if (utilEffetto.cartaAcquisibileRisorse(acquisizione.get(i))) {
						controlloRisorse = true;
						acquisizione.get(i)[0] = 0;
					} else if ((int) acquisizione.get(i)[0] == 2)
						if (utilEffetto.cartaAcquisibilePunti(acquisizione.get(i))) {
							controlloPunti = true;
							acquisizione.get(i)[0] = 0;
						}
		}
		if (controlloRisorse | controlloPunti)
			// se almeno con i punti o con le risorse posso pagare, allora la
			// carta e' acquisibile
			return true;
		else
			return false;
	}

	/**
	 * Metodo che attiva l'effetto immediato della carta
	 * 
	 * @param Object
	 * @return
	 */
	public void effettoImmediato(Giocatore giocatore, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < effettoImmediato.size(); i++) {
			effettoImmediato.get(i)[1] = giocatore;
			effettoImmediato.get(i)[3] = famigliare;
			effettoImmediato.get(i)[4] = carta;
			/*
			 * if ((int) effettoImmediato.get(i)[0] == 0)
			 * utilEffetto.aggiungiRisorse(effettoImmediato.get(i)); else if
			 * ((int) effettoImmediato.get(i)[0] == 3)
			 * utilEffetto.eseguiPrivilegioDelConsiglio(effettoImmediato.get(i))
			 * ;
			 */
			attivaEffettoSingolo(this.effettoImmediato.get(i));
		}
	}

	/**
	 * Metodo che attiva l'effetto permanente della carta
	 * 
	 * @param Object
	 * @return
	 */
	public void effettoPermanente(Giocatore giocatore, Famigliare famigliare, Carta carta,
			EEffettiPermanenti effettoScelto) {
		if (this.effettiPermanentiDelleCarteComunicazione.size() >= 1
				&& effettoScelto == this.effettiPermanentiDelleCarteComunicazione.get(0))
			attivaEffettoSingolo(effettoPermanente.get(0));
		else if (this.effettiPermanentiDelleCarteComunicazione.size() >= 2
				&& effettoScelto == this.effettiPermanentiDelleCarteComunicazione.get(1))
			attivaEffettoSingolo(effettoPermanente.get(1));
		else if (effettoScelto == null) {

			for (int i = 0; i < effettoPermanente.size(); i++) {
				effettoPermanente.get(i)[1] = giocatore;
				effettoPermanente.get(i)[3] = famigliare;
				effettoPermanente.get(i)[4] = carta;
				attivaEffettoSingolo(effettoPermanente.get(i));
			}
		}
	}

	public boolean Attivabile(int valoreAzione) {
		// gli passo un valore, non il
		// famigliare, perche' devo
		// potere variare il valore
		// a seconda dei bonus e
		// malus ricevuti con
		// effetti vari
		if (valoreAzione >= this.valoreNecessarioEffettoPermanente)
			return true;
		else
			return false;
	}

	/**
	 * Metodo che restituisce il periodo della carta
	 * 
	 * @param
	 * @return
	 */
	public int getPeriodoCarta() {
		return this.periodoCarta;
	}

	/**
	 * Metodo che effettua l'acquisto da parte di un giocatore di una carta
	 * 
	 */
	public void acquisizione(Giocatore giocatore, ECostiCarte costoScelto) throws NoEnoughResourcesException {
		if (costoScelto == this.costiDellaCartaComunicazione.get(0))
			if ((int) (acquisizione.get(0)[0]) == 0) {
				utilEffetto.preparaPagamento(acquisizione.get(0));
			} else
				throw new NoEnoughResourcesException();
		else if (acquisizione.size() > 1) {
			if ((int) (acquisizione.get(1)[0]) == 0)
				utilEffetto.preparaPagamento(acquisizione.get(1));
		} else if (costoScelto == null) {
			if ((int) (acquisizione.get(0)[0]) == 0) {
				utilEffetto.preparaPagamento(acquisizione.get(0));
			}
		} else
			throw new NoEnoughResourcesException();

	}

	/**
	 * Metodo che verifica che un effetto possa essere attivato e lo attiva
	 * 
	 * @param
	 * @return
	 */
	public void attivaOnAzione(Giocatore giocatore, EAzioniGioco azione, Famigliare famigliare, Carta carta) {
		for (int i = 0; i < this.effettoPermanente.size(); i++) {
			this.effettoPermanente.get(i)[1] = giocatore;
			this.effettoPermanente.get(i)[3] = famigliare;
			this.effettoPermanente.get(i)[4] = carta;
			if ((EAzioniGioco) (this.effettoPermanente.get(i)[2]) == azione)
				attivaEffettoSingolo(this.effettoPermanente.get(i));
		}
	}

	/**
	 * Metodo che, dato un array di Object in ingresso, va ad eseguire l'effetto
	 * corrispondente con i parametri codificati nell'array di Object
	 */
	public void attivaEffettoSingolo(Object[] parametri) {
		if ((int) parametri[0] == 0)
			utilEffetto.aggiungiRisorse(parametri);
		else if ((int) parametri[0] == 3)
			utilEffetto.eseguiPrivilegioDelConsiglio(parametri);
		else if ((int) parametri[0] == 4)
			utilEffetto.aumentaDiDueAzione(parametri);
		else if ((int) parametri[0] == 5)
			utilEffetto.aumentaDiTreAzione(parametri);
		else if ((int) parametri[0] == 6)
			utilEffetto.eseguiEffettoImmediatoRaccolto(parametri);
		else if ((int) parametri[0] == 7)
			utilEffetto.eseguiEffettoImmediatoProduzioneValoreTre(parametri);
		else if ((int) parametri[0] == 8)
			utilEffetto.eseguiEffettoImmediatoProduzioneValoreQuattro(parametri);
		else if ((int) parametri[0] == 9)
			utilEffetto.duePVxedificio(parametri);
		else if ((int) parametri[0] == 10)
			utilEffetto.duePVxpersonaggio(parametri);
		else if ((int) parametri[0] == 11)
			utilEffetto.duePVximpresa(parametri);
		else if ((int) parametri[0] == 12)
			utilEffetto.duePVxterritorio(parametri);
		else if ((int) parametri[0] == 13)
			utilEffetto.unPVxpersonaggio(parametri);
		else if ((int) parametri[0] == 14)
			utilEffetto.unPVximpresa(parametri);
		else if ((int) parametri[0] == 15)
			utilEffetto.unaMonetaxterritorio(parametri);
		else if ((int) parametri[0] == 16)
			utilEffetto.unaMonetaxedificio(parametri);
		else if ((int) parametri[0] == 17)
			utilEffetto.unPVxduePM(parametri);
		else if ((int) parametri[0] == 18)
			utilEffetto.decrementaDiQuattroAzione(parametri);
		else if ((int) parametri[0] == 19)
			utilEffetto.decrementaDiTreAzione(parametri);
		else if ((int) parametri[0] == 20)
			utilEffetto.decrementaDiUnoAzione(parametri);
		else if ((int) parametri[0] == 23)
			utilEffetto.scontoUnaMoneta(parametri);
		else if ((int) parametri[0] == 24)
			utilEffetto.scontoLegnoEPietra(parametri);
		else if ((int) parametri[0] == 25)
			utilEffetto.prendiCartaSenzaFamigliareQuattro(parametri);
	}

	/**
	 * Metodo che controlla che uno degli effetti permanenti si attivi sul bonus
	 * percepito dalla azione della torre. Ritorna true se tale effetto esiste,
	 * false altrimenti
	 * 
	 * @param
	 * @return
	 * 
	 */
	public boolean attivaOnEffettoTorre() {
		for (int i = 0; i < this.effettoPermanente.size(); i++) {
			if ((EAzioniGioco) (this.effettoPermanente.get(i)[2]) == EAzioniGioco.EffettoTorre)
				return true;
		}
		return false;
	}

	/**
	 * Metodo che restituisce il valore dell'azione necessario per attivare
	 * l'effetto permanente della carta, se c'e'
	 * 
	 * @return
	 */
	public int getValoreNecessarioEffettoPermanente() {
		return this.valoreNecessarioEffettoPermanente;
	}

	public String getNome() {
		return this.nome;
	}

	public ArrayList<Object[]> getAcquisizione() {
		return this.acquisizione;
	}

	public int getNumeroScelteCosti() {
		return this.numeroScelteCostiComunicazione;
	}

	public int getNumeroScelteEffettiPermanentiComunicazione() {
		return this.numeroScelteEffettiPermanentiComunicazione;
	}

	public ArrayList<ECostiCarte> getCostiCarta() {
		return costiDellaCartaComunicazione;
	}

	public ArrayList<EEffettiPermanenti> getEffettiPermanentiCarta() {
		return effettiPermanentiDelleCarteComunicazione;
	}
}