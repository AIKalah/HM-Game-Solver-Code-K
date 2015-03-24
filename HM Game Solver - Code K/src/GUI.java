import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBox;



public class GUI implements ActionListener {
	public static byte [][] gridInit = new byte[][] { 
		
		{0,1,1,1,0,3,3,0},
		{0,1,1,1,0,3,3,0}
		};
		
		
		private static boolean print = true;
		
		public static kalahArrayClass grid = new kalahArrayClass(gridInit, false, false, false);
		public static kalahArrayClass grid2 = new kalahArrayClass(gridInit, false, false, false);
		
		public static int numofrows = 2;
		public static int numofcols = 5; 
		public static int numofseeds = 3;
		
		private static BufferedReader seedCalc = null;
		private static BufferedReader commandInput = null;
		
		private static int turn = 1;
		private static int seedsRemain = 36;
		private static String command = null;
	//Player One Pits	
	public static JButton pitOnePlayerOne = new JButton("3");
	public static JButton pitTwoPlayerOne = new JButton("3");
	public static JButton pitThreePlayerOne = new JButton("3");
	public static JButton pitFourPlayerOne = new JButton("3");
	public static JButton pitFivePlayerOne = new JButton("3");
	public static JButton pitSixPlayerOne = new JButton("3");
	
	//Player Two Pits
	public static  JButton pitOnePlayerTwo = new JButton("3");
	public static JButton pitTwoPlayerTwo = new JButton("3");
	public static JButton pitThreePlayerTwo = new JButton("3");
	public static JButton pitFourPlayerTwo = new JButton("3");
	public static JButton pitFivePlayerTwo = new JButton("3");
	public static JButton pitSixPlayerTwo = new JButton("3");
	
	//Score Boxes
	public static JButton scorePlayerone = new JButton("0");
	public static JButton scorePlayertwo = new JButton("0");
	
	public static JButton solveGrid = new JButton("Solve");
	
	private int button;
	JFrame frame;
	
	//Hash map to contain all the possible board layouts and their moves
		private static HashMap<kalahArrayClass, LinkedList<kalahOption>> allGrids = new HashMap<kalahArrayClass, LinkedList<kalahOption>>();

		//Hash map to contain all the possible board layouts contained in the first hash map to allow changes to the keys
		private static HashMap<kalahArrayClass,kalahArrayClass> allGridsKeys = new HashMap<kalahArrayClass,kalahArrayClass>();
	/**
	 * Launch the application.
	 */
	public static void main (String[] args) throws java.lang.Exception
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		updateGUI(grid2);
		
		//Button Actions
		pitOnePlayerOne.addActionListener(new GUI(1));
		pitTwoPlayerOne.addActionListener(new GUI(2));
		pitThreePlayerOne.addActionListener(new GUI(3));
		pitFourPlayerOne.addActionListener(new GUI(4));
		pitFivePlayerOne.addActionListener(new GUI(5));
		pitSixPlayerOne.addActionListener(new GUI(6));
		
		pitOnePlayerTwo.addActionListener(new GUI(6));
		pitTwoPlayerTwo.addActionListener(new GUI(5));
		pitThreePlayerTwo.addActionListener(new GUI(4));
		pitFourPlayerTwo.addActionListener(new GUI(3));
		pitFivePlayerTwo.addActionListener(new GUI(2));
		pitSixPlayerTwo.addActionListener(new GUI(1));
		
		solveGrid.addActionListener(new GUI(7));
		
		seedCalc = new BufferedReader(new InputStreamReader(System.in));
		commandInput = new BufferedReader(new InputStreamReader(System.in));
	kalahFunctions functions = new kalahFunctions();
	
	
			
	System.out.println(grid);
	LinkedList<kalahOption> startGridList = new LinkedList<kalahOption>();
	allGridsKeys.put(grid, grid);
	allGrids.put(grid, startGridList);
	
	
	//Start a random test
			Random randMove = new Random();
			int count = 0;
			allGrids.get(grid);
			grid2 = grid.clone();
			System.out.println("Enter In Number of Seeds Until Calculation: ");
			String seedCalcs = seedCalc.readLine();
			int seeds = Integer.parseInt(seedCalcs);
			System.out.println("Command List:\nh: Select a pit to move\nr: Random\nar: All Random\ns: Solve Grid");
			boolean allRandom = false;
			
