package main.model;

import java.util.*;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.ECarte;
import main.model.enums.EScomuniche;
import main.model.enums.ETipiCarte;
import main.model.enums.PlayerColors;
import main.model.errors.Errors;
import main.model.errors.GameError;

/**
 * 
 */
public class Partita {

	/**
	 * 
	 */
	protected ArrayList<Carta> mazzo;

	/**
	 * Array giocatori (usato per determinare l'ordine attuale del turno di
	 * gioco).
	 */
	protected ArrayList<Giocatore> giocatori;

	/**
	 * Puntatore al giocatore attualmente di turno
	 */
	protected Giocatore giocatoreDiTurno;

	/**
	 * 
	 */
	protected SpazioAzione spazioAzione;

	/**
	 * 
	 */
	protected Scomunica[] scomuniche;

	/**
	 * TOT. 2 turni per periodo
	 */
	protected int turno;

	/**
	 * TOT. 2 periodi per partita
	 */
	protected int periodo;

	/**
	 * Flag usato per determinare se la partita e' terminata.
	 */
	protected boolean partitaTerminata;

	/**
	 * Costruttore.
	 */
	public Partita() {
		giocatori = new ArrayList<Giocatore>();
		this.mazzo = new ArrayList<Carta>();
		this.giocatoreDiTurno = null;
		this.spazioAzione = new SpazioAzione();
		this.scomuniche = new Scomunica[3];
		for (int i = 0; i < 3; i++) {
			this.scomuniche[i] = new Scomunica();
		}
		this.turno = 0;
		this.periodo = 0;
		this.partitaTerminata = false;
	}

	/**
	 * Metodo usato per inizializzare la partita.
	 */
	protected void inizializzaPartita() {
		// TOT. 2 periodi per partita
		this.periodo = 1;

		// TOT. 2 Turni per periodo
		this.turno = 1;

		// TODO: come inizializzate il mazzo?
		inizializzaMazzo();
		mescolaMazzo();

		// TODO: aggiustare (scatena IllegalArgumentException)
		inizializzaScomunica();

		inizializzaGiocatori();
		// prossimo giocatore ad eseguire un azione
		this.giocatoreDiTurno = giocatori.get(0);

		// Per ora non mi viene in mente altro che si potrebbe fare per
		// inizializzare la partita. C'e' ancora da implementare la parte
		// della inizializzazione del mazzo perche' dipende dal file (per farlo
		// funzionare anche solo temporaneamente dovrei fare un enum). Stesso
		// discorso vale per le scomuniche.

	}

	/**
	 * Metodo per verificare la possibilita' di eseguire un azione da parte di
	 * un determinato giocatore
	 * 
	 * @param g
	 *            giocatore su cui verificare la validita' dell'azione da
	 *            eseguire
	 * @param e
	 *            (nel caso di invalidita' dell'azione che il giocatore sta
	 *            tentando di compiere) conterra' il codice associato all'errore
	 * @return true se giocatore puo' eseguire l'azione, false altrimenti
	 */

	protected boolean isElegible(Giocatore g, GameError e) {
		boolean elegibility = true;
		if (!isPartitaIniziata()) {
			e.setError(Errors.GAME_NOT_STARTED);
			elegibility = false;
		} else if (!isGiocatoreDiTurno(g)) {
			e.setError(Errors.NOT_YOUR_TURN);
			elegibility = false;
		} else if (isPartitaFinita()) {
			e.setError(Errors.GAME_ENDED);
			elegibility = false;
		}
		return elegibility;
	}

	protected boolean isPartitaIniziata() {
		return this.periodo > 0;
	}

	protected boolean isPeriodoTerminato() {
		return (turno == 2 && isGiroDiTurniTerminato());
	}

	protected boolean isPartitaFinita() {
		return partitaTerminata;
	}

	protected void terminaPartita() {
		this.partitaTerminata = true;
	}

	protected boolean isGiocatoreDiTurno(Giocatore g) {
		return this.giocatoreDiTurno.equals(g);
	}

	protected boolean isGiroDiTurniTerminato() {
		return this.giocatoreDiTurno.equals(null);
	}

