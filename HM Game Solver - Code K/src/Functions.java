import java.util.*;
import java.util.Map.Entry;
import java.lang.*;
import java.io.*;

public class Functions {
	
	/*
	 * 
	 * Initialize number of rows and columns
	 * Initialize number of combinations
	 * 
	 */
	private  int numofrows = 2;
	private  int numofcols = 3;
	int combinations = 0;
	
	/*
	 * 
	 * 
	 * Declaring last used rows and columns
	 * 
	 */
	private  int lastoptionrow1;
	private  int lastoptioncol1;
	private  int lastoptionrow2;
	private  int lastoptioncol2;
	
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	public  int number = 0;
	
	private int doneCount = 0;
	private int gridArrayLength = 1;
	
	/*
	 * 
	 * Declaring TWO LinkedLists for worstCases, one of gridArrayClass (for the grid)
	 * and the second of String (for the move)
	 * 
	 */
	private LinkedList<gridArrayClass> worstCases = new LinkedList<gridArrayClass>();
	private LinkedList<String> worstCaseMoves = new LinkedList<String>();
	
	private LinkedList<gridArrayClass> worstCases2 = new LinkedList<gridArrayClass>();
	private LinkedList<String> worstCaseMoves2 = new LinkedList<String>();

	public void learnCompile(String master, String move,String bool){
		byte [][] addGrid = new byte[][] { 
				
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
						
				};
		byte moveRow1 = 1;
		byte moveRow2 = 1;
		byte moveCol1 = 1;
		byte moveCol2 = 1;

		switch (master.charAt(0)) {
		case 'A':
			moveCol1 = 0;
			break;
		case 'B':
			moveCol1 = 1;
			break;
		case 'C':
			moveCol1 = 2;
			break;
		case 'D':
			moveCol1 = 3;
			break;
		case 'E':
			moveCol1 = 4;
			break;
		}
		
		switch (master.charAt(1)) {
		case '1':
			moveRow1 = 0;
			break;
		case '2':
			moveRow1 = 1;
			break;
		case '3':
			moveRow1 = 2;
			break;
		case '4':
			moveRow1 = 3;
			break;
		case '5':
			moveRow1 = 4;
			break;
		}
		
		switch (master.charAt(2)) {
		case 'A':
			moveCol2 = 0;
			break;
		case 'B':
			moveCol2 = 1;
			break;
		case 'C':
			moveCol2 = 2;
			break;
		case 'D':
			moveCol2 = 3;
			break;
		case 'E':
			moveCol2 = 4;
			break;
		}
		
		switch (master.charAt(3)) {
		case '1':
			moveRow2 = 0;
			break;
		case '2':
			moveRow2 = 1;
			break;
		case '3':
			moveRow2 = 2;
			break;
		case '4':
			moveRow2 = 3;
			break;
		case '5':
			moveRow2 = 4;
			break;
		}
		
	addGrid[moveRow1][moveCol1] = 1;
    addGrid[moveRow2][moveCol2] = 1;

	
	boolean win;
    if (bool.equals("true")){
    	
    	win = true;
    }
    else {
    	win = false;
    }
    gridArrayClass moveArrayClass = new gridArrayClass(addGrid, win, false);
   
	worstCases2.add(moveArrayClass);
	worstCaseMoves2.add(move);
}
	/************************************************************
	 * @param passedGrid - Starting grid if going first
	 * @return String of the move to take with that worst move
	 * 
	 * Method to check for the worst case. Simple compare.
	 ***********************************************************/
	public String checkForWorst(gridArrayClass passedGrid){
		String returnString = "N";
		for (int worst = 0; worst < worstCases2.size(); worst++){
			if (passedGrid.equals(worstCases2.get(worst))){
				//If a worst case is found, return the pre-calculated best move
				System.out.println("Grid already learned!");
				returnString = worstCaseMoves2.get(worst);
				break;
			}
		}
		//If a worst case is not found, return N
		return returnString;
	}
	public String checkForWin(gridArrayClass passedGrid){
		String returnWin = "true";
		for (int worst = 0; worst < worstCases2.size(); worst++){
			if (passedGrid.equals(worstCases2.get(worst))){
				if(worstCases2.get(worst).getWin() == false){
					returnWin = "false";
				}
				break;
			}
		}
		return returnWin;
		
	}
	/*********************************************
	 * @param allGrids - 
	 * @param allGridsKeys - 
	 * 
	 * Calls buildHash() to solve the given grid
	 *********************************************/
	public void solve (HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys, gridArrayClass grid){
	boolean done = false;
	
	
	buildHash(allGrids, allGridsKeys, grid);
	done = true;
	if (done){
		System.out.println("Done");
	}
}
	
