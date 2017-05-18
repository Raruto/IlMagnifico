package com.client.rmi;

import java.rmi.RemoteException;

public class ClientImplementation implements ClientInterface {
	private String name="NOMEGIOCATORE";

	@Override
	public void notify(String object) throws RemoteException {
		System.out.println("Ho ricevuto il messaggio: " + object);
	}
	
	@Override
	public String getPlayerName() throws RemoteException{
		return name;
	}

}
