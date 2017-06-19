package test.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Giocatore;
import main.model.Partita;
import main.model.SpazioAzione;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.exceptions.ChurchSupportException;
import main.model.exceptions.FamiliarAlreadyUsedException;
import main.model.exceptions.InsufficientValueException;

public class PartitaTest extends Partita {

	@Override
	public void log(String message) {

	}

	/**
	 * Test che verifica che se uno o più giocatori spostano un famigliare sulla
	 * zona Palazzo Del Consiglio, il turno successivo giocano per primi
	 * 
	 * @throws InsufficientValueException
	 * @throws FamiliarAlreadyUsedException
	 */
	@Test
	public void testScegliOrdine() throws FamiliarAlreadyUsedException, InsufficientValueException {
		spazioAzione = new SpazioAzione();
		Giocatore giocatoreTemp1 = new Giocatore();
		Giocatore giocatoreTemp2 = new Giocatore();
		for (int i = 0; i < 4; i++) {
			this.giocatori.add(new Giocatore());
		}
		inizializzaPartita();
		this.giocatori.get(3).getFamigliare(0).cambiaValore(1);
		this.giocatori.get(2).getFamigliare(0).cambiaValore(1);
		this.giocatori.get(3).getFamigliare(0)
				.eseguiSpostamentoPalazzoConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		this.giocatori.get(2).getFamigliare(0)
				.eseguiSpostamentoPalazzoConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		giocatoreTemp1 = this.giocatori.get(0);
		giocatoreTemp2 = this.giocatori.get(1);
		scegliOrdine();

		assertTrue(this.giocatori.get(0).equals(this.spazioAzione.getGiocatoriPalazzoDelConsiglio().get(0)));
		assertTrue(this.giocatori.get(1).equals(this.spazioAzione.getGiocatoriPalazzoDelConsiglio().get(1)));
		assertTrue(this.giocatori.get(2).equals(giocatoreTemp1));
		assertTrue(this.giocatori.get(3).equals(giocatoreTemp2));
	}

	/**
	 * Test che verifica il funzionamento del calcolo della classifica dei punti
	 * militari, influente sul numero dei punti vittoria dei giocatori, e il
	 * funzionamento del calcolo della classifica dei punti vittoria
	 */
	@Test
	public void testCalcolaClassificaFinale() {
		for (int i = 0; i < 2; i++) {
			this.giocatori.add(new Giocatore());
		}
		this.giocatori.get(0).getPunti().cambiaPuntiMilitari(5);
		assertTrue(this.giocatori.get(0).equals(calcolaClassificaFinale().get(0)));
		this.giocatori.get(1).getPunti().cambiaPuntiVittoria(10);
		assertTrue(this.giocatori.get(1).equals(calcolaClassificaFinale().get(0)));
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo
	 * eseguiRapportoVaticano e il metodo di supporto puoSostenereChiesa. In
	 * questo test se il giocatore può sostenere la Chiesa lo fa.
	 * 
	 * @throws ChurchSupportException
	 */
	@Test
	public void testEseguiRapportoVaticano() throws ChurchSupportException {
		this.periodo = 2;
		this.turno=4;
		inizializzaScomunica();
		Giocatore giocatore = new Giocatore();
		giocatore.getPunti().cambiaPuntiFede(4);

		eseguiRapportoVaticano(giocatore, puoSostenereChiesa(giocatore));
		assertTrue(giocatore.getPunti().getPuntiFede() == 0 && giocatore.getPunti().getPuntiVittoria() == 4);

		eseguiRapportoVaticano(giocatore, puoSostenereChiesa(giocatore));
		assertTrue(giocatore.getScomunica(1) == this.scomuniche[1]);
	}

	/**
	 * Test che verifica che alla fine del turno il metodo
	 * giocatoreDelTurnoSuccessivo restituisca null.
	 */
	@Test
	public void testFineTurno() {
		for (int i = 0; i < 2; i++) {
			this.giocatori.add(new Giocatore());
			for (int j = 0; j < 4; j++) {
				this.giocatori.get(i).getFamigliare(j).setPosizionato(true);
			}
		}
		this.giocatoreDiTurno = this.giocatori.get(1);
		assertTrue(this.giocatoreDelTurnoSuccessivo(giocatoreDiTurno) == null);
	}
}
