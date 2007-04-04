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
			boolean done = false;
			while(!done){
				// Display the board custom board menu
				player.DisplayMyBoard();
				custom_board.PrintMenu();
				done = custom_board.Input(player);
			}
			break;
		case 2:
				// Ideally all this should be placed in a function for a function call
			
			String whichShip;
			String coordinate;
			
				// Display the random board and the random board menu 
			// DISPLAY BOARDS!!!!!
			int whichInput=0;
			Ship temp = new Ship();
			Random generator = new Random();
			int randomIndex = generator.nextInt();
			while (!player.myBoard.carrier.placed || !player.myBoard.battleship.placed
					|| !player.myBoard.cruiser.placed || !player.myBoard.submarine.placed
					|| !player.myBoard.patrolboat.placed) {

				whichInput++;

				whichShip = String.valueOf(whichInput);
				
				int direction=0;

				boolean placed = false;
				while (!placed) {
					randomIndex = generator.nextInt(10);
					coordinate = player.indexToLetter(randomIndex);
					coordinate += String.valueOf(generator.nextInt(10));
					direction = generator.nextInt(4) + 1;
					
					
					
					
					switch (Integer.parseInt(whichShip)) {
					case 1:
						temp = new Carrier();
						temp.name="Carrier";
						break;
					case 2:
						temp = new Battleship();
						temp.name="Battleship";
						break;
					case 3:
						temp = new Cruiser();
						temp.name="Cruiser";
						break;
					case 4:
						temp = new Submarine();
						temp.name="Submarine";
						break;
					case 5:
						temp = new PatrolBoat();
						temp.name="Patrol Boat";
						break;
					} 
					
					placed = player.validateShipPlacement(temp, coordinate, direction);

					if (placed) {
						System.out.println("Success");
						player.placeShip(temp, coordinate, direction);
						
					} else {
						System.out
								.println("Invalid coordinate, please enter another");
					}
				}

				
			}
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
