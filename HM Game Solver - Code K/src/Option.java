public class Option {
	private gridArrayClass grid;
	private int rowOne;
	private int rowTwo;
	private int colOne;
	private int colTwo;
	
	
	/********************************************************************************************
	 * @param currentgrid - The current Grid
	 * @param row1 - First row move taken on grid
	 * @param col1 - First column move taken on grid
	 * @param row2 - Second row move taken on grid
	 * @param col2 - Second row move taken on grid
	 * 
	 * Object to save the current grid and also the move that was taken to make the grid happen
	 ********************************************************************************************/
	public Option (gridArrayClass currentgrid, int row1, int col1, int row2, int col2){
		setGrid (currentgrid);
		setRowOne (row1);
		setRowTwo (row2);
		setColOne (col1);
		setColTwo (col2);
	}



	/*
	 * 
	 * 
	 * Simple Getters and Setters Below
	 * 
	 */

	public gridArrayClass getGrid() {
		return grid;
	}


	public void setGrid(gridArrayClass grid) {
		this.grid = grid;
	}


	public int getRowOne() {
		return rowOne;
	}


	public void setRowOne(int rowOne) {
		this.rowOne = rowOne;
	}


	public int getRowTwo() {
		return rowTwo;
	}


	public void setRowTwo(int rowTwo) {
		this.rowTwo = rowTwo;
	}


	public int getColOne() {
		return colOne;
	}


	public void setColOne(int colOne) {
		this.colOne = colOne;
	}


	public int getColTwo() {
		return colTwo;
	}


	public void setColTwo(int colTwo) {
		this.colTwo = colTwo;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return "\n" + this.grid.toString() + ", " + this.rowOne + ", " 
				+ this.rowTwo + ", " + this.colOne + ", " + this.colTwo;
	}
}
