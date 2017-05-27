package main.LorenzoIlMagnifico.old.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
	
	public void notify(String object) throws RemoteException;
	
}
