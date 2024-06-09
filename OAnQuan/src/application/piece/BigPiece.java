package application.piece;

import application.board.Cell;

public class BigPiece extends Piece{
	public BigPiece() {
		super();
		this.setValue(5);
	}
	public BigPiece(Cell position) {
		super(position);
		this.setValue(5);
	}
}
