import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class KMain {
	
	public static byte [][] gridInit = new byte[][] { 
	
	{0,1,1,1,0,3,3,0},
	{0,1,2,1,0,3,3,0}
	};
	
	
	private static boolean print = true;
	
	public static kalahArrayClass grid = new kalahArrayClass(gridInit, false, false, false);
	public static kalahArrayClass grid2 = new kalahArrayClass(gridInit, false, false, false);
	
	public static int numofrows = 2;
	public static int numofcols = 5; 
	public static int numofseeds = 3;
	//This is for you Chris
	public static boolean wePlayerOne = true;
	
	//BufferedReader to get user input
		private static BufferedReader seedCalc = null;
		private static BufferedReader commandInput = null;
		
	public static void main (String[] args) throws java.lang.Exception
	{
		int sumPlayerone = 0;
		int sumPlayertwo = 0;
		
		for (int i = 1; i < numofcols - 1;i++){
			sumPlayertwo += gridInit [numofrows - 2][i];
		}
		for (int i = 1; i < numofcols - 1;i++){
			sumPlayerone += gridInit [numofrows - 1][i];
		}
		
		seedCalc = new BufferedReader(new InputStreamReader(System.in));
		commandInput = new BufferedReader(new InputStreamReader(System.in));
		
		kalahFunctions functions = new kalahFunctions();
		functions.sum = sumPlayertwo+sumPlayerone;
		
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
		System.out.println("Command List:\nh: Select a pit to move\nr: Random\nar: All Random\ns: Solve Grid");
		boolean allRandom = false;
		String command = null;
		while(seedsRemain > seeds ){
			if (grid2.isPlayerTwoTurn()){
				turn = -1;
			}
			else if (!grid2.isPlayerTwoTurn()){
				turn = 1;
			}
			if (!allRandom){
				System.out.println("Enter Command: ");
				command = commandInput.readLine();
			}
			
			//Human Move
			if (command.equals("h")){
				System.out.println("Enter in pit number: ");
				command = commandInput.readLine();
				int pit = Integer.parseInt(command);
				
				if (turn == 1){
					if (functions.canPlace(grid2, 1, pit)){
						functions.playerOnePlace(grid2, 1, pit, allGrids, allGridsKeys);
						if (!allGrids.get(grid2).isEmpty()){
							seedsRemain = 36 - (allGrids.get(grid2).get(0).getGrid().getGrid()[0][0] + allGrids.get(grid2).get(0).getGrid().getGrid()[numofrows - 1][numofcols - 1]);
						}
						//grid2 = allGrids.get(grid2).get(0).getGrid();
						kalahOption move = null;
						LinkedList<kalahOption> keyGridList = allGrids.get(grid2);
						for (int j = 0; j < keyGridList.size(); j++)
						{
							if (keyGridList.get(j).getRowOne() == 1 && keyGridList.get(j).getColOne() == pit){
								move = keyGridList.get(j);
							}
						}
						grid2 = allGridsKeys.get(move.getGrid());
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
					if (functions.canPlace(grid2, 0, pit)){
						functions.playerTwoPlace(grid2, 0, pit, allGrids, allGridsKeys);
						if (!allGrids.get(grid2).isEmpty()){
							seedsRemain = 36 - (allGrids.get(grid2).get(0).getGrid().getGrid()[0][0] + allGrids.get(grid2).get(0).getGrid().getGrid()[numofrows - 1][numofcols - 1]);
						}
						//grid2 = allGrids.get(grid2).get(0).getGrid();
						kalahOption move = null;
						LinkedList<kalahOption> keyGridList = allGrids.get(grid2);
						for (int j = 0; j < keyGridList.size(); j++)
						{
							if (keyGridList.get(j).getRowOne() == 0 && keyGridList.get(j).getColOne() == pit){
								move = keyGridList.get(j);
							}
						}
						grid2 = allGridsKeys.get(move.getGrid());
						if (grid2.isPlayerTwoTurn()){
							
						}
						else {
							turn = turn*-1;
						}
						
						
					}
					else {
						
						
					}
				}
			}
			//Random
			else if (command.equals("r")){
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
			}
			//All Random
			else if (command.equals ("ar")){
				command = "r";
				allRandom = true;
			}
			//Solve Hash
			else if (command.equals("s")){
				functions.buildHash(allGrids, allGridsKeys, grid.clone());
				System.out.println("\nRoot: " + allGridsKeys.get(grid));
				System.out.println("\nHashmap Size: " + allGridsKeys.size());
				
				if (print == true){
					System.out.println("\n\n\nPrint out from hashmap now:");
					Iterator<kalahArrayClass> iterator = allGrids.keySet().iterator();
					while (iterator.hasNext()) {
					   System.out.println("\n" + iterator.next());
					}
					System.out.println("\nDone printing Hashmap");
				}
				
				while (grid2.isPlayerTwoTurn() != wePlayerOne) {
					kalahOption move = functions.findMove(allGrids, allGridsKeys, grid2.clone());
					System.out.println("\nThe move: " + move);
					grid2 = allGridsKeys.get(move.getGrid());
				}
			}
			else if (command.equals("ls")){
				allGrids.clear();
				allGridsKeys.clear();
				kalahFunctions functions1 = new kalahFunctions();
				functions1.sum = sumPlayertwo+sumPlayerone;
				LinkedList<kalahOption> startGridList1 = new LinkedList<kalahOption>();
				allGridsKeys.put(grid2, grid2);
				allGrids.put(grid2, startGridList1);
				
				functions1.limitedbuildHash(allGrids, allGridsKeys, grid2.clone(), (byte) 4);
				while (grid2.isPlayerTwoTurn() != wePlayerOne) {
					kalahOption move = functions1.limitedFindMove(allGrids, allGridsKeys, grid2.clone());
					System.out.println("\nThe move: " + move);
					grid2 = allGridsKeys.get(move.getGrid());
				}
				grid2.setTurnCounter((byte) 1);
			}
			
			else if (command.equals("fls")) {
				functions.fulllimitedbuildHash(allGrids, allGridsKeys, grid2.clone(), (byte) 4);
				
				if (print == true){
					System.out.println("\n\n\nPrint out from hashmap now:");
					Iterator<kalahArrayClass> iterator = allGrids.keySet().iterator();
					while (iterator.hasNext()) {
					   System.out.println("\n" + iterator.next());
					}
					System.out.println("\nDone printing Hashmap");
				}
				
				while (grid2.isPlayerTwoTurn() != wePlayerOne) {
					kalahOption move = functions.findMove(allGrids, allGridsKeys, grid2.clone());
					System.out.println("\nThe move: " + move);
					grid2 = allGridsKeys.get(move.getGrid());
				}
			}
			else if (command.equals("m")){
				while (grid2.isPlayerTwoTurn() != wePlayerOne) {
					kalahOption move = functions.findMove(allGrids, allGridsKeys, grid2.clone());
					System.out.println("\nThe move: " + move);
					grid2 = allGridsKeys.get(move.getGrid());
				}
			}
			else{
				
			}
			
			
			
			
			count++;
			System.out.println(grid2);
		}
		System.out.println(seedsRemain);

	
		
		//functions.buildHash(allGrids, allGridsKeys, grid.clone());
		
		if (print == true){
			System.out.println("\n\n\nPrint out from hashmap now:");
			Iterator<kalahArrayClass> iterator = allGrids.keySet().iterator();
			while (iterator.hasNext()) {
			   System.out.println("\n" + iterator.next());
			}
			System.out.println("\nDone printing Hashmap");
		}
		
		System.out.println("\nRoot: " + allGridsKeys.get(grid));
		System.out.println("\nHashmap Size: " + allGridsKeys.size());
		
		
	}
}
