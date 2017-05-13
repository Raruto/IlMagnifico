package main.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
	
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099); //Creo un registy sulla porta 1099 (quella di default).
		} catch (RemoteException e) {
			System.out.println("Registry già  presente!");			
		}		
		
		try {
			ServerImplementation serverImplementation = new ServerImplementation();//Creo l'oggetto da esportare normalmente (in quanto la classe ServerImplementation estende UnicastRemoteObject)			
			Naming.rebind("Server", serverImplementation);//Aggiungo al registry l'associazione dell'oggetto serverImplementation con "//localhost/Server".																	  
		} catch (MalformedURLException e) {
			System.err.println("Impossibile registrare l'oggetto indicato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}		
		//TODO: Aggiungere la possibilità  di terminare la connessione da parte del client inviando il messaggio "STOP".		
	}

}
