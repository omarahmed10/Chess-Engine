package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chessBoard.Tile;

public abstract class Piece implements Cloneable{

	public final static int WHITE_ARMY = -1;
	public final static int BLACK_ARMY = 1;
	protected final static int HAS_NO_PIECE = 3;
	protected final static int HAS_ENEMY = 4;
	protected final static int HAS_ALLY = 5;
	protected final static String BOARD_LOW_LIMIT = "A1";
	protected final static String BOARD_HIGH_LIMIT = "H8";
	public final static int pieceWidth = Tile.TILEWIDTH - 25;
	public final static int pieceHieght = Tile.TILEWIDTH - 15;
	public final static int MOVED = 0;
	public final static int ATTACK = 1;
	protected int armyType;
	protected String currentPosition;
	protected List<String> availablePositions;
	protected Image myImage;
	protected Map<String, Tile> chessBoard;

	public Piece(String initialPosition, int armyType,
			Map<String, Tile> chesschessBoard, Image pieceImage) {
		if (armyType != WHITE_ARMY && armyType != BLACK_ARMY) {
			throw new RuntimeException("Error , -1 for white & 1 for black");
		}
		//
		currentPosition = initialPosition;
		this.armyType = armyType;
		availablePositions = new ArrayList<String>();
		this.myImage = pieceImage;
		this.chessBoard = chesschessBoard;
		if (isOutOfBounds(initialPosition)) {
			throw new RuntimeException(
					"The point is out of the chessBoard's bounds");
		}
		// setAvailablePositions(chessBoard);
	}

	public int move(String toPosition, List<Piece> graveyard) {
		int ans = -1;
		if (!isDead()) {

			if (availablePositions.contains(toPosition)) {

				// if this position has an enemy , then we send it to the grave
				// yard
				if (getSquareStatus(toPosition) == HAS_ENEMY) {
					chessBoard.get(toPosition).getPiece()
							.sendToGraveyard(graveyard);
					ans = ATTACK;
				} else {
					ans = MOVED;
				}
				chessBoard.get(currentPosition).setPiece(null);
				currentPosition = toPosition;
				// setAvaliablePositions must be called for all pieces.
				// setAvailablePositions();
				// move is completed
			}

		}
		// move isn't completed
		return ans;
	}

	public abstract void setAvailablePositions();

	public List<String> getAvailablePositions() {
		return availablePositions;
	}

	public String getPosition() {
		return currentPosition;
	}

	public void sendToGraveyard(List<Piece> graveyard) {
		this.currentPosition = null;
		this.availablePositions = null;
		graveyard.add(this);
	}

	public boolean isDead() {
		return this.currentPosition == null && this.availablePositions == null;
	}

	public boolean isThreatened(List<Point> attackerAvailablePositions) {
		return attackerAvailablePositions.contains(currentPosition);
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

	public int getSquareStatus(String squarePosition) {
		Tile tile = chessBoard.get(squarePosition);
		Piece piece = tile.getPiece();
		if (piece == null) {
			return HAS_NO_PIECE;
		}
		// System.out.println(this.armyType + " " + piece);

		// if the piece has the same color of "this" piece
		if (this.armyType == piece.armyType) {
			return HAS_ALLY;
		}

		// if the piece has different color of "this" piece
		else {
			return HAS_ENEMY;
		}
		// if object is not a piece , then it's a free square
	}

	protected String translate(String currPosition, int dx, int dy) {
		int x = (int) currPosition.charAt(0) + dx;
		int y = Integer.parseInt(currentPosition.charAt(1) + "") - dy;
		String toPosition = (char) x + "" + y;
		return toPosition;
	}

	private Point graveCoordinate;

	public void addGraveCoordinate(Point x) {
		graveCoordinate = x;
		System.out.println(x);
	}

	public void draw(Graphics g) {
		if (!isDead()) {
			Point position = chessBoard.get(currentPosition).getCoordinate();
			g.drawImage(myImage, position.x + 10, position.y + 5,
					pieceWidth, pieceHieght, null);
		} else {
			System.out.println(graveCoordinate.x + " " + graveCoordinate.y + " "
					+ pieceWidth + " " + pieceHieght);
			g.drawImage(myImage, graveCoordinate.x, graveCoordinate.y,
					pieceWidth, pieceHieght, null);
		}
	}
	
	public void setAvailablePositions (List defenders) {
	}
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
