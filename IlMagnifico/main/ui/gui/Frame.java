package main.ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.network.client.Client;
import main.network.client.ClientException;
import main.network.client.IClient;
import main.network.protocol.ConnectionTypes;
import main.network.server.game.UpdateStats;
import main.ui.gui.aggiornamento.*;
import main.ui.gui.aggiornamento.Aggiornamento;
import main.ui.gui.aggiornamento.Giocatore;
import main.ui.gui.aggiornamento.Punti;
import main.ui.gui.aggiornamento.Risorse;
import main.ui.gui.altriComponenti.ButtonLIM;
import main.ui.gui.altriComponenti.PanelImmagine;
import main.util.Costants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EventListener;

public class Frame extends JFrame implements IClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7802964308401362865L;
	private Frame frame = this;
	private PrivilegioConsiglio framePrivilegioConsiglio = new PrivilegioConsiglio(this);

	private JPanel contentPane;
	private ButtonLIM btnMostraTabellone = new ButtonLIM();
	private ButtonLIM btnMostraPlancia = new ButtonLIM();
	private ButtonLIM btnMostraPlanciaAvversari = new ButtonLIM();
	private ButtonLIM btnPassaTurno = new ButtonLIM();

	private int turno;
	private int numeroGiocatoriPartita = 2;
	private String nomeGiocatore;
	private String colore;

	private Tabellone tabellone;
	private Plancia plancia;
	private PlanciaAvversario planciaAvversari;

	private Client client;

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
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Frame() {
		setIconImage(new ImageIcon(getClass().getResource(Costants.PATH_RESOURCES + "/giglio.png")).getImage());
		setTitle("         lorenzo il magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(null);

		/*
		 * AGGIORNAMENTO PER INIZIALIZZAZIONE
		 */

		// ESEMPIO

		nomeGiocatore = "Pino";
		colore = "rosso";
		aggiornamento();
		aggiornamento();

		try {
			this.client = new Client(this);
			
			//se RMI
			client.startClient(ConnectionTypes.RMI.toString(), Costants.SERVER_ADDRESS, Costants.SOCKET_PORT,
					Costants.RMI_PORT);
			
			//se Socket
			// client.startClient(ConnectionTypes.SOCKET.toString(),
			// Costants.SERVER_ADDRESS, Costants.SOCKET_PORT,
			// Costants.RMI_PORT);

			while (!client.isLogged()) {
				// System.out.print("Choose Player Name: ");
				// inText = scanner.nextLine();
				client.loginPlayer("Foo");
			}

		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		ArrayList<main.ui.gui.aggiornamento.Famigliare> fp = new ArrayList<main.ui.gui.aggiornamento.Famigliare>();
		// fp.add(new aggiornamento.Famigliare(0,0,"rosso", "Pino"));
		fp.add(null);
		fp.add(new main.ui.gui.aggiornamento.Famigliare(0, 1, "rosso", "Pino"));
		fp.add(new main.ui.gui.aggiornamento.Famigliare(0, 2, "rosso", "Pino"));
		fp.add(new main.ui.gui.aggiornamento.Famigliare(0, 3, "rosso", "Pino"));

		ArrayList<String> carteEdificio1 = new ArrayList<String>();
		carteEdificio1.add("fiera");
		carteEdificio1.add("banca");
		carteEdificio1.add("palazzo");

		Giocatore g1 = new Giocatore("Pino", "rosso", new Punti(55, 12, 34), new Risorse(10, 12, 1, 3), scomuniche1, fp,
				new ArrayList<String>(), carteEdificio1, new ArrayList<String>(), new ArrayList<String>());

		boolean[] scomuniche2 = { false, false, false };
		ArrayList<main.ui.gui.aggiornamento.Famigliare> fp2 = new ArrayList<main.ui.gui.aggiornamento.Famigliare>();
		fp2.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "blu", "Bruno"));
		fp2.add(new main.ui.gui.aggiornamento.Famigliare(0, 1, "blu", "Bruno"));
		fp2.add(new main.ui.gui.aggiornamento.Famigliare(0, 2, "blu", "Bruno"));
		fp2.add(new main.ui.gui.aggiornamento.Famigliare(0, 3, "blu", "Bruno"));
		ArrayList<String> carteTerritorio2 = new ArrayList<String>();
		carteTerritorio2.add("avamposto commerciale");
		carteTerritorio2.add("borgo");
		carteTerritorio2.add("foresta");
		Giocatore g2 = new Giocatore("Bruno", "blu", new Punti(56, 52, 11), new Risorse(2, 8, 5, 24), scomuniche2, fp2,
				carteTerritorio2, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());

		boolean[] scomuniche3 = { true, false, false };
		ArrayList<main.ui.gui.aggiornamento.Famigliare> fp3 = new ArrayList<main.ui.gui.aggiornamento.Famigliare>();
		fp3.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "verde", "Beppe"));
		fp3.add(new main.ui.gui.aggiornamento.Famigliare(0, 1, "verde", "Beppe"));
		// fp3.add(new aggiornamento.Famigliare(4, 2, "verde", "Beppe"));
		fp3.add(null);
		fp3.add(new main.ui.gui.aggiornamento.Famigliare(0, 3, "verde", "Beppe"));
		ArrayList<String> cartePersonaggio3 = new ArrayList<String>();
		cartePersonaggio3.add("artigiano");
		cartePersonaggio3.add("badessa");
		cartePersonaggio3.add("dama");
		cartePersonaggio3.add("condottiero");
		Giocatore g3 = new Giocatore("Beppe", "verde", new Punti(64, 30, 44), new Risorse(13, 21, 0, 50), scomuniche3,
				fp3, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), cartePersonaggio3);

		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		giocatori.add(g1);
		giocatori.add(g2);
		giocatori.add(g3);

		main.ui.gui.aggiornamento.Famigliare[] torre = { null,
				new main.ui.gui.aggiornamento.Famigliare(0, 0, "verde", "Beppe"), null, null, null, null, null, null,
				null, null, new main.ui.gui.aggiornamento.Famigliare(4, 2, "rosso", "Pino"), null,
				new main.ui.gui.aggiornamento.Famigliare(2, 3, "blu", "Bruno"), null, null, null };
		main.ui.gui.aggiornamento.Famigliare[] mercato = { null, null,
				new main.ui.gui.aggiornamento.Famigliare(0, 0, "verde", "Beppe"), null };
		main.ui.gui.aggiornamento.Famigliare raccoltoRotondo = new main.ui.gui.aggiornamento.Famigliare(0, 0, "verde",
				"Beppe");
		ArrayList<main.ui.gui.aggiornamento.Famigliare> raccoltoOvale = new ArrayList<main.ui.gui.aggiornamento.Famigliare>();
		raccoltoOvale.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "rosso", "Pino"));
		raccoltoOvale.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "verde", "Beppe"));
		main.ui.gui.aggiornamento.Famigliare produzioneRotondo = new main.ui.gui.aggiornamento.Famigliare(0, 0, "verde",
				"Beppe");
		ArrayList<main.ui.gui.aggiornamento.Famigliare> produzioneOvale = new ArrayList<main.ui.gui.aggiornamento.Famigliare>();
		produzioneOvale.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "rosso", "Pino"));
		produzioneOvale.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "blu", "Bruno"));
		ArrayList<main.ui.gui.aggiornamento.Famigliare> palazzoConsiglio = new ArrayList<main.ui.gui.aggiornamento.Famigliare>();
		palazzoConsiglio.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "blu", "Bruno"));
		palazzoConsiglio.add(new main.ui.gui.aggiornamento.Famigliare(0, 0, "blu", "Bruno"));
		String[] carteScomunica = { "scomunica 1_1", "scomunica 1_2", "scomunica 1_3" };
		String[] carteTorre = { "citta", "citta", "citta", "citta", "citta", "citta", "citta", "capitano", "citta",
				"citta", "citta", "citta", "citta", "citta", "citta", "avamposto commerciale" };

		Aggiornamento agg = new Aggiornamento(5, giocatori, torre, mercato, raccoltoRotondo, raccoltoOvale,
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

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public String getColore() {
		return colore;
	}

	public int getNumeroGiocatori() {
		return numeroGiocatoriPartita;
	}

	public void aggiungiBottoni() {
		btnMostraTabellone.setBounds(961, 11, 127, 32);
		btnMostraTabellone.setText("TABELLONE");
		getContentPane().add(btnMostraTabellone);
		btnMostraTabellone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabellone.setVisible(true);
				plancia.setVisible(false);
				planciaAvversari.setVisible(false);
			}
		});

		btnMostraPlancia.setBounds(1093, 11, 127, 32);
		btnMostraPlancia.setText("PLANCIA");
		getContentPane().add(btnMostraPlancia);
		btnMostraPlancia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabellone.setVisible(false);
				plancia.setVisible(true);
				planciaAvversari.setVisible(false);
			}
		});

		btnMostraPlanciaAvversari.setBounds(1225, 11, 127, 32);
		btnMostraPlanciaAvversari.setText("AVVERSARI");
		getContentPane().add(btnMostraPlanciaAvversari);
		btnMostraPlanciaAvversari.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabellone.setVisible(false);
				plancia.setVisible(false);
				planciaAvversari.setVisible(true);
			}
		});

		btnPassaTurno = new ButtonLIM("PASSA TURNO");
		btnPassaTurno.setBounds(0, 10, 140, 32);
		getContentPane().add(btnPassaTurno);
		btnPassaTurno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("CHIAMATA A SERVER PER PASSAGGIO TURNO");
				/*
				 * ArrayList<String> scelte = new ArrayList<String>();
				 * scelte.add("4 legno"); scelte.add("2 pietre");
				 * scelte.add("8 servitori");
				 * framePrivilegioConsiglio.mostraFinestra(scelte, 2);
				 */
				new ApriPaginaFinePartita();
			}

		});
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
				System.out.println("CHIAMATA A SERVER PER AGGIUNTA SERVITORE");
				System.out.println("FAMIGLIARE: " + famigliareSelezionato.getGiocatoreAppartenenza() + ", numero: "
						+ famigliareSelezionato.getNumero() + ", valore: " + famigliareSelezionato.getValore());
				System.out.println("TENTATIVO ASSEGNAMENTO SERVITORE");
				/*
				 * COVERSAZIONE CON SERVER
				 */

				servitoreSelezionato = false;
				famigliareSelezionato = null;
				return;
			}

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
			/*
			 * COVERSAZIONE CON SERVER
			 */
			aggiornamento();

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
			aggiornamento();

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
			aggiornamento();

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
			aggiornamento();

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
			aggiornamento();

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

			ArrayList<String> scelte = new ArrayList<String>();
			scelte.add("1 pietra e 1 legno");
			scelte.add("2 servitori");
			scelte.add("2 monete");
			scelte.add("2 punti militari");
			scelte.add("1 fede");
			new ChiediPrivilegioConsiglio(scelte, 2);
			/*
			 * COVERSAZIONE CON SERVER
			 */
			aggiornamento();

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

			ArrayList<String> scelte = new ArrayList<String>();
			scelte.add("1 pietra e 1 legno");
			scelte.add("2 servitori");
			scelte.add("2 monete");
			scelte.add("2 punti militari");
			scelte.add("1 fede");
			new ChiediPrivilegioConsiglio(scelte, 2);
			/*
			 * COVERSAZIONE CON SERVER
			 */
			aggiornamento();

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
			aggiornamento();
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
		public ChiediPrivilegioConsiglio(ArrayList<String> scelte, int numScelte) {
			framePrivilegioConsiglio.mostraFinestra(scelte, numScelte);
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
	// Eventi scatenati dal Server sul Client
	/////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void onChatMessage(boolean privateMessage, String author, String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActionNotValid(String errorCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameUpdate(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChurchSupport(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarket(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPayServant(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTower(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCouncilPalace(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHarvestRound(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProductionRound(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onHarvestOval(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProductionOval(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTurnEnd(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPeriodEnd(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameEnd(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerMove(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTurnStarted(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPeriodStarted(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameStarted(UpdateStats update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNotify(Object object) throws RemoteException {
		// TODO Auto-generated method stub

	}
}
