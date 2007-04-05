/**
 * 
 */
package menuPack;

import java.io.IOException;

import Gameplay.Player;

/**
 * @author Steve
 * 
 */
public class CustomBoardMenu extends Menu {

	private int whichShip;

	private String coord;

	/*
	 * (non-Javadoc)
	 * 
	 * @see menuPack.Menu#printMenu()
	 */
	public int PrintMenu(Player player) {

		while (!player.myBoard.carrier.placed
				|| !player.myBoard.battleship.placed
				|| !player.myBoard.cruiser.placed
				|| !player.myBoard.submarine.placed
				|| !player.myBoard.patrolboat.placed) {
			System.out.println("1) Place/Adjust Carrier");
			System.out.println("2) Place/Adjust Battleship");
			System.out.println("3) Place/Adjust Cruiser");
			System.out.println("4) Place/Adjust Submarine");
			System.out.println("5) Place/Adjust Patrol Boat");
			System.out.println("6) Cancel");
			System.out.println("7) Quit");
			System.out.println();
			System.out.println("\nuser> ");

			whichShip = getInput(1, 7);

			if (choice == 6){
				player.myBoard.resetAllShips();
				return 2;
			}
			if (choice == 7){
				player.myBoard.resetAllShips();
				return 0;
			}

			int direction = 0;
			// Get the ship location information
			boolean placed = false;
			while (!placed) {
				player.DisplayMyBoard();
				System.out.println("Please enter the start coordinate:");
				System.out.println("user> ");
				// This get's parsed later
				GetCoord();// that works, for some reason it won't use my
							// menu's getString()
				System.out.println();

				System.out.println("Please enter the direction");
				System.out.println("1) Right   2) Down   3) Left   4) Up");
				System.out.println("user> ");
				getInput(1, 4);
				direction = choice;
				System.out.println();

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
		return 1;
	}

	public void GetCoord() {
		try {
			coord = getString();
		} catch (IOException ioe) {
			coord = "INVALID COMMAND";
		}
	}

	// private boolean placeShipMenu(Ship temp, String coordinate, String
	// direction) {

	//}
}
