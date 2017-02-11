package player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import pieces.Piece;

public class Player {
	private Map<String, Piece> myArmy;
	private LinkedList<Piece> myGraveYard;
	private final int armyType;

	public Player(int armyType) {
		this.armyType = armyType;
		myArmy = new HashMap<String, Piece>();
		myGraveYard = new LinkedList<Piece>();
	}

	public void addPiece(String pieceName, Piece piece) {
		myArmy.put(pieceName, piece);
	}

	public Map<String, Piece> getArmy() {
		return myArmy;
	}

	public LinkedList<Piece> getDeadArmy() {
		return myGraveYard;
	}

	public int getArmyType() {
		return armyType;
	}
}
