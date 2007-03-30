

import java.io.*;

import Networking.*;
import Gameplay.*;
import menuPack.*;



/**
 * @author Josh
 *
 */
public class BattleshipGame{

	private static BattleshipServer server;
	private static BattleshipClient client;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		StartMenu start_menu = new StartMenu();
		
	START1:{		// All definitions should go before the label,
	START2:			// All function calls and program flow should go in the label
		for(;true;){	// This loops the actual running of the program.
						// It is not intended that the program loop forever,
						// this is simply done in order to allow for a restert
						// with a "continue START1;" command.
						// To exit the for loop (and thus the game)
						// use the command "break START2;".
			start_menu.PrintMenu();
			if(!start_menu.Input(server, client))
				break START1;

		
		
		
		
		
		
		
		}}// end label START, end for -- Should ALWAYS be tied together
		 // this should also be the end, if not near the end of the program
		
		System.out.println("Goodbye... ");
		
		
		
/* Temporary Driver for Josh's Stuff 
 * -- DELETE ME
 */

		
/*
		BattleshipServer server = null;
		BattleshipClient client = null;
		
		if(args[0].equalsIgnoreCase("s")){
			System.out.println("Starting Server");
			server= new BattleshipServer(7780, "Server");
		}
	//	Thread serverT = new Thread(server,"Server Thread");
		if(args[0].equalsIgnoreCase("c"))
		{
			System.out.println("Starting Client");
			client = new BattleshipClient("127.0.0.1", 7780, "Client");
		}
		System.out.println("Do I Make it here?");
	
		

		ThreadedReceiver listen = null;
		ThreadedXmitter send = null;
		
			if(args[0].equalsIgnoreCase("s")){

				// create a receiving thread
				listen = new ThreadedReceiver(server);
				// create a transmitting thread
				send = new ThreadedXmitter(server);
			}
			else if(args[0].equalsIgnoreCase("c")){
				
				listen = new ThreadedReceiver(client);
				send = new ThreadedXmitter(client);
			}

		while (true);
		
    //	System.out.println("Quitting...");
		// NEED:
		//	- Top Level Module in a top-level class to be threaded and handle listening
		//    - ability to send messages at same time
		
		
	//	server.Host();
	//	client.Join();
	
		
*/
		
/*
 * End Temporary Driver
 */
		

	}

}
