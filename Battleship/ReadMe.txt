###################################################################################


					Battleship


				   Software Engineering
					 CS 2450
			

Group 4: Conner Egbert, Nate Folkner, Quinn Jackson, Steve Lyon, Josh Templin
###################################################################################


How to compile from source:
---------------------------

	- Open a prompt and navigate to the root "Battleship" folder

		ie. cd "C:\Workspace\Battleship"

	- From the root "Battleship" folder, type the following command:

		javac -sourcepath src src\BattleshipGame.java -d bin
	 
	   This will build all the .class files from the .java files needed
		to compile BattleshipGame.java.  All source files will
		be placed in the Battleship\bin directory and will maintain
		the same filestructure as the .java files.
	
	* NOTE:  This requires that the directory containing javac.exe be
		registered in the system PATH environment variable.  If it is
		not, then the previous command will return a system message:

		"'javac' is not recognized as an internal or external command,
		operable program or batch file."

		To solve this problem, make sure you have a Java jdk installed.
		If you do, it will be located at "C:\Program Files\Java\jdkx.y.z_r",
		   where x, y, z, and r are revision numbers.
		If you do not, then you will need to download the latest jdk from
		
			java.sun.com

		Once this is done, type:

			set PATH=%PATH%;C:\Program Files\Java\jdkx.y.z_r\bin
			
			where x, y, z, and r are the revision numbers of the
			directory created from your jdk download.
		
		This will add the folder containing javac.exe to the path so that
		  it can be called by simply typing javac.

------------------------------------------------------------------------------------

How to run Battleship:
----------------------

	- Open a prompt and navigate to the root "Battleship" folder 

		ie. cd "C:\Workspace\Battleship"

	- From the Battleship folder, type the following command:

		java -jar Battleship.jar

	This will launch Battleship in your console.

------------------------------------------------------------------------------------

How to Generate the JavaDoc Documentation:
------------------------------------------

	- Open a prompt and navigate to the root "Battleship" folder
	
		ie. cd "C:\Workspace\Battleship"

	- From the Battleship folder, type the following command:
	
		Doxygen

		*Note: This requires that Doxygen be downloaded and installed on the 
			local computer.  If it is not, it will need to be downloaded.

	- The documentation will be generated and placed inside the doc/ folder

To view the HTML Documentation:
-------------------------------
	
	- With the prompt located at the root "Battleship" folder, type

		Documentation

		*Note: This requires Mozilla Firefox to be installed.

	- This will execute a batch file that will launch the index.html page in Firefox.

		
	- If Firefox is not available, then manually launch the index.html file located in:

		Battleship/doc/html/index.html

------------------------------------------------------------------------------------
	
