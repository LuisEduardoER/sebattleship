package Networking;


import java.io.IOException;

import Utilities.Console;

/**
 * BattleshipClient extends Client.
 * Adds ability to store names for the server
 *   and client.
 *   
 * @author Josh
 *
 */
public class BattleshipClient extends Client {

	/**
	 * Name of the server as declared at instantiation
	 */
	private String serverName = null;
	
	/**
	 * Name of the client as received at connection
	 */
	private String clientName = null;
	
	private String serverIP = null;
	
	private int serverPort = 7777;
	

	/**
	 * BattleshipClient Constructor
	 * Creates a connection to the designated server.
	 * Sends client name and blocks to receive server name once
	 *  connection is established.
	 * 
	 * @param serverIP	IP address of the server to connect to
	 * @param serverPort	Port number of the server to use for communication
	 * @param clientName	Desired name to use as reference to this client
	 */
	public BattleshipClient(String serverIP, int serverPort, String clientName){
		this.clientName=clientName;
		this.serverPort=serverPort;
		this.clientName=clientName;
	}
	
	
	public boolean Connect(Console display){
		if(!super.Connect(serverIP, serverPort))
			return false;
		try {
			this.Send(this.clientName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while((serverName = this.Listen()) == null);
		display.putStaticLine(" ");
		display.putStaticLine("You have joined " + serverName + "'s game!");
		display.putStaticLine("Press Enter to continue... ");
		display.printScreen();
		display.readLine();
		return true;
	}
	
	
	/**
	 * Gets the name of this client.
	 *   
	 * @return	String containing the name of this client.
	 */	
	public String GetClientName(){
		return clientName;
	}
	
	/**
	 * Gets the name of the server this client is connected to.
	 * 
	 * @return String containing the name of the server this client is connected to.
	 */	
	public String GetServerName(){
		return serverName;
	}
	
	
}