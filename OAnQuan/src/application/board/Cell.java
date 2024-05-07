package application.board;

import java.util.ArrayList;
import application.piece.Piece;

public abstract class Cell {
	private int position;
	private final ArrayList<Piece> pieceList = new ArrayList<Piece>();
	public boolean pickable = false;
	
	public Cell(int position) {
		this.position = position;
	}
	
	public ArrayList<Piece> getPieceList() {
		return pieceList;
	}
	
	public void addPiece(Piece newPiece) {
		if (newPiece != null) {
			this.pieceList.add(newPiece);
			this.pieceList.get(this.getNumOfPieces() - 1).setPosition(this);
		} else {
			System.out.print("Cannot add a null piece to the cell");
		}
	}
	
	public void emptyCell() {
		this.pieceList.clear();
	}
	public int getPosition() {
		return this.position;
	}
	public void setPostion(int newPosition) {
		this.position = newPosition;
	}
	public boolean isEmpty() {
		return this.pieceList.size() == 0;
	}
	public int getNumOfPieces() {
		return this.pieceList.size();
	}
	public int getValue() {
		int result = 0;
		for (Piece p : this.pieceList) {
			result += p.getValue();
		}
		return result;
	}
	public void makePickable() {
		this.pickable = true;
	}
	public void seeDetail() {
		System.out.println("Cell Position: " + this.position);
		for (Piece p : this.pieceList) {
			System.out.println("Piece " + p);
		}
	}
	public String toString() {
		return "Cell " + this.position;
	}
}
