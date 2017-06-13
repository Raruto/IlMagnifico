package main.ui.gui.lorenzoIlMagnificoInterfaccia;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CartaScomunica extends JPanel{
	
	private String nomeCarta;
	private String pathImmagine;
	private ArrayList<CuboScomunica> cubiScomunica = new ArrayList<CuboScomunica>();
	
	public CartaScomunica (String nomeCarta, String pathImmagine){
		super();
		this.nomeCarta = nomeCarta;
		this.pathImmagine = pathImmagine;
	}
	
	public void aggiungiCuboScomunica(CuboScomunica cuboScomunica){
		cuboScomunica.setBounds(0+cubiScomunica.size()*12, 0, 10, 10);
		cubiScomunica.add(cuboScomunica);
		add(cuboScomunica); 
	}
	
	public void removeCubiScomunica(){
		for(int i=0; i<cubiScomunica.size(); i++){
			remove(cubiScomunica.get(i));
		}
		cubiScomunica.clear();
	}
	
	public void paintComponent(Graphics g){
		ImageIcon icon = new ImageIcon(pathImmagine);
		Image image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}

}
