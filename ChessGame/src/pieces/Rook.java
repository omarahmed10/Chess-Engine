package pieces;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import chessBoard.ChessTiles;
import chessBoard.Move;
import chessBoard.Tile;

public class Rook extends Piece {

	public Rook(String initialPosition, int armyType, Point myCoordinate,
			Image pieceImage) {
		super(initialPosition, armyType, myCoordinate, pieceImage);
		pieceValue = 500;
	}

	@Override
	public void setLegalMoves() {
		if (availableMoves == null) {
			availableMoves = new ArrayList<Move>();
		} else {
			availableMoves.clear();
		}

		int d = (int) currentPosition.charAt(0);
		int d2 = Integer.parseInt(currentPosition.charAt(1) + "");
		// all right positions
		for (int i = d - 65 + 1; i < 8; i++) {
			String position = (char) (i + 65) + "" + d2;
			if (!freeSquare(position))
				break;
		}

		// all left positions
		for (int i = d - 65 - 1; i >= 0; i--) {
			String position = (char) (i + 65) + "" + d2;
			if (!freeSquare(position))
				break;
		}
		// all up positions
		for (int j = d2 + 1; j <= 8; j++) {
			String position = (char) d + "" + j;
			if (!freeSquare(position))
				break;
		}

		// all down position
		for (int j = d2 - 1; j >= 1; j--) {
			String position = (char) d + "" + j;
			if (!freeSquare(position))
				break;
		}

	}

	private boolean freeSquare(String position) {
		// if the square is free , then add it
		if (ChessTiles.getSquareStatus(this, position) == Tile.HAS_NO_PIECE) {
			availableMoves.add(new Move(this, position));
		}

		else {
			// if the square has an enemy piece , we add the point and stop
			// and if it's an ally , then stop
			if (ChessTiles.getSquareStatus(this, position) == Tile.HAS_ENEMY) {
				availableMoves.add(new Move(this, position));
			}
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		int n = armyType;
		if (n == -1) {
			n = 0;
		}
		return n + "R ";
	}
}
