/**
 * 
 */
package menuPack;

import java.io.IOException;

import Gameplay.Battleship;
import Gameplay.Carrier;
import Gameplay.Cruiser;
import Gameplay.PatrolBoat;
import Gameplay.Player;
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
		
			System.out.println("1) Place/Adjust Carrier");
			System.out.println("2) Place/Adjust Battleship");
			System.out.println("3) Place/Adjust Cruiser");
			System.out.println("4) Place/Adjust Submarine");
			System.out.println("5) Place/Adjust Patrol Boat");
			System.out.println("6) Done");
			System.out.println("7) Cancel");
			System.out.println();
			System.out.println("\nuser> ");
	}
	
	public boolean Input(Player player){

			while(!getInput(1,6)){
				System.out.println("Invalid Input: " + choice);
				System.out.print("user> ");
			}
		
			whichShip = choice;
			Ship temp = new Ship();
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
			case 6:
				return true;
			}
			
			int direction=0;
					
				// Get the ship location information
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


				placed = player.validateShipPlacement(temp, coord, direction);

				if (placed) {
					System.out.println("Success");
				} else {
					System.out
							.println("Invalid placement");
					player.myBoard.Display_Board();
					if(coord.length()>2)
						System.out.println("Format input invalid.  Enter coordinate with letter then number (i.e. B2)");
				}
			}
				// Place the ship
			player.placeShip(temp, coord, direction);
			return false;
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
