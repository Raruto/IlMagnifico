package main.network.client;

import java.rmi.RemoteException;

import main.model.enums.EAzioniGiocatore;
import main.model.enums.EFasiDiGioco;
import main.model.errors.Errors;
import main.network.server.game.Game;
import main.network.server.game.UpdateStats;

/**
 * Interfaccia usata come client controller in {@link AbstractClient} (notifiche
 * dal server verso il client stesso).
 */
public interface IClient {

	/**
	 * Notifica che � arrivato un nuovo messaggio dalla chat.
	 * 
	 * @param privateMessage
	 *            "True" se il messaggio � privato, "False" se pubblico.
	 * @param author
	 *            autore del messaggio.
	 * @param message
	 *            corpo del messaggio.
	 */
	void onChatMessage(boolean privateMessage, String author, String message);

	/**
	 * Metodo invocato dal Server ogni qualvolta si presenta un errore (es.
	 * azione illegale) a seguito di una richiesta del giocatore (vedi
	 * {@link Client#performGameAction(UpdateStats)})
	 * 
	 * @param errorCode
	 *            stringa contenenti informazioni sull'errore (vedi
	 *            {@link Errors}).
	 */
	public void onActionNotValid(String errorCode);

	/**
	 * Metodo invocato dal Server ogni qualvolta l'azione richiesta dal
	 * giocatore è stata accettata (vedi
	 * {@link Client#performGameAction(UpdateStats)}) oppure si è verificato
	 * un'avanzamento nello stato della logica della partita (vedi
	 * {@link Game}).
	 * 
	 * @param update
	 *            oggetto aggiornamento contenente tutte le informazioni
	 *            relative all'avanzamento della partita (vedi
	 *            {@link UpdateStats}).
	 */
	public void onGameUpdate(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#SostegnoChiesa}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onChurchSupport(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#Mercato}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onMarket(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#Famigliare}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onPayServant(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#Torre}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onTower(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#PalazzoConsiglio}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onCouncilPalace(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#Raccolto}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onHarvestRound(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#Produzione}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onProductionRound(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#RaccoltoOvale}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onHarvestOval(UpdateStats update);

	/**
	 * Scatenato quando il server ha autorizzato un giocatore ad eseguire:
	 * {@link EAzioniGiocatore#ProduzioneOvale}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onProductionOval(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#FineTurno}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onTurnEnd(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#FinePeriodo}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onPeriodEnd(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#FinePartita}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onGameEnd(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#MossaGiocatore}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onPlayerMove(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#InizioTurno}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onTurnStarted(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#InizioPeriodo}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onPeriodStarted(UpdateStats update);

	/**
	 * Scatenato quando il server notifica {@link EFasiDiGioco#InizioPartita}.
	 * 
	 * @param update
	 *            in ingresso un oggetto aggiornamento {@link UpdateStats}
	 *            contenente lo stato della partita con tutte le modifiche
	 *            apportate allo stato della partita.
	 */
	public void onGameStarted(UpdateStats update);

	/**
	 * Metodo per il "debug"
	 */
	void onNotify(Object object) throws RemoteException;
}