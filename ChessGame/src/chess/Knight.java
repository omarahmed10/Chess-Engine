package chess;

import java.awt.Image;
import java.awt.Point;

public class Knight extends Piece {

	

	public Knight(Point initialPosition, int armyType, Object[][] board,
			Image pieceImage) {
		super(initialPosition, armyType, board, pieceImage);
	}

	@Override
	protected void setAvailablePositions(Object[][] board) {
		availablePositions.clear();

		final int movementFactor1 = 1;
		final int movementFactor2 = 2;

		// available points :
		// (x-1,y-2), (x-1,y+2), (x+1,y-2), (x+1,y+2)
		// (x-2,y-1), (x-2,y+1), (x+2,y-1), (x+2,y+1)
		for (int x = -(movementFactor1); x <= (movementFactor1); x += (2 * movementFactor1)) {
			for (int y = -(movementFactor2); y <= (movementFactor2); y += (2 * movementFactor2)) {

				// 2 symmetric positions per time
				
				Point point = new Point(currentPosition.x, currentPosition.y);
				point.translate(x, y);
				
				// The square available must not be out of bounds
				// if the square is free or has an enemy (hasn't an ally
				// piece) , then we can add this position
				if (!isOutOfBounds(point) && getSquareStatus(point, board) != HAS_ALLY) {
					availablePositions.add(point);
				}
				
				
				point = new Point(currentPosition.x, currentPosition.y);
				point.translate(y, x);
				
				if (!isOutOfBounds(point) && getSquareStatus(point, board) != HAS_ALLY) {
					availablePositions.add(point);
				}

			}
		}

	}

}
