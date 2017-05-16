package com.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.client.rmi.ClientInterface;

/*
 * IMPORTANTE:
 * L'interfaccia che definisce la classe da esportare deve:
 * - Estendere l'interfaccia Remote
 * - Tutti i metodi devono essere pubblici e lanciare l'eccezione RemoteException
 */
public interface RMIServerInterface extends Remote {	
	
	public void addClient(ClientInterface client) throws RemoteException;
	
	public void send(String message) throws RemoteException;

}
