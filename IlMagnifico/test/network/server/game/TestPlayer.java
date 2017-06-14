package test.network.server.game;

import java.rmi.RemoteException;

import main.network.NetworkException;
import main.network.server.game.RemotePlayer;
import main.network.server.game.UpdateStats;

public class TestPlayer extends RemotePlayer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5322904858021957193L;

	@Override
	public void onChatMessage(String author, String message) throws NetworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGameUpdate(UpdateStats update) throws NetworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(Object object) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
