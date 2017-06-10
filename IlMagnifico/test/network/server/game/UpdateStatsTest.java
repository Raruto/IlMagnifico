package test.network.server.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.model.Giocatore;
import main.model.SpazioAzione;
import main.model.enums.EAzioniGiocatore;
import main.network.server.game.UpdateStats;

public class UpdateStatsTest {

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
		updateStats = new UpdateStats(g, EAzioniGiocatore.Mercato, new SpazioAzione());
		
		assertTrue("foo".equals(updateStats.getNomeGiocatore()) == false);
	}

}
