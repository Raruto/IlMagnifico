package LorenzoIlMagnifico.old;

import LorenzoIlMagnifico.old.rmi.client.Client;
import LorenzoIlMagnifico.old.rmi.server.Server;

public class Program {
public static void main(String[] args) {
	//Starts the RMIServer
	Server.main(args);
	
	//Starts the RMIClient	
	Client.main(args);
}
}
