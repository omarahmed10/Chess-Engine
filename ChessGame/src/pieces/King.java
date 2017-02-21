package pieces;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chessBoard.ChessTiles;
import chessBoard.Move;
import chessBoard.Tile;
import player.Player;

public class King extends Piece {

	public King(String initialPosition, int armyType, Point myCoordinate, Image pieceImage) {
		super(initialPosition, armyType, myCoordinate, pieceImage);
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
				if (!isOutOfBounds(position) && ChessTiles.getSquareStatus(this, position) != Tile.HAS_ALLY) {
					availableMoves.add(new Move(this, position));
				}
			}
		}

	}

	public boolean isChecked(Map<String, Tile> boardMap) {

		List<Piece> checkers = getCheckers(boardMap);

		return !checkers.isEmpty();
	}

	// Stalemate is when the king is not checked and there is no legal move to
	// any piece
	public boolean isStalemate(Map<String, Tile> boardMap , Player opponentPlayer) {

		if (!isChecked(boardMap)) {

			for (String tilePosition : boardMap.keySet()) {
				if (boardMap.get(tilePosition).hasPiece()) {
					Piece piece = boardMap.get(tilePosition).getPiece();
					// if there is at least one piece in this army has at least
					// one legal move that cause no checking for this army's
					// king , then it is not a stalemate
					
					if (piece.getArmyType() == this.armyType && !piece.getLegalMoves().isEmpty()) {
						for (Move move : piece.getLegalMoves()) {
							move.doMove(opponentPlayer);

							if (!isChecked(boardMap)) {
								return false;
							} else {
								move.undoMove(opponentPlayer);
							}
						}
					}
				}
			}

			return true;
		}

		return false;
	}

	public boolean isCheckmate(Map<String, Tile> boardMap , Player opponentPlayer) {
		List<Piece> checkers = getCheckers(boardMap);

		if (isChecked(boardMap)) {
			for (Piece checker : checkers) {

				for (String tilePosition : boardMap.keySet()) {
					if (boardMap.get(tilePosition).hasPiece()
							&& boardMap.get(tilePosition).getPiece().getArmyType() == this.getArmyType()) {

						Piece defender = boardMap.get(tilePosition).getPiece();

						// if the defender can kill the checker
						Move m = defender.hasMoveTo(checker.getPosition());

						if (m != null)
							m.doMove(opponentPlayer);

						if (!isChecked(boardMap)) {
							return false;
						} else {
							m.undoMove(opponentPlayer);
						}

						// else try all moves
						for (Move move : defender.getLegalMoves()) {
							move.doMove(opponentPlayer);

							if (!isChecked(boardMap)) {
								return false;
							} else {
								move.undoMove(opponentPlayer);
							}
						}

					}
				}
			}

			return true;
		}

		return false;
	}

	private List<Piece> getCheckers(Map<String, Tile> boardMap) {
		List<Piece> checkers = new ArrayList<Piece>();

		for (String tilePosition : boardMap.keySet()) {

			if (boardMap.get(tilePosition).hasPiece()) {
				Piece piece = boardMap.get(tilePosition).getPiece();

				// if the piece is enemy and has the king's position in its
				// available positions , then the king is checked

				if (piece.getArmyType() != this.armyType && piece.hasMoveTo(getPosition()) != null) {
					checkers.add(piece);
				}
			}
		}

		return checkers;
	}

}
