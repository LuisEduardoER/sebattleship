
package Gameplay;

import Networking.*;
import Utilities.Console;
import Utilities.Sound;

import java.io.*;
import java.util.Random;

/**
 * @author   Nathan & Quinn
 */
public class Player {
	public Console display = new Console();
	public boolean host=false;
	public boolean isTurn;
	public boolean board_sent = false;
	public boolean board_received = false;
	public boolean victory=false;
	public boolean opponent_victory=false;
	public My_Board myBoard = new My_Board();
	public Opponent_Board hisBoard = new Opponent_Board();
	private String attack_coord;
	protected int xcoor;
	protected int ycoor;
	public boolean messaging=false;
	public boolean turn_change=false;
	
	
	/**
	 * Player class constructor
	 */
	public Player(){
		xcoor=0;
		ycoor=0;
		attack_coord="";
		isTurn=false;
	}
	
	/**
	 *  Gets the input.  Doesn't handle the IOException.
	 *  
	 */
	public String Get_Input()throws IOException{
		String input="";
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		
		input = in.readLine();
		
		return input;
	}
	
	/**
	 * My_Turn is the code that is ran when the 
	 * game is in play and then calls His_Turn to 
	 * continue the game play.
	 * 
	 * @return true if the turn was successful, false if there was a problem
	 * 			sending the coordinates
	 */
	public boolean My_Turn(BattleshipServer server, BattleshipClient client) {
			display.putStaticLine("");	
			display.putStaticLine("Enter the position you want to Attack (i.e. A2) ");
			display.printScreen();
			display.printPrompt("user> ");
			try{
			attack_coord=Get_Input();
			}catch(IOException ioe){
				attack_coord="INVALID COMMAND";
			}
		while(!Validate_Input(attack_coord)){
			display.printPrompt("user> ");
	//		display.putStaticLine("");	
	//		display.putStaticLine("Enter the position you want to Attack (i.e. A2) ");
			try{
				attack_coord=Get_Input();
				}catch(IOException ioe){
					attack_coord="INVALID COMMAND";
				}
		}
		
		try {
			/*
			 * send coordinates with C in front to indicate they are coordinate
			 */
			if(server!=null)
				server.Send("C" + attack_coord); 
			else if(client!=null)
				client.Send("C" + attack_coord);
		} catch (Exception e) {
			System.err.println("Error with connection");
			return false;
		}		
		/*
		 * We now have a valid guess and have sent it to the opponent.  Update the Display
		 */
		
		boolean hit=false;
		
		/*
		 * update hit_history
		 */
		hisBoard.hit_history[xcoor][ycoor]=1;     
			// Check for sunk ships and update the board accordingly.

		
		if(hisBoard.hit_or_miss(xcoor, ycoor)){
			hit=true;
			Sound.playSound("res/explosion.wav");
		}
		else{
			Sound.playSound("res/Missile_Incoming_FlyBy.wav");
		}
		
		String sunk=hisBoard.check_sunk();
		if(sunk!="nothing"){
			Sound.playSound("res/bubbles.wav");
			if(hisBoard.check_all_sunk()){
				victory=true;
			}
		}
			// Now that all the calculations are done, print the display.
		display.clearScreen();
		this.Display_Boards();
		display.putStaticLine("");
		if(hit)
			display.putStaticLine(attack_coord + "... Hit!");
		else
			display.putStaticLine(attack_coord + "... Miss");
		display.putStaticLine("");
		if(sunk!="nothing")
			display.putStaticLine("You sunk his " + sunk);  //not sure of syntax on this
		display.putStaticLine("");
		if(victory)
			display.putStaticLine("You WIN!!!!!!");
		
		
			// It's not my turn anymore
		hit=false;
		this.isTurn=false;
		return true;
}

