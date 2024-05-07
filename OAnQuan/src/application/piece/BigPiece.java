package application.piece;

import application.board.Cell;

public class BigPiece extends Piece{
	public BigPiece() {
		super();
		this.setValue(10);
	}
	public BigPiece(Cell position) {
		super(position);
		this.setValue(10);
	}
}
