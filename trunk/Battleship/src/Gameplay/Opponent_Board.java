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
	
	/*
	 * The incoming string turns into members.  Deletes the characters where the ships are
	 */
	public void StringToOppBoard(String str){
		display_line[0] = str.substring(0, 32);
		display_line[1] = str.substring(32, 64);
		display_line[2] = str.substring(64, 96);
		display_line[3] = str.substring(96, 128);
		display_line[4] = str.substring(128, 160);
		display_line[5] = str.substring(160, 192);
		display_line[6] = str.substring(192, 224);
		display_line[7] = str.substring(224, 256);
		display_line[8] = str.substring(256, 288);
		display_line[9] = str.substring(288, 320);
		display_line[10] = str.substring(320);
		
		for(int i=1;i<=10;i++){          //go through the display lines to look for ships.  Won't look at the coordinate characters.
			for(int j=2;j<(max_x*3);j++){
				
				if(display_line[i].charAt(j)=='B'){
					battleship.position[(j-2)/3][i-1]=1;
				}
				if(display_line[i].charAt(j)=='C'){
					cruiser.position[(j-2)/3][i-1]=1;
				}
				if(display_line[i].charAt(j)=='P'){
					patrolboat.position[(j-2)/3][i-1]=1;
				}
				if(display_line[i].charAt(j)=='A'){
					carrier.position[(j-2)/3][i-1]=1;
				}
				if(display_line[i].charAt(j)=='S'){
					submarine.position[(j-2)/3][i-1]=1;
				}
				
			}
			//get all instances of the letters on the display_lines
			display_line[i]=display_line[i].substring(0,2)+display_line[i].substring(2).replace('B', ' ');
			display_line[i]=display_line[i].substring(0,2)+display_line[i].substring(2).replace('C', ' ');
			display_line[i]=display_line[i].substring(0,2)+display_line[i].substring(2).replace('A', ' ');
			display_line[i]=display_line[i].substring(0,2)+display_line[i].substring(2).replace('S', ' ');
			display_line[i]=display_line[i].substring(0,2)+display_line[i].substring(2).replace('P', ' ');
			
			
		}
		
		
	}
	
	}
	
	