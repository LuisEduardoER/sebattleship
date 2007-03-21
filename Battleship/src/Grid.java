
public abstract class Grid {
public static final int max_x=9;
public static final int max_y=9;
private int [][] grid;

public boolean in_Grid(int x_coor, int y_coor){
	if(x_coor < 0 || y_coor < 0)
		return false;
	if(x_coor>max_x)
		return false;
	if(y_coor>max_y)
		return false;
	else
		return true;
}
}
