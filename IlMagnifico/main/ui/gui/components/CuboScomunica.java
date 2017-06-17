package main.ui.gui.components;

import java.awt.Color;

import javax.swing.JPanel;

public class CuboScomunica extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1364144286017018984L;
	private String colore;

	public CuboScomunica(String colore) {
		this.colore = colore;
		if (colore.equals("rosso"))
			setBackground(Color.RED);
		else if (colore.equals("verde"))
			setBackground(Color.GREEN);
		else if (colore.equals("giallo"))
			setBackground(Color.YELLOW);
		else if (colore.equals("blu"))
			setBackground(Color.BLUE);
	}

}
