package main.game.activatable;

import java.util.ArrayList;

import main.game.players.Giocatore;

public class Effetto extends Attivabile {
	// vado a prendere dall'enumerazione degli effetti l'effetto corrispondente
	private Effetti effetto;

	/**
	 * 
	 */

	public Effetto() {
	}

	public void attiva(Giocatore giocatore) {
		this.effetto.attiva(giocatore);
	}

/*public static ArrayList<Effetto> generaArrayEffetti(String nomeEffetto){
	ArrayList<Effetto> effetti=new ArrayList<Effetto>();
	for(Effetti e : Effetti.values()){
		if(nomeEffetto.equals(e.getEffettoPrincipale()))				{	
								if((e.getEffettoAlternativo().equals(null)))
			effetti.add(0, new Effetto());
				else		
					effetti.add(0, new Effetto());
	}
	return effetti;
}
}*/
}
