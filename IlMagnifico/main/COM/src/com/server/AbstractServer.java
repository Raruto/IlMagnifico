package com.server;

/**
 * This class is the abstraction of the module for server communication.
 */
public abstract class AbstractServer {

	/**
	 * Interface used to communicate directly with the server.
	 */
	//private final IServer controller;

	/**
	 * Abstract constructor.
	 * 
	 * @param controller
	 *            server interface.
	 */
	// public AbstractServer(IServer controller) {
	// this.controller = controller;
	// }

	/**
	 * Get the server interface passed during instantiation.
	 * 
	 * @return the server interface.
	 */
	// protected IServer getController() {
	// return this.controller;
	// }

	/**
	 * Abstract method to start the connection of this module.
	 * 
	 * @param port
	 *            number of the port to use.
	 * @throws ServerException
	 *             if some error occurs.
	 */
	public abstract void startServer(int port) throws ServerException;
}