	public void inizializzaMazzo() {
		ECarte carte[] = ECarte.values();
		for (int i = 0; i < carte.length; i++) {
			if (carte[i].getTipoCarta() == ETipiCarte.Territorio)
				this.mazzo.add(new Territorio(carte[i].getNome(), carte[i].getCosti(), carte[i].getEffettiImmediati(),
						carte[i].getEffettiPermanenti(), carte[i].getvaloreNecessarioAttivazione(),
						carte[i].getPeriodo()));
			if (carte[i].getTipoCarta() == ETipiCarte.Edificio)
				this.mazzo.add(new Edificio(carte[i].getNome(), carte[i].getCosti(), carte[i].getEffettiImmediati(),
						carte[i].getEffettiPermanenti(), carte[i].getvaloreNecessarioAttivazione(),
						carte[i].getPeriodo()));
			if (carte[i].getTipoCarta() == ETipiCarte.Personaggio)
				this.mazzo.add(new Personaggio(carte[i].getNome(), carte[i].getCosti(), carte[i].getEffettiImmediati(),
						carte[i].getEffettiPermanenti(), carte[i].getvaloreNecessarioAttivazione(),
						carte[i].getPeriodo()));
			if (carte[i].getTipoCarta() == ETipiCarte.Impresa)
				this.mazzo.add(new Impresa(carte[i].getNome(), carte[i].getCosti(), carte[i].getEffettiImmediati(),
						carte[i].getEffettiPermanenti(), carte[i].getvaloreNecessarioAttivazione(),
						carte[i].getPeriodo()));
		}
	}

	/**
	 * Metodo che mischia il mazzo senza distinguere le carte per periodo e per
	 * tipo. Il riconoscere le carte da prendere per il relativo periodo e per
	 * la relativa torre vengono lasciate al metodo posizionaCartaSuTorre
	 * 
	 * @return
	 */
	public void mescolaMazzo() {
		Collections.shuffle(mazzo);
	}

	/**
	 * Metodo per riempire l'array delle tessere scomunica
	 * 
	 * @return
	 */
	public void inizializzaScomunica() {
		for (int i = 0; i < 3; i++) {
			int periodo = i + 1;
			int indice;
			ArrayList<Scomunica> temporaneo = new ArrayList<Scomunica>();
			Random random = new Random();
			EScomuniche scomuniche[] = EScomuniche.values();
			for (int j = 0; j < scomuniche.length; j++) {
				if (scomuniche[j].getPeriodo() == periodo) {
					temporaneo.add(new Scomunica(scomuniche[j].getNome(), scomuniche[j].getPeriodo(),
							scomuniche[j].getEffetto()));
				}
			}
			indice = random.nextInt(temporaneo.size());
			this.scomuniche[i] = temporaneo.get(indice);
		}
	}

	/**
	 * @return
	 */
	public void inizializzaGiocatori() {
		int contatoreMonete = 4;
		Collections.shuffle(giocatori);
		// generazione casuale dell'ordine dei turni di gioco

		for (int i = 0; i < this.giocatori.size(); i++) {
			this.giocatori.get(i).getRisorse().cambiaLegno(2);
			this.giocatori.get(i).getRisorse().cambiaPietre(2);
			this.giocatori.get(i).getRisorse().cambiaServitori(3);
			this.giocatori.get(i).getRisorse().cambiaMonete(contatoreMonete + 1);
			this.giocatori.get(i).setSpazioAzione(this.spazioAzione);
			if (i == 0)
				this.giocatori.get(i).setColore(PlayerColors.BLUE);
			if (i == 1)
				this.giocatori.get(i).setColore(PlayerColors.GREEN);
			if (i == 2)
				this.giocatori.get(i).setColore(PlayerColors.RED);
			if (i == 3)
				this.giocatori.get(i).setColore(PlayerColors.YELLOW);
		}

	}

	/**
	 * Metodo che calcola il nuovo ordine di gioco.
	 * 
	 * @return
	 */
	public void scegliOrdine() {
		// prende la lista senza duplicati e ordinata di giocatori nella zona
		// palazzo del consiglio, ci aggiunge i giocatori non ancora presenti
		// nella lista e ritorna il nuovo ordine di gioco
		ArrayList<Giocatore> ordineNuovo = new ArrayList<Giocatore>();
		ordineNuovo = this.spazioAzione.getGiocatoriPalazzoDelConsiglio();
		Giocatore giocatore = new Giocatore();
		for (int i = 0; i < this.giocatori.size(); i++) {
			giocatore = this.giocatori.get(i);
			if (!ordineNuovo.contains(giocatore))
				ordineNuovo.add(giocatore);
		}
		this.giocatori = ordineNuovo;
	}

	/**
	 * Metodo che posiziona le carte sulle torri all'inizio del turno
	 * 
	 * @return
	 */
	public void posizionaCarteSuTorre() {
		for (int j = 0; j < 16; j++) {
			for (int i = 0; i < this.mazzo.size(); i++) {
				if ((0 <= j) && j < 4) {
					if ((this.mazzo.get(i).getPeriodoCarta() == this.periodo)
							&& (this.mazzo.get(i) instanceof Territorio)) {
						this.spazioAzione.setCartaTorre(this.mazzo.get(i), j);
						this.mazzo.remove(i);
						break;
					}
				}
				if ((4 <= j) && j < 8) {
					if ((this.mazzo.get(i).getPeriodoCarta() == this.periodo)
							&& (this.mazzo.get(i) instanceof Personaggio)) {
						this.spazioAzione.setCartaTorre(this.mazzo.get(i), j);
						this.mazzo.remove(i);
						break;
					}
				}
				if ((8 <= j) && j < 12) {
					if ((this.mazzo.get(i).getPeriodoCarta() == this.periodo)
							&& (this.mazzo.get(i) instanceof Edificio)) {
						this.spazioAzione.setCartaTorre(this.mazzo.get(i), j);
						this.mazzo.remove(i);
						break;
					}
				}
				if ((12 <= j) && j < 16) {
					if ((this.mazzo.get(i).getPeriodoCarta() == this.periodo)
							&& (this.mazzo.get(i) instanceof Impresa)) {
						this.spazioAzione.setCartaTorre(this.mazzo.get(i), j);
						this.mazzo.remove(i);
						break;
					}
				}
			}
		}
	}

	/**
	 * Metodo che restituisce il giocatore che deve giocatore al turno
	 * successivo. Restituisce null se sono finiti i famigliari da muovere e
	 * restituisce il giocatore in ingresso nel caso non sia presente
	 * all'interno dell'elenco dei giocatori nella partita
	 * 
	 * @param
	 * @return
	 */
	public Giocatore giocatoreDelTurnoSuccessivo(Giocatore giocatoreDiTurno) {
		for (int i = 0; i < this.giocatori.size(); i++) {
			if (this.giocatori.get(i) == giocatoreDiTurno) {
				if ((i == (this.giocatori.size() - 1) && (!(this.giocatori.get(i).checkPosizionato()))))
					return this.giocatori.get(0);
				else if ((i == (this.giocatori.size() - 1)) && (this.giocatori.get(i).checkPosizionato()))
					return null;
				else
					return this.giocatori.get(i + 1);
			}
		}
		return giocatoreDiTurno;
	}

	/**
	 * Metodo che esegue il rapporto del vaticano per un giocatore. In ingresso
	 * sono il giocatore stesso e un boolean che indica se il giocatore vuole
	 * supportare (true) o no (false) la Chiesa.
	 * 
	 * @param
	 * @return
	 */
	public void eseguiRapportoVaticano(Giocatore giocatore, boolean esegui) {
		int puntiFede = 0;
		int incremento = 0;
		if (esegui == true) {
			puntiFede = giocatore.getPunti().getPuntiFede();
			for (int i = 0; i < puntiFede; i++) {
				if (i > 4)
					incremento++;
			}
			giocatore.getPunti().setPuntiVittoria(puntiFede + incremento);
			giocatore.getPunti().setPuntiFede(0);
		} else {
			// gli array delle scomuniche corrispondono al numero del periodo-1
			giocatore.setScomunica(periodo - 1, this.scomuniche[periodo - 1]);
		}
	}

	/**
	 * Inizializza un nuovo SpazioAzione, aggiorna i giocatori con tale
	 * SpazioAzione e per ogni famigliare di ogni giocatore rimette il valore a
	 * 0 e la verifica di essere già spostati a false. Questo metodo NON mette
	 * le carte nuove sul tabellone.
	 * 
	 * @return
	 */
	public void resetPerNuovoTurno() {
		this.spazioAzione = new SpazioAzione();
		Giocatore giocatore = new Giocatore();
		for (int i = 0; i < this.giocatori.size(); i++) {
			giocatore = this.giocatori.get(i);
			giocatore.setSpazioAzione(spazioAzione);
			for (int j = 0; j < 4; j++) {
				giocatore.getFamigliare(j).setValore(0);
				giocatore.getFamigliare(j).setPosizionato(false);
			}
		}
	}

