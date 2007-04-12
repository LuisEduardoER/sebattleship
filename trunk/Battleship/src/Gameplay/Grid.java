package Gameplay;

/**
 * Describes a generic Grid
 * @author Nathan
 *
 */
public abstract class Grid {
public static final int max_x=10;
public static final int max_y=10;
//private int [][] grid;

/**
 * Checks to see if the given coordinates are in the grid
 * @param xcoor
 * @param ycoor
 * @return whether or not the coordinates are within the grid boundaries
 */
public boolean in_Grid(int x_coor, int y_coor){
	if(x_coor < 0 || y_coor < 0)
		return false;
	if(x_coor>=max_x)
		return false;
	if(y_coor>=max_y)
		return false;
	else
		return true;
}
}
