import java.util.HashMap;
import java.util.Iterator;
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
				if (clonedGrid.getScoreDifference() == 0){
					clonedGrid.setState("TIE");
				}
				else{
					if (KMain.wePlayerOne){
						if (clonedGrid.getScoreDifference() > 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
					else{
						if (clonedGrid.getScoreDifference() < 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
				}
				clonedGrid.setProcessed(true);
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
				if (clonedGrid.getScoreDifference() == 0){
					clonedGrid.setState("TIE");
				}
				else{
					if (KMain.wePlayerOne){
						if (clonedGrid.getScoreDifference() > 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
					else{
						if (clonedGrid.getScoreDifference() < 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
				}
				clonedGrid.setProcessed(true);
			}
			
			if (clonedGrid.isProcessed() == false && clonedGrid.isPlayerTwoTurn() == true){
				clonedGrid.setTurnCounter((byte) (grid.getTurnCounter()+1));
			}
			else{
				clonedGrid.setTurnCounter((byte) (grid.getTurnCounter()));
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
				if (clonedGrid.getScoreDifference() == 0){
					clonedGrid.setState("TIE");
				}
				else{
					if (KMain.wePlayerOne){
						if (clonedGrid.getScoreDifference() > 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
					else{
						if (clonedGrid.getScoreDifference() < 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
				}
				clonedGrid.setProcessed(true);
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
				if (clonedGrid.getScoreDifference() == 0){
					clonedGrid.setState("TIE");
				}
				else{
					if (KMain.wePlayerOne){
						if (clonedGrid.getScoreDifference() > 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
					else{
						if (clonedGrid.getScoreDifference() < 0){
							clonedGrid.setState("WIN");
						}
						else{
							clonedGrid.setState("LOSS");
						}
					}
				}
				clonedGrid.setProcessed(true);
			}
			
			if (clonedGrid.isProcessed() == false && clonedGrid.isPlayerTwoTurn() == false){
				clonedGrid.setTurnCounter((byte) (grid.getTurnCounter()+1));
			}
			else{
				clonedGrid.setTurnCounter((byte) (grid.getTurnCounter()));
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
					//System.out.println("grid.isPlayertwoTurn: "+ grid.isPlayerTwoTurn());
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
						if (((kalahArrayClass) gridArray[i]).getScoreDifference() == 0){
							((kalahArrayClass) gridArray[i]).setState("TIE");
						}
						else{
							if (KMain.wePlayerOne){
								if (((kalahArrayClass) gridArray[i]).getScoreDifference() > 0){
									((kalahArrayClass) gridArray[i]).setState("WIN");
								}
								else{
									((kalahArrayClass) gridArray[i]).setState("LOSS");
								}
							}
							else{
								if (((kalahArrayClass) gridArray[i]).getScoreDifference() < 0){
									((kalahArrayClass) gridArray[i]).setState("WIN");
								}
								else{
									((kalahArrayClass) gridArray[i]).setState("LOSS");
								}
							}
						}
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
				//System.out.println("\ngridArray[" + i + "]: " + (kalahArrayClass) gridArray[i]);
				//System.out.println("allGridsKeys.get(((kalahArrayClass) gridArray[" + i + "])).isProcessed: " + allGridsKeys.get(((kalahArrayClass) gridArray[i])).isProcessed());
				if (!allGridsKeys.get(((kalahArrayClass) gridArray[i])).isProcessed()){
					LinkedList<kalahOption> keyGridList = allGrids.get(gridArray[i]);
					int winCount = 0;
					int lossCount = 0;
					int tieCount = 0;
					int scoreCount = 0;
					int processedCount = 0;
					long childrenCount = 0;
					long winResults = 0;//allGridsKeys.get(((kalahArrayClass) gridArray[i])).getWinResults();
					long lossResults = 0;//allGridsKeys.get(((kalahArrayClass) gridArray[i])).getLossResults();
					long tieResults = 0;//allGridsKeys.get(((kalahArrayClass) gridArray[i])).getTieResults();
					for (int j = 0; j < keyGridList.size(); j++)
					{
						//System.out.println("allGridsKeys.get(keyGridList.get(" + j + ")).isProcessed: " + allGridsKeys.get(keyGridList.get(j).getGrid()).isProcessed());
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
							if (keyGridList.get(j).getGrid().getState().equals("WIN")){
								winCount++;
								if (keyGridList.get(j).getGrid().isGameOver()){
									winResults += allGridsKeys.get(keyGridList.get(j).getGrid()).getWinResults() + 1;
								}
							}
							if (keyGridList.get(j).getGrid().getState().equals("LOSS")){
								lossCount++;
								if (keyGridList.get(j).getGrid().isGameOver()){
									lossResults += allGridsKeys.get(keyGridList.get(j).getGrid()).getLossResults() + 1;
								}
							}
							if (keyGridList.get(j).getGrid().getState().equals("TIE")){
								tieCount++;
								if (keyGridList.get(j).getGrid().isGameOver()){
									tieResults += allGridsKeys.get(keyGridList.get(j).getGrid()).getTieResults() + 1;
								}
							}
							//Sum scores only of game over boards? Sum over boards too for a divisor
							//scoreCount;
							childrenCount += allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren() + 1;
						}
					}
					//System.out.println("Processed Count: " + processedCount + " and Size: " + keyGridList.size());
					//System.out.println("Grid Before: " + allGridsKeys.get(((kalahArrayClass) gridArray[i])));
					if (processedCount == keyGridList.size()){
						if (winCount == keyGridList.size()){
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("WIN");
							((kalahArrayClass) gridArray[i]).setState("WIN");
						}
						else if (lossCount == keyGridList.size()){
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("LOSS");
							((kalahArrayClass) gridArray[i]).setState("LOSS");
						}
						
						else if (tieCount == keyGridList.size()){
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("TIE");
							((kalahArrayClass) gridArray[i]).setState("TIE");
						}
						else{
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("MIXED");
							((kalahArrayClass) gridArray[i]).setState("MIXED");
						}
						((kalahArrayClass) gridArray[i]).setProcessed(true);
						((kalahArrayClass) gridArray[i]).setAverageScore(scoreCount);
						((kalahArrayClass) gridArray[i]).setTotalChildren(childrenCount);
						((kalahArrayClass) gridArray[i]).setWinResults(winResults);
						((kalahArrayClass) gridArray[i]).setLossResults(lossResults);
						((kalahArrayClass) gridArray[i]).setTieResults(tieResults);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setProcessed(true);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setAverageScore(scoreCount);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setTotalChildren(childrenCount);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setWinResults(winResults);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setLossResults(lossResults);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setTieResults(tieResults);
					}
					else{
						((kalahArrayClass) gridArray[i]).setProcessed(false);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setProcessed(false);
					}
					//System.out.println("Grid After: " + allGridsKeys.get(((kalahArrayClass) gridArray[i])));
				}
				else{
					totalProcessed++;
				}
			}
			//System.out.println("Total processed: " + totalProcessed + ", Size: " + gridArray.length);
		}
	}
	public void limitedbuildHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys, kalahArrayClass grid, byte limit){
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
		//fillHash(allGrids, allGridsKeys);
		limitedfillHash(allGrids, allGridsKeys, limit);
	}
	
	public void buildHashStandAlone (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys, kalahArrayClass grid){
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
	}
	
	public int sum;
	public void limitedfillHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys, byte limit){         
		while (this.doneCount < this.gridArrayLength){
			System.out.println("\n" + this.doneCount + " is less than " + this.gridArrayLength);
			System.out.println("Free Memory: "+ (runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory()))/1024 );
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
				//System.out.println("TC: " + (allGridsKeys.get(gridArray[i]).getTurnCounter() < 7));
				if (allGrids.get(gridArray[i]).peekFirst() == null && allGridsKeys.get(gridArray[i]).getTurnCounter() < limit) //2 for ours, 3 for both turns, 4 for both plus ours
				{
					boolean placed = false;
					//playerOne
					kalahArrayClass grid = allGridsKeys.get(gridArray[i]);
					//LinkedList<kalahOption> keyGridList = allGrids.get(grid);
					//System.out.println("grid.isPlayertwoTurn: "+ grid.isPlayerTwoTurn());
					if (grid.isPlayerTwoTurn() == false){
						for (int j = 1; j < (numofcols - 1); j++){
							if (grid.getGrid()[1][j] == ((numofcols - 1) - j) /*|| grid.getGrid()[1][j] == ((numofcols) - j) + ((numofcols - 1) + (numofcols - 2)) */){
									if (this.canPlace(grid, 1, j)){
										this.playerOnePlace(grid,1,j,allGrids,allGridsKeys);
										placed = true;
									}
							}
						}
						
						for (int j = 1; j < (numofcols - 1); j++){
							if (!canPlace(grid,1,j)  && grid.getGrid()[0][j] != 0){
								for (int k= 1; k < numofcols - 1;k++){
									if (grid.getGrid()[1][k] == (j - k) /*|| (grid.getGrid()[1][k] - ((numofcols - 1) - k)) - (numofcols - 2) == j*/){
										if (this.canPlace(grid, 1, k))
										{
										this.playerOnePlace(grid,1,k,allGrids,allGridsKeys);
										placed = true;
										}
									}
								}
							 }
						}
					}
					//playerTwo grid.getGrid()[1][j] != 0
					else{
						for (int j = 1; j < numofcols - 1;j++){
							if (grid.getGrid()[0][j] - j == (0) /*|| (grid.getGrid()[0][j] - j) - ((numofcols - 1) + (numofcols -2)) == (0)*/){
								if (this.canPlace(grid, 0, j)){
									this.playerTwoPlace(grid,0,j,allGrids,allGridsKeys);
									placed = true;
								}
							}
						}
							
						for (int j = 1; j < (numofcols - 1); j++){
							if (!canPlace(grid,0,j) && grid.getGrid()[1][j] != 0){
								for (int k = 1; k < (numofcols - 1); k++){
									if (grid.getGrid()[0][k] == k - j /*||( (numofcols - 1) - ( (grid.getGrid()[0][k] - k) - (numofcols -2 ) ) ) == j*/){
										if (this.canPlace(grid, 0, k)){
											this.playerTwoPlace(grid,0,k,allGrids,allGridsKeys);
											placed = true;
										}
									}
								}
							}
							
							
						}
						
					}
					
					if (!placed)
					{
						kalahArrayClass tempGrid = (kalahArrayClass) gridArray[i];
						int compareScore = tempGrid.getGrid()[0][0] + tempGrid.getGrid()[numofrows - 1][numofcols - 1];
						if (compareScore != sum){
							buildHashStandAlone(allGrids, allGridsKeys, allGridsKeys.get(gridArray[i]));
						}
						else {
							kalahOption option = new kalahOption((kalahArrayClass)gridArray[i],0,0);
							allGrids.get(gridArray[i]).add(option);
							((kalahArrayClass) gridArray[i]).setGameOver(true);
							if (((kalahArrayClass) gridArray[i]).getScoreDifference() == 0){
								((kalahArrayClass) gridArray[i]).setState("TIE");
							}
							else{
								if (KMain.wePlayerOne){
									if (((kalahArrayClass) gridArray[i]).getScoreDifference() > 0){
										((kalahArrayClass) gridArray[i]).setState("WIN");
									}
									else{
										((kalahArrayClass) gridArray[i]).setState("LOSS");
									}
								}
								else{
									if (((kalahArrayClass) gridArray[i]).getScoreDifference() < 0){
										((kalahArrayClass) gridArray[i]).setState("WIN");
									}
									else{
										((kalahArrayClass) gridArray[i]).setState("LOSS");
									}
								}
							}
							allGridsKeys.get(gridArray[i]).setAverageScore(((kalahArrayClass) gridArray[i]).getScoreDifference());
							((kalahArrayClass) gridArray[i]).setProcessed(true);
						}
						
					}
				}
				else
				{
				
					//System.out.println(i + " is not null");
					this.doneCount++;
				}
			}
		}
		
		Object[] gridArray = allGrids.keySet().toArray();
		this.gridArrayLength = gridArray.length;
		for(int i = 0; i < this.gridArrayLength; i++) {
			if (allGridsKeys.get(gridArray[i]).getTurnCounter() == limit){
				if (((kalahArrayClass) gridArray[i]).getScoreDifference() == 0){
					((kalahArrayClass) gridArray[i]).setState("TIE");
				}
				else{
					if (KMain.wePlayerOne){
						if (((kalahArrayClass) gridArray[i]).getScoreDifference() > 0){
							((kalahArrayClass) gridArray[i]).setState("WIN");
						}
						else{
							((kalahArrayClass) gridArray[i]).setState("LOSS");
						}
					}
					else{
						if (((kalahArrayClass) gridArray[i]).getScoreDifference() < 0){
							((kalahArrayClass) gridArray[i]).setState("WIN");
						}
						else{
							((kalahArrayClass) gridArray[i]).setState("LOSS");
						}
					}
				}
				allGridsKeys.get(gridArray[i]).setAverageScore(((kalahArrayClass) gridArray[i]).getScoreDifference());
				allGridsKeys.get(gridArray[i]).setProcessed(true);
			}
		}
		
		limitedProcessHash(allGrids, allGridsKeys, limit);
	}
	
	public void limitedProcessHash (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys, byte limit){
		System.out.println("Processing Limited Hashes");
		Object[] gridArray = allGrids.keySet().toArray();
		this.gridArrayLength = gridArray.length;
		int totalProcessed = 0;
		while (totalProcessed < gridArray.length){
			totalProcessed = 0;
			for(int i = 0; i < this.gridArrayLength; i++) {
				if (i % 100000 == 0){
					System.out.println("Processing " + (i + 1) + " of " + gridArray.length + " keys/grids.");
				}
				//System.out.println("\ngridArray[" + i + "]: " + (kalahArrayClass) gridArray[i]);
				//System.out.println("allGridsKeys.get(((kalahArrayClass) gridArray[" + i + "])).isProcessed: " + allGridsKeys.get(((kalahArrayClass) gridArray[i])).isProcessed());
				if (!allGridsKeys.get(((kalahArrayClass) gridArray[i])).isProcessed()){
					LinkedList<kalahOption> keyGridList = allGrids.get(gridArray[i]);
					int winCount = 0;
					int lossCount = 0;
					int tieCount = 0;
					float scoreCount = 0;
					int processedCount = 0;
					long childrenCount = 0;
					long winResults = 0;//allGridsKeys.get(((kalahArrayClass) gridArray[i])).getWinResults();
					long lossResults = 0;//allGridsKeys.get(((kalahArrayClass) gridArray[i])).getLossResults();
					long tieResults = 0;//allGridsKeys.get(((kalahArrayClass) gridArray[i])).getTieResults();
					for (int j = 0; j < keyGridList.size(); j++)
					{
						//System.out.println("allGridsKeys.get(keyGridList.get(" + j + ")).isProcessed: " + allGridsKeys.get(keyGridList.get(j).getGrid()).isProcessed());
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
							winResults += allGridsKeys.get(keyGridList.get(j).getGrid()).getWinResults();
							if (keyGridList.get(j).getGrid().getState().equals("WIN")){
								winCount++;
								if (keyGridList.get(j).getGrid().isGameOver() || keyGridList.get(j).getGrid().getTurnCounter() == limit){
									winResults += 1;
								}
							}
							lossResults += allGridsKeys.get(keyGridList.get(j).getGrid()).getLossResults();
							if (keyGridList.get(j).getGrid().getState().equals("LOSS")){
								lossCount++;
								if (keyGridList.get(j).getGrid().isGameOver() || keyGridList.get(j).getGrid().getTurnCounter() == limit){
									lossResults += 1;
								}
							}
							tieResults += allGridsKeys.get(keyGridList.get(j).getGrid()).getTieResults();
							if (keyGridList.get(j).getGrid().getState().equals("TIE")){
								tieCount++;
								if (keyGridList.get(j).getGrid().isGameOver() || keyGridList.get(j).getGrid().getTurnCounter() == limit){
									tieResults += 1;
								}
							}
							//Sum scores only of game over boards? Sum over boards too for a divisor
							scoreCount += keyGridList.get(j).getGrid().getScoreDifference();
							childrenCount += allGridsKeys.get(keyGridList.get(j).getGrid()).getTotalChildren() + 1;
						}
					}
					//System.out.println("Processed Count: " + processedCount + " and Size: " + keyGridList.size());
					//System.out.println("Grid Before: " + allGridsKeys.get(((kalahArrayClass) gridArray[i])));
					if (processedCount == keyGridList.size()){
						if (winCount == keyGridList.size()){
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("WIN");
							((kalahArrayClass) gridArray[i]).setState("WIN");
						}
						else if (lossCount == keyGridList.size()){
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("LOSS");
							((kalahArrayClass) gridArray[i]).setState("LOSS");
						}
						
						else if (tieCount == keyGridList.size()){
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("TIE");
							((kalahArrayClass) gridArray[i]).setState("TIE");
						}
						else{
							allGridsKeys.get(((kalahArrayClass) gridArray[i])).setState("MIXED");
							((kalahArrayClass) gridArray[i]).setState("MIXED");
						}
						((kalahArrayClass) gridArray[i]).setProcessed(true);
						System.out.println("Score Count: " + scoreCount + " and size: " + keyGridList.size());
						((kalahArrayClass) gridArray[i]).setAverageScore(scoreCount/keyGridList.size());
						((kalahArrayClass) gridArray[i]).setTotalChildren(childrenCount);
						((kalahArrayClass) gridArray[i]).setWinResults(winResults);
						((kalahArrayClass) gridArray[i]).setLossResults(lossResults);
						((kalahArrayClass) gridArray[i]).setTieResults(tieResults);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setProcessed(true);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setAverageScore(scoreCount/keyGridList.size());
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setTotalChildren(childrenCount);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setWinResults(winResults);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setLossResults(lossResults);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setTieResults(tieResults);
					}
					else{
						((kalahArrayClass) gridArray[i]).setProcessed(false);
						allGridsKeys.get(((kalahArrayClass) gridArray[i])).setProcessed(false);
					}
				}
				else{
					totalProcessed++;
				}
			}
			//System.out.println("Total processed: " + totalProcessed + ", Size: " + gridArray.length);
		}
	}

	public String findMove (HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids, HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys, kalahArrayClass currentBoard){
		String theMove = "";
		/*byte moveRow1 = 1;
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
		}*/
		
		return theMove;
	}
	
}
