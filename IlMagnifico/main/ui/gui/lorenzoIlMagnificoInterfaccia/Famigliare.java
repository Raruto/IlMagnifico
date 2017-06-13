package main.ui.gui.lorenzoIlMagnificoInterfaccia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Famigliare extends JPanel {
	
	private int valore;
	private int numero;
	private String colore;
	private String giocatoreAppartenenza;
	private JLabel lblValore = new JLabel();
	
	public Famigliare(int valore, int numero, String colore, String giocatoreAppartenenza){
		this.valore = valore;
		this.numero = numero;
		this.colore = colore;
		this.giocatoreAppartenenza = giocatoreAppartenenza;
		
		setOpaque(true);
		lblValore.setText(valore+"");
		formatoTesto();
		lblValore.setFont(new Font("ALGERIAN", 25, 50));
		add(lblValore);
	}
	
	public int getValore() { return valore; }
	public int getNumero() { return numero; }
	public String getColore() { return colore; }
	public String getGiocatoreAppartenenza() { return giocatoreAppartenenza; }
	
	public void formatoTesto(){
		if(colore.equals("giallo") || numero == 3) lblValore.setForeground(Color.BLACK);
		else lblValore.setForeground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g){
		String pathImmagine = "";
		if(colore.equals("rosso")) {
			if(numero != 3) pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareRosso.png";
			else pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareRossoNeutro.png";
			
		}
		else if(colore.equals("verde")) {
			if(numero != 3) pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareVerde.png";
			else pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareVerdeNeutro.png";
		}
		else if(colore.equals("giallo")) {
			if(numero != 3) pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareGiallo.png";
			else pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareGialloNeutro.png";
		}
		else if(colore.equals("blu")) {
			if(numero != 3) pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareBlu.png";
			else pathImmagine = "./src/lorenzoIlMagnificoInterfaccia/famigliareBluNeutro.png";
		}
		
		ImageIcon icon = new ImageIcon(pathImmagine);
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}
	
}
