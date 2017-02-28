package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import chessBoard.Move;
import chessBoard.Tile;

public abstract class Piece implements Cloneable {

	public final static int WHITE_ARMY = -1;
	public final static int BLACK_ARMY = 1;
	protected final static String BOARD_LOW_LIMIT = "A1";
	protected final static String BOARD_HIGH_LIMIT = "H8";
	public final static int PIECE_WIDTH = Tile.TILEWIDTH - 25;
	public final static int PIECE_HEIGHT = Tile.TILEWIDTH - 15;
	protected int armyType;
	protected String currentPosition;
	protected List<Move> availableMoves;
	private Point tileCoordinate;
	protected Image myImage;
	protected int pieceValue;

	public Piece(String initialPosition, int armyType, Point myCoordinate,
			Image pieceImage) {
		if (armyType != WHITE_ARMY && armyType != BLACK_ARMY) {
			throw new RuntimeException("Error , -1 for white & 1 for black");
		}
		//
		currentPosition = initialPosition;
		this.tileCoordinate = myCoordinate;
		this.armyType = armyType;
		availableMoves = new ArrayList<Move>();
		this.myImage = pieceImage;
		if (isOutOfBounds(initialPosition)) {
			throw new RuntimeException(
					"The point is out of the chessBoard's bounds");
		}
		// setAvailablePositions(chessBoard);
	}


	public abstract void setLegalMoves();

	public List<Move> getLegalMoves() {
		return availableMoves;
	}

	public String getPosition() {
		return currentPosition;
	}

	public void setPosition(String position, Point newCoordinates) {
		tileCoordinate = newCoordinates;
		currentPosition = position;
	}

	public void sendToGraveyard() {
		this.currentPosition = null;
		this.availableMoves = null;
	}

	public boolean isDead() {
		return this.currentPosition == null && this.availableMoves == null;
	}

	public void awake(String position) {
		this.currentPosition = position;
	}

	public int getArmyType() {
		return armyType;
	}

	public boolean isOutOfBounds(String testedPosition) {
		if (testedPosition.length() > 2) {
			return true;
		}
		int x = (int) testedPosition.charAt(0);
		int y = Integer.parseInt(testedPosition.charAt(1) + "");
		return x > 72 || x < 65 || y < 1 || y > 8;
	}


	protected String translate(String currPosition, int dx, int dy) {
		int x = (int) currPosition.charAt(0) + dx;
		int y = Integer.parseInt(currentPosition.charAt(1) + "") - dy;
		String toPosition = (char) x + "" + y;
		return toPosition;
	}

	public int getPieceValue() {
		return pieceValue;
	}

	public Image getImage() {
		return myImage;
	}

	public void draw(Graphics g) {
		if (!isDead()) {
			g.drawImage(myImage, tileCoordinate.x + 10, tileCoordinate.y + 5,
					PIECE_WIDTH, PIECE_HEIGHT, null);
		} else {
			System.out.println("Draw in grave: ");
			// g.drawImage(myImage, graveCoordinate.x, graveCoordinate.y,
			// pieceWidht, pieceHieght, null);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Move hasMoveTo(String position) {
		if (getLegalMoves() != null) {
			for (Move m : getLegalMoves()) {
				if (m.getToPosition().equals(position)) {
					return m;
				}
			}
		}

		return null;
	}

}
