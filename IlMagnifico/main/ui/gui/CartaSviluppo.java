package main.ui.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CartaSviluppo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4709591449919711866L;
	private String nomeCarta;
	private String pathImmagine;

	private int N = 1, D = 1;

	public CartaSviluppo(String nomeCarta, String pathImmagine) {
		super();
		this.nomeCarta = nomeCarta;
		this.pathImmagine = pathImmagine;
	}

	public String getNomeCarta() {
		return nomeCarta;
	}

	public String getPath() {
		return pathImmagine;
	}

	public void setD(int D) {
		this.D = D;
	}

	public void setN(int N) {
		this.N = N;
	}

	public void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon(getClass().getResource(pathImmagine));
		Image image = icon.getImage();
		image = icon.getImage().getScaledInstance(icon.getIconWidth() * N / D, icon.getIconHeight() * N / D,
				Image.SCALE_SMOOTH);
		icon = new ImageIcon(image, icon.getDescription());
		g.drawImage(image, 0, 0, null);
	}
}
