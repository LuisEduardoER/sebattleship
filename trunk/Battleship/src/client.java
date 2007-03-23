import java.io.*;
import java.net.*;

/**
 *	<p>Program: TCP client
 *	</p>
 *
 *
 *	@author SeungJin Lim
 */
public class Client {


	private String serverIP;
	private int serverPort;
    private DataOutputStream out = null;
   	private Socket clientSocket = null;
   	
   	
	public Client(String serverIP, int serverPort){
    	this.serverIP=serverIP;
    	this.serverPort=serverPort;
	 
        try {
        	System.out.println("Test HEre");
			clientSocket = new Socket( this.serverIP, this.serverPort );
			System.out.println("Test Here is better!");
			out = new DataOutputStream( clientSocket.getOutputStream() );
			
	    } catch( UnknownHostException e ) {
	        System.err.println( "Unknown host: " + serverIP );
	        System.exit(1);
	    } catch( IOException e ) {
	        System.err.println( "I/O exception in connecting to: " + serverPort );
	        System.exit(1);
	    } // try_catch
    }
	
	public void Send(String msg) throws IOException{
		out.writeUTF(msg);
	}
	
    public String Listen() {
		Connection c = new Connection( clientSocket);
		return c.GetMsg();
    } 
}
