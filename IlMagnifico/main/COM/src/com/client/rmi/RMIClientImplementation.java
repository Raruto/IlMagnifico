package com.client.rmi;

import java.rmi.RemoteException;

public class RMIClientImplementation implements RMIClientInterface {
	private String playerName;

	@Override
	public void notify(String object) throws RemoteException {
		//System.out.println("Ho ricevuto il messaggio: " + object);
		System.out.println(object);
	}

	@Override
	public String getPlayerName() throws RemoteException {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) throws RemoteException {
		this.playerName = playerName;
	}

}
