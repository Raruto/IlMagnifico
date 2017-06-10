package test.network.server.game;

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
		// TODO Auto-generated method stub

	}

	@Test
	public void testScegliOrdine() {
		spazioAzione = new SpazioAzione();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.giocatori.get(2).getFamigliare(0)
					.eseguiSpostamentoPalazzoConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		} catch (FamiliarAlreadyUsedException | InsufficientValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scegliOrdine();

		assertTrue(this.giocatori.get(0) == this.spazioAzione.getGiocatoriPalazzoDelConsiglio().get(0));
		assertTrue(this.giocatori.get(1) == this.spazioAzione.getGiocatoriPalazzoDelConsiglio().get(1));
	}

}
