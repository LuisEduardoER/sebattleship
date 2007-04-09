

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Networking.*;
import Utilities.Console;
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
	private static ThreadedReceiver listener=null;
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Console display = new Console();
		StartMenu start_menu = new StartMenu();
		BoardSetupMenu board_menu = new BoardSetupMenu();
		CustomBoardMenu custom_menu = new CustomBoardMenu();
		RandomBoardMenu random_menu = new RandomBoardMenu();
		GameMenu game_menu = new GameMenu();
		Player player = new Player();
		
		
	

			/*
			 * One Time Only Stuff Goes HERE...
			 */ 
		display.clearScreen();
		DisplayTitleScreen(display);
		WaitForEnter(display);

		
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
				// reset the members
				// we should make sure not to have multiple listeners
				// or have both a server and a client (ie. they create a server,
				// then cancel and decide to join a game instead).
			server = null;
			client = null;
			listener = null;
			
			
			// Display the start menu.
			// Block for input and handle the return
			//  (all inputs handle themselves except for the quit
			//   option which MUST? be handled here, in the top module)
			String strtmenu[] = start_menu.PrintMenu();
			display.clearScreen();
			display.putStaticLine("");
			for(int i =0; i<strtmenu.length; i++)
				display.putStaticLine(strtmenu[i]);
			display.printScreen();
			display.printPrompt("user> ");
			
			int choice = start_menu.Input();
			
			// Must handle all messages in here
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
			String name=null;
			String server_ip=null;	
			int server_port=7777;
			display.clearScreen();
			display.putStaticLine("");
			// NEED TO DO:  Implement error checking on all inputs
			// NEED TO DO
			// NEED TO DO
			// NEED TO DO
			switch(choice){
			case 1:
				display.putStaticLine("Enter your name: ");
				display.printScreen();
				name = display.readLine("user> ");
				display.putStaticLine("");
				display.putStaticLine("  Your Name: "+name);
//				System.out.println("Enter the server port: ");
//				try {
//					server_port = Integer.parseInt(stdin.readLine());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}	
				try {
					display.putStaticLine("");
					display.putStaticLine("  Your IP: " + InetAddress.getLocalHost().getHostAddress() + " Port: " + server_port);
					display.printScreen();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					// set this player as the host
				player.host=true;
					// Start the server connection
				server = new BattleshipServer(server_port, name, display);
				// Start the listener now so that we can receiver the opponent's board
				listener = new ThreadedReceiver(server, player);
				break;
			case 2:
				display.putStaticLine("Enter your name: ");
				display.printScreen();
				name = display.readLine("user> ");
				display.putStaticLine("");
				display.putStaticLine("  Your Name: "+name);
				
				display.putStaticLine("");
				display.putStaticLine("Enter the server IP: ");
				display.printScreen();
				server_ip = display.readLine("user> ");
//				System.out.println("Enter the server port: ");
//				try {
//					server_port = Integer.parseInt(stdin.readLine());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
			String brdmenu[]=board_menu.PrintMenu();
			display.clearScreen();
			display.putStaticLine("");
			for(int i =0; i<brdmenu.length; i++)
				display.putStaticLine(brdmenu[i]);
			display.printScreen();
			display.printPrompt("user> ");
			
			int boardMenuChoice = board_menu.Input(player, custom_menu, random_menu);
			switch (boardMenuChoice){
			case 0:	break START1;
			case 1:	continue START2;
			default:
				if(server!=null)
					try {
						server.Send("B"+player.MyBoardToString());
						player.board_sent=true;			// Indicate the board has been sent
						display.clearScreen();
						display.putStaticLine("");
						display.putStaticLine("Waiting for opponent to finish ship placement");
						display.printScreen();
					} catch (IOException e) {
						display.scroll("Error Sending Board to Opponent");
						display.printScreen();
						e.printStackTrace();
					}
				else if(client!=null)
					try {
						client.Send("B"+player.MyBoardToString());
						player.board_sent=true;			// Indicate the board has been sent
						display.clearScreen();
						display.putStaticLine("");
						display.putStaticLine("Waiting for opponent to finish ship placement");
						display.printScreen();
						System.out.println();
					} catch (IOException e) {
						display.scroll("Error Sending Board to Opponent");
						display.printScreen();
						e.printStackTrace();
					}
			}
			
			
			// By this point the board has been set up and the game is ready to be played.
	
				// block for the player to send his board	
			while(!player.board_received);
				// Once we've got it, display it and go on to the game process
	//		display.clearScreen();
	//		player.Display_Boards();
	//		display.printScreen();
			player.board_received=false;		// clear the flag so we never do this again
		
				// Loop forever.  When its my turn, take my turn.
				//  Need to exit when the game is over...
			while(true){
				while(player.isTurn){	// keep getting inputs as long is it's my turn
					display.clearScreen();
					player.Display_Boards();
					display.putStaticLine("");
					String gmmenu[] = game_menu.PrintMenu(true);
					for(int i=0; i<gmmenu.length; i++)
						display.putStaticLine(gmmenu[i]);
					display.printScreen();
					display.printPrompt("user> ");
					
					// Block for input
					if(!game_menu.Input(player, server, client, true))
						continue START2;	// If they chose to quit
					
					//player.Display_Boards();   except for the client's first display, the rest of the displays are called inside the player class
					if(player.victory || player.opponent_victory)
						continue START2;   //if victory, exit giant loop
						                //victory messages are taken care of in My_Turn and His_Turn
				}
				while(!player.isTurn){	// keep getting inputs as long is it's not my turn
					display.clearScreen();
					player.Display_Boards();
					display.putStaticLine("");
					String gmmenu[] = game_menu.PrintMenu(false);
					for(int i=0; i<gmmenu.length; i++)
						display.putStaticLine(gmmenu[i]);
					display.printScreen();
					display.printPrompt("user> ");
					
						// block for input
					if(!game_menu.Input(player, server, client, false))
						continue START2;	// If they chose to quit
					
					display.clearScreen();	// Clear the screen
				}
			}
		
		
		
	
			
		}}// end label START, end for -- Should ALWAYS be tied together
		 // this should also be the end, if not near the end of the program
	/*	
		String response="n";
		System.out.println("Would you like to play again? (y/n)");
		try {
			response = stdin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response.charAt(0)=='y' || response.charAt(0)=='Y')
			//GO BACK TO BOARD SETUP.  INCOMPLETE.****************************
			 * */
			 
		display.putStaticLine("Goodbye... ");
		
	}

	public static void DisplayTitleScreen(Console display){
		display.putStaticLine("");
		display.putStaticLine("      ___                 |           ___                                ");
		display.putStaticLine("     ||  \\     ____    /-----\\    § ||  |                              ");
		display.putStaticLine("     ||   \\ //      |  =======      ||  |      ______                     ");
		display.putStaticLine("     || O  |//_/|   |  ======= §_§_ ||  |     /      \\         #        ")        ;
		display.putStaticLine("     ||   <   `||   |   /| |  /O_O\\ ||  |    |  []    |              #  ");
		display.putStaticLine("     ||   \\  /      |  / | |    ||  ||  |    | -------        #         ");
		display.putStaticLine("     ||  0  ||  []  |_/__|_|    ||  ||  |__   \\ |______       #  #        ");
		display.putStaticLine("     ||_____||______|      \\    ||  ||_____|_  -------   __ / #         ");
		display.putStaticLine("      /  o  o   o   o   o   \\  ||||  |  o  o |          |  |/         ");
		display.putStaticLine(" ____/_______________________\\_||||__|_______|__________|__|_______ ");
		display.putStaticLine("     ___                                                           /    ");
		display.putStaticLine("    /  _\\             _   _           _            ___            |    ");
		display.putStaticLine("   | /__             | |_| |         | |          | o \\          |    ");
		display.putStaticLine("    \\_  |            | __  |         | |          |  _|         |   ");
		display.putStaticLine("    __| |            | | | |         | |          | |          |    ");
		display.putStaticLine("    \\__/             |_| |_|         |_|          |_|         |  ");
		display.putStaticLine("     _________________________________________________________|       ");
	}
	
	public static void WaitForEnter(Console display){
		display.putStaticLine("");
		display.putStaticLine("Press Enter to Continue... ");
		display.printScreen();
		display.readLine();
	}
}
