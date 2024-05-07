package application.piece;
import application.board.Cell;

public abstract class Piece {
	private int value;
	private Cell position;
	// Constructors
	public Piece() {	}
	public Piece(Cell position) {
		this.position = position;
	}
	// Getter and setter methods
	public int getValue() {
		return this.value;
	}
	public void setValue(int newValue) {
		this.value = newValue;
	}
	public Cell getPosition() {
		return this.position;
	}
	public void setPosition(Cell newPosition) {
		this.position = newPosition;
	}
	// toString methods
	public String toString() {
		return "Position: " + this.position + "." + " Value: " + this.value; 
	}
}