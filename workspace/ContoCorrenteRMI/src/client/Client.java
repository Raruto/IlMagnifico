package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import contocorrente.RemoteCC;

public class Client {
	public static final String serverAddress = "localhost";
	public static final String remoteObjectName = "server.RemoteInterface";

	public static void main(String[] args) {
		ClientCom client = new ClientCom(remoteObjectName, serverAddress);

		try {

			client.connect();
			client.run();

		} catch (RemoteException | NotBoundException e) {
			System.out.println("(CLIENT) Connection failure, exiting...");
			System.exit(-1);
		}
	}
}

class ClientCom {
	public final String remoteObjectName;
	public final String serverAddress;
	public Registry registry;
	public RemoteCC conto;

	public ClientCom(String remoteObjectName, String serverAddress) {
		this.remoteObjectName = remoteObjectName;
		this.serverAddress = serverAddress;
		this.conto = null;
	}

	public void connect() throws RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry(serverAddress);
		RemoteCC conto = (RemoteCC) registry.lookup(remoteObjectName);

		System.out.println("SERVER SAYS: \"" + conto.testConnection() + "\"");
	}

	public void run() throws RemoteException {
		System.out.println("SERVER SAYS: \"" + conto.getSaldo() + "\"");
	}
}
