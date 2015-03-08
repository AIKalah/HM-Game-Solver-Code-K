
public class KMain {
	
	public static byte [][] gridInit = new byte[][] { 
	
	{0,3,2,0,3,3,3,0},
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
		//functions.playerOneTurn(grid);
		functions.playerTwoTurn(grid);
	}
}
