package chessBoard;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import gui.ImageLoader;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import player.Player;

public class ChessTiles {

	private ChessTiles() {
	}

	/**
	 * this array is used for simplicity.
	 */
	private final static int arr[] = { 8, 7, 6, 5, 4, 3, 2, 1 };
	private final static Map<String, Tile> allTiles = new HashMap<String, Tile>();

	public static void createSkeletonBoard(ChessBoard Parent,
			Player whitePlayer, Player blackPlayer) {
		for (int i = 65, status = 1; i <= 72; i++) {
			if (status == 1)
				status = 2;
			else
				status = 1;
			for (int j = 0; j < 8; j++) {
				String s = (char) i + "" + arr[j];
				int x = ChessBoard.startPoint.x + Tile.TILEWIDTH * (i - 65);
				int y = ChessBoard.startPoint.y + Tile.TILEWIDTH * j;
				allTiles.put(s, new Tile(Parent, new Point(x, y), s, status));
				if (status == 1)
					status = 2;
				else
					status = 1;
			}
		}
		createWhitePieces(whitePlayer);
		createBlackPieces(blackPlayer);
		setAvailablePositions();
	}

	/*
	 * there are two piece of the same name for the same player so the name will
	 * be given a number to be indicated with..
	 */
	private static int pieceNo = 0;

	private static void createWhitePieces(Player whitePlayer) {
		String whitePiece = "white";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 2;
			Tile parent = allTiles.get(s);
			Piece p = new Pawn(parent.getPostion(), Piece.WHITE_ARMY, allTiles,
					ImageLoader.loadImage(whitePiece + "Pawn.png").getImage());
			parent.setPiece(p);
			whitePlayer.addPiece("pawn" + (i + 1), p);
		}
		createPiece("A1", Piece.WHITE_ARMY, whitePiece, "Rock", whitePlayer);
		createPiece("H1", Piece.WHITE_ARMY, whitePiece, "Rock", whitePlayer);
		pieceNo = 0;
		createPiece("B1", Piece.WHITE_ARMY, whitePiece, "Knight", whitePlayer);
		createPiece("G1", Piece.WHITE_ARMY, whitePiece, "Knight", whitePlayer);
		pieceNo = 0;
		createPiece("C1", Piece.WHITE_ARMY, whitePiece, "Bishop", whitePlayer);
		createPiece("F1", Piece.WHITE_ARMY, whitePiece, "Bishop", whitePlayer);
		pieceNo = 0;
		createPiece("D1", Piece.WHITE_ARMY, whitePiece, "King", whitePlayer);
		createPiece("E1", Piece.WHITE_ARMY, whitePiece, "Queen", whitePlayer);
	}

	private static void createBlackPieces(Player blackPlayer) {
		String blackPiece = "black";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 7;
			Tile parent = allTiles.get(s);
			Piece p = new Pawn(parent.getPostion(), Piece.BLACK_ARMY, allTiles,
					ImageLoader.loadImage(blackPiece + "Pawn.png").getImage());
			parent.setPiece(p);
			blackPlayer.addPiece("pawn" + (i + 1), p);
		}
		createPiece("A8", Piece.BLACK_ARMY, blackPiece, "Rock", blackPlayer);
		createPiece("H8", Piece.BLACK_ARMY, blackPiece, "Rock", blackPlayer);
		createPiece("B8", Piece.BLACK_ARMY, blackPiece, "Knight", blackPlayer);
		createPiece("G8", Piece.BLACK_ARMY, blackPiece, "Knight", blackPlayer);
		createPiece("C8", Piece.BLACK_ARMY, blackPiece, "Bishop", blackPlayer);
		createPiece("F8", Piece.BLACK_ARMY, blackPiece, "Bishop", blackPlayer);
		createPiece("D8", Piece.BLACK_ARMY, blackPiece, "King", blackPlayer);
		createPiece("E8", Piece.BLACK_ARMY, blackPiece, "Queen", blackPlayer);
	}

	private static void createPiece(String position, int army, String pieceType,
			String pieceName, Player player) {
		Tile parent = allTiles.get(position);
		Piece p;
		if (position == "A8" || position == "A1" || position == "H1"
				|| position == "H8") {
			p = new Rook(parent.getPostion(), army, allTiles, ImageLoader
					.loadImage(pieceType + pieceName + ".png").getImage());
			parent.setPiece(p);
			player.addPiece(pieceName + ++pieceNo, p);
		} else if (position == "B8" || position == "B1" || position == "G8"
				|| position == "G1") {
			p = new Knight(parent.getPostion(), army, allTiles, ImageLoader
					.loadImage(pieceType + pieceName + ".png").getImage());
			parent.setPiece(p);
			player.addPiece(pieceName + ++pieceNo, p);
		} else if (position == "F8" || position == "F1" || position == "C8"
				|| position == "C1") {
			p = new Bishop(parent.getPostion(), army, allTiles, ImageLoader
					.loadImage(pieceType + pieceName + ".png").getImage());
			parent.setPiece(p);
			player.addPiece(pieceName + ++pieceNo, p);
		} else if (position == "D8" || position == "D1") {
			p = new King(parent.getPostion(), army, allTiles, ImageLoader
					.loadImage(pieceType + pieceName + ".png").getImage());
			parent.setPiece(p);
			player.addPiece(pieceName, p);
		} else if (position == "E8" || position == "E1") {
			p = new Queen(parent.getPostion(), army, allTiles, ImageLoader
					.loadImage(pieceType + pieceName + ".png").getImage());
			parent.setPiece(p);
			player.addPiece(pieceName, p);
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
}
