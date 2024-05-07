package test.gameplay;

import application.board.Board;
import application.player.Player;

public class PlayerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		Player player = new Player(1, board);
		printBoard(board);
		player.getPiecesFromCell(board.getCellList().get(5));
		player.distributePieces(board.getCellList().get(5), "CLOCKWISE");
		printBoard(board);
		player.getPiecesFromCell(board.getCellList().get(2));
		player.distributePieces(board.getCellList().get(2), "COUNTERCLOCKWISE");
		printBoard(board);
		player.getPiecesFromCell(board.getCellList().get(5));
		player.distributePieces(board.getCellList().get(5), "CLOCKWISE");
		printBoard(board);
		player.getPiecesFromCell(board.getCellList().get(2));
		player.distributePieces(board.getCellList().get(2), "CLOCKWISE");
		printBoard(board);
		player.getPiecesFromCell(board.getCellList().get(11));
		player.distributePieces(board.getCellList().get(11), "CLOCKWISE");
		printBoard(board);
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
