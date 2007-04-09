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

public class GameMenu extends Menu {

	public String[] PrintMenu(){
		return null;
	}
	
	public String[] PrintMenu(boolean myturn) {
		String myturnmenu[] = new String[3];
		String histurnmenu[] = new String[2];
		
		myturnmenu[0]= "1) Send Message";
		myturnmenu[1]= "2) Take Turn";
		myturnmenu[2]= "3) Quit Game";
		
		histurnmenu[0]= "1) Send Message";
		histurnmenu[1]= "3) Quit Game";
		
		if(myturn)
			return myturnmenu;
		else
			return histurnmenu;
	}
	
	
	
	public boolean Input(Player player, BattleshipServer server, BattleshipClient client, boolean myturn){

		for(getInput();!check(1,3);) {
			display.scroll("Invalid Input: " + choice);
			display.printScreen();
			display.printPrompt("user> ");
			getInput();
		}

		
		switch(choice){
		case 1:		// Get a message and send it
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
