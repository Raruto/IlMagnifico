package main.game.players;

/**
 * Classe che definisce il concetto di Riserva Giocatore, composta di:
 * {@link RisorseGiocatore}, {@link PuntiGiocatore}
 */
public class RiservaGiocatore {

	/**
	 * Risorse Giocatore
	 */
	private RisorseGiocatore risorse;

	/**
	 * Punti Giocatore
	 */
	private PuntiGiocatore punti;

	/**
	 * Default constructor
	 */
	public RiservaGiocatore(RisorseGiocatore risorse, PuntiGiocatore punti) {
		this.risorse = risorse;
		this.punti = punti;
	}

	public RisorseGiocatore getRisorseGiocatore() {
		return this.risorse;
	}

	public PuntiGiocatore getPuntiGiocatore() {
		return this.punti;
	}

	public void setRisorseGiocatore(RisorseGiocatore risorse) {
		this.risorse = risorse;
	}

	public void setPuntiGiocatore(PuntiGiocatore punti) {
		this.punti = punti;
	}

}