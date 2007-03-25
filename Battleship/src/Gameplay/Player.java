package Gameplay;

import java.io.*;
public class Player {
	
	public String name;
	public boolean isTurn=false;
	private My_Board myBoard = new My_Board();
	private Opponent_Board hisBoard = new Opponent_Board();
	
	public String Get_Input()throws IOException{
		String input="";
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		
		input = in.readLine();
		
		return input;
	}
	
	public void My_Turn() {
		
		System.out.println("Enter the position you want to Attack");
		String attack_coord=Get_Input();
		//my turn code
		
		
	}
	
	public void His_Turn(){
		//his turn code
		
	}
	
	public boolean Validate_Input(){
		//validate_input code
	}
	



}
