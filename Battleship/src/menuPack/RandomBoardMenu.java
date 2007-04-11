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
import Networking.ThreadedReceiver;

/**
 * @author Steve
 * 
 */
public class RandomBoardMenu extends Menu {
	
	int whichShip;
	String coord;

	
	 public String[] PrintMenu() {
			String menu[] = new String[8];
		 	menu[0]="1) Place/Adjust Aircraft Carrier";
		 	menu[1]="2) Place/Adjust Battleship";
		 	menu[2]="3) Place/Adjust Cruiser";
		 	menu[3]="4) Place/Adjust Submarine";
		 	menu[4]="5) Place/Adjust Patrol Boat";
		 	menu[5]="6) Place/Reset the Board";
		 	menu[6]="7) Random Again";
		 	menu[7]="8) Done";
		 	return menu;
	}

	public int Input(Player player,ThreadedReceiver listener){
		for(getInput(listener);!check(1,8) && !(listener.error);) {
			display.scroll("Invalid Input: " + choice);
			display.printScreen();
			display.printPrompt("user> ");
			getInput(listener);
		}
		if(listener.error)
			return 0;
		
	
		whichShip = choice;
		Ship temp = new Ship();
		switch (whichShip) {
		case 1:
			temp = new Carrier();
			temp.name="Carrier";
			player.myBoard.carrier.Reset();
			break;
		case 2:
			temp = new Battleship();
			temp.name="Battleship";
			player.myBoard.battleship.Reset();
			break;
		case 3:
			temp = new Cruiser();
			temp.name="Cruiser";
			player.myBoard.cruiser.Reset();
			break;
		case 4:
			temp = new Submarine();
			temp.name="Submarine";
			player.myBoard.submarine.Reset();
			break;
		case 5:
			temp = new PatrolBoat();
			temp.name="Patrol Boat";
			player.myBoard.patrolboat.Reset();
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
			return 1;
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
			display.clearScreen();
			String brd[]=player.DisplayMyBoard();
			String rndmbrd[]=this.PrintMenu();
			
			display.putStaticLine(brd[0]);
			display.putStaticLine(brd[1]);
			display.putStaticLine(brd[2]+ "   " + rndmbrd[0]);
			display.putStaticLine(brd[3]+ "   " + rndmbrd[1]);
			display.putStaticLine(brd[4]+ "   " + rndmbrd[2]);
			display.putStaticLine(brd[5]+ "   " + rndmbrd[3]);
			display.putStaticLine(brd[6]+ "   " + rndmbrd[4]);
			display.putStaticLine(brd[7]+ "   " + rndmbrd[5]);
			display.putStaticLine(brd[8]+ "   " + rndmbrd[6]);
			display.putStaticLine(brd[9]+ "   " + rndmbrd[7]);
			display.putStaticLine(brd[10]);
			display.putStaticLine(brd[11]);
			
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
			getInput(listener);
			direction = choice;


			placed = player.validateShipPlacement(temp, coord, direction);

			if (placed) {
//				 Place the ship
				player.placeShip(temp, coord, direction);
			} else {
				display.scroll("Invalid placement");
				display.printScreen();
				if(coord.length()>2){
					display.scroll("Format input invalid.  Enter coordinate with letter then number (i.e. B2)");
					display.printScreen();
				}
			}
		}
			
		return 2;
	}
		
	public void GetCoord(){
		try{
		coord=getString();
		}catch(IOException ioe){
			coord="INVALID COMMAND";
		}
	}
}
