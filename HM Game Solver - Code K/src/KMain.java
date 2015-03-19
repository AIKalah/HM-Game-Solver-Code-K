import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class KMain {
	
	public static byte [][] gridInit = new byte[][] { 
	
	{10,0,0,1,2,1,1,14},
	{10,1,1,1,1,1,2,14}
	};
	
	
	private static boolean print = true;
	
	public static kalahArrayClass grid = new kalahArrayClass(gridInit, false, false, false);
	public static kalahArrayClass grid2 = new kalahArrayClass(gridInit, false, false, false);
	
	public static int numofrows = 2;
	public static int numofcols = 8; 
	public static int numofseeds = 3;
	
	//BufferedReader to get user input
		private static BufferedReader seedCalc = null;
		
	public static void main (String[] args) throws java.lang.Exception
	{
		seedCalc = new BufferedReader(new InputStreamReader(System.in));
		kalahFunctions functions = new kalahFunctions();
		
		//Hash map to contain all the possible board layouts and their moves
		HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids = new HashMap<kalahArrayClass, LinkedList<kalahOption>>();

		//Hash map to contain all the possible board layouts contained in the first hash map to allow changes to the keys
		HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys = new HashMap<kalahArrayClass,kalahArrayClass>();
				
		System.out.println(grid);
		LinkedList<kalahOption> startGridList = new LinkedList<kalahOption>();
		allGridsKeys.put(grid, grid);
		allGrids.put(grid, startGridList);
		//Start a random test
		Random randMove = new Random();
		int count = 0;
		allGrids.get(grid);
		grid2 = grid.clone();
		int turn = 1;
		int seedsRemain = 36;
		System.out.println("Enter In Number of Seeds Until Calculation: ");
		String seedCalcs = seedCalc.readLine();
		int seeds = Integer.parseInt(seedCalcs);
		while(seedsRemain > seeds ){
			int move = randMove.nextInt(((6 - 1) + 1)) + 1;
			System.out.println(move);
			
			
			if (turn == 1){
				if (functions.canPlace(grid2, 1, move)){
					functions.playerOnePlace(grid2, 1, move, allGrids, allGridsKeys);
					if (!allGrids.get(grid2).isEmpty()){
						seedsRemain = 36 - (allGrids.get(grid2).get(0).getGrid().getGrid()[0][0] + allGrids.get(grid2).get(0).getGrid().getGrid()[numofrows - 1][numofcols - 1]);
					}
					grid2 = allGrids.get(grid2).get(0).getGrid();
					if (grid2.isPlayerTwoTurn() == false){
						
					}
					else {
						turn = turn*-1;
					}
					
					
				}
				else {
					
				}
			}
			else if (turn == -1){
				if (functions.canPlace(grid2, 0, move)){
					functions.playerTwoPlace(grid2, 0, move, allGrids, allGridsKeys);
					if (!allGrids.get(grid2).isEmpty()){
						seedsRemain = 36 - (allGrids.get(grid2).get(0).getGrid().getGrid()[0][0] + allGrids.get(grid2).get(0).getGrid().getGrid()[numofrows - 1][numofcols - 1]);
					}
					grid2 = allGrids.get(grid2).get(0).getGrid();
					if (grid2.isPlayerTwoTurn()){
						
					}
					else {
						turn = turn*-1;
					}
					
					
				}
				else {
					
					
				}
			}
			
			
			count++;
		}
		System.out.println(grid2);
		System.out.println(seedsRemain);
		functions.buildHash(allGrids, allGridsKeys, grid2);
		
		if (print == true){
			System.out.println("\n\n\nPrint out from hashmap now:");
			Iterator<kalahArrayClass> iterator = allGrids.keySet().iterator();
			while (iterator.hasNext()) {
			   System.out.println("\n" + iterator.next());
			}
			System.out.println("\nDone printing Hashmap");
		}
		
	
	}
}
