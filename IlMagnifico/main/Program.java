package main;

import main.rmi.client.Client;
import main.rmi.server.Server;

public class Program {
public static void main(String[] args) {
	//Starts the RMIServer
	Server.main(args);
	
	//Starts the RMIClient	
	Client.main(args);
}
}
