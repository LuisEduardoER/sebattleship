/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Networking.ThreadedReceiver;
import Utilities.Console;

import Gameplay.Player;
import Gameplay.Board;
import Gameplay.My_Board;
import Gameplay.Ship;

/**
 * @author   Steve
 */
public abstract class Menu {
	public Console display = new Console();
	protected int choice;

	abstract String[] PrintMenu();

	/**
	 * Depracated, also built into the getInput function
	 * @param min
	 * @param max
	 * @return True if the choice in the current Menu object is in the given range
	 */
	boolean check(int min, int max) {
		if (choice < min)
			return false;
		if (choice > max)
			return false;
		return true;
	}

	// open up standard input
	/**
	 * open up standard input
	 */ 
	protected boolean getInput(ThreadedReceiver listener) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = 0;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			choice = Integer.parseInt(br.readLine());
			return true;
		} catch (IOException ioe) {
			if(listener.error)
				return true;
			display.scroll("Invalid Command");
			display.printScreen();
			return false;
		}
		catch(NumberFormatException nfe){
			if(listener.error)
				return true;
			display.scroll("Input Invalid");
			display.printScreen();
			return false;
		}
	}
	
	/**
	 * Depracated, use the getInput that uses range parameters
	 * @return True if input is received
	 */
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
			display.scroll("Invalid Command");
			display.printScreen();
			return false;
		}
		catch(NumberFormatException nfe){
			display.scroll("Input Invalid");
			display.printScreen();
			return false;
		}
	}
	
	/**
	 * Gets a string from the user
	 * 
	 * @return the string
	 * @throws IOException
	 */
	protected String getString()throws IOException{
			String input="";
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			
			input = in.readLine();
			
			return input;
		}
	
	// open up standard input
	/**
	 * Gets an integer input from the user.  Must be within the given range
	 * @param min Min in range
	 * @param max Max in range
	 * @return True if the choice in the current Menu object is in the given range
	 */
	protected boolean getInput(int min, int max) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = 0;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			choice = Integer.parseInt(br.readLine());
			for (; !check(min, max);){
				display.scroll("Not a Valid Choice");  
				display.printScreen();
				choice = Integer.parseInt(br.readLine());
				return false;
			}

			return true;
		} catch (IOException ioe) {
			display.scroll("Invalid Command");
			display.printScreen();
			return false;
		}  catch (NumberFormatException nfe ){
			display.scroll("Invalid Command");
			display.printScreen();
			return false;
		}
		
	}

}

		
		
