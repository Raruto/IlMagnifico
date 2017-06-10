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

}
