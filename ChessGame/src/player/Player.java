package player;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import chessBoard.Move;
import chessBoard.Tile;
import pieces.King;
import pieces.Piece;

public class Player implements Cloneable {
	private List<Piece> myArmy;
	private LinkedList<Piece> myGraveYard;
	private final int armyType;
	private List<Move> legalMoves;
	private King myKing;
	private final PlayerType playerType;

	public Player(int armyType, PlayerType playerType) {
		this.armyType = armyType;
		myArmy = new LinkedList<Piece>();
		myGraveYard = new LinkedList<Piece>();
		this.playerType = playerType;
	}

	public void addPiece(Piece piece) {
		if (piece.isDead()) {
			myGraveYard.add(piece);
		} else {
			myArmy.add(piece);
		}
	}

	public void reset() {
		myGraveYard = new LinkedList<>();
		myArmy = new LinkedList<>();
	}

	public void setKing(Piece king) {
		this.myKing = (King) king;
	}

	public boolean isInCheckMate(Map<String, Tile> boardMap,
			Player opponentPlayer) {
		return myKing.isCheckmate(boardMap, opponentPlayer);
	}

	public boolean isInCheck(Map<String, Tile> boardMap) {
		return myKing.isChecked(boardMap);
	}

	public boolean isInStaleMate(Map<String, Tile> boardMap,
			Player opponentPlayer) {
		return myKing.isStalemate(boardMap, opponentPlayer);
	}

	public List<Piece> getArmy() {
		return myArmy;
	}

	public LinkedList<Piece> getDeadArmy() {
		return myGraveYard;
	}

	public void addDeadPiece(Piece e) {
		e.sendToGraveyard();
		myArmy.remove(e);
		myGraveYard.add(e);
	}

	public void awakeLastDeadPiece(String position, Piece killedPiece) {
		int index = myGraveYard.indexOf(killedPiece);
		myGraveYard.remove(index);
		killedPiece.awake(position);
		myArmy.add(killedPiece);
	}

	public int getArmyType() {
		return armyType;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public List<Move> getLegalMoves() {
		legalMoves = new LinkedList<Move>();
		for (Piece piece : myArmy) {
			piece.setLegalMoves();
			for (Move m : piece.getLegalMoves()) {
				legalMoves.add(m);
			}
		}
		return legalMoves;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
