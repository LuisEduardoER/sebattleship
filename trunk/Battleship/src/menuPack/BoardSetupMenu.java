/**
 * 
 */
package menuPack;

import Gameplay.Player;


/**
 * @author Steve
 *
 */
public class BoardSetupMenu extends Menu {

	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */

	public int PrintMenu(Player player) {
		System.out.println("1) Make your own board");
		System.out.println("2) Generate a random board");
		System.out.println("3) Cancel");
		System.out.println("4) Quit");
		System.out.println();
		System.out.print("user> ");
		getInput(1, 4);
		System.out.println();

		// NEED TO DO:  Implement error checking on all inputs
		// NEED TO DO
		// NEED TO DO
		// NEED TO DO
		switch (choice) {
		case 1:
			CustomBoardMenu custom = new CustomBoardMenu();
			custom.PrintMenu(player);
			return 1;
		case 2:
			RandomBoardMenu random = new RandomBoardMenu();
			random.PrintMenu(player);
			return 1;
		case 3:
			return 2; //Cancelling here means they wish to try a new session
		case 4:
			// Return to quit the game
			return 0;	// The calling function handles the quitting
		}
		return 0; //If they make it here, it's an error
	}
}
