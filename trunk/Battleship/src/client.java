import java.io.*;
import java.net.*;

/**
 *	<p>Program: TCP client
 *	</p>
 *
 *	TCP client socket:
 *	<ul>
 *	<li>1.	Open a (client) socket. 
 *	<li>2.	Open an input stream and output stream from the socket. 
 *	<li>3.	Write to the output stream and read from the input stream according to the server's protocol. 
 *	<li>4.	Close the streams. 
 *	<li>5.	Close the socket. 
 *	</ul>
 *
 *	@author SeungJin Lim
 */
public class client {

    public static void main(String[] args) throws IOException {

    	/**
    	 * Check the program parameter(s).
    	 */
		if( args.length < 2 ) {
			System.err.println( "Usage: java client <server address> <server port>" );
			System.err.print( "   args.length="+args.length );
			for( int i=0; i<args.length; i++ ) {
				System.err.print( " "+args[i] );
			}
			System.err.println();
			System.exit(-1);
		} // if

       	Socket clientSocket = null;
   	    DataOutputStream out = null;
        DataInputStream in = null;
        String localHostAddress = null;
        int localPortNumber;
        String serverHostAddress = null;
        int serverPortNumber;

        try {
        	/**
        	 * 1. Open a client stream socket and connect it to the specified port number on the named server 
        	 */
			clientSocket = new Socket( args[0], Integer.parseInt( args[1] ) );

			localHostAddress = InetAddress.getLocalHost().getHostAddress(); // the local host address.
			localPortNumber = clientSocket.getLocalPort(); // the local port to which this socket is bound.
			serverHostAddress = clientSocket.getInetAddress().getHostAddress(); // the address to which the socket is connected.
			serverPortNumber = clientSocket.getPort(); // the remote port to which this socket is connected.
			
            System.out.println( "Client (" + localHostAddress + ") is listening on " +
            					"server port " + serverPortNumber + " of " + 
								"server (" + serverHostAddress + ") using " +
								"local port " + localPortNumber + " ... " );

            /**
             * 2. Establish an input and output streams by openning an input and output streams for this socket. 
             */
			out = new DataOutputStream( clientSocket.getOutputStream() );
			in = new DataInputStream( clientSocket.getInputStream() );

			
			/**
			 * 3.a Prepare messages to the server using the standard input.
			 */
			BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in) );

			String user_input;
			while( (user_input = stdin.readLine()) != null && (!user_input.equalsIgnoreCase("bye")) ) {

				/**
				 * 3.b Write out the message to the output stream. 
				 */
				out.writeUTF( user_input );
				
				System.out.println( "Message sent to server (" + serverHostAddress + ":" + serverPortNumber + "): " +
						user_input );

				/**
				 * 3.c Read from the input stream. 
				 */
				String serverMessage = in.readUTF(); 
				
				System.out.println( "Message arrived from server (" + serverHostAddress + ":" + serverPortNumber + "): " +
									serverMessage + " ... " );
			} // while

			/**
			 * 4. Close the streams.
			 */
	        out.close();
	        in.close();

        } catch( UnknownHostException e ) {
            System.err.println( "Unknown host: "+args[0] );
            System.exit(1);
        } catch( IOException e ) {
            System.err.println( "I/O exception in connecting to: "+args[0] );
            System.exit(1);
        } // try_catch

        /**
         * 5. Close the socket.
         */
        clientSocket.close();
    } // main
}
