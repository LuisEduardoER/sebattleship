/**
 * 
 */
package menuPack;

/**
 * @author Steve
 *
 */
public class StartMenu extends Menu {

	public void PrintMenu() {
		System.out.println("1) Host Game");
		System.out.println("2) Join Game");
		System.out.println("3) Quit");
	}
	

	public int Input(){
		// block until the user gives appropriate input
		for (getInput(); !check(1, 3);) {
			getInput();
		}
		return choice;
	}

	//I'm assuming i then need to know how to return choice to some other
	//function... I wonder which one it is!
}
