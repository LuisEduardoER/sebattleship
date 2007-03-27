/**
 * 
 */
package menuPack;

import Gameplay.My_Board;
import Gameplay.Ship;

/**
 * @author Steve
 *
 */
public class BoardSetupMenu extends Menu {

	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */
	public My_Board myBoard;
	
	void PrintMenu() {
		System.out.println("1) Make your own board");
		System.out.println("1) Generate a random board");
		System.out.println("1) Quit");
		System.out.println();
		System.out.println("user> ");
		
		for(;getInput(););
		System.out.println();
	}
	
	public boolean placeShip(Ship thisShip, 
			String coordinate, String direction){
		int letterCoord = letterToIndex(coordinate.charAt(0));
		int numberCoord = Integer.parseInt(coordinate.substring(1,1));
		if (myBoard.in_Grid(letterCoord, numberCoord)) {
			if (direction == "1") {
				for (int i = 0; i < thisShip.getSize(); i++) {
					if (myBoard.in_Grid(letterCoord, numberCoord + i) == false){
						return false;
					}
				}
			} else if (direction == "2") {
				for(int i=0; i < thisShip.getSize(); i++){
					if(myBoard.in_Grid(letterCoord + i, numberCoord) == false){
						return false;
					}
				}
			} else if (direction == "3")  {
				for(int i=0; i < thisShip.getSize(); i++){
					if(myBoard.in_Grid(letterCoord, numberCoord - i) == false){
						return false;
					}
				}
			} else if (direction == "4")  {
				for(int i=0; i < thisShip.getSize(); i++){
					if(myBoard.in_Grid(letterCoord - i, numberCoord) == false){
						return false;
					}
				}
			}
			}
		return true;
		}
	
	public int letterToIndex(char letter) {
		if (Character.toLowerCase(letter) == 'a')
			return 0;
		else if (Character.toLowerCase(letter) == 'b')
			return 1;
		else if (Character.toLowerCase(letter) == 'c')
			return 2;
		else if (Character.toLowerCase(letter) == 'd')
			return 3;
		else if (Character.toLowerCase(letter) == 'e')
			return 4;
		else if (Character.toLowerCase(letter) == 'f')
			return 5;
		else if (Character.toLowerCase(letter) == 'g')
			return 6;
		else if (Character.toLowerCase(letter) == 'h')
			return 7;
		else if (Character.toLowerCase(letter) == 'i')
			return 8;
		else if (Character.toLowerCase(letter) == 'j')
			return 9;
		else
			return -1;
	}
	
	//	I'm assuming i then need to know how to return choice to some other
		//function... I wonder which one it is!

}
