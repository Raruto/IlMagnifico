package test.network.server.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.EScomuniche;
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
		System.out.println("il turno corrente è:"+game.getTurno());
		System.out.println("il periodo corrente è:"+game.getPeriodo());
		assertTrue(game.getTurno() == 3);
		assertTrue(game.getPeriodo() == 2);
	}

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
			System.out.println("il nome del prossimo giocatore è:"+game.giocatoreDelTurnoSuccessivo(game.getGiocatoreDiTurno()).getNome());
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
				&& player1.getScomunica(0).getNome()==EScomuniche.RICEVI_MENO_PM.getNome());
		assertTrue(player2.getPunti().getPuntiFede() == 0 && player2.getPunti().getPuntiVittoria() == 3);
	}
	

}
