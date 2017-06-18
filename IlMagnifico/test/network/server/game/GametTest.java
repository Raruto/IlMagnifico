package test.network.server.game;

import static org.junit.Assert.*;

import org.junit.Test;

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
	 * 
	 * @throws GameException
	 * @throws RoomFullException
	 */
	@Test
	public void testFinePeriodo() throws GameException, RoomFullException {
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo");
		player2.setNome("secondo");
		UpdateStats update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setNomeGiocatore("charlie");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		update.setColorePedina(EColoriPedine.Nera);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Nera);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Bianca);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Bianca);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Arancione);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Arancione);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setNomeGiocatore("charlie");
		update.setColorePedina(EColoriPedine.Nera);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Nera);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Bianca);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Bianca);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Arancione);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update.setColorePedina(EColoriPedine.Arancione);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setServitoriDaPagare(1);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.setColorePedina(EColoriPedine.Neutrale);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);

		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update.supportaChiesa(true);

		game.performGameAction(player1, update);
		update = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update.supportaChiesa(true);
		game.performGameAction(player2, update);

		assertTrue(game.getTurno() == 3);
		assertTrue(game.getPeriodo() == 2);
	}

	/**
	 * Test che verifica il corretto funzionamento della gestione del Rapporto
	 * col Vaticano
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testRapportoVaticano() throws RoomFullException, GameException {
		// invio all'utente la notifica che è il rapporto del vaticano
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);

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
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);

			for (int i = 1; i < 4; i++) {
				game.getGiocatoreDiTurno().getFamigliare(i).setPosizionato(true);
			}
			update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
			update.setColorePedina(EColoriPedine.Nera);
			update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
			game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		}
		UpdateStats update2 = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update2.supportaChiesa(true);
		game.performGameAction(player1, update2);

		update2 = new UpdateStats(EAzioniGiocatore.SostegnoChiesa);
		update2.supportaChiesa(true);
		game.performGameAction(player2, update2);

		assertTrue(player1.getPunti().getPuntiFede() == 0);
		assertTrue(player1.getPunti().getPuntiVittoria() == 0);
		System.out.println();
		System.out.println(player1.getScomunica(0).getNome() + " == " + EScomuniche.RICEVI_MENO_PM.getNome() + "?");
		System.out.println(player1.getScomunica(0).getNome().equals(EScomuniche.RICEVI_MENO_PM.getNome()));
		// MODIFICARE SE SI AGGIUNGONO ALTRE SCOMUNICHE...
		assertTrue(player1.getScomunica(0).getNome().equals(game.getScomuniche()[0]
				.getNome()/* EScomuniche.RICEVI_MENO_PM.getNome() */));
		assertTrue(player2.getPunti().getPuntiFede() == 0 && player2.getPunti().getPuntiVittoria() == 3);
	}

	/**
	 * Test che verifica il corretto funzionamento della eccezione SpaceTaken
	 * nel metodo OnMarket e della relativa gestione
	 * 
	 * @throws RoomFullException
	 */
	@Test
	public void testOnMarketExceptionSpaceTaken() throws RoomFullException {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
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
	 * 
	 * @throws RoomFullException
	 */
	@Test
	public void testOnMarketExceptionInvalidPosition() throws RoomFullException {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
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
	 * 
	 * @throws RoomFullException
	 */
	@Test
	public void testOnMarketExceptionInsufficientFamiliarValue() throws RoomFullException {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
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
	 * 
	 * @throws RoomFullException
	 */
	@Test
	public void testOnMarketExceptionInvalidChoice() throws RoomFullException {
		boolean exceptionThrown = false;
		String nomeGiocatoreEccezione = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
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
	 * @throws RoomFullException
	 * @throws GameException
	 * 
	 */
	@Test
	public void testOnMarket() throws RoomFullException, GameException {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();

		UpdateStats update = new UpdateStats(EAzioniGiocatore.Mercato);
		update.spostaPedina(EColoriPedine.Nera, 1);
		nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(nomeGiocatoreFamigliareSpostato
				.equals(game.getSpazioAzione().getMercato()[1].getGiocatore().getNome()));
	}

	/**
	 * Test per verificare il corretto funzionamento del metodo OnPayServant
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnPayServant() throws RoomFullException, GameException {
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.aumentaValorePedina(EColoriPedine.Neutrale, 2);
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getGiocatoreDiTurno().getFamigliare(3).getValore() == 2);
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onTower
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnTower() throws RoomFullException, GameException {
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Torre);
		update.spostaPedina(EColoriPedine.Nera, 0);
		ECostiCarte[] costi = { null, null };
		update.setScelteCosti(costi);
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getSpazioAzione().getCartaTorre(0) == null);
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onCouncilPalace
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnCouncilPalace() throws RoomFullException, GameException {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.PalazzoConsiglio);
		update.spostaPedina(EColoriPedine.Nera, 0);
		update.setSceltaConsiglio(ESceltePrivilegioDelConsiglio.LegnoEPietra);
		nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getSpazioAzione().getGiocatoriPalazzoDelConsiglio().get(0).getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onHarvestRound
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnHarvestRound() throws RoomFullException, GameException {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Raccolto);
		update.spostaPedina(EColoriPedine.Nera, 1);
		nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getSpazioAzione().getZonaRaccoltoRotonda().getGiocatore().getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}

	/**
	 * Test che verifica il corretto funzionamento del metodo onHarvestOval
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnHarvestOval() throws RoomFullException, GameException {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.aumentaValorePedina(EColoriPedine.Nera, 3);
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.RaccoltoOvale);
		update.spostaPedina(EColoriPedine.Nera, 0);
		nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getSpazioAzione().getZonaRaccoltoOvale().get(0).getGiocatore().getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}

	/**
	 * Test che verifica il corretto funzionamento del metoodo
	 * onProductionRoound
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnProductionRound() throws RoomFullException, GameException {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Produzione);
		update.spostaPedina(EColoriPedine.Nera, 1);
		nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getSpazioAzione().getZonaProduzioneRotonda().getGiocatore().getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}

	/**
	 * Test cche verifica il corretto funzionamento del metodo onProductionOval
	 * 
	 * @throws RoomFullException
	 * @throws GameException
	 */
	@Test
	public void testOnProductionOval() throws RoomFullException, GameException {
		String nomeGiocatoreFamigliareSpostato = "";
		TestPlayer player1 = new TestPlayer();
		TestPlayer player2 = new TestPlayer();
		player1.setNome("primo giocatore");
		player2.setNome("secondo giocatore");
		Room room = new Room(player1, 0, 0);

		room.joinPlayer(player2);
		Game game = new Game(room);
		game.startNewGame();
		UpdateStats update = new UpdateStats(EAzioniGiocatore.Famigliare);
		update.aumentaValorePedina(EColoriPedine.Nera, 3);
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		update = new UpdateStats(EAzioniGiocatore.ProduzioneOvale);
		update.spostaPedina(EColoriPedine.Nera, 0);
		nomeGiocatoreFamigliareSpostato = game.getGiocatoreDiTurno().getNome();
		game.performGameAction((RemotePlayer) game.getGiocatoreDiTurno(), update);
		assertTrue(game.getSpazioAzione().getZonaProduzioneOvale().get(0).getGiocatore().getNome()
				.equals(nomeGiocatoreFamigliareSpostato));
	}
}
