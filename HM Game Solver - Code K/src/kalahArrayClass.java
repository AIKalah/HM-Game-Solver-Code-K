
public class kalahArrayClass {
	byte grid[][];
	boolean turn;
	
	public kalahArrayClass(byte[][] gridInit, boolean playerTurn) {
		grid = gridInit;
		setTurn(playerTurn);
	}
	
	public boolean isTurn() {
		return turn;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
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
		  if (turn == false){
			  returnString += "Turn: " + "Player 1";
		  }
		  else {
			  returnString += "Turn: " + "Player 2";
		  }
		  
		  return returnString;
		}
	
	public kalahArrayClass clone(){
		kalahArrayClass clonedGrid = new kalahArrayClass(this.grid, this.turn);
		return clonedGrid;
	}
}
