

import java.io.*;
import Networking.*;


/**
 * @author Josh
 *
 */
public class BattleshipGame{
	public static ThreadedReceiver listen = null;
	public static ThreadedXmitter send = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		
/* Temporary Driver for Josh's Stuff 
 * -- DELETE ME
 */
		
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
	
		
		// Communication Driver
		BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
		String input = " ";
		String msg;
		System.out.println("Starting Communication...");
	//	System.out.println("Begin Typing-");
		
//		while(!input.equalsIgnoreCase("quit")){

			if(args[0].equalsIgnoreCase("s")){

				// create a listening thread
				listen = new ThreadedReceiver(server);
				// create a sending thread
				send = new ThreadedXmitter(server);
/*
				//
		//		listen = new ThreadedReceiver(server);
		//		System.out.println("Started Listening for partner...");
		//		listen = null;
				
				if((msg = server.Listen()) != null)
					System.out.println("   Message from _____ " + msg);
				try{

					if( stdin.ready()){
						try{
							input = stdin.readLine();
							server.Send(input);
						}catch(IOException e){
							System.err.println("Error Sending " + input);
						}
					}
				}catch(IOException e){
					System.err.println("Error Reading from System.in");
				}
*/
			}
/////////////////////////////////////////////////////////////////////////////////////
			else if(args[0].equalsIgnoreCase("c")){
				
				listen = new ThreadedReceiver(client);
				send = new ThreadedXmitter(client);
				
/*	
//				listen = new ThreadedReceiver(client);
				if((msg = client.Listen()) != null)
					System.out.println("   Message from _____ " + msg);			
				
//				System.out.println("Started Listening for partner...");
				try{
					if( stdin.ready()){
						try{
							client.Send(stdin.readLine());
						}catch(IOException e){
							System.err.println("Error Sending " + input);
						}
					}
				}catch(IOException e){
					System.err.println("Error Reading from System.in");
				}
*/
			}
//		}
		while (true);
//		System.out.println("Quitting...");
		// NEED:
		//	- Top Level Module in a top-level class to be threaded and handle listening
		//    - ability to send messages at same time
		
		
	//	server.Host();
	//	client.Join();
		
		
/*
 * End Temporary Driver
 */
		

	}

}
