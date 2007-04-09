/**
 * 
 */
package menuPack;

import java.io.IOException;

import Utilities.Console;

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
	public Console display = new Console();
	int whichShip;
	String coord;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see menuPack.Menu#printMenu()
	 */
	public String[] PrintMenu() {
		String menu[]=new String[7];
		
			menu[0]="1) Place/Adjust Aircraft Carrier";
			menu[1]="2) Place/Adjust Battleship";
			menu[2]="3) Place/Adjust Cruiser";
			menu[3]="4) Place/Adjust Submarine";
			menu[4]="5) Place/Adjust Patrol Boat";
			menu[5]="6) Reset the Board";
			menu[6]="7) Done";

		return menu;
	}
	
	public boolean Input(Player player){

		for(getInput();!check(1,7);) {
			//display.scroll("Invalid Input: " + choice);
			display.printScreen();
			display.printPrompt("user> ");
			getInput();
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
				if(player.myBoard.carrier.placed && player.myBoard.battleship.placed && player.myBoard.cruiser.placed
						&& player.myBoard.submarine.placed && player.myBoard.patrolboat.placed){
				return true;
				} else {
					display.scroll("You must place all ships before you can continue.");
					display.printScreen();
					break;
				}
				
			}
			
			int direction=0;
					
				// Get the ship location information
			boolean placed = false;
			while (!placed && choice<6) {
				display.putStaticLine("");
				display.putStaticLine("Please enter the start coordinate:");
				display.printScreen();
				display.printPrompt("user> ");
				GetCoord();
				display.putStaticLine("");
				display.putStaticLine("Please enter the direction");
				display.putStaticLine("1) Right   2) Down   3) Left   4) Up");
				display.printScreen();
				display.printPrompt("user> ");
				getInput();
				direction = choice;


				placed = player.validateShipPlacement(temp, coord, direction);

				if (placed) {
	//				System.out.println("Success");
					player.placeShip(temp, coord, direction); // Place the ship
				} else {
					display.scroll("Invalid placement");
					display.printScreen();
					player.myBoard.Display_Board();
					if(coord.length()>2){
						display.scroll("Format input invalid.  Enter coordinate with letter then number (i.e. B2)");
						display.printScreen();
					}
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
	
	
	// private boolean placeShipMenu(Ship temp, String coordinate, String
	// direction) {

	//}
}
