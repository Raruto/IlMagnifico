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
public class BattagliaNavaleServer {

    private final int PORT1 = 8235;
    private final int PORT2 = 8236;
    private ServerSocket socket1, socket2;

    public void Server() {
        Socket socketgiocatore1, socketgiocatore2;
        socketgiocatore1 = this.Connetti(socket1, PORT1);
        socketgiocatore2 = this.Connetti(socket2, PORT2);
        new Partita(socketgiocatore1, socketgiocatore2);
    }

    public Socket Connetti(ServerSocket socket, int porta) {
        try {
            socket = new ServerSocket(porta);
            System.out.println("attesa del giocatore");
            while (true) {
                Socket socketprimo = socket.accept();
                return socketprimo;
            }
        } catch (Exception e) {
            System.out.println("errore nella connessione");
            try {
                this.socket1.close();
            } catch (Exception ex) {
            }
        }
        Socket socketfalse = null;
        return socketfalse;
    }

}
