
public class kalahArrayClass {
	byte grid[][];
	Boolean playerTwoTurn;
	
	public kalahArrayClass(byte[][] gridInit, boolean playerTurn) {
		grid = gridInit;
		setPlayerTwoTurn(playerTurn);
	}
	
	public boolean isPlayerTwoTurn() {
		return playerTwoTurn.booleanValue();
	}
	
	public void setPlayerTwoTurn(boolean turn) {
		this.playerTwoTurn = turn;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * 
	 * Overridden to ensure only the grid layout and the player turn are hashed together
	 */
		@Override
	  public int hashCode() {
	    int hash = java.util.Arrays.deepHashCode(grid) * this.playerTwoTurn.hashCode();
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
		  if (playerTwoTurn == false){
			  returnString += "Turn: " + "Player 1";
		  }
		  else {
			  returnString += "Turn: " + "Player 2";
		  }
		  
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
		
		kalahArrayClass clonedGrid = new kalahArrayClass(clonedGridInit, this.playerTwoTurn);
		return clonedGrid;
	}
}
