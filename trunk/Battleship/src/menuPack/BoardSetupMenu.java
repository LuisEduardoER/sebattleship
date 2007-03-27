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
	
	void PrintMenu() {
		System.out.println("1) Make your own board");
		System.out.println("1) Generate a random board");
		System.out.println("1) Quit");
		System.out.println();
		System.out.println("user> ");
		
		for(;getInput(););
		System.out.println();
	}
	
	//	I'm assuming i then need to know how to return choice to some other
		//function... I wonder which one it is!

}
