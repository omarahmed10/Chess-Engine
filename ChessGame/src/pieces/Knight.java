package pieces;

import java.awt.Image;
import java.util.Map;

import chessBoard.Move;
import chessBoard.Tile;

public class Knight extends Piece {

	public Knight(String initialPosition, int armyType,
			Map<String, Tile> chesschessBoard, Image pieceImage) {
		super(initialPosition, armyType, chesschessBoard, pieceImage);
		pieceValue = 320;
	}

	@Override
	public void setLegalMoves() {
		availableMoves.clear();

		final int movementFactor1 = 1;
		final int movementFactor2 = 2;

		// available points :
		// (x-1,y-2), (x-1,y+2), (x+1,y-2), (x+1,y+2)
		// (x-2,y-1), (x-2,y+1), (x+2,y-1), (x+2,y+1)
		for (int x = -(movementFactor1); x <= (movementFactor1); x += (2
				* movementFactor1)) {
			for (int y = -(movementFactor2); y <= (movementFactor2); y += (2
					* movementFactor2)) {

				// 2 symmetric positions per time
				String position = translate(currentPosition, x, y);

				// The square available must not be out of bounds
				// if the square is free or has an enemy (hasn't an ally
				// piece) , then we can add this position
				if (!isOutOfBounds(position)
						&& getSquareStatus(position) != Tile.HAS_ALLY) {
					availableMoves.add(new Move(chessBoard, this, position));
				}

				position = translate(currentPosition, y, x);

				if (!isOutOfBounds(position)
						&& getSquareStatus(position) != Tile.HAS_ALLY) {
					availableMoves.add(new Move(chessBoard, this, position));
				}

			}
		}

	}

}