	/**
	 * @param allGrids
	 * @param allGridsKeys
	 * 
	 * Adds all possible moves from the starting grid to hash maps
	 */
	public void buildHash (HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys, gridArrayClass grid){
        	for (int r = 0;r <= this.getNumofrows() - 1;r++){
    			for (int col = 0; col <= this.getNumofcols() - 1;col++){
    				if (this.canPlaceHor (grid,r,col)){
        					if (col == this.getNumofcols() - 1){
        						
        					}
        					else this.placeOption(grid,r,col,r,col + 1, allGrids, allGridsKeys);
    				}
    				if (this.canPlaceVer (grid,r,col)){
    					if (r == this.getNumofrows() - 1){
    						
    					}
    					else this.placeOption(grid,r,col,r + 1,col, allGrids, allGridsKeys);
    				}
    			}
    		}
        		fillHash(allGrids, allGridsKeys);
    	}
	
	/***********************************************
	 * @param allGrids
	 * @param allGridsKeys
	 * 
	 * Adds all possible board states to the hash maps, looping until complete
	 ***********************************************/
	@SuppressWarnings("unchecked")
	public void fillHash (HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys){         
		while (this.doneCount < this.gridArrayLength){
			System.out.println(this.doneCount + " is less than " + this.gridArrayLength);
			Object[] gridArray = allGrids.keySet().toArray();
			this.gridArrayLength = gridArray.length;
			this.doneCount = 0;
			for(int i = 0; i < this.gridArrayLength; i++) {
			
				if (allGrids.get(gridArray[i]).peekFirst() == null)
				{
				
					boolean placed = false;
					for (int r = 0;r <= this.getNumofrows() - 1;r++){
						for (int col = 0; col <= this.getNumofcols() - 1;col++){
							if (this.canPlaceHor ((gridArrayClass)gridArray[i],r,col)){
								if (col == this.getNumofcols() - 1){
								
								}
								else
									{
									placed = true;
									this.placeOption((gridArrayClass)gridArray[i],r,col,r,col + 1, allGrids, allGridsKeys);
									}
							}
							if (this.canPlaceVer ((gridArrayClass)gridArray[i],r,col)){
								if (r == this.getNumofrows() - 1){
						
								}
								else
								{
									placed = true;
									this.placeOption((gridArrayClass)gridArray[i],r,col,r + 1,col, allGrids, allGridsKeys);
								}
							}
						}
					}
					if (!placed)
					{
						
						Option option = new Option((gridArrayClass)gridArray[i],0,0,0,0);
						allGrids.get(gridArray[i]).add(option);
						((gridArrayClass) gridArray[i]).setWin(false);
						((gridArrayClass) gridArray[i]).setProcessed(true);
						
					}
				}
				else
				{
					//System.out.println(i + " is not null");
					this.doneCount++;
				}
			}
		}
		processHash(allGrids, allGridsKeys);
	}
	
