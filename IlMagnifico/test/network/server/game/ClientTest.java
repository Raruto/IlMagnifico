package test.network.server.game;

import static org.junit.Assert.*;

import org.junit.Test;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.network.client.Client;
import main.network.client.ClientException;
import main.network.server.Server;
import main.network.server.ServerException;
import main.ui.cli.CLI;
import main.util.Costants;

public class ClientTest {
	Client client1, client2, client3, client4, client5;
	Server server;
	String nomeGiocatore;
	String nomeLastUpdate;
	int sec = Costants.CONNECTION_RETRY_SECONDS * 1000;

	/**
	 * Test che verifica il funzionamento della comunicazione nel metodo
	 * relativo allo spostamento di un famigliare su una zona mercato
	 */
	public void testMovePawnMarket() {

		nomeGiocatore = client5.getNickname();
		client5.movePawn(EAzioniGiocatore.Mercato, EColoriPedine.Nera, 1,
				new ESceltePrivilegioDelConsiglio[] { null, null });
		nomeLastUpdate = client5.getLatestUpdate().getNomeGiocatore();

	}

	/**
	 * Metodo per verificare il funzionamento della comunicazione nel metodo
	 * relativo allo spostamento del famigliare in una zona Raccolto
	 */
	public void testMovePawnHarvest() {

		nomeGiocatore = client4.getNickname();
		client4.movePawn(EAzioniGiocatore.Raccolto, EColoriPedine.Nera, 1, new EEffettiPermanenti[] { null, null });
		nomeLastUpdate = client4.getLatestUpdate().getNomeGiocatore();

	}

	/**
	 * Metodo per verificare il funzionamento della comunicazione nel metodo
	 * relativo allo spostamento del famigliare in una zona Produzione
	 */
	public void testMovePawnProduction() {

		nomeGiocatore = client3.getNickname();
		client3.movePawn(EAzioniGiocatore.Produzione, EColoriPedine.Nera, 1, new EEffettiPermanenti[] { null, null });
		nomeLastUpdate = client3.getLatestUpdate().getNomeGiocatore();

	}

	/**
	 * Metodo per verificare il funzionamento della comunicazione nel metodo
	 * relativo allo spostamento del famigliare in una zona Torre senza la
	 * scelta del costo da pagare
	 */
	public void testMovePawnTowerNoCostChoice() {

		nomeGiocatore = client2.getNickname();
		client2.movePawn(EAzioniGiocatore.Torre, EColoriPedine.Nera, 1);
		nomeLastUpdate = client2.getLatestUpdate().getNomeGiocatore();

	}

	/**
	 * Metodo per verificare il funzionamento della comunicazione nel metodo
	 * relativo allo spostamento del famigliare in una zona Torre con la scelta
	 * del costo alternativo da pagare
	 */

	public void testMovePawnTowerCostChoice() {

		nomeGiocatore = client1.getNickname();
		client1.movePawn(EAzioniGiocatore.Torre, EColoriPedine.Nera, 4, new ECostiCarte[] { null, null });
		nomeLastUpdate = client1.getLatestUpdate().getNomeGiocatore();
	}

	@Test
	public void ordineEsecuzione() {
		final int SOCKET_PORT = 2004, RMI_PORT = 2005;
		try {
			Server server = new Server();
			server.startServer(SOCKET_PORT, RMI_PORT);
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String nomeGiocatore = "";
		String nomeLastUpdate = "";

		try {
			client1 = new Client(new CLI());
			client1.startClient("RMI", Costants.SERVER_ADDRESS, SOCKET_PORT, RMI_PORT);
			client1.loginPlayer("primo giocatore- ,movePawnTowerCostChoice");
			Thread.sleep(sec);

			client2 = new Client(new CLI());
			client2.startClient("SOCKET", Costants.SERVER_ADDRESS, SOCKET_PORT, RMI_PORT);
			client2.loginPlayer("secondo giocatore- movePawnTowerNoCostChoice");
			Thread.sleep(sec);

			client3 = new Client(new CLI());
			client3.startClient("RMI", Costants.SERVER_ADDRESS, SOCKET_PORT, RMI_PORT);
			client3.loginPlayer("terzo giocatore- movePawnProduction");
			Thread.sleep(sec);

			client4 = new Client(new CLI());
			client4.startClient("SOCKET", Costants.SERVER_ADDRESS, SOCKET_PORT, RMI_PORT);
			client4.loginPlayer("quarto giocatore- movePawnHarvest");
			Thread.sleep(sec);

			client5 = new Client(new CLI());
			client5.startClient("RMI", Costants.SERVER_ADDRESS, SOCKET_PORT, RMI_PORT);
			client5.loginPlayer("quinto giocatore- movePawnMarket");
			Thread.sleep(sec);

		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e1) {

		}
		System.out.println("il giocatore di turno è:"+client1.getPlayerTurn());
		for (int i = 0; i < 5; i++) {
			if (client1.getNickname().equals(client1.getPlayerTurn()))
				testMovePawnTowerCostChoice();
			else if (client2.getNickname().equals(client2.getPlayerTurn()))
				testMovePawnTowerNoCostChoice();
			else if (client3.getNickname().equals(client3.getPlayerTurn()))
				testMovePawnProduction();
			else if (client4.getNickname().equals(client4.getPlayerTurn()))
				testMovePawnHarvest();
			else if (client5.getNickname().equals(client5.getPlayerTurn()))
				testMovePawnMarket();

			assertTrue(nomeGiocatore.equals(nomeLastUpdate));
		}
	}
}
