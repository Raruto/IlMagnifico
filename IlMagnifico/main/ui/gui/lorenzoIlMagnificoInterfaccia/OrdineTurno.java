package main.ui.gui.lorenzoIlMagnificoInterfaccia;

import javax.swing.JPanel;

import main.ui.gui.altriComponenti.PanelImmagine;

public class OrdineTurno extends JPanel {
	
	private PanelImmagine[] segnaTurno = new PanelImmagine[4];
	
	public OrdineTurno(){
		setVisible(true);
		setOpaque(false);
		setLayout(null);
	}
	
	public void aggiornaOrdineTurno(String colore[]){
		for(int i=0; i<colore.length; i++){
			if(colore[i] == "rosso") segnaTurno[i] = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoRosso.png");
			else if(colore[i] == "verde") segnaTurno[i] = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoVerde.png");
			else if(colore[i] == "blu") segnaTurno[i] = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoBlu.png");
			else if(colore[i] == "giallo") segnaTurno[i] = new PanelImmagine("main/ui/gui/lorenzoIlMagnificoInterfaccia/segnaTurnoGiallo.png");
			segnaTurno[i].setBounds(0, 50*i+10, 50, 50);
			add(segnaTurno[i]);
		}
	}
	
	public void rimuoviOrdineTurno(){
		for(int i=0; i<segnaTurno.length; i++){
			remove(segnaTurno[i]);
			segnaTurno[i] = null;
		}
	}
}
