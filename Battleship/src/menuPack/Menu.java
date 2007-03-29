/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gameplay.Player;
import Gameplay.Board;
import Gameplay.My_Board;
import Gameplay.Ship;

/**
 * @author Steve
 * 
 */
public abstract class Menu {

	protected My_Board myBoard = new My_Board();
	
	protected int choice;

	abstract void PrintMenu();

	boolean check(int min, int max) {
		if (choice < min)
			return false;
		if (choice > max)
			return false;
		return true;
	}

	// open up standard input
	protected boolean getInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = 0;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			choice = Integer.parseInt(br.readLine());
			return true;
		} catch (IOException ioe) {
			System.out.println("Invalid Command");
			return false;
		}
	}

	// open up standard input
	protected boolean getInput(int min, int max) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = 0;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			choice = Integer.parseInt(br.readLine());
			for (; !check(min, max);){
				System.out.println("Not a Valid Choice");
				choice = Integer.parseInt(br.readLine());			
			}

			return true;
		} catch (IOException ioe) {
			System.out.println("Invalid Command");
			return false;
		}
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
	
	public String indexToLetter(int index) {
		if (Character.toLowerCase(index) == 0)
			return "a";
		else if (Character.toLowerCase(index) == 1)
			return "b";
		else if (Character.toLowerCase(index) == 2)
			return "c";
		else if (Character.toLowerCase(index) == 3)
			return "d";
		else if (Character.toLowerCase(index) == 4)
			return "e";
		else if (Character.toLowerCase(index) == 5)
			return "f";
		else if (Character.toLowerCase(index) == 6)
			return "g";
		else if (Character.toLowerCase(index) == 7)
			return "h";
		else if (Character.toLowerCase(index) == 8)
			return "i";
		else if (Character.toLowerCase(index) == 9)
			return "j";
		else
			return null;
	}
	
	public boolean isOccupied(int x, int y){
		if(myBoard.carrier.get_position(x, y))
			return true;
		if(myBoard.battleship.get_position(x, y))
			return true;
		if(myBoard.cruiser.get_position(x, y))
			return true;
		if(myBoard.submarine.get_position(x, y))
			return true;
		if(myBoard.patrolboat.get_position(x, y))
			return true;
		
		return false;
	}

	public void placeShip(Ship thisShip, String coordinate, int direction){
		thisShip.placed = true;
		int letterCoord = letterToIndex(coordinate.charAt(0));
		int numberCoord = Integer.parseInt(coordinate.substring(1, 1));
		
			if (direction == 1) {
				for (int i = 0; i < thisShip.getSize(); i++) {
						thisShip.set_position(letterCoord, numberCoord + i);
					}
			} else if (direction == 2) {
				for (int i = 0; i < thisShip.getSize(); i++) {
					thisShip.set_position(letterCoord + i, numberCoord);
					}
			} else if (direction == 3) {
				for (int i = 0; i < thisShip.getSize(); i++) {
					thisShip.set_position(letterCoord, numberCoord - i);
				}
			} else if (direction == 4) {
				for (int i = 0; i < thisShip.getSize(); i++) {
					thisShip.set_position(letterCoord - i, numberCoord);
				}
			}
		}

	public boolean validateShipPlacement(Ship thisShip, String coordinate,
			int direction) {
		int letterCoord = letterToIndex(coordinate.charAt(0));
		int numberCoord = Integer.parseInt(coordinate.substring(1, 1));

		if (thisShip.placed = true)
			return false;
		else {
			if (myBoard.in_Grid(letterCoord, numberCoord)) {
				if (direction == 1) {
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(letterCoord, numberCoord + i) == false
								|| isOccupied(letterCoord, numberCoord)) {
							return false;
						}
					}
				} else if (direction == 2) {
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(letterCoord + i, numberCoord) == false
								|| isOccupied(letterCoord, numberCoord)) {
							return false;
						}
					}
				} else if (direction == 3) {
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(letterCoord, numberCoord - i) == false
								|| isOccupied(letterCoord, numberCoord)) {
							return false;
						}
					}
				} else if (direction == 4) {
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(letterCoord - i, numberCoord) == false
								|| isOccupied(letterCoord, numberCoord)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * @return the myBoard
	 */
	public My_Board getMyBoard() {
		return myBoard;
	}

	/**
	 * @param myBoard the myBoard to set
	 */
	public void setMyBoard(My_Board myBoard) {
		this.myBoard = myBoard;
	}
}
