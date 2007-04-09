package Networking;


import java.io.IOException;

import Utilities.Console;

/**
 * BattleshipServer extends Server.
 * Adds ability to store names for the server
 *   and client.
 *   
 * @author Josh
 *
 */
public class BattleshipServer extends Server{

	/**
	 * Name of the server as declared at instantiation
	 */
	private String serverName = null;
	
	/**
	 * Name of the client as received at connection
	 */
	private String clientName = null;
	

	
	/**
	 * BattleshipServer Constructor
	 * Creates server and blocks for connection.
	 * Sends server name and blocks to receive client name once
	 *   connection is established.
	 * 
	 * @param port	Port number to use for communication on this server
	 * @param name	Desired name to use as reference to this server
	 */
	public BattleshipServer(int port, String name, Console display) {
		super(port);
		serverName=name;
			// Wait for a connection
		display.putStaticLine(" ");
		display.putStaticLine("Waiting for a connection...");
		display.printScreen();
		super.Connect();
			// Send server name and block to receive client name
		try {
			this.Send(this.serverName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while((clientName = this.Listen()) == null);
		display.putStaticLine(" ");
		display.putStaticLine(clientName + " has joined your game!");
		display.putStaticLine("Pres Enter to continue... ");
		display.printScreen();
		display.readLine();
	}
	
	
	/**
	 * Gets the name of the client this server is
	 *   connected to.
	 *   
	 * @return	String containing the name of the client.
	 */
	public String GetClientName(){
		return clientName;
	}
	
	
	/**
	 * Gets the name of this server.
	 * 
	 * @return String containing the name of the server.
	 */
	public String GetServerName(){
		return serverName;
	}
}
