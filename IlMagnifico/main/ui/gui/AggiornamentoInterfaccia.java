package main.ui.gui;

import java.util.ArrayList;

import main.ui.gui.aggiornamento.Aggiornamento;
import main.ui.gui.aggiornamento.SGiocatore;
import main.util.Costants;

public class AggiornamentoInterfaccia {

	private Aggiornamento aggiornamento;
	private Frame frame;

	public AggiornamentoInterfaccia(Aggiornamento aggiornamento, Frame frame) {
		this.aggiornamento = aggiornamento;
		this.frame = frame;
	}

	public void setAggiornamento(Aggiornamento aggiornamento) {
		this.aggiornamento = aggiornamento;
	}

	public void aggiornaNomeGiocatori() {
		int planceRiempite = 0;
		for (int i = 0; i < aggiornamento.getGiocatori().size(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().setNomeGiocatore(frame.getNomeGiocatore(), frame.getColore());
			} else {
				frame.getPlanciaAvversario().getPlance().get(planceRiempite).setNomeGiocatore(
						aggiornamento.getGiocatori().get(i).getNome(), aggiornamento.getGiocatori().get(i).getColore());
				planceRiempite++;
			}
		}
	}

	public void aggiornaPunti() {
		int planceRiempite = 0;
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().setPuntiVittoria(giocatoreAgg.getPunti().getPuntiVittoria());
				frame.getPlancia().setPuntiMilitari(giocatoreAgg.getPunti().getPuntiMilitari());
				frame.getPlancia().setPuntiFede(giocatoreAgg.getPunti().getPuntiFede());
			} else {
				Plancia planciaAvv = frame.getPlanciaAvversario().getPlance().get(planceRiempite);
				planciaAvv.setPuntiVittoria(giocatoreAgg.getPunti().getPuntiVittoria());
				planciaAvv.setPuntiMilitari(giocatoreAgg.getPunti().getPuntiMilitari());
				planciaAvv.setPuntiFede(giocatoreAgg.getPunti().getPuntiFede());
				planceRiempite++;
			}
		}
	}

	public void aggiornaRisorse() {
		int planceRiempite = 0;
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().setMonete(giocatoreAgg.getRisorse().getMonete());
				frame.getPlancia().setLegno(giocatoreAgg.getRisorse().getLegno());
				frame.getPlancia().setPietre(giocatoreAgg.getRisorse().getPietre());
				frame.getPlancia().setServitori(giocatoreAgg.getRisorse().getServitori());
			} else {
				Plancia planciaAvv = frame.getPlanciaAvversario().getPlance().get(planceRiempite);
				planciaAvv.setMonete(giocatoreAgg.getRisorse().getMonete());
				planciaAvv.setLegno(giocatoreAgg.getRisorse().getLegno());
				planciaAvv.setPietre(giocatoreAgg.getRisorse().getPietre());
				planciaAvv.setServitori(giocatoreAgg.getRisorse().getServitori());
				planceRiempite++;
			}
		}
	}

	public void aggiornaScomuniche() {
		frame.getTabellone().rimuoviCarteScomunica();
		CartaScomunica[] carteScomunica = new CartaScomunica[aggiornamento.getCarteScomunica().length];
		for (int i = 0; i < carteScomunica.length; i++)
			carteScomunica[i] = new CartaScomunica(aggiornamento.getCarteScomunica()[i],
					Costants.FOLDER_EXCOMM_CARDS + "/" + aggiornamento.getCarteScomunica()[i] + ".png");
		frame.getTabellone().aggiungiCarteScomunica(carteScomunica);

		for (int i = 0; i < aggiornamento.getGiocatori().size(); i++) {
			for (int j = 0; j < aggiornamento.getGiocatori().get(i).getScomuniche().length; j++) {
				if (aggiornamento.getGiocatori().get(i).getScomuniche()[j]) {
					frame.getTabellone().getCarteScomunica().get(j)
							.add(new CuboScomunica(aggiornamento.getGiocatori().get(i).getColore()));
				}
			}
		}
	}

	public void aggiornaCarteSviluppoTorre() {
		frame.getTabellone().rimuoviCarteTorri();
		CartaSviluppo[] carteTorre = new CartaSviluppo[aggiornamento.getCarteSviluppoTorre().length];
		for (int i = 0; i < aggiornamento.getCarteSviluppoTorre().length; i++) {
			if (aggiornamento.getCarteSviluppoTorre()[i] != null)
				carteTorre[i] = new CartaSviluppo(aggiornamento.getCarteSviluppoTorre()[i],
						Costants.FOLDER_DEV_CARDS + "/" + aggiornamento.getCarteSviluppoTorre()[i] + ".png");
			else
				carteTorre[i] = null;
		}
		frame.getTabellone().aggiungiCarteTorri(carteTorre);
		frame.aggiungiListenerCarteTorre();
	}

	public void aggiornaFamigliariPlancia() {
		frame.getPlancia().rimuoviFamigliariStart();
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				Famigliare[] famigliariPlancia = new Famigliare[4];
				for (int j = 0; j < 4; j++) {
					if (giocatoreAgg.getFamigliariPlancia().get(j) != null) {
						int valore = giocatoreAgg.getFamigliariPlancia().get(j).getValore();
						int numero = giocatoreAgg.getFamigliariPlancia().get(j).getNumero();
						String colore = giocatoreAgg.getFamigliariPlancia().get(j).getColore();
						String giocatoreAppartenenza = giocatoreAgg.getFamigliariPlancia().get(j)
								.getGiocatoreAppartenenza();
						famigliariPlancia[j] = new Famigliare(valore, numero, colore, giocatoreAppartenenza);
					} else
						famigliariPlancia[j] = null;

				}
				frame.getPlancia().aggiungiFamigliariStart(famigliariPlancia);
			}
		}
		frame.aggiungiListenerFamigliariPlancia();
	}

	public void aggiornaCarteTerritorio() {
		int planceRiempite = 0;
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().rimuoviCarteTerritorio();
				CartaSviluppo[] carteTerritorio = new CartaSviluppo[giocatoreAgg.getCarteTerritorio().size()];
				for (int j = 0; j < giocatoreAgg.getCarteTerritorio().size(); j++) {
					carteTerritorio[j] = new CartaSviluppo(giocatoreAgg.getCarteTerritorio().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCarteTerritorio().get(j) + ".png");
				}
				frame.getPlancia().aggiungiCarteTerritorio(carteTerritorio);
			} else {
				Plancia planciaAvv = frame.getPlanciaAvversario().getPlance().get(planceRiempite);
				planciaAvv.rimuoviCarteTerritorio();
				CartaSviluppo[] carteTerritorio = new CartaSviluppo[giocatoreAgg.getCarteTerritorio().size()];
				for (int j = 0; j < giocatoreAgg.getCarteTerritorio().size(); j++) {
					carteTerritorio[j] = new CartaSviluppo(giocatoreAgg.getCarteTerritorio().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCarteTerritorio().get(j) + ".png");
				}
				planciaAvv.aggiungiCarteTerritorio(carteTerritorio);
				planceRiempite++;
			}
		}
	}

	public void aggiornaCarteEdificio() {
		int planceRiempite = 0;
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().rimuoviCarteProduzione();
				CartaSviluppo[] carteProduzione = new CartaSviluppo[giocatoreAgg.getCarteEdificio().size()];
				for (int j = 0; j < giocatoreAgg.getCarteEdificio().size(); j++) {
					carteProduzione[j] = new CartaSviluppo(giocatoreAgg.getCarteEdificio().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCarteEdificio().get(j) + ".png");
				}
				frame.getPlancia().aggiungiCarteTerritorio(carteProduzione);
			} else {
				Plancia planciaAvv = frame.getPlanciaAvversario().getPlance().get(planceRiempite);
				planciaAvv.rimuoviCarteProduzione();
				CartaSviluppo[] carteProduzione = new CartaSviluppo[giocatoreAgg.getCarteEdificio().size()];
				for (int j = 0; j < giocatoreAgg.getCarteEdificio().size(); j++) {
					carteProduzione[j] = new CartaSviluppo(giocatoreAgg.getCarteEdificio().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCarteEdificio().get(j) + ".png");
				}
				planciaAvv.aggiungiCarteProduzione(carteProduzione);
				planceRiempite++;
			}
		}
	}

	public void aggiornaCarteImprese() {
		int planceRiempite = 0;
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().rimuoviCarteImprese();
				CartaSviluppo[] carteImprese = new CartaSviluppo[giocatoreAgg.getCarteImprese().size()];
				for (int j = 0; j < giocatoreAgg.getCarteImprese().size(); j++) {
					carteImprese[j] = new CartaSviluppo(giocatoreAgg.getCarteImprese().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCarteImprese().get(j) + ".png");
				}
				frame.getPlancia().aggiungiCarteImprese(carteImprese);
			} else {
				Plancia planciaAvv = frame.getPlanciaAvversario().getPlance().get(planceRiempite);
				planciaAvv.rimuoviCarteImprese();
				CartaSviluppo[] carteImprese = new CartaSviluppo[giocatoreAgg.getCarteImprese().size()];
				for (int j = 0; j < giocatoreAgg.getCarteImprese().size(); j++) {
					carteImprese[j] = new CartaSviluppo(giocatoreAgg.getCarteImprese().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCarteImprese().get(j) + ".png");
				}
				planciaAvv.aggiungiCarteImprese(carteImprese);
				planceRiempite++;
			}
		}
	}

	public void aggiornaCartePersonaggio() {
		int planceRiempite = 0;
		for (int i = 0; i < frame.getNumeroGiocatori(); i++) {
			SGiocatore giocatoreAgg = aggiornamento.getGiocatori().get(i);
			if (giocatoreAgg.getNome().equals(frame.getNomeGiocatore())
					&& giocatoreAgg.getColore().equals(frame.getColore())) {
				frame.getPlancia().rimuoviCartePersonaggio();
				CartaSviluppo[] cartePersonaggio = new CartaSviluppo[giocatoreAgg.getCartePersonaggio().size()];
				for (int j = 0; j < giocatoreAgg.getCartePersonaggio().size(); j++) {
					cartePersonaggio[j] = new CartaSviluppo(giocatoreAgg.getCartePersonaggio().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCartePersonaggio().get(j) + ".png");
				}
				frame.getPlancia().aggiungiCartePersonaggio(cartePersonaggio);
			} else {
				Plancia planciaAvv = frame.getPlanciaAvversario().getPlance().get(planceRiempite);
				planciaAvv.rimuoviCartePersonaggio();
				CartaSviluppo[] cartePersonaggio = new CartaSviluppo[giocatoreAgg.getCartePersonaggio().size()];
				for (int j = 0; j < giocatoreAgg.getCartePersonaggio().size(); j++) {
					cartePersonaggio[j] = new CartaSviluppo(giocatoreAgg.getCartePersonaggio().get(j),
							Costants.FOLDER_DEV_CARDS + "/" + giocatoreAgg.getCartePersonaggio().get(j) + ".png");
				}
				planciaAvv.aggiungiCartePersonaggio(cartePersonaggio);
				planceRiempite++;
			}
		}
	}

	public void aggiornaFamigliariTorre() {
		SpazioFamigliare[] torre = frame.getTabellone().getTorre();
		for (int i = 0; i < torre.length; i++) {
			torre[i].rimuoviFamigliari();
			if (aggiornamento.getTorre()[i] != null) {
				int valore = aggiornamento.getTorre()[i].getValore();
				int numero = aggiornamento.getTorre()[i].getValore();
				String colore = aggiornamento.getTorre()[i].getColore();
				String giocatoreAppartenenza = aggiornamento.getTorre()[i].getGiocatoreAppartenenza();
				torre[i].addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
			}
		}
	}

	public void aggiornaMercato() {
		SpazioFamigliare[] mercato = frame.getTabellone().getMercato();
		for (int i = 0; i < mercato.length; i++) {
			mercato[i].rimuoviFamigliari();
			if (aggiornamento.getMercato()[i] != null) {
				int valore = aggiornamento.getMercato()[i].getValore();
				int numero = aggiornamento.getMercato()[i].getNumero();
				String colore = aggiornamento.getMercato()[i].getColore();
				String giocatoreAppartenenza = aggiornamento.getMercato()[i].getGiocatoreAppartenenza();
				mercato[i].addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
			}
		}
	}

	public void aggiornamentoRaccoltoRotondo() {
		SpazioFamigliare raccoltoRotondo = frame.getTabellone().getRaccoltoRotondo();
		raccoltoRotondo.rimuoviFamigliari();
		if (aggiornamento.getRaccoltoRotondo() != null) {
			int valore = aggiornamento.getRaccoltoRotondo().getValore();
			int numero = aggiornamento.getRaccoltoRotondo().getNumero();
			String colore = aggiornamento.getRaccoltoRotondo().getColore();
			String giocatoreAppartenenza = aggiornamento.getRaccoltoRotondo().getGiocatoreAppartenenza();
			raccoltoRotondo.addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
		}
	}

	public void aggiornamentoProduzioneRotondo() {
		SpazioFamigliare produzioneRotondo = frame.getTabellone().getProduzioneRotondo();
		produzioneRotondo.rimuoviFamigliari();
		if (aggiornamento.getProduzioneRotondo() != null) {
			int valore = aggiornamento.getProduzioneRotondo().getValore();
			int numero = aggiornamento.getProduzioneRotondo().getNumero();
			String colore = aggiornamento.getProduzioneRotondo().getColore();
			String giocatoreAppartenenza = aggiornamento.getProduzioneRotondo().getGiocatoreAppartenenza();
			produzioneRotondo.addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
		}
	}

	public void aggiornamentoRaccoltoOvale() {
		SpazioFamigliare raccoltoOvale = frame.getTabellone().getRaccoltoOvale();
		raccoltoOvale.rimuoviFamigliari();
		if (aggiornamento.getRaccoltoOvale() != null) {
			for (int i = 0; i < aggiornamento.getRaccoltoOvale().size(); i++) {
				int valore = aggiornamento.getRaccoltoOvale().get(i).getValore();
				int numero = aggiornamento.getRaccoltoOvale().get(i).getNumero();
				String colore = aggiornamento.getRaccoltoOvale().get(i).getColore();
				String giocatoreAppartenenza = aggiornamento.getRaccoltoOvale().get(i).getGiocatoreAppartenenza();
				raccoltoOvale.addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
			}
		}
	}

	public void aggiornamentoProduzioneOvale() {
		SpazioFamigliare produzioneOvale = frame.getTabellone().getProduzioneOvale();
		produzioneOvale.rimuoviFamigliari();
		if (aggiornamento.getProduzioneOvale() != null) {
			for (int i = 0; i < aggiornamento.getProduzioneOvale().size(); i++) {
				int valore = aggiornamento.getProduzioneOvale().get(i).getValore();
				int numero = aggiornamento.getProduzioneOvale().get(i).getNumero();
				String colore = aggiornamento.getProduzioneOvale().get(i).getColore();
				String giocatoreAppartenenza = aggiornamento.getProduzioneOvale().get(i).getGiocatoreAppartenenza();
				produzioneOvale.addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
			}
		}
	}

	public void aggiornamentoPalazzoConsiglio() {
		SpazioFamigliare palazzoConsiglio = frame.getTabellone().getPalazzoConsiglio();
		palazzoConsiglio.rimuoviFamigliari();
		if (aggiornamento.getPalazzoConsiglio() != null) {
			for (int i = 0; i < aggiornamento.getPalazzoConsiglio().size(); i++) {
				int valore = aggiornamento.getPalazzoConsiglio().get(i).getValore();
				int numero = aggiornamento.getPalazzoConsiglio().get(i).getNumero();
				String colore = aggiornamento.getPalazzoConsiglio().get(i).getColore();
				String giocatoreAppartenenza = aggiornamento.getPalazzoConsiglio().get(i).getGiocatoreAppartenenza();
				palazzoConsiglio.addFamigliare(new Famigliare(valore, numero, colore, giocatoreAppartenenza));
			}
		}
	}

	public void aggiornamentoOrdineTurno() {
		String[] colori = new String[frame.getNumeroGiocatori()];
		for (int i = 0; i < colori.length; i++) {
			colori[i] = aggiornamento.getGiocatori().get(i).getColore();
		}
		frame.getTabellone().getOrdineTurno().aggiornaOrdineTurno(colori);
	}

	public void aggiornaTurno() {
		frame.setTurno(aggiornamento.getTurno());
		frame.getTabellone().getLblTurnoPeriodo().setText("<html><br>TURN: " + frame.getTurno() + "</br><br>PERIOD: "
				+ (frame.getTurno() + 1) / 2 + "</br></html>");
	}

	public void Inizializza() {

		frame.setNumeroGiocatori(aggiornamento.getGiocatori().size());

		frame.setTabellone(new Tabellone());
		frame.getTabellone().setVisible(true);
		frame.getContentPane().add(frame.getTabellone());

		frame.setPlancia(new Plancia(frame.getNomeGiocatore()));
		frame.getPlancia().setVisible(false);
		frame.getContentPane().add(frame.getPlancia());

		ArrayList<Plancia> avv = new ArrayList<Plancia>();
		for (int i = 0; i < frame.getNumeroGiocatori() - 1; i++) {
			avv.add(new Plancia(null));
		}
		/*
		 * avv.add(new Plancia(null)); avv.get(avv.size()-1).setMonete(2);
		 * avv.add(new Plancia(null)); avv.get(avv.size()-1).setMonete(4);
		 * avv.add(new Plancia(null)); avv.get(avv.size()-1).setMonete(6);
		 */
		PlanciaAvversario planciaAvversario = new PlanciaAvversario(avv);
		frame.setPlanciaAvversario(planciaAvversario);
		frame.getPlanciaAvversario().setVisible(false);
		frame.getContentPane().add(frame.getPlanciaAvversario());

		frame.aggiungiBottoni();

		frame.setTurno(1);
		frame.getTabellone().aggiungiLblTurnoPeriodo();
		frame.getTabellone().getLblTurnoPeriodo().setText("<html><br>TURNO: " + frame.getTurno() + "</br><br>PERIODO: "
				+ (frame.getTurno() + 1) / 2 + "</br></html>");

		frame.aggiungiListenerFamigliariPlancia();
		frame.aggiungiListenerTorreTabellone();
		frame.aggiungiListenerRaccoltoRotondoTabellone();
		frame.aggiungiListenerRaccoltoOvaleTabellone();
		frame.aggiungiListenerProduzioneRotondoTabellone();
		frame.aggiungiListenerProduzioneOvaleTabellone();
		frame.aggiungiListenerMercatoTabellone();
		frame.aggiungiListenerPalazzoConsiglioTabellone();
		frame.aggiungiListenerPanelServitore();
	}

	public void aggiornaTutto() {
		////////////////////////////////////////////////////////////////////////
		// nel caso di aggiunta servitori vorrei rimanere nello stesso "panel"//
		boolean isPlanciaVisible = false, isPlanciaAvversarioVisible = false;
		if (frame.getPlancia() != null && frame.getPlancia().isVisible())
			isPlanciaVisible = true;
		if (frame.getPlanciaAvversario() != null && frame.getPlanciaAvversario().isVisible())
			isPlanciaAvversarioVisible = true;
		////////////////////////////////////////////////////////////////////////

		if (frame.getPlancia() != null)
			frame.remove(frame.getPlancia());
		if (frame.getTabellone() != null)
			frame.remove(frame.getTabellone());
		if (frame.getPlanciaAvversario() != null)
			frame.remove(frame.getPlanciaAvversario());

		Inizializza();
		aggiornaNomeGiocatori();
		aggiornaTurno();
		aggiornaPunti();
		aggiornaRisorse();
		aggiornaFamigliariPlancia();
		aggiornaScomuniche();
		aggiornaCarteTerritorio();
		aggiornaCarteEdificio();
		aggiornaCarteImprese();
		aggiornaCartePersonaggio();
		aggiornaFamigliariTorre();
		aggiornaMercato();
		aggiornamentoRaccoltoRotondo();
		aggiornamentoProduzioneRotondo();
		aggiornamentoRaccoltoOvale();
		aggiornamentoProduzioneOvale();
		aggiornamentoPalazzoConsiglio();
		aggiornamentoOrdineTurno();
		aggiornaCarteSviluppoTorre();

		////////////////////////////////////////////////////////////////////////

		frame.repaint();

		////////////////////////////////////////////////////////////////////////
		// nel caso di aggiunta servitori vorrei rimanere nello stesso "panel"//
		if (isPlanciaVisible) {
			frame.getBtnMostraTabellone().setBounds(1093, 11, 127, 32);
			frame.getBtnMostraPlancia().setVisible(false);
			frame.getBtnMostraTabellone().setVisible(true);
			frame.getTabellone().setVisible(false);
			frame.getPlancia().setVisible(true);
			frame.getPlanciaAvversario().setVisible(false);
		} else if (isPlanciaAvversarioVisible) {
			frame.getBtnMostraTabellone().setBounds(961, 11, 127, 32);
			frame.getBtnMostraTabellone().setVisible(true);
			frame.getBtnMostraPlancia().setVisible(true);
			frame.getTabellone().setVisible(false);
			frame.getPlancia().setVisible(false);
			frame.getPlanciaAvversario().setVisible(true);
		}
		////////////////////////////////////////////////////////////////////////

	}
}
