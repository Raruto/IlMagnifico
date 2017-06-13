package main.ui.gui.altriComponenti;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImmagine extends JPanel {
		
	private String pathImmagine;
	
	public PanelImmagine(String pathImmagine){
		this.pathImmagine = pathImmagine;
		setVisible(true);
	}
	
	public void setPathImmagine(String nuovoPath){
		this.pathImmagine = nuovoPath;
	}
	
	public void paintComponent(Graphics g){
		ImageIcon icon = new ImageIcon(pathImmagine);
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}
}
