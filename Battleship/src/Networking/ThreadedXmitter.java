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
	
	// dedicated for chatting
	public void run(){
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
		String data=null;
		if(server != null){
	    	try {
	    		// wait for message to be typed
				while( (data=stdin.readLine()) != null ){
					// send it
					String temp = "M";
					temp = temp.concat(data);
					server.Send(temp);
					System.out.println(server.GetServerName() + ": " + data);
				}
	        } catch (IOException e) {
				System.out.println( "IO: "+e.getMessage() );
	        }				
		}
		else if(client != null){
	    	try {
				while( (data=stdin.readLine()) != null ){
					String temp = "M";	// affix the message type indicator
					temp = temp.concat(data);
					client.Send(temp);
					System.out.println(client.GetClientName() + ": " + data);
				}
	        } catch (IOException e) {
				System.out.println( "IO: "+e.getMessage() );
	        }
		}
	}
}
