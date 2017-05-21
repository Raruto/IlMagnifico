package com.client.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.NetworkException;
import com.client.AbstractClient;
import com.client.ClientException;
import com.client.IClient;
import com.protocol.socket.ClientProtocol;

/**
 * This class is the implementation of {@link AbstractClient} class. It manages the network connection with sockets.
 */
public class SocketClient extends AbstractClient {

    /**
     * Socket endpoint of client.
     */
    private Socket socketStream;

    /**
     * Object input stream for receiving serialized objects from server socket.
     */
    private ObjectInputStream inputStream;

    /**
     * Object output stream for sending serialized objects to server socket.
     */
    private ObjectOutputStream outputStram;

    /**
     * Socket protocol used for communication between client and server.
     */
    private ClientProtocol socketProtocol;

    /**
     * Create a socket client instance.
     * @param controller client controller.
     * @param address of the server.
     * @param port of the server.
     */
    //public SocketClient(IClient controller, String address, int port) {
    //    super(controller, address, port);
    //}
    public SocketClient() {}

    /**
     * Open a connection with the server and initialize socket protocol.
     * @throws ClientConnectionException if server is not reachable or something went wrong.
     */
    @Override
    public void startClient() throws ClientException {
        try {
            //socketStream = new Socket(getAddress(), getPort());
        	socketStream = new Socket("localhost", 1001);
            outputStram = new ObjectOutputStream(new BufferedOutputStream(socketStream.getOutputStream()));
            outputStram.flush();
            inputStream = new ObjectInputStream(new BufferedInputStream(socketStream.getInputStream()));
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    /**
     * Initialize socket protocol.
     */
    @Override
    public void initializeConnection() {
        socketProtocol = new ClientProtocol(inputStream, outputStram/*, getController()*/);
    }

	@Override
	public void loginPlayer(String nickname) throws NetworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinFirstAvailableRoom() throws NetworkException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendChatMessage(String receiver, String message) throws NetworkException {
		// TODO Auto-generated method stub
		
	}

}