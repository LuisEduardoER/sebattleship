package Networking;

import java.io.*;
import java.net.*;

/**
 *	A TCP/IP client connection.
 *  Supports sending and receiving of Strings.
 *
 *  @author Josh
 */
public class Client {



	/**
	 * Contains the IP address of the server.
	 */
	private String serverIP = null;
	
	/**
	 * Contains the port number of the server.
	 */
	private int serverPort = 0;
	
    /**
     * Output data stream to the server
     */
    public DataOutputStream out = null;
    
    /**
     * Input data stream to the server
     */
    public DataInputStream in = null;
    
    /**
     * Socket connected to the server
     */
    public Socket clientSocket = null;
   	
   	
	/**
	 * Client Constructor.
	 * Blocks while creating a socket to the server.
	 * Creates input and output data streams with the server.
	 * Handles exceptions if socket creation fails.
	 * 
	 * @param serverIP	IP address of the server to connect to.
	 * @param serverPort	Port number to use when connecting to server.
	 */
	public boolean Connect(String serverIP, int serverPort){
    	this.serverIP=serverIP;
    	this.serverPort=serverPort;
	 
        try {
			clientSocket = new Socket( this.serverIP, this.serverPort );
			out = new DataOutputStream( clientSocket.getOutputStream() );
			in = new DataInputStream(clientSocket.getInputStream());
	    } catch( UnknownHostException e ) {
	        return false;
	    } catch( IOException e ) {
	        return false;
	    } // try_catch
	    return true;
    } // end Constructor
	
	/**
	 * Sends a single String message to the server.
	 * 
	 * @param msg	Message to send to server
	 * @throws IOException	If there is a failure while trying
	 * 						to send the message.
	 */
	public void Send(String msg) throws IOException{
		out.writeUTF(msg);
	} // end Send
	
	
    /**
     * Attempts a single read of UTF from the server.
     * Handles exceptions if read fails.
     * 
     * @return	String data	contains the result of a read operation.
     * 			Contains 'null' if the read failed.
     */
    public String Listen() {
    	String data = null;
    	try{
        	data = in.readUTF();  		
        } catch (IOException e) {
			System.err.println( "IO: "+e.getMessage() );
        }
        return data;
    } // end Listen
}
