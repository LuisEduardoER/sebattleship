
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
	private My_Board myBoard = new My_Board();
	private Opponent_Board hisBoard = new Opponent_Board();
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
	 *    tested.  Seemed to work.
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
	 */
	public void My_Turn() {
		
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
		
		/**
		 * SEND ATTACK_COORD TO OPPONENT
		 */
		
		Client snd_message= new Client("192.168.128.4",112);
		try {
			/**
			 * send coordinates with C in front to indicate they are coordinate
			 */
			snd_message.Send("C " + attack_coord); 
		} catch (Exception e) {
			System.out.println("Error sending coordinates");
			//need to resovle this somehow.
		}		
		
		/**
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
	
		/**
		 * Maybe call His_Turn to continue gameplay here, if victory equals false?
		 */
		if(victory==false){
			His_Turn();
		}
		else{
			//return to menu here, don't know syntax
		}
}

	/**
	 * His_Turn is the code ran for the opponents turn
	 */
	public void His_Turn(){
		
		/**
		 * WAIT FOR OPPONENT TO SEND ATTACK_COORD
		 * create a client object to recieve coordinates
		 */
		Client rcv_message = new Client("192.168.128.4"/*will need to be IP_address when finished*/,112);
		
		/**
		 * store message in attack_coord
		 */
		attack_coord = rcv_message.Listen();  
		
		/**
		 * will get xcoor and ycoor
		 */
		Validate_Input(attack_coord);
		
		/**
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
		
		/**
		 * call My_Turn here to continue gameplay, if victory equals false
		 */
		if(victory==false){
			My_Turn();
		}
		else{
			//return to menu here, don't know syntax
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
	 * tested.  Seems to work.
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
	 * tested. works fine.
	 */
	public void Display_Boards(){
		for(int i=0; i<12; i++){
			System.out.println(myBoard.Display(i)+"       "+hisBoard.Display(i));
		}
	}
}

