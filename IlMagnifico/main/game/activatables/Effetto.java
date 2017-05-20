package main.game.activatables;

import java.util.ArrayList;

import main.game.players.Giocatore;

public class Effetto extends Attivabile {
	// vado a prendere dall'enumerazione degli effetti l'effetto corrispondente
	private Effetti effetto;

	/**
	 * 
	 */

	public Effetto(Effetti effetto) {
		this.effetto = effetto;
	}

	public void attiva(Giocatore giocatore) {
		this.effetto.attiva(giocatore);
	}

	/**
	 * Metodo d'appoggio per ottenere dal nome di un effetto composto una lista
	 * di eventi semplici
	 * 
	 * @param nomeEffetto
	 * @return ArrayList<Effetto>
	 */
	public static ArrayList<Effetto> nomeEffettoToArrayEffetti(String nomeEffetto) {
		ArrayList<Effetto> effetti = new ArrayList<Effetto>();

		// DALLA DOCUMENTAZIONE:
		// Il Privilegio del Consiglio è un bonus a scelta tra:
		// 1 legno e 1 pietra / 2 servitori / 2 monete /
		// 2 Punti Militari / 1 Punto Fede

		// DALL'ENUM Effetti
		// EFFETTO("categoria","nome", legna, monete, pietra, servitori,
		// puntiFede ,puntiMilitari, puntiVittoria, cumulabile, sottoGruppo)

		// es: PRIVILEGIO DEL CONSIGLIO
		// PRIVILEGIO_DEL_CONSIGLIO_1("privilegio_del_consiglio_1",
		// "aumenta_legna_pietra_uno", 1, 0, 1, 0, 0, 0, 0, false, 1);
		//
		// PRIVILEGIO_DEL_CONSIGLIO_1("privilegio_del_consiglio_1",
		// "aumenta_monete_due", 0, 2, 0, 0, 0, 0, 0, false, 1);
		//
		// PRIVILEGIO_DEL_CONSIGLIO_1("privilegio_del_consiglio_1",
		// "aumenta_servitori_due", 0, 0, 0, 2, 0, 0, 0, false, 1);
		//
		// PRIVILEGIO_DEL_CONSIGLIO_1("privilegio_del_consiglio_1",
		// "aumenta_punti_militari_due", 0, 0, 0, 0, 0, 2, 0, false, 1);
		//
		// PRIVILEGIO_DEL_CONSIGLIO_1("privilegio_del_consiglio_1",
		// "aumenta_punti_fede_due", 0, 0, 0, 0, 2, 0, 0, false, 1);

		for (Effetti e1 : Effetti.values()) {
			// es: "privilegio_del_consiglio_1"
			if (nomeEffetto.equals(e1.getCategoriaEffetto())) {
				// es: "aumenta_legna_pietra_uno","aumenta_monete_due",
				// "aumenta_servitori_due", "aumenta_punti_militari_due",
				// "aumenta_punti_fede_due"
				for (Effetti e2 : Effetti.values()) {
					if ((e1.getCategoriaEffetto().equals(e2.getCategoriaEffetto())))
						effetti.add(new Effetto(e2));
				}
			}
		}
		return effetti;
	}
}
