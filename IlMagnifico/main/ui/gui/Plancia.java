package main.ui.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.ui.gui.altriComponenti.PanelImmagine;
import main.util.Costants;

public class Plancia extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7325431608492287012L;
	private String nomeGiocatore;
	private ArrayList<CartaSviluppo> carteTerritorio = new ArrayList<CartaSviluppo>();
	private ArrayList<CartaSviluppo> carteProduzione = new ArrayList<CartaSviluppo>();
	private ArrayList<CartaSviluppo> carteImprese = new ArrayList<CartaSviluppo>();
	private ArrayList<CartaSviluppo> cartePersonaggio = new ArrayList<CartaSviluppo>();

	private int puntiVittoria = 0;
	private int puntiMilitari = 0;
	private int puntiFede = 0;
	private int monete = 0;
	private int servitori = 0;
	private int pietre = 0;
	private int legno = 0;
	private ArrayList<SpazioFamigliare> startFamigliari = new ArrayList<SpazioFamigliare>();

	private JLabel lblNomeGiocatore;
	private JLabel lblMonete;
	private JLabel lblLegno;
	private JLabel lblPietre;
	private JLabel lblServitori;
	private JLabel lblPuntiVittoria;
	private JLabel lblPuntiMilitari;
	private JLabel lblPuntiFede;
	private PanelImmagine panelPuntiVittoria;
	private PanelImmagine panelPuntiFede;
	private PanelImmagine panelPuntiMilitari;
	private ArrayList<PannelloCarta> pannelloCarteImprese = new ArrayList<PannelloCarta>();
	private ArrayList<PannelloCarta> pannelloCartePersonaggio = new ArrayList<PannelloCarta>();
	private PanelImmagine mostraCartaImpresePersonaggio = new PanelImmagine(null);
	private JPanel panelServitore = new JPanel();

	private String pathSfondo = Costants.PATH_RESOURCES + "/plancia.png";

	Plancia(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
		setBounds(0, 50, 1362, 644);
		setLayout(null);
		setVisible(true);

		setNomeGiocatore(nomeGiocatore, null);
		aggiungiPannelloMostraCarteImpresePersonaggio();
		aggiungiLabelMonete();
		aggiungiLabelLegno();
		aggiungiLabelPietre();
		aggiungiLabelServitori();
		aggiungiPanelServitori();

		aggiungiPuntiVittoria();
		aggiungiPuntiMilitari();
		aggiungiPuntiFede();
	}

	public void setNomeGiocatore(String nomeGiocatore, String colore) {
		this.nomeGiocatore = nomeGiocatore;
		if (lblNomeGiocatore != null) {
			if (colore.equals("rosso"))
				lblNomeGiocatore.setBackground(Color.RED);
			else if (colore.equals("verde"))
				lblNomeGiocatore.setBackground(Color.GREEN);
			else if (colore.equals("blu")) {
				lblNomeGiocatore.setBackground(Color.BLUE);
				lblNomeGiocatore.setForeground(Color.WHITE);
			} else if (colore.equals("giallo"))
				lblNomeGiocatore.setBackground(Color.YELLOW);
			lblNomeGiocatore.setText(nomeGiocatore);
			return;
		}
		lblNomeGiocatore = new JLabel();
		lblNomeGiocatore.setOpaque(true);
		lblNomeGiocatore.setBackground(Color.WHITE);
		lblNomeGiocatore.setText(nomeGiocatore);
		lblNomeGiocatore.setFont(new Font("ALGERIAN", 20, 20));
		lblNomeGiocatore.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeGiocatore.setVisible(true);
		lblNomeGiocatore.setBounds(0, 50, 100, 50);
		lblNomeGiocatore.setLayout(null);
		add(lblNomeGiocatore);
	}

	public void setPuntiVittoria(int puntiVittoria) {
		this.puntiVittoria = puntiVittoria;
		lblPuntiVittoria.setText(puntiVittoria + "");
	}

	public void setPuntiMilitari(int puntiMilitari) {
		this.puntiMilitari = puntiMilitari;
		lblPuntiMilitari.setText(puntiMilitari + "");
	}

	public void setPuntiFede(int puntiFede) {
		this.puntiFede = puntiFede;
		lblPuntiFede.setText(puntiFede + "");
	}

	public void setMonete(int monete) {
		this.monete = monete;
		lblMonete.setText(monete + "");
	}

	public void setLegno(int legno) {
		this.legno = legno;
		lblLegno.setText(legno + "");
	}

	public void setPietre(int pietre) {
		this.pietre = pietre;
		lblPietre.setText(pietre + "");
	}

	public void setServitori(int servitori) {
		this.servitori = servitori;
		lblServitori.setText(servitori + "");
	}

	public void setPathSfondo(String pathSfondo) {
		this.pathSfondo = pathSfondo;
	}

	public JPanel getPanelServitore() {
		return panelServitore;
	}

	public void aggiungiLabelMonete() {
		lblMonete = new JLabel(monete + "");
		lblMonete.setBounds(240, 470, 100, 100);
		lblMonete.setVisible(true);
		lblMonete.setForeground(Color.BLACK);
		lblMonete.setFont(new Font("ALGERIAN", 100, 50));
		add(lblMonete);
	}

	public void aggiungiLabelLegno() {
		lblLegno = new JLabel(legno + "");
		lblLegno.setBounds(400, 470, 100, 100);
		lblLegno.setVisible(true);
		lblLegno.setForeground(Color.BLACK);
		lblLegno.setFont(new Font("ALGERIAN", 100, 50));
		add(lblLegno);
	}

	public void aggiungiLabelPietre() {
		lblPietre = new JLabel(pietre + "");
		lblPietre.setBounds(550, 470, 100, 80);
		lblPietre.setVisible(true);
		lblPietre.setForeground(Color.BLACK);
		lblPietre.setFont(new Font("ALGERIAN", 100, 50));
		add(lblPietre);
	}

	public void aggiungiLabelServitori() {
		lblServitori = new JLabel(servitori + "");
		lblServitori.setBounds(680, 500, 100, 80);
		lblServitori.setVisible(true);
		lblServitori.setForeground(Color.BLACK);
		lblServitori.setFont(new Font("ALGERIAN", 100, 50));
		add(lblServitori);
	}

	public void aggiungiPanelServitori() {
		panelServitore = new JPanel();
		panelServitore.setBounds(675, 575, 70, 70);
		panelServitore.setOpaque(false);
		panelServitore.setVisible(true);
		add(panelServitore);
	}

	public void aggiungiPuntiVittoria() {
		panelPuntiVittoria = new PanelImmagine(Costants.PATH_RESOURCES + "/puntiVittoria.png");
		panelPuntiVittoria.setBounds(10, 400, 60, 60);
		add(panelPuntiVittoria);
		lblPuntiVittoria = new JLabel(puntiVittoria + "");
		lblPuntiVittoria.setBounds(100, 400, 100, 60);
		lblPuntiVittoria.setVisible(true);
		lblPuntiVittoria.setForeground(Color.WHITE);
		lblPuntiVittoria.setFont(new Font("ALGERIAN", 100, 50));
		add(lblPuntiVittoria);
	}

	public void aggiungiPuntiMilitari() {
		panelPuntiMilitari = new PanelImmagine(Costants.PATH_RESOURCES + "/puntiMilitari.png");
		panelPuntiMilitari.setBounds(10, 475, 100, 60);
		add(panelPuntiMilitari);
		lblPuntiMilitari = new JLabel(puntiMilitari + "");
		lblPuntiMilitari.setBounds(100, 475, 60, 60);
		lblPuntiMilitari.setVisible(true);
		lblPuntiMilitari.setForeground(Color.WHITE);
		lblPuntiMilitari.setFont(new Font("ALGERIAN", 100, 50));
		add(lblPuntiMilitari);
	}

	public void aggiungiPuntiFede() {
		panelPuntiFede = new PanelImmagine(Costants.PATH_RESOURCES + "/puntiFede.png");
		panelPuntiFede.setBounds(10, 550, 100, 60);
		add(panelPuntiFede);
		lblPuntiFede = new JLabel(puntiFede + "");
		lblPuntiFede.setBounds(100, 550, 60, 60);
		lblPuntiFede.setVisible(true);
		lblPuntiFede.setForeground(Color.WHITE);
		lblPuntiFede.setFont(new Font("ALGERIAN", 100, 50));
		add(lblPuntiFede);
	}

	public void aggiungiPannelloMostraCarteImpresePersonaggio() {
		/*
		 * mostraCartaImpresePersonaggio = new PanelImmagine(
		 * Costants.PATH_RESOURCES + "/famigliareBlu.png" );
		 * mostraCartaImpresePersonaggio.setBounds(1200, 20, 120, 180);
		 * mostraCartaImpresePersonaggio.setOpaque(true);
		 * mostraCartaImpresePersonaggio.setVisible(true);
		 * this.add(mostraCartaImpresePersonaggio);
		 */
		mostraCartaImpresePersonaggio = new PanelImmagine(null);
		mostraCartaImpresePersonaggio.setBounds(1200, 20, 120, 180);
		mostraCartaImpresePersonaggio.setOpaque(true);
		mostraCartaImpresePersonaggio.setVisible(false);
		this.add(mostraCartaImpresePersonaggio);
	}

	public ArrayList<SpazioFamigliare> getStartFamigliari() {
		return startFamigliari;
	}

	public void aggiungiCarteTerritorio(CartaSviluppo[] carte) {
		for (int i = 0; i < carte.length; i++) {
			carteTerritorio.add(carte[i]);
			if (i == 0)
				carte[i].setBounds(170, 280, 130, 180);
			else if (i == 1)
				carte[i].setBounds(330, 280, 130, 180);
			else if (i == 2)
				carte[i].setBounds(490, 280, 130, 180);
			else if (i == 3)
				carte[i].setBounds(650, 280, 130, 180);
			else if (i == 4)
				carte[i].setBounds(810, 280, 130, 180);
			else if (i == 5)
				carte[i].setBounds(970, 280, 130, 180);
			add(carte[i]);
		}
	}

	public void rimuoviCarteTerritorio() {
		for (int i = 0; i < carteTerritorio.size(); i++) {
			remove(carteTerritorio.get(i));
		}
		carteTerritorio.clear();
	}

	public void aggiungiCarteProduzione(CartaSviluppo[] carte) {
		for (int i = 0; i < carte.length; i++) {
			carteProduzione.add(carte[i]);
			if (i == 0)
				carte[i].setBounds(170, 10, 130, 180);
			else if (i == 1)
				carte[i].setBounds(330, 10, 130, 180);
			else if (i == 2)
				carte[i].setBounds(490, 10, 130, 180);
			else if (i == 3)
				carte[i].setBounds(650, 10, 130, 180);
			else if (i == 4)
				carte[i].setBounds(810, 10, 130, 180);
			else if (i == 5)
				carte[i].setBounds(970, 10, 130, 180);
			add(carte[i]);
		}
	}

	public void rimuoviCarteProduzione() {
		for (int i = 0; i < carteProduzione.size(); i++) {
			remove(carteProduzione.get(i));
		}
		carteProduzione.clear();
	}

	public void aggiungiCarteImprese(CartaSviluppo[] carte) {
		for (int i = 0; i < carte.length; i++) {
			carteImprese.add(carte[i]);
			pannelloCarteImprese.add(new PannelloCarta(carte[i], this));
			pannelloCarteImprese.get(i).setBounds(1110 + 35 * (i % 2), 50 + 20 * (i - i % 2), 30, 30);
			this.add(pannelloCarteImprese.get(i));
		}
	}

	public void rimuoviCarteImprese() {
		for (int i = 0; i < pannelloCarteImprese.size(); i++) {
			remove(pannelloCarteImprese.get(i));
		}
		carteImprese.clear();
	}

	public void aggiungiCartePersonaggio(CartaSviluppo[] carte) {
		for (int i = 0; i < carte.length; i++) {
			cartePersonaggio.add(carte[i]);
			pannelloCartePersonaggio.add(new PannelloCarta(carte[i], this));
			pannelloCartePersonaggio.get(i).setBounds(1110 + 35 * (i % 2), 300 + 20 * (i - i % 2), 30, 30);
			this.add(pannelloCartePersonaggio.get(i));
		}
	}

	public void rimuoviCartePersonaggio() {
		for (int i = 0; i < pannelloCartePersonaggio.size(); i++) {
			remove(pannelloCartePersonaggio.get(i));
		}
		cartePersonaggio.clear();
	}

	public void aggiungiFamigliariStart(Famigliare[] famigliari) {
		System.out.println("size " + famigliari.length);
		for (int i = 0; i < famigliari.length; i++) {
			if (famigliari[i] != null) {
				famigliari[i].setBounds(0, 0, 42, 75);
				startFamigliari.add(new SpazioFamigliare());
				startFamigliari.get(i).setBounds(800 + 60 * i, 550, 50, 80);
				startFamigliari.get(i).setVisible(true);
				startFamigliari.get(i).addFamigliare(famigliari[i]);
				add(startFamigliari.get(i));
			} else {
				startFamigliari.add(null);
			}

		}
	}

	public void rimuoviFamigliariStart() {
		for (int i = 0; i < startFamigliari.size(); i++) {
			if (startFamigliari.get(i) != null)
				remove(startFamigliari.get(i));
		}
		startFamigliari.clear();
	}

	public void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(getClass().getResource(pathSfondo));
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}

	private class PannelloCarta extends JPanel implements MouseListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5460170892565144958L;
		private CartaSviluppo carta;

		public PannelloCarta(CartaSviluppo carta, Plancia plancia) {
			this.carta = carta;
			setVisible(true);
			addMouseListener(this);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			mostraCartaImpresePersonaggio.setPathImmagine(carta.getPath());
			mostraCartaImpresePersonaggio.setVisible(true);
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			mostraCartaImpresePersonaggio.setVisible(false);
			mostraCartaImpresePersonaggio.setPathImmagine(null);
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void paintComponent(Graphics g) {
			ImageIcon icon = new ImageIcon(getClass().getResource(Costants.PATH_RESOURCES + "/pannelloCarta2.png"));
			Image image = icon.getImage();
			g.drawImage(image, 0, 0, null);
		}

	}

}
