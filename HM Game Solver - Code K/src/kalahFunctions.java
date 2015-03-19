import java.util.HashMap;
import java.util.LinkedList;


public class kalahFunctions {
	public static int numofrows = 2;
	public static int numofcols = 8; 
	public static int numofseeds = 3;
	private boolean checkEveryMove = false;
	//public static byte currentseeds;
	//public static byte currentPitIndex;
	
	private int doneCount = 0;
	private int gridArrayLength = 1;
	
	Runtime runtime = Runtime.getRuntime();
	
	public void playerOnePlace(kalahArrayClass grid, int pitRow, int pitCol, HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys){
		int track = 0;
		int lastPit = 0;
		boolean direction = true; //true direction means on the players side of the board
		kalahArrayClass clonedGrid;
		byte gridInit [][];
		
			//choose the first one
			clonedGrid = grid.clone();
			gridInit = clonedGrid.getGrid();
			byte temp = gridInit [numofrows - 1][pitCol];
			byte tempLoop = (byte) (pitCol + 1);
			gridInit[numofrows - 1][pitCol] = 0;
			for (int j = 0; j < temp ; j++ ){
				if (tempLoop == (numofcols - 1)){
					gridInit[numofrows - 1][tempLoop] = (byte) (gridInit[numofrows - 1][tempLoop] + 1);
					//go backwards now
					for (int k = 0; k < (temp - j);k++){
						if (tempLoop == 0){
							break;
						}
						gridInit[numofrows - 2][tempLoop] = (byte) (gridInit[numofrows - 2][tempLoop] + 1);
						direction = false;
						if (checkEveryMove == true){
							System.out.println("\n" + clonedGrid);
						}
						track = k;
						lastPit = tempLoop;
						tempLoop--;
					}
					j = j + track;
				}
				else {
					//go forward
				if (tempLoop == 0){
					tempLoop++;
				}
				gridInit[numofrows - 1][tempLoop] = (byte) (gridInit[numofrows - 1][tempLoop] + 1);
				direction = true;
				if (checkEveryMove == true){
					System.out.println("\n" + clonedGrid);
				}
				lastPit = tempLoop;
				tempLoop++;
				}
			}
			
			if (lastPit == numofcols - 1){
				//Player one gets to go again
				//System.out.print("\nI CAN GO AGAIN YAY!");
				clonedGrid.setPlayerTwoTurn(false);
			}
			else{
				//Turn proceeds to player two
				clonedGrid.setPlayerTwoTurn(true);
			}
			if (gridInit [numofrows - 2][lastPit] != 0 && gridInit [numofrows - 1][lastPit] == 1 && direction == true){
				//Take all the scores if the last one player was empty
				gridInit [numofrows - 1][numofcols - 1] += (byte) (gridInit [numofrows - 1][lastPit] + gridInit [numofrows - 2][lastPit]);
				gridInit [numofrows - 2][numofcols - 1] += (byte) (gridInit [numofrows - 1][lastPit] + gridInit [numofrows - 2][lastPit]);
				gridInit [numofrows - 1][lastPit] = 0;
				gridInit [numofrows - 2][lastPit] = 0;
				//System.out.print("\nTAKE ALL TEH SCORES");
			}
			
			int sumPlayerone = 0;
			int sumPlayertwo = 0;
			for (int i = 1; i < numofcols - 1;i++){
				sumPlayerone += gridInit [numofrows - 1][i];
			}
			if (sumPlayerone == 0){
				for (int i = 1; i < numofcols - 1;i++){
					sumPlayertwo += gridInit [numofrows - 2][i];
					gridInit [numofrows - 2][i] = 0;
					
				}
				gridInit [0][0] += (byte) sumPlayertwo;
				gridInit [1][0] += (byte) sumPlayertwo;
			}
			
				//System.out.println("\n" + clonedGrid);
				kalahOption option = new kalahOption(clonedGrid, pitRow, pitCol);
				if (allGrids.get(clonedGrid.getGrid()) != null)
				{
					allGrids.get(grid).add(option);
				}
				else
				{
					allGrids.get(grid).add(option);
					LinkedList<kalahOption> newGrid = new LinkedList<kalahOption>();
					allGridsKeys.put(clonedGrid, clonedGrid);
					allGrids.put(clonedGrid, newGrid);
				}
		
	}
	
