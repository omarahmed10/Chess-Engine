package chess;

import java.awt.Image;
import java.awt.Point;

public class Queen extends Piece{


	public Queen(Point initialPosition, int armyType, Object[][] board,
			Image pieceImage) {
		super(initialPosition, armyType, board, pieceImage);
	}

	@Override
	protected void setAvailablePositions(Object[][] board) {
		availablePositions.clear();

		
		
		// What ROOK does

		// all "down" points
		for (int i = (currentPosition.x + 1); i < BOARD_HIGH_LIMIT; i++){
			Point point = new Point(i , currentPosition.y);
			
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
		for (int i = (currentPosition.x - 1); i >= BOARD_LOW_LIMIT; i--){
			Point point = new Point(i , currentPosition.y);
			
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
		for (int j = (currentPosition.y + 1); j < BOARD_HIGH_LIMIT; j++){
			Point point = new Point(currentPosition.x , j);
			
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
		for (int j = (currentPosition.y - 1); j >= BOARD_LOW_LIMIT; j--){
			Point point = new Point(currentPosition.x , j);
			
			if (getSquareStatus(point, board) == HAS_NO_PIECE) {
				availablePositions.add(point);
			}
			
			else {
				if (getSquareStatus(point, board) == HAS_ENEMY)
					availablePositions.add(point);
				break;
			}
		}
		
		
		
		
		
		// What BISHOP does
		
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
