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

/**
 * @author Steve
 *
 */
public class StartMenu extends Menu {

	public void PrintMenu() {
		System.out.println("1) Host Game");
		System.out.println("2) Join Game");
		System.out.println("3) Quit");
		System.out.print("user> ");
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
		while(!getInput(1,3)) {
			System.out.println("Invalid Input: " + choice);
			System.out.print("user> ");
		}
		return choice;
	}

	//I'm assuming i then need to know how to return choice to some other
	//function... I wonder which one it is!
}
