/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import Gameplay.Battleship;
import Gameplay.Carrier;
import Gameplay.Cruiser;
import Gameplay.PatrolBoat;
import Gameplay.Player;
import Gameplay.Ship;
import Gameplay.Submarine;

import Networking.*;


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
	}

	public int Input(Player player, CustomBoardMenu custom_board, RandomBoardMenu random_board){
		// block until the user gives appropriate input
		while (!getInput(1, 4)) {
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
		
		boolean done = false;
		switch(choice){
		case 1:
			while(!done){
				// Display the board custom board menu
				player.DisplayMyBoard();
				custom_board.PrintMenu();
				done = custom_board.Input(player);
			}
				// When done... send board to opponent

			break;
		case 2:
			player.PlaceRandomBoard();
			while(!done){
				// Display the board custom board menu
				player.DisplayMyBoard();
				random_board.PrintMenu();
				done = random_board.Input(player);
			}	
			break;
		case 3:
				// Return to restart from the top
			return 1;	// Let the calling function handle the restarting
		case 4:
				// Return to quit the game
			return 0;	// Let the calling function handle the quitting
		}
		return 2;		// Indicates the input handling occured in the menu class
	}
}