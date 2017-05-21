package com.server.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.server.AbstractServer;
import com.server.Server;

public class RMIServer extends AbstractServer {
	private RMIServerImplementation serverImplementation;
	private int RMI_PORT;

	public RMIServer() {
		try {
			serverImplementation = new RMIServerImplementation();
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}
	}

	public void startServer(int rmiPort) {
		RMI_PORT = rmiPort;

		initializeRegistry();
		publishRemoteServerObject();

		// TODO: Aggiungere la possibilità  di terminare la connessione da parte
		// del client inviando il messaggio "STOP".
	}

	private void initializeRegistry() {
		try {
			// Creo un registry sulla porta "rmiPort".
			LocateRegistry.createRegistry(RMI_PORT);

		} catch (RemoteException e) {
			System.out.println("Registry già  presente!");
		}
	}

	private void publishRemoteServerObject() {
		try {
			// Aggiungo al registry l'associazione dell'oggetto
			// serverImplementation con "//localhost/Server".
			Naming.rebind("Server", serverImplementation);

			System.out.println("[RMI Server] OK");

		} catch (MalformedURLException e) {
			System.err.println("Impossibile registrare l'oggetto indicato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}
	}
}