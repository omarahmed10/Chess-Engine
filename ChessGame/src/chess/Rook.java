package chess;

import java.awt.Image;
import java.awt.Point;

public class Rook extends Piece {


	public Rook(Point initialPosition, int armyType, Object[][] board,
			Image pieceImage) {
		super(initialPosition, armyType, board, pieceImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setAvailablePositions(Object[][] board) {
		availablePositions.clear();

		// all "down" points
		for (int i = (currentPosition.x + 1); i < BOARD_HIGH_LIMIT; i++) {
			Point point = new Point(i, currentPosition.y);

			// if the square is free , then add it
			if (getSquareStatus(point, board) == HAS_NO_PIECE) {
				availablePositions.add(point);
			}

			else {
				// if the square has an enemy piece , we add the point and stop
				// and if it's an ally , then stop
				if (getSquareStatus(point, board) == HAS_ENEMY)
					availablePositions.add(point);
				break;
			}
		}

		// all "up" points
		for (int i = (currentPosition.x - 1); i >= BOARD_LOW_LIMIT; i--) {
			Point point = new Point(i, currentPosition.y);

			if (getSquareStatus(point, board) == HAS_NO_PIECE) {
				availablePositions.add(point);
			}

			else {
				if (getSquareStatus(point, board) == HAS_ENEMY)
					availablePositions.add(point);
				break;
			}
		}

		// all "right" points
		for (int j = (currentPosition.y + 1); j < BOARD_HIGH_LIMIT; j++) {
			Point point = new Point(currentPosition.x, j);

			if (getSquareStatus(point, board) == HAS_NO_PIECE) {
				availablePositions.add(point);
			}

			else {
				if (getSquareStatus(point, board) == HAS_ENEMY)
					availablePositions.add(point);
				break;
			}
		}

		// all "left" points
		for (int j = (currentPosition.y - 1); j >= BOARD_LOW_LIMIT; j--) {
			Point point = new Point(currentPosition.x, j);

			if (getSquareStatus(point, board) == HAS_NO_PIECE) {
				availablePositions.add(point);
			}

			else {
				if (getSquareStatus(point, board) == HAS_ENEMY)
					availablePositions.add(point);
				break;
			}
		}

	}

}
