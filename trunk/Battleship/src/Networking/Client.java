package Networking;
import java.io.*;
import java.net.*;

/**
 *	<p>Program: TCP client
 *	</p>
 *
 *
 *	@author Josh
 */
public class Client {


	private String serverIP = null;
	private int serverPort = 0;
    public DataOutputStream out = null;
    public DataInputStream in = null;
    public Socket clientSocket = null;
   	
   	
	public Client(String serverIP, int serverPort){
    	this.serverIP=serverIP;
    	this.serverPort=serverPort;
	 
        try {
			clientSocket = new Socket( this.serverIP, this.serverPort );
			out = new DataOutputStream( clientSocket.getOutputStream() );
			in = new DataInputStream(clientSocket.getInputStream());
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
    	String data = null;
    	try{
        	data = in.readUTF();  		
        } catch (IOException e) {
			System.out.println( "IO: "+e.getMessage() );
        }
        return data;
    } 
}
