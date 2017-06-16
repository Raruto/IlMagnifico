package main.ui.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import main.network.client.Client;
import main.network.client.ClientException;
import main.ui.gui.components.ButtonLIM;
import main.ui.gui.components.PanelImmagine;
import main.util.Costants;
import res.images.Resources;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class UsernameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8923233989755772950L;
	private JPanel contentPane;
	private PanelImmagine immagine;
	private JLabel lblUsername;
	private JLabel lblMessaggioErrore;
	private JTextField txtFieldUsername;
	private ButtonLIM btnIniziaPartita;
	private JRadioButton rbtnSceltaSocket;
	private JRadioButton rbtnSceltaRMI;

	private Frame framePartita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try {
		 * UsernameFrame frame = new UsernameFrame(); frame.setVisible(true); }
		 * catch (Exception e) { e.printStackTrace(); } } });
		 */
	}

	/**
	 * Create the frame.
	 * 
	 * @param frame
	 */
	public UsernameFrame(Frame frame) {
		framePartita = frame;

		setIconImage(new ImageIcon(Resources.class.getResource(Costants.FOLDER_BASE + "/giglio.png")).getImage());
		setTitle("         lorenzo il magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);

		inserisciLabelUsername();
		inserisciLabelMessaggioErrore();
		inserisciTextFieldUsername();
		inserisciBottoneIniziaPartita();
		inserisciSceltaConnessione();
		inserisciImmagine();

	}

	public void inserisciLabelUsername() {
		lblUsername = new JLabel("INSERISCI USERNAME : ");
		lblUsername.setBounds(400, 242, 250, 30);
		lblUsername.setFont(new Font("ALGERIAN", 50, 20));
		lblUsername.setForeground(Color.WHITE);
		contentPane.add(lblUsername);
	}

	public void inserisciLabelMessaggioErrore() {
		lblMessaggioErrore = new JLabel("");
		lblMessaggioErrore.setBounds(400, 350, 250, 30);
		lblMessaggioErrore.setFont(new Font("ALGERIAN", 50, 15));
		lblMessaggioErrore.setForeground(Color.RED);
		lblMessaggioErrore.setVisible(false);
		contentPane.add(lblMessaggioErrore);
	}

	public void inserisciTextFieldUsername() {
		txtFieldUsername = new JTextField();
		txtFieldUsername.setBounds(400, 295, 265, 30);
		contentPane.add(txtFieldUsername);
		txtFieldUsername.setColumns(10);
	}

	public void inserisciBottoneIniziaPartita() {
		btnIniziaPartita = new ButtonLIM("INIZIA PARTITA");
		btnIniziaPartita.setBounds(400, 400, 250, 30);
		contentPane.add(btnIniziaPartita);
		btnIniziaPartita.addActionListener(new IniziaPartita(this));
	}

	public void inserisciSceltaConnessione() {
		rbtnSceltaSocket = new JRadioButton("SOCKET");
		rbtnSceltaSocket.setBounds(50, 300, 200, 20);
		rbtnSceltaSocket.setOpaque(false);
		rbtnSceltaSocket.setForeground(Color.WHITE);
		add(rbtnSceltaSocket);
		rbtnSceltaRMI = new JRadioButton("RMI");
		rbtnSceltaRMI.setBounds(50, 340, 200, 20);
		rbtnSceltaRMI.setOpaque(false);
		rbtnSceltaRMI.setForeground(Color.WHITE);
		add(rbtnSceltaRMI);
	}

	public void inserisciImmagine() {
		immagine = new PanelImmagine(Costants.FOLDER_BASE + "/giglio firenze.png");
		immagine.setBounds(800, 200, 400, 600);
		immagine.setVisible(true);
		contentPane.add(immagine);
	}

	private class IniziaPartita implements ActionListener {

		private UsernameFrame usernameFrame;

		public IniziaPartita(UsernameFrame usernameFrame) {
			this.usernameFrame = usernameFrame;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			lblMessaggioErrore.setForeground(Color.RED);

			if (txtFieldUsername.getText().length() == 0) {
				lblMessaggioErrore.setText("nome inserito non valido");
				lblMessaggioErrore.setVisible(true);
				return;
			}
			lblMessaggioErrore.setVisible(false);

			if ((rbtnSceltaSocket.isSelected() && rbtnSceltaRMI.isSelected())
					|| (!rbtnSceltaSocket.isSelected() && !rbtnSceltaRMI.isSelected())) {
				lblMessaggioErrore.setText("scelta connessione non valida");
				lblMessaggioErrore.setVisible(true);
				return;
			}

			// nomeUtente è il nome con cui il giocatore vuole effettuare il
			// lgin
			// connessione è una stringa che indica il tipo di connessione
			// (SOCKET o RMI)
			String nomeUtente = txtFieldUsername.getText();
			String connessione = "";
			if (rbtnSceltaSocket.isSelected())
				connessione = "SOCKET";
			if (rbtnSceltaRMI.isSelected())
				connessione = "RMI";
			try {
				framePartita.getClient().startClient(connessione, Costants.SERVER_ADDRESS, Costants.SOCKET_PORT,
						Costants.RMI_PORT);
				framePartita.getClient().loginPlayer(nomeUtente);
				if (framePartita.getClient().isLogged()) {
					if (!framePartita.getClient().isGameStarted()) {
						lblMessaggioErrore.setForeground(Color.YELLOW);
						lblMessaggioErrore.setText("In attesa di altri giocatori");
						lblMessaggioErrore.setVisible(true);
					}
				}
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
