/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battaglianavale;

/**
 *
 * @author Carlo Castelnuovo
 */
public class BattagliaNavale {

    private static BattagliaNavaleServer Server;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//fa partire direttamente il server
        // TODO code application logic here
        Server=new BattagliaNavaleServer();
            try {
                Server.Server();
            } catch (Exception male) {
                System.out.println("errore nell'esecuzione del server");
            }
        
    }

}
