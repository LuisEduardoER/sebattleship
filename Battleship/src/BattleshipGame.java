

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Networking.*;
import Gameplay.*;
import menuPack.*;



/**
 * @author Josh
 *
 */
public class BattleshipGame{

	// only one or the other should ever exist at a time
	private static BattleshipServer server=null;
	private static BattleshipClient client=null;
	private static ThreadedXmitter talker=null;
	private static ThreadedReceiver listener=null;
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		StartMenu start_menu = new StartMenu();
		BoardSetupMenu board_menu = new BoardSetupMenu();
		CustomBoardMenu custom_menu = new CustomBoardMenu();
		RandomBoardMenu random_menu = new RandomBoardMenu();
		Player player = new Player();
	

			/*
			 * One Time Only Stuff Goes HERE...
			 */ 
		DisplayTitleScreen();
		WaitForEnter();

		
			/*
			 * Stuff to be Repeated Until Exit Goes HERE...
			 */
	START1:{		// All definitions should go before the label,
	START2:			// All function calls and program flow should go in the label
		while(true){	// This loops the actual running of the program.
						// It is not intended that the program loop forever,
						// this is simply done in order to allow for a restert
						// with a "continue START2;" command.
						// To exit the for loop (and thus the game)
						// use the command "break START1;".
			

			// Display the start menu.
			// Block for input and handle the return
			//  (all inputs handle themselves except for the quit
			//   option which MUST? be handled here, in the top module)
			//int choice = start_menu.();
			
			// Must handle all messages in here
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			String name=null;
			String server_ip=null;	
			int server_port=0;
			
			// NEED TO DO:  Implement error checking on all inputs
			// NEED TO DO
			// NEED TO DO
			// NEED TO DO
			switch(start_menu.PrintMenu(player)){   //get input
			case 1:
				System.out.println("Enter your name: ");
				try {
					name = stdin.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Enter the server port: ");
				try {
					server_port = Integer.parseInt(stdin.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				try {
						System.out.println("Your IP: " + InetAddress.getLocalHost().getHostAddress() + " Port: " + server_port);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					// set this player as the host
				player.host=true;
					// Start the server connection
				server = new BattleshipServer(server_port, name);
				// Start the listener now so that we can receiver the opponent's board
				listener = new ThreadedReceiver(server, player);
				break;
			case 2:
				System.out.println("Enter your name: ");
				try {
					name = stdin.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Enter the server IP: ");
				try {
					server_ip = stdin.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				System.out.println("Enter the server port: ");
				try {
					server_port = Integer.parseInt(stdin.readLine());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.host=false;
				client = new BattleshipClient(server_ip, server_port, name);
				// Start the listener now so that we can receiver the opponent's board
				listener = new ThreadedReceiver(client, player);
				break;
			case 3:
				break START1;
			}

			// else, if start_menu.Input returned true, then connection has been made
			//  proceed with the setup
			
			
			
			// Display the board setup menu.
			// Block for input and handle the return
			//  (all inputs handle themselves except for the quit
			//   and cancel options which MUST? be handled here, in the top module)
			//  If canclee then restart the eternal loop, which restarts at start menu
			//  If quit, then kick out of the eternal loop
			
			
			switch (board_menu.PrintMenu(player)){
			case 0:	break START1;
			case 1:
					// reset the members
					// we should make sure not to have multiple listeners
					// or have both a server and a client (ie. they create a server,
					// then cancel and decide to join a game instead).
				server = null;
				client = null;
				listener = null;
				talker = null;
				continue START2;
			default:
				if(server!=null)
					try {
						server.Send("B"+player.MyBoardToString());
					} catch (IOException e) {
						System.err.println("Error Sending Board to Opponent");
						e.printStackTrace();
					}
				else if(client!=null)
					try {
						client.Send("B"+player.MyBoardToString());
					} catch (IOException e) {
						System.err.println("Error Sending Board to Opponent");
						e.printStackTrace();
					}
			}
			
			
			// By this point the boards have been set up and the game is ready to be played.

				// Loop forever.  When its my turn, take my turn.
				//  Need to exit when the game is over...
			while(true){
				if(player.isTurn){
					player.Display_Boards();
					player.My_Turn(server, client);
				}
			}
		
		
		
	
		
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

	public static void DisplayTitleScreen(){
		System.out.println();
		System.out.println("      ___                 |           ___                                ");
		System.out.println("     ||  \\     ____    /-----\\    § ||  |                              ");
		System.out.println("     ||   \\ //      |  =======      ||  |      ______                     ");
		System.out.println("     || O  |//_/|   |  ======= §_§_ ||  |     /      \\         #        ")        ;
		System.out.println("     ||   <   `||   |   /| |  /O_O\\ ||  |    |  []    |              #  ");
		System.out.println("     ||   \\  /      |  / | |    ||  ||  |    | -------        #         ");
		System.out.println("     ||  0  ||  []  |_/__|_|    ||  ||  |__   \\ |______       #  #        ");
		System.out.println("     ||_____||______|      \\    ||  ||_____|_  -------   __ / #         ");
		System.out.println("      /  o  o   o   o   o   \\  ||||  |  o  o |          |  |/         ");
		System.out.println(" ____/_______________________\\_||||__|_______|__________|__|_______ ");
		System.out.println("     ___                                                           /    ");
		System.out.println("    /  _\\             _   _           _            ___            |    ");
		System.out.println("   | /__             | |_| |         | |          | o \\          |    ");
		System.out.println("    \\_  |            | __  |         | |          |  _|         |   ");
		System.out.println("    __| |            | | | |         | |          | |          |    ");
		System.out.println("    \\__/             |_| |_|         |_|          |_|         |  ");
		System.out.println("     _________________________________________________________|       ");
		System.out.println("                                                                      ");
		System.out.println("                                                                      ");
		
		
	}
	
	public static void WaitForEnter(){
		System.out.println("");
		System.out.println("Press Enter to Continue... ");
		try {
			stdin.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
