package test.board;

import application.board.HalfCircle;
import application.board.Square;
import application.piece.BigPiece;
import application.piece.SmallPiece;

public class CellTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Square square = new Square(1);
		HalfCircle halfCircle = new HalfCircle(0);
		for (int i = 1; i<= 5; i++) {
			square.addPiece(new SmallPiece());
		}
		halfCircle.addPiece(new BigPiece());	
		halfCircle.addPiece(new SmallPiece());
		square.seeDetail();
		System.out.println(square.getValue());
		System.out.println(square.getNumOfPieces());
		square.emptyCell();
		square.seeDetail();
		System.out.println(square.getValue());
		halfCircle.seeDetail();
		System.out.println(halfCircle.getValue());
		System.out.println(halfCircle.getNumOfPieces());
		Square emptySquare = new Square(3);
		System.out.println(emptySquare.isEmpty());
	}

}
