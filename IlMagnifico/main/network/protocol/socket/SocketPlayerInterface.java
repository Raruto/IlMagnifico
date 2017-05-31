package main.network.protocol.socket;

import java.util.List;

import main.network.exceptions.CreateRoomException;
import main.network.exceptions.JoinRoomException;
import main.network.exceptions.LoginException;

/**
 * This interface is used as callback for communication from the socket protocol to the server.
 */
public interface SocketPlayerInterface {

    /**
     * Try to login the player into server with the given nickname.
     * @param nickname to use.
     * @throws LoginException if another player with the same nickname is already logged.
     */
    void loginPlayer(String nickname) throws LoginException;

    /**
     * Try to join an existing room.
     * @throws JoinRoomException if no available room is found.
     */
    void joinRoom() throws JoinRoomException;

    /**
     * Dispatch a chat message to the receiver or to all players.
     * @param receiver nickname of the receiver, null if message is public.
     * @param message body of the message.
     */
    void sendChatMessage(String receiver, String message);
}