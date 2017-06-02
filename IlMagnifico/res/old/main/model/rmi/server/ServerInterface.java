package res.old.main.model.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import res.old.main.model.rmi.client.ClientInterface;

/*
 * IMPORTANTE:
 * L'interfaccia che definisce la classe da esportare deve:
 * - Estendere l'interfaccia Remote
 * - Tutti i metodi devono essere pubblici e lanciare l'eccezione RemoteException
 */
public interface ServerInterface extends Remote {	
	
	public void addClient(ClientInterface client) throws RemoteException;
	
	public void send(String message) throws RemoteException;

}
