package pieces;

import java.awt.Image;
import java.util.Map;

import chessBoard.Tile;

public class King extends Piece {

	public King(String initialPosition, int armyType,
			Map<String, Tile> chesschessBoard, Image pieceImage) {
		super(initialPosition, armyType, chesschessBoard, pieceImage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAvailablePositions() {
		availablePositions.clear();

		final int movementFactor = 1;

		// Available points : all the the squares around the king
		for (int x = -(movementFactor); x <= movementFactor; x++) {
			for (int y = -(movementFactor); y <= movementFactor; y++) {
				String position = translate(currentPosition, x, y);
				// The square available must not be out of bounds
				// if the square is free or has an enemy (hasn't an ally
				// piece) , then we can add this position
				if (!isOutOfBounds(position)
						&& getSquareStatus(position) != HAS_ALLY) {
					availablePositions.add(position);
				}
			}
		}

	}

}
