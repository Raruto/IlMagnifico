package test.network.server.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.network.NetworkException;
import main.network.server.game.Game;
import main.network.server.game.RemotePlayer;
import main.network.server.game.Room;
import main.network.server.game.UpdateStats;
import main.network.server.game.exceptions.GameException;
import main.network.server.game.exceptions.RoomFullException;

public class GametTest {

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
		game.inizializzaGiocatori();
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
			
			update=new UpdateStats(EAzioniGiocatore.Famigliare);
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
			
			update=new UpdateStats(EAzioniGiocatore.Famigliare);
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
		assertTrue(game.getTurno()==2);
	}

}
