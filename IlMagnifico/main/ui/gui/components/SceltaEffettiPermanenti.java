package main.ui.gui.components;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.EColoriPedine;
import main.model.enums.EEffettiPermanenti;
import main.ui.gui.GUI;
import main.ui.gui.components.swing.ButtonLIM;
import main.util.Costants;
import res.images.Resources;

public class SceltaEffettiPermanenti extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8525471854298151358L;
	private JPanel contentPane;
	private EAzioniGiocatore azione;
	private int numeroScelte;
	private EColoriPedine colorePedina;

	private GUI framePrincipale;
	private JRadioButton[] radioButtons;
	private ButtonLIM btnOK = new ButtonLIM("OK");
	private JLabel lblComunicazione;

	private ArrayList<String> scelte;
	// private int numScelte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ArrayList<String> scelte = new ArrayList<String>();
				scelte.add("Choiche 1");
				scelte.add("Choiche 2");
				scelte.add("Choiche 3");
				try {
					SceltaEffettiPermanenti frame = new SceltaEffettiPermanenti(null);
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
	public SceltaEffettiPermanenti(GUI framePrincipale) {
		setIconImage(new ImageIcon(Resources.class.getResource(Costants.FOLDER_BASE + "/giglio.png")).getImage());
		setTitle("         lorenzo il magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		// "./src/cornice3.png"
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		this.framePrincipale = framePrincipale;
	}

	public void setScelteEffetti(ArrayList<String> scelte) {
		this.scelte = scelte;
	}

	public void mostraFinestra(EAzioniGiocatore azione, EColoriPedine colorePedina, ArrayList<String> scelte,
			int numScelte) {
		framePrincipale.setVisible(false);
		setVisible(true);
		getContentPane().setLayout(null);
		contentPane.setBounds(0, 0, 1362, 694);
		contentPane.setLayout(null);
		this.scelte = scelte;
		this.numeroScelte = numScelte;
		this.azione = azione;
		this.colorePedina = colorePedina;
		aggiungiBtnOK();
		aggiungiLblComunicazione();
		rimuoviRadioButton();
		aggiungiRadioButton(numeroScelte, scelte);

		this.repaint();
	}

	public void rimuoviRadioButton() {
		if (radioButtons != null) {
			for (int i = 0; i < radioButtons.length; i++) {
				if (radioButtons[i] != null)
					remove(radioButtons[i]);
			}
		}

	}

	public void aggiungiRadioButton(int numeroScelte, ArrayList<String> scelte) {
		if (radioButtons == null)
			rimuoviRadioButton();
		radioButtons = new JRadioButton[scelte.size()];
		for (int i = 0; i < scelte.size(); i++) {
			radioButtons[i] = new JRadioButton(scelte.get(i));
			radioButtons[i].setBounds(600, 200 + 30 * i, 500, 25);
			radioButtons[i].setVisible(true);
			radioButtons[i].setOpaque(false);
			radioButtons[i].setFont(new Font("ALGERIAN", 20, 20));
			radioButtons[i].setForeground(Color.WHITE);
			radioButtons[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					JRadioButton temp = (JRadioButton) (arg0.getSource());
					if (!temp.isSelected())
						temp.setForeground(Color.YELLOW);

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					JRadioButton temp = (JRadioButton) (arg0.getSource());
					temp.setForeground(Color.RED);
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					JRadioButton temp = (JRadioButton) (arg0.getSource());
					if (!temp.isSelected())
						temp.setForeground(Color.WHITE);
					else
						temp.setForeground(Color.YELLOW);

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
			contentPane.add(radioButtons[i]);
		}
	}

	public void aggiungiLblComunicazione() {
		if (lblComunicazione != null) {
			remove(lblComunicazione);
		}
		lblComunicazione = new JLabel("Select also the permanent effects you want to activate: ");
		lblComunicazione.setBounds(350, 100, 719, 35);
		lblComunicazione.setFont(new Font("ALGERIAN", 50, 20));
		lblComunicazione.setForeground(Color.WHITE);
		getContentPane().add(lblComunicazione);

		lblComunicazione.repaint();
	}

	public void aggiungiBtnOK() {
		btnOK.setBounds(575, 450, 185, 30);
		btnOK.setVisible(true);
		contentPane.setLayout(null);
		contentPane.add(btnOK);
		btnOK.addActionListener(new Conferma(this));
	}

	private class Conferma implements ActionListener {

		public SceltaEffettiPermanenti frameSceltaCosti;

		public Conferma(SceltaEffettiPermanenti frameSceltaCosti) {
			this.frameSceltaCosti = frameSceltaCosti;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int contaValoriSelezionati = 0;
			ArrayList<String> decisioni = new ArrayList<String>();
			decisioni.clear();
			for (int i = 0; i < radioButtons.length; i++) {
				if (radioButtons[i].isSelected()) {
					decisioni.add(scelte.get(i));
					contaValoriSelezionati++;
				}
			}
			if (contaValoriSelezionati != numeroScelte && numeroScelte != -1) {
				lblComunicazione.setForeground(Color.RED);
				return;
			}

			/*
			 * CHIAMATA A SERVER PER COMUNICAZIONE DECISIONI ( all'interno di
			 * ArrayList<String> decisioni AGGIORNAMENTO (utilizzare
			 * framePrincipale)
			 */

			if (azione == EAzioniGiocatore.Produzione || azione == EAzioniGiocatore.ProduzioneOvale) {
				EEffettiPermanenti[] eff = framePrincipale.getClient().getPlayersDashboards()
						.get(framePrincipale.getNomeGiocatore()).getEffettiPermanentiEdifici();
				if (eff != null && eff.length > 0) {
					EEffettiPermanenti[] scelte = new EEffettiPermanenti[eff.length];
					for (int i = 0; i < decisioni.size(); i++) {
						for (EEffettiPermanenti ec : eff) {
							if (decisioni.get(i).equals(ec.getDescrizione())) {
								scelte[i] = ec;
							}
						}
					}
					framePrincipale.movePawn(azione, colorePedina, 0, scelte);
				}
			} else if (azione == EAzioniGiocatore.Raccolto || azione == EAzioniGiocatore.RaccoltoOvale) {
				EEffettiPermanenti[] eff = framePrincipale.getClient().getPlayersDashboards()
						.get(framePrincipale.getNomeGiocatore()).getEffettiPermanentiTerritori();
				if (eff != null && eff.length > 0) {
					EEffettiPermanenti[] scelte = new EEffettiPermanenti[eff.length];

					for (int i = 0; i < decisioni.size(); i++) {
						for (EEffettiPermanenti ec : eff) {
							if (decisioni.get(i).equals(ec.getDescrizione())) {
								scelte[i] = ec;
							}
						}
					}

					framePrincipale.movePawn(azione, colorePedina, 0, scelte);
				}
			}

			framePrincipale.setVisible(true);
			frameSceltaCosti.setVisible(false);
		}

	}

}
