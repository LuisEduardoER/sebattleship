#include <iostream>
#include <fstream>
using namespace std;

#include "utilities.h"
#include "math.h"

extern int known[][9];
extern bool possible[][9][9];
extern int coords[][9*9][2];
extern int gridStartRow[];
extern int gridStartCol[];
extern bool refresh;





void Math()			//#####   This function is the M.C. of all math functions   #####//
{
	CheckRow();
	CheckCol();
	CheckGrid();
	FindCoords();
//	ImplicitRowColElimination();		//duplicated in ImplicitGridElimination
	ImplicitGridElimination();
	if(refresh == true)			//keep cycling as long as changes are being made
		CheckForKnowns();
}

//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
//#####################################  Math  ############################################
//
//All of these algorithms can rather easily be combined into ImplicitGridElimination
//		This would speed up the program a little bit, though it is already almost instantanious.
//		It would make it more confusing, though.
//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//


void CheckRow()							//Check to see what values are in what rows
{
	for(int j=0; j<9; j++)
	{
		int count[9]={0};
		int index[9]={0};
		for(int k=0; k<9; k++)
		{
			///////////////////////////////////////////////////////////////
			if (known[j][k] != 0)				//if this entry is known
			{
				Write(j,k,known[j][k],1);
			}
			/////////////////////////////////////////////////////////////////////
			for(int a=0; a<9; a++)
			{
				if(possible[j][k][a])		//count the occurance of each possible value in the row
				{
					count[a]++;
					index[a]=k;
				}
			}
			////////////////////////////////////////////////////////////////////
		}
		////////////////////////////////////////////////////////////////////////
		for(int a=0; a<9; a++)							//if count[a] totals 1, then there is only one possible location for 'a' in this row
		{
			if(count[a] == 1)
			{
				WritePossible(j,index[a],a+1,true);
			}
		}
		////////////////////////////////////////////////////////////////////////
	}
}


void CheckCol()						//Check to see what values are in what columns
{
	for(int j=0; j<9; j++)
	{
		int count[9]={0};
		int index[9]={0};
		for(int k=0; k<9; k++)
		{
			if (known[k][j] != 0)				//if a cell is known, remove it from possible values of other cells in col
			{
				Write(k,j,known[k][j],2);
			}
			/////////////////////////////////////////////////////////////////////
			for(int a=0; a<9; a++)
			{
				if(possible[k][j][a])		//count the occurance of each possible value in the col
				{
					count[a]++;
					index[a]=k;
				}
			}
			////////////////////////////////////////////////////////////////////
		}
		////////////////////////////////////////////////////////////////////////
		for(int a=0; a<9; a++)				//if count[a] totals 1, then there is only one possible location for 'a' in this col
		{
			if(count[a] == 1)
			{
				WritePossible(index[a],j,a+1,true);
			}
		}
		////////////////////////////////////////////////////////////////////////
	}
}

void CheckGrid()					//Check to see what values are in what 3x3 grids
{
	for(int z=0; z<9; z++)
	{
		int count[9]={0};
		int index[9][2]={0};
		for(int j=gridStartRow[z]; j<gridStartRow[z]+3; j++)
		{
			for(int k=gridStartCol[z]; k<gridStartCol[z]+3; k++)
			{
				/////////////////////////////////////////////////////////////
				if(known[j][k] != 0)		//if this cell is known, remove it from other possibles
				{
					Write(j,k,known[j][k],0);
				}
				/////////////////////////////////////////////////////////////////////
				for(int a=0; a<9; a++)
				{
					if(possible[j][k][a])		//count the occurance of each possible value in the grid
					{
						count[a]++;
						index[a][0]=j;
						index[a][1]=k;
					}
				}
				////////////////////////////////////////////////////////////////////
			}
		}
		////////////////////////////////////////////////////////////////////////
		for(int a=0; a<9; a++)		//if count[a] totals 1, then there is only one possible location for 'a' (value) in this col
		{
			if(count[a] == 1)
			{
				WritePossible(index[a][0],index[a][1],a+1,true);
			}
		}
		////////////////////////////////////////////////////////////////////////
	}
}


