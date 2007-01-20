#include <iostream>
#include <fstream>
#include <cstring>
#include <string>
#include <cmath>
using namespace std;

#include "utilities.h"

extern int known[][9];
extern bool possible[][9][9];
extern int coords[][81][2];
extern int gridStartRow[];
extern int gridStartCol[];
extern bool refresh;


// ################################   UTILITIES  ##############################################
void InitializeArray()				// Initialize the possible array to all possible values for all entries
{
	for(int j=0; j<9; j++)
		for(int k=0; k<9; k++)
			for(int l=0; l<9; l++)
				possible[j][k][l]=true;
}


void GetKnownValues()					// Get known Entries from User (optional) or File (Standard)
{
	int col, row, value;
/*
	while(row != 0)
	{
		cout << "Enter a known entry (col row value): ";
		cin >> row >> col >> value;
		if((col > 9 || row > 9 || value > 9 || col < 1 || row < 1 || value < 0) && row != 0)
		{
			cout << "Not a valid entry.  Please make sure col, row and value are between 1 and 9";
		}
		else
		{
			known[row-1][col-1]=value;
		}
	}
*/
	ifstream fin("HeraldJournal5Star.txt");
	fin >> row >> col >> value;
	while(row != 0)
	{
		if((col > 9 || row > 9 || value > 9 || col < 1 || row < 1 || value < 0) && row != 0)
		{
			cout << "Not a valid entry.  Please make sure col, row and value are between 1 and " << 9;
		}
		else
		{
			known[row-1][col-1]=value;
		}
		WritePossible(row-1, col-1, value, true);
		fin >> row >> col >> value;
	}

}

void PrintPuzzle()								//Print the puzzle with current known entries
{
	cout << " -----------------------------------\n";
	for(int j=0; j<9; j++)
	{
		cout << "| ";
		for(int k=0; k<9; k++)
		{
			if(known[j][k] == 0)
			{
					cout << "  | ";
			}
			else
			{
			//	if(known[j][k] >= 10)
					cout << known[j][k] << " | ";
			//	else
			//		cout << char(known[j][k]+64) << "  | ";
			}
		}
		cout << endl;
		cout << " -----------------------------------\n";
	}
}
void WritePossible(int arrRow, int arrCol, int puzzleValue, bool all)	//Edit the possible array (all = true to set all entries to 'false' except value, all = false to set just that entry value to 'false'
{
	// puzzleValue is one more than the array value
	bool old;
	if(all==true)
	{
		for(int l=0; l<9; l++)
		{
			if(l+1 != puzzleValue)
			{
				old=possible[arrRow][arrCol][l];
				possible[arrRow][arrCol][l] = false;
				if(old != possible[arrRow][arrCol][l])
					refresh=true;
			}
		}
	}
	else
	{
		old=possible[arrRow][arrCol][puzzleValue-1];
		possible[arrRow][arrCol][puzzleValue-1] = false;
		if(old != possible[arrRow][arrCol][puzzleValue-1])
			refresh=true;
	}
}

void Write(int j, int k, int l, int type)  //This utility calls WritePossible in 3 commonly called loops && j=row value, k=col value, l=puzzle value, type=0-grid, 1-row, 2-col
{
	if(type==0)
	{
		for(int m=gridStartRow[j]; m<(gridStartRow[j]+3); m++)
		{
			for(int n=gridStartRow[k]; n<(gridStartRow[k]+3); n++)    //gridStartRow because 0<=k<3 then n=0, 3<=k<6 then n=3, and so on
			{
				if(m != j || n != k)		//skip the (j,k)th entry becuase it's already known
				{
					WritePossible(m,n,l,false);
				}
			}
		}
	}
	if(type==1)
	{
		for(int m=0; m<9; m++)
		{
			if(m!=j)
				WritePossible(m,k,l,false);
		}
	}
	if(type==2)
	{
		for(int m=0; m<9; m++)
		{
			if(m!=k)
				WritePossible(j,m,l,false);
		}
	}
}

void FindCoords()
{
	int count[9]={0};
	for(int j=0; j<9; j++)
	{
		for(int k=0; k<9; k++)
		{
			for(int l=0; l<9; l++)
			{
				if(possible[j][k][l])		//if 'l+1' is possible in (j,k)
				{
					coords[l][count[l]][0]=j;		//store row (j) value
					coords[l][count[l]][1]=k;		//store col (k) value
					coords[l][count[l]+1][0]=-1;	//set end flag
					coords[l][count[l]+1][1]=-1;	//set end flag
					count[l]++;						//increment count[l]
				}
			}
		}
	}
}

void TestPrintPossible()				//  Print the possible Array to see possible values for entries
{
	int j,k,l=k=j=0;
	while(j<9)
	{
		while(k<9)
		{
			cout << "Entry " << j+1 << "," << k+1 << " has possible values: ";
			while(l<9)
			{
				if(possible[j][k][l])
				{
					cout << l << ", ";
				}
				l++;
			}
			cout << endl;
			k++;
			l=0;
		}
		cout << endl;
		j++;
		k=0;
	}
}
// ##############################################################################################
