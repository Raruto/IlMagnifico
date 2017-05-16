package com.client;

import com.client.rmi.ClientRMI;

public class Client {

	public static void main(String[] args) {
		ClientRMI rmi = new ClientRMI();
		rmi.startClient();
	}

}
