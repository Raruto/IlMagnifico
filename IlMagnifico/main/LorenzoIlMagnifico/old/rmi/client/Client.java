package main.LorenzoIlMagnifico.old.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import main.LorenzoIlMagnifico.old.rmi.client.ClientImplementation;
import main.LorenzoIlMagnifico.old.rmi.client.ClientInterface;
import main.LorenzoIlMagnifico.old.rmi.server.ServerInterface;

public class Client {

	public static void main(String[] args) {
		ServerInterface server;
		try {
			
			// Ottengo il riferimento remoto associato alla stringa passata 
			// (contiene l'host target e l'identificativo dell'oggetto sull'host).
			server = (ServerInterface)Naming.lookup("//localhost/Server");		
			
			// Creo l'oggetto client normalmente.
			ClientImplementation client = new ClientImplementation();
			
			// Tuttavia, dato che ClientImplementation non estende la classe 
			// UnicastRemoteObject, devo creare un riferimento remoto
			// all'oggetto col metodo UnicastRemoteObject.exportObject che 
			// prende come parametri l'oggetto da esportare e la porta da
			// utilizzare per la connessione. Con 0 la porta viene scelta 
			// automaticamente.
			// Altrimenti avrebbe tentato di serializzare l'oggetto e di 
			// passarlo come copia al server.
			// In questo caso non devo associare un identificativo 
			// all'oggetto (in quanto il riferimento remoto verrà  passato 
			// al server).			
			ClientInterface remoteRef = (ClientInterface) UnicastRemoteObject.exportObject(client, 0);	 

			server.addClient(remoteRef);
			Scanner scanner = new Scanner(System.in);
			boolean active = true;
			while(active){
				System.out.println("Inserire un messaggio:");
				String message = scanner.nextLine();
				server.send(message);
			}		
			scanner.close();
			
		} catch (MalformedURLException e) {
			System.err.println("URL non trovato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		} catch (NotBoundException e) {
			System.err.println("Il riferimento passato non è associato a nulla!");
		}
		
		
	}

}
