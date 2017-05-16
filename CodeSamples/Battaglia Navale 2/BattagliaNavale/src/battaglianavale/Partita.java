/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Carlo Castelnuovo
 */
public class Partita extends Thread {

    private Giocatore giocatore1, giocatore2;
    private Griglia griglia1, griglia2;
    private Socket socketgiocatore1, socketgiocatore2;
    private BufferedReader inSocket1, inSocket2, inKeyboard;
    private PrintWriter outSocket1, outSocket2, outVideo;

    public Partita(Socket socket1, Socket socket2) {
        try {
            this.socketgiocatore1 = socket1;
            this.socketgiocatore2 = socket2;
            inSocket1 = new BufferedReader(new InputStreamReader(socketgiocatore1.getInputStream()));
            inSocket2 = new BufferedReader(new InputStreamReader(socketgiocatore2.getInputStream()));
            inKeyboard = new BufferedReader(new InputStreamReader(System.in));
            outSocket1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketgiocatore1.getOutputStream())), true);
            outSocket2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketgiocatore2.getOutputStream())), true);
            outVideo = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), true);
            System.out.println("tutti i client connessi");
            start();
        } catch (Exception e) {
            System.out.println("errore nell'impostazione degli stream");
            try {
                socketgiocatore1.close();
                socketgiocatore2.close();
            } catch (Exception etc) {
            }
        }
    }

    public void run() {
        boolean vittoria;
        vittoria = false;
        Preparazione();
        while (true) {
            vittoria = TurnoGiocatore(this.giocatore1, this.giocatore2, this.griglia1, this.griglia2, this.inSocket1, this.outSocket1, this.outSocket2);
            if (vittoria == true) {
                this.outSocket1.println("hai vinto");
                this.outSocket1.flush();
                this.outSocket2.println("sei vinto");
                this.outSocket2.flush();
                break;
            }
            vittoria = TurnoGiocatore(this.giocatore2, this.giocatore1, this.griglia2, this.griglia1, this.inSocket2, this.outSocket2, this.outSocket1);
            if (vittoria == true) {
                this.outSocket2.println("hai vinto");
                this.outSocket2.flush();
                this.outSocket1.println("sei vinto");
                this.outSocket1.flush();
                break;
            }
        }
        System.out.println("fine partita");
    }

    public void Preparazione() {
        this.giocatore1 = new Giocatore();
        this.giocatore2 = new Giocatore();
        
        this.griglia1=new Griglia();
        this.griglia2=new Griglia();
        
        this.griglia1.SetGriglia(10);
        this.griglia2.SetGriglia(10);
        
        
        try {
            outSocket1.println("richiedo nome");
            outSocket1.flush();
            outSocket2.println("attendi");
            outSocket2.flush();
            this.giocatore1.SetGiocatore(this.inSocket1.readLine(), 10);
            outSocket2.println("richiedo nome");
            outSocket2.flush();
            outSocket1.println("attendi");
            outSocket1.flush();
            this.giocatore2.SetGiocatore(this.inSocket2.readLine(), 10);
            this.outSocket2.println("attendi");
            this.outSocket2.flush();
            PosizionaNavi(this.giocatore1, this.griglia1);
            this.outSocket1.println("attendi");
            this.outSocket1.flush();
            PosizionaNavi(this.giocatore2, this.griglia2);
        } catch (Exception prep) {
            outVideo.println("errore nella preparazione del gioco");
            try {
                this.socketgiocatore1.close();
                this.socketgiocatore2.close();
            } catch (Exception chiusura) {
                System.out.println("errore nella chiusura dei socket");
            }
        }
    }

    public void PosizionaNavi(Giocatore giocatore, Griglia campo) {
        BufferedReader inSocket;
        PrintWriter outSocket;
        int i, ascissa, ordinata;
        String direzione;
        if (giocatore == this.giocatore1) {
            inSocket = this.inSocket1;
            outSocket = this.outSocket1;
        } else {
            inSocket = this.inSocket2;
            outSocket = this.outSocket2;
        }
        try {
            for (i = 0; i < 4; i++) {
                PosizionamentoNaveGiocatore(inSocket, outSocket, 2, Nave.tiponave.CACCIATORPEDINIERE, campo, giocatore, i);
            }
            for (i = 0; i < 3; i++) {
               PosizionamentoNaveGiocatore(inSocket, outSocket, 3, Nave.tiponave.CORAZZATA, campo, giocatore, i+4);
            }
            for (i = 0; i < 2; i++) {
               PosizionamentoNaveGiocatore(inSocket, outSocket, 4, Nave.tiponave.PORTAEREI, campo, giocatore, i+7);
                }
                PosizionamentoNaveGiocatore(inSocket, outSocket, 5, Nave.tiponave.SOTTOMARINO, campo, giocatore, 9);
            }
         catch (Exception posizionamento) {
            outVideo.println("errore nel posizionamento");
            try {
                this.socketgiocatore1.close();
                this.socketgiocatore2.close();
            } catch (Exception ex) {
                System.out.println("errore nella chiusura dei socket nel posizionamento");
            }
        }
    }

    public boolean TurnoGiocatore(Giocatore giocatorediturno, Giocatore giocatoreinattesa, Griglia grigliaditurno, Griglia grigliainattesa, BufferedReader inputg1, PrintWriter outputg1, PrintWriter outputg2) {
        boolean controllo;
        int i, ascissa, ordinata;
        String opzione, esito;
        controllo = false;
        while (controllo == false) {
            try {
                outputg1.println("tuoturno");
                outputg1.flush();
                opzione = inputg1.readLine();
                if (opzione.equals("navialtrogiocatore")) {
                    outputg1.println(giocatoreinattesa.GetNumeroNavi());
                    for (i = 0; i < 10; i++) {
                        if (giocatoreinattesa.GetDimensioneNaveGiocatore(i) == 0) {
                            outputg1.println(giocatoreinattesa.GetTipoNaveGiocatore(i));//do al giocatore quante navi ha ancora l'avversario a disposizione e quali tipi sono già stati affondati
                        }
                    }
                    controllo = false;
                }
                if (opzione.equals("tuenavi")) {
                    outputg1.println(giocatorediturno.GetNumeroNavi());
                    for (i = 0; i < 10; i++) {
                        outputg1.println(giocatorediturno.GetTipoNaveGiocatore(i));
                    }
                    controllo = false;
                }
                if (opzione.equals("sparo")) {
                    ascissa = Integer.parseInt(inputg1.readLine());
                    ordinata = Integer.parseInt(inputg1.readLine());
                    esito = grigliaditurno.Sparo(ascissa, ordinata);
                    if (esito.equals("colpito")) {
                        outputg1.println("hai colpito");
                        outputg1.flush();
                        outputg2.println("sei colpito");
                        outputg2.flush();
                        controllo = false;
                    }
                    if (esito.equals("acqua")) {
                        outputg1.println("acqua");
                        outputg1.flush();
                        outputg2.println("mancato");
                        outputg2.flush();
                        controllo = true;
                    }
                    if (esito.equals("colpito e affondato")) {
                        outputg1.println("affondato");
                        outputg1.flush();
                        outputg2.println("affondatoti");
                        outputg2.flush();
                        if (giocatoreinattesa.GetNumeroNavi() == 0) {
                            return true;
                        } else {
                            controllo = false;
                        }
                    }
                }
            } catch (Exception e) {
            	e.printStackTrace();
                System.out.println("errore nel turnogiocatore");
                try {
                    this.socketgiocatore1.close();
                    this.socketgiocatore2.close();
                    return false;
                } catch (Exception ex) {
                    System.out.println("errore nella chiusura dei socket nel turnogiocatore");
                    return false;
                }
            }
        }
        return false;
    }

    public void PosizionamentoNaveGiocatore(BufferedReader ingresso, PrintWriter uscita, int dimensioneNave, Nave.tiponave tipo, Griglia campo, Giocatore giocatore, int i) {
        int ascissa, ordinata;
        String direzione;
        try {
            while (true) {
            	//System.out.println(campo.toString());
                uscita.println("ascissa e ordinata");
                uscita.flush();
                ascissa = Integer.parseInt(ingresso.readLine());
                ordinata = Integer.parseInt(ingresso.readLine());
                uscita.println("direzione");
                uscita.flush();
                direzione = ingresso.readLine();
                                
                if (campo.ControlloTotaleNave(ascissa, ordinata, direzione, 2)) {
                    campo.PosizionamentoNave(ascissa, ordinata, 2, Nave.tiponave.CACCIATORPEDINIERE, Nave.statonave.INDENNE);
                    giocatore.SetNaveGiocatore(i, campo.GetNaveCella(ascissa, ordinata));//vado a creare il riferimento nell'oggetto giocatore
                    break;
                }
                uscita.println("non valido");
                uscita.flush();
            }
        } catch (Exception posizionamento) {
            outVideo.println("errore nel posizionamento");
            try {
                this.socketgiocatore1.close();
                this.socketgiocatore2.close();

            } catch (Exception ex) {
                System.out.println("errore nella chiusura dei socket nel posizionamento");
            }
        }
    }
}
