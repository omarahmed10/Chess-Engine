package pieces;

import java.awt.Image;
import java.awt.Point;

import chessBoard.ChessTiles;
import chessBoard.Move;
import chessBoard.Tile;

public class Pawn extends Piece {

	private String initialPosition;

	public Pawn(String initialPosition, int armyType, Point myCoordinate,
			Image pieceImage) {
		super(initialPosition, armyType, myCoordinate, pieceImage);
		this.initialPosition = initialPosition;
		pieceValue = 100;
	}

	@Override
	public void setLegalMoves() {
		availableMoves.clear();

		final int minStep = 1;
		int maxStep = minStep;
		// if pawn in its initial position , it can move one or two steps
		if (currentPosition == initialPosition) {
			maxStep = 2;
		}

		for (int i = minStep; i <= maxStep; i++) {
			// if white then step is -ve & vice versa
			String position = translate(currentPosition, 0, (i * armyType));
			if (!isOutOfBounds(position) && ChessTiles.getSquareStatus(this,
					position) == Tile.HAS_NO_PIECE) {
				availableMoves.add(new Move(this, position));
			} else {
				break;
			}
		}

		// if has enemy on the left , the it's available position
		String position = translate(currentPosition, (minStep * armyType),
				-minStep * -armyType);
		if (!isOutOfBounds(position) && ChessTiles.getSquareStatus(this,
				position) == Tile.HAS_ENEMY) {
			availableMoves.add(new Move(this, position));
		}

		// if has enemy on the right , the it's available position
		position = translate(currentPosition, minStep * -armyType,
				(minStep * armyType));
		if (!isOutOfBounds(position) && ChessTiles.getSquareStatus(this,
				position) == Tile.HAS_ENEMY) {
			availableMoves.add(new Move(this, position));
		}
	}

}
