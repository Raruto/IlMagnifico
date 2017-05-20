package main.game.cards.development;

import java.util.*;

import main.game.activatables.Effetti;
import main.game.activatables.Effetto;
import main.game.players.PuntiGiocatore;
import main.game.players.RiservaGiocatore;
import main.game.players.RisorseGiocatore;

/**
 * 
 */
public class CartaTerritorio extends CartaSviluppo {

	private CarteTerritorio carta;

	public CarteTerritorio getCarta() {
		return this.carta;
	}

	public CartaTerritorio(CarteTerritorio c) {

		super(c.getNomeCarta(), c.getPeriodoCarta(),
				new RiservaGiocatore(new RisorseGiocatore(c.getLegna(), c.getMonete(), c.getPietra(), c.getServitori()),
						new PuntiGiocatore(c.getPuntiFede(), c.getPuntiMilitari(), c.getPuntiVittoria())),
				Effetto.nomeEffettoToArrayEffetti(c.getNomeEffettoImmediato()),
				Effetto.nomeEffettoToArrayEffetti(c.getNomeEffettoPermanente()));

		this.carta = c;

		/*
		 * Guardo negli effetti quelli che hanno l'effetto principale indicato
		 * nella carta e riempio l'arraylist degli effetti indicati nelle
		 * alternative dell'effetto principale (es. con l'effetto
		 * PRIVILEGIO_DEL_CONSIGLIO scorro l'enum e riempio l'arraylist di tutti
		 * gli effetti alternativi di PRIVILEGIO_DEL_CONSIGLIO). Ditemi se può
		 * andare bene o se invece ho sbagliato qualcosa
		 */
		// for (Effetti eTemp1 : Effetti.values()) {
		// if (c.getEffImm() == eTemp1.getEffettoPrincipale() &&
		// !(eTemp1.getEffettoAlternativo() == null)) {
		// for (Effetti eTemp2 : Effetti.values()) {
		// if (eTemp1.getEffettoAlternativo() == eTemp2.getEffettoPrincipale())
		// this.effettiImmediati.add(0, new Effetto(eTemp2));
		// }
		// }
		// if (c.getEffImm() == eTemp1.getEffettoPrincipale() &&
		// eTemp1.getEffettoAlternativo() == null)
		// this.effettiImmediati.add(0, new Effetto(eTemp1));
		// }
		/*
		 * Faccio la stessa cosa con gli effetti permanenti
		 */
		// for (Effetti eTemp1 : Effetti.values()) {
		// if (c.getEffPerm() == eTemp1.getEffettoPrincipale() &&
		// !(eTemp1.getEffettoAlternativo() == null)) {
		// for (Effetti eTemp2 : Effetti.values()) {
		// if (eTemp1.getEffettoAlternativo() == eTemp2.getEffettoPrincipale())
		// this.effettiPermanenti.add(0, new Effetto(eTemp2));
		// }
		// }
		// if (c.getEffPerm() == eTemp1.getEffettoPrincipale() &&
		// eTemp1.getEffettoAlternativo() == null)
		// this.effettiPermanenti.add(0, new Effetto(eTemp1));
		// }
	}

}
