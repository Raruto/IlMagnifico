package main.network.server.game;

import java.io.Serializable;

import main.model.Giocatore;
import main.util.EAzioniGiocatore;

public class UpdateStats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6689449538127752618L;

	private EAzioniGiocatore azioneGiocatore;

	private String nomeGiocatore;

	public UpdateStats(Giocatore giocatore, EAzioniGiocatore azione) {
		this.azioneGiocatore = azione;
		this.nomeGiocatore = giocatore.getNome();
	}

	public EAzioniGiocatore getAzioneGiocatore() {
		return azioneGiocatore;
	}

	public void setAzioneGiocatore(EAzioniGiocatore azioneGiocatore) {
		this.azioneGiocatore = azioneGiocatore;
	}

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

}
