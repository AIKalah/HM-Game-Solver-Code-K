import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class KMain {
	
	public static byte [][] gridInit = new byte[][] { 
	
	{0,1,1,0,3,3,3,0},
	{0,1,1,0,3,3,3,0}
	};

	
	public static kalahArrayClass grid = new kalahArrayClass(gridInit, false, false, false);
	
	public static int numofrows = 2;
	public static int numofcols = 4; 
	public static int numofseeds = 3;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		
		kalahFunctions functions = new kalahFunctions();
		
		//Hash map to contain all the possible board layouts and their moves
		HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids = new HashMap<kalahArrayClass, LinkedList<kalahOption>>();

		//Hash map to contain all the possible board layouts contained in the first hash map to allow changes to the keys
		HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys = new HashMap<kalahArrayClass,kalahArrayClass>();
				
		System.out.println(grid);
		LinkedList<kalahOption> startGridList = new LinkedList<kalahOption>();
		allGridsKeys.put(grid, grid);
		allGrids.put(grid, startGridList);
		functions.buildHash(allGrids, allGridsKeys, grid);
		
		System.out.println("\n\n\nPrint out from hashmap now:");
		Iterator<kalahArrayClass> iterator = allGrids.keySet().iterator();
		while (iterator.hasNext()) {
		   System.out.println("\n" + iterator.next());
		}
		System.out.println("\nDone printing Hashmap");
	
	}
}
