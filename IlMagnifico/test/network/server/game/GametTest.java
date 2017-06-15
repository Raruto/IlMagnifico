package test.network.server.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.model.Carta;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.EScomuniche;
import main.model.errors.Errors;
import main.network.server.game.Game;
import main.network.server.game.RemotePlayer;
import main.network.server.game.Room;
import main.network.server.game.UpdateStats;
import main.network.server.game.exceptions.GameException;
import main.network.server.game.exceptions.RoomFullException;
import sun.misc.PerformanceLogger;

public class GametTest {

	/**
	 * Test che verifica che il turno avanzi effettivamente quando i giocatori
	 * terminano i famigliari da muovere
	 */
	@Test
	public void testGiroDiTurniTerminato() {
		// faccio avanzare lo stato del gioco fino a che non sia finito il
		// giroDiTurni
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo");
		player2.setNome("secondo");
		UpdateStats update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setNomeGiocatore("charlie");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		update.setColorePedina(EColoriPedine.Nera);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Nera);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Bianca);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Bianca);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Arancione);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Arancione);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// per verificare che il giro di turni sia terminato
		assertTrue(game.getTurno() == 2);
	}

	/**
	 * Test che verifica che il periodo effettivamente avanzi dopo avere
	 * eseguito la fase del Rapporto Col Vaticano
	 */
	@Test
	public void testFinePeriodo() {
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo");
		player2.setNome("secondo");
		UpdateStats update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setNomeGiocatore("charlie");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		update.setColorePedina(EColoriPedine.Nera);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Nera);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Bianca);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Bianca);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Arancione);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Arancione);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setNomeGiocatore("charlie");
		update.setColorePedina(EColoriPedine.Nera);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Nera);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Bianca);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Bianca);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Arancione);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update.setColorePedina(EColoriPedine.Arancione);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update.supportaChiesa(true);
		try {
			game.performGameAction(player1, update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update.supportaChiesa(true);
		try {
			game.performGameAction(player2, update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(game.getTurno() == 3);
		assertTrue(game.getPeriodo() == 2);
	}

	/**
	 * Test che verifica il corretto funzionamento della gestione del Rapporto
	 * col Vaticano
	 */
	@Test
	public void testRapportoVaticano() {
		// invio all'utente la notifica che è il rapporto del vaticano
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Game game = new Game(room);
		game.startNewGame();
		player2.getPunti().cambiaPuntiFede(3);
		// mi metto nella situazione del rapporto del vaticano
		// tutti i giocatori hanno posizionato tutte le pedine, tranne una che
		// sposto nell'ultima mossa
		for (int j = 0; j < 2; j++) {
			for (int i = 1; i < 4; i++) {
				game.getGiocatoreDiTurno().getFamigliare(i).setPosizionato(true);
			}
			UpdateStats update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
			update.setColorePedina(EColoriPedine.Nera);
			update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
			try {
				game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
			} catch (GameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 1; i < 4; i++) {
				game.getGiocatoreDiTurno().getFamigliare(i).setPosizionato(true);
			}
			update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
			update.setColorePedina(EColoriPedine.Nera);
			update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
			try {
				game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
			} catch (GameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		UpdateStats update2 = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update2.supportaChiesa(true);
		try {
			game.performGameAction(player1, update2);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		update2 = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update2.supportaChiesa(true);
		try {
			game.performGameAction(player2, update2);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(player1.getPunti().getPuntiFede() == 0 && player1.getPunti().getPuntiVittoria() == 0
				&& player1.getScomunica(0).getNome() == EScomuniche.RICEVI_MENO_PM.getNome());
		assertTrue(player2.getPunti().getPuntiFede() == 0 && player2.getPunti().getPuntiVittoria() == 3);
	}

	/**
	 * Test che verifica il corretto funzionamento della eccezione SpaceTaken
	 * nel metodo OnMarket e della relativa gestione
	 */
	@Test
	public void testOnMarketExceptionSpaceTaken() {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();

		UpdateStats update = new UpdateStats(EAzioniGiocatore.Mercato);
		update.spostaPedina(EColoriPedine.Nera, 1);
		update.setSceltePrivilegiConsiglio(null);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown == false);

		try {
			nomeGiocatoreEccezione = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			exceptionThrown = true;
			assertTrue(e.getMessage() == Errors.SPACE_TAKEN.toString());
		}
		assertTrue(nomeGiocatoreEccezione == game.getGiocatoreDiTurno().getNome());
		assertTrue(exceptionThrown);
	}

	/**
	 * Test che verifica il corretto funzionamento della eccezione
	 * InvalidPosition nel metodo onMarket e della relativa gestione
	 */
	@Test
	public void testOnMarketExceptionInvalidPosition() {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();

		UpdateStats update = new UpdateStats(EAzioniGiocatore.Mercato);
		update.spostaPedina(EColoriPedine.Nera, 1);
		update.setSceltePrivilegiConsiglio(null);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown == false);
		update.spostaPedina(EColoriPedine.Nera, 10);
		try {
			nomeGiocatoreEccezione = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			exceptionThrown = true;
			assertTrue(e.getMessage() == Errors.INVALID_POSTITION.toString());
		}
		assertTrue(nomeGiocatoreEccezione == game.getGiocatoreDiTurno().getNome());
		assertTrue(exceptionThrown);
	}

	/**
	 * Test che verifica il corretto funzionamento della eccezione
	 * InsufficientFamiliarValue nel metodo onMarket e della relativa gestione
	 */
	@Test
	public void testOnMarketExceptionInsufficientFamiliarValue() {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();

		UpdateStats update = new UpdateStats(EAzioniGiocatore.Mercato);
		update.spostaPedina(EColoriPedine.Nera, 1);
		update.setSceltePrivilegiConsiglio(null);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown == false);
		update.spostaPedina(EColoriPedine.Neutrale, 0);

		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			assertTrue(e.getMessage() == Errors.INSUFFICIENT_FAMILIAR_VALUE.toString());
			exceptionThrown = true;
			nomeGiocatoreEccezione = game.getGiocatoreDiTurno().getNome();
		}
		assertTrue(nomeGiocatoreEccezione == game.getGiocatoreDiTurno().getNome());
		assertTrue(exceptionThrown == true);

	}

	/**
	 * Metodo che verifica il corretto funzioonamento della eccezione
	 * InvalidChoiche nel metodo onMarket e della relativa eccezione
	 */
	@Test
	public void testOnMarketExceptionInvalidChoice() {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();

		UpdateStats update = new UpdateStats(EAzioniGiocatore.Mercato);
		update.spostaPedina(EColoriPedine.Nera, 1);
		update.setSceltePrivilegiConsiglio(null);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown == false);
		update.spostaPedina(EColoriPedine.Nera, 3);
		ESceltePrivilegioDelConsiglio[] scelte = { ESceltePrivilegioDelConsiglio.LegnoEPietra,
				ESceltePrivilegioDelConsiglio.LegnoEPietra };
		update.setSceltePrivilegiConsiglio(scelte);

		try {
			nomeGiocatoreEccezione = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			assertTrue(e.getMessage() == Errors.INVALID_CHOICE.toString());
			exceptionThrown = true;
		}
		assertTrue(nomeGiocatoreEccezione == game.getGiocatoreDiTurno().getNome());
		assertTrue(exceptionThrown);
	}

	/**
	 * Test che verifica l'effettivo spostamento del famigliare quando è
	 * invocato il metodo onMarket.
	 * 
	 */
	@Test
	public void testOnMarket() {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();

		UpdateStats update = new UpdateStats(EAzioniGiocatore.Mercato);
		update.spostaPedina(EColoriPedine.Nera, 1);
		try {
			nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(nomeGiocatoreFamigliareSpostato
				.equals(game.getSpazioAzione().getMercato()[1].getGiocatore().getNome()));
	}

	/**
	 * Test per verificare il corretto funzionamento del metodo OnPayServant
	 */
	@Test
	public void testOnPayServant() {
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.aumentaValorePedina(EColoriPedine.Neutrale, 2);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(game.getGiocatoreDiTurno().getFamigliare(3).getValore() == 2);
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onTower
	 */
	@Test
	public void testOnTower() {
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Torre);
		update.spostaPedina(EColoriPedine.Nera, 0);
		ECostiCarte[] costi = { null, null };
		update.setScelteCosti(costi);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
		}
		assertTrue(game.getSpazioAzione().getCartaTorre(0) == null);
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onCouncilPalace
	 */
	@Test
	public void testOnCouncilPalace() {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.spostaPedina(EColoriPedine.Nera, 0);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		try {
			nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(game.getSpazioAzione().getGiocatoriPalazzoDelConsiglio().get(0).getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onHarvestRound
	 */
	@Test
	public void testOnHarvestRound() {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Raccolto);
		update.spostaPedina(EColoriPedine.Nera, 1);
		try {
			nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(game.getSpazioAzione().getZonaRaccoltoRotonda().getGiocatore().getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onHarvestOval
	 */
	@Test
	public void testOnHarvestOval() {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		try {
			room.joinPlayer(player2);
		} catch (RoomFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.aumentaValorePedina(EColoriPedine.Nera, 3);
		try {
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update = new UpdateStats(EAzioniGiocatore.RaccoltoOvale);
		update.spostaPedina(EColoriPedine.Nera, 0);
		try {
			nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		} catch (GameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(game.getSpazioAzione().getZonaRaccoltoOvale().get(0).getGiocatore().getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}
}
