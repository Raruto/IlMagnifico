package com.protocol.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.client.IClient;

/**
 * This class is used to define the Socket protocol for communicating with server trough Sockets.
 * The methods here must communicate with {@link ServerProtocol}.
 */
public class ClientProtocol {

    /**
     * Generic debug tag.
     */
    private static final String DEBUG_PROTOCOL_EXCEPTION = "Exception while handling server message";

    /**
     * Input stream used to read server responses.
     */
    private final ObjectInputStream mInput;

    /**
     * Output stream used to send messages to server.
     */
    private final ObjectOutputStream mOutput;

    /**
     * Interface used as callback to communicate with the client.
     */
    //private final IClient mCallback;

    /**
     * Object used as mutex to ensure that two threads never send a message over output stream concurrently.
     * (Only one thread can write on output stream).
     */
    private static final Object OUTPUT_MUTEX = new Object();

    /**
     * Map of all defined server responses headers.
     */
    private final HashMap<Object, ResponseHandler> mResponseMap;

    /**
     * Base constructor.
     * @param input stream from the socket.
     * @param output stream from the socket.
     * @param callback used to communicate with the client.
     */
    public ClientProtocol(ObjectInputStream input, ObjectOutputStream output/*, IClient callback*/) {
        mInput = input;
        mOutput = output;
        //mCallback = callback;
        mResponseMap = new HashMap<>();
        //loadResponses();
    }

    /**
     * Handle the server response and execute the defined method.
     * @param object response header from server.
     */
    public void handleResponse(Object object) {
        ResponseHandler handler = mResponseMap.get(object);
        if (handler != null) {
            handler.handle();
        }
    }

    /**
     * This interface is used like {@link Runnable} interface.
     */
    @FunctionalInterface
    private interface ResponseHandler {

        /**
         * Handle the server response.
         */
        void handle();
    }
}