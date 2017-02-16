package pieces;

import java.awt.Image;
import java.util.Map;

import chessBoard.Move;
import chessBoard.Tile;

public class King extends Piece {

	public King(String initialPosition, int armyType,
			Map<String, Tile> chesschessBoard, Image pieceImage) {
		super(initialPosition, armyType, chesschessBoard, pieceImage);
		pieceValue = 10000;
	}

	@Override
	public void setLegalMoves() {
		availableMoves.clear();

		final int movementFactor = 1;

		// Available points : all the the squares around the king
		for (int x = -(movementFactor); x <= movementFactor; x++) {
			for (int y = -(movementFactor); y <= movementFactor; y++) {
				String position = translate(currentPosition, x, y);
				// The square available must not be out of bounds
				// if the square is free or has an enemy (hasn't an ally
				// piece) , then we can add this position
				if (!isOutOfBounds(position)
						&& getSquareStatus(position) != Tile.HAS_ALLY) {
					availableMoves.add(new Move(chessBoard, this, position));
				}
			}
		}

	}
	
	public boolean isChecked() {
		
		for (String tilePosition : chessBoard.keySet()) {
			if (chessBoard.get(tilePosition).hasPiece()) {
				Piece piece = chessBoard.get(tilePosition).getPiece();
				
				// if the piece is enemy and has the king's position in its available positions , then the king is checked
				if (piece.getArmyType() != this.armyType && piece.hasMoveTo(currentPosition)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// Stalemate is when the king is not checked and there is no legal move to any piece
	public boolean isStalemate() {
		if (!isChecked()) {
			
			for (String tilePosition : chessBoard.keySet()) {
				if (chessBoard.get(tilePosition).hasPiece()) {
					Piece piece = chessBoard.get(tilePosition).getPiece();
					// if there is at least one piece in this army has at least one legal move , then it is not a stalemate
					if (piece.getArmyType() == this.armyType && piece.getLegalMoves().size() != 0) {
						return false;
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean isCheckmate() {
		return false;
	}

}
