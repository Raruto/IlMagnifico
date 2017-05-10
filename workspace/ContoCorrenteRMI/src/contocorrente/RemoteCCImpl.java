package contocorrente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCCImpl extends UnicastRemoteObject implements RemoteCC {
	private static final long serialVersionUID = 1L;

	public RemoteCCImpl() throws RemoteException {
	}

	@Override
	public String testConnection() throws RemoteException {
		return "connection is UP";
	}

	@Override
	public double getSaldo() throws RemoteException {
		return 0.0;
	}

}
