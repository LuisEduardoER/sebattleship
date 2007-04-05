
package Gameplay;

import Networking.*;
import java.io.*;

/**
 * @author Nathan & Quinn
 *
 */
public class Player {
	
	public boolean host=false;
	public boolean isTurn;
	private boolean victory;
	public My_Board myBoard = new My_Board();
	public Opponent_Board hisBoard = new Opponent_Board();
	private String attack_coord;
	protected int xcoor;
	protected int ycoor;
	
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
			
			
			
			System.out.println("Enter the position you want to Attack (i.e. A2) ");
			try{
			attack_coord=Get_Input();
			}catch(IOException ioe){
				attack_coord="INVALID COMMAND";
			}
		while(!Validate_Input(attack_coord)){
			System.out.println("Enter the position you want to Attack (i.e. A2");
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
		 * update hit_history
		 */
		hisBoard.hit_history[xcoor][ycoor]=1;   
		
		
		if(hisBoard.hit_or_miss(xcoor, ycoor))
			System.out.println("Hit!");
		else
			System.out.println("Miss");
		
		String sunk=hisBoard.check_sunk();
		if(sunk!="nothing"){
			System.out.println("You sunk his " + sunk);  //not sure of syntax on this
			if(hisBoard.check_all_sunk()){
				System.out.println("You WIN!!!!!!");
				victory=true;
			}
		}
		
			// It's not my turn anymore
		this.isTurn=false;
		System.out.println();
		Display_Boards();     //so player can see where they were just attacked
		System.out.println("                Waiting for Opponent's Move....");
		return true;
}

	/*
	 * His_Turn is the code ran for the opponents turn
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
		System.out.println();
		System.out.println();
		
		System.out.println("Opponent attacked " + opponent_coord+"...");
		if(myBoard.hit_or_miss(xcoor, ycoor))
			System.out.println("Hit!");
		else
			System.out.println("Miss");
		
		String sunk=myBoard.check_sunk();
		if(sunk!="nothing"){
			System.out.println("He sunk your " + sunk);  //not sure of syntax on this
			if(hisBoard.check_all_sunk()){
				System.out.println("You LOSE!!!!!!");
				victory=false;
			}
		}
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
	 */
	public boolean Validate_Input(String attack_coord){
		attack_coord=attack_coord.trim();   //deletes any unecessary whitespace
		if(attack_coord.length()>3) {   //format incorrect
			System.out.println("Input string too long to be proper coordinate");
			return false;
		}
		
		
		// Convert the string inputs into integers
		String x_as_string = attack_coord.substring(1);
		ycoor = letterToIndex(attack_coord.charAt(0));
		
		
		try{
			xcoor=Integer.parseInt(x_as_string);
			xcoor--;                            //to match the xcoor with the array position
		} catch (NumberFormatException nfe) {
	         System.out.println("Incorrect Format.  Enter with letter and then number i.e. B3");
	         return false;
		}

		
		

		if(!hisBoard.in_Grid(xcoor, ycoor)){
			System.out.println("The Coordinate input is not within (A-J) or (1-10)");
			return false;
		}
		if(hisBoard.already_attacked(xcoor,ycoor)){
			System.out.println("Coordinate has already been attacked");
		
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
		for(int i=0; i<12; i++){
			System.out.println(myBoard.Display(i)+"       "+hisBoard.Display(i));
		}
	}

	
	public void DisplayMyBoard(){
		for(int i=0; i<12; i++){
			System.out.println(myBoard.Display(i));
		}
	}
	
	/**
	 * @return the myBoard
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
	}
	
	
	
		// Brought in from the Menu class
		//  Needs to be parsed and integrated
	
	

	public int letterToIndex(char letter) {
		int base = 1;
		
		if (Character.toLowerCase(letter) == 'a')
			return base;
		else if (Character.toLowerCase(letter) == 'b')
			return base + 1;
		else if (Character.toLowerCase(letter) == 'c')
			return base + 2;
		else if (Character.toLowerCase(letter) == 'd')
			return base + 3;
		else if (Character.toLowerCase(letter) == 'e')
			return base + 4;
		else if (Character.toLowerCase(letter) == 'f')
			return base + 5;
		else if (Character.toLowerCase(letter) == 'g')
			return base + 6;
		else if (Character.toLowerCase(letter) == 'h')
			return base + 7;
		else if (Character.toLowerCase(letter) == 'i')
			return base + 8;
		else if (Character.toLowerCase(letter) == 'j')
			return base + 9;
		else
			return -1;
	}
	
	
	public String indexToLetter(int index) {
		int base = 1;
		
		if (Character.toLowerCase(index) == base)
			return "a";
		else if (Character.toLowerCase(index) == base + 1)
			return "b";
		else if (Character.toLowerCase(index) == base + 2)
			return "c";
		else if (Character.toLowerCase(index) == base + 3)
			return "d";
		else if (Character.toLowerCase(index) == base + 4)
			return "e";
		else if (Character.toLowerCase(index) == base + 5)
			return "f";
		else if (Character.toLowerCase(index) == base + 6)
			return "g";
		else if (Character.toLowerCase(index) == base + 7)
			return "h";
		else if (Character.toLowerCase(index) == base + 8)
			return "i";
		else if (Character.toLowerCase(index) == base + 9)
			return "j";
		else
			return null;
	}
	
	
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
}