	/***********************************************
	 * @param allGrids
	 * @param allGridsKeys
	 * 
	 * Parses the hash maps to find winning boards, count children and determine win ratios
	 ***********************************************/
	public void processHash (HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys){
		System.out.println("Processing Hashes");
		Object[] gridArray = allGrids.keySet().toArray();
		this.gridArrayLength = gridArray.length;
		int totalProcessed = 0;
		while (totalProcessed < gridArray.length){
			totalProcessed = 0;
			for(int i = 0; i < this.gridArrayLength; i++) {
				if (i % 10000 == 0){
					System.out.println("Processing " + (i + 1) + " of " + gridArray.length + " keys/grids.");
				}
				if (!allGridsKeys.get(((gridArrayClass) gridArray[i])).getProcessed()){
					LinkedList<Option> keyGridList = allGrids.get(gridArray[i]);
					int winCount = 0;
					int processedCount = 0;
					long childrenCount = 0;
					for (int j = 0; j < keyGridList.size(); j++)
					{
						if(allGridsKeys.get(keyGridList.get(j).getGrid()).getProcessed()){
							keyGridList.get(j).getGrid().setProcessed(true);
							processedCount++;
							if(keyGridList.get(j).getGrid().getWin() != allGridsKeys.get(keyGridList.get(j).getGrid()).getWin()){
								keyGridList.get(j).getGrid().setWin(allGridsKeys.get(keyGridList.get(j).getGrid()).getWin());
							}
							if(keyGridList.get(j).getGrid().getWinningRatio() != allGridsKeys.get(keyGridList.get(j).getGrid()).getWinningRatio()){
								keyGridList.get(j).getGrid().setWinningRatio(allGridsKeys.get(keyGridList.get(j).getGrid()).getWinningRatio());
							}
							if(keyGridList.get(j).getGrid().getTotalChildren() != allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren()){
								keyGridList.get(j).getGrid().setTotalChildren(allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren());
							}
							if (keyGridList.get(j).getGrid().getWin()){
								winCount++;
							}
							childrenCount += allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren() + 1;
						}
					}
					if (processedCount == keyGridList.size()){
						if (winCount == keyGridList.size()){
							allGridsKeys.get(((gridArrayClass) gridArray[i])).setWin(false);
						}
						else{
							allGridsKeys.get(((gridArrayClass) gridArray[i])).setWin(true);
						}
						allGridsKeys.get(((gridArrayClass) gridArray[i])).setProcessed(true);
						allGridsKeys.get(((gridArrayClass) gridArray[i])).setWinningRatio((float)winCount/(float)keyGridList.size());
						allGridsKeys.get(((gridArrayClass) gridArray[i])).setTotalChildren(childrenCount);
					}
					else{
						allGridsKeys.get(((gridArrayClass) gridArray[i])).setProcessed(false);
					}
				}
				else{
					totalProcessed++;
				}
			}
			System.out.println("Total processed: " + totalProcessed + ", Size: " + gridArray.length);
		}
	}
	
	/*****************************************************
	 * @param allGrids
	 * @param allGridsKeys
	 * @param currentBoard
	 * @return String for our move in the format provided to us by the server
	 * 
	 * Finds the best possible move for us given the current board state using the pre-calculated states
	 *****************************************************/
	public String findMove (HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys, gridArrayClass currentBoard){
		String theMove = "";
		byte moveRow1 = 1;
		byte moveRow2 = 1;
		byte moveCol1 = 1;
		byte moveCol2 = 1;
		boolean canWin = allGridsKeys.get(currentBoard).getWin();
		
		if(canWin){
			//Search for L with least children
			LinkedList<Option> keyGridList = allGrids.get(currentBoard);
			long leastChildren = Long.MAX_VALUE;
			int leastChildrenIndex = 0;
			for (int i = 0; i < keyGridList.size(); i++){
				if(!keyGridList.get(i).getGrid().getWin()){
					if (keyGridList.get(i).getGrid().getTotalChildren() < leastChildren){
						leastChildren = keyGridList.get(i).getGrid().getTotalChildren();
						leastChildrenIndex = i;
					}
				}
			}
			moveRow1 += (keyGridList.get(leastChildrenIndex).getRowOne());
			moveRow2 += (keyGridList.get(leastChildrenIndex).getRowTwo());
			moveCol1 += (keyGridList.get(leastChildrenIndex).getColOne());
			moveCol2 += (keyGridList.get(leastChildrenIndex).getColTwo());
		}
		else{
			//Search for W with highest ratio then most children
			LinkedList<Option> keyGridList = allGrids.get(currentBoard);
			float highestRatio = 0;
			long mostChildren = 0;
			int mostChildrenIndex = 0;
			for (int i = 0; i < keyGridList.size(); i++){
				if (keyGridList.get(i).getGrid().getWinningRatio() > highestRatio){
					highestRatio = keyGridList.get(i).getGrid().getWinningRatio();
					mostChildren = keyGridList.get(i).getGrid().getTotalChildren();
					mostChildrenIndex = i;
				}
				else if(keyGridList.get(i).getGrid().getWinningRatio() == highestRatio){
					if (keyGridList.get(i).getGrid().getTotalChildren() > mostChildren){
						highestRatio = keyGridList.get(i).getGrid().getWinningRatio();
						mostChildren = keyGridList.get(i).getGrid().getTotalChildren();
						mostChildrenIndex = i;
					}
				}
			}
			moveRow1 += (keyGridList.get(mostChildrenIndex).getRowOne());
			moveRow2 += (keyGridList.get(mostChildrenIndex).getRowTwo());
			moveCol1 += (keyGridList.get(mostChildrenIndex).getColOne());
			moveCol2 += (keyGridList.get(mostChildrenIndex).getColTwo());
		}
	
		 switch (moveCol1) {
		  	case 1:
		  		theMove = "A" + moveRow1;
		  		break;
		  	case 2:
		  		theMove = "B" + moveRow1;
		  		break;
		 	case 3:
		 		theMove = "C" + moveRow1;
		 		break;
		 	case 4:
		 		theMove = "D" + moveRow1;
		 		break;
		 	case 5:
		 		theMove = "E" + moveRow1;
		 		break;
		}
		 
		switch (moveCol2) {
		  	case 1:
		  		theMove += "A" + moveRow2;
		  		break;
		  	case 2:
		  		theMove += "B" + moveRow2;
		  		break;
		 	case 3:
		 		theMove += "C" + moveRow2;
		 		break;
		 	case 4:
		 		theMove += "D" + moveRow2;
		 		break;
		 	case 5:
		 		theMove += "E" + moveRow2;
		 		break;
		}
		
		return theMove;
	}
	
