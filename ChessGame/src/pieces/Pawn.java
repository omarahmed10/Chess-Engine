package pieces;

import java.awt.Image;
import java.util.Map;

import chessBoard.Tile;

public class Pawn extends Piece {

	private String initialPosition;

	public Pawn(String initialPosition, int armyType,
			Map<String, Tile> chesschessBoard, Image pieceImage) {
		super(initialPosition, armyType, chesschessBoard, pieceImage);
		this.initialPosition = initialPosition;
	}

	@Override
	public void setAvailablePositions() {
		availablePositions.clear();

		final int minStep = 1;
		int maxStep = minStep;
		// if pawn in its initial position , it can move one or two steps
		if (currentPosition == initialPosition) {
			maxStep = 2;
		}

		for (int i = minStep; i <= maxStep; i++) {
			// if white then step is -ve & vice versa
			String position = translate(currentPosition, 0, (i * armyType));
			if (!isOutOfBounds(position)
					&& getSquareStatus(position) == HAS_NO_PIECE) {
				availablePositions.add(position);
			} else {
				break;
			}
		}

		// if has enemy on the left , the it's available position
		String position = translate(currentPosition, (minStep * armyType),
				-minStep * -armyType);
		if (!isOutOfBounds(position)
				&& getSquareStatus(position) == HAS_ENEMY) {
			availablePositions.add(position);
		}

		// if has enemy on the right , the it's available position
		position = translate(currentPosition, minStep * -armyType,
				(minStep * armyType));
		if (!isOutOfBounds(position)
				&& getSquareStatus(position) == HAS_ENEMY) {
			availablePositions.add(position);
		}
	}

}
