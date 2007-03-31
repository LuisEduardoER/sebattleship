/**
 * 
 */
package menuPack;

import java.io.IOException;

import Gameplay.Battleship;
import Gameplay.Carrier;
import Gameplay.Cruiser;
import Gameplay.PatrolBoat;
import Gameplay.Ship;
import Gameplay.Submarine;

/**
 * @author Steve
 * 
 */
public class CustomBoardMenu extends Menu {

	int whichShip;
	String coord;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see menuPack.Menu#printMenu()
	 */
	public void  PrintMenu() {
		
		while (!myBoard.carrier.placed
				|| !myBoard.battleship.placed
				|| !myBoard.cruiser.placed
				|| !myBoard.submarine.placed
				|| !myBoard.patrolboat.placed) {
			myBoard.Display_Board();
			System.out.println("1) Place Carrier");
			System.out.println("2) Place Battleship");
			System.out.println("3) Place Cruiser");
			System.out.println("4) Place Submarine");
			System.out.println("5) Place Patrol Boat");
			System.out.println("6) Quit");
			System.out.println();
			System.out.println("\nuser> ");

			for (getInput(); !check(1, 6);)
				getInput();
			whichShip = choice;
			Ship temp = new Ship();
			int direction=0;

			boolean placed = false;
			while (!placed) {
				System.out.println("Please enter the start coordinate:");
				System.out.println("user> ");
				GetCoord();
				System.out.println();

				System.out.println("Please enter the direction");
				System.out.println("1) Right   2) Down   3) Left   4) Up");
				System.out.println("user> ");
				getInput();
				direction = choice;
				System.out.println();

				temp = new Ship();
				switch (whichShip) {
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

				placed = validateShipPlacement(temp, coord, direction);

				if (placed) {
					System.out.println("Success");
				} else {
					System.out
							.println("Invalid placement");
					myBoard.Display_Board();
					if(coord.length()>2)
						System.out.println("Format input invalid.  Enter coordinate with letter then number (i.e. B2)");
				}
			}

			placeShip(temp, coord, direction);
		}
	}

	
	public void GetCoord(){
		try{
		coord=getString();
		}catch(IOException ioe){
			coord="INVALID COMMAND";
		}
	}
	
	
	// private boolean placeShipMenu(Ship temp, String coordinate, String
	// direction) {

	//}
}
