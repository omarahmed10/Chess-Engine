package chess;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

	protected final static int WHITE_ARMY = -1;
	protected final static int BLACK_ARMY = 1;
	protected final static int BOARD_LOW_LIMIT = 0;
	protected final static int BOARD_HIGH_LIMIT = 8;
	protected final static int HAS_NO_PIECE = 3;
	protected final static int HAS_ENEMY = 4;
	protected final static int HAS_ALLY = 5;

	protected int armyType;
	protected Point currentPosition;
	protected List<Point> availablePositions;
	protected Image myImage;

	public Piece(Point initialPosition, int armyType, Object[][] board,
			Image pieceImage) {
//		if (armyType != WHITE_ARMY && armyType != BLACK_ARMY) {
//			throw new RuntimeException("Error , -1 for white & 1 for black");
//		}
//
//		if (isOutOfBounds(initialPosition)) {
//			throw new RuntimeException(
//					"The point is out of the board's bounds");
//		}
		currentPosition = initialPosition;
		this.armyType = armyType;
		availablePositions = new ArrayList<Point>();
		this.myImage = pieceImage;
		// setAvailablePositions(board);
	}

	public boolean move(Point toPosition, Object[][] board,
			List<Piece> graveyard) {
		if (!isDead()) {

			if (availablePositions.contains(toPosition)) {

				// if this position has an enemy , then we send it to the grave
				// yard
				if (getSquareStatus(toPosition, board) == HAS_ENEMY) {
					((Piece) board[toPosition.x][toPosition.y])
							.sendToGraveyard(graveyard);
				}

				currentPosition = toPosition;
				setAvailablePositions(board);
				// move is completed
				return true;
			}

		}
		// move isn't completed
		return false;
	}

	protected abstract void setAvailablePositions(Object[][] board);

	public List<Point> getAvailablePositions() {
		return availablePositions;
	}

	public Point getPosition() {
		return currentPosition;
	}

	public void sendToGraveyard(List<Piece> graveyard) {
		currentPosition = null;
		availablePositions = null;
		graveyard.add(this);
	}

	public boolean isDead() {
		return currentPosition == null && availablePositions == null;
	}

	public boolean isThreatened(List<Point> attackerAvailablePositions) {
		return attackerAvailablePositions.contains(currentPosition);
	}

	public int getArmyType() {
		return armyType;
	}

	public boolean isOutOfBounds(Point testedPoint) {

		return (testedPoint.x >= BOARD_HIGH_LIMIT
				|| testedPoint.x < BOARD_LOW_LIMIT)
				|| (testedPoint.y >= BOARD_HIGH_LIMIT
						|| testedPoint.y < BOARD_LOW_LIMIT);
	}

	public int getSquareStatus(Point squarePosition, Object[][] board) {
		try {

			Piece piece = (Piece) board[squarePosition.x][squarePosition.y];

			// System.out.println(this.armyType + " " + piece);

			// if the piece has the same color of "this" piece
			if (this.armyType == piece.armyType) {
				return HAS_ALLY;
			}

			// if the piece has different color of "this" piece
			else {
				return HAS_ENEMY;
			}
		}
		// if object is not a piece , then it's a free square
		catch (ClassCastException e) {
			return HAS_NO_PIECE;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(myImage, currentPosition.x+5, currentPosition.y+5,
				Tile.TILEWIDTH-15, Tile.TILEWIDTH-15, null);
	}
	

}
