/**
 * 
 */
package menuPack;

import Gameplay.Ship;

/**
 * @author Steve
 *
 */
public class RandomBoardMenu extends Menu {

	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */
	void PrintMenu() {		
		//DISPLAY BOARDS!!!!!
	}
	
		
	private void placeShipMenu(Ship thisShip) {
		boolean placed = false;
		while(!placed)
		{
		System.out.println("Please enter the start coordinate:");
		System.out.println("user> ");
		String coordinate = getInput();
		System.out.println();
		
		System.out.println("Please enter the direction");
		System.out.println("1) Up   2) Right   3) Down   4) Left");
		System.out.println("user> ");
		String direction = getInput();
		System.out.println();
		
		placed = placeShip(thisShip, coordinate, direction);
		}
	}
}
