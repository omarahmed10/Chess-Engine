package chess;

import java.awt.Image;
import java.awt.Point;

public class Pawn extends Piece {

	private Point initialPosition;

	public Pawn(Point initialPosition, int armyType, Object[][] board,
			Image pieceImage) {
		super(initialPosition, armyType, board, pieceImage);
		this.initialPosition = initialPosition;
	}

	@Override
	protected void setAvailablePositions(Object[][] board) {
		availablePositions.clear();

		final int minStep = 1;
		int maxStep = minStep;
		// if pawn in its initial position , it can move one or two steps
		if (currentPosition == initialPosition) {
			maxStep = 2;
		}

		for (int i = minStep; i <= maxStep; i++) {
			// if white then step is -ve & vice versa
			Point point = new Point(currentPosition.x + (i * armyType),
					currentPosition.y);

			if (!isOutOfBounds(point)
					&& getSquareStatus(point, board) == HAS_NO_PIECE) {
				availablePositions.add(point);
			}
		}

		// if has enemy on the left , the it's available position
		Point point = new Point(currentPosition.x + (minStep * armyType),
				currentPosition.y - minStep);
		if (!isOutOfBounds(point)
				&& getSquareStatus(point, board) == HAS_ENEMY) {
			availablePositions.add(point);
		}

		// if has enemy on the left , the it's available position
		point = new Point(currentPosition.x + (minStep * armyType),
				currentPosition.y + minStep);
		if (!isOutOfBounds(point)
				&& getSquareStatus(point, board) == HAS_ENEMY) {
			availablePositions.add(point);
		}
	}

}