	/***************************************************************************
	 * @param theMove - The servers MOVE string
	 * @param theGrid - The current grid
	 * 
	 * Decodes the servers string into our format and then adds it to our grid
	 ***************************************************************************/
	public void parseMove (String theMove, gridArrayClass theGrid){
		byte moveRow1 = 1;
		byte moveRow2 = 1;
		byte moveCol1 = 1;
		byte moveCol2 = 1;

		switch (theMove.charAt(0)) {
		case 'A':
			moveCol1 = 0;
			break;
		case 'B':
			moveCol1 = 1;
			break;
		case 'C':
			moveCol1 = 2;
			break;
		case 'D':
			moveCol1 = 3;
			break;
		case 'E':
			moveCol1 = 4;
			break;
		}
		
		switch (theMove.charAt(1)) {
		case '1':
			moveRow1 = 0;
			break;
		case '2':
			moveRow1 = 1;
			break;
		case '3':
			moveRow1 = 2;
			break;
		case '4':
			moveRow1 = 3;
			break;
		case '5':
			moveRow1 = 4;
			break;
		}
		
		switch (theMove.charAt(2)) {
		case 'A':
			moveCol2 = 0;
			break;
		case 'B':
			moveCol2 = 1;
			break;
		case 'C':
			moveCol2 = 2;
			break;
		case 'D':
			moveCol2 = 3;
			break;
		case 'E':
			moveCol2 = 4;
			break;
		}
		
		switch (theMove.charAt(3)) {
		case '1':
			moveRow2 = 0;
			break;
		case '2':
			moveRow2 = 1;
			break;
		case '3':
			moveRow2 = 2;
			break;
		case '4':
			moveRow2 = 3;
			break;
		case '5':
			moveRow2 = 4;
			break;
		}
		
		theGrid.grid[moveRow1][moveCol1] = 1;
		theGrid.grid[moveRow2][moveCol2] = 1;
	}
	/*********************************************************************************************
	 * @param currentgrid - The current grid we want to place the move on
	 * @param row1 - The row we want to place the move on
	 * @param col1 - The column we want to place the move on
	 * @param row2 - The second row we want to place the move on
	 * @param col2 - The second column we want to place the move on
	 * @param allGrids - The hash map containing all the grids and options
	 * @param allGridsKeys - The hash map containing all of the grids to allow for key modification
	 *
	 * Attempts to add a move into the Hash Maps, adding it to a current key if it exists or 
	 * creating a new key if it does not
	 *
	 *********************************************************************************************/
	public  void placeOption (gridArrayClass currentgrid, int row1, int col1, int row2, int col2, HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys){
		int flag = 0;
		byte [][] myIntInit = new byte[][]
				{ 
				
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0},
				{0,0,0,0,0}
						
				};
		gridArrayClass myInt = new gridArrayClass(myIntInit, true, false);
		for(int i = 0; i < 5; i++)
		    myInt.grid[i] = currentgrid.grid[i].clone();
		myInt.grid [row1][col1] = 1;
		myInt.grid [row2][col2] = 1;
		Option option = new Option(myInt,row1,col1,row2,col2);
		if (flag == 0 && allGrids.get(myInt) != null)
		{
			
			allGrids.get(currentgrid).add(option);
		}
		else
		{
			
			allGrids.get(currentgrid).add(option);
			LinkedList<Option> newGrid = new LinkedList<Option>();
			allGridsKeys.put(myInt, myInt);
			allGrids.put(myInt, newGrid);
		}
		setLastoptionrow1(row1);
		setLastoptioncol1(col1);
		setLastoptionrow2(row2);
		setLastoptioncol2(col2);
		combinations++;
	}
	
	public  void removeOption (byte currentgrid [][], int row1, int col1, int row2, int col2){
		currentgrid [row1][col1] = 0;
		currentgrid [row2][col2] = 0;
		 System.out.println("REMOVE:");
	}
	
	/************************************************************************************************
	 * @param currentgrid - Checks if move can be placed on this grid
	 * @param row - Checks the row
	 * @param col - Check this column
	 * @return - Returns true if move can be placed and false if it can't
	 * 
	 * Returns true if move can be placed HORIZONTALLY and false if it can't be placed HORIZONTALLY
	 ************************************************************************************************/
	public  boolean canPlaceHor (gridArrayClass currentgrid, int row, int col){
		if (row > 4 || col >= 4){
			   
		 }
		else{
		if (currentgrid.grid [row][col] == 0 && currentgrid.grid [row] [col + 1] == 0){
			return true;
		}
		}
		return false;
		
	}
	
	/********************************************************************************************
	 * @param currentgrid - Checks if move can be placed on this grid
	 * @param row - Checks the row
	 * @param col - Check this column
	 * @return - Returns true if move can be placed and false if it can't
	 * 
	 * Returns true if move can be placed VERTICALLY and false if it can't be placed VERTICALLY
	 ********************************************************************************************/
	public  boolean canPlaceVer (gridArrayClass currentgrid, int row, int col){
		if (row >= 4 || col >4){
			   
		  }
		else{
		if (currentgrid.grid [row][col] == 0 && currentgrid.grid [row + 1] [col] == 0){
			return true;
		}
		}
		return false;
		
	}
	
	public gridArrayClass rotateClockWise(gridArrayClass currentGrid, int amount){
		for (int i = 0; i < amount; i++ ){
			
		
		currentGrid = transposeGrid(currentGrid);
		currentGrid = reverseRow(currentGrid);
		
		}
		return currentGrid;
		
	}
	private gridArrayClass transposeGrid (gridArrayClass currentgrid){
		byte temp;
		//byte temp2;
		int start = 0;
		for (int j = 0; j < this.numofcols ;j++ ){
			start++;
			for (int i = start; i < this.numofrows; i++){
				temp = currentgrid.grid[i][j];
				//temp2 = currentgrid.grid
				currentgrid.grid[i][j] = currentgrid.grid[j][i];
				currentgrid.grid[j][i] = temp;
				}
		}
		return currentgrid;
		
	}
	
	@SuppressWarnings("null")
	public gridArrayClass reverseRow (gridArrayClass currentgrid){
		Stack<Byte> temp = new Stack<Byte>();
		byte temp2;
		for (int i = 0; i < this.numofrows; i++){
			for (int j = 0; j < this.numofcols; j++){
				temp2 = currentgrid.grid[i][j];
				//System.out.println(temp2);
				temp.push(temp2);
			}
			for (int j = 0; j < this.numofcols; j++){
				currentgrid.grid[i][j] = temp.pop();
			}
		}
		return currentgrid;
		
	}
	
	/*
	 * 
	 * 
	 * Getters and setters below
	 * 
	 * 
	 */
	public  int getNumofrows() {
		return numofrows;
	}
	public  void setNumofrows(int numofrows) {
		this.numofrows = numofrows;
	}
	public  int getNumofcols() {
		return numofcols;
	}
	public  void setNumofcols(int numofcols) {
		this.numofcols = numofcols;
	}
	public  int getLastoptionrow1() {
		return lastoptionrow1;
	}
	public  void setLastoptionrow1(int lastoptionrow1) {
		this.lastoptionrow1 = lastoptionrow1;
	}
	public  int getLastoptioncol1() {
		return lastoptioncol1;
	}
	public  void setLastoptioncol1(int lastoptioncol1) {
		this.lastoptioncol1 = lastoptioncol1;
	}
	public  int getLastoptionrow2() {
		return lastoptionrow2;
	}
	public  void setLastoptionrow2(int lastoptionrow2) {
		this.lastoptionrow2 = lastoptionrow2;
	}
	public  int getLastoptioncol2() {
		return lastoptioncol2;
	}
	public  void setLastoptioncol2(int lastoptioncol2) {
		this.lastoptioncol2 = lastoptioncol2;
	}
}
