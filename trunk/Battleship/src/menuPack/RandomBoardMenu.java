/**
 * 
 */
package menuPack;

import java.util.Random;
import Gameplay.Carrier;
import Gameplay.Battleship;
import Gameplay.Cruiser;
import Gameplay.Submarine;
import Gameplay.PatrolBoat;
import Gameplay.Ship;
import Gameplay.Player;

/**
 * @author Steve
 * 
 */
public class RandomBoardMenu extends Menu {

	static int whichInput = 0;

	String whichShip;
	String coordinate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see menuPack.Menu#printMenu()
	 */
	 public void PrintMenu() {
		// DISPLAY BOARDS!!!!!
		whichInput=0;
		Ship temp = new Ship();
		Random generator = new Random();
		int randomIndex = generator.nextInt();
		while (!myBoard.carrier.placed || !myBoard.battleship.placed
				|| !myBoard.cruiser.placed || !myBoard.submarine.placed
				|| !myBoard.patrolboat.placed) {

			whichInput++;

			whichShip = String.valueOf(whichInput);
			
			int direction=0;

			boolean placed = false;
			while (!placed) {
				randomIndex = generator.nextInt(10);
				coordinate = indexToLetter(randomIndex);
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
				
				placed = validateShipPlacement(temp, coordinate, direction);

				if (placed) {
					System.out.println("Success");
					placeShip(temp, coordinate, direction);
					
				} else {
					System.out
							.println("Invalid coordinate, please enter another");
				}
			}

			
		}
	}

	// private boolean placeShipMenu(Ship temp, String coordinate, String
	// direction) {

	//}
}
