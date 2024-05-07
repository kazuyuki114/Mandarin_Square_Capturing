package test.piece;

import application.piece.SmallPiece;
import application.board.HalfCircle;
import application.board.Square;
import application.piece.BigPiece;

public class PieceTest {

	public static void main(String[] args) {
		Square square = new Square(1);
		HalfCircle halfcircle = new HalfCircle(0);
		
		SmallPiece smallPiece = new SmallPiece(square);
		System.out.println(smallPiece);
		smallPiece.setValue(2);
		smallPiece.setPosition(halfcircle);
		
		BigPiece bigPiece = new BigPiece(halfcircle);
		System.out.println(bigPiece);
		
		//square.addPiece(smallPiece);
		halfcircle.addPiece(bigPiece);
		halfcircle.addPiece(smallPiece);
		
		square.seeDetail();
		halfcircle.seeDetail();
	}

}
