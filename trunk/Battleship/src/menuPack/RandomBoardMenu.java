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

	/*
	 * (non-Javadoc)
	 * 
	 * @see menuPack.Menu#printMenu()
	 */
	void PrintMenu() {
		// DISPLAY BOARDS!!!!!
		Random generator = new Random();
		int randomIndex = generator.nextInt();
		
		while (!Player.myBoard.carrier.placed
				&& !Player.myBoard.battleship.placed
				&& !Player.myBoard.cruiser.placed
				&& !Player.myBoard.submarine.placed
				&& !Player.myBoard.patrolboat.placed) {
			
			whichInput = generator.nextInt(5) + 1;
			
			whichShip = String.valueOf(whichInput);
			Ship temp = new Ship();
			String coordinate = new String();
			String direction = new String();

			boolean placed = false;
			while (!placed) {
				randomIndex = generator.nextInt( 10 );
				coordinate = indexToLetter(randomIndex);
				coordinate += String.valueOf(generator.nextInt());
				
				direction = String.valueOf(generator.nextInt(4) + 1);

				temp = new Ship();
				switch (Integer.parseInt(whichShip)) {
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

				placed = validateShipPlacement(temp, coordinate, direction);

				if (placed) {
					System.out.println("Success");
				} else {
					System.out
							.println("Invalid coordinate, please enter another");
				}
			}

			placeShip(temp, coordinate, direction);
		}
	}

	// private boolean placeShipMenu(Ship temp, String coordinate, String
	// direction) {

	//}
}
