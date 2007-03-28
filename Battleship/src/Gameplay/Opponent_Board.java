package Gameplay;
/**
 * 
 */

/**
 * @author Nathan
 *
 */
public class Opponent_Board extends Board{
	private StringBuffer dynamic_line;
	/**
	 * 
	 */
	public Opponent_Board() {
		super();
	
		display_line[11]="       opponent's board         ";
		
	}
	
	/*
	 * Receives the line it needs to return.
	 *
	 * 
	 * Puts an O depending on the hit_history
	 * And then a X on top of that if it has been hit.
	 * 
	 */
	public String Display(int line_index){
		if(line_index>0 && line_index<11){
		dynamic_line= new StringBuffer(display_line[line_index]);
		
		for(int i=0;i<max_x;i++){         //put O wherever has been attacked on this board 	
				if(hit_history[i][line_index-1]!=0){
					dynamic_line.deleteCharAt(2+3*i);
					dynamic_line.insert(2+3*i, 'O');
					if(hit_or_miss(i,line_index-1)){   //if that attack was a hit, put an X
						dynamic_line.deleteCharAt(2+3*i);
						dynamic_line.insert(2+3*i, 'X');
					}
				}
				
			
		}
		display_line[line_index] = dynamic_line.toString();  //save the updated string
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
		default: return "";
				
		}
	
		
	}
}
