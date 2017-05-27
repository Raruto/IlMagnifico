package main.LorenzoIlMagnifico.old;

import main.LorenzoIlMagnifico.old.rmi.client.Client;
import main.LorenzoIlMagnifico.old.rmi.server.Server;

public class Program {
public static void main(String[] args) {
	//Starts the RMIServer
	Server.main(args);
	
	//Starts the RMIClient	
	Client.main(args);
}
}
