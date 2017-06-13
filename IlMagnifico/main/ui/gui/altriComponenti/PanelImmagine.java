package main.ui.gui.altriComponenti;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImmagine extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8294675557121399496L;
	private String pathImmagine;

	public PanelImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
		setVisible(true);
	}

	public void setPathImmagine(String nuovoPath) {
		this.pathImmagine = nuovoPath;
	}

	public void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(getClass().getResource(pathImmagine));
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}
}