	/**
	 * His_Turn is the code ran for the opponents turn
	 * @param the coordinate the opponent attacked
	 */
	public void His_Turn(String opponent_coord){

		/*
		 * will get xcoor and ycoor
		 * NOTE:  We don't need to validate, it should be already validate
		 *        we only need this function to parse the xcoor and ycoor
		 */
		Validate_Input(opponent_coord);
		
		/*
		 * update hit_history
		 */
		myBoard.hit_history[xcoor][ycoor]=1;  

		boolean hit=false;
		
		display.putStaticLine("");
		if(myBoard.hit_or_miss(xcoor, ycoor)){
			Sound.playSound("res/explosion.wav");
			hit=true;
		}
		else{
			Sound.playSound("res/Missile_Incoming_FlyBy.wav");
		}

		
		// Check if a ship was sunk and update the board accordingly
		String sunk=myBoard.check_sunk();
		if(sunk!="nothing"){
			Sound.playSound("res/bubbles.wav");
			if(myBoard.check_all_sunk()){
				opponent_victory=true;
			}
		}
		
			// Handle all the output/display after all the calculation...
		display.clearScreen();
		this.Display_Boards();
		display.putStaticLine("");
		if(hit)
			display.putStaticLine("Opponent attacked " + opponent_coord+"... " + "Hit!");
		else
			display.putStaticLine("Opponent attacked " + opponent_coord+"... " + "Miss");
		if(sunk!="nothing"){
			display.putStaticLine("");
			display.putStaticLine("He sunk your " + sunk); 
		}
		if(opponent_victory){
			display.putStaticLine("");
			display.putStaticLine("You LOSE!!!!!!");
		}
		
		display.putStaticLine("");
		display.putStaticLine("Press Enter to continue with you turn...");
		display.printScreen();
		display.printPrompt("user> ");
		
		hit=false;
		turn_change=true;
		// now it's my turn again
		this.isTurn=true;
	}
	
	
	/**
	 * Returns true if the coordinate entered is a legal move.  
	 * 
	 * For the coordinate to be a legal move it must be:
	 * 1. in the grid
	 * 2. be in the correct format
	 * 3. not have already been attacked
	 * 
	 * @param attack_coord
	 * @return whether or not the attack_coord is a valid move
	 */
	public boolean Validate_Input(String attack_coord){
		attack_coord=attack_coord.trim();   //deletes any unecessary whitespace
		if(attack_coord.length()>3) {   //format incorrect
			display.scroll("Input string too long to be proper coordinate");
			display.printScreen();
			return false;
		}
		if(attack_coord.length()<=1){
			display.scroll("Input string too short to be proper coordinate");
			display.printScreen();
			return false;
		}
		// Convert the string inputs into integers
		String x_as_string = attack_coord.substring(1);
		ycoor = letterToIndex(attack_coord.charAt(0));
		
		
		try{
			xcoor=Integer.parseInt(x_as_string);
			xcoor--;                            //to match the xcoor with the array position
		} catch (NumberFormatException nfe) {
	         display.scroll("Incorrect Format.  Enter with letter and then number i.e. B3");
	         display.printScreen();
	         return false;
		}

		
		

		if(!hisBoard.in_Grid(xcoor, ycoor)){
			display.scroll("The Coordinate input is not within (A-J) or (1-10)");
			display.printScreen();
			return false;
		}
		if(hisBoard.already_attacked(xcoor,ycoor)){
			display.scroll("Coordinate has already been attacked");
			display.printScreen();		
			return false;
		}
		return true;
	}
	
	/**
	 * Displays the opponent board and the myboard.
	 * uses the myboard and opponentboard methods to display line-by-line.  
	 * 
	 */
	public void Display_Boards(){
		for(int i=0; i<13; i++){
			display.putStaticLine(myBoard.Display(i)+"       "+hisBoard.Display(i));
		}
	}

	
	public String[] DisplayMyBoard(){
		String board[]=new String[12];
		for(int i=0; i<12; i++){
			board[i]=myBoard.Display(i);
		}
		return board;
	}
	
