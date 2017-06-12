package test.model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.Famigliare;
import main.model.Giocatore;
import main.model.exceptions.NoEnoughResourcesException;

public class GiocatoreTest {

	/**
	 * Metodo che verifica il corretto funzionamento del metodo checkPosizionato
	 */
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

	/**
	 * Test per verificare il corretto funzionamento del metodo sia nel calcolo
	 * della classifica dei punti militari sia nel calcolo della classifica dei
	 * unti vittoria
	 */
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

	/**
	 * Test he verifica il corretto funzionamento del metodo pagaServitore e
	 * dello scatenamento della relativa eccezione nei casi opportuni
	 */
	@Test
	public void testPagaServitore() {
		boolean controlloEccezione = false;
		Giocatore giocatore = new Giocatore();
		giocatore.getRisorse().cambiaServitori(1);
		Famigliare famigliare = new Famigliare(giocatore, 0, false);
		try {
			giocatore.pagaServitore(famigliare, 1);
		} catch (NoEnoughResourcesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(giocatore.getRisorse().getServitori() == 0);
		assertTrue(famigliare.getValore() == 1);

		try {
			giocatore.pagaServitore(famigliare, 1);
		} catch (NoEnoughResourcesException e) {
			controlloEccezione = true;
		}
		assertTrue(controlloEccezione == true);
	}
}
