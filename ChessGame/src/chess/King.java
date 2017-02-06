package chess;

import java.awt.Point;

public class King extends Piece {

	public King(Point initialPosition, int armyType, Object[][] board) {
		super(initialPosition, armyType, board);
	}

	@Override
	protected void setAvailablePositions(Object[][] board) {
		availablePositions.clear();
		
		final int movementFactor = 1;

		// Available points : all the the squares around the king
		for (int x = -(movementFactor); x <= movementFactor; x++) {
			for (int y = -(movementFactor); y <= movementFactor; y++) {

				Point point = new Point(currentPosition.x, currentPosition.y);
				point.translate(x, y);

				// The square available must not be out of bounds
				// if the square is free or has an enemy (hasn't an ally
				// piece) , then we can add this position
				if (!isOutOfBounds(point) && getSquareStatus(point, board) != HAS_ALLY) {
					availablePositions.add(point);
				}
			}
		}

	}

}
