package pieces;

import java.awt.Image;
import java.awt.Point;
import chessBoard.ChessTiles;
import chessBoard.Move;
import chessBoard.Tile;

public class Bishop extends Piece {


	public Bishop(String initialPosition, int armyType, Point myCoordinate,
			Image pieceImage) {
		super(initialPosition, armyType, myCoordinate, pieceImage);
		pieceValue = 330;
	}

	@Override
	public void setLegalMoves() {
		availableMoves.clear();

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
		if (ChessTiles.getSquareStatus(this,position) == Tile.HAS_NO_PIECE) {
			availableMoves.add(new Move(this, position));
		}

		else {
			// if the square has an enemy piece , we add the point and stop
			// and if it's an ally , then stop
			if (ChessTiles.getSquareStatus(this,position) == Tile.HAS_ENEMY) {
				availableMoves.add(new Move(this, position));
			}
			return false;
		}
		return true;
	}

}
