package com.server.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.server.Server;

public class RMIServer {

	public RMIServer(Server server) {
		// TODO Auto-generated constructor stub
	}

	public void startServer(int rmiPort) {
		
		try {
			
			// Creo un registry sulla porta "rmiPort".
			LocateRegistry.createRegistry(rmiPort);
			
		} catch (RemoteException e) {
			System.out.println("Registry già  presente!");
		}

		try {

			// Creo l'oggetto da esportare normalmente
			// (in quanto la classe ServerImplementation estende
			// UnicastRemoteObject)
			RMIServerImplementation serverImplementation = new RMIServerImplementation();

			// Aggiungo al registry l'associazione dell'oggetto
			// serverImplementation
			// con "//localhost/Server".
			Naming.rebind("Server", serverImplementation);
			
			System.out.println("[RMI Server] OK");

		} catch (MalformedURLException e) {
			System.err.println("Impossibile registrare l'oggetto indicato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}

		// TODO: Aggiungere la possibilità  di terminare la connessione da parte
		// del client inviando il messaggio "STOP".
	}

}