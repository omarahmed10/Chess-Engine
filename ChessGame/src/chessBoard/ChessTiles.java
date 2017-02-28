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
	final static int arr[] = { 8, 7, 6, 5, 4, 3, 2, 1 };
	private static Map<String, Tile> allTiles = new HashMap<String, Tile>();

	public static void createSkeletonBoard(ChessBoard Parent,
			Player whitePlayer, Player blackPlayer) {
		for (int i = 65, status = 1; i <= 72; i++) {
			if (status == 1)
				status = 2;
			else
				status = 1;
			for (int j = 0; j < 8; j++) {
				String s = (char) i + "" + arr[j];
				int x = ChessBoard.START_POINT.x + Tile.TILEWIDTH * (i - 65);
				int y = ChessBoard.START_POINT.y + Tile.TILEWIDTH * j;
				allTiles.put(s, new Tile(Parent, new Point(x, y), s, status));
				if (status == 1)
					status = 2;
				else
					status = 1;
			}
		}
		createWhitePieces(whitePlayer);
		createBlackPieces(blackPlayer);
		setAvailablePositions(allTiles);
	}

	/*
	 * there are two piece of the same name for the same player so the name will
	 * be given a number to be indicated with..
	 */

	private static void createWhitePieces(Player whitePlayer) {
		String whitePiece = "white";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 2;
			Tile parent = allTiles.get(s);
			Piece p = new Pawn(parent.getPosition(), Piece.WHITE_ARMY,
					parent.getCoordinate(),
					ImageLoader.loadImage(whitePiece + "Pawn.png").getImage());
			parent.setPiece(p);
			whitePlayer.addPiece(p);
		}
		createPiece("A1", Piece.WHITE_ARMY, whitePiece, "Rock", whitePlayer);
		createPiece("H1", Piece.WHITE_ARMY, whitePiece, "Rock", whitePlayer);
		createPiece("B1", Piece.WHITE_ARMY, whitePiece, "Knight", whitePlayer);
		createPiece("G1", Piece.WHITE_ARMY, whitePiece, "Knight", whitePlayer);
		createPiece("C1", Piece.WHITE_ARMY, whitePiece, "Bishop", whitePlayer);
		createPiece("F1", Piece.WHITE_ARMY, whitePiece, "Bishop", whitePlayer);
		createPiece("D1", Piece.WHITE_ARMY, whitePiece, "King", whitePlayer);
		createPiece("E1", Piece.WHITE_ARMY, whitePiece, "Queen", whitePlayer);
	}

	private static void createBlackPieces(Player blackPlayer) {
		String blackPiece = "black";
		for (int i = 0; i < 8; i++) {
			String s = (char) (65 + i) + "" + 7;
			Tile parent = allTiles.get(s);
			Piece p = new Pawn(parent.getPosition(), Piece.BLACK_ARMY,
					parent.getCoordinate(),
					ImageLoader.loadImage(blackPiece + "Pawn.png").getImage());
			parent.setPiece(p);
			blackPlayer.addPiece(p);
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
			p = new Rook(parent.getPosition(), army, parent.getCoordinate(),
					ImageLoader.loadImage(pieceType + pieceName + ".png")
							.getImage());
			parent.setPiece(p);
			player.addPiece(p);
		} else if (position == "B8" || position == "B1" || position == "G8"
				|| position == "G1") {
			p = new Knight(parent.getPosition(), army, parent.getCoordinate(),
					ImageLoader.loadImage(pieceType + pieceName + ".png")
							.getImage());
			parent.setPiece(p);
			player.addPiece(p);
		} else if (position == "F8" || position == "F1" || position == "C8"
				|| position == "C1") {
			p = new Bishop(parent.getPosition(), army, parent.getCoordinate(),
					ImageLoader.loadImage(pieceType + pieceName + ".png")
							.getImage());
			parent.setPiece(p);
			player.addPiece(p);
		} else if (position == "D8" || position == "D1") {
			p = new King(parent.getPosition(), army, parent.getCoordinate(),
					ImageLoader.loadImage(pieceType + pieceName + ".png")
							.getImage());
			parent.setPiece(p);
			player.addPiece(p);
			player.setKing(p);
		} else if (position == "E8" || position == "E1") {
			p = new Queen(parent.getPosition(), army, parent.getCoordinate(),
					ImageLoader.loadImage(pieceType + pieceName + ".png")
							.getImage());
			parent.setPiece(p);
			player.addPiece(p);
		}

	}

	public static void setAvailablePositions(Map<String, Tile> map) {
		for (String position : map.keySet()) {
			Piece p = map.get(position).getPiece();
			if (p != null)
				p.setLegalMoves();
		}
	}

	public static Map<String, Tile> getBoardTiles() {
		return allTiles;
	}

	public void setNewBoard(Map<String, Tile> boardMap) {
		allTiles = boardMap;
	}

	public static int getTileStatus(Piece requiredToMove, String squarePosition,
			Map<String, Tile> map) {
		Tile tile = map.get(squarePosition);
		Piece piece = tile.getPiece();
		if (piece == null) {
			return Tile.HAS_NO_PIECE;
		}
		// System.out.println(this.armyType + " " + piece);

		// if the piece has the same color of "this" piece
		if (requiredToMove.getArmyType() == piece.getArmyType()) {
			return Tile.HAS_ALLY;
		}

		// if the piece has different color of "this" piece
		else {
			return Tile.HAS_ENEMY;
		}
		// if object is not a piece , then it's a free square
	}

	public static int getSquareStatus(Piece requiredToMove,
			String squarePosition) {
		return getTileStatus(requiredToMove, squarePosition, allTiles);
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
