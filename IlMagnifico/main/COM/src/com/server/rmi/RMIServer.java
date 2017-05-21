package com.server.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.client.rmi.RMIClient;
import com.server.AbstractServer;
import com.server.Server;

public class RMIServer extends AbstractServer {
	private RMIServerImplementation serverImplementation;

	public RMIServer(Server server) {
		super(server);
		try {
			serverImplementation = new RMIServerImplementation();
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}
	}

	public void startServer(int rmiPort) {
		initializeServerRegistry(rmiPort);
		publishRemoteServerObject(serverImplementation);

		// TODO: Aggiungere la possibilità  di terminare la connessione da parte
		// del client inviando il messaggio "STOP".
	}

	private void initializeServerRegistry(int rmiPort) {
		try {
			LocateRegistry.createRegistry(rmiPort);
		} catch (RemoteException e) {
			System.err.println("Registry già  presente!");
		}
	}

	private void publishRemoteServerObject(RMIServerImplementation serverImplementation) {
		try {
			Naming.rebind("Server", serverImplementation);
			System.out.println("[RMI Server] OK");
		} catch (MalformedURLException e) {
			System.err.println("Impossibile registrare l'oggetto indicato!");
		} catch (RemoteException e) {
			System.err.println("Errore di connessione: " + e.getMessage() + "!");
		}
	}
}