/**
 * 
 */
package menuPack;

/*
import Gameplay.Player;
import Gameplay.Ship;
import Gameplay.My_Board;
import Gameplay.Board;
*/

/**
 * @author Steve
 *
 */
public class BoardSetupMenu extends Menu {

	
	/* (non-Javadoc)
	 * @see menuPack.Menu#printMenu()
	 */
	
	public void PrintMenu() {
		System.out.println("1) Make your own board");
		System.out.println("2) Generate a random board");
		System.out.println("3) Cancel");		
		System.out.println("4) Quit");
		System.out.println();
		System.out.print("user> ");
		
		for(getInput();check(1, 4);){
			System.out.println("Invalid command");
			System.out.print("user> ");
			getInput();
		}
			
		System.out.println();
	}
	
	//	I'm assuming i then need to know how to return choice to some other
		//function... I wonder which one it is!

}