			while(seedsRemain > seeds ){
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
				updateGUI(grid2);
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
	
	public static void updateGUI (kalahArrayClass grid){
		//Player one update
		String p1p1 = Byte.toString(grid.getGrid()[1][1]);
		pitOnePlayerOne.setText(p1p1);
		String p2p1 = Byte.toString(grid.getGrid()[1][2]);
		pitTwoPlayerOne.setText(p2p1);
		String p3p1 = Byte.toString(grid.getGrid()[1][3]);
		pitThreePlayerOne.setText(p3p1);
		String p4p1 = Byte.toString(grid.getGrid()[1][4]);
		pitFourPlayerOne.setText(p4p1);
		String p5p1 = Byte.toString(grid.getGrid()[1][5]);
		pitFivePlayerOne.setText(p5p1);
		String p6p1 = Byte.toString(grid.getGrid()[1][6]);
		pitSixPlayerOne.setText(p6p1);
		
		//Player two update
		String p1p2 = Byte.toString(grid.getGrid()[0][6]);
		pitOnePlayerTwo.setText(p1p2);
		String p2p2 = Byte.toString(grid.getGrid()[0][5]);
		pitTwoPlayerTwo.setText(p2p2);
		String p3p2 = Byte.toString(grid.getGrid()[0][4]);
		pitThreePlayerTwo.setText(p3p2);
		String p4p2 = Byte.toString(grid.getGrid()[0][3]);
		pitFourPlayerTwo.setText(p4p2);
		String p5p2 = Byte.toString(grid.getGrid()[0][2]);
		pitFivePlayerTwo.setText(p5p2);
		String p6p2 = Byte.toString(grid.getGrid()[0][1]);
		pitSixPlayerTwo.setText(p6p2);
		
		//Update Player Score
		String p1score = Byte.toString(grid.getGrid()[0][7]);
		scorePlayerone.setText(p1score);
		String p2score = Byte.toString(grid.getGrid()[0][0]);
		scorePlayertwo.setText(p2score);
		if (!grid2.isPlayerTwoTurn()){
			pitOnePlayerTwo.setEnabled(false);
			pitTwoPlayerTwo.setEnabled(false);
			pitThreePlayerTwo.setEnabled(false);
			pitFourPlayerTwo.setEnabled(false);
			pitFivePlayerTwo.setEnabled(false);
			pitSixPlayerTwo.setEnabled(false);
			
			pitOnePlayerOne.setEnabled(true);
			pitTwoPlayerOne.setEnabled(true);
			pitThreePlayerOne.setEnabled(true);
			pitFourPlayerOne.setEnabled(true);
			pitFivePlayerOne.setEnabled(true);
			pitSixPlayerOne.setEnabled(true);
		}
		else {
			pitOnePlayerTwo.setEnabled(true);
			pitTwoPlayerTwo.setEnabled(true);
			pitThreePlayerTwo.setEnabled(true);
			pitFourPlayerTwo.setEnabled(true);
			pitFivePlayerTwo.setEnabled(true);
			pitSixPlayerTwo.setEnabled(true);
			
			pitOnePlayerOne.setEnabled(false);
			pitTwoPlayerOne.setEnabled(false);
			pitThreePlayerOne.setEnabled(false);
			pitFourPlayerOne.setEnabled(false);
			pitFivePlayerOne.setEnabled(false);
			pitSixPlayerOne.setEnabled(false);
		}
		System.out.println(grid2);
	}
	
	public GUI(int button)
	{
	    this.button = button;
	}
	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 300, 1008, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		
		pitOnePlayerOne.setBounds(133, 289, 110, 104);
		frame.getContentPane().add(pitOnePlayerOne);
		
		
		pitTwoPlayerOne.setBounds(253, 289, 110, 104);
		frame.getContentPane().add(pitTwoPlayerOne);
		
		
		pitThreePlayerOne.setBounds(373, 289, 110, 104);
		frame.getContentPane().add(pitThreePlayerOne);
		
		
		pitFourPlayerOne.setBounds(493, 289, 110, 104);
		frame.getContentPane().add(pitFourPlayerOne);
		
		
		pitFivePlayerOne.setBounds(613, 289, 110, 104);
		frame.getContentPane().add(pitFivePlayerOne);
		
	
		pitSixPlayerOne.setBounds(733, 289, 110, 104);
		frame.getContentPane().add(pitSixPlayerOne);
		
		
		pitSixPlayerTwo.setBounds(133, 11, 110, 104);
		frame.getContentPane().add(pitSixPlayerTwo);
		
		
		pitFivePlayerTwo.setBounds(253, 11, 110, 104);
		frame.getContentPane().add(pitFivePlayerTwo);
		
		
		pitFourPlayerTwo.setBounds(373, 11, 110, 104);
		frame.getContentPane().add(pitFourPlayerTwo);
		
		
		pitThreePlayerTwo.setBounds(493, 11, 110, 104);
		frame.getContentPane().add(pitThreePlayerTwo);
		
	
		pitTwoPlayerTwo.setBounds(613, 11, 110, 104);
		frame.getContentPane().add(pitTwoPlayerTwo);
		
		
		pitOnePlayerTwo.setBounds(733, 11, 110, 104);
		frame.getContentPane().add(pitOnePlayerTwo);
		
		
		scorePlayertwo.setEnabled(false);
		scorePlayertwo.setBounds(10, 147, 110, 104);
		frame.getContentPane().add(scorePlayertwo);
		
		
		scorePlayerone.setEnabled(false);
		scorePlayerone.setBounds(872, 147, 110, 104);
		frame.getContentPane().add(scorePlayerone);
		
		
		solveGrid.setBounds(488, 188, 89, 23);
		frame.getContentPane().add(solveGrid);
	
	}
	
	public void actionPerformed(ActionEvent e) {
		kalahFunctions functions = new kalahFunctions();
		int pit = 0;
		if(this.button==1)
		{
			 pit = 1;
		    System.out.println("One");//do button 1 stuff
		}
		else if(this.button==2)
		{
			 pit = 2;
		    System.out.println("Two");//do button 1 stuff
		}
		else if(this.button==3)
		{
			 pit = 3;
		    System.out.println("Three");//do button 1 stuff
		}
		else if(this.button==4)
		{
			 pit = 4;
		    System.out.println("Four");//do button 1 stuff
		}
		else if(this.button==5)
		{
			 pit = 5;
		    System.out.println("Five");//do button 1 stuff
		}
		else if(this.button==6)
		{
			 pit = 6;
		    System.out.println("Six");//do button 1 stuff
		}
		else if(this.button==7)
		{
			pit = 0;
			command = "s";
			//functions.buildHash(allGrids, allGridsKeys, grid2);
		    System.out.println("Seven");//do button 1 stuff
		}
		else if(this.button==8)
		{
		    System.out.println("Eight");//do button 1 stuff
		}
		if (pit == 0){
			
		}
		else {
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
		}
	}
		updateGUI(grid2);
	}
}
