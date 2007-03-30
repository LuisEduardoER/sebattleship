/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Networking.BattleshipClient;
import Networking.BattleshipServer;

/**
 * @author Steve
 *
 */
public class StartMenu extends Menu {

	public void PrintMenu() {
		System.out.println("1) Host Game");
		System.out.println("2) Join Game");
		System.out.println("3) Quit");
		System.out.print("user> ");
	}
	

	/**
	 * Gets and handles the user input from the menu.  Receives as parameters all objects
	 * which need to be accessed as a result of a menu choice selection.
	 * 
	 * @param server a BattleshipServer object to initialize if 'Host Game' is chosen
	 * @param client a BattleshipCleint object to initialize if 'Join Game' is chosen
	 * @return returns true if the menu option was handled by function.
	 * 		   returns false if the menu option was not handled by function.
	 * 				Quit should be the only menu option not handled in function.
	 */
	public boolean Input(BattleshipServer server, BattleshipClient client){
		// block until the user gives appropriate input
		for (getInput(); !check(1, 3);) {
			System.out.println("Invalid Input: " + choice);
			System.out.print("user> ");
			getInput();
		}
		
		// Must handle all messages in here
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String name=null;
		String server_ip=null;	
		int server_port=7777;
		
		// NEED TO DO:  Implement error checking on all inputs
		// NEED TO DO
		// NEED TO DO
		// NEED TO DO
		switch(choice){
		case 1:
			System.out.println("Enter your name: ");
			try {
				name = stdin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Enter the server port: ");
			try {
				server_port = Integer.parseInt(stdin.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			server = new BattleshipServer(server_port, name);
			break;
		case 2:
			System.out.println("Enter your name: ");
			try {
				name = stdin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Enter the server IP: ");
			try {
				server_ip = stdin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("Enter the server port: ");
			try {
				server_port = Integer.parseInt(stdin.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			client = new BattleshipClient(server_ip, server_port, name);
			break;
		case 3:
			return false;	// Let the calling function handle the quitting
		}
		return true;
	}

	//I'm assuming i then need to know how to return choice to some other
	//function... I wonder which one it is!
}
