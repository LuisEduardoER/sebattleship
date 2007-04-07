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
		catch(NumberFormatException nfe){
			System.out.println("Input Invalid");
			return false;
		}
	}
	
	protected String getString()throws IOException{
			String input="";
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			
			input = in.readLine();
			
			return input;
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

}

		
		
