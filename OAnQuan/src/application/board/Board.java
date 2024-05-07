package application.board;

import java.util.ArrayList;
import application.piece.BigPiece;
import application.piece.SmallPiece;

public class Board {
	private static final int numOfSquares = 10;
	private static final int numOfHalfCircles = 2;
	private static final int numOfSmallPieces = 50;
	private static final int numOfBigPieces = 2;
	private final ArrayList<Cell> cellList = new ArrayList<Cell>(Board.numOfHalfCircles + Board.numOfSquares);
	// Constructors
	public Board() {
		// First HalfCicrcle
		this.cellList.add(new HalfCircle(0));
		this.cellList.get(0).addPiece(new BigPiece());
		// First 5 squares
		for (int i = 1; i < ((Board.numOfSquares + Board.numOfHalfCircles)/2); i++){
			this.cellList.add(new Square(i));
			for (int j = 1; j <= 5; j++) {
				this.cellList.get(i).addPiece(new SmallPiece());
			}
		}
		// Second Circle
		this.cellList.add(new HalfCircle((Board.numOfSquares + Board.numOfHalfCircles)/2));
		this.cellList.get((Board.numOfSquares + Board.numOfHalfCircles)/2).addPiece(new BigPiece());
		// Second 5 squares
		for (int i = (Board.numOfSquares + Board.numOfHalfCircles) / 2 + 1; i < (Board.numOfSquares + Board.numOfHalfCircles); i++){
			this.cellList.add(new Square(i));
			for (int j = 1; j <= 5; j++) {
				this.cellList.get(i).addPiece(new SmallPiece());
			}
		}
	}
	// Getter and setter methods
	public int getNumOfSquares() {
		return Board.numOfSquares;
	}
	public int getNumOfHalfCircles() {
		return Board.numOfHalfCircles;
	}
	public int getNumOfSmallPieces() {
		return Board.numOfSmallPieces;
	}
	public int getNumOfBigPieces() {
		return Board.numOfBigPieces;
	}
	public ArrayList<Cell> getCellList(){
		return this.cellList;
	}
	// Get the clockwise next cell 
	public Cell getNextCellClockwise(Cell currentCell) {
		if(currentCell.getPosition() == (Board.numOfHalfCircles + Board.numOfSquares - 1)) {
			return this.cellList.get(0);
		} else {
			return this.cellList.get(currentCell.getPosition() + 1);
		}
	}	
	// Get the counter clockwise next cell
	public Cell getNextCellCounterClockwise(Cell currentCell) {
		if(currentCell.getPosition() == 0) {
			return this.cellList.get(Board.numOfSquares + Board.numOfHalfCircles - 1);
		} else {
			return this.cellList.get(currentCell.getPosition() - 1);
		}
	}
}
