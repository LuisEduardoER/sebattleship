
package Gameplay;

import Networking.*;
import java.io.*;

/**
 * @author Nathan & Quinn
 *
 */
public class Player {
	
	public String name;
	//needs to be initialized in setup menus
	private long IP_address; 
	//needs to be initialized in setup menus
	public boolean connected;
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
		name="unknown";
		xcoor=0;
		ycoor=0;
		attack_coord="";
		isTurn=false;
		connected=false;
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
		
			System.out.println("Enter the position you want to Attack (i.e. A2");
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
		if(attack_coord.length()>2)    //format incorrect
			return false;
		attack_coord.toUpperCase();    //normalize any case differences
		
		// Convert the string inputs into integers
		String x_as_string = attack_coord.substring(1);
		String y_as_string = attack_coord.substring(0,1);
		
		try{
			xcoor=Integer.parseInt(x_as_string);
			xcoor--;                            //to match the xcoor with the array position
		} catch (NumberFormatException nfe) {
	         System.out.println("Incorrect Format.  Enter with letter and then number i.e. B3");
	         return false;
		}

		try{
			char y_ASCII=y_as_string.charAt(0);
			ycoor= (int) y_ASCII - 65;
		}catch(NumberFormatException e) {
			System.out.println("Incorrect Format.  Enter with letter and then number i.e. B3");
			return false;
		}

		if(!hisBoard.in_Grid(xcoor, ycoor))
			return false;
		if(hisBoard.already_attacked(xcoor,ycoor))
			return false;
		
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
	public void setMyBoard(My_Board myBoard) {
		this.myBoard = myBoard;
	}
}

