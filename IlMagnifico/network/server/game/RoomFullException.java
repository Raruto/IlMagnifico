package network.server.game;

/**
 * This exception is thrown when a player is trying to join in a full or closed room.
 * This exception is used only by the server side of the game.
 */
public class RoomFullException extends Exception {

    /**
     * Base constructor.
     */
    public RoomFullException() {
        super();
    }

    /**
     * Base constructor.
     * @param message of the error.
     */
    public RoomFullException(String message) {
        super(message);
    }
}