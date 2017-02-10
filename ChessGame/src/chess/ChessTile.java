package chess;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import view.ImageLoader;

public class ChessTile {

	private ChessTile() {
	}

	private final static Point startPoint = new Point(50, 50);
	/**
	 * this array is used for simplicity.
	 */
	private final static int arr[] = { 8, 7, 6, 5, 4, 3, 2, 1 };
	private final static Map<String, Tile> allTiles = new HashMap<String, Tile>();

	public static void createSkeletonBoard(JPanel Parent) {
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
	}

	private static void createWhitePieces() {
		String whitePiece = "white";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 2;
			Tile parent = allTiles.get(s);
			parent.setPiece(new Pawn(parent.getCoordinate(), Piece.WHITE_ARMY,
					null,
					ImageLoader.loadImage(whitePiece + "Pawn.png").getImage()));
		}
		createPiece("A1", Piece.WHITE_ARMY, whitePiece + "Rock.png");
		createPiece("H1", Piece.WHITE_ARMY, whitePiece + "Rock.png");
		createPiece("B1", Piece.WHITE_ARMY, whitePiece + "Knight.png");
		createPiece("G1", Piece.WHITE_ARMY, whitePiece + "Knight.png");
		createPiece("C1", Piece.WHITE_ARMY, whitePiece + "Bishop.png");
		createPiece("F1", Piece.WHITE_ARMY, whitePiece + "Bishop.png");
		createPiece("D1", Piece.WHITE_ARMY, whitePiece + "Queen.png");
		createPiece("E1", Piece.WHITE_ARMY, whitePiece + "King.png");
	}

	private static void createBlackPieces() {
		String blackPiece = "black";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 7;
			Tile parent = allTiles.get(s);
			parent.setPiece(new Pawn(parent.getCoordinate(), Piece.BLACK_ARMY,
					null,
					ImageLoader.loadImage(blackPiece + "Pawn.png").getImage()));
		}
		createPiece("A8", Piece.BLACK_ARMY, blackPiece + "Rock.png");
		createPiece("H8", Piece.BLACK_ARMY, blackPiece + "Rock.png");
		createPiece("B8", Piece.BLACK_ARMY, blackPiece + "Knight.png");
		createPiece("G8", Piece.BLACK_ARMY, blackPiece + "Knight.png");
		createPiece("C8", Piece.BLACK_ARMY, blackPiece + "Bishop.png");
		createPiece("F8", Piece.BLACK_ARMY, blackPiece + "Bishop.png");
		createPiece("D8", Piece.BLACK_ARMY, blackPiece + "Queen.png");
		createPiece("E8", Piece.BLACK_ARMY, blackPiece + "King.png");
	}

	private static void createPiece(String position, int army,
			String pieceName) {
		Tile parent = allTiles.get(position);
		parent.setPiece(new Rook(parent.getCoordinate(), army, null,
				ImageLoader.loadImage(pieceName).getImage()));
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
	// }
}
