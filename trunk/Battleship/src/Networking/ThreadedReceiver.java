package Networking;

import java.io.IOException;
import Gameplay.*;

/**
 * 
 */

/**
 * @author Josh
 *
 */
public class ThreadedReceiver extends Thread {
	// These are references to objects in the higher level class
	private BattleshipServer server = null;
	private BattleshipClient client = null;
	public Player player = null;
	
	
	public ThreadedReceiver (BattleshipServer server, Player player){
		this.server = server;
		this.player = player;
		this.start();
		System.out.println("ThreadedReceiver Thread Started...");
	}
	
	public ThreadedReceiver (BattleshipClient client, Player player){
		this.client = client;
		this.player = player;
		this.start();
		System.out.println("ThreadedReceiver Thread Started...");
	}
	
	public void run(){
		String data=null;
		if(server != null){
			// NEED TO DO: Figure a way to detect socket disconnections
			while(true){
		    	try {
		    			// Wait for a message to arrive
					while( (data=server.in.readUTF()) == null ){
						try {
							this.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
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
						// object that contains the function
						player.His_Turn(data.substring(1));
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
		        	System.out.println( "Server Disconnection Detected... Press any key to continue.");
		
		        	try {
						client.in.read();
					} catch (IOException e1) {
					}
		        	return;
			//		System.out.println( "IO: "+e.getMessage() );
		        }
			}
		}
		else if(client != null){
			while(true){
		    	try {
					while( (data=client.in.readUTF()) == null ){
						try {
							this.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
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
						// object that contains the function
						player.His_Turn(data.substring(1));
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
		        	System.out.println( "Server Disconnection Detected... Press any key to continue.");
		
		        	try {
						client.in.read();
					} catch (IOException e1) {
					}
		        	return;
			//		System.out.println( "IO: "+e.getMessage() );
		        }
			}
		}
	}
}
