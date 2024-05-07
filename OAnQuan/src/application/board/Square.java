package application.board;

import application.piece.SmallPiece;
import application.piece.Piece;

// All squares are pickable

public class Square extends Cell{
	public Square(int position) {
		super(position);
		this.makePickable();
	}
	@Override
	public void addPiece(Piece newPiece) {
		if (newPiece instanceof SmallPiece) {
			this.getPieceList().add(newPiece);
			this.getPieceList().get(this.getNumOfPieces() - 1).setPosition(this);
		} else {
			System.out.println("Cannot add a big piece to the square cell");
		}
	}
}
