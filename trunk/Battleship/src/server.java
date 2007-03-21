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

public class server {
    public static void main(String[] args) throws IOException {

    	/**
    	 * Check the program parameter(s).
    	 */
		if( args.length < 1 ) {
			System.err.println( "Usage: java server <port>" );
			System.exit(1);
		} // if

        ServerSocket serverSocket = null;
        String localHostAddress = null;
        int localPortNumber;
        String clientHostAddress = null;
        int clientPortNumber;
        
		/**
		 * 1. Open a server socket.
		 */
        try {
			serverSocket = new ServerSocket( Integer.parseInt( args[0] ) );

			localHostAddress = InetAddress.getLocalHost().getHostAddress();
			localPortNumber = serverSocket.getLocalPort();
			
			System.out.println( "Server (" + localHostAddress + ") is listening on " +
								"server port " + 
								localPortNumber + " ... " );

			/**
			 * 2. Accept a client connection.
			 */
    		while( true ) {
    	        Socket clientSocket = null;
        	    try {
            	    clientSocket = serverSocket.accept();
            	    
            	    clientHostAddress = clientSocket.getInetAddress().getHostAddress();
            	    clientPortNumber = clientSocket.getPort();
            	    
        	        System.out.println(	"Client (" + clientHostAddress + ") is talking through " +
        	        				"client port " + clientPortNumber + " ... " );
        	        
    				@SuppressWarnings("unused")
    				Connection c = new Connection( clientSocket );
    	        } catch (IOException e) {
        	        System.err.println( "Accept failed. ");
            	    System.exit(1);
    	        } // try_catch
    		} // while

        } catch( IOException e ) {
            System.err.println( "Could not listen on local port: "+args[0] );
            System.exit(1);
        } // try_catch

    } // main
}


class Connection extends Thread {
	
	DataInputStream in = null;
	DataOutputStream out = null;
	Socket clientSocket = null;

	public Connection( Socket client ) {
		/**
		 * 3. Open an input and output streams from the client socket.
		 */
		try {
			clientSocket = client;
			in = new DataInputStream( clientSocket.getInputStream() );
			out = new DataOutputStream( clientSocket.getOutputStream() );

			System.out.println( "[Server Thread "+this.getId()+"] Input/OutputStream is ready for " +
								clientSocket.getInetAddress().getHostAddress() +
								" ..." );
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
