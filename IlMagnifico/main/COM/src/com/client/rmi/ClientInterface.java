package com.client.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	
	public void notify(String object) throws RemoteException;
	
	public String getPlayerName() throws RemoteException;

	public void setPlayerName(String name) throws RemoteException;
	
}
