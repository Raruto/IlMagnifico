package main.ui.gui.lorenzoIlMagnificoInterfaccia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.ui.gui.altriComponenti.ButtonLIM;
import main.ui.gui.altriComponenti.PanelImmagine;

public class ClassificaFinaleFrame extends JFrame {
	
	private ClassificaFinaleFrame frame = this;
	private JPanel contentPane;
	private ButtonLIM btnTornaAPaginaIniziale;
	
	ArrayList<Classifica> classifica = new ArrayList<Classifica>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassificaFinaleFrame frame = new ClassificaFinaleFrame();
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
	public ClassificaFinaleFrame() {
		setIconImage(new ImageIcon(getClass().getResource("main/gui/ui/lorenzoIlMagnificoInterfaccia/giglio.png")).getImage());
		setTitle("         lorenzo il magnifico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane.setBackground(Color.BLACK);
		
		btnTornaAPaginaIniziale = new ButtonLIM("TORNA ALLA PAGINA INIZIALE");
		btnTornaAPaginaIniziale.setBounds(500, 550, 300, 50);
		btnTornaAPaginaIniziale.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				UsernameFrame nuovaPaginaLogin = new UsernameFrame();
				nuovaPaginaLogin.setVisible(true);
			}
			
		});
		add(btnTornaAPaginaIniziale);
		
		aggiornamento();
		
	}
	
	public void aggiornamento(){
		/*
		 * CHIAMATA A SERVER + OTTENERE GIOCATORI (CON COLORE E POSIZIONE)
		 */
		// ESEMPIO
		
		classifica.add(new Classifica("Pino", "rosso", 2, this));
		classifica.add(new Classifica("Bruno", "blu", 3, this));
		classifica.add(new Classifica("Beppe", "verde", 1, this));
	}
	
	private class Classifica extends JPanel {
		
		private ClassificaFinaleFrame frame;
		
		private JLabel lblNome;
		private PanelImmagine panelColore;
		
		private String nomeGiocatore;
		private String colore;
		private int posizione;
		
		public Classifica(String nomeGiocatore, String colore, int posizione, ClassificaFinaleFrame frame){
			
			this.frame = frame;
			this.nomeGiocatore = nomeGiocatore;
			this.colore = colore;
			this.posizione = posizione;
			
			setPannello();
		}
		
		public String getNomeGiocatore() { return nomeGiocatore; }
		
		public String getColore() { return colore; }
		
		public int getPosizione() { return posizione; }
		
		public void setPannello(){
			setLayout(null);
			setOpaque(false);
			
			lblNome = new JLabel("               "+posizione+" - "+nomeGiocatore);
			lblNome.setFont(new Font("ALGERIAN", 50, 30));
			if(colore.equals("rosso")) lblNome.setForeground(Color.RED);
			else if(colore.equals("giallo")) lblNome.setForeground(Color.YELLOW);
			else if(colore.equals("verde")) lblNome.setForeground(Color.GREEN);
			else if(colore.equals("blu")) lblNome.setForeground(Color.BLUE);
			lblNome.setBounds(0, 0, 350, 45);
			add(lblNome);
			
			if(colore.equals("rosso")) panelColore = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoRosso.png");
			else if(colore.equals("giallo")) panelColore = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoGiallo.png");
			else if(colore.equals("verde")) panelColore = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoVerde.png");
			else if(colore.equals("blu")) panelColore = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoBlu.png");
			panelColore.setLayout(null);
			panelColore.setOpaque(true);
			panelColore.setBounds(0, 0, 50, 50);
			panelColore.setVisible(true);
			add(panelColore);
			
			
			int y = 0;
			if(posizione == 1) y = 100;
			else if(posizione == 2) y = 200;
			else if(posizione == 3) y = 300;
			else y = 400;
			setBounds(500, y, 500, 50);
			setVisible(true);
			frame.getContentPane().add(this);
		}
	}

}
