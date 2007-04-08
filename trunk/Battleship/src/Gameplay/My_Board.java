package Gameplay;

import Gameplay.Board;


/**
 * @author Nathan
 *
 */
public class My_Board extends Board{
	private StringBuffer dynamic_line;
	
	public My_Board(){
		super();
		display_line[11]="           My Board             ";
	}
	/*
	 * Receives the line it needs to return.
	 * Updates the line by checking to see if any ships are on that row
	 * Places corresponding letter if ship is on the row
	 * 
	 * It then puts a O depending on the hit_history
	 * And then a X on top of that if it has been hit.
	 * 
	 */
	public String Display(int line_index){
		
		display_line[0]= " |1 |2 |3 |4 |5 |6 |7 |8 |9 |10|";
		display_line[1]= "A|  |  |  |  |  |  |  |  |  |  |";
		display_line[2]= "B|  |  |  |  |  |  |  |  |  |  |";
		display_line[3]= "C|  |  |  |  |  |  |  |  |  |  |";
		display_line[4]= "D|  |  |  |  |  |  |  |  |  |  |";
		display_line[5]= "E|  |  |  |  |  |  |  |  |  |  |";
		display_line[6]= "F|  |  |  |  |  |  |  |  |  |  |";
		display_line[7]= "G|  |  |  |  |  |  |  |  |  |  |";
		display_line[8]= "H|  |  |  |  |  |  |  |  |  |  |";
		display_line[9]= "I|  |  |  |  |  |  |  |  |  |  |";
		display_line[10]="J|  |  |  |  |  |  |  |  |  |  |";
		
		dynamic_line= new StringBuffer(display_line[line_index]);
		if(line_index>0 && line_index<11){
		for(int i=0;i<max_x;i++){     
			if(battleship.position[i][line_index-1]>0){
				dynamic_line.deleteCharAt(2+3*i);
				dynamic_line.insert(2+3*i, 'B');
			}
			if(submarine.position[i][line_index-1]>0){
				dynamic_line.deleteCharAt(2+3*i);
				dynamic_line.insert(2+3*i, 'S');
			}
			if(carrier.position[i][line_index-1]>0){
				dynamic_line.deleteCharAt(2+3*i);
				dynamic_line.insert(2+3*i, 'A');
			}
			if(patrolboat.position[i][line_index-1]>0){
				dynamic_line.deleteCharAt(2+3*i);
				dynamic_line.insert(2+3*i, 'P');
			}
			if(cruiser.position[i][line_index-1]>0){
				dynamic_line.deleteCharAt(2+3*i);
				dynamic_line.insert(2+3*i, 'C');
			}
			if(hit_history[i][line_index-1]!=0){//put O wherever has been attacked on this board 	
				dynamic_line.deleteCharAt(2+3*i);
				dynamic_line.insert(2+3*i, 'O');
				if(hit_or_miss(i,line_index-1)){  //overwrite any O with an X if it's a hit
					dynamic_line.deleteCharAt(2+3*i);
					dynamic_line.insert(2+3*i, 'X');
				}
			}
		}
		display_line[line_index] = dynamic_line.toString();  //save the modified string
		}
		
		//return the string
		switch(line_index) {
		case 0: return display_line[0];  
		case 1: return display_line[1];  
		case 2: return display_line[2];  
		case 3: return display_line[3];  
		case 4: return display_line[4];  
		case 5: return display_line[5]; 
		case 6: return display_line[6];  
		case 7: return display_line[7];  
		case 8: return display_line[8];  
		case 9: return display_line[9];  
		case 10: return display_line[10];  
		case 11: return display_line[11]; 
		case 12: 
			dynamic_line.deleteCharAt(12);
			dynamic_line.insert(12, shipsleft);
			display_line[12]=dynamic_line.toString();
			return display_line[12];
		default: return "";
				
		}
	
		
			
		}
	/**
	 * Displays the entire board at once.  Uses the Display() method to write line by line
	 *
	 */
	public void Display_Board(){
		for(int i=0;i<12;i++)
			System.out.println(Display(i));
	}
	
	
	public String ToString(){
		return Display(0)+Display(1)+Display(2)+Display(3)+Display(4)+Display(5)+Display(6)+Display(7)+Display(8)+Display(9)+Display(10);
	}

}
