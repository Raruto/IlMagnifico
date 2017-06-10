package test.network.server.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Giocatore;

public class GiocatoreTest {

	@Test
	public void checkPosizionato() throws Exception {
		// create player
		Giocatore giocatore = new Giocatore();

		// checkPosizionatoTest
		assertTrue(giocatore.checkPosizionato() == false);

		for (int i = 0; i < 4; i++) {
			giocatore.getFamigliare(i).setPosizionato(true);
		}

		assertTrue(giocatore.checkPosizionato() == true);
	}

	@Test
	public void calcolaPVFinali() {
		Giocatore giocatore = new Giocatore();
		giocatore.calcolaPVFinali();

		assertTrue(giocatore.getPunti().getPuntiVittoria() == 0);

		giocatore.getRisorse().cambiaLegno(5);
		giocatore.getRisorse().cambiaMonete(5);
		giocatore.getRisorse().cambiaPietre(5);
		giocatore.getRisorse().cambiaServitori(5);

		giocatore.calcolaPVFinali();

		assertTrue((giocatore.getPunti().getPuntiVittoria() == 4) == true);
	}

}
