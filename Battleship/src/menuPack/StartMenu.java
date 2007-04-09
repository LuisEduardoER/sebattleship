/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import Gameplay.Player;
import Networking.BattleshipClient;
import Networking.BattleshipServer;
import Networking.ThreadedReceiver;
import Utilities.Console;
/**
 * @author Steve
 *
 */
public class StartMenu extends Menu {
	public Console display = new Console();
	public String[] PrintMenu() {
		String menu[]=new String[3];
		
		menu[0]="1) Host Game";
		menu[1]="2) Join Game";
		menu[2]="3) Quit";
		
		return menu;
	}
	

	/**
	 * Gets and handles the user input from the menu.  Receives as parameters all objects
	 * which need to be accessed as a result of a menu choice selection.
	 * 
	 * @param server a BattleshipServer object to initialize if 'Host Game' is chosen
	 * @param client a BattleshipCleint object to initialize if 'Join Game' is chosen
	 * @return returns true if the menu option was handled by function.
	 * 		   returns false if the menu option was not handled by function.
	 * 				Quit should be the only menu option not handled in function.
	 */
	public int Input(){
		// block until the user gives appropriate input
		for(getInput();!check(1,3);) {
			display.scroll("Invalid Input: " + choice);
			display.printScreen();
			display.printPrompt("user> ");
			getInput();
		}
		return choice;
	}

	//I'm assuming i then need to know how to return choice to some other
	//function... I wonder which one it is!
}
