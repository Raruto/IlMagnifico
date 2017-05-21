package com.server.rmi;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Iterator;

import com.client.rmi.RMIClient;
import com.client.rmi.RMIClientInterface;
import com.server.AbstractServer;
import com.server.RemotePlayer;
import com.server.Server;

/**
 * This class is built on top of {@link AbstractServer} and let Server to
 * communicate whit RMIClients.
 */
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