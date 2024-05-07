package application.board;

import java.util.ArrayList;
import application.piece.Piece;

public abstract class Cell {
	private int position;
	private final ArrayList<Piece> pieceList = new ArrayList<Piece>();
	public boolean pickable = false;
	// Constructors
	public Cell(int position) {
		this.position = position;
	}
	// Getter and setter methods
	public ArrayList<Piece> getPieceList() {
		return pieceList;
	}
	public int getPosition() {
		return this.position;
	}
	public void setPostion(int newPosition) {
		this.position = newPosition;
	}
	// Add a piece to the cell
	public void addPiece(Piece newPiece) {
		if (newPiece != null) {
			this.pieceList.add(newPiece);
			this.pieceList.get(this.getNumOfPieces() - 1).setPosition(this);
		} else {
			System.out.print("Cannot add a null piece to the cell");
		}
	}
	// Empty the cell
	public void emptyCell() {
		this.pieceList.clear();
	}
	// Check if the cell is empty or not
	public boolean isEmpty() {
		return this.pieceList.size() == 0;
	}
	// Get the number of pieces in the cell
	public int getNumOfPieces() {
		return this.pieceList.size();
	}
	// Get the value of the cell
	public int getValue() {
		int result = 0;
		for (Piece p : this.pieceList) {
			result += p.getValue();
		}
		return result;
	}
	// Square can pickable
	public void makePickable() {
		this.pickable = true;
	}
	// See the detail of a cell (Cell position and list of pieces in cell
	public void seeDetail() {
		System.out.println("Cell Position: " + this.position);
		for (Piece p : this.pieceList) {
			System.out.println("Piece " + p);
		}
	}
	// toString methods
	public String toString() {
		return "Cell " + this.position;
	}
}
