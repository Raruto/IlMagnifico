package com.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.client.rmi.ClientInterface;

/**
 * Remote interface for RemoteMethodInvocation from client to server.
 */
public interface RMIServerInterface extends Remote {

	public void addClient(ClientInterface client) throws RemoteException;

	public void send(String message) throws RemoteException;

}
