package main.ui.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.model.Edificio;
import main.model.Impresa;
import main.model.Personaggio;
import main.model.Scomunica;
import main.model.Territorio;
import main.model.enums.EAzioniGiocatore;
import main.model.enums.ECarte;
import main.model.enums.EColoriGiocatori;
import main.model.enums.EColoriPedine;
import main.model.enums.ECostiCarte;
import main.model.enums.EEffettiPermanenti;
import main.model.enums.ESceltePrivilegioDelConsiglio;
import main.model.enums.EScomuniche;
import main.network.client.Client;
import main.network.client.ClientException;
import main.network.client.IClient;
import main.network.server.game.UpdateStats;
import main.ui.gui.aggiornamento.Aggiornamento;
import main.ui.gui.aggiornamento.SFamigliare;
import main.ui.gui.aggiornamento.SGiocatore;
import main.ui.gui.aggiornamento.SPunti;
import main.ui.gui.aggiornamento.SRisorse;
import main.ui.gui.components.ButtonLIM;
import main.util.ANSI;
import main.util.Costants;
import res.images.Resources;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Frame extends JFrame implements IClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7802964308401362865L;
	private Frame frame = this;
	private PrivilegioConsiglio framePrivilegioConsiglio = new PrivilegioConsiglio(this);
	private SceltaCosti frameSceltaCosti = new SceltaCosti(this);
	private SceltaEffettiPermanenti frameSceltaEffettiPermanenti = new SceltaEffettiPermanenti(this);
	private SceltaSupportoChiesa frameSceltaSupportoChiesa = new SceltaSupportoChiesa(this);

	private JPanel contentPane;
	private ButtonLIM btnMostraTabellone = new ButtonLIM();
	private ButtonLIM btnMostraPlancia = new ButtonLIM();
	private ButtonLIM btnMostraPlanciaAvversari = new ButtonLIM();
	// private ButtonLIM btnPassaTurno = new ButtonLIM();
	private JLabel lblTextLogger;

	private UsernameFrame Userframe;

	private int turno = 0;
	private int numeroGiocatoriPartita = 2;
	private String nomeGiocatore;
	private String colore;
	private ArrayList<String> nomeGiocatoriPartita;

	private Tabellone tabellone;
	private Plancia plancia;
	private PlanciaAvversario planciaAvversari;

	private static Client client;

	/**
	 * Get Singleton Client
	 * 
	 * @return {@link Client}
	 */
	public Client getClient() {
		if (client == null) {
			try {
				client = new Client(this);
			} catch (ClientException e) {
				e.printStackTrace();
				System.err.println("Exiting...");
			}
		}
		return client;
	}

	// ATTRIBUTI PER AZIONI
	private boolean servitoreSelezionato = false;
	private Famigliare famigliareSelezionato = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Frame frame = new Frame();
					frame.getClient();
					frame.Userframe = new UsernameFrame(frame);
					frame.Userframe.setVisible(true);
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*
				 * try { Frame frame = new Frame();
				 * 
				 * client = CLI.mainClient(Costants.SERVER_ADDRESS,
				 * Costants.SOCKET_PORT, Costants.RMI_PORT, frame); while
				 * (!client.isGameStarted()) { try { Thread.sleep(2000); } catch
				 * (InterruptedException e) { // TODO Auto-generated catch block
				 * } }
				 * 
				 * frame.setVisible(true); } catch (Exception e) {
				 * e.printStackTrace(); }
				 */
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setIconImage(new ImageIcon(Resources.class.getResource(Costants.FOLDER_BASE + "/giglio.png")).getImage());
		setTitle("         lorenzo il magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(null);

		inserisciLabelTextLogger();

		/*
		 * AGGIORNAMENTO PER INIZIALIZZAZIONE
		 */

		// ESEMPIO

		nomeGiocatore = getClient().getNickname();
		colore = "rosso";
		HashMap<String, EColoriGiocatori> coloriGiocatori = getClient().getPlayersColors();
		if (EColoriGiocatori.RED == coloriGiocatori.get(nomeGiocatore))
			colore = "rosso";
		else if (EColoriGiocatori.BLUE == coloriGiocatori.get(nomeGiocatore))
			colore = "blu";
		else if (EColoriGiocatori.GREEN == coloriGiocatori.get(nomeGiocatore))
			colore = "verde";
		else if (EColoriGiocatori.YELLOW == coloriGiocatori.get(nomeGiocatore))
			colore = "giallo";

		// aggiornamento();
	}

	public void aggiornamento(UpdateStats update) {
		ArrayList<String> nomeGiocatori = this.nomeGiocatoriPartita;
		numeroGiocatoriPartita = nomeGiocatori.size();

		// CONVERSIONE GIOCATORI

		ArrayList<SGiocatore> giocatori = new ArrayList<SGiocatore>();
		for (int i = 0; i < numeroGiocatoriPartita; i++) {

			// SCOMUNICHE
			boolean[] scomuniche = new boolean[3];
			Scomunica[] scomunicheModel = getClient().getPlayersExcommunications().get(nomeGiocatori.get(i));
			for (int j = 0; j < 3; j++) {
				if (scomunicheModel[j] != null && scomunicheModel[j].getNome().length() != 0)
					scomuniche[j] = true;
				else
					scomuniche[j] = false;
			}

			// FAMIGLIARI PLANCIA GIOCATORE
			ArrayList<SFamigliare> fp = new ArrayList<SFamigliare>();
			main.model.Famigliare f0, f1, f2, f3;
			if (nomeGiocatori.get(i).equals(nomeGiocatore)) {
				main.model.Famigliare[] famigliariModel = getClient().getPlayersFamilies().get(nomeGiocatori.get(i));
				for (int j = 0; j < famigliariModel.length; j++) {
					if (famigliariModel[j].getPosizionato())
						fp.add(null);
					else {
						int numero = 0;
						if (EColoriPedine.Nera == famigliariModel[j].getColoreFamigliare())
							numero = 0;
						else if (EColoriPedine.Arancione == famigliariModel[j].getColoreFamigliare())
							numero = 1;
						else if (EColoriPedine.Bianca == famigliariModel[j].getColoreFamigliare())
							numero = 2;
						else
							numero = 3;
						fp.add(new SFamigliare(famigliariModel[j].getValore(), numero, colore, nomeGiocatore));
					}
				}
			}

			// CARTE SVILUPPO

			ArrayList<String> carteEdificio = new ArrayList<String>();
			ArrayList<String> carteTerritorio = new ArrayList<String>();
			ArrayList<String> cartePersonaggio = new ArrayList<String>();
			ArrayList<String> carteImprese = new ArrayList<String>();

			ArrayList<Edificio> edificiModel = getClient().getPlayersDashboards().get(nomeGiocatori.get(i))
					.getEdifici();
			for (int j = 0; j < edificiModel.size(); j++) {
				carteEdificio.add(edificiModel.get(j).getNome());
			}

			ArrayList<Territorio> territoriModel = getClient().getPlayersDashboards().get(nomeGiocatori.get(i))
					.getTerritori();
			for (int j = 0; j < territoriModel.size(); j++) {
				carteTerritorio.add(territoriModel.get(j).getNome());
			}

			ArrayList<Personaggio> personaggiModel = getClient().getPlayersDashboards().get(nomeGiocatori.get(i))
					.getPersonaggi();
			for (int j = 0; j < personaggiModel.size(); j++) {
				cartePersonaggio.add(personaggiModel.get(j).getNome());
			}

			ArrayList<Impresa> impreseModel = getClient().getPlayersDashboards().get(nomeGiocatori.get(i)).getImprese();
			for (int j = 0; j < impreseModel.size(); j++) {
				carteImprese.add(impreseModel.get(j).getNome());
			}

			// CREAZIONE GIOCATORE

			String coloreGiocatore = "";
			HashMap<String, EColoriGiocatori> coloriGiocatori = getClient().getPlayersColors();
			if (EColoriGiocatori.RED == coloriGiocatori.get(nomeGiocatori.get(i)))
				coloreGiocatore = "rosso";
			else if (EColoriGiocatori.BLUE == coloriGiocatori.get(nomeGiocatori.get(i)))
				coloreGiocatore = "blu";
			else if (EColoriGiocatori.GREEN == coloriGiocatori.get(nomeGiocatori.get(i)))
				coloreGiocatore = "verde";
			else if (EColoriGiocatori.YELLOW == coloriGiocatori.get(nomeGiocatori.get(i)))
				coloreGiocatore = "giallo";

			// update.getPuntiGiocatori()

			SGiocatore g = new SGiocatore(nomeGiocatori.get(i), coloreGiocatore,
					new SPunti(getClient().getPlayersPoints().get(nomeGiocatori.get(i)).getPuntiVittoria(),
							getClient().getPlayersPoints().get(nomeGiocatori.get(i)).getPuntiMilitari(),
							getClient().getPlayersPoints().get(nomeGiocatori.get(i)).getPuntiFede()),
					new SRisorse(getClient().getPlayersResources().get(nomeGiocatori.get(i)).getMonete(),
							getClient().getPlayersResources().get(nomeGiocatori.get(i)).getLegno(),
							getClient().getPlayersResources().get(nomeGiocatori.get(i)).getPietre(),
							getClient().getPlayersResources().get(nomeGiocatori.get(i)).getServitori()),
					scomuniche, fp, carteTerritorio, carteEdificio, carteImprese, cartePersonaggio);
			giocatori.add(g);

		}

		// FAMIGLIARI SULLA TORRE

		SFamigliare[] torre = new SFamigliare[16];
		for (int i = 0; i < 16; i++) {
			int posizione = (i / 4) * 4 + (4 - i % 4) - 1;
			main.model.Famigliare famigliareTorre = null;
			if (update.getSpazioAzione() != null)
				famigliareTorre = update.getSpazioAzione().getFamigliareTorre(posizione);

			if (famigliareTorre == null)
				torre[i] = null;
			else {
				int numero = 0;
				if (EColoriPedine.Nera == famigliareTorre.getColoreFamigliare())
					numero = 0;
				else if (EColoriPedine.Arancione == famigliareTorre.getColoreFamigliare())
					numero = 1;
				else if (EColoriPedine.Bianca == famigliareTorre.getColoreFamigliare())
					numero = 2;
				else
					numero = 3;

				String coloreGiocatoreFamigliare = "";
				if (famigliareTorre.getGiocatore().getColore() == EColoriGiocatori.RED)
					coloreGiocatoreFamigliare = "rosso";
				else if (famigliareTorre.getGiocatore().getColore() == EColoriGiocatori.BLUE)
					coloreGiocatoreFamigliare = "blu";
				else if (famigliareTorre.getGiocatore().getColore() == EColoriGiocatori.GREEN)
					coloreGiocatoreFamigliare = "verde";
				else if (famigliareTorre.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
					coloreGiocatoreFamigliare = "giallo";

				torre[i] = new SFamigliare(famigliareTorre.getValore(), numero, coloreGiocatoreFamigliare,
						famigliareTorre.getGiocatore().getNome());
			}
		}

		// FAMIGLIARI SUL MERCATO

		SFamigliare[] mercato = new SFamigliare[4];
		for (int i = 0; i < 4; i++) {
			main.model.Famigliare famigliareMercato = null;
			if (update.getSpazioAzione() != null)
				famigliareMercato = update.getSpazioAzione().getMercato()[i];

			if (famigliareMercato == null)
				mercato[i] = null;
			else {
				int numero = 0;
				if (EColoriPedine.Nera == famigliareMercato.getColoreFamigliare())
					numero = 0;
				else if (EColoriPedine.Arancione == famigliareMercato.getColoreFamigliare())
					numero = 1;
				else if (EColoriPedine.Bianca == famigliareMercato.getColoreFamigliare())
					numero = 2;
				else
					numero = 3;

				String coloreGiocatoreFamigliare = "";
				if (famigliareMercato.getGiocatore().getColore() == EColoriGiocatori.RED)
					coloreGiocatoreFamigliare = "rosso";
				else if (famigliareMercato.getGiocatore().getColore() == EColoriGiocatori.BLUE)
					coloreGiocatoreFamigliare = "blu";
				else if (famigliareMercato.getGiocatore().getColore() == EColoriGiocatori.GREEN)
					coloreGiocatoreFamigliare = "verde";
				else if (famigliareMercato.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
					coloreGiocatoreFamigliare = "giallo";

				mercato[i] = new SFamigliare(famigliareMercato.getValore(), numero, coloreGiocatoreFamigliare,
						famigliareMercato.getGiocatore().getNome());
			}
		}

		// FAMIGLIARE SUL RACCOLTO ROTONDO

		SFamigliare raccoltoRotondo;

		main.model.Famigliare famigliareRaccoltoRotondoModel = null;
		if (update.getSpazioAzione() != null)
			famigliareRaccoltoRotondoModel = update.getSpazioAzione().getZonaRaccoltoRotonda();
		if (famigliareRaccoltoRotondoModel == null)
			raccoltoRotondo = null;
		else {
			int numero = 0;
			if (EColoriPedine.Nera == famigliareRaccoltoRotondoModel.getColoreFamigliare())
				numero = 0;
			else if (EColoriPedine.Arancione == famigliareRaccoltoRotondoModel.getColoreFamigliare())
				numero = 1;
			else if (EColoriPedine.Bianca == famigliareRaccoltoRotondoModel.getColoreFamigliare())
				numero = 2;
			else
				numero = 3;

			String coloreGiocatoreFamigliare = "";
			if (famigliareRaccoltoRotondoModel.getGiocatore().getColore() == EColoriGiocatori.RED)
				coloreGiocatoreFamigliare = "rosso";
			else if (famigliareRaccoltoRotondoModel.getGiocatore().getColore() == EColoriGiocatori.BLUE)
				coloreGiocatoreFamigliare = "blu";
			else if (famigliareRaccoltoRotondoModel.getGiocatore().getColore() == EColoriGiocatori.GREEN)
				coloreGiocatoreFamigliare = "verde";
			else if (famigliareRaccoltoRotondoModel.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
				coloreGiocatoreFamigliare = "giallo";

			raccoltoRotondo = new SFamigliare(famigliareRaccoltoRotondoModel.getValore(), numero,
					coloreGiocatoreFamigliare, famigliareRaccoltoRotondoModel.getGiocatore().getNome());
		}

		// FAMIGLIARE SUL RACCOLTO OVALE

		ArrayList<SFamigliare> raccoltoOvale = new ArrayList<SFamigliare>();
		if (update.getSpazioAzione() != null)
			for (int i = 0; i < update.getSpazioAzione().getZonaRaccoltoOvale().size(); i++) {
				main.model.Famigliare famigliareRaccoltoOvaleModel = null;
				if (update.getSpazioAzione() != null)
					famigliareRaccoltoOvaleModel = update.getSpazioAzione().getZonaRaccoltoOvale().get(i);
				if (famigliareRaccoltoOvaleModel == null)
					raccoltoOvale.add(null);
				else {
					int numero = 0;
					if (EColoriPedine.Nera == famigliareRaccoltoOvaleModel.getColoreFamigliare())
						numero = 0;
					else if (EColoriPedine.Arancione == famigliareRaccoltoOvaleModel.getColoreFamigliare())
						numero = 1;
					else if (EColoriPedine.Bianca == famigliareRaccoltoOvaleModel.getColoreFamigliare())
						numero = 2;
					else
						numero = 3;

					String coloreGiocatoreFamigliare = "";
					if (famigliareRaccoltoOvaleModel.getGiocatore().getColore() == EColoriGiocatori.RED)
						coloreGiocatoreFamigliare = "rosso";
					else if (famigliareRaccoltoOvaleModel.getGiocatore().getColore() == EColoriGiocatori.BLUE)
						coloreGiocatoreFamigliare = "blu";
					else if (famigliareRaccoltoOvaleModel.getGiocatore().getColore() == EColoriGiocatori.GREEN)
						coloreGiocatoreFamigliare = "verde";
					else if (famigliareRaccoltoOvaleModel.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
						coloreGiocatoreFamigliare = "giallo";

					raccoltoOvale.add(new SFamigliare(famigliareRaccoltoOvaleModel.getValore(), numero,
							coloreGiocatoreFamigliare, famigliareRaccoltoOvaleModel.getGiocatore().getNome()));
				}
			}

		// FAMIGLIARE SU PRODUZIONE ROTONDO

		SFamigliare produzioneRotondo;
		main.model.Famigliare famigliareProduzioneRotondoModel = null;
		if (update.getSpazioAzione() != null)
			famigliareProduzioneRotondoModel = update.getSpazioAzione().getZonaProduzioneRotonda();
		if (famigliareProduzioneRotondoModel == null)
			produzioneRotondo = null;
		else {
			int numero = 0;
			if (EColoriPedine.Nera == famigliareProduzioneRotondoModel.getColoreFamigliare())
				numero = 0;
			else if (EColoriPedine.Arancione == famigliareProduzioneRotondoModel.getColoreFamigliare())
				numero = 1;
			else if (EColoriPedine.Bianca == famigliareProduzioneRotondoModel.getColoreFamigliare())
				numero = 2;
			else
				numero = 3;

			String coloreGiocatoreFamigliare = "";
			if (famigliareProduzioneRotondoModel.getGiocatore().getColore() == EColoriGiocatori.RED)
				coloreGiocatoreFamigliare = "rosso";
			else if (famigliareProduzioneRotondoModel.getGiocatore().getColore() == EColoriGiocatori.BLUE)
				coloreGiocatoreFamigliare = "blu";
			else if (famigliareProduzioneRotondoModel.getGiocatore().getColore() == EColoriGiocatori.GREEN)
				coloreGiocatoreFamigliare = "verde";
			else if (famigliareProduzioneRotondoModel.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
				coloreGiocatoreFamigliare = "giallo";

			produzioneRotondo = new SFamigliare(famigliareProduzioneRotondoModel.getValore(), numero,
					coloreGiocatoreFamigliare, famigliareProduzioneRotondoModel.getGiocatore().getNome());
		}

		// FAMIGLIARE SU PRODUZIONE OVALE

		ArrayList<SFamigliare> produzioneOvale = new ArrayList<SFamigliare>();
		if (update.getSpazioAzione() != null)
			for (int i = 0; i < update.getSpazioAzione().getZonaProduzioneOvale().size(); i++) {
				main.model.Famigliare famigliareProduzioneOvaleModel = null;
				if (update.getSpazioAzione() != null)
					famigliareProduzioneOvaleModel = update.getSpazioAzione().getZonaProduzioneOvale().get(i);
				if (famigliareProduzioneOvaleModel == null)
					produzioneOvale.add(null);
				else {
					int numero = 0;
					if (EColoriPedine.Nera == famigliareProduzioneOvaleModel.getColoreFamigliare())
						numero = 0;
					else if (EColoriPedine.Arancione == famigliareProduzioneOvaleModel.getColoreFamigliare())
						numero = 1;
					else if (EColoriPedine.Bianca == famigliareProduzioneOvaleModel.getColoreFamigliare())
						numero = 2;
					else
						numero = 3;

					String coloreGiocatoreFamigliare = "";
					if (famigliareProduzioneOvaleModel.getGiocatore().getColore() == EColoriGiocatori.RED)
						coloreGiocatoreFamigliare = "rosso";
					else if (famigliareProduzioneOvaleModel.getGiocatore().getColore() == EColoriGiocatori.BLUE)
						coloreGiocatoreFamigliare = "blu";
					else if (famigliareProduzioneOvaleModel.getGiocatore().getColore() == EColoriGiocatori.GREEN)
						coloreGiocatoreFamigliare = "verde";
					else if (famigliareProduzioneOvaleModel.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
						coloreGiocatoreFamigliare = "giallo";

					produzioneOvale.add(new SFamigliare(famigliareProduzioneOvaleModel.getValore(), numero,
							coloreGiocatoreFamigliare, famigliareProduzioneOvaleModel.getGiocatore().getNome()));
				}
			}

		// FAMIGLIARE SU PALAZZO DEL CONSIGLIO

		ArrayList<SFamigliare> palazzoConsiglio = new ArrayList<SFamigliare>();
		if (update.getSpazioAzione() != null)
			for (int i = 0; i < update.getSpazioAzione().getPalazzoDelConsiglio().size(); i++) {
				main.model.Famigliare famigliarePalazzoConsiglioModel = null;
				if (update.getSpazioAzione() != null)
					famigliarePalazzoConsiglioModel = update.getSpazioAzione().getPalazzoDelConsiglio().get(i);
				if (famigliarePalazzoConsiglioModel == null)
					palazzoConsiglio.add(null);
				else {
					int numero = 0;
					if (EColoriPedine.Nera == famigliarePalazzoConsiglioModel.getColoreFamigliare())
						numero = 0;
					else if (EColoriPedine.Arancione == famigliarePalazzoConsiglioModel.getColoreFamigliare())
						numero = 1;
					else if (EColoriPedine.Bianca == famigliarePalazzoConsiglioModel.getColoreFamigliare())
						numero = 2;
					else
						numero = 3;

					String coloreGiocatoreFamigliare = "";
					if (famigliarePalazzoConsiglioModel.getGiocatore().getColore() == EColoriGiocatori.RED)
						coloreGiocatoreFamigliare = "rosso";
					else if (famigliarePalazzoConsiglioModel.getGiocatore().getColore() == EColoriGiocatori.BLUE)
						coloreGiocatoreFamigliare = "blu";
					else if (famigliarePalazzoConsiglioModel.getGiocatore().getColore() == EColoriGiocatori.GREEN)
						coloreGiocatoreFamigliare = "verde";
					else if (famigliarePalazzoConsiglioModel.getGiocatore().getColore() == EColoriGiocatori.YELLOW)
						coloreGiocatoreFamigliare = "giallo";

					palazzoConsiglio.add(new SFamigliare(famigliarePalazzoConsiglioModel.getValore(), numero,
							coloreGiocatoreFamigliare, famigliarePalazzoConsiglioModel.getGiocatore().getNome()));
				}
			}

		// CARTE SCOMUNICA
		EScomuniche[] es = EScomuniche.values();
		Scomunica[] scomuniche = getClient().getExcommunications();
		String[] carteScomunica = new String[scomuniche.length];
		for (int i = 0; i < scomuniche.length; i++) {
			for (int j = 0; j < es.length; j++) {
				if (scomuniche[i].getNome().equals(es[j].getNome())) {
					carteScomunica[i] = es[j].getNomeFile();
				}
			}
		}

		// CARTE SVILUPPO TORRE

		String[] carteTorre = new String[16];
		for (int i = 0; i < 16; i++) {
			main.model.Carta cartaModel = null;
			if (update.getSpazioAzione() != null)
				cartaModel = update.getSpazioAzione().getCartaTorre((i / 4) * 4 + (4 - i % 4) - 1);
			if (cartaModel == null)
				carteTorre[i] = null;
			else
				carteTorre[i] = cartaModel.getNome();
		}

		// AGGIORNAMENTO

		Aggiornamento agg = new Aggiornamento(getClient().getTurnNumber(), giocatori, torre, mercato, raccoltoRotondo,
				raccoltoOvale, produzioneRotondo, produzioneOvale, palazzoConsiglio, carteScomunica, carteTorre);

		AggiornamentoInterfaccia ai = new AggiornamentoInterfaccia(agg, this);
		ai.aggiornaTutto();
	}

	public void aggiornamento() {
		if (plancia != null)
			remove(plancia);
		if (tabellone != null)
			remove(tabellone);
		/*
		 * 
		 * colore dei giocatori?
		 * 
		 * HashMap<String, String> coloreModel = Client.getPlayersColor(); ????
		 * 
		 * HashMap<String, model.Plancia> planciaModel =
		 * Client.getPlayersDashboard(); HashMap<String, model.Famigliare[]>
		 * famigliariModel = Client.getPlayersFamilies(); HashMap<String,
		 * model.Risorsa> risorseModel = Client.getPlayersResources();
		 * HashMap<String, model.Punti> puntiModel = Client.getPlayersPoints();
		 * model.SpazioAzione spazioAzioneModel = getGameBoard();
		 * 
		 * 
		 * ArrayList<String> nomeGiocatori = new ArrayList<String>(); for(int
		 * i=0; i<planciaModel.size(); i++){
		 * 
		 * }
		 * 
		 * for(int i=0; i<planciaModel.size(); i++){ for(int j=0; j< }
		 * 
		 */

		boolean[] scomuniche1 = { false, false, false };
		ArrayList<SFamigliare> fp = new ArrayList<SFamigliare>();
		// fp.add(new aggiornamento.Famigliare(0,0,colore, nomeGiocatore));
		fp.add(new SFamigliare(0, 0, colore, nomeGiocatore));
		fp.add(new SFamigliare(0, 1, colore, nomeGiocatore));
		fp.add(new SFamigliare(0, 2, colore, nomeGiocatore));
		fp.add(new SFamigliare(0, 3, colore, nomeGiocatore));

		ArrayList<String> carteEdificio1 = new ArrayList<String>();

		SGiocatore g1 = new SGiocatore(nomeGiocatore, colore, new SPunti(0, 0, 0), new SRisorse(0, 0, 0, 0),
				scomuniche1, fp, new ArrayList<String>(), carteEdificio1, new ArrayList<String>(),
				new ArrayList<String>());

		ArrayList<SGiocatore> giocatori = new ArrayList<SGiocatore>();
		giocatori.add(g1);

		SFamigliare[] torre = { null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null };
		SFamigliare[] mercato = { null, null, null, null };
		SFamigliare raccoltoRotondo = null;
		ArrayList<SFamigliare> raccoltoOvale = new ArrayList<SFamigliare>();
		SFamigliare produzioneRotondo = null;
		ArrayList<SFamigliare> produzioneOvale = new ArrayList<SFamigliare>();
		ArrayList<SFamigliare> palazzoConsiglio = new ArrayList<SFamigliare>();
		String[] carteScomunica = {};
		String[] carteTorre = {};

		Aggiornamento agg = new Aggiornamento(0, giocatori, torre, mercato, raccoltoRotondo, raccoltoOvale,
				produzioneRotondo, produzioneOvale, palazzoConsiglio, carteScomunica, carteTorre);

		AggiornamentoInterfaccia ai = new AggiornamentoInterfaccia(agg, this);
		ai.aggiornaTutto();
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public void setNumeroGiocatori(int numGiocatori) {
		this.numeroGiocatoriPartita = numGiocatori;
	}

	public Tabellone getTabellone() {
		return tabellone;
	}

	public void setTabellone(Tabellone tabellone) {
		this.tabellone = tabellone;
	}

	public Plancia getPlancia() {
		return plancia;
	}

	public void setPlancia(Plancia plancia) {
		this.plancia = plancia;
	}

	public PlanciaAvversario getPlanciaAvversario() {
		return planciaAvversari;
	}

	public void setPlanciaAvversario(PlanciaAvversario planciaAvversario) {
		this.planciaAvversari = planciaAvversario;
	}

	public ButtonLIM getBtnMostraTabellone() {
		return btnMostraTabellone;
	}

	public ButtonLIM getBtnMostraPlancia() {
		return btnMostraPlancia;
	}

	public ButtonLIM getBtnMostraPlanciaAvversari() {
		return btnMostraPlanciaAvversari;
	}

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public String getColore() {
		return colore;
	}

	public int getNumeroGiocatori() {
		return numeroGiocatoriPartita;
	}

	public void inserisciLabelTextLogger() {
		lblTextLogger = new JLabel("");
		lblTextLogger.setBounds(10, 10, 250, 30);
		lblTextLogger.setFont(new Font("ALGERIAN", 50, 15));
		lblTextLogger.setForeground(Color.RED);
		lblTextLogger.setVisible(false);
		contentPane.add(lblTextLogger);
	}

	public void aggiungiBottoni() {
		// btnMostraTabellone.setBounds(961, 11, 127, 32);
		btnMostraTabellone.setBounds(1093, 11, 127, 32);
		btnMostraTabellone.setVisible(false);
		btnMostraTabellone.setText("BOARD");
		getContentPane().add(btnMostraTabellone);
		btnMostraTabellone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMostraTabellone.setBounds(1093, 11, 127, 32);
				btnMostraTabellone.setVisible(false);
				btnMostraPlancia.setVisible(true);
				tabellone.setVisible(true);
				plancia.setVisible(false);
				planciaAvversari.setVisible(false);
			}
		});

		btnMostraPlancia.setBounds(1093, 11, 127, 32);
		btnMostraPlancia.setVisible(true);
		btnMostraPlancia.setText("DASHBOARD");
		getContentPane().add(btnMostraPlancia);
		btnMostraPlancia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMostraTabellone.setBounds(1093, 11, 127, 32);
				btnMostraPlancia.setVisible(false);
				btnMostraTabellone.setVisible(true);
				tabellone.setVisible(false);
				plancia.setVisible(true);
				planciaAvversari.setVisible(false);
			}
		});

		btnMostraPlanciaAvversari.setBounds(1225, 11, 127, 32);
		btnMostraPlanciaAvversari.setVisible(true);
		btnMostraPlanciaAvversari.setText("OPPONENTS");
		getContentPane().add(btnMostraPlanciaAvversari);
		btnMostraPlanciaAvversari.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMostraTabellone.setBounds(961, 11, 127, 32);
				btnMostraTabellone.setVisible(true);
				btnMostraPlancia.setVisible(true);
				tabellone.setVisible(false);
				plancia.setVisible(false);
				planciaAvversari.setVisible(true);
			}
		});

		// btnPassaTurno = new ButtonLIM("PASSA TURNO");
		// btnPassaTurno.setBounds(0, 10, 140, 32);
		// getContentPane().add(btnPassaTurno);
		// btnPassaTurno.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// System.out.println("CHIAMATA A SERVER PER PASSAGGIO TURNO");
		// /*
		// * ArrayList<String> scelte = new ArrayList<String>();
		// * scelte.add("4 legno"); scelte.add("2 pietre");
		// * scelte.add("8 servitori");
		// * framePrivilegioConsiglio.mostraFinestra(scelte, 2);
		// */
		// new ApriPaginaFinePartita();
		// }
		//
		// });
	}

	public void aggiungiListenerFamigliariPlancia() {
		ArrayList<SpazioFamigliare> famigliariStart = plancia.getStartFamigliari();
		for (int i = 0; i < famigliariStart.size(); i++) {
			if (famigliariStart.get(i) != null) {
				if (famigliariStart.get(i).getOccupanti().get(0) != null) {
					famigliariStart.get(i).getOccupanti().get(0).addMouseListener(new SelezionaFamigliarePlancia());
				}
			}

		}
	}

	public void aggiungiListenerCarteTorre() {
		ArrayList<CartaSviluppo> carteTorre = tabellone.getCarteTorre();
		for (int i = 0; i < carteTorre.size(); i++) {
			if (carteTorre.get(i) != null)
				carteTorre.get(i).addMouseListener(new PrendiCarta());
		}
	}

	public void aggiungiListenerTorreTabellone() {
		SpazioFamigliare[] torre = tabellone.getTorre();
		for (int i = 0; i < torre.length; i++) {
			torre[i].addMouseListener(new SpostamentoTorre());
		}
	}

	public void aggiungiListenerRaccoltoRotondoTabellone() {
		SpazioFamigliare raccoltoRotondo = tabellone.getRaccoltoRotondo();
		raccoltoRotondo.addMouseListener(new SpostamentoRaccoltoRotondo());
	}

	public void aggiungiListenerRaccoltoOvaleTabellone() {
		SpazioFamigliare raccoltoOvale = tabellone.getRaccoltoOvale();
		raccoltoOvale.addMouseListener(new SpostamentoRaccoltoOvale());
	}

	public void aggiungiListenerProduzioneRotondoTabellone() {
		SpazioFamigliare produzioneRotondo = tabellone.getProduzioneRotondo();
		produzioneRotondo.addMouseListener(new SpostamentoProduzioneRotondo());
	}

	public void aggiungiListenerProduzioneOvaleTabellone() {
		SpazioFamigliare produzioneOvale = tabellone.getProduzioneOvale();
		produzioneOvale.addMouseListener(new SpostamentoProduzioneOvale());
	}

	public void aggiungiListenerPalazzoConsiglioTabellone() {
		SpazioFamigliare palazzoConsiglio = tabellone.getPalazzoConsiglio();
		palazzoConsiglio.addMouseListener(new SpostamentoPalazzoDelConsiglio());
	}

	public void aggiungiListenerMercatoTabellone() {
		SpazioFamigliare[] mercato = tabellone.getMercato();
		for (int i = 0; i < mercato.length; i++)
			mercato[i].addMouseListener(new SpostamentoMercato());
	}

	public void aggiungiListenerPanelServitore() {
		JPanel panelServitore = plancia.getPanelServitore();
		panelServitore.addMouseListener(new SelezionaServitorePlancia());
	}

	private class SelezionaFamigliarePlancia implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			famigliareSelezionato = (Famigliare) (arg0.getSource());

			if (servitoreSelezionato) {
				if (nomeGiocatore.equals(getClient().getPlayerTurn())) {
					lblTextLogger.setForeground(Color.GREEN);
					lblTextLogger.setText("IT'S YOUR TURN");
				} else {
					lblTextLogger.setForeground(Color.RED);
					lblTextLogger.setText(getClient().getPlayerTurn() + "'S TURN");
				}
				lblTextLogger.setVisible(true);

				System.out.println("CHIAMATA A SERVER PER AGGIUNTA SERVITORE");
				System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
						+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
				System.out.println("TENTATIVO ASSEGNAMENTO SERVITORE");
				/*
				 * COVERSAZIONE CON SERVER
				 */

				EColoriPedine colorePedina;
				if (famigliareSelezionato.getNumero() == 0)
					colorePedina = EColoriPedine.Nera;
				else if (famigliareSelezionato.getNumero() == 1)
					colorePedina = EColoriPedine.Arancione;
				else if (famigliareSelezionato.getNumero() == 2)
					colorePedina = EColoriPedine.Bianca;
				else
					colorePedina = EColoriPedine.Neutrale;

				incrementPawnValue(colorePedina, 1);

				servitoreSelezionato = false;
				famigliareSelezionato = null;
				return;
			}

			lblTextLogger.setText(
					"SELECTED: " + famigliareSelezionato.getNumero() + ", value: " + famigliareSelezionato.getValore());
			lblTextLogger.setVisible(true);

			System.out.println("famigliare selezionato");
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class SelezionaServitorePlancia implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!servitoreSelezionato) {
				servitoreSelezionato = true;
				System.out.println("selezionato servitore");
			} else {
				servitoreSelezionato = true;
				System.out.println("deselezionato servitore");
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoTorre implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (famigliareSelezionato == null)
				return;
			int posizioneTorre = 0;
			for (posizioneTorre = 0; posizioneTorre < tabellone.getTorre().length; posizioneTorre++) {
				if (tabellone.getTorre()[posizioneTorre] == (SpazioFamigliare) (e.getSource()))
					break;
			}
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU TORRE IN POSIZIONE " + posizioneTorre);

			// Frame.
			/*
			 * COVERSAZIONE CON SERVER
			 */

			/*
			 * public void movePawn(EAzioniGiocatore action, EColoriPedine
			 * color, Integer position, ECostiCarte[] choosedCosts) {
			 * client.movePawn(action, color, position, choosedCosts); }
			 */
			// movePawn(EAzioniGiocatori., color, position);
			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			for (ECarte carta : ECarte.values()) {
				if (tabellone.getCarteTorre().get(posizioneTorre).getNomeCarta().equals(carta.getNome())) {
					int numScelteCosti = carta.getNumScelteCosti();
					if (numScelteCosti > 0) {
						ArrayList<String> scelte = new ArrayList<String>();

						for (int i = 0; i < carta.getCostiCarta().size(); i++) {

							scelte.add(carta.getCostiCarta().get(i).getDescrizione());
						}
						frameSceltaCosti.setScelteCosti(scelte, posizioneTorre);
						new ChiediSceltaCosti(EAzioniGiocatore.Torre, colorePedina, scelte, numScelteCosti);
						return;
					}
				}
			}
			int pos = (posizioneTorre / 4) * 4 + (4 - posizioneTorre % 4) - 1;
			movePawn(EAzioniGiocatore.Torre, colorePedina, pos);

			// aggiornamento();

			famigliareSelezionato = null;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoRaccoltoRotondo implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (famigliareSelezionato == null)
				return;
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU RACCOLTO ROTONDO");
			/*
			 * COVERSAZIONE CON SERVER
			 */

			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			EEffettiPermanenti[] effetti = getClient().getPlayersDashboards().get(nomeGiocatore)
					.getEffettiPermanentiTerritori();

			if (effetti != null && effetti.length > 0) {
				ArrayList<String> scelte = new ArrayList<String>();
				for (int i = 0; i < effetti.length; i++) {
					scelte.add(effetti[i].getDescrizione());
				}
				frameSceltaEffettiPermanenti.setScelteEffetti(scelte);
				new ChiediSceltaEffettiPermanenti(EAzioniGiocatore.Raccolto, colorePedina, scelte, -1);
				return;

			}

			movePawn(EAzioniGiocatore.Raccolto, colorePedina, 0);

			// aggiornamento();

			famigliareSelezionato = null;

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoRaccoltoOvale implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (famigliareSelezionato == null)
				return;
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU RACCOLTO OVALE");
			/*
			 * COVERSAZIONE CON SERVER
			 */

			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			EEffettiPermanenti[] effetti = getClient().getPlayersDashboards().get(nomeGiocatore)
					.getEffettiPermanentiTerritori();

			if (effetti != null && effetti.length > 0) {
				ArrayList<String> scelte = new ArrayList<String>();
				for (int i = 0; i < effetti.length; i++) {
					scelte.add(effetti[i].getDescrizione());
				}
				frameSceltaEffettiPermanenti.setScelteEffetti(scelte);
				new ChiediSceltaEffettiPermanenti(EAzioniGiocatore.RaccoltoOvale, colorePedina, scelte, -1);
				return;

			}

			movePawn(EAzioniGiocatore.RaccoltoOvale, colorePedina, 0);

			// aggiornamento();

			famigliareSelezionato = null;

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoProduzioneRotondo implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (famigliareSelezionato == null)
				return;
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU PRODUZIONE ROTONDO");
			/*
			 * COVERSAZIONE CON SERVER
			 */

			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			EEffettiPermanenti[] effetti = getClient().getPlayersDashboards().get(nomeGiocatore)
					.getEffettiPermanentiEdifici();

			if (effetti != null && effetti.length > 0) {
				ArrayList<String> scelte = new ArrayList<String>();
				for (int i = 0; i < effetti.length; i++) {
					scelte.add(effetti[i].getDescrizione());
				}
				frameSceltaEffettiPermanenti.setScelteEffetti(scelte);
				new ChiediSceltaEffettiPermanenti(EAzioniGiocatore.Produzione, colorePedina, scelte, -1);
				return;

			}

			movePawn(EAzioniGiocatore.Produzione, colorePedina, 0);

			// aggiornamento();

			famigliareSelezionato = null;

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoProduzioneOvale implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (famigliareSelezionato == null)
				return;
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU PRODUZIONE OVALE");
			/*
			 * COVERSAZIONE CON SERVER
			 */

			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			EEffettiPermanenti[] effetti = getClient().getPlayersDashboards().get(nomeGiocatore)
					.getEffettiPermanentiEdifici();

			if (effetti != null && effetti.length > 0) {
				ArrayList<String> scelte = new ArrayList<String>();
				for (int i = 0; i < effetti.length; i++) {
					scelte.add(effetti[i].getDescrizione());
				}
				frameSceltaEffettiPermanenti.setScelteEffetti(scelte);
				new ChiediSceltaEffettiPermanenti(EAzioniGiocatore.ProduzioneOvale, colorePedina, scelte, -1);
				return;

			}

			movePawn(EAzioniGiocatore.ProduzioneOvale, colorePedina, 0);

			// aggiornamento();

			famigliareSelezionato = null;

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoPalazzoDelConsiglio implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (famigliareSelezionato == null)
				return;
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU PALAZZO CONSIGLIO");

			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			ArrayList<String> scelte = new ArrayList<String>();
			scelte.add("1 wood and 1 stone");
			scelte.add("2 servants");
			scelte.add("2 coins");
			scelte.add("2 military points");
			scelte.add("1 faith point");
			new ChiediPrivilegioConsiglio(EAzioniGiocatore.PalazzoConsiglio, colorePedina, scelte, 1);
			/*
			 * COVERSAZIONE CON SERVER
			 */
			// aggiornamento();

			famigliareSelezionato = null;

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class SpostamentoMercato implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (famigliareSelezionato == null)
				return;
			int posizioneMercato = 0;
			for (posizioneMercato = 0; posizioneMercato < tabellone.getMercato().length; posizioneMercato++) {
				if (tabellone.getMercato()[posizioneMercato] == (SpazioFamigliare) (e.getSource()))
					break;
			}
			System.out.println("CHIAMATA A SERVER PER SPOSTAMENTO");
			System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
					+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
			System.out.println("SPOSTAMENTO SU MERCATO IN POSIZIONE " + posizioneMercato);

			EColoriPedine colorePedina;
			if (famigliareSelezionato.getNumero() == 0)
				colorePedina = EColoriPedine.Nera;
			else if (famigliareSelezionato.getNumero() == 1)
				colorePedina = EColoriPedine.Arancione;
			else if (famigliareSelezionato.getNumero() == 2)
				colorePedina = EColoriPedine.Bianca;
			else
				colorePedina = EColoriPedine.Neutrale;

			if (posizioneMercato == 3) {
				ArrayList<String> scelte = new ArrayList<String>();
				scelte.add("1 wood and 1 stone");
				scelte.add("2 servants");
				scelte.add("2 coins");
				scelte.add("2 military points");
				scelte.add("1 faith point");
				new ChiediPrivilegioConsiglio(EAzioniGiocatore.Mercato, colorePedina, scelte, 2);
			} else {
				movePawn(EAzioniGiocatore.Mercato, colorePedina, posizioneMercato);
			}
			/*
			 * COVERSAZIONE CON SERVER
			 */
			// aggiornamento();

			famigliareSelezionato = null;

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class PrendiCarta implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println("CHIAMATA A SERVER PER PRENDERE CARTA");
			CartaSviluppo cartaSelezionata = (CartaSviluppo) (arg0.getSource());
			int posizioneCarta = 0;
			for (posizioneCarta = 0; posizioneCarta < tabellone.getCarteTorre().size(); posizioneCarta++) {
				if (tabellone.getCarteTorre().get(posizioneCarta) == cartaSelezionata)
					break;
			}
			System.out.println("TENTATIVO ");
			System.out.println("PRENDERE CARTA " + cartaSelezionata.getNomeCarta() + " in posizione " + posizioneCarta);
			// aggiornamento();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	private class ChiediPrivilegioConsiglio implements EventListener {
		public ChiediPrivilegioConsiglio(EAzioniGiocatore azione, EColoriPedine colorePedina, ArrayList<String> scelte,
				int numScelte) {
			framePrivilegioConsiglio.mostraFinestra(azione, colorePedina, scelte, numScelte);
		}
	}

	private class ChiediSceltaCosti implements EventListener {
		public ChiediSceltaCosti(EAzioniGiocatore azione, EColoriPedine colorePedina, ArrayList<String> scelte,
				int numScelte) {
			frameSceltaCosti.mostraFinestra(azione, colorePedina, scelte, numScelte);
		}
	}

	private class ChiediSceltaEffettiPermanenti implements EventListener {
		public ChiediSceltaEffettiPermanenti(EAzioniGiocatore azione, EColoriPedine colorePedina,
				ArrayList<String> scelte, int numScelte) {
			frameSceltaEffettiPermanenti.mostraFinestra(azione, colorePedina, scelte, numScelte);
		}
	}

	private class ChiediSupportoChiesa implements EventListener {
		public ChiediSupportoChiesa() {
			frameSceltaSupportoChiesa.mostraFinestra();
		}
	}

	private class ApriPaginaFinePartita implements EventListener {
		public ApriPaginaFinePartita() {
			ClassificaFinaleFrame classificaFinaleFrame = new ClassificaFinaleFrame();
			classificaFinaleFrame.setVisible(true);
			frame.setVisible(false);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Metodi di richiesta (da Client verso Server)
	/////////////////////////////////////////////////////////////////////////////////////

	public void loginPlayer(String nickname) {
		client.loginPlayer(nickname);
	}

	public void sendChatMessage(String receiver, String message) {
		client.sendChatMessage(receiver, message);
	}

	public void performGameAction(UpdateStats requestedAction) {
		client.performGameAction(requestedAction);
	}

	public void movePawn(EAzioniGiocatore action, EColoriPedine color, Integer position,
			ESceltePrivilegioDelConsiglio[] choosedPrivileges) {
		client.movePawn(action, color, position, choosedPrivileges);
	}

	public void movePawn(EAzioniGiocatore action, EColoriPedine color, Integer position, ECostiCarte[] choosedCosts) {
		client.movePawn(action, color, position, choosedCosts);
	}

	public void movePawn(EAzioniGiocatore action, EColoriPedine color, Integer position,
			EEffettiPermanenti[] choosedEffects) {
		client.movePawn(action, color, position, choosedEffects);
	}

	public void movePawn(EAzioniGiocatore action, EColoriPedine color, int position) {
		client.movePawn(action, color, position);
	}

	public void supportChurch(boolean isSupported) {
		client.supportChurch(isSupported);
	}

	public void incrementPawnValue(EColoriPedine color, int servants) {
		client.incrementPawnValue(color, servants);
	}

	/////////////////////////////////////////////////////////////////////////////////////
	// Eventi di risposta (scatenati dal Server sul Client)
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onChatMessage(String author, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActionNotValid(String errorCode) {
		lblTextLogger.setForeground(Color.YELLOW);
		lblTextLogger.setText(errorCode);
		lblTextLogger.setVisible(true);

		System.out.println("ERROR: " + ANSI.YELLOW + errorCode + ANSI.RESET);

		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				if (nomeGiocatore.equals(getClient().getPlayerTurn())) {
					lblTextLogger.setForeground(Color.GREEN);
					lblTextLogger.setText("IT'S YOUR TURN");
				} else {
					lblTextLogger.setForeground(Color.RED);
					lblTextLogger.setText(getClient().getPlayerTurn() + "'s TURN");
				}
				lblTextLogger.setVisible(true);
			}
		}, 3000);

	}

	@Override
	public void onGameUpdate(UpdateStats update) {
		// "fallback" (please don't use it..)
		// aggiornamento(update);

		// if (btnMostraPlancia.isVisible())
		// btnMostraTabellone.doClick();
		// if (btnMostraTabellone.isVisible())
		// btnMostraPlancia.doClick();
	}

	@Override
	public void onChurchSupport(UpdateStats update) {
		aggiornamento(update);
		if (update.getNomiGiocatori().contains(nomeGiocatore))
			new ChiediSupportoChiesa();
	}

	@Override
	public void onMarket(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onPayServant(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onTower(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onCouncilPalace(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onHarvestRound(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onProductionRound(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onHarvestOval(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onProductionOval(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onTurnEnd(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onPeriodEnd(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onGameEnd(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onPlayerMove(UpdateStats update) {
		if (nomeGiocatore.equals(update.getNomeGiocatore())) {
			lblTextLogger.setForeground(Color.GREEN);
			lblTextLogger.setText("IT'S YOUR TURN");
		} else {
			lblTextLogger.setForeground(Color.RED);
			lblTextLogger.setText(update.getNomeGiocatore() + "'S TURN");
		}
		lblTextLogger.setVisible(true);

		aggiornamento(update);
	}

	@Override
	public void onTurnStarted(UpdateStats update) {
		aggiornamento(update);
	}

	@Override
	public void onPeriodStarted(UpdateStats update) {
		aggiornamento(update);

	}

	@Override
	public void onGameStarted(UpdateStats update) {
		this.setVisible(true);
		Userframe.setVisible(false);

		nomeGiocatore = getClient().getNickname();
		colore = getClient().getPlayersColors().get(nomeGiocatore).getSwingName();

		nomeGiocatoriPartita = update.getNomiGiocatori();
		numeroGiocatoriPartita = this.nomeGiocatoriPartita.size();

		aggiornamento(update);
	}

	@Override
	public void onNotify(Object object) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
