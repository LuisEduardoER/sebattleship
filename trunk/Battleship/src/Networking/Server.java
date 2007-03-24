package Networking;

import java.net.*;
import java.io.*;

/**
 * Creates a server that listens on a specified port
 *   for clients to connect.
 * Establishes a socket with the client when one
 *   does attempt a connection.
 * Creates an unlimited number of client sockets.
 * 
 * @author Josh
 */
public class Server{
	

	
	/**
	 * Server socket created on instantiation
	 */
	private ServerSocket serverSocket = null;
	
	/**
	 * Client socket created on connection
	 */
	public Socket clientSocket=null;
	
	/**
	 * Output data stream to the client
	 */
	public DataOutputStream out = null;
	
	/**
	 * Input data stream to the client
	 */
	public DataInputStream in = null;
	
	/**
	 * Port to use for socket communication on this server
	 */
	private int port = 0;
    
	
	/**
	 * Server Constructor.
	 * Establishes a socket.
	 * 
	 * @param port Port of the local computer to listen on for clients.
	 */
	public Server(int port){
    	this.port=port;
    	
    // Create socket for the server
        try {
			serverSocket = new ServerSocket( this.port );
        } catch( IOException e ) {
            System.err.println( "Could not listen on local port: " + port );
            System.exit(1);
        } // try_catch    	       
	} // end Constructor
	
	/**
	 *  Blocks while waiting for a client to connect. Establishes
	 *   input and ouput data streams to the client
	 */
	public void Connect(){
		try {
	    		// stalls on this line until a client connects
		    clientSocket = serverSocket.accept();
		    	// establish the in/out communication streams
	 	    out = new DataOutputStream(clientSocket.getOutputStream());
	 	    in  = new DataInputStream(clientSocket.getInputStream());
	    } catch (IOException e) {
	        System.err.println( "Accept failed. ");
		    System.exit(1);
	    } // try_catch
	} // end Connect
	
	/**
	 * Sends a String message to the connected client.
	 * 
	 * @param msg	string to send to client
	 * @throws IOException	If an error occurs while trying to 
	 * 						send message.  Allows user to determine
	 * 						how to handle the error.
	 */
	public void Send(String msg) throws IOException{
		out.writeUTF(msg);
	} // end Send
	
	
    /**
     * Makes a single attempt to read a UTF string from the client.
     * 
     * @return	data	Contains the result of an attempted
     * 					read from the client.  Contains 'null'
     * 					if there was nothing to read.
     */
    public String Listen(){
		String data=null;
    	try{
        	data = in.readUTF();  		
        } catch (IOException e) {
			System.out.println( "IO: "+e.getMessage() );
        }
        return data;
    } // end Listen
}
