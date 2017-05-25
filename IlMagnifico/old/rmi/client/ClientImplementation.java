package old.rmi.client;

import java.rmi.RemoteException;

public class ClientImplementation implements ClientInterface {

	@Override
	public void notify(String object) throws RemoteException {
		System.out.println("Ho ricevuto il messaggio: " + object);
	}

}
