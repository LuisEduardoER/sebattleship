/*
	Program: Multithreaded TCP server

	TCP server socket:
	1.	Open a server socket.
	2.	Accept a client connection. 
	3.	Open an input stream and output stream to the client socket. 
	4.	Read from and write to the client stream according to the server's protocol. 
	5.	Close the streams. 
	6.	Close the socket. 

	Note 1: 127.0.0.0 is a class A network, but is reserved for use as a loopback address (typically 127.0.0.1).
	Note 2: The 0.0.0.0 network is reserved for use as the default route.
 */
import java.net.*;
import java.io.*;

/**
 * 
 * @author SeungJin Lim
 *
 * Creates a server that listens on a specified port
 *   for clients to connect.
 * Establishes a socket with the client when one
 *   does attempt a connection.
 * Creates an unlimited number of client sockets.
 * 
 */
public class Server {
	private static String serverName;
	private static String clientName;
	
    public static void main(int port, String name) throws IOException {

    	serverName = name;
    	
        ServerSocket serverSocket = null;
    	DataInputStream in = null;
    	DataOutputStream out = null;
    	
		/**
		 * 1. Open a server socket.
		 */
        try {
			serverSocket = new ServerSocket( port );

			System.out.println( "Game created...  Waiting for a player to join." );

			/**
			 * 2. Accept a client connection.
			 */
    		while( true ) {
    	        Socket clientSocket = null;
        	    try {
        	    	// stalls on this line until a client tries to connect
            	    clientSocket = serverSocket.accept();
    
        			in = new DataInputStream( clientSocket.getInputStream() );
        			out = new DataOutputStream( clientSocket.getOutputStream() );
        			
        			// Exchange client and server names
        			out.writeUTF(serverName);
        			clientName=in.readUTF();
        			
        			// To be displayed upon creation of socket with client
        	        System.out.println(	clientName + " has joined your game.");
        	        
    				@SuppressWarnings("unused")
    				// Connection creates new thread and stalls in the run
    				//   method until termination of client connection
    				//   This threading allows multiple connecions
    				Connection c = new Connection( clientSocket );
    	        } catch (IOException e) {
        	        System.err.println( "Accept failed. ");
            	    System.exit(1);
    	        } // try_catch
    		} // while

        } catch( IOException e ) {
            System.err.println( "Could not listen on local port: "+port );
            System.exit(1);
        } // try_catch
    } // main
}

/**
 * @class Connection
 * 
 * Establishes a threaded I/O connection to the client.
 * This connection continually listens for text, receives it, and relays it back
 * Useful for chatting.
 */
class Connection extends Thread {
	
	DataInputStream in = null;
	DataOutputStream out = null;
	Socket clientSocket = null;

	public Connection( Socket client ) {
		/**
		 * 3. Open input and output streams from the client socket.
		 */
		try {
			clientSocket = client;
			in = new DataInputStream( clientSocket.getInputStream() );
			out = new DataOutputStream( clientSocket.getOutputStream() );

			System.out.println( "[Server Thread "+this.getId()+"] Input/OutputStream is ready for " +
								clientSocket.getInetAddress().getHostAddress() +
								" ..." );

			// Starts the thread which calls the run function
			this.start();

		} catch( IOException e ) {
			e.printStackTrace();
		} // try_catch
	} // Connection

	public void run() {
		/**
		 * 4. Read from and write to the client stream according to the server's protocol.
		 */
		try {
			String data;
			// As long as the client has not hit enter without typing anyting and has not typed "bye"
			while( (data = in.readUTF()) != null && (!data.equalsIgnoreCase("bye")) ) {

				System.out.println( "[Server Thread " + this.getId() + "] talks back to Client (" +
							clientSocket.getInetAddress().getHostAddress() +
							"): " + data );
				
				out.writeUTF( data );
			} // while
        } catch (EOFException e) {
			//System.out.println( "EOF: "+e.getMessage() );
        } catch (IOException e) {
			//System.out.println( "IO: "+e.getMessage() );
        } // try_catch

		System.out.println( "[Server Thread " + this.getId() + "] Client (" +
							clientSocket.getInetAddress().getHostAddress() +
							") has left..." );
	} // run
}
