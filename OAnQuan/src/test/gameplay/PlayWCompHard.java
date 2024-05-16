package test.gameplay;

import java.util.ArrayList;
import java.util.Scanner;
import application.board.Board;
import application.board.Cell;
import application.board.HalfCircle;
import application.player.Player;

public class PlayWCompHard {

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
		while(!isEndGame(board, player1, player2)) {
			printBoard(board);
			if (player1.isInTurn()) {
				if (player1.checkCellsOnSideEmpty()) {
					int numDispatch = player1.getNumOfSmallPiecesCaptured();
					if(numDispatch < 5) {
						player1.borrowPiecesFrom(player2, 5 - numDispatch);
					} 
					player1.dispatch();
					
				}
	        	System.out.println("********************************************");
	        	System.out.println("Player 1's turn");
	        	int cell_Num;
	        	String Player1Direction;
	        	String solution = calculateMax(board, 1, 5);
	        	try {
	        		cell_Num = Integer.parseInt(solution.substring(0, 2));
	        		System.out.println(cell_Num);
	        		Player1Direction = solution.substring(2);
	        		System.out.println(Player1Direction);
		            player1.getPiecesFromCell(board.getCellList().get(cell_Num));
		            player1.distributePieces(board.getCellList().get(cell_Num), Player1Direction);
		            player1.setInTurn(false);
		            player2.setInTurn(true);
	        	} catch (NumberFormatException e) {
	        		e.printStackTrace();
	        		break;
	        	}
			} else {
				if (player2.checkCellsOnSideEmpty()) {
					int numDispatch = player2.getNumOfSmallPiecesCaptured();
					if(numDispatch < 5) {
						player2.borrowPiecesFrom(player1, 5 - numDispatch);
					} 
					player2.dispatch();
				}
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
	        	} while(cell_Num <= 6 || cell_Num >= 12 || board.getCellList().get(cell_Num).isEmpty());
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
		scanner.close();
	}
	public static boolean isEndGame(Board board, Player player1, Player player2) {
		int emptyHalfCircles = 0;
		for (Cell c : board.getCellList()) {
			if (c instanceof HalfCircle && c.isEmpty()) {
				emptyHalfCircles++;
			} 
		}
		if (emptyHalfCircles == 2) {
			// Capture the remaining pieces on player's side
			for (Cell c1 : player1.getCellsOnPlayerSide()) {
				if(!c1.isEmpty()) {
					player1.capturePiecesFrom(c1);
				}
			}
			for (Cell c2 : player2.getCellsOnPlayerSide()) {
				if(!c2.isEmpty()) {
					player2.capturePiecesFrom(c2);
				}
			}
			return true;
		} else {
			return false;
		}
	}
	public static int calculateMove(ArrayList<Integer> temp_board, int cell_Num, String direction) {
		int score = 0;
		if (direction.equals("CLOCKWISE")) {
			int remaining = temp_board.get(cell_Num);
			temp_board.set(cell_Num, 0);
			while(remaining > 0) {
				cell_Num = cell_Num == 11 ? 0 : cell_Num + 1;
				temp_board.set(cell_Num, temp_board.get(cell_Num) + 1);
				remaining--;
			}
			if(cell_Num == 5 || cell_Num == 11) {
				return 0;
			} else {
				cell_Num = cell_Num == 11 ? 0 : cell_Num + 1;
				if (temp_board.get(cell_Num) != 0) {
					return calculateMove(temp_board, cell_Num, "CLOCKWISE");
				} else {
					int next_cellNum;
					next_cellNum =  cell_Num == 11 ? 0 : cell_Num + 1 ;
					while(temp_board.get(next_cellNum) != 0) {
						score += temp_board.get(next_cellNum);
						cell_Num = next_cellNum == 11 ? 0 : next_cellNum + 1;
						if (temp_board.get(cell_Num) != 0) {
							return score;
						} else {
							next_cellNum =  cell_Num == 11 ? 0 : cell_Num + 1 ;
						}
					}
					return score;
				}
			}
		} else {
			int remaining = temp_board.get(cell_Num);
			temp_board.set(cell_Num, 0);
			while(remaining > 0) {
				cell_Num = (cell_Num == 0) ? 11 : cell_Num - 1;
				temp_board.set(cell_Num, temp_board.get(cell_Num) + 1);
				remaining--;
			}
			if(cell_Num == 1 || cell_Num == 7) {
				return 0;
			} else {
				cell_Num = (cell_Num == 0) ? 11 : cell_Num - 1;
				if (temp_board.get(cell_Num) != 0) {
					return calculateMove(temp_board, cell_Num, "COUNTERCLOCKWISE");
				} else {
					int next_cellNum;
					next_cellNum =  cell_Num == 0 ? 11 : cell_Num - 1 ;
					while(temp_board.get(next_cellNum) != 0) {
						score += temp_board.get(next_cellNum);
						cell_Num = next_cellNum == 0 ? 11 : next_cellNum - 1;
						if (temp_board.get(cell_Num) != 0) {
							return score;
						} else {
							next_cellNum =  cell_Num == 0 ? 11 : cell_Num - 1 ;
						}
					}
					return score;
				}
			}
		}
	}
	public static String calculateMax(Board board, int start_cell, int end_cell) {
		int max = -1;
		String solution = null;
		for (int i = start_cell; i <= end_cell; i++) {
			if (board.getCellList().get(i).getValue() > 0) {
	        	ArrayList<Integer> temp_board = new ArrayList<>(12);
	        	for (Cell c : board.getCellList()) {
	        		temp_board.add(c.getValue());
	        	}
				int clw = calculateMove(temp_board, i, "CLOCKWISE");
				int cclw = calculateMove(temp_board, i, "COUNTERCLOCKWISE");
				if (clw > max) {
					max = clw;
					solution = (i >= 1 && i <= 9) ? "0" + i +"CLOCKWISE" : i + "CLOCKWISE";
				}
				if (cclw > max) {
					max = cclw;
					solution = (i >= 1 && i <= 9) ? "0" + i + "COUNTERCLOCKWISE" : i + "COUNTERCLOCKWISE";
				}
				}
		}
		return solution;
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
