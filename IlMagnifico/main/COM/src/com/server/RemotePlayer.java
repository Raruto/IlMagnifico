package com.server;

import com.client.rmi.ClientInterface;

public /* abstract */ class RemotePlayer {
	private ClientInterface client;

	public RemotePlayer(ClientInterface client) {
		this.client = client;
	}

	public ClientInterface getClientInterface() {
		return this.client;
	}

}
