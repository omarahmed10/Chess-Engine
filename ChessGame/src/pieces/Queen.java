package pieces;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import chessBoard.ChessTiles;
import chessBoard.Move;
import chessBoard.Tile;

public class Queen extends Piece {

	public Queen(String initialPosition, int armyType, Point myCoordinate,
			Image pieceImage) {
		super(initialPosition, armyType, myCoordinate, pieceImage);
		pieceValue = 900;
	}

	@Override
	public void setLegalMoves() {
		if (availableMoves == null) {
			availableMoves = new ArrayList<Move>();
		} else {
			availableMoves.clear();
		}

		// What ROOK does
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

		// What BISHOP does
		boolean leftUp = true;
		boolean rightUp = true;
		boolean leftDown = true;
		boolean rightDown = true;

		for (int i = 1; i <= 8; i++) {
			if (leftUp) {
				String position = translate(currentPosition, -i, -i);

				if (!isOutOfBounds(position)) {
					leftUp = freeSquare(position);
				}

				else
					leftUp = false;

			}

			if (rightUp) {
				String position = translate(currentPosition, -i, i);

				if (!isOutOfBounds(position)) {
					rightUp = freeSquare(position);
				}

				else
					rightUp = false;

			}

			if (leftDown) {
				String position = translate(currentPosition, i, -i);

				if (!isOutOfBounds(position)) {
					leftDown = freeSquare(position);
				}

				else
					leftDown = false;

			}

			if (rightDown) {
				String position = translate(currentPosition, i, i);

				if (!isOutOfBounds(position)) {
					rightDown = freeSquare(position);
				}

				else
					rightDown = false;

			}
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
		return  n + "Q ";
	}
}
