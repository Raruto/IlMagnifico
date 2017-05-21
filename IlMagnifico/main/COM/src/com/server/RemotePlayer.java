package com.server;

import com.client.rmi.RMIClientInterface;

public /* abstract */ class RemotePlayer {
	private RMIClientInterface client;

	public RemotePlayer(RMIClientInterface client) {
		this.client = client;
	}

	public RMIClientInterface getClientInterface() {
		return this.client;
	}

	public void setNickname(String nickname) {
		// TODO Auto-generated method stub
		
	}

}
