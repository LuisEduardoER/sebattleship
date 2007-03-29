/**
 * 
 */
package menuPack;

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
	void PrintMenu() {
		// DISPLAY BOARDS!!!!!
		while (myBoard.carrier.placed
				&& myBoard.battleship.placed
				&& myBoard.cruiser.placed
				&& myBoard.submarine.placed
				&& myBoard.patrolboat.placed) {
			System.out.println("1) Place Carrier");
			System.out.println("2) Place Battleship");
			System.out.println("3) Place Cruiser");
			System.out.println("4) Place Submarine");
			System.out.println("5) Place Patrol Boat");
			System.out.println("6) Quit");
			System.out.println();
			System.out.println("\nuser> ");

			for (getInput(); check(1, 6);)
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
				System.out.println("1) Up   2) Right   3) Down   4) Left");
				System.out.println("user> ");
				getInput();
				direction = choice;
				System.out.println();

				temp = new Ship();
				switch (whichShip) {
				case 1:
					temp = new Carrier();
					break;
				case 2:
					temp = new Battleship();
					break;
				case 3:
					temp = new Cruiser();
					break;
				case 4:
					temp = new Submarine();
					break;
				case 5:
					temp = new PatrolBoat();
					break;
				}

				placed = validateShipPlacement(temp, coord, direction);

				if (placed) {
					System.out.println("Success");
				} else {
					System.out
							.println("Invalid coordinate, please enter another");
				}
			}

			placeShip(temp, coord, direction);
		}
	}

	
	public void GetCoord(){
		
	}
	
	
	// private boolean placeShipMenu(Ship temp, String coordinate, String
	// direction) {

	//}
}