	/**
	 * Metodo che calcola la classifica finale dei giocatori
	 * 
	 * @return
	 */
	public ArrayList<Giocatore> calcolaClassificaFinale() {
		ArrayList<Giocatore> classifica = new ArrayList<Giocatore>();
		// calcolo la classifica dei punti militari per potere assegnare i punti
		// vittoria derivanti da tale classifica
		classifica.add(giocatori.get(0));
		for (int i = 1; i < this.giocatori.size(); i++) {
			for (int j = 0; j < classifica.size(); j++) {
				if (this.giocatori.get(i).getPunti().getPuntiMilitari() > classifica.get(j).getPunti()
						.getPuntiMilitari()) {
					classifica.add(j, this.giocatori.get(i));
					break;
				} else if (j == classifica.size() - 1)
					classifica.add(giocatori.get(i));
			}
		}
		classifica.get(0).getPunti().cambiaPuntiVittoria(5);
		classifica.get(1).getPunti().cambiaPuntiVittoria(2);
		classifica.removeAll(classifica);
		// attivo gli effetti delle scomuniche di terzo periodo
		for (int i = 0; i < giocatori.size(); i++) {
			if (this.giocatori.get(i).getScomunica(2) != null)
				this.giocatori.get(i).getScomunica(2).attivaOnAzione(this.giocatori.get(i),
						EAzioniGiocatore.FinePartita, null, null);
		}
		// procedo al calcolo dei punti finali e alla relativa classifica
		for (int i = 0; i < this.giocatori.size(); i++) {
			this.giocatori.get(i).calcolaPVFinali();
		}
		classifica.add(giocatori.get(0));
		for (int i = 1; i < this.giocatori.size(); i++) {
			for (int j = 0; j < classifica.size(); j++) {
				if (this.giocatori.get(i).getPunti().getPuntiVittoria() > classifica.get(j).getPunti()
						.getPuntiVittoria()) {
					classifica.add(j, this.giocatori.get(i));
					break;
				} else if (j == classifica.size() - 1)
					classifica.add(giocatori.get(i));
			}
		}
		return classifica;
	}

	/**
	 * Metodo che lancia i dadi ed assegna i valori alle variabili legate ai
	 * dadi in SpazioAzione ed ai famigliari dei giocatori. Per convenzione
	 * lancio sempre prima il dado nero, poi l'arancione, poi il bianco.
	 * 
	 * @return
	 */
	public void lanciaDadi() {
		int valoreDado;
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			valoreDado = random.nextInt(6) + 1;
			for (int j = 0; j < this.giocatori.size(); j++) {
				this.giocatori.get(j).setValore(i, valoreDado);
				this.spazioAzione.setValoreDadi(valoreDado, i);
			}
		}
	}

	/**
	 * Metodo che controlla se qualche giocatore e' in possesso della carta
	 * scomunica che fa giocare per ultimi. In caso affermativo provvede allo
	 * spostamento
	 */
	public void controlloScomunicaModificaOrdine() {
		ArrayList<Giocatore> cloneGiocatori = new ArrayList<Giocatore>();
		Giocatore giocatore = new Giocatore();
		// creo un arraylist identico all'ordine di turno
		for (int i = 0; i < this.giocatori.size(); i++) {
			giocatore = this.giocatori.get(i);
			cloneGiocatori.add(giocatore);
		}
		// vado a scorrere l'arraylist dell'ordine attuale e modifico l'ordine
		// se la scomunica e' presente
		for (int j = 0; j < cloneGiocatori.size(); j++) {
			giocatore = cloneGiocatori.get(j);
			if (giocatore.getScomunica(1) != null)
				if (giocatore.getScomunica(1).attivaOnInizioTurno()) {
					// rimuovo il giocatore dalla posizione in cui e' e lo
					// inserisco alla fine
					giocatori.remove(giocatore);
					giocatori.add(giocatore);
				}
		}
	}

	/**
	 * Metodo che modifica l'attributo giocatoreDiTurno quando si avanza di
	 * turno
	 */
	public void avanzaDiTurno() {
		this.giocatoreDiTurno = giocatoreDelTurnoSuccessivo(giocatoreDiTurno);
	}
}