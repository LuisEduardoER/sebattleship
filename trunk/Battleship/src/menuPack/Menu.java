/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Steve
 * 
 */
public abstract class Menu {

	protected String choice;

	abstract void PrintMenu();

	boolean check(int min, int max) {
		if (Integer.parseInt(choice.substring(1, 1)) < min)
			return false;
		if (Integer.parseInt(choice.substring(1, 1)) > max)
			return false;
		return true;
	}

	// open up standard input
	protected boolean getInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = null;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			choice = br.readLine();
			return true;
		} catch (IOException ioe) {
			System.out.println("Invalid Command");
			return false;
		}
	}
	
	// open up standard input
	protected boolean getInput(int min, int max) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = null;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		try {
			choice = br.readLine();
			for(; check(min,max);)
				choice = br.readLine();
			return true;
		} catch (IOException ioe) {
			System.out.println("Invalid Command");
			return false;
		}
	}
	
	

}
