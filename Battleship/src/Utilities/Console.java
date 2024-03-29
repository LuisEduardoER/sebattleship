package Utilities;
/*
    Console.java

	Copyright (c) 2002, by the University of Washington, Tacoma,
	Institute of Technology, All Rights Reserved.

	Permission to use this program or derivatives thereof for educational
	purposes is freely given.  Usage should include the copyright of the
	University of Washington.  This software cannot be used for commercial
	purposes, including for-profit educational instruction.


    This class provides a set of quick and easy console (keyboard and
    screen) utilities.  The screen is 24 rows by 80 columns with the
    upper lefthand corner being 0,0.  Keyboard entry is handled only in
    line 25.

    Keyboard entry includes, integer, double and Strings.  These can
    be done with, or without a prompt string.

    Screen output can be (roughly) formatted.  The class maintains an
    internal cursor that keeps track of the location of where the next
    character will be printed.  The cursor is changed using the gotoXY()
    method or is reset to 0,0 by the clearScreen() method.  It is also
    set to the next character position by any of the put*() methods.

    At present, only single characters or Strings can be printed to
    the screen (no ints or doubles except as formatted to String objects).


*/

import java.io.*;

/**
The Console is a simple interface for keyboard input and screen output.
This "device" allows a programmer to write code for simple
text-mode, user interfaces.
<p>
The 'screen' is composed of a 25 row by 80 column matrix of character locations.
It is divided into two basic sections.  Row 25 is reserved for input.
When input is requested using one of the read*() methods, any data entered
by the user appears on this line.  The input may be optionally preceded
by a prompt string.
<p>
There is an internal cursor that keeps track of the location, in the first
(top) 24 rows where characters may be written.  The screen coordiates
run from 0 to 24 (for rows) and 0 to 79 (for columns).  The normal
convention is to indicate the row number first (first parameter in most
method calls) but the gotoXY() method reverses this order for historical
reasons.  A program can format the upper screen area in any way desired,
for example to produce menus or text-based graphics.
<p>

@author George Mobus & Bill Conlen
@version 1.0
*/
public class Console {

	/**
	 * Contains the number of rows the console will manage.  This should be 
	 * 1 less than the total screen resolution to allow a row at the bottom
	 * for the prompt.
	 */
	public static final int ROWS = 29; // -1 for the prompt placement at the bottom

	/**
	 * Contians the number of columns the console will manage.  This should be
	 * 1 less than the total screen width to prevent the console from auto returning
	 * after a character has been printed on the 79th column.  Thus, the 79th column
	 * is always left blank.
	 */
	public static final int COLS = 79;	// allow a buffer of one so the prompt doesn't auto return
	
	/**
	 * Contains the number of rows to reserver at the bottom of the console for the 
	 * chat box.
	 */
	public static final int CHATSIZE = 7;

	/**
	 * Contains the character array that represents the screen.
	 */
	private static char[][] screen;
	
	/**
	 * Contains a blank screen for printing a clean screen.
	 */
	private static char[][] blankscreen;
	
	/**
	 * Holds the current row of the cursor.
	 */
	private static int cursorRow = 0;
	
