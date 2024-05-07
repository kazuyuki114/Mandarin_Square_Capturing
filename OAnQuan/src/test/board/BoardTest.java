package test.board;

import application.board.Board;

public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board();
//		for (Cell c : board.getCellList()) {
//			c.seeDetail();;
//		}
//		board.getNextCellClockwise(board.getCellList().get(7)).seeDetail();
		System.out.print("  ");
		for (int i = 1; i <= 5; i++) {
			System.out.print(" " + board.getCellList().get(i).getValue() + " ");
		}
		System.out.println();
		System.out.print(board.getCellList().get(0).getValue() + " ");
		for (int i = 1; i <= 13; i++) System.out.print("-");
		System.out.print(" " + board.getCellList().get(0).getValue());
		System.out.println();
		System.out.print("  ");
		for (int i = 7; i <= 11; i++) {
			System.out.print(" " + board.getCellList().get(i).getValue() + " ");
		}
		System.out.println();
		System.out.println(board.getNextCellCounterClockwise(board.getCellList().get(0)));
	}
}
