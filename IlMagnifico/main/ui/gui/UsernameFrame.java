package main.ui.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.ui.gui.components.ButtonLIM;
import main.ui.gui.components.PanelImmagine;
import main.util.Costants;

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

	private Frame framePartita;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsernameFrame frame = new UsernameFrame();
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
	public UsernameFrame() {
		setIconImage(
				new ImageIcon(getClass().getResource(Costants.PATH_RESOURCES + Costants.FOLDER_BASE + "/giglio.png"))
						.getImage());
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
		lblMessaggioErrore = new JLabel("username inserito non valido");
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

	public void inserisciImmagine() {
		immagine = new PanelImmagine(Costants.PATH_RESOURCES + Costants.FOLDER_BASE + "/giglio firenze.png");
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
			if (txtFieldUsername.getText().length() == 0) {
				lblMessaggioErrore.setVisible(true);
				return;
			}
			lblMessaggioErrore.setVisible(false);
			/*
			 * CHIAMATA A SERVER PER INIZIARE LA PARTITA
			 */
			framePartita = new Frame();
			framePartita.setVisible(true);
			usernameFrame.setVisible(false);
		}

	}

}
