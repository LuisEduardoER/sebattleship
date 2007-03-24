package Networking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author Josh
 *
 */
public class ThreadedXmitter extends Thread {
	private BattleshipServer server = null;
	private BattleshipClient client = null;
	
	
	public ThreadedXmitter (BattleshipServer server){
		this.server = server;
		
		this.start();
		System.out.println("ThreadedXmitter Thread Started...");
	}
	
	public ThreadedXmitter (BattleshipClient client){
		this.client = client;
		this.start();
		System.out.println("ThreadedXmitter Thread Started...");
	}
	
	public void run(){
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
		String data=null;
		if(server != null){
//			while(server.out != null){
		    	try {
		    		// wait for message to be typed
					while( (data=stdin.readLine()) != null )
						// send it
						server.Send(data);
		        } catch (IOException e) {
					System.out.println( "IO: "+e.getMessage() );
		        }
//			}
		}
		else if(client != null){
//			while(client.out != null){
		    	try {
					while( (data=stdin.readLine()) != null )
						client.Send(data);
		        } catch (IOException e) {
					System.out.println( "IO: "+e.getMessage() );
		        }
			}
//		}
	}
}