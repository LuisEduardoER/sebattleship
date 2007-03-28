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
	 * Returns the string at the specified line.  Each display_line checks
	 */
	public String Display(int line_index){
		dynamic_line= new StringBuffer(display_line[line_index]);
		
		for(int i=0;i<max_x;i++){         //put O wherever has been attacked on this board 	
				if(hit_history[i][line_index-1]!=0){
					dynamic_line.deleteCharAt(2+3*i);
					dynamic_line.insert(2+3*i, 'O');
					if(hit_or_miss(i,line_index-1)){
						dynamic_line.deleteCharAt(2+3*i);
						dynamic_line.insert(2+3*i, 'X');
					}
				}
				
			
		}
		display_line[line_index] = dynamic_line.toString();
		
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
				
		}
	
		
	}
}
