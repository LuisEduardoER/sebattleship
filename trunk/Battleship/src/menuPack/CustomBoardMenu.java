/**
 * 
 */
package menuPack;

import Gameplay.Ship;

/**
 * @author Steve
 *
 */
public class CustomBoardMenu extends Menu {

	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */
	void PrintMenu() {		
		//DISPLAY BOARDS!!!!!
		
		System.out.println("1) Place Carrier");
		System.out.println("2) Place Battleship");
		System.out.println("3) Place Cruiser");
		System.out.println("4) Place Submarine");
		System.out.println("5) Place Patrol Boat");
		System.out.println("6) Quit");
		System.out.println();
		System.out.println("\nuser> ");
		
		for(getInput(); check(1, 6);)
			getInput();
		System.out.println();
	}
	
	private void placeShipMenu(Ship thisShip) {
		boolean placed = false;
		while(!placed)
		{
		System.out.println("Please enter the start coordinate:");
		System.out.println("user> ");
		getInput();
		String coordinate = choice;
		System.out.println();
		
		System.out.println("Please enter the direction");
		System.out.println("1) Up   2) Right   3) Down   4) Left");
		System.out.println("user> ");
		getInput();
		String direction = choice;
		System.out.println();
		
		placed = placeShip(thisShip, coordinate, direction);
		}
	}
}
