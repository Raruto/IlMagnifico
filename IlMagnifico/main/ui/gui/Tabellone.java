package main.ui.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.util.Costants;
import res.images.Resources;

public class Tabellone extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8798989223596717964L;

	private String pathSfondo = Costants.FOLDER_BASE + "/tabelloneSfondo.png";

	private ArrayList<CartaSviluppo> carteTorre = new ArrayList<CartaSviluppo>();
	private ArrayList<CartaScomunica> carteScomunica = new ArrayList<CartaScomunica>();
	private OrdineTurno ordineTurno = new OrdineTurno();
	private SpazioFamigliare[] torre = new SpazioFamigliare[16];
	private SpazioFamigliare[] mercato = new SpazioFamigliare[4];
	private SpazioFamigliare raccoltoOvale;
	private SpazioFamigliare raccoltoRotondo;
	private SpazioFamigliare produzioneOvale;
	private SpazioFamigliare produzioneRotondo;
	private SpazioFamigliare palazzoConsiglio;
	private JLabel lblTurnoPeriodo = new JLabel();

	public Tabellone() {
		setBounds(0, 50, 1362, 644);
		setLayout(null);
		setVisible(true);

		aggiungiOrdineTurno();
		aggiungiTorri();
		aggiungiMercato();
		aggiungiRaccolto();
		aggiungiProduzione();
		aggiungiPalazzoConsiglio();
	}

	public void setPathSfondo(String pathSfondo) {
		this.pathSfondo = pathSfondo;
	}

	public SpazioFamigliare[] getTorre() {
		return torre;
	}

	public SpazioFamigliare[] getMercato() {
		return mercato;
	}

	public SpazioFamigliare getRaccoltoOvale() {
		return raccoltoOvale;
	}

	public SpazioFamigliare getRaccoltoRotondo() {
		return raccoltoRotondo;
	}

	public SpazioFamigliare getProduzioneRotondo() {
		return produzioneRotondo;
	}

	public SpazioFamigliare getProduzioneOvale() {
		return produzioneOvale;
	}

	public SpazioFamigliare getPalazzoConsiglio() {
		return palazzoConsiglio;
	}

	public OrdineTurno getOrdineTurno() {
		return ordineTurno;
	}

	public ArrayList<CartaScomunica> getCarteScomunica() {
		return carteScomunica;
	}

	public ArrayList<CartaSviluppo> getCarteTorre() {
		return carteTorre;
	}

	public JLabel getLblTurnoPeriodo() {
		return lblTurnoPeriodo;
	}

	public void aggiungiOrdineTurno() {
		ordineTurno.setBackground(Color.GREEN);
		ordineTurno.setBounds(1250, 0, 60, 220);
		add(ordineTurno);
	}

	public void aggiungiLblTurnoPeriodo() {
		lblTurnoPeriodo.setBounds(1250, 200, 150, 80);
		lblTurnoPeriodo.setForeground(Color.WHITE);
		lblTurnoPeriodo.setFont(new Font("ALGERIAN", 50, 20));
		add(lblTurnoPeriodo);
	}

	public void aggiungiTorri() {
		torre[0] = new SpazioFamigliare();
		torre[0].setBounds(115, 65, 50, 80);
		add(torre[0]);
		torre[1] = new SpazioFamigliare();
		torre[1].setBounds(115, 220, 50, 80);
		add(torre[1]);
		torre[2] = new SpazioFamigliare();
		torre[2].setBounds(115, 375, 50, 80);
		add(torre[2]);
		torre[3] = new SpazioFamigliare();
		torre[3].setBounds(115, 530, 50, 80);
		add(torre[3]);
		torre[4] = new SpazioFamigliare();
		torre[4].setBounds(295, 65, 50, 80);
		add(torre[4]);
		torre[5] = new SpazioFamigliare();
		torre[5].setBounds(295, 220, 50, 80);
		add(torre[5]);
		torre[6] = new SpazioFamigliare();
		torre[6].setBounds(295, 375, 50, 80);
		add(torre[6]);
		torre[7] = new SpazioFamigliare();
		torre[7].setBounds(295, 530, 50, 80);
		add(torre[7]);
		torre[8] = new SpazioFamigliare();
		torre[8].setBounds(475, 65, 50, 80);
		add(torre[8]);
		torre[9] = new SpazioFamigliare();
		torre[9].setBounds(475, 220, 50, 80);
		add(torre[9]);
		torre[10] = new SpazioFamigliare();
		torre[10].setBounds(475, 375, 50, 80);
		add(torre[10]);
		torre[11] = new SpazioFamigliare();
		torre[11].setBounds(475, 530, 50, 80);
		add(torre[11]);
		torre[12] = new SpazioFamigliare();
		torre[12].setBounds(655, 65, 50, 80);
		add(torre[12]);
		torre[13] = new SpazioFamigliare();
		torre[13].setBounds(655, 220, 50, 80);
		add(torre[13]);
		torre[14] = new SpazioFamigliare();
		torre[14].setBounds(655, 375, 50, 80);
		add(torre[14]);
		torre[15] = new SpazioFamigliare();
		torre[15].setBounds(655, 530, 50, 80);
		add(torre[15]);
	}

	public void aggiungiMercato() {
		mercato[0] = new SpazioFamigliare();
		mercato[0].setBounds(810, 335, 50, 80);
		add(mercato[0]);
		mercato[1] = new SpazioFamigliare();
		mercato[1].setBounds(920, 335, 50, 80);
		add(mercato[1]);
		mercato[2] = new SpazioFamigliare();
		mercato[2].setBounds(1030, 335, 50, 80);
		add(mercato[2]);
		mercato[3] = new SpazioFamigliare();
		mercato[3].setBounds(1130, 335, 50, 80);
		add(mercato[3]);
	}

	public void aggiungiRaccolto() {
		raccoltoRotondo = new SpazioFamigliare();
		raccoltoRotondo.setBounds(810, 440, 50, 80);
		add(raccoltoRotondo);
		raccoltoOvale = new SpazioFamigliare();
		raccoltoOvale.setBounds(920, 440, 300, 80);
		add(raccoltoOvale);
	}

	public void aggiungiProduzione() {
		produzioneRotondo = new SpazioFamigliare();
		produzioneRotondo.setBounds(810, 540, 50, 80);
		add(produzioneRotondo);
		produzioneOvale = new SpazioFamigliare();
		produzioneOvale.setBounds(920, 540, 300, 80);
		add(produzioneOvale);
	}

	public void aggiungiPalazzoConsiglio() {
		palazzoConsiglio = new SpazioFamigliare();
		palazzoConsiglio.setBounds(790, 210, 300, 80);
		add(palazzoConsiglio);

	}

	public void aggiungiCarteTorri(CartaSviluppo[] carte) {
		for (int i = 0; i < carte.length; i++) {
			carteTorre.add(carte[i]);
			if (carte[i] != null) {
				if (i == 0)
					carte[i].setBounds(15, 30, 98, 135);
				else if (i == 1)
					carte[i].setBounds(15, 190, 98, 135);
				else if (i == 2)
					carte[i].setBounds(15, 345, 98, 135);
				else if (i == 3)
					carte[i].setBounds(15, 500, 98, 135);
				else if (i == 4)
					carte[i].setBounds(195, 30, 98, 135);
				else if (i == 5)
					carte[i].setBounds(195, 190, 98, 135);
				else if (i == 6)
					carte[i].setBounds(195, 345, 98, 135);
				else if (i == 7)
					carte[i].setBounds(195, 500, 98, 135);
				else if (i == 8)
					carte[i].setBounds(380, 30, 98, 135);
				else if (i == 9)
					carte[i].setBounds(380, 190, 98, 135);
				else if (i == 10)
					carte[i].setBounds(380, 345, 98, 135);
				else if (i == 11)
					carte[i].setBounds(380, 500, 98, 135);
				else if (i == 12)
					carte[i].setBounds(555, 30, 98, 135);
				else if (i == 13)
					carte[i].setBounds(555, 190, 98, 135);
				else if (i == 14)
					carte[i].setBounds(555, 345, 98, 135);
				else if (i == 15)
					carte[i].setBounds(555, 500, 98, 135);
				add(carte[i]);
			}

		}
		for (int i = 0; i < carte.length; i++) {
			if (carte[i] != null) {
				carte[i].setD(4);
				carte[i].setN(3);
			}
		}
	}

	public void rimuoviCarteTorri() {
		for (int i = 0; i < carteTorre.size(); i++) {
			remove(carteTorre.get(i));
		}
		carteTorre.clear();
	}

	public void aggiungiCarteScomunica(CartaScomunica[] carte) {
		for (int i = 0; i < carte.length; i++) {
			carteScomunica.add(carte[i]);
			if (i == 0)
				carte[i].setBounds(800, 10, 120, 180);
			else if (i == 1)
				carte[i].setBounds(920, 10, 120, 180);
			else if (i == 2)
				carte[i].setBounds(1040, 10, 120, 180);
			add(carte[i]);
		}
	}

	public void rimuoviCarteScomunica() {
		for (int i = 0; i < carteScomunica.size(); i++) {
			remove(carteScomunica.get(i));
		}
		carteScomunica.clear();
	}

	public void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(Resources.class.getResource(pathSfondo));
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}

}
