package test.gameplay;

import java.util.ArrayList;
import java.util.Scanner;
import application.board.Board;
import application.board.Cell;
import application.player.Player;

public class GamePlayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Board board = new Board();
		Player player1 = new Player(1, board);
		Player player2 = new Player(2, board);
		ArrayList<Cell> Player1CellOnSide = new ArrayList<Cell>();
		ArrayList<Cell> Player2CellOnSide = new ArrayList<Cell>();
        for (int i = 1; i <= 5; i++) { 
            Player1CellOnSide.add(board.getCellList().get(i));
        }
        for (int i = 7; i <= 11; i++) {
            Player2CellOnSide.add(board.getCellList().get(i));
        }
        player1.setCellsOnPlayerSide(Player1CellOnSide);
        player2.setCellsOnPlayerSide(Player2CellOnSide);
        player1.setInTurn(true);
		while(!isEndGame(board)) {
			printBoard(board);
			if (player1.isInTurn()) {
	        	System.out.println("********************************************");
	        	System.out.println("Player 1's turn");
	        	int cell_Num = 0;
	        	do {
	        		try {
	    	        	System.out.println("Player 1 chooses a cell to pick from: ");
	        			cell_Num = scanner.nextInt();
	        			scanner.nextLine();
	        		} catch(Exception e) {
	                    System.out.println("Invalid input. Please enter an integer.");
	        			scanner.nextLine();
	        		}
	        	} while(cell_Num <= 0 || cell_Num >= 6);
	        	String Player1Direction;
	        	do {
		            System.out.println("Player 1 chooses Direction");
		            Player1Direction = scanner.nextLine();
		            Player1Direction = Player1Direction.toUpperCase();
	        	} while(!Player1Direction.equals("CLOCKWISE") && !Player1Direction.equals("COUNTERCLOCKWISE"));

	            player1.getPiecesFromCell(board.getCellList().get(cell_Num));
	            player1.distributePieces(board.getCellList().get(cell_Num), Player1Direction);
	            player1.setInTurn(false);
	            player2.setInTurn(true);
			} else {
	        	System.out.println("********************************************");
	        	System.out.println("Player 2's turn");
	        	int cell_Num = 0;
	        	do {
	        		try {
	    	        	System.out.println("Player 2 chooses a cell to pick from: ");
	        			cell_Num = scanner.nextInt();
	        			scanner.nextLine();
	        		} catch(Exception e) {
	                    System.out.println("Invalid input. Please enter an integer.");
	        			scanner.nextLine();
	        		}
	        	} while(cell_Num <= 0 || cell_Num >= 6);
	        	String Player2Direction;
	        	do {
		            System.out.println("Player 2 chooses Direction");
		            Player2Direction = scanner.nextLine();
		            Player2Direction = Player2Direction.toUpperCase();
	        	} while(!Player2Direction.equals("CLOCKWISE") && !Player2Direction.equals("COUNTERCLOCKWISE"));
	            player2.getPiecesFromCell(board.getCellList().get(cell_Num));
	            player2.distributePieces(board.getCellList().get(cell_Num), Player2Direction);
	            player2.setInTurn(false);
	            player1.setInTurn(true);
			}
	        if (player1.calculateScore() > player2.calculateScore()) {
	            System.out.println("Player 1 wins.");
	            System.out.println("Player 1's score is: " + player1.calculateScore());
	            System.out.println("Player 2's score is: " + player2.calculateScore());
	        } else if (player1.calculateScore() < player2.calculateScore()) {
	            System.out.println("Player 2 wins.");
	            System.out.println("Player 1's score is: " + player1.calculateScore());
	            System.out.println("Player 2's score is: " + player2.calculateScore());
	        } else {
	            System.out.println("Draw");
	            System.out.println("The score of two players is: " + player1.calculateScore());
	        }
		}
		scanner.close();
	}
	public static boolean isEndGame(Board board) {
		int num = (board.getNumOfSquares() + board.getNumOfHalfCircles()) / 2;
		if(board.getCellList().get(0).isEmpty() && board.getCellList().get(num).isEmpty()) {
			return true;
		} else {
			for (Cell c : board.getCellList()) {
				if (!c.isEmpty()) {
					return false;
				}
			}
			return true;
		}
	}
	public static void printBoard(Board board) {
		System.out.print("  ");
		for (int i = 1; i <= 5; i++) {
			System.out.print(" " + board.getCellList().get(i).getValue() + " ");
		}
		System.out.println();
		System.out.print(board.getCellList().get(0).getValue() + " ");
		for (int i = 1; i <= 17; i++) System.out.print("-");
		System.out.print(" " + board.getCellList().get(6).getValue());
		System.out.println();
		System.out.print("  ");
		for (int i = 11; i >= 7; i--) {
			System.out.print(" " + board.getCellList().get(i).getValue() + " ");
		}
		System.out.println();
	}

}
