import java.util.HashMap;
import java.util.LinkedList;


public class kalahFunctions {
	public static int numofrows = 2;
	public static int numofcols = 5; 
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
			boolean didOne = false;
			boolean didTwo = false;
			for (int i = 1; i < numofcols - 1;i++){
				sumPlayerone += gridInit [numofrows - 1][i];
			}
			for (int i = 1; i < numofcols - 1;i++){
				sumPlayertwo += gridInit [numofrows - 2][i];
			}
			if (sumPlayerone == 0){
				sumPlayertwo = 0;
				for (int i = 1; i < numofcols - 1;i++){
					sumPlayertwo += gridInit [numofrows - 2][i];
					gridInit [numofrows - 2][i] = 0;
					
				}
				gridInit [0][0] += (byte) sumPlayertwo;
				gridInit [1][0] += (byte) sumPlayertwo;
				clonedGrid.setGameOver(true);
				clonedGrid.setProcessed(true);
				didOne = true;
			}
			else if (sumPlayertwo == 0){
				sumPlayerone = 0;
				for (int i = 1; i < numofcols - 1;i++){
					sumPlayerone += gridInit [numofrows - 1][i];
					gridInit [numofrows - 1][i] = 0;
				}
				gridInit [0][numofcols - 1] += (byte) sumPlayerone;
				gridInit [1][numofcols - 1] += (byte) sumPlayerone;
				clonedGrid.setGameOver(true);
				clonedGrid.setProcessed(true);
				didTwo = true;
			}
			if (didOne == true && didTwo == true){
				System.out.println("IT DID BOTH!");
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
			boolean didOne = false;
			boolean didTwo = false;
			for (int i = 1; i < numofcols - 1;i++){
				sumPlayertwo += gridInit [numofrows - 2][i];
			}
			for (int i = 1; i < numofcols - 1;i++){
				sumPlayerone += gridInit [numofrows - 1][i];
			}
			if (sumPlayertwo == 0){
				sumPlayerone = 0;
				for (int i = 1; i < numofcols - 1;i++){
					sumPlayerone += gridInit [numofrows - 1][i];
					gridInit [numofrows - 1][i] = 0;
				}
				gridInit [0][numofcols - 1] += (byte) sumPlayerone;
				gridInit [1][numofcols - 1] += (byte) sumPlayerone;
				clonedGrid.setGameOver(true);
				clonedGrid.setProcessed(true);
				didOne = true;
			}
			else if (sumPlayerone == 0){
				sumPlayertwo = 0;
				for (int i = 1; i < numofcols - 1;i++){
					sumPlayertwo += gridInit [numofrows - 2][i];
					gridInit [numofrows - 2][i] = 0;
					
				}
				gridInit [0][0] += (byte) sumPlayertwo;
				gridInit [1][0] += (byte) sumPlayertwo;
				clonedGrid.setGameOver(true);
				clonedGrid.setProcessed(true);
				didTwo = true;
			}
			if (didOne == true && didTwo == true){
				System.out.println("IT DID BOTH!");
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
		if (grid.isPlayerTwoTurn() == false)
		{
		for (int i = 1; i < (numofcols - 1); i++)
		{
			if (this.canPlace(grid, 1, i))
			{
				this.playerOnePlace(grid,1,i,allGrids,allGridsKeys);
			}
		}
		}
		else
		{
			for (int i = 1; i < (numofcols - 1); i++)
			{
				if (this.canPlace(grid, 0, i))
				{
					this.playerTwoPlace(grid,0,i,allGrids,allGridsKeys);
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
	public void fillHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys){         
		while (this.doneCount < this.gridArrayLength){
			System.out.println("\n" + this.doneCount + " is less than " + this.gridArrayLength);
			System.out.println("Free Memory: "+ (runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory()))/1024 );
			Long freeUsage = runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory())/1024;
			/*if (freeUsage < 1200000){
				System.gc();
			}
			else {
				
			}*/
			
			Object[] gridArray = allGrids.keySet().toArray();
			this.gridArrayLength = gridArray.length;
			this.doneCount = 0;
			for(int i = 0; i < this.gridArrayLength; i++) {
				if (allGrids.get(gridArray[i]).peekFirst() == null)
				{
					boolean placed = false;
					//playerOne
					kalahArrayClass grid = allGridsKeys.get(gridArray[i]);
					System.out.println("grid.isPlayertwoTurn: "+ grid.isPlayerTwoTurn());
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
		processHash(allGrids, allGridsKeys);
	}
	
	/***********************************************
	 * @param allGrids
	 * @param allGridsKeys
	 * 
	 * Parses the hash maps to find winning boards, count children and determine win ratios
	 ***********************************************/
	public void processHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys){
		System.out.println("Processing Hashes");
		Object[] gridArray = allGrids.keySet().toArray();
		this.gridArrayLength = gridArray.length;
		int totalProcessed = 0;
		while (totalProcessed < gridArray.length){
			totalProcessed = 0;
			for(int i = 0; i < this.gridArrayLength; i++) {
				if (i % 100000 == 0){
					System.out.println("Processing " + (i + 1) + " of " + gridArray.length + " keys/grids.");
				}
				if (!allGridsKeys.get(((kalahArrayClass) gridArray[i])).isProcessed()){
					LinkedList<kalahOption> keyGridList = allGrids.get(gridArray[i]);
					int scoreCount = 101010101;
					int processedCount = 0;
					long childrenCount = 0;
					for (int j = 0; j < keyGridList.size(); j++)
					{
						//System.out.println("Processed: \n" + allGridsKeys.get(keyGridList.get(j).getGrid()));
						if(allGridsKeys.get(keyGridList.get(j).getGrid()).isProcessed()){
							keyGridList.get(j).getGrid().setProcessed(true);
							processedCount++;
							if(keyGridList.get(j).getGrid().isGameOver() != allGridsKeys.get(keyGridList.get(j).getGrid()).isGameOver()){
								keyGridList.get(j).getGrid().setGameOver(allGridsKeys.get(keyGridList.get(j).getGrid()).isGameOver());
							}
							if(keyGridList.get(j).getGrid().getAverageScore() != allGridsKeys.get(keyGridList.get(j).getGrid()).getAverageScore()){
								keyGridList.get(j).getGrid().setAverageScore(allGridsKeys.get(keyGridList.get(j).getGrid()).getAverageScore());
							}
							if(keyGridList.get(j).getGrid().getTotalChildren() != allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren()){
								keyGridList.get(j).getGrid().setTotalChildren(allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren());
							}
							childrenCount += allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren() + 1;
						}
					}
					//System.out.println("Processed Count: " + processedCount + " and Size: " + keyGridList.size());
					if (processedCount == keyGridList.size()){
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setProcessed(true);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setAverageScore(scoreCount);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setTotalChildren(childrenCount);
					}
					else{
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setProcessed(false);
					}
				}
				else{
					totalProcessed++;
				}
			}
			System.out.println("Total processed: " + totalProcessed + ", Size: " + gridArray.length);
		}
	}
public String encode (int pit, kalahArrayClass grid2, boolean side, String gameNumber, String groupNumber){
	String L1 = Byte.toString(grid2.getGrid()[1][1]);
	String L2 = Byte.toString(grid2.getGrid()[1][2]);
	String L3 = Byte.toString(grid2.getGrid()[1][3]);
	String L4 = Byte.toString(grid2.getGrid()[1][4]);
	String L5 = Byte.toString(grid2.getGrid()[1][5]);
	String L6 = Byte.toString(grid2.getGrid()[1][6]);
	String Lstore = Byte.toString(grid2.getGrid()[1][7]);
	
	String R1 = Byte.toString(grid2.getGrid()[0][6]);
	String R2 = Byte.toString(grid2.getGrid()[0][5]);
	String R3 = Byte.toString(grid2.getGrid()[0][4]);
	String R4 = Byte.toString(grid2.getGrid()[0][3]);
	String R5 = Byte.toString(grid2.getGrid()[0][2]);
	String R6 = Byte.toString(grid2.getGrid()[0][1]);
	String Rstore = Byte.toString(grid2.getGrid()[0][0]);
	if (side == true){
	
		
		return (gameNumber + " " + "group" + groupNumber + " " + "L" + pit + " " + L1 + " " + L2 + " " + L3 + " " + L4 + " " + L5 + " " + L6 + " " + Lstore + " " + R1 + " " + R2 + " " + R3 + " " + R4 + " " + R5 + " " + R6 + " " + Rstore);
	}
	else if (side == false){
		if (pit == 1){
			pit = 6;
		}
		else if (pit == 2){
			pit = 5;
		}
		else if (pit == 3){
			pit = 4;
		}
		else if (pit == 4){
			pit = 3;
		}
		else if (pit == 5){
			pit = 2;
		}
		else if (pit == 6){
			pit = 1;
		}
		
		return (gameNumber + " " + "group" + groupNumber + " " + "L" + pit + " " + L1 + " " + L2 + " " + L3 + " " + L4 + " " + L5 + " " + L6 + " " + Lstore + " " + R1 + " " + R2 + " " + R3 + " " + R4 + " " + R5 + " " + R6 + " " + Rstore);

	}
	else{
		
	}
	return null;
	
}
}
