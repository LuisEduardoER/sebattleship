package Gameplay;

/**
 * Describes a generic BattleshipGame Board.  
 * @author Nathan
 *
 */
public abstract class Board extends Grid{
private int [][] hit_history = new int [Grid.max_x][Grid.max_y];
/**
 
 */
public Carrier carrier = new Carrier();
public Battleship battleship = new Battleship();
public Cruiser cruiser = new Cruiser();
public Submarine submarine = new Submarine();
public PatrolBoat patrolboat = new PatrolBoat();
	
public Board(){
	for(int i=0;i<max_x;i++){
		for(int k=0;k<max_y;k++){
			hit_history[i][k]=0;
		}
	}
}
/**
 *  returns true if coordinates input match the coordinates of any ships. */
/**
 * @param xcoor
 * @param ycoor
 * @return
 */
public boolean hit_or_miss(int xcoor, int ycoor)
{
	if(!in_Grid(xcoor, ycoor))
		return false;
	
	hit_history[xcoor][ycoor] = 1;
	
	if(battleship.position[xcoor][ycoor]>=1)
		return true;
	if(carrier.position[xcoor][ycoor]>=1)
		return true;
	if(submarine.position[xcoor][ycoor]>=1)
		return true;
	if(cruiser.position[xcoor][ycoor]>=1)
		return true;
	if(patrolboat.position[xcoor][ycoor]>=1)
		return true;
	else 
		return false;
}




/* checks to see if coordinates input have already been entered */
public boolean already_attacked(int xcoor, int ycoor){
	if(hit_history[xcoor][ycoor]!=0)
		return true;
	else
		return false;
}


/**
 * checks to see if any of the ships were sunk this turn.  
 * Returns which ship if it was sunk this turn
 * Returns "nothing" if no ships sunk this turn
 * 
 * Whatever calls this must check to see if value is "nothing", 
 * in which case no ships were sunk this turn.
 */
public String check_sunk(){
	if(battleship.sunk_this_turn)
		return Battleship.name;
	if(carrier.sunk_this_turn)
		return Carrier.name;
	if(submarine.sunk_this_turn)
		return Submarine.name;
	if(cruiser.sunk_this_turn)
		return Cruiser.name;
	if(patrolboat.sunk_this_turn)
		return PatrolBoat.name;
	else
		return null;
	
}

/**
 * Checks to see if all of the ships of the board are sunk this turn
 * returns true if all are sunk
 * returns false if not all are sunk
 */
public boolean check_all_sunk(){
	if(battleship.sunk && carrier.sunk && cruiser.sunk && submarine.sunk && patrolboat.sunk)
		return true;
	else
		return false;	
}


/*
 * Must be defined by subclasses.  Displays the board.
 */
abstract void Display();
}