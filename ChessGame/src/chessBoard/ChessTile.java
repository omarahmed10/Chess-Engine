package chessBoard;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import gui.ChessBoard;
import gui.ImageLoader;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class ChessTile {

	private ChessTile() {
	}

	private final static Point startPoint = new Point(50, 50);
	/**
	 * this array is used for simplicity.
	 */
	private final static int arr[] = { 8, 7, 6, 5, 4, 3, 2, 1 };
	private final static Map<String, Tile> allTiles = new HashMap<String, Tile>();

	public static void createSkeletonBoard(ChessBoard Parent) {
		for (int i = 65, status = 1; i <= 72; i++) {
			if (status == 1)
				status = 2;
			else
				status = 1;
			for (int j = 0; j < 8; j++) {
				String s = (char) i + "" + arr[j];
				int x = startPoint.x + Tile.TILEWIDTH * (i - 65);
				int y = startPoint.y + Tile.TILEWIDTH * j;
				allTiles.put(s, new Tile(Parent, new Point(x, y), s, status));
				if (status == 1)
					status = 2;
				else
					status = 1;
			}
		}
		createWhitePieces();
		createBlackPieces();
		setAvailablePositions();
	}

	private static void createWhitePieces() {
		String whitePiece = "white";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 2;
			Tile parent = allTiles.get(s);
			parent.setPiece(new Pawn(parent.getPostion(), Piece.WHITE_ARMY,
					allTiles,
					ImageLoader.loadImage(whitePiece + "Pawn.png").getImage()));
		}
		createPiece("A1", Piece.WHITE_ARMY, whitePiece + "Rock.png");
		createPiece("H1", Piece.WHITE_ARMY, whitePiece + "Rock.png");
		createPiece("B1", Piece.WHITE_ARMY, whitePiece + "Knight.png");
		createPiece("G1", Piece.WHITE_ARMY, whitePiece + "Knight.png");
		createPiece("C1", Piece.WHITE_ARMY, whitePiece + "Bishop.png");
		createPiece("F1", Piece.WHITE_ARMY, whitePiece + "Bishop.png");
		createPiece("D1", Piece.WHITE_ARMY, whitePiece + "King.png");
		createPiece("E1", Piece.WHITE_ARMY, whitePiece + "Queen.png");
	}

	private static void createBlackPieces() {
		String blackPiece = "black";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 7;
			Tile parent = allTiles.get(s);
			parent.setPiece(new Pawn(parent.getPostion(), Piece.BLACK_ARMY,
					allTiles,
					ImageLoader.loadImage(blackPiece + "Pawn.png").getImage()));
		}
		createPiece("A8", Piece.BLACK_ARMY, blackPiece + "Rock.png");
		createPiece("H8", Piece.BLACK_ARMY, blackPiece + "Rock.png");
		createPiece("B8", Piece.BLACK_ARMY, blackPiece + "Knight.png");
		createPiece("G8", Piece.BLACK_ARMY, blackPiece + "Knight.png");
		createPiece("C8", Piece.BLACK_ARMY, blackPiece + "Bishop.png");
		createPiece("F8", Piece.BLACK_ARMY, blackPiece + "Bishop.png");
		createPiece("D8", Piece.BLACK_ARMY, blackPiece + "King.png");
		createPiece("E8", Piece.BLACK_ARMY, blackPiece + "Queen.png");
	}

	private static void createPiece(String position, int army,
			String pieceName) {
		Tile parent = allTiles.get(position);
		if (position == "A8" || position == "A1" || position == "H1"
				|| position == "H8") {
			parent.setPiece(new Rook(parent.getPostion(), army, allTiles,
					ImageLoader.loadImage(pieceName).getImage()));
		} else if (position == "B8" || position == "B1" || position == "G8"
				|| position == "G1") {
			parent.setPiece(new Knight(parent.getPostion(), army, allTiles,
					ImageLoader.loadImage(pieceName).getImage()));
		} else if (position == "F8" || position == "F1" || position == "C8"
				|| position == "C1") {
			parent.setPiece(new Bishop(parent.getPostion(), army, allTiles,
					ImageLoader.loadImage(pieceName).getImage()));
		} else if (position == "D8" || position == "D1") {
			parent.setPiece(new King(parent.getPostion(), army, allTiles,
					ImageLoader.loadImage(pieceName).getImage()));
		} else if (position == "E8" || position == "E1") {
			parent.setPiece(new Queen(parent.getPostion(), army, allTiles,
					ImageLoader.loadImage(pieceName).getImage()));
		}

	}

	public static void setAvailablePositions() {
		for (int i = 65; i <= 72; i++) {
			for (int j : arr) {
				String s = (char) i + "" + j;
				Piece p = allTiles.get(s).getPiece();
				if (p != null)
					p.setAvailablePositions();
			}
		}
	}

	public static Map<String, Tile> getBoardTiles() {
		return allTiles;
	}

	public static void drawTiles(Graphics g) {
		for (int i = 65; i <= 72; i++) {
			for (int j = 0; j < 8; j++) {
				String s = (char) i + "" + (j + 1);
				allTiles.get(s).draw(g);
			}
		}
	}
	// public static void main(String[] args) {
	// System.out.println(ChessTile.allTiles);
	// sysout
	// }
}
