package Networking;
import java.io.IOException;

/**
 * 
 */

/**
 * @author Josh
 *
 */
public class ThreadedReceiver extends Thread {
	private BattleshipServer server = null;
	private BattleshipClient client = null;
	
	
	public ThreadedReceiver (BattleshipServer server){
		this.server = server;
		
		this.start();
		System.out.println("ThreadedReceiver Thread Started...");
	}
	
	public ThreadedReceiver (BattleshipClient client){
		this.client = client;
		this.start();
		System.out.println("ThreadedReceiver Thread Started...");
	}
	
	public void run(){
		String data=null;
		if(server != null){
			// NEED TO DO: Figure a way to detect socket disconnections
			while(server.in != null){
		    	try {
		    			// Wait for a message to arrive
					while( (data=server.in.readUTF()) == null );
						// Display message
					System.out.println(server.GetClientName() + ": " + data);
		        } catch (IOException e) {
					System.out.println( "IO: "+e.getMessage() );
		        }
			}
		}
		else if(client != null){
			while(client.in != null){
		    	try {
					while( (data=client.in.readUTF()) == null );
					System.out.println(client.GetServerName() + ": " + data);
		        } catch (IOException e) {
					System.out.println( "IO: "+e.getMessage() );
		        }
			}
		}
	}
}
