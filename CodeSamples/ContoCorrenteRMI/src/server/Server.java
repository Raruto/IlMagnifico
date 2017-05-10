package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import contocorrente.RemoteCC;
import contocorrente.RemoteCCImpl;

public class Server {
	public static final int PORT = 1099;
	public static final String remoteObjectName = "server.RemoteInterface";

	public static void main(String[] args) {
		
		ContoCorrente cc = new ContoCorrente(1000);
		
		ServerCom server = new ServerCom(PORT, remoteObjectName);

		try {
			
			server.load();
			server.run();

		} catch (RemoteException | MalformedURLException e) {
			System.out.println("(SERVER) Boot failure, exiting...");
			System.exit(-1);
		}
	}
}

class ServerCom {
	public final int PORT;
	public final String remoteObjectName;
	public RemoteCC conto;

	public ServerCom(int port, String remoteObjectName) {
		this.PORT = port;
		this.remoteObjectName = remoteObjectName;
		this.conto=null;
	}

	public void load() throws RemoteException, MalformedURLException {
		LocateRegistry.createRegistry(PORT);
		conto = new RemoteCCImpl();
		Naming.rebind(remoteObjectName, conto);

		System.out.println("(SERVER) Server loaded");
	}
	public void run(){
		
	}
}
