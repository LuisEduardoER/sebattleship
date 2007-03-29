/**
 * 
 */
package menuPack;

/**
 * @author Steve
 *
 */
public class StartMenu extends Menu {

	void PrintMenu() {
		System.out.println("1) Host Game");
		System.out.println("2) Join Game");
		System.out.println("3) Quit");
		System.out.println();
		System.out.print("user> ");

		for (getInput(); check(1, 3);) {
			getInput();
		}
		System.out.println();
	}

	//I'm assuming i then need to know how to return choice to some other
	//function... I wonder which one it is!
}
