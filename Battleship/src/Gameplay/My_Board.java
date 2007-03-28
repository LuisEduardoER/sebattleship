package Gameplay;

import Gameplay.Board;
/**
 * 
 */

/**
 * @author Nathan
 *
 */
public class My_Board extends Board{
	private StringBuffer dynamic_line;
	
	public My_Board(){
		super();
		display_line[11]="           my board             ";
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
		dynamic_line= new StringBuffer(display_line[line_index]);
		
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
		
		//return the string
		switch(line_index) {
		case 0: return display_line[0];  break;
		case 1: return display_line[1];  break;
		case 2: return display_line[2];  break;
		case 3: return display_line[3];  break;
		case 4: return display_line[4];  break;
		case 5: return display_line[5];  break;
		case 6: return display_line[6];  break;
		case 7: return display_line[7];  break;
		case 8: return display_line[8];  break;
		case 9: return display_line[9];  break;
		case 10: return display_line[10];  break;
		case 11: return display_line[11]; break;
				
		}
	
		
	}
}
