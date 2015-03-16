
public class kalahOption {
	private kalahArrayClass grid;
	private int rowOne;
	private int colOne;
	
	
	/********************************************************************************************
	 * @param currentgrid - The current Grid
	 * @param row1 - First row move taken on grid
	 * @param col1 - First column move taken on grid
	 * @param row2 - Second row move taken on grid
	 * @param col2 - Second row move taken on grid
	 * 
	 * Object to save the current grid and also the move that was taken to make the grid happen
	 ********************************************************************************************/
	public kalahOption (kalahArrayClass currentgrid, int row, int col){
		setGrid (currentgrid);
		setRowOne (row);
		setColOne (col);
	}

	/*
	 * 
	 * 
	 * Simple Getters and Setters Below
	 * 
	 */

	public kalahArrayClass getGrid() {
		return grid;
	}

	public void setGrid(kalahArrayClass grid) {
		this.grid = grid;
	}

	public int getRowOne() {
		return rowOne;
	}

	public void setRowOne(int rowOne) {
		this.rowOne = rowOne;
	}

	public int getColOne() {
		return colOne;
	}

	public void setColOne(int colOne) {
		this.colOne = colOne;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "\n" + this.grid.toString() + ", " + this.rowOne + ", " 
				+ this.colOne;
	}
}
