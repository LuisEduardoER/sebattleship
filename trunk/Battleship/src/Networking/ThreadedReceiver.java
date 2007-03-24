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
					
						// Switch the message type
					switch(data.charAt(0)){			
					case 'B':		// Board Data
						// call the string to board conversion function
						// will probably need to save a reference of the
						// object that contains the function
						break;						
					case 'C':		// Coordinate Data
						// call the string to coordinate converstion function
						// will probably need to save a reference of the 
						// object tat contains the function
						break;					
					case 'S':		// System Data
						// figure out what system messages might come and
						// determine how to handle them here
						break;	
					case 'M':		// Chat Message	
						default:   	//  use as default, if all else fails, print it!
							// Display message
							System.out.println(server.GetClientName() + ": " + data.substring(1));	
					}
		        } catch (IOException e) {
					System.out.println( "IO: "+e.getMessage() );
		        }
			}
		}
		else if(client != null){
			while(client.in != null){
		    	try {
					while( (data=client.in.readUTF()) == null );
					
					// Switch the message type
					switch(data.charAt(0)){			
					case 'B':		// Board Data
						// call the string to board conversion function
						// will probably need to save a reference of the
						// object that contains the function
						break;						
					case 'C':		// Coordinate Data
						// call the string to coordinate converstion function
						// will probably need to save a reference of the 
						// object tat contains the function
						break;					
					case 'S':		// System Data
						// figure out what system messages might come and
						// determine how to handle them here
						break;	
					case 'M':		// Chat Message	
						default:   	//  use as default, if all else fails, print it!
							// Display message
							System.out.println(client.GetServerName() + ": " + data.substring(1));
					}
		        } catch (IOException e) {
					System.out.println( "IO: "+e.getMessage() );
		        }
			}
		}
	}
}
