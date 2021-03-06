package main.ui.gui.components;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import res.images.Resources;

public class CartaScomunica extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3207034549846413587L;
	private String nomeCarta;
	private String pathImmagine;
	private ArrayList<CuboScomunica> cubiScomunica = new ArrayList<CuboScomunica>();

	public CartaScomunica(String nomeCarta, String pathImmagine) {
		super();
		this.nomeCarta = nomeCarta;
		this.pathImmagine = pathImmagine;
	}

	public void aggiungiCuboScomunica(CuboScomunica cuboScomunica) {
		cuboScomunica.setBounds(0 + cubiScomunica.size() * 12, 0, 10, 10);
		cubiScomunica.add(cuboScomunica);
		add(cuboScomunica);
	}

	public void removeCubiScomunica() {
		for (int i = 0; i < cubiScomunica.size(); i++) {
			remove(cubiScomunica.get(i));
		}
		cubiScomunica.clear();
	}

	public void paintComponent(Graphics g) {
		try {
			ImageIcon icon = new ImageIcon(Resources.class.getResource(pathImmagine));
			Image image = icon.getImage();
			g.drawImage(image, 0, 0, null);
		} catch (NullPointerException e) {
			System.err.println(e.getMessage() + ": " + pathImmagine);
		}
	}

}
