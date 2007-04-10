package menuPack;

import java.io.IOException;

import Gameplay.Battleship;
import Gameplay.Carrier;
import Gameplay.Cruiser;
import Gameplay.PatrolBoat;
import Gameplay.Player;
import Gameplay.Ship;
import Gameplay.Submarine;
import Networking.BattleshipClient;
import Networking.BattleshipServer;

/**
 * A menu class containing the options to be displayed while in the game.
 * There are 3 options: Send Message, Take Turn, and Resign Game.  
 * Depending on whose turn it is, variations of the menu are displayed.
 * This class also handles the inputs and menu choice selection.
 * 
 * @author Josh
 *
 */
public class GameMenu extends Menu {

	/**
	 * Overridden PrintMenu() function.  Not used in this menu.
	 */
	public String[] PrintMenu(){
		return null;
	}
	
	/**
	 * Returns a String array containing the lines of menu options.
	 * Chooses which menu to return based on whose turn it is.
	 * 
	 * @param myturn	true if it is the players turn, otherwise false
	 * @return	String array containing the menu, with each item being it's
	 * 			own String.
	 */
	public String[] PrintMenu(boolean myturn) {
		String myturnmenu[] = new String[3];
		String histurnmenu[] = new String[2];
		
		myturnmenu[0]= "1) Send Message";
		myturnmenu[1]= "2) Take Turn";
		myturnmenu[2]= "3) Resign Game";
		
		histurnmenu[0]= "1) Send Message";
		histurnmenu[1]= "3) Resign Game";
		
		if(myturn)
			return myturnmenu;
		else
			return histurnmenu;
	}
	
	
	
	/**
	 * Function to get menu selection and handle the appropriate actions.  Handles
	 * all menu selections except for that of "Resign Game", which must be handled
	 * by the higher level caller.
	 * 
	 * @param player	A reference to the game player, for accessing boards and functions
	 * @param server	A reference to the server, if this is the servers'
	 * @param client	A reference to the client, if this is the clients'
	 * @param myturn	Contains true if it is the player's turn, otherwise false
	 * @return	True if the menu choice was handled by the Input() function.  False if
	 * 			outside handling needs to be performed
	 */
	public boolean Input(Player player, BattleshipServer server, BattleshipClient client, boolean myturn){

		for(getInput();!check(1,3);) {
			//display.scroll("Invalid Input: " + choice);  redundant error message.  -nate
			display.printScreen();
			display.printPrompt("user> ");
			getInput();
		}

		
		switch(choice){
		case 1:		// Get a message and send it
			player.messaging=true;
			display.clearScreen();
			player.Display_Boards();
			display.putStaticLine("");
			display.putStaticLine("Enter Message");
			display.printScreen();
			String message = display.readLine("user> ");
			if(server!=null)
				try {
					server.Send("M"+message);
				} catch (IOException e) {
				}
			if(client!=null)
				try {
					client.Send("M"+message);
				} catch (IOException e) {
				}
			if(server!=null)
				display.scroll(server.GetServerName() + ": " + message);
			if(client!=null)
				display.scroll(client.GetClientName() + ": " + message);
			display.printScreen();
			player.messaging=false;
			break;
		case 2:		// Call My_Turn to let them take their turn, if it is their turn, else nothing
			if(myturn){
				display.clearScreen();
				player.Display_Boards();
				player.My_Turn(server, client);
				display.putStaticLine("                Waiting for Opponent's Move....");
				display.printScreen();
			}
			break;
		case 3:		// Return false to quit
			return false;	
		}
		return true;
	}

}
