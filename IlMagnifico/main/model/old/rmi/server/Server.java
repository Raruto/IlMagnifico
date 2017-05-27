package main.model.old.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import main.model.old.rmi.server.ServerImplementation;

public class Server {
	
	public static void main(String[] args) {
		try {
			
			// Creo un registry sulla porta 1099 (quella di default).
			LocateRegistry.createRegistry(1099);
		
		} catch (RemoteException e) {
			System.out.println("Registry già  presente!");			
		}		
		
		try {
			
			// Creo l'oggetto da esportare normalmente 
			// (in quanto la classe ServerImplementation estende UnicastRemoteObject)
			ServerImplementation serverImplementation = new ServerImplementation();
			
			// Aggiungo al registry l'associazione dell'oggetto serverImplementation
			// con "//localhost/Server".
			Naming.rebind("Server", serverImplementation);
			
		} catch (MalformedURLException e) {
			System.err.println("Impossibile registrare l'oggetto indicato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}		
		
		//TODO: Aggiungere la possibilità  di terminare la connessione da parte 
		//      del client inviando il messaggio "STOP".		
	}
}
