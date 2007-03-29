

import java.io.*;
import Networking.*;



/**
 * @author Josh
 *
 */
public class BattleshipGame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		
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
