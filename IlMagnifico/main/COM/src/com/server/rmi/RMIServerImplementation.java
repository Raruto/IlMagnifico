package com.server.rmi;

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

import com.client.rmi.RMIClient;
import com.client.rmi.RMIClientInterface;
import com.exceptions.LoginException;
import com.server.RemotePlayer;

public class RMIServerImplementation extends UnicastRemoteObject implements RMIServerInterface {

	private static final long serialVersionUID = -7098548671967083832L;
	private static final String SERVER_ID = "[SERVER]";

	private ArrayList<RemotePlayer> players = new ArrayList<RemotePlayer>();
	//private ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();

	protected RMIServerImplementation() throws RemoteException {
		super(0);
	}

	@Override
	public void addClient(RMIClientInterface client) throws RemoteException {
		send(client.getPlayerName() + " has joined.");
		players.add(new RemotePlayer(client));
		//clients.add(client);
	}

	@Override
	public void send(String message) throws RemoteException {
		Iterator<RemotePlayer> itr = players.iterator();
		
		while (itr.hasNext()) {
			try {
				itr.next().getClientInterface().notify(SERVER_ID + " " + message);
			} catch (ConnectException e) {
				itr.remove();
				System.out.println("Client rimosso!");
			}
		}

		/*
		Iterator<ClientInterface> clientIterator = clients.iterator();
		while (clientIterator.hasNext()) {
			try {
				clientIterator.next().notify(SERVER_ID + " " + message);
			} catch (ConnectException e) {
				clientIterator.remove();
				System.out.println("Client rimosso!");
			}
		}
	*/
	}

	@Override
	public String loginPlayer(String nickname, RMIClientInterface player) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void joinFirstAvailableRoom(String sessionToken) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendChatMessage(String sessionToken, String receiver, String message) throws IOException {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public String loginPlayer(String nickname, RMIClient rmiClient) throws LoginException {
		// TODO Auto-generated method stub
		return null;
	}	
*/
}