	/**
	 * Holds the current column of the cursor.
	 */
	private static int cursorCol = 0;

/**
Constructing a Console device creates a screen, 24 rows by 80 columns
of text (ASCII characters).  The screen is initially cleared.
*/
	public Console ()
	{
		screen = new char[ROWS][COLS];
		blankscreen = new char[ROWS][COLS];
		initScreen();
	}

/**
Sets up the screen for display. A special, internal cursor location is
set to 0,0 (upper left-hand corner of screen).  This cursor will be
used to determine the location where the next character will be printed
unless one of the put***At() methods is used.  In the latter case, the
cursor location will be updated to the coordinates immediately following
the text that was printed using the put***At() method.

See also: gotoXY
*/
	public void initScreen()
	{
		for (int row=0; row<=ROWS-CHATSIZE; row++)
			for (int col=0; col<COLS; col++)
				blankscreen[row][col] = screen[row][col] = ' ';
		cursorRow = 0;
		cursorCol = 0;
	}

/**
This method is called to display the current contents of the screen.
It must be called after using any methods which change the contents of
the screen, in order for those changes to show up on the monitor device.
For example, one might call the putStringAt() method several times to
put formatted text onto the screen, then call printScreen() to display
the results on the monitor.
<PRE>
	Console c = new Console();

	c.putStringAt("Hello World!", 4, 18);
	c.putStringAt("How are you?", 5, 18);
	c.printScreen();
</PRE>
*/
	public void printScreen ()
	{
			// Allow some buffer space, just in case
		System.out.println();
		System.out.println();
		System.out.println();
		String line = "";
		for (int row = 0; row < ROWS; row++) {
			line = "";
			for (int col=0; col<COLS; col++) {
				line += screen[row][col];
			}
			System.out.println(line);
		}
	}

/**
Clears all characters from the screen and prints the cleared screen to
the monitor.
*/
	public void clearScreen ()
	{
		initScreen();
		for (int i=0;i<25;i++) System.out.println();
	}

	
	/**
	 * Prints a clean screen without clearing the internally stored screen.
	 */
	public void printClean(){
		for(int i=0; i<25; i++)
			System.out.println();
	}
/**
Allows the placement of a single ASCII (ANSI) character at any screen location
desired. The character will not show up on the monitor until the next
call to printScreen() has occurred.
*/
	public void putCharAt(char c, int row, int col)
	{
		if (row < 0 || row > (ROWS-1)) return;
		if (col < 0 || col > (COLS-1)) return;
		screen[row][col] = c;
		// reset cursor
		cursorCol = ++col;
		cursorRow = row;
		if (cursorCol == COLS) {
			cursorCol = 0;
			cursorRow++;
			if (cursorRow == ROWS) cursorRow = 0;
		}
	}

/**
Puts a character in the location determined by the internal cursor.  For
example if you use the gotoXY() method to place the cursor, and then call
this method, the character will be printed at the coordinates specified in
the gotoXY() parameters.
<PRE>
	c.gotoXY(22, 5);  \\ put cursor at the coordinates 5, 22
	putChar('|');      \\
</PRE>
*/
	public void putChar(char c)
	{
		screen[cursorRow][cursorCol++] = c;
		if (cursorCol == COLS) {
			cursorCol = 0;
			cursorRow++;
			if (cursorRow == ROWS) cursorRow = 0;
		}
	}

/**
Allows the placement of a String at any screen location
desired. The String will not show up on the monitor until the next
call to printScreen() has occurred. Any string that runs past the 80th
column will be continued on the next row from column 0.  The screen
cursor will be updated accordingly.
*/
	public void putStringAt(String s, int row, int col)
	{
		for (int i=0; i<s.length(); i++)
			putCharAt(s.charAt(i), row, col++);
	}

/**
Places a String on the screen starting at the current screen cursor
location.  To place a string at a specific location use the gotoXY()
method to set the cursor, or use the putStringAt() method.
*/
	public void putString(String s)
	{
		putStringAt(s,cursorRow, cursorCol);
	}

