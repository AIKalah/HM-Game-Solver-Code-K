
public class KMain {
	
	public static byte [][] gridInit = new byte[][] { 
	
	{0,9,2,0,3,3,3,0},
	{0,3,3,3,3,3,3,0}
	};

	
	public static kalahArrayClass grid = new kalahArrayClass(gridInit, false);
	
	public static int numofrows = 2;
	public static int numofcols = 8; 
	public static int numofseeds = 3;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		
		kalahFunctions functions = new kalahFunctions();
		System.out.println(grid);
		for (int i = 1; i < (numofcols - 1); i++){
			if (functions.canPlace(grid, 1, i)){
				functions.playerOnePlace(grid,1,i);
			}
		}
		System.out.println("\n\n Player 2 Test:\n");
		for (int i = numofcols - 2; i >= 1; i--){
			if (functions.canPlace(grid, 0, i)){
				functions.playerTwoPlace(grid,0,i);
			}
		}
	
	}
}
