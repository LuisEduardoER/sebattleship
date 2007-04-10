package Networking;

import java.io.IOException;

import Utilities.Console;
import Gameplay.*;


/**
 * ThreadedReceiver is a class that polls the socket for incoming transmissions.  The
 * clas is threaded to allow it to run simultaneously with other code.  The thread
 * waits for a message to arrive and once it does, it parses the message type based
 * upon the first character of the message.  "B" indicates the message contains a board
 * in the form of a string.  "C" indicates the message contains a coordinate, the
 * coordinate just guessed by the opponent.  "S" indicates a system message, which
 * currently only entails a quit notification.  "M" indicates the remainder of the
 * message contains a message from the opponent and needs to be displayed to the console.
 * 
 * 
 * @author Josh
 *
 */
public class ThreadedReceiver extends Thread {
	// These are references to objects in the higher level class
	/**
	 * Holds a reference to the server in order to access names
	 */
	private BattleshipServer server = null;
	
	
	/**
	 * Holds a reference to the client in order to access names
	 */
	private BattleshipClient client = null;
	
	
	/**
	 * Holds a reference to the player to call functions to handle various message types
	 */
	public Player player = null;
	
	
	/**
	 * Holds a copy of the static Console so that output can be updated accordingly
	 */
	public Console display = new Console();
	
	
	
	/**
	 * ThreadReceiver Constructor.
	 * This constructor is used when created by the server
	 * 
	 * @param server	A reference to the server this receiver is associated with
	 * @param player	A reference to the player of this game
	 */
	public ThreadedReceiver (BattleshipServer server, Player player){
		this.server = server;
		this.player = player;
		this.start();
	}
	
	/**
	 * ThreadReceiver Constructor.
	 * This constructor is used when created by the client
	 * 
	 * @param client	A reference to the client this receiver is asociated with
	 * @param player	A reference to the player of this game
	 */
	public ThreadedReceiver (BattleshipClient client, Player player){
		this.client = client;
		this.player = player;
		this.start();
	}
	
	/**
	 * The thread's run function.  Polls the input stream of the client/server until
	 * a message is received.  The message is then parsed by the first letter of the
	 * message.  The appropriate handling is performed to complete the message task.
	 */
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
							e.printStackTrace();
						}
					}
					
						// Switch the message type
					switch(data.charAt(0)){			
					case 'B':		// Board Data
						// call the string to board conversion function
						// will probably need to save a reference of the
						// object that contains the function
						player.StringToOppBoard(data.substring(1));
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
						display.clearScreen();
						display.putStaticLine("Network Disconnection Detected...");
						display.putStaticLine("");
						display.putStaticLine("");
						display.putStaticLine("      You Win!!!");
						display.putStaticLine("");
						display.putStaticLine("");
						display.putStaticLine("Press 3, then Enter to continue");
						display.printScreen();
						display.printPrompt("user> ");	
						return;	
					case 'M':		// Chat Message	
						default:   	//  use as default, if all else fails, print it!
							// Display message
						display.scroll(server.GetClientName() + ": " + data.substring(1));
						display.printScreen();
						display.printPrompt("user> ");
					}
		        } catch (IOException e) {        	
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
							e.printStackTrace();
						}
					}
					
					// Switch the message type
					switch(data.charAt(0)){			
					case 'B':		// Board Data
						// call the string to board conversion function
						// will probably need to save a reference of the
						// object that contains the function
						player.StringToOppBoard(data.substring(1));
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
			        	display.clearScreen();
			        	display.putStaticLine("Server Disconnection Detected...");
			        	display.putStaticLine("");
			        	display.putStaticLine("");
			        	display.putStaticLine("      You Win!!!");
			        	display.putStaticLine("");
			        	display.putStaticLine("");
			        	display.putStaticLine("Press 3, then Enter to continue");
			        	display.printScreen();
			        	display.printPrompt("user> ");
			        	return;
					case 'M':		// Chat Message	
						default:   	//  use as default, if all else fails, print it!
							// Display message
						display.scroll(client.GetServerName() + ": " + data.substring(1));
						display.printScreen();
						display.printPrompt("user> ");
					}
		        } catch (IOException e) {
		        }
			}
		}
	}
}
