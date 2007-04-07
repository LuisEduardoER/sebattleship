/**
 * 
 */
package menuPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gameplay.Player;

/**
 * @author Steve
 * 
 */
public abstract class Menu {

	protected int choice;

	abstract int PrintMenu(Player player);

	boolean check(int min, int max) {
		if (choice < min)
			return false;
		if (choice > max)
			return false;
		return true;
	}

	
	//Think of this as depracated, i won't maintain it.
	//I don't know if anyone uses it, though, so it can stay
	//Use the getInput with the int ranges below
	public boolean getInput() {
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
		} catch (NumberFormatException nfe) {
			System.out.println("Input Invalid");
			return false;
		}
	}


	
	
	protected String getString() throws IOException {
		String input = "";
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);

		input = in.readLine();

		return input;
	}

	// open up standard input
	protected int getInput(int min, int max) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		choice = min - 1;

		// read the username from the command-line; need to use try/catch with
		// the
		// readLine() method
		while (!check(min, max)) {
			try {
				choice = Integer.parseInt(br.readLine());
				for (; !check(min, max);) {
					System.out.println("Not a Valid Choice - " + max
							+ " to quit");
					System.out.print("user>");
					choice = Integer.parseInt(br.readLine());
				}

				return choice;
			} catch (IOException ioe) {
				System.out.println("Invalid Command");
				return max; // This will quit the program on an exception (hopefully)
			}
		}
		return choice;
	}
}
