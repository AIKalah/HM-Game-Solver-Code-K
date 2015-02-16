import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
	
	//Starting grid which can be manually set for testing
	public static byte [][] gridInit = new byte[][] { 
			
			{1,1,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,0,0},
			{0,0,0,1,1}
					
			};
	
	//Creates a new gridArrayClass object using
	public static gridArrayClass grid = new gridArrayClass(gridInit, true, false);
	
	//Size of starting grid which can also be manually changed for testing
	public static int numofrows = 5;
	public static int numofcols = 5; 
	
	//BufferedReader to get user input
	private static BufferedReader inputLine = null;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		//BufferedReader to get user input
		inputLine = new BufferedReader(new InputStreamReader(System.in));
		
		//Creating an instance of functions and passing the grid size
		Functions functionsInstance = new Functions();
		functionsInstance.setNumofcols(numofcols);
		functionsInstance.setNumofrows(numofrows);
		
		//Hash map to contain all the possible board layouts and their moves
		HashMap<gridArrayClass, LinkedList<Option>> allGrids = new HashMap<gridArrayClass, LinkedList<Option>>();

		//Hash map to contain all the possible board layouts contained in the first hash map to allow changes to the keys
		HashMap<gridArrayClass,gridArrayClass> allGridsKeys = new HashMap<gridArrayClass,gridArrayClass>();
		
		
		String path;
		path = "Learn.data";
		FileReader fr = new FileReader(path);
		FileReader fr2 = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		BufferedReader buffer = new BufferedReader(fr2);
		String line;
		int numberOfLines = 0;
		while ((line = buffer.readLine()) != null){
			numberOfLines++;
		}
		String [] textData = new String[numberOfLines];
		for (int i = 0; i < numberOfLines; i++){
			textData[i] = textReader.readLine();
		}
		int j = 0;
		while (j < numberOfLines){
		String parts [] = textData[j].split(",");
		String master = parts[0];
		String move = parts[1];
		String bool = parts[2];
		//System.out.println(bool);
		functionsInstance.learnCompile(master, move,bool);
		j++;
		}
	
		//functionsInstance.print();
		
		
		System.out.println("Initial board config:");
		String initialPegs = null;
		while(initialPegs == null){		
			initialPegs = inputLine.readLine();
		}
		if (initialPegs.equals("A1A1")){
			
		}
		else{
		functionsInstance.parseMove(initialPegs, grid);
		}
		System.out.println("Starting board:\n" + grid);
		
		//First asks if we play first
		boolean boardSolved = false;
		System.out.println("Our move first? Y/N");
		String moveFirst = null;
		while(moveFirst == null){		
			moveFirst = inputLine.readLine();
		}
		//If we play first
		if (moveFirst.equals("Y")){
			String worstReturn = functionsInstance.checkForWorst(grid);
			String winReturn = null;
			//If it's an easier board then find the move normally
			if(worstReturn.equals("N")){
				System.out.println("Solving... and Learning starting grid");
				LinkedList<Option> startGridList = new LinkedList<Option>();
				allGridsKeys.put(grid, grid);
				allGrids.put(grid, startGridList);
				functionsInstance.solve(allGrids, allGridsKeys, grid);
				System.out.println("Starting Grid\n" + grid);
				boardSolved = true;
				String theMove = functionsInstance.findMove(allGrids, allGridsKeys, grid);
				try {
				    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Learn.data", true)));
				    out.println(initialPegs + "," + theMove + "," + grid.getWin());
				    out.close();
				} catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
				System.out.println("Our Move: " + theMove);
				functionsInstance.parseMove(theMove, grid);
			}
			//If the starting grid that was set would take a long time to calculate
			//Get a pre-calculated move

			else{
				
				System.out.println("Worst case starting grid.");
				System.out.println("Our Move: " + worstReturn);
				winReturn = functionsInstance.checkForWin(grid);
				System.out.println(winReturn);
				boolean win;
			    if (winReturn.equals("true")){
			    	
			    	win = true;
			    }
			    else {
			    	win = false;
			    }
				
				grid.setWin(win);
				functionsInstance.parseMove(worstReturn, grid);
			}

			
			System.out.println("Their board:\n" + grid);
		}
		//Ask for the other player's move manually for testing
		while (true){
			System.out.println("What is their move?");
			String theirMove = null;
			while(theirMove == null){		
				theirMove = inputLine.readLine();
			}
			//If the board has been solved already, simply find the next move
			if (boardSolved){
			functionsInstance.parseMove(theirMove, grid);
			System.out.println("Our board:\n" + allGridsKeys.get(grid));
			String theMove = functionsInstance.findMove(allGrids, allGridsKeys, grid);
			System.out.println("Our Move: " + theMove);
			functionsInstance.parseMove(theMove, grid);
			System.out.println("Their board:\n" + allGridsKeys.get(grid));
			}
			//If the board hasn't been solved yet, solve it
			else{
				functionsInstance.parseMove(theirMove, grid);
				LinkedList<Option> startGridList = new LinkedList<Option>();
				allGridsKeys.put(grid, grid);
				allGrids.put(grid, startGridList);
				System.out.println("Our board:\n" + allGridsKeys.get(grid));
				functionsInstance.solve(allGrids, allGridsKeys, grid);
				boardSolved = true;
				String theMove = functionsInstance.findMove(allGrids, allGridsKeys, grid);
				System.out.println("Our Move: " + theMove);
				functionsInstance.parseMove(theMove, grid);
				System.out.println("Their board:\n" + allGridsKeys.get(grid));
			}
		}
	}
}