	/**
	 * @return  the myBoard
	 * @uml.property  name="myBoard"
	 */
	public My_Board getMyBoard() {
		return myBoard;
	}

	/**
	 * @param myBoard the myBoard to set
	 */
	// I don't think we'll ever need to set myBoard?
	// The only board we'll be receiving as a whole board
	//  will be the opponent board, right?
	public void setOppBoard(Opponent_Board hisBoard) {
		this.hisBoard = hisBoard;
		if(host)
			this.isTurn=true;		
	}
	
	
	public String MyBoardToString(){
		return myBoard.ToString();
	}
	
	
	public void StringToOppBoard(String str){
		hisBoard.StringToOppBoard(str);
			// Once we've received their board, it is our turn,
			//  if we're the host.
		if(this.host)
			this.isTurn=true;
		this.board_received=true;
	}
	
	
	
		// Brought in from the Menu class
		//  Needs to be parsed and integrated
	
	
	/**
	 * Converts a letter of a coordinate to the corresponding integer
	 * @param letter
	 * @return integer
	 */
	public int letterToIndex(char letter) {
		if (Character.toLowerCase(letter) == 'a')
			return 0;
		else if (Character.toLowerCase(letter) == 'b')
			return 1;
		else if (Character.toLowerCase(letter) == 'c')
			return 2;
		else if (Character.toLowerCase(letter) == 'd')
			return 3;
		else if (Character.toLowerCase(letter) == 'e')
			return 4;
		else if (Character.toLowerCase(letter) == 'f')
			return 5;
		else if (Character.toLowerCase(letter) == 'g')
			return 6;
		else if (Character.toLowerCase(letter) == 'h')
			return 7;
		else if (Character.toLowerCase(letter) == 'i')
			return 8;
		else if (Character.toLowerCase(letter) == 'j')
			return 9;
		else
			return -1;
	}
	
	/**
	 * converts the given integer into a character for the coordinate system
	 * @param index
	 * @return the letter corresponding to the index
	 */
	public String indexToLetter(int index) {
		if (index == 0)
			return "a";
		else if (index == 1)
			return "b";
		else if (index == 2)
			return "c";
		else if (index == 3)
			return "d";
		else if (index == 4)
			return "e";
		else if (index == 5)
			return "f";
		else if (index == 6)
			return "g";
		else if (index == 7)
			return "h";
		else if (index == 8)
			return "i";
		else if (index == 9)
			return "j";
		else
			return null;
	}
	
	/**
	 * checks to see if the given coordinate is occupied
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOccupied(int x, int y){
		if(myBoard.carrier.get_position(x, y))
			return true;
		if(myBoard.battleship.get_position(x, y))
			return true;
		if(myBoard.cruiser.get_position(x, y))
			return true;
		if(myBoard.submarine.get_position(x, y))
			return true;
		if(myBoard.patrolboat.get_position(x, y))
			return true;
		
		return false;
	}
	
	/**
	 * Places a ship on the board
	 * @param thisShip
	 * @param coordinate
	 * @param direction
	 */
	public void placeShip(Ship thisShip, String coordinate, int direction){
		thisShip.placed = true;
		int letterCoord = letterToIndex(coordinate.charAt(0));
		int numberCoord = Integer.parseInt(coordinate.substring(1))-1;
		
		
			if (direction == 1) {
				for (int i = 0; i < thisShip.getSize(); i++) {
						thisShip.set_position(numberCoord+i, letterCoord);
					}
			} else if (direction == 2) {
				for (int i = 0; i < thisShip.getSize(); i++) {
					thisShip.set_position(numberCoord, letterCoord+i);
					}
			} else if (direction == 3) {
				for (int i = 0; i < thisShip.getSize(); i++) {
					thisShip.set_position(numberCoord-i, letterCoord);
				}
			} else if (direction == 4) {
				for (int i = 0; i < thisShip.getSize(); i++) {
					thisShip.set_position(numberCoord, letterCoord - i);
				}
			}
			
		if(thisShip.name == "Carrier"){
			myBoard.carrier.placed=thisShip.placed;
			myBoard.carrier.position=thisShip.position;
			}
			
		else if(thisShip.name == "Battleship"){
			myBoard.battleship.placed=thisShip.placed;
			myBoard.battleship.position=thisShip.position;
		}
		else if(thisShip.name == "Cruiser"){
			myBoard.cruiser.placed=thisShip.placed;
			myBoard.cruiser.position=thisShip.position;
		}
		else if(thisShip.name == "Submarine"){
			myBoard.submarine.placed=thisShip.placed;
			myBoard.submarine.position=thisShip.position;
		}
		else if(thisShip.name == "Patrol Boat"){
				myBoard.patrolboat.placed=thisShip.placed;
				myBoard.patrolboat.position=thisShip.position;
		}
			
		}

