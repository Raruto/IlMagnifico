package com.server.rmi;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import com.client.rmi.ClientInterface;

public class RMIServerImplementation extends UnicastRemoteObject implements RMIServerInterface {

	private static final long serialVersionUID = -7098548671967083832L;
	private static final String SERVER_ID = "[SERVER]";

	private ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();

	protected RMIServerImplementation() throws RemoteException {
		super(0);
	}

	@Override
	public void addClient(ClientInterface client) throws RemoteException {
		send(client.getPlayerName() + " has joined.");
		clients.add(client);
	}

	@Override
	public void send(String message) throws RemoteException {
		Iterator<ClientInterface> clientIterator = clients.iterator();
		while (clientIterator.hasNext()) {
			try {
				clientIterator.next().notify(SERVER_ID + " " + message);
			} catch (ConnectException e) {
				clientIterator.remove();
				System.out.println("Client rimosso!");
			}
		}
	}

}
