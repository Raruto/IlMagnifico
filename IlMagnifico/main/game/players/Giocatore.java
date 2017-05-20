package main.game.players;

/**
 * Classe per la gestione del Giocatore, composto di: - PlanciaGiocatore -
 * PuntiGiocatore - RisorseGiocatore - FamigliaGiocatore
 */
public class Giocatore {

	/**
	 * Nome del Giocatore
	 */
	private String nome;

	/**
	 * Plancia per tenere traccia delle Carte Sviluppo associate al Giocatore
	 */
	private PlanciaGiocatore plancia;

	/**
	 * Punti vittoria e Punti Militari
	 */
	private PuntiGiocatore punti;

	/**
	 * Denari, Legno, Servitori, Pietre
	 */
	private RisorseGiocatore risorse;

	/**
	 * Pedina Arancione, Bianca, Nera, Neutra
	 */
	private FamigliaGiocatore famiglia;

	/**
	 * Default constructor
	 */
	public Giocatore() {
		// TODO: decidere a quale valore inizializzare le pedine del giocatore
		int dadoArancione = 0, dadoBianco = 0, dadoNero = 0, pedinaNeutrale = 0;

		famiglia = new FamigliaGiocatore(dadoArancione, dadoBianco, dadoNero, pedinaNeutrale, this);
	}

	/**
	 * Imposta il valore di tutte le Pedine associate al giocatore
	 * 
	 * @param pedinaBianca
	 * @param pedinaArancione
	 * @param pedinaNera
	 * @param pedinaNeutrale
	 */
	public void setValoriPedine(int pedinaBianca, int pedinaArancione, int pedinaNera, int pedinaNeutrale) {

		// Si potrebbe anche passare l'oggetto "Dado dado" e dopo:

		// dado.getDadoBianco();
		// dado.getDadoArancione();
		// dado.getDadoNero();

		// Dipende da come vogliamo suddividere gli ambiti

		famiglia.setValorePedinaArancione(pedinaArancione);
		famiglia.setValorePedinaBianca(pedinaBianca);
		famiglia.setValorePedinaNera(pedinaNera);
		famiglia.setValorePedinaNeutrale(pedinaNeutrale);
	}

}