	/**
	 * Checks to see if a given ship can be positioned at the given coordinate
	 * @param thisShip
	 * @param coordinate
	 * @param direction
	 * @return
	 */
	public boolean validateShipPlacement(Ship thisShip, String coordinate,
			int direction) {
		int letterCoord = letterToIndex(coordinate.charAt(0));
		int numberCoord;
		try{
		numberCoord = Integer.parseInt(coordinate.substring(1))-1;
		}catch (NumberFormatException nfe){
			return false;
		}
		if (thisShip.placed == true)
			return false;
		else {
			if (myBoard.in_Grid(numberCoord, letterCoord)) {
				if (direction == 1) {   //left to right
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(numberCoord+i, letterCoord) == false
								|| isOccupied(numberCoord+i, letterCoord)) {
							return false;
						}
					}
				} else if (direction == 2) {    //top to bottom
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(numberCoord, letterCoord+i) == false
								|| isOccupied(numberCoord, letterCoord+i)) {
							return false;
						}
					}
				} else if (direction == 3) {   //right to left
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(numberCoord-i, letterCoord ) == false
								|| isOccupied(numberCoord-i, letterCoord )) {
							return false;
						}
					}
				} else if (direction == 4) {   //bottom to top
					for (int i = 0; i < thisShip.getSize(); i++) {
						if (myBoard
								.in_Grid(numberCoord, letterCoord-i) == false
								|| isOccupied(numberCoord, letterCoord-i)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Creates a random board for the user
	 *
	 */
	public void PlaceRandomBoard(){

		String whichShip;
		String coordinate;
		
			// Display the random board and the random board menu 
		// DISPLAY BOARDS!!!!!
		int whichInput=0;
		Ship temp = new Ship();
		Random generator = new Random();
		int randomIndex = generator.nextInt();
		while (!this.myBoard.carrier.placed || !this.myBoard.battleship.placed
				|| !this.myBoard.cruiser.placed || !this.myBoard.submarine.placed
				|| !this.myBoard.patrolboat.placed) {

			whichInput++;

			whichShip = String.valueOf(whichInput);
			
			int direction=0;

			boolean placed = false;
			while (!placed) {
				randomIndex = generator.nextInt(10);
				coordinate = this.indexToLetter(randomIndex);
				coordinate += String.valueOf(generator.nextInt(10)+1);
				direction = generator.nextInt(4) + 1;
				
				
				switch (Integer.parseInt(whichShip)) {
				case 1:
					temp = new Carrier();
					temp.name="Carrier";
					break;
				case 2:
					temp = new Battleship();
					temp.name="Battleship";
					break;
				case 3:
					temp = new Cruiser();
					temp.name="Cruiser";
					break;
				case 4:
					temp = new Submarine();
					temp.name="Submarine";
					break;
				case 5:
					temp = new PatrolBoat();
					temp.name="Patrol Boat";
					break;
				} 
				
				placed = this.validateShipPlacement(temp, coordinate, direction);

				if (placed) {
	//				System.out.println("Success");
					this.placeShip(temp, coordinate, direction);
					
				} else {
	//				display.scroll("Invalid coordinate, please enter another");
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

