package main.ui.gui.lorenzoIlMagnificoInterfaccia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SpazioFamigliare extends JPanel {
	
	private ArrayList<Famigliare> occupanti = new ArrayList<Famigliare>();
	//JPanel panelInterno = new JPanel();
	
	public SpazioFamigliare(){
		setLayout(null);
		setOpaque(false);
		
		/*add(panelInterno);
		panelInterno.setOpaque(false);
		panelInterno.setVisible(true);
		panelInterno.setBounds(0, 0, 500, 100);
		panelInterno.setLayout(null);*/
		
	}
	
	public ArrayList<Famigliare> getOccupanti(){
		return occupanti;
	}
	
	public void addFamigliare(Famigliare nuovoOccupante){
		nuovoOccupante.setBounds(occupanti.size()*45, 0, 42, 75);
		occupanti.add(nuovoOccupante);
		//panelInterno.
		add(nuovoOccupante);
		//for(int i=0; i<occupanti.size(); i++) System.out.println("ciao");
		//occupanti.get(0).repaint();
	}
	
	public void rimuoviFamigliari(){
		for(int i=0; i<occupanti.size(); i++){
			remove(occupanti.get(i));
		}
		occupanti.clear();
	}

}
