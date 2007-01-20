
#include <iostream>
#include <fstream>

using namespace std;



//  replacements to upgrade from 9 to 16 grid
//		replace occurances of 9 with 16
//		replace occurances of 3 in grid searches with 4 (sgrt(9))
//			- this implies a test to verify that 9 is valid (if(!sqrt(9)))

int known[9][9];
bool possible[9][9][9];
int coords[9][81][2];			//coords[possible value][occurance (starting at 0)][0-row 1-col values at occurance]
int gridStartRow[9]={0,0,0,3,3,3,6,6,6};
int gridStartCol[9]={0,3,6,0,3,6,0,3,6};
//int gridStartRow[9]={0,0,0,0,4,4,4,4,8,8,8,8,12,12,12,12};
//int gridStartCol[9]={0,4,8,12,0,4,8,12,0,4,8,12,0,4,8,12};
bool refresh=false;


#include "utilities.h"
#include "math.h"



int main()
{

	InitializeArray();
	GetKnownValues();
	PrintPuzzle();
	Math();
	TestPrintPossible();
	PrintPuzzle();


	return 0;
}