	/**
	 * Displays the first 80 characters of s on the next available line
	 * 
	 * @param s	a string containing the text to print on the next line
	 */
	public void putStaticLine(String s){	
			// Start on this row, at col 0
		cursorCol=0;
		for(int i=0; i<COLS; i++){
			if(i<s.length())
				this.putChar(s.charAt(i));
			else
				this.putChar(' ');			
		}
		// putChar at the 79th column takes care of incrementing the row
		
		if(cursorRow >ROWS-CHATSIZE)
			cursorRow=ROWS-CHATSIZE;
	}
	
	
	/**
	 * Prints a string on the bottom of the chat box at the bottom.  All
	 * lines above it are automatically scrolled up.
	 * 
	 * @param s	a string containing the text to add to the scrolling chat box.
	 */
	public void scroll(String s){
		int x,y;
		String temp;
		// run x from ROWS-CHATSIZE+1 to ROWS-1
		//  eg, 21 to 23
		for(x=ROWS-CHATSIZE+1; x<ROWS; x++){
			// run y from 0 to COLS-1
			for(y=0; y<COLS; y++){
				this.putCharAt(screen[x][y], x-1,y);
				this.putCharAt(' ', x, y);
			}
		}
		this.putStringAt(s,ROWS-1,0);
		
	}
	
	
/**
Sets the screen cursor to locations indicated by <i>x</i> and <i>y</i> parameters.
The <i>x</i> parameter is the column number; the <i>y</i> parameter is the
row number.  Note that the order of these parameters is reversed from the
other screen method calls that specify row first.  This is due to historical
reasons, the gotoXY function is used by a number of different systems and
is provided here for consistancy.  If the x or y parameters are outside
the range of row and column values (0 to 24 and 0 to 79 respectively)
the method does not change the screen cursor position.  Subsequent
calls to put*() methods will not result in correct placement of the char
or string arguments.
*/
	public void gotoXY (int x, int y)
	{
		if (x < 0 || x > (COLS-1)) return;
		if (y < 0 || y > (ROWS-1)) return;
		cursorRow = y;
		cursorCol = x;
	}

	//public char getCharAt(int row, int col)

/**
Prints a prompt message to the console input row (row 25).  To print a
prompt message to some other portion of the screen you will need to
use the putString or putStringAt methods.
*/
   public static void printPrompt(String prompt)
   {  System.out.print(prompt + " ");
      System.out.flush();
   }

/**
Reads a string input from the console input row (row 25) typed by the
user.  This version does not print a preceding prompt message.
*/
   public static String readLine()
   {  int ch;
      String line = "";
      boolean done = false;
      while (!done)
      {  try
         {  ch = System.in.read();
            if (ch < 0 || (char)ch == '\n')
               done = true;
            else if ((char)ch != '\r')
               line = line + (char) ch;
         }
         catch(IOException e)
         {  done = true;
         }
      }
      return line;
   }

/**
Similar to readLine() but prints a prompt message on the input row (row 25)
prior to waiting for user input.
*/
   public static String readLine(String prompt)
   {  printPrompt(prompt);
      return readLine();
   }

/**
Reads an integer value input on the input row (row 25).  If anything
other than an integer is entered, the method notifies the user of the
error and waits for an integer to be entered.
*/
   public static int readInt(String prompt)
   {  while(true)
      {  printPrompt(prompt);
         try
         {  return Integer.valueOf
               (readLine().trim()).intValue();
         } catch(NumberFormatException e)
         {  System.out.println
               ("Not an integer. Please try again!");
         }
      }
   }

   
/**
Reads a double value input on the input row (row 25).  If anything
other than a double or an integer is entered, the method notifies the
user of the error and waits for a number to be entered.
*/
   public static double readDouble(String prompt)
   {  while(true)
      {  printPrompt(prompt);
         try
         {  return Double.parseDouble(readLine().trim());
         } catch(NumberFormatException e)
         {  System.out.println
         ("Not a floating point number. Please try again!");
         }
      }
   }

   public static void main (String[] args)
   {
	   Console c = new Console();

	   c.clearScreen();
//	   c.gotoXY(20, 6);
//	   c.putString("Hello world");
//	   c.printScreen();
//	   String a = c.readLine("Press enter: ");
//	   c.clearScreen();
	   c.putStringAt("Welcome to ...",1,0);
	   c.putStringAt("  SCROLLING TEXTINATOR!!  ",2,0);
	   c.putStringAt("Type text and watch it scroll up to: ".concat("7").concat(" lines."),4,0);
	   c.printScreen();
	   //c.initScreen();
	   //c.printScreen();
	   while(true){
		   c.printScreen();   
		   String ans = c.readLine("Text: ");
		   c.scroll(ans);		   
	   }

   }
}