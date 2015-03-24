import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.source.TwitterMessenger;

public class KMainTwitter {
	
	public static byte [][] gridInit = new byte[][] { 
	
	{0,3,3,3,3,3,3,0},
	{0,3,3,3,3,3,3,0}
	};
	
	
	private static boolean print = true;
	
	public static kalahArrayClass grid = new kalahArrayClass(gridInit, false, false, false);
	public static kalahArrayClass grid2 = new kalahArrayClass(gridInit, false, false, false);
	
	public static int numofrows = 2;
	public static int numofcols = 8; 
	public static int numofseeds = 3;
	public static TwitterMessenger tweeter = new TwitterMessenger(2);
	//BufferedReader to get user input
		private static BufferedReader seedCalc = null;
		private static BufferedReader commandInput = null;
		private static BufferedReader first = null;
		
	public static void main (String[] args) throws java.lang.Exception
	{
		
		seedCalc = new BufferedReader(new InputStreamReader(System.in));
		commandInput = new BufferedReader(new InputStreamReader(System.in));
		first = new BufferedReader(new InputStreamReader(System.in));
	
		kalahFunctions functions = new kalahFunctions();
		
		//Hash map to contain all the possible board layouts and their moves
		HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids = new HashMap<kalahArrayClass, LinkedList<kalahOption>>();

		//Hash map to contain all the possible board layouts contained in the first hash map to allow changes to the keys
		HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys = new HashMap<kalahArrayClass,kalahArrayClass>();
				
		System.out.println(grid);
		LinkedList<kalahOption> startGridList = new LinkedList<kalahOption>();
		allGridsKeys.put(grid, grid);
		allGrids.put(grid, startGridList);
		
		//Twitter Test
		boolean sidePass = true;
		System.out.println("Type start to make a game, and accept to handshake one: ");
		String game = commandInput.readLine();
		System.out.println("Are we going first?");
		String side = commandInput.readLine();
		System.out.println("What game number?: ");
		String gameNumber = commandInput.readLine();
		System.out.println("What group are you facing?: ");
		String groupNumber = commandInput.readLine();
		if (game.equals("start")){
			if (side.equals("y")){
				sidePass = true;
				String invite = gameNumber + " " + "group2" + " " + "group"+groupNumber;
				System.out.println(invite);
				//tweeter.post("cdbh2", invite);
			}
			else if (side.equals("n")){
				sidePass = false;
				String invite = gameNumber + " " + "group"+groupNumber + " " + "group2";
				System.out.println(invite);
				//tweeter.post("cdbh2", invite);
			}
			else{
				
			}
		}
		else if (game.equals("accept")){
			if (side.equals("y")){
				sidePass = true;
				String invite = gameNumber + " " + "group2" + " " + "group"+groupNumber+" accepted";
				System.out.println(invite);
				//tweeter.post("cdbh2", invite);
			}
			else if (side.equals("n")){
				sidePass = false;
				String invite = gameNumber + " " + "group"+groupNumber + " " + "group2"+" accepted";
				System.out.println(invite);
				//tweeter.post("cdbh2", invite);
			}
			else{
				
			}
		}
		//////////////////////
		
		
		
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
		System.out.println("Are you going first?");
		String firstTurn = first.readLine();
		System.out.println("Command List:\nh: Select a pit to move\nr: Random\nar: All Random\ns: Solve Grid");
		boolean allRandom = false;
		String command = null;
		while(seedsRemain > seeds ){
			if (!allRandom){
				if (firstTurn.equals("n")){
					//grid.setPlayerTwoTurn(true);
					//turn = turn*-1;
					command = "h";
				}
				else{
					System.out.println("Enter Command: ");
					command = commandInput.readLine();
				}
				
			}
			
			//Human Move
			if (command.equals("h")){
				firstTurn = "y";
				System.out.println("Enter in pit number: ");
				command = commandInput.readLine();
				int pit = Integer.parseInt(command);
				if (turn == 1){
					if (functions.canPlace(grid2, 1, pit)){
						functions.playerOnePlace(grid2, 1, pit, allGrids, allGridsKeys);
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
					String move = functions.encode(pit,grid2,sidePass,gameNumber,"2");
					System.out.println(move);
				}
				else if (turn == -1){
					if (functions.canPlace(grid2, 0, pit)){
						functions.playerTwoPlace(grid2, 0, pit, allGrids, allGridsKeys);
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
					String move = functions.encode(pit,grid2,sidePass,gameNumber,"2");
					System.out.println(move);
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
				break;
			}
			else{
				
			}
		
			
			
			
			
			count++;
			System.out.println(grid2);
		}
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
