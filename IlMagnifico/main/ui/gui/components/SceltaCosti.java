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
import main.model.enums.ECostiCarte;
import main.ui.gui.GUI;
import main.ui.gui.components.swing.ButtonLIM;
import main.util.Costants;
import res.images.Resources;

public class SceltaCosti extends JFrame {

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
	private int posTorre;
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
					SceltaCosti frame = new SceltaCosti(null);
					frame.setScelteCosti(scelte, 0);
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
	public SceltaCosti(GUI framePrincipale) {
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

	public void setScelteCosti(ArrayList<String> scelte, int posTorre) {
		this.scelte = scelte;
		this.posTorre = posTorre;
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
			radioButtons[i].setBounds(590, 200 + 30 * i, 500, 25);
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
		lblComunicazione = new JLabel("make " + numeroScelte + " choiches");
		lblComunicazione.setBounds(590, 500, 719, 35);
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

		public SceltaCosti frameSceltaCosti;

		public Conferma(SceltaCosti frameSceltaCosti) {
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
			if (contaValoriSelezionati != numeroScelte) {
				lblComunicazione.setForeground(Color.RED);
				return;
			}

			/*
			 * CHIAMATA A SERVER PER COMUNICAZIONE DECISIONI ( all'interno di
			 * ArrayList<String> decisioni AGGIORNAMENTO (utilizzare
			 * framePrincipale)
			 */

			ECostiCarte[] scelte = new ECostiCarte[numeroScelte];

			if (azione == EAzioniGiocatore.Torre) {
				for (int i = 0; i < decisioni.size(); i++) {
					for (ECostiCarte ec : ECostiCarte.values()) {
						if (decisioni.get(i).equals(ec.getDescrizione())) {
							scelte[i] = ec;
						}
					}
				}
				framePrincipale.movePawn(azione, colorePedina, posTorre, scelte);
			}

			framePrincipale.setVisible(true);
			frameSceltaCosti.setVisible(false);
		}

	}

}
