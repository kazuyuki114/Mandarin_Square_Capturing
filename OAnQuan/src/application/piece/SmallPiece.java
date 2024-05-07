package application.piece;

import application.board.Cell;

public class SmallPiece extends Piece{
	public SmallPiece() {
		super();
		this.setValue(1);
	}
	public SmallPiece(Cell position) {
		super(position);
		this.setValue(1);
	}
}
