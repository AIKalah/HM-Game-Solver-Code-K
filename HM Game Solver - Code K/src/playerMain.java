//package com.runtime;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;



public class playerMain {
private static byte [][] gridInit = new byte[][] { 
		
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,0,0,0,0},
		{0,0,0,0,0}
				
		};

	public static gridArrayClass grid = new gridArrayClass(gridInit, false, false);
	
	static boolean boardSolved = false;
	
	private static Functions f = new Functions ();
	
	// The client socket
		private static Socket clientSocket = null;
		// The output stream
		private static PrintStream os = null;
		// The input stream
		private static DataInputStream is = null;

		private static BufferedReader inputLine = null;
		
		private static String input;
		
		private static boolean inGame = false;
		
		private static String gameID;
		
		private static String turn;
		private static String boardAsString;
		private static String previousMove;
		public static void main(String[] args) throws UnknownHostException, IOException{
			
			HashMap<gridArrayClass, LinkedList<Option>> allGrids = new HashMap<gridArrayClass, LinkedList<Option>>();

			HashMap<gridArrayClass,gridArrayClass> allGridsKeys = new HashMap<gridArrayClass,gridArrayClass>();
			
			//////////////////////////////////////////////////
			// JOIN SERVER
			//////////////////////////////////////////////////
			
			clientSocket = new Socket("beastMode.ddns.net", 63400);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
				
			System.out.println("Connection Successful!");
			
			os.println("Player"); // for server side info
			
			System.out.println("Enter your name ( char : not allowed) . . .");
			String pName = null;
			while(pName == null){		
					
				pName = inputLine.readLine();
				
			}
			os.println("NEWGAME:" + pName);
			System.out.println("NEWGAME:" + pName);
			
			while(!inGame){
				
				input = null;
				
				try {
					input = getMessage();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(input != null){
					
					if(input.length() > pName.length()){
						if(input.substring(0, pName.length()).equals(pName)){
							
							String mid[] = input.split(":");
							gameID = mid[1];
							
							System.out.println(gameID + " is my game ID");
							
							inGame = true;
							
						}
					}
				}
				
			}
			
			///////////////////////////////////////////////////////
			// SERVER JOINED ... GIVEN GAME ID
			///////////////////////////////////////////////////////
			
			boolean gameWon = false;
			
			while(!gameWon){
				
				//////////////////////////////////////
				// Get who's turn it is, will keep listening for your turn
				/////////////////////////////////////
				
				os.println("GETTURN:" + gameID + ":P1");
				System.out.println("GETTURN:" + gameID + ":P1");
				boolean gotTurn = false;
				while(!gotTurn){
					
					input = null;
					
					input = getMessage();
					
					if(input != null){
						
						if(input.length() == 16){
							if(input.substring(0, 11).equals(gameID + ":")){
								
								String mid[] = input.split(":");
								String tCheck = mid[2];
								
								if(tCheck.equals("P1")){
									turn = mid[1];
								
									System.out.println("turn:" + turn);
								
									gotTurn = true;
								}
						
							}
							
						}
						
					}
					
				}
				
				////////////////////////////////////////////////
				// Turn received if its yours ... move
				////////////////////////////////////////////////
				
				if(turn.equals("P1")){ // its your turn
					
					// get current state of gameboard
					os.println("GETGAME:" + gameID + ":P1");
					boolean gotBoard = false;
					while(!gotBoard){
						
						input = null;
						
						input = getMessage();
						
						if(input != null){
							
							if(input.length() == 44){
								if(input.substring(0, 10).equals(gameID)){
									
									String mid[] = input.split(":");
									String pCheck = mid[2];
									
									if(pCheck.equals("P1")){
									
										boardAsString = mid[1];
										
										previousMove = mid[3];
										
										System.out.println("boardAsString: " + boardAsString);
										
										gotBoard = true;
										
									}
									
									
								}
								
							}
							
						}
						
					}
					
					
					// got board of game ... now prompt player move
					
					String pMove = move(allGrids, allGridsKeys);
					
					os.println(gameID + "P1" + pMove);	// send player move to master server
					
					// pause program to not overload master
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if(turn.equals("VA")){
					
					gameWon = true;
					
				}
				else{
					
					// pause program to not overload master
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				
			}
			
			System.out.println("Game is over.");
			os.println("/quit");
		
		
		}
		
		
		
		public static String getMessage() throws IOException{
			
			
			String message = is.readLine();
			
			
			if(message != null)
				return message;
			
			else
				return null;
			
		}
		
		
		public static String move(HashMap<gridArrayClass, LinkedList<Option>> allGrids, HashMap<gridArrayClass,gridArrayClass> allGridsKeys) throws IOException{ // can remove exception when user input is removed
			
			String playerMove = null;
			
			char boardMatrix[][] = new char[5][5];
			
			System.out.println("Board as matrix");
			
			for(int index = 0; index < 25; index++){
				
				boardMatrix[index%5][index/5] = boardAsString.charAt(index);
				if(index%5 == 4)
					System.out.print(boardMatrix[index%5][index/5] + "\n");
				else
					System.out.print(boardMatrix[index%5][index/5] + " ");
				
			}
			System.out.println("Previous move: " + previousMove);
			
			
			///////////////////////////////////////////////////////
			// 
			//
			// INSERT YOUR ALGORITHM BELOW
			//
			// THE MOVE MUST BE STRING IN FORMAT A1A2 WHERE A1 REPRESESNTS ONE SQUARE AND A2 THE OTHER
			// THE HORIZONTAL AXIS OF THE BOARD IS A -> E
			// THE VERTICAL AXIS OF THE BOARD IS 1 -> 5
			// THE LETTERS ARE CASE SENSITIVE
			// 
			//
			// NOTE THAT THE GIVEN MATRIX IS NUMBERED 0 -> 4 IN EACH DIMENSION
			// THE GIVEN MATRIX REPRESENTS THE CURRENT STATE OF THE BOARD
			// EX. A1 IS boardMatrix[0][0] AND E5 is boardMatrix[4][4]
			// CHAR O ON A COORD MEANS SPOT IS VACANT, R AND B REPRESENT THE PLAYER MOVES AND M THE GREY SQUARES
			// YOU CAN ONLY PLACE PIECES ON 2 ADJACENT O SPACES, IT IS YOUR RESPONSIBILITY TO MAKE SURE THE MOVE IS VALID
			//
			// NOTE ALONG WITH THE GIVE BOARD ... THE PREVIOUS MOVE IS AVAILABLE IN STRING previousMove
			//
			////////////////////////////////////////////////////////
			
			if (!boardSolved){
				System.out.println("Board not solved.");
				int row = 0;
				int col = 0;
				for (int i = 1; i <= boardAsString.length(); i++){

					//temp = 5*row;
					if (boardAsString.charAt(i - 1) == 'M' || boardAsString.charAt(i - 1) == 'R' || boardAsString.charAt(i - 1) == 'B'){
						gridInit [row][col] = 1;
					}
					col++;
					if ((i % 5) == 0){
						row++;
						col = 0;
					}
				}
				
				int numofrows = 5;
				int numofcols = 5;
				
				
				System.out.println("Starting board:\n" + grid);
				f.setNumofcols(numofcols);
				f.setNumofrows(numofrows);

				String worstReturn = f.checkForWorst(grid);
				if(worstReturn.equals("N")){
					System.out.println("Not a worst case starting grid.");
					LinkedList<Option> startGridList1 = new LinkedList<Option>();
					allGridsKeys.put(grid, grid);
					allGrids.put(grid, startGridList1);
					f.solve(allGrids, allGridsKeys, grid);
					boardSolved = true;
					playerMove = f.findMove(allGrids, allGridsKeys, grid);
					System.out.println("Our Move: " + playerMove);
					f.parseMove(playerMove, grid);
				}
				else{
					System.out.println("Worst case starting grid.");
					System.out.println("Our Move: " + worstReturn);
					f.parseMove(worstReturn, grid);
				}
				System.out.println("Their board:\n" + grid);
			}
			else{
				System.out.println("Board already solved.");
				f.parseMove(previousMove, grid);
				playerMove = f.findMove(allGrids, allGridsKeys, grid);
				System.out.println(playerMove);
			}	
					
					for(int i = 0; i < 5; i++)
					   {
					      for(int j = 0; j < 5; j++)
					      {
					         System.out.printf("%5d ", grid.grid[i][j]);
					      }
					      
					      System.out.println();
					   }
				      System.out.println("\n");
				      
			//////////////////////////////////////////////////////
			// END OF ALGORITHM
			//////////////////////////////////////////////////////
			
			return playerMove;
			
		}
}