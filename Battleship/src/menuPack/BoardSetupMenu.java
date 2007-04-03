/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gameplay.Player;



/*
import Gameplay.Player;
import Gameplay.Ship;
import Gameplay.My_Board;
import Gameplay.Board;
*/

/**
 * @author Steve
 *
 */
public class BoardSetupMenu extends Menu {

	
	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */
	
	public void PrintMenu() {
		System.out.println("1) Make your own board");
		System.out.println("2) Generate a random board");
		System.out.println("3) Cancel");		
		System.out.println("4) Quit");
		System.out.println();
		System.out.print("user> ");
			
		System.out.println();
	}
	
	//	I'm assuming i then need to know how to return choice to some other
		//function... I wonder which one it is!

	public int Input(Player player, CustomBoardMenu custom_board, RandomBoardMenu random_board){
		// block until the user gives appropriate input
		for (getInput(); !check(1, 4);) {
			System.out.println("Invalid Input: " + choice);
			System.out.print("user> ");
			getInput();
		}
		
		// Must handle all messages in here
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		
		// NEED TO DO:  Implement error checking on all inputs
		// NEED TO DO
		// NEED TO DO
		// NEED TO DO
		switch(choice){
		case 1:
				// Display the board custom board menu
			custom_board.PrintMenu();
			break;
		case 2:
				// Display the random board and the random board menu 
			break;
		case 3:
				// Return to restart from the top
			return 1;
		case 4:
				// Return to quit the game
			return 0;	// Let the calling function handle the quitting
		}
		return 2;
	}
}
