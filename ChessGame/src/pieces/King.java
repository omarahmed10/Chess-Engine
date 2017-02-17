package pieces;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chessBoard.Tile;

public class King extends Piece {

	public King(String initialPosition, int armyType, Map<String, Tile> chesschessBoard, Image pieceImage) {
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
				if (!isOutOfBounds(position) && getSquareStatus(position) != HAS_ALLY) {
					availablePositions.add(position);
				}
			}
		}

	}

	public boolean isChecked() {

		for (String tilePosition : chessBoard.keySet()) {
			if (chessBoard.get(tilePosition).hasPiece()) {
				Piece piece = chessBoard.get(tilePosition).getPiece();

				// if the piece is enemy and has the king's position in its
				// available positions , then the king is checked
				if (piece.getArmyType() != this.armyType
						&& piece.getAvailablePositions().contains(this.currentPosition)) {
					return true;
				}
			}
		}

		return false;
	}

	// Stalemate is when the king is not checked and there is no legal move to
	// any piece
	public boolean isStalemate() {
		if (!isChecked()) {

			for (String tilePosition : chessBoard.keySet()) {
				if (chessBoard.get(tilePosition).hasPiece()) {
					Piece piece = chessBoard.get(tilePosition).getPiece();
					// if there is at least one piece in this army has at least
					// one legal move , then it is not a stalemate
					if (piece.getArmyType() == this.armyType && piece.getAvailablePositions().size() != 0) {
						return false;
					}
				}
			}

			return true;
		}

		return false;
	}

	public boolean isCheckmate(List<Piece> checkers) {
		
		// In this copy , the contents of the original board are the same of the copy
		// I want to make method to iterate and make copy of each value
		Map<String, Tile> boardCopy = new HashMap<>(chessBoard);
		
		
		List<Piece> graveyardCopy = new ArrayList<Piece>();
		Piece checkerCopy;

		if (isChecked()) {
			for (Piece checker : checkers) {
				try {
					checkerCopy = (Piece) checker.clone();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (String tilePosition : chessBoard.keySet()) {
					if (chessBoard.get(tilePosition).hasPiece()
							&& chessBoard.get(tilePosition).getPiece().getArmyType() == this.getArmyType()) {

						Piece defender = chessBoard.get(tilePosition).getPiece();

						if (defender.getAvailablePositions().contains(checker.getPosition())) {
							defender.move(checker.getPosition(), graveyardCopy);
							//if ()
						}

					}
				}
			}

			return true;
		}

		return false;
	}

}
