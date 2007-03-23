
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
 * @author SeungJin Lim
 * 
 */
public class Server{
	

	private Socket clientSocket=null;
	private DataOutputStream out = null;
	private int port;

	
	/**
	 * Server Constructor.
	 * Establishes a socket and waits fora connection
	 * 
	 * @param port Port of the local computer to listen on for clients.
	 * @param serverName Name of the server as to be displayed.
	 */
	public Server(int port){

    	this.port=port;
      	  
        ServerSocket serverSocket = null;

        try {
			serverSocket = new ServerSocket( this.port );

    		while( true ) {
        	    try {
        	    	// stalls on this line until a client connects
            	    clientSocket = serverSocket.accept();
            	    // establish the in/out communication streams
             	    out = new DataOutputStream(clientSocket.getOutputStream());
    	        } catch (IOException e) {
        	        System.err.println( "Accept failed. ");
            	    System.exit(1);
    	        } // try_catch
    		} // while

        } catch( IOException e ) {
            System.err.println( "Could not listen on local port: " + port );
            System.exit(1);
        } // try_catch    	       
	}
	
	
	public void Send(String msg) throws IOException{
		out.writeUTF(msg);
	}
	
	
    public String Listen(){
		Connection c = new Connection( clientSocket);
		return c.GetMsg();
    } // main
}
