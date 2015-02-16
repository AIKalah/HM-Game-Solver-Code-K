public class gridArrayClass {
  byte grid[][];
  boolean win;
  boolean processed;
  float winningRatio;
  long totalChildren;
  
  /****************************************************************************************************
   * @param b - Grid at this point
   * @param condition - Can we win at this board position? True if YES, false if NO (DEFAULT TRUE)
   * @param processed - Has this board been processed yet? True if YES, False if NO (DEFAULT FALSE)
   ****************************************************************************************************/
  public gridArrayClass( byte b[][], boolean condition, boolean processed ) {
     grid = b;
     setWin(condition);
     setProcessed(processed);
     setWinningRatio(0);
     setTotalChildren(0);
  }
  
  /*
   * Getters and Setters
   */
	public boolean getWin() {
		return win;
	}
	
	public void setWin(boolean win) {
		this.win = win;
	}
	
	public boolean getProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	public float getWinningRatio() {
		return winningRatio;
	}

	public void setWinningRatio(float winningRatio) {
		this.winningRatio = winningRatio;
	}
	
	public long getTotalChildren() {
		return totalChildren;
	}

	public void setTotalChildren(long totalChildren) {
		this.totalChildren = totalChildren;
	}

/*
 * (non-Javadoc)
 * @see java.lang.Object#hashCode()
 * 
 * Overridden to ensure only the grids are compared with hashing, not the other variables
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
 * Overridden to ensure only the grids are compared with equals, not the other variables
 */
  @Override
  public boolean equals( Object b ) {
     if (!(b instanceof gridArrayClass)){
        return false;
     }
     if ( grid.length != ((gridArrayClass)b).grid.length ){
        return false;
     }
     for (int k = 0; k < grid.length; k++ ){
    	 if ( grid[k].length != ((gridArrayClass)b).grid[k].length ){
    	        return false;
    	 }
     }
     for (int i = 0; i < grid.length; i++ ){
    	 for (int j = 0; j < grid[i].length; j++ ){
    		 if (grid[i][j] != ((gridArrayClass)b).grid[i][j]){
    			 return false;
    		 }
    	 }
     }
     return true;
  }
  
  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   * 
   * Overridden to provide a legible System.out
   */
  public String toString(){
	  String returnString = "";
	  for (int i = 0; i < Main.numofrows; i++){
		  returnString += "[";
		  for (int j = 0; j < Main.numofcols; j++){
			  returnString += grid[i][j];
			  if (j != Main.numofcols - 1){
				  returnString += ", ";
			  }
		  }
		  if (i != Main.numofrows - 1){
			  returnString += "]\n";
		  }
	  }
	  returnString += "]";
	  returnString += "W: " + win;
	  returnString += ", P: " + processed;
	  returnString += ", R: " + winningRatio;
	  returnString += ", C: " + totalChildren;
	  return returnString;
	}
}