void ImplicitRowColElimination()		//duplicated in ImplicitGridElimination
{
	for(int l=0; l<9; l++)				//cycle through each possible value
	{
		for(int t=0; t<9; t++)		//check the row and col for occurances only in same grid
		{
			int testr, testc=testr=-1;		//testr~columns, testc~rows
			bool failr, failc=failr=false;	//used as a flag as soon as two values are found outside the same grid
			for(int c=0; coords[l][c][0] != -1 && (!failc || !failr); c++)		//go until you hit the end or fail both row & col
			{
				if(coords[l][c][0]==t)
				{
					if(testc==-1)								//assign this variable to the first col occurance, then don't change it
						testc=gridStartRow[coords[l][c][1]];
					if(gridStartRow[coords[l][c][1]] != testc)	//if a col occurance isn't in the same grid as testc, then it fails the test
						failc=true;
				}
				if(coords[l][c][1]==t)					//same, except for row instead of colo
				{
					if(testr==-1)
						testr=gridStartRow[coords[l][c][0]];
					if(gridStartRow[coords[l][c][0]] != testr)
						failr=true;
				}
			}

			if(!failc)						//if this row didn't fail, then all occurances of 'l' are in the same grid
			{
				for(int m=gridStartRow[t]; m<gridStartRow[t]+3; m++)	//cycle through the grid and remove 'l' from the possible
				{			
					if(m != t)											//values of all rows except the current ('t'th) row
						for(int n=testc; n<testc+3; n++)    
						{
							WritePossible(m,n,l+1,false);
						}
				}
			}
			if(!failr)						//same except for col instead of row
			{
				for(int m=gridStartRow[t]; m<gridStartRow[t]+3; m++)
				{
					if(m != t)
						for(int n=testr; n<testr+3; n++)    
						{
							WritePossible(n,m,l+1,false);
						}
				}
			}
		}
	}
}


// This function can be used for all implicit elimination... I think
void ImplicitGridElimination()
{
	for(int l=0; l<9; l++)
	{
		
		for(int z=0; z<9; z++)
		{
			int testc, testr=testc=-1;
			bool failr, failc=failr=false;
			int row, col;

			for(int c=0; coords[l][c][0] != -1 && (!failr || !failc); c++)
			{
				if(gridStartRow[coords[l][c][0]] == gridStartRow[z] && gridStartRow[coords[l][c][1]] == gridStartCol[z])
				{
					if(testr == -1)				//only set this variable once
					{
						testr=coords[l][c][0];		//store the row of the first occurance to test against
						col=coords[l][c][1];		//store the col of the first occurance
					}
					if(coords[l][c][0] != testr)	//set failr flag if an occurance exists in a different row
						failr=true;				

					if(testc == -1)
					{
						testc=coords[l][c][1];
						row=coords[l][c][0];
					}
					if(coords[l][c][1] != testc)
						failc=true;
				}
			}

			if(!failr)								//if all occurances were in the same row (!failr)
			{
				for(int k=0; k<9; k++)
				{
					if(k<gridStartCol[z] || k>=gridStartCol[z]+3)	//remove 'l+1' as poss. value from every cell in row 'testr' but outside of the current grid 'z'
						WritePossible(testr,k,l+1,false);
				}
			}
			if(!failc)
			{
				for(int j=0; j<9; j++)
				{
					if(j<gridStartRow[z] || j>=gridStartRow[z]+3)
						WritePossible(j,testc,l+1,false);
				}
			}
		}
	}
}

void CheckForKnowns()			//check to see if there is only one possible value in a cell
{
	for(int j=0; j<9; j++)
	{
		for(int k=0; k<9; k++)
		{
			int count=0;
			int maybe=0;
			for(int l=0; l<9; l++)
			{
				if(possible[j][k][l] == true)		//count the number of possible values and store the last occurance in maybe (if count=1, this will be the only possible value in the cell)
				{
					count++;
					maybe=l+1;
				}
			}
			if (count == 1 && known[j][k] == 0)	//if there's only one possible value, assign the value to known
			{
				known[j][k]=maybe;
			}
		}
	}
	refresh=false;
	Math();			//cycle through
}




//#################################################################################################



// Future Improvements:
//
//		Combine Math Algorithms
//		Write Guessing Algorithm
//		Other Implicit Algorithms?
//
//
//