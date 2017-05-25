package old.game.activatables;

import old.game.players.Giocatore;

public class Attivabile {

	private int contatoreAttivazioni;
	private boolean permanente;

	public void attiva(Giocatore giocatore) {
		//TODO: if(attivabile?){
		incrementaAttivazioni();
		//}
	}

	private void incrementaAttivazioni() {
		contatoreAttivazioni++;
	}
}
