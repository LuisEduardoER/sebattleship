package Networking;

import java.net.*;
import java.io.*;

/**
 *
 * Creates a server that listens on a specified port
 *   for clients to connect.
 * Establishes a socket with the client when one
 *   does attempt a connection.
 * Creates an unlimited number of client sockets.
 * 
 * @author Josh
 * 
 */
public class Server{
	

	public Socket clientSocket=null;
	public DataOutputStream out = null;
	public DataInputStream in = null;
	private int port = 0;
	private ServerSocket serverSocket = null;
    
	
	/**
	 * Server Constructor.
	 * Establishes a socket and waits for a connection.
	 * 
	 * @param port Port of the local computer to listen on for clients.
	 * @param serverName Name of the server as to be displayed.
	 */
	public Server(int port){

    	this.port=port;
    	
        try {
			serverSocket = new ServerSocket( this.port );
        } catch( IOException e ) {
            System.err.println( "Could not listen on local port: " + port );
            System.exit(1);
        } // try_catch    	       
	}
	
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
	}
	
	public void Send(String msg) throws IOException{
		out.writeUTF(msg);
	}
	
	
    public String Listen(){
		String data=null;
 //   	try {
//			while( (data=in.readUTF()) != null );
 //       } catch (IOException e) {
//			System.out.println( "IO: "+e.getMessage() );
 //       }
//		return data;
    	try{
        	data = in.readUTF();  		
        } catch (IOException e) {
			System.out.println( "IO: "+e.getMessage() );
        }
        return data;
    } // main
}
