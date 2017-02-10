package chess;

import java.awt.Image;
import java.awt.Point;

public class Bishop extends Piece {


	public Bishop(Point initialPosition, int armyType, Object[][] board,
			Image pieceImage) {
		super(initialPosition, armyType, board, pieceImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setAvailablePositions(Object[][] board) {
		availablePositions.clear();

		
		boolean leftUp = true;
		boolean rightUp = true;
		boolean leftDown = true;
		boolean rightDown = true;

		for (int i = BOARD_LOW_LIMIT + 1; i < BOARD_HIGH_LIMIT; i++) {
			if (leftUp) {
				Point point = new Point(currentPosition.x, currentPosition.y);
				point.translate(-i, -i);

				if (!isOutOfBounds(point)) {
					// if the square is free , then add it
					if (getSquareStatus(point, board) == HAS_NO_PIECE) {
						availablePositions.add(point);
					}

					else {
						// if the square has an enemy piece , we add the point
						// and stop
						// and if it's an ally , then stop
						if (getSquareStatus(point, board) == HAS_ENEMY)
							availablePositions.add(point);
						leftUp = false;
					}
				}

				else
					leftUp = false;

			}
			
			if (rightUp) {
				Point point = new Point(currentPosition.x, currentPosition.y);
				point.translate(-i, i);

				if (!isOutOfBounds(point)) {
					if (getSquareStatus(point, board) == HAS_NO_PIECE) {
						availablePositions.add(point);
					}

					else {
						if (getSquareStatus(point, board) == HAS_ENEMY)
							availablePositions.add(point);
						rightUp = false;
					}
				}

				else
					rightUp = false;

			}
			
			if (leftDown) {
				Point point = new Point(currentPosition.x, currentPosition.y);
				point.translate(i, -i);

				if (!isOutOfBounds(point)) {
					if (getSquareStatus(point, board) == HAS_NO_PIECE) {
						availablePositions.add(point);
					}

					else {
						if (getSquareStatus(point, board) == HAS_ENEMY)
							availablePositions.add(point);
						leftDown = false;
					}
				}

				else
					leftDown = false;

			}
			
			if (rightDown) {
				Point point = new Point(currentPosition.x, currentPosition.y);
				point.translate(i, i);

				if (!isOutOfBounds(point)) {
					if (getSquareStatus(point, board) == HAS_NO_PIECE) {
						availablePositions.add(point);
					}

					else {
						if (getSquareStatus(point, board) == HAS_ENEMY)
							availablePositions.add(point);
						rightDown = false;
					}
				}

				else
					rightDown = false;

			}
		}
	}

}
