package network.protocol.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to define the Socket protocol for communicating with clients trough Sockets.
 * The methods here must communicate with {@link ClientProtocol}.
 */
public class ServerProtocol {

    /**
     * Generic debug tag.
     */
    private static final String DEBUG_PROTOCOL_EXCEPTION = "Exception while handling client request";

    /**
     * Input stream used to read client requests.
     */
    private final ObjectInputStream mInput;

    /**
     * Output stream used to send messages to client.
     */
    private final ObjectOutputStream mOutput;

    /**
     * Interface used as callback to communicate with the server player.
     */
    private final ServerSocketProtocolInt mCallback;

    /**
     * Object used as mutex to ensure that two threads never send a message over output stream concurrently.
     * (Only one thread can write on output stream).
     */
    private static final Object OUTPUT_MUTEX = new Object();

    /**
     * Map of all defined client requests headers.
     */
    private final HashMap<Object, RequestHandler> mRequestMap;

    /**
     * Base constructor.
     * @param input stream from the socket.
     * @param output stream from the socket.
     * @param callback used to communicate with the server.
     */
    public ServerProtocol(ObjectInputStream input, ObjectOutputStream output, ServerSocketProtocolInt callback) {
        mInput = input;
        mOutput = output;
        mCallback = callback;
        mRequestMap = new HashMap<>();
        //loadRequests();
    }


    /**
     * Handle the client request and execute the defined method.
     * @param object request header from client.
     */
    public void handleClientRequest(Object object) {
        RequestHandler handler = mRequestMap.get(object);
        if (handler != null) {
            synchronized (OUTPUT_MUTEX) {
                handler.handle();
            }
        }
    }

    /**
     * This interface is used like {@link Runnable} interface.
     */
    @FunctionalInterface
    private interface RequestHandler {

        /**
         * Handle the client request.
         */
        void handle();
    }
}