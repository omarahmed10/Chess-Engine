package player;

import java.util.LinkedList;
import java.util.List;

import chessBoard.Move;
import pieces.Piece;

public class Player {
	private List<Piece> myArmy;
	private LinkedList<Piece> myGraveYard;
	private final int armyType;
	private List<List<Move>> legalMoves;
	private Piece myKing;

	public Player(int armyType) {
		this.armyType = armyType;
		myArmy = new LinkedList<Piece>();
		myGraveYard = new LinkedList<Piece>();
	}

	public void addPiece(Piece piece) {
		myArmy.add(piece);
	}

	public void setKing(Piece king) {
		this.myKing = king;
	}

	public List<Piece> getArmy() {
		return myArmy;
	}

	public LinkedList<Piece> getDeadArmy() {
		return myGraveYard;
	}

	public int getArmyType() {
		return armyType;
	}

	public List<List<Move>> getLegalMoves() {
		legalMoves = new LinkedList<List<Move>>();
		for(Piece piece : myArmy){
			legalMoves.add(piece.getLegalMoves());
		}
		return legalMoves;
	}

}
