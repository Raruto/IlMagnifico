package main.game.activatable;

import main.game.enums.Effetti;
import main.game.players.Giocatore;

public class Effetto extends Attivabili {
	//vado a prendere dall'enumerazione degli effetti l'effetto corrispondente
private Effetti effetto;

public void attiva(Giocatore giocatore){
	this.effetto.attiva(giocatore);
}
}