	public void playerTwoPlace(kalahArrayClass grid, int pitRow, int pitCol, HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys){
		int track = 0;
		int lastPit = 0;
		boolean direction = true; //true direction means on the players side of the board
		kalahArrayClass clonedGrid;
		byte gridInit [][];
		
			//choose the first one
			clonedGrid = grid.clone();
			gridInit = clonedGrid.getGrid();
			byte temp = gridInit [numofrows - 2][pitCol];
			byte tempLoop = (byte) (pitCol - 1);
			gridInit[numofrows - 2][pitCol] = 0;
			for (int j = 0; j < temp ; j++ ){
				if (tempLoop == 0){
					gridInit[numofrows - 2][tempLoop] = (byte) (gridInit[numofrows - 2][tempLoop] + 1);
					//go forwards now
					for (int k = 0; k < (temp - j);k++){
						if (tempLoop == numofcols - 1){
							break;
						}
						gridInit[numofrows - 1][tempLoop] = (byte) (gridInit[numofrows - 1][tempLoop] + 1);
						direction = false;
						if (checkEveryMove == true){
							System.out.println("\n" + clonedGrid);
						}
						track = k;
						lastPit = tempLoop;
						tempLoop++;
					}
					j = j + track;
				}
				else {
					//go forward
				if (tempLoop == numofcols - 1){
					tempLoop--;
				}
				gridInit[numofrows - 2][tempLoop] = (byte) (gridInit[numofrows - 2][tempLoop] + 1);
				direction = true;
				if (checkEveryMove == true){
					System.out.println("\n" + clonedGrid);
				}
				lastPit = tempLoop;
				tempLoop--;
				}
			}
			
			if (lastPit == 0){
				//Player two gets to go again
				//System.out.print("\nI CAN GO AGAIN YAY!");
				clonedGrid.setPlayerTwoTurn(true);
			}
			else{
				//Turn proceeds to player one
				clonedGrid.setPlayerTwoTurn(false);
			}
			if (gridInit [numofrows - 1][lastPit] != 0 && gridInit [numofrows - 2][lastPit] == 1 && direction == true){
				//Take all the scores if the last one player was empty
				gridInit [numofrows - 1][0] += (byte) (gridInit [numofrows - 1][lastPit] + gridInit [numofrows - 2][lastPit]);
				gridInit [numofrows - 2][0] += (byte) (gridInit [numofrows - 1][lastPit] + gridInit [numofrows - 2][lastPit]);
				gridInit [numofrows - 1][lastPit] = 0;
				gridInit [numofrows - 2][lastPit] = 0;
				//System.out.print("\nTAKE ALL TEH SCORES");
			}
			
			int sumPlayerone = 0;
			int sumPlayertwo = 0;
			for (int i = 1; i < numofcols - 1;i++){
				sumPlayertwo += gridInit [numofrows - 2][i];
			}
			if (sumPlayertwo == 0){
				for (int i = 1; i < numofcols - 1;i++){
					sumPlayerone += gridInit [numofrows - 1][i];
					gridInit [numofrows - 1][i] = 0;
				}
				gridInit [0][numofcols - 1] += (byte) sumPlayerone;
				gridInit [1][numofcols - 1] += (byte) sumPlayerone;
			}
			
				//System.out.println("\n" + clonedGrid);
				kalahOption option = new kalahOption(clonedGrid, pitRow, pitCol);
				if (allGrids.get(clonedGrid.getGrid()) != null)
				{
					allGrids.get(grid).add(option);
				}
				else
				{
					allGrids.get(grid).add(option);
					LinkedList<kalahOption> newGrid = new LinkedList<kalahOption>();
					allGridsKeys.put(clonedGrid, clonedGrid);
					allGrids.put(clonedGrid, newGrid);
				}
	}
	
	public boolean canPlace (kalahArrayClass grid , int pitRow, int pitCol){
		if (pitRow == 0 || pitRow == 1){
			if (grid.getGridValue(pitRow,pitCol) != 0){
				return true;
			}
			else{
				//System.out.println("Pit empty");
				return false;
			}
		}
		else{
			//System.out.println("Row out of Bounds");
			return false;
		}

	}
	
	/**
	 * @param allGrids
	 * @param allGridsKeys
	 * 
	 * Adds all possible moves from the starting grid to hash maps
	 */
	public void buildHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys, kalahArrayClass grid){
		for (int i = 1; i < (numofcols - 1); i++){
			if (this.canPlace(grid, 1, i)){
				this.playerOnePlace(grid,1,i,allGrids,allGridsKeys);
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
	public void fillHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys){         
		while (this.doneCount < this.gridArrayLength){
			System.out.println(this.doneCount + " is less than " + this.gridArrayLength);
			System.out.println("\nFree Memory: "+ (runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory()))/1024 );
			Long freeUsage = runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory())/1024;
			if (freeUsage < 1200000){
				System.gc();
			}
			else {
				
			}
			
			Object[] gridArray = allGrids.keySet().toArray();
			this.gridArrayLength = gridArray.length;
			this.doneCount = 0;
			for(int i = 0; i < this.gridArrayLength; i++) {
				if (allGrids.get(gridArray[i]).peekFirst() == null)
				{
					boolean placed = false;
					//playerOne
					kalahArrayClass grid = allGridsKeys.get(gridArray[i]);
					if (grid.isPlayerTwoTurn() == false){
						for (int j = 1; j < (numofcols - 1); j++){
							if (this.canPlace(grid, 1, j)){
								this.playerOnePlace(grid,1,j,allGrids,allGridsKeys);
								placed = true;
							}
						}
					}
					//playerTwo
					else{
						for (int j = 1; j < (numofcols - 1); j++){
							if (this.canPlace(grid, 0, j)){
								this.playerTwoPlace(grid,1,j,allGrids,allGridsKeys);
								placed = true;
							}
						}
					}
					if (!placed)
					{
						kalahOption option = new kalahOption((kalahArrayClass)gridArray[i],0,0);
						allGrids.get(gridArray[i]).add(option);
						((kalahArrayClass) gridArray[i]).setGameOver(true);
						((kalahArrayClass) gridArray[i]).setProcessed(true);
					}
				}
				else
				{
					//System.out.println(i + " is not null");
					this.doneCount++;
				}
			}
		}
		//processHash(allGrids, allGridsKeys);
	}
}
