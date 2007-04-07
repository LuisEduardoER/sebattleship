/**
 * 
 */
package menuPack;

import java.io.IOException;
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

	int whichShip;
	String coord;

	
	 public void PrintMenu() {
			
			System.out.println("1) Place/Adjust Aircraft Carrier");
			System.out.println("2) Place/Adjust Battleship");
			System.out.println("3) Place/Adjust Cruiser");
			System.out.println("4) Place/Adjust Submarine");
			System.out.println("5) Place/Adjust Patrol Boat");
			System.out.println("6) Place/Reset the Board");
			System.out.println("7) Random Again");
			System.out.println("8) Done");
			System.out.println();
			System.out.println("\nuser> ");
	}

	public boolean Input(Player player){

		while(!getInput(1,8)){
			//System.out.println("Invalid Input: " + choice);  error message sent out in GetInput function
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
			player.myBoard.resetAllShips();
			break;
		case 7:
			player.myBoard.resetAllShips();
			player.PlaceRandomBoard();
			break;
		case 8:
			if(player.myBoard.carrier.placed && player.myBoard.battleship.placed && player.myBoard.cruiser.placed
					&& player.myBoard.submarine.placed && player.myBoard.patrolboat.placed){
			return true;
			} else {
				System.out.println("You must place all ships before you can continue.");
				System.out.println();
				break;
			}
			
		}
		
		int direction=0;
				
			// Get the ship location information
		boolean placed = false;
		while (!placed && choice<6) {
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
				System.out.println();
//				 Place the ship
				player.placeShip(temp, coord, direction);
			} else {
				System.out.println("Invalid placement");
				System.out.println();
				player.myBoard.Display_Board();
				if(coord.length()>2)
					System.out.println("Format input invalid.  Enter coordinate with letter then number (i.e. B2)");
			}
		}
			
		return false;
	}
		
	public void GetCoord(){
		try{
		coord=getString();
		}catch(IOException ioe){
			coord="INVALID COMMAND";
		}
	}
}
