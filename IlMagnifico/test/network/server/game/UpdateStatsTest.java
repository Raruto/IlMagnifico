package test.network.server.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.model.Giocatore;
import main.model.SpazioAzione;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriGiocatori;
import main.model.enums.EFasiDiGioco;
import main.network.server.game.UpdateStats;

public class UpdateStatsTest {

	@Test
	public void getAzioneGiocatore() throws Exception {
		// Create Player
		Giocatore g = new Giocatore();

		// Create UpdateStats
		UpdateStats updateStats = new UpdateStats(g, EAzioniGiocatore.Produzione, new SpazioAzione());

		// Make start test
		assertTrue(EAzioniGiocatore.Produzione.equals(updateStats.getAzioneGiocatore()) == true);
	}

	@Test
	public void getNomeGiocatore() throws Exception {
		// Create Player
		Giocatore g = new Giocatore();
		g.setNome("foo");

		// Create UpdateStats
		UpdateStats updateStats = new UpdateStats(g, EAzioniGiocatore.Mercato, new SpazioAzione());

		// Make start test
		assertTrue("foo".equals(updateStats.getNomeGiocatore()) == true);

		g.setNome("boo");
		updateStats = new UpdateStats(g, EAzioniGiocatore.Famigliare, new SpazioAzione());

		assertTrue("foo".equals(updateStats.getNomeGiocatore()) == false);
	}

	@Test
	public void getColoreGiocatore() throws Exception {
		// Create Player
		Giocatore g = new Giocatore();
		g.setColore(EColoriGiocatori.GREEN);

		// Create UpdateStats
		UpdateStats updateStats = new UpdateStats(g, EAzioniGiocatore.PalazzoConsiglio, new SpazioAzione());

		// Make start test
		assertTrue(EColoriGiocatori.GREEN.equals(updateStats.getColoreGiocatore()) == true);

		g.setColore(EColoriGiocatori.BLUE);
		updateStats = new UpdateStats(g, EAzioniGiocatore.Produzione, new SpazioAzione());

		assertTrue(EColoriGiocatori.GREEN.equals(updateStats.getNomeGiocatore()) == false);
	}

	@Test
	public void getSpazioAzione() throws Exception {
		// Create Player
		Giocatore g = new Giocatore();
		SpazioAzione spazioAzione = new SpazioAzione();
		g.setSpazioAzione(spazioAzione);

		// Create UpdateStats
		UpdateStats updateStats = new UpdateStats(g, EAzioniGiocatore.Torre, spazioAzione);

		// Make start test
		assertTrue(spazioAzione.equals(updateStats.getSpazioAzione()) == true);

		spazioAzione = new SpazioAzione();
		assertTrue(spazioAzione.equals(updateStats.getSpazioAzione()) == false);
	}
	
	@Test
	public void getAzioneServer() throws Exception {
		// Create UpdateStats
		UpdateStats updateStats = new UpdateStats(EFasiDiGioco.InizioPartita, new SpazioAzione());

		// Make start test
		assertTrue(EFasiDiGioco.InizioPartita.equals(updateStats.getAzioneServer()) == true);
	}
}
