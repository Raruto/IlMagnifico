package main.rmi.server;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import main.rmi.client.ClientInterface;

public class ServerImplementation extends UnicastRemoteObject implements
		ServerInterface {
	
	private ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();

	protected ServerImplementation() throws RemoteException {
		super(0);		
	}

	private static final long serialVersionUID = -7098548671967083832L;

	@Override
	public void addClient(ClientInterface client) throws RemoteException {
		clients.add(client);		
	}

	@Override
	public void send(String message) throws RemoteException {
		Iterator<ClientInterface> clientIterator = clients.iterator();
		while(clientIterator.hasNext()){
			try{
				clientIterator.next().notify(message);
			}catch(ConnectException e) {
				clientIterator.remove();	
				System.out.println("Client rimosso!");
			}			
		}	
	}

}
