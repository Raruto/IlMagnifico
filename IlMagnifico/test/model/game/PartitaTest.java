package test.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Giocatore;
import main.model.Partita;
import main.model.SpazioAzione;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.exceptions.FamiliarAlreadyUsedException;
import main.model.exceptions.InsufficientValueException;

public class PartitaTest extends Partita {

	@Override
	public void log(String message) {

	}

	@Test
	public void testScegliOrdine() {
		spazioAzione = new SpazioAzione();
		Giocatore giocatoreTemp1 = new Giocatore();
		Giocatore giocatoreTemp2 = new Giocatore();
		for (int i = 0; i < 4; i++) {
			this.giocatori.add(new Giocatore());
		}
		inizializzaPartita();
		this.giocatori.get(3).getFamigliare(0).cambiaValore(1);
		this.giocatori.get(2).getFamigliare(0).cambiaValore(1);
		try {
			this.giocatori.get(3).getFamigliare(0)
					.eseguiSpostamentoPalazzoConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		} catch (FamiliarAlreadyUsedException | InsufficientValueException e) {

			e.printStackTrace();
		}
		try {
			this.giocatori.get(2).getFamigliare(0)
					.eseguiSpostamentoPalazzoConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		} catch (FamiliarAlreadyUsedException | InsufficientValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giocatoreTemp1 = this.giocatori.get(0);
		giocatoreTemp2 = this.giocatori.get(1);
		scegliOrdine();

		assertTrue(this.giocatori.get(0) == this.spazioAzione.getGiocatoriPalazzoDelConsiglio().get(0));
		assertTrue(this.giocatori.get(1) == this.spazioAzione.getGiocatoriPalazzoDelConsiglio().get(1));
		assertTrue(this.giocatori.get(2) == giocatoreTemp1);
		assertTrue(this.giocatori.get(3) == giocatoreTemp2);
	}

	@Test
	public void testCalcolaClassificaFinale() {
		for (int i = 0; i < 2; i++) {
			this.giocatori.add(new Giocatore());
		}
		this.giocatori.get(0).getPunti().cambiaPuntiMilitari(5);
		assertTrue(this.giocatori.get(0) == calcolaClassificaFinale().get(0));
		this.giocatori.get(1).getPunti().cambiaPuntiVittoria(10);
		assertTrue(this.giocatori.get(1) == calcolaClassificaFinale().get(0));
	}

	@Test
	public void testEseguiRapportoVaticano() {
		this.periodo = 2;
		inizializzaScomunica();
		Giocatore giocatore = new Giocatore();
		giocatore.getPunti().cambiaPuntiFede(4);
		eseguiRapportoVaticano(giocatore, puoSostenereChiesa(giocatore));
		assertTrue(giocatore.getPunti().getPuntiFede() == 0 && giocatore.getPunti().getPuntiVittoria() == 4);
		eseguiRapportoVaticano(giocatore, puoSostenereChiesa(giocatore));
		assertTrue(giocatore.getScomunica(1) == this.scomuniche[1]);
	}
}
