/**
 * 
 */
package menuPack;

import java.io.IOException;
import java.util.Random;

import Gameplay.Player;

/**
 * @author Steve
 * 
 */

// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHH,
// YOU KILLED IT AAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHH...
// ahem, fine, i'll just write it again... thanks though ;P
// private boolean placeShipMenu(Ship temp, String coordinate, String
// direction) {
// }
// This is an exact copy of the CustomBoardMenu class, except it generates
// random input in place of user input, and the only prompt is to ask
// the user what they wish to do with the completed board
public class RandomBoardMenu extends Menu {

	private Random random = new Random();

	private int whichShip;

	private String coord;

	/*
	 * (non-Javadoc)
	 * 
	 * @see menuPack.Menu#printMenu()
	 */
	public int PrintMenu(Player player) {

		boolean liked = false;

		// Until the player likes what they see
		while (!liked) {
			// while not all the ships are placed
			while (!player.myBoard.carrier.placed
					|| !player.myBoard.battleship.placed
					|| !player.myBoard.cruiser.placed
					|| !player.myBoard.submarine.placed
					|| !player.myBoard.patrolboat.placed) {
				// This randomly picks which ship we will place
				whichShip = random.nextInt(5) + 1;

				boolean placed = false;
				while (!placed) {
					// This randomly generates a coordinate and direction
					coord = String.valueOf(player.indexToLetter(random
							.nextInt(10) + 1))
							+ String.valueOf(random.nextInt(10) + 1);
					int direction = random.nextInt(4) + 1;

					switch (whichShip) {
					case 1:
						placed = player.validateShipPlacement(
								player.myBoard.carrier, coord, direction);
					case 2:
						placed = player.validateShipPlacement(
								player.myBoard.battleship, coord, direction);
					case 3:
						placed = player.validateShipPlacement(
								player.myBoard.cruiser, coord, direction);
					case 4:
						placed = player.validateShipPlacement(
								player.myBoard.submarine, coord, direction);
					case 5:
						placed = player.validateShipPlacement(
								player.myBoard.patrolboat, coord, direction);
					case 6:// unreachable
					case 7:// unreachable
					}

					// I set placed in validateShipPlacement
					if (placed) {
						switch (whichShip) {
						case 1:
							player.placeShip(player.myBoard.carrier, coord,
									direction);
						case 2:
							player.placeShip(player.myBoard.battleship, coord,
									direction);
						case 3:
							player.placeShip(player.myBoard.cruiser, coord,
									direction);
						case 4:
							player.placeShip(player.myBoard.submarine, coord,
									direction);
						case 5:
							player.placeShip(player.myBoard.patrolboat, coord,
									direction);
						}
						System.out.println("Success");
					} else {
						System.out.println("Invalid placement");
						player.myBoard.Display_Board();
						if (coord.length() > 2)
							System.out
									.println("Format input invalid.  Enter coordinate with letter then number (i.e. B2)");
					}
				}
			}

			System.out.println("Is this fine, or would you like to remake it?");
			System.out.println("1) It's fine  ");
			System.out.println(" 2) Do it again   ");
			System.out.println("3) Cancel");
			System.out.println("4) Quit");
			System.out.println("user> ");
			getInput(1, 4);
			System.out.println();

			switch (choice) {
			case 1:
				return 1;
			case 2:
				player.myBoard.resetAllShips();
				break;
			case 3:
				player.myBoard.resetAllShips();
				return 2;
			case 4:
				player.myBoard.resetAllShips();
				return 0;
			}

		}
		return 0; //just in case
	}

	// This works. For some reason the getString in my Menu base class won't
	// work
	public void GetCoord() {
		try {
			coord = getString();
		} catch (IOException ioe) {
			coord = "INVALID COMMAND";
		}
	}

}
