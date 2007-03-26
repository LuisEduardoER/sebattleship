package Gameplay;

import java.io.*;
public class Player {
	
	public String name;
	private long IP_address;
	public boolean connected;
	public boolean isTurn;
	private boolean victory;
	private My_Board myBoard = new My_Board();
	private Opponent_Board hisBoard = new Opponent_Board();
	protected int xcoor;
	protected int ycoor;
	
	public String Get_Input()throws IOException{
		String input="";
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		
		input = in.readLine();
		
		return input;
	}
	
	public void My_Turn() {
		
			System.out.println("Enter the position you want to Attack (i.e. A2");
			String attack_coord=Get_Input();
		while(!Validate_Input(attack_coord)){
			System.out.println("Enter the position you want to Attack (i.e. A2");
			attack_coord=Get_Input();
		}
		
		//SEND ATTACK_COORD TO OPPONENT HERE
		
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
		
	}
	
	public void His_Turn(){
		//WAIT FOR OPPONENT TO SEND ATTACK_COORD
		Validate_Input(attack_coord);   //will get xcoor and ycoor
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
	
	public boolean Validate_Input(String attack_coord){
		attack_coord=attack_coord.trim();   //deletes any unecessary whitespace
		if(attack_coord.length()>2)    //format incorrect
			return false;
		attack_coord.toUpperCase();    //normalize any case differences
		
		// Convert the string inputs into integers
		String x_as_string=attack_coord.substring(0, 0);
		String y_as_string=attack_coord.substring(1,1);
		
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
		
		
	}
	



}
