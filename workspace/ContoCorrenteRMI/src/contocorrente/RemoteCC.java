package contocorrente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCC extends Remote {
	String testConnection() throws RemoteException;

	public double getSaldo() throws RemoteException;
}