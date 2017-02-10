package chess;

import java.awt.Image;
import java.awt.Point;
import java.util.Map;

public class Rook extends Piece {

	public Rook(String initialPosition, int armyType,
			Map<String, Tile> chesschessBoard, Image pieceImage) {
		super(initialPosition, armyType, chesschessBoard, pieceImage);
	}

	@Override
	protected void setAvailablePositions() {
		availablePositions.clear();
		
		int d = (int) currentPosition.charAt(0);
		int d2 = Integer.parseInt(currentPosition.charAt(1) + "");
		// all right positions
		for (int i = d - 65 + 1; i < 8; i++) {
			String position = (char) (i + 65) + "" + d2;
			if (!freeSquare(position))
				break;
		}

		// all left positions
		for (int i = d - 65 - 1; i >= 0; i--) {
			String position = (char) (i + 65) + "" + d2;
			if (!freeSquare(position))
				break;
		}
		// all up positions
		for (int j = d2 + 1; j <= 8; j++) {
			String position = (char) d + "" + j;
			if (!freeSquare(position))
				break;
		}

		// all down position
		for (int j = d2 - 1; j >= 1; j--) {
			String position = (char) d + "" + j;
			if (!freeSquare(position))
				break;
		}

	}

	private boolean freeSquare(String position) {
		// if the square is free , then add it
		if (getSquareStatus(position) == HAS_NO_PIECE) {
			availablePositions.add(position);
		}

		else {
			// if the square has an enemy piece , we add the point and stop
			// and if it's an ally , then stop
			if (getSquareStatus(position) == HAS_ENEMY) {
				availablePositions.add(position);
			}
			return true;
		}
		return false;
	}
}
