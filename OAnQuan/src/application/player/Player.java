package application.player;

import java.util.ArrayList;
import application.board.Cell;
import application.board.HalfCircle;
import application.piece.Piece;
import application.board.Board;

public class Player {
	private boolean inTurn;
	private final int playerID;
	private Board playingBoard;
	private ArrayList<Piece> piecesInHand = new ArrayList<Piece>();
	private ArrayList<Piece> piecesCaptured = new ArrayList<Piece>();
	private ArrayList<Cell> cellsOnPlayerSide = new ArrayList<Cell>();
	private int numOfPiecesLent = 0;
	private int numOfPiecesBorrowed = 0;
	//Constructor
	public Player(int playerID, Board board) {
		this.playerID = playerID;
		this.playingBoard = board;
	}
	//Getter and setter methods
	public ArrayList<Piece> getPiecesInHand(){
		return this.piecesInHand;
	}
	public ArrayList<Piece> getPiecesCaptured(){
		return this.piecesCaptured;
	}
	public boolean isInTurn() {
		return inTurn;
	}
	public void setInTurn(boolean inTurn) {
		this.inTurn = inTurn;
	}
	public int getPlayerID() {
		return playerID;
	}
	public Board getPlayingBoard() {
		return this.playingBoard;
	}
	public ArrayList<Cell> getCellsOnPlayerSide() {
		return cellsOnPlayerSide;
	}
	public void setCellsOnPlayerSide(ArrayList<Cell> cellsOnPlayerSide) {
		this.cellsOnPlayerSide = cellsOnPlayerSide;
	}
	public int getNumOfPiecesLent() {
		return numOfPiecesLent;
	}
	public void setNumOfPiecesLent(int numOfPiecesLent) {
		this.numOfPiecesLent = numOfPiecesLent;
	}
	public int getNumOfPiecesBorrowed() {
		return numOfPiecesBorrowed;
	}
	public void setNumOfPiecesBorrowed(int numOfPiecesBorrowed) {
		this.numOfPiecesBorrowed = numOfPiecesBorrowed;
	}
	// Gameplay methods
	public boolean getPiecesFromCell(Cell givenCell) {
		if((givenCell.pickable == true) && !(givenCell.isEmpty())) {
			this.piecesInHand.addAll(givenCell.getPieceList());
			givenCell.emptyCell();
			return true;
		} else {
			return false;
		}
	}
	public void dropPieceTo(Cell cell) {
		if(!this.piecesInHand.isEmpty()) {
			int indexOfTheLast = this.piecesInHand.size() - 1;
			cell.addPiece(this.piecesInHand.get(indexOfTheLast));
			this.piecesInHand.remove(indexOfTheLast);
		}
	}
	public void capturePiecesFrom(Cell cell) {
		if (!cell.isEmpty()) {
	        this.piecesCaptured.addAll(cell.getPieceList());
	        cell.emptyCell();
		}
	}
	public void distributePieces(Cell startCell, String direction) {
		Cell temp = startCell;
		Cell next_temp;
		Cell prev_temp;
		switch(direction) {
		case "CLOCKWISE":
			while(!this.piecesInHand.isEmpty()) {
				temp = this.getPlayingBoard().getNextCellClockwise(temp);
				this.dropPieceTo(temp);
			}
			temp = this.getPlayingBoard().getNextCellClockwise(temp);
			System.out.println(temp);
			if(!(temp instanceof HalfCircle)) {
				if(!(temp.isEmpty())) {
					this.getPiecesFromCell(temp);
					this.distributePieces(temp, "CLOCKWISE");
				} else {
					prev_temp = temp;
					next_temp = this.getPlayingBoard().getNextCellClockwise(temp);
					//System.out.println(next_temp);
					while(!(next_temp.isEmpty())) {
						//System.out.println(next_temp.isEmpty());
						this.capturePiecesFrom(next_temp);
						prev_temp = this.getPlayingBoard().getNextCellClockwise(next_temp);
						if (prev_temp instanceof HalfCircle || !prev_temp.isEmpty()) {
							break;
						} else {
							next_temp = this.getPlayingBoard().getNextCellClockwise(prev_temp);
						}
					}
				}
			}
			break;
		case "COUNTERCLOCKWISE":
			while(!this.piecesInHand.isEmpty()) {
				temp = this.getPlayingBoard().getNextCellCounterClockwise(temp);
				this.dropPieceTo(temp);
			}
			temp = this.getPlayingBoard().getNextCellCounterClockwise(temp);
			System.out.println(temp);
			if(!(temp instanceof HalfCircle)) {
				if(!(temp.isEmpty())) {
					this.getPiecesFromCell(temp);
					this.distributePieces(temp, "COUNTERCLOCKWISE");
				} else {
					prev_temp = temp;
					next_temp = this.getPlayingBoard().getNextCellCounterClockwise(temp);
					//System.out.println(next_temp);
					while(!(next_temp.isEmpty())) {
						//System.out.println(next_temp.isEmpty());
						this.capturePiecesFrom(next_temp);
						prev_temp = this.getPlayingBoard().getNextCellCounterClockwise(next_temp);
						if (prev_temp instanceof HalfCircle || !prev_temp.isEmpty()) {
							break;
						} else {
							next_temp = this.getPlayingBoard().getNextCellCounterClockwise(prev_temp);
						}
					}
				}
			}
			break;
		}
	}
	public int calculateScore() {
		int score = 0;
		for (Piece p : this.piecesCaptured) {
			score += p.getValue();
		}
		return score - this.getNumOfPiecesBorrowed() + this.getNumOfPiecesLent();
	}
	public boolean checkCellsOnSideEmpty() {
		for (Cell c : this.cellsOnPlayerSide) {
			if(!c.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	public void dispatch() {
		for (Cell c : this.cellsOnPlayerSide) {
			if(this.piecesCaptured.size() >= 5) {
				int indexOfTheLast = this.piecesCaptured.size() - 1;
				c.addPiece(this.piecesInHand.get(indexOfTheLast));
				this.piecesCaptured.remove(indexOfTheLast);
			}
		}
	}
	public void borrowPiecesFrom(Player player, int num) {
		int n = player.getPiecesCaptured().size();
		if(n >= num) {
			this.piecesCaptured.add(player.getPiecesCaptured().get(n - 1));
			this.numOfPiecesBorrowed++;
			player.getPiecesCaptured().remove(n - 1);
			player.setNumOfPiecesLent(player.getNumOfPiecesLent() + 1);
		}
	}
}
