package Gameplay;
/**
 * 
 */

/**
 * @author Nathan
 * Describes the abstract ship class.  The five different ship classes inherit this class.
 */
public class Ship {
protected static int size;
protected boolean sunk;
private int count_hits;
protected int [][] position;
protected int [][] position_hit;
protected boolean sunk_this_turn;

public Ship(){
	sunk=false;
	for(int i=0;i<=Grid.max_x; i++){
		for(int j=0; j<=Grid.max_y; j++){
			position[i][j]=0;
			position_hit[i][j]=0;
		}
	}
}
/*
 * Updates the ship's sunk status.  
 * If the number of hits is greater or equal to the size of the ship
 * then the ship is sunk, and it updates its member.
 */
protected void update_sunk(){
	if(!sunk){
		count_hits=0;
		for(int i=0; i<=Grid.max_x; i++){
			for(int k =0; k<=Grid.max_y; k++){
				if(position_hit[i][k]!=0)
					count_hits++;
			}
		}
		if(count_hits>=size){
			sunk=true;
			sunk_this_turn=true;
		}
		else{
			sunk=false;
			sunk_this_turn=false;
		}
	}
	else
		sunk_this_turn=false;
}

/* UNFINISHED
 * Allows the program to set the position array of the ship.  
 * It takes in the x and y coordinates the program wants the ship to be positioned.
 * 
 */
public void set_position(int xcoor, int ycoor){
	position[xcoor][ycoor]=1;
}
}