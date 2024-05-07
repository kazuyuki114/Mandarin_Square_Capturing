package application.piece;
import application.board.Cell;

public abstract class Piece {
	private int value;
	private Cell position;
	
	public Piece() {	}
	public Piece(Cell position) {
		this.position = position;
	}
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
	public String toString() {
		return "Position: " + this.position + "." + " Value: " + this.value; 
	}
}