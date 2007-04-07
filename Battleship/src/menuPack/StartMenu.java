/**
 * 
 */
package menuPack;

import Gameplay.Player;

/**
 * @author Steve
 *
 */
public class StartMenu extends Menu {

	public int PrintMenu(Player player) {
		System.out.println("1) Host Game");
		System.out.println("2) Join Game");
		System.out.println("3) Quit");
		System.out.print("user> ");
		getInput(1, 3);
		System.out.println();
		
		
		return choice;
		
		
		//This should probably get called from the network session...
		  //I'm not sure though, but it will stay here until changed
		
		//April 6, 7:30 PM Nate
		// Let's not do this here.  Let's let it return the choice to the driver function.  Then the
		// driver class will call the boardsetupmenu.  Otherwise
		// a lot of the network input is put in this class.  It seems best if they are separate. 
		//  I hope that's alright.
		
		//BoardSetupMenu boardSetupMenu = new BoardSetupMenu();
		//boardSetupMenu.PrintMenu(player);
		
		
		
		}
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

