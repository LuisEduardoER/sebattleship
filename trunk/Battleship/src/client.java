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
public class Client {

	private static String clientName;
	private static String serverName;
		
    public static void main(String serverIP, int serverPort, String name) throws IOException {

    	clientName=name;
    	
       	Socket clientSocket = null;
   	    DataOutputStream out = null;
        DataInputStream in = null;


        try {
        	/**
        	 * 1. Open a client stream socket and connect it to the specified port number on the named server 
        	 */
			clientSocket = new Socket( serverIP, serverPort );

            /**
             * 2. Establish an input and output stream for this socket. 
             */
			out = new DataOutputStream( clientSocket.getOutputStream() );
			in = new DataInputStream( clientSocket.getInputStream() );			
			
			// Exchange client and server names
			out.writeUTF(clientName);
			serverName=in.readUTF();
			
			
			// To display on connection establishment
            System.out.println( "You have joined " + serverName + "'s game." );



			
			/**
			 * 3.a Prepare messages to the server using the standard input.
			 */
			BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in) );

			String user_input;
			while( (user_input = stdin.readLine()) != null && (!user_input.equalsIgnoreCase("bye")) ) {

				/**
				 * 3.b Write out the message to the output stream.  Display verification of sent msg. 
				 */
				out.writeUTF( user_input );
				

				/**
				 * 3.c Read from the input stream to receive verification of receipt from server.
				 */

			} // while

			/**
			 * 4. Close the streams.
			 */
	        out.close();
	        in.close();

        } catch( UnknownHostException e ) {
            System.err.println( "Unknown host: "+serverIP );
            System.exit(1);
        } catch( IOException e ) {
            System.err.println( "I/O exception in connecting to: "+serverPort );
            System.exit(1);
        } // try_catch

        /**
         * 5. Close the socket.
         */
        clientSocket.close();
    } // main
}
