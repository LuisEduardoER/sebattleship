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
import Utilities.Console;


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
	public Console display = new Console();
	
	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */
	
	public String[] PrintMenu() {
		String menu[]=new String[4];

		menu[0]="1) Make your own board";
		menu[1]="2) Generate a random board";
		menu[2]="3) Cancel";		
		menu[3]="4) Quit";
		
		return menu;
	}

	public int Input(Player player, CustomBoardMenu custom_board, RandomBoardMenu random_board,ThreadedReceiver listener){
		// block until the user gives appropriate input
		for(getInput(listener);!check(1,4) && !(listener.error);) {
			//display.scroll("Invalid Input: " + choice);  redundant error message
			display.printScreen();
			display.printPrompt("user> ");
			getInput(listener);
		}
		if(listener.error)
			return 1;
		
		// Must handle all messages in here
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		
		// NEED TO DO:  Implement error checking on all inputs
		// NEED TO DO
		// NEED TO DO
		// NEED TO DO		
		
		int done = 2;
		switch(choice){
		case 1:
			while(done == 2){
				// Display the board custom board menu
				display.clearScreen();
				
				String brd[]=player.DisplayMyBoard();
				String cstmbrd[]=custom_board.PrintMenu();
				display.putStaticLine(brd[0]);
				display.putStaticLine(brd[1]);
				display.putStaticLine(brd[2]);
				display.putStaticLine(brd[3]+ "   " + cstmbrd[0]);
				display.putStaticLine(brd[4]+ "   " + cstmbrd[1]);
				display.putStaticLine(brd[5]+ "   " + cstmbrd[2]);
				display.putStaticLine(brd[6]+ "   " + cstmbrd[3]);
				display.putStaticLine(brd[7]+ "   " + cstmbrd[4]);
				display.putStaticLine(brd[8]+ "   " + cstmbrd[5]);
				display.putStaticLine(brd[9]+ "   " + cstmbrd[6]);
				display.putStaticLine(brd[10]);
				display.putStaticLine(brd[11]);
				
				display.printScreen();
				display.printPrompt("user> ");
				done = custom_board.Input(player,listener);
			}
				// When done... send board to opponent
			if(done==0)	// an error occured, there was likely a disconnect
				return 1;
			
			// if we get to here, done == 1 and that means it was sucessful
			break;
		case 2:
			player.PlaceRandomBoard();
			while(done==2){
				display.clearScreen();
				// Display the board custom board menu
				String brd[]=player.DisplayMyBoard();
				String rndmbrd[]=random_board.PrintMenu();
				
				display.putStaticLine(brd[0]);
				display.putStaticLine(brd[1]);
				display.putStaticLine(brd[2]+ "   " + rndmbrd[0]);
				display.putStaticLine(brd[3]+ "   " + rndmbrd[1]);
				display.putStaticLine(brd[4]+ "   " + rndmbrd[2]);
				display.putStaticLine(brd[5]+ "   " + rndmbrd[3]);
				display.putStaticLine(brd[6]+ "   " + rndmbrd[4]);
				display.putStaticLine(brd[7]+ "   " + rndmbrd[5]);
				display.putStaticLine(brd[8]+ "   " + rndmbrd[6]);
				display.putStaticLine(brd[9]+ "   " + rndmbrd[7]);
				display.putStaticLine(brd[10]);
				display.putStaticLine(brd[11]);
				
				display.printScreen();
				display.printPrompt("user> ");
				
				done = random_board.Input(player,listener);
			}	
			if(done==0)
				return 1;
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
