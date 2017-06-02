package res.old.main.model;

import res.old.main.model.rmi.client.Client;
import res.old.main.model.rmi.server.Server;

public class Program {
public static void main(String[] args) {
	//Starts the RMIServer
	Server.main(args);
	
	//Starts the RMIClient	
	Client.main(args);
}
}
