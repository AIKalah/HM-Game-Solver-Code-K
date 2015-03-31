
public class kalahArrayClass {
	private byte grid[][];
	Boolean playerTwoTurn;
	boolean gameOver;
	boolean processed;
	String state;
	float averageScore;
	long totalChildren;
	long winResults;
	long lossResults;
	long tieResults;
	byte turnCounter;

	public kalahArrayClass(byte[][] gridInit, boolean playerTurn, boolean gameOver, boolean processed) {
		setGrid(gridInit);
		setPlayerTwoTurn(playerTurn);
		setGameOver(gameOver);
		setProcessed(processed);
		setState("UNKNOWN");
		setTotalChildren(0);
		setWinResults(0);
		setLossResults(0);
		setTieResults(0);
		setTurnCounter((byte)1);
	}
	
	public void reset(){
		setGameOver(false);
		setProcessed(false);
		setState("UNKNOWN");
		setTotalChildren(0);
		setWinResults(0);
		setLossResults(0);
		setTieResults(0);
		setTurnCounter((byte)1);
	}
	
	public byte[][] getGrid() {
		return this.grid;
	}

	public void setGrid(byte[][] grid) {
		this.grid = grid;
	}
	
	public byte getGridValue(int row, int col){
		return this.grid[row][col];
	}
	
	public void setGridValue(int row, int col, byte value){
		this.grid[row][col] = value;
	}

	public boolean isPlayerTwoTurn() {
		return this.playerTwoTurn.booleanValue();
	}
	
	public void setPlayerTwoTurn(boolean turn) {
		this.playerTwoTurn = turn;
	}
	
	public boolean isGameOver() {
		return this.gameOver;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public boolean isProcessed() {
		return this.processed;
	}
	
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	public int getPlayerOneScore(){
		return this.grid[0][KMain.numofcols-1];
	}
	
	public void setPlayerOneScore(byte score){
		this.grid[0][KMain.numofcols-1] = score;
		this.grid[1][KMain.numofcols-1] = score;
	}
	
	public int getPlayerTwoScore(){
		return this.grid[0][0];
	}
	
	public void setPlayerTwoScore(byte score){
		this.grid[0][0] = score;
		this.grid[1][0] = score;
	}
	
	public int getScoreDifference(){
		return this.getPlayerOneScore() - this.getPlayerTwoScore();
		//return (byte) ((byte)(this.grid[0][0]) - (byte) (this.grid[0][grid[0].length-1]));
	}
	
	public float getAverageScore() {
		return this.averageScore;
	}

	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	
	public long getTotalChildren() {
		return this.totalChildren;
	}

	public void setTotalChildren(long totalChildren) {
		this.totalChildren = totalChildren;
	}
	
	public String getState(){
		return this.state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public long getWinResults() {
		return this.winResults;
	}

	public void setWinResults(long winResults) {
		this.winResults = winResults;
	}
	
	public long getLossResults() {
		return this.lossResults;
	}

	public void setLossResults(long lossResults) {
		this.lossResults = lossResults;
	}
	
	public long getTieResults() {
		return this.tieResults;
	}

	public void setTieResults(long tieResults) {
		this.tieResults = tieResults;
	}
	
	public byte getTurnCounter() {
		return this.turnCounter;
	}

	public void setTurnCounter(byte turnCounter) {
		this.turnCounter = turnCounter;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 
	 * Overridden to ensure only the grid layout and the player turn are hashed together
	 */
		@Override
	  public int hashCode() {
	    int hash = java.util.Arrays.deepHashCode(grid);
	    return hash;
	  }
		
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * Overridden to ensure only the grid layout and player turn are compared
	 */
	  @Override
	  public boolean equals( Object b ) {
		 //Check if other object is a kalahArrayClass object 
	     if (!(b instanceof kalahArrayClass)){
	        return false;
	     }
	     //Compare whose turn it is
	     if ( ((kalahArrayClass) b).isPlayerTwoTurn() != this.isPlayerTwoTurn()){
	    	 return false;
	     }
	     //Compare the size of the grid in rows/columns
	     if ( grid.length != ((kalahArrayClass)b).grid.length ){
	        return false;
	     }
	     //Compare the size of the grid in rows/columns
	     for (int k = 0; k < grid.length; k++ ){
	    	 if ( grid[k].length != ((kalahArrayClass)b).grid[k].length ){
	    	        return false;
	    	 }
	     }
	     //Compare each value of the grid
	     for (int i = 0; i < grid.length; i++ ){
	    	 for (int j = 0; j < grid[i].length; j++ ){
	    		 if (grid[i][j] != ((kalahArrayClass)b).grid[i][j]){
	    			 return false;
	    		 }
	    	 }
	     }
	     //If none of the checks failed, the boards are identical
	     return true;
	  }
	
	public String toString(){
		  String returnString = "";
		  for (int i = 0; i < KMain.numofrows; i++){
			  returnString += "[";
			  for (int j = 0; j < KMain.numofcols; j++){
				  returnString += grid[i][j];
				  if (j != KMain.numofcols - 1){
					  returnString += ", ";
				  }
			  }
			  if (i != KMain.numofrows - 1){
				  returnString += "]\n";
			  }
		  }
		  returnString += "]";
		  if (this.playerTwoTurn == false){
			  returnString += "Turn: " + "Player 1";
		  }
		  else {
			  returnString += "Turn: " + "Player 2";
		  }
		  returnString += ", P: " + this.processed;
		  returnString += ", P1: " + this.getPlayerOneScore();
		  returnString += ", P2: " + this.getPlayerTwoScore();
		  returnString += ", D: " + this.getScoreDifference();
		  returnString += ", C: " + this.totalChildren;
		  returnString += ", G: " + this.gameOver;
		  returnString += ", S: " + this.state;
		  returnString += ", AS: " + this.getAverageScore();
		  returnString += ", WR: " + this.getWinResults();
		  returnString += ", LR: " + this.getLossResults();
		  returnString += ", TR: " + this.getTieResults();
		  returnString += ", TC: " + this.getTurnCounter();
		  return returnString;
		}
	
	public kalahArrayClass clone(){
		byte clonedGridInit[][] = new byte[][] { 
				
				{0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0}
				};
		
		for(int i = 0; i < 2; i++){
			clonedGridInit[i] = this.grid[i].clone();
		}
		
		kalahArrayClass clonedGrid = new kalahArrayClass(clonedGridInit, this.playerTwoTurn, this.gameOver, this.processed);
		return clonedGrid;
	}
}
