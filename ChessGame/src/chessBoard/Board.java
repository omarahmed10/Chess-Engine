package chessBoard;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pieces.Piece;

public class Board {
	static final String BLACK = "**";
	static final String WHITE = "--";
	static final int BOARD_LOW_LIMIT = 0;
	static final int BOARD_HIGH_LIMIT = 8;

	private Object[][] board = new Object[BOARD_HIGH_LIMIT][BOARD_HIGH_LIMIT];
	private String[][] boardDisplay = new String[BOARD_HIGH_LIMIT][BOARD_HIGH_LIMIT];
	private List<Piece> graveyard = new ArrayList<Piece>();
	// Here , initialize the board when the pieces classes are completed ,
	// cells remain null , look below .

	/**
	 * makes the empty places in the board black or white
	 * 
	 * @param board
	 * @return edited board
	 */
	private void blackAndWhite() {
		for (int i = 0, status = 0; i < BOARD_HIGH_LIMIT; i++) {

			if (status == 0) // 1 for white and 2 for black
				status = 1;
			else
				status = 0;

			for (int j = 0; j < 8; j++) {
				if (board[i][j] == null) {
					if (status == 0) {
						board[i][j] = BLACK;
					}

					else {
						board[i][j] = WHITE;
					}
				}
				if (status == 0)
					status = 1;
				else
					status = 0;
			}
		}

	}

	/**
	 * takes string input in A5B0 form and adjust it to be in 0510 like form
	 * 
	 * @param y1,
	 *            x1, y2, x2
	 * 
	 * @return the array of these adjusted 2 points
	 */
	public Point[] adjustPoints(String userInput) { // from A0B1
													// to 0011

		char y1 = userInput.charAt(0), y2 = userInput.charAt(2);

		int x1 = Integer.valueOf(userInput.substring(1, 2)),
				x2 = Integer.valueOf(userInput.substring(3));

		Point[] fromTo = new Point[2];

		fromTo[0] = new Point(Math.abs(x1 - 8), y1 - 65);
		fromTo[1] = new Point(Math.abs(x2 - 8), y2 - 65);

		return fromTo;
	}

	/**
	 * takes string input and check if the points are out of bounds
	 * 
	 * @param userInput
	 * 
	 * @return a boolean value
	 */
	public boolean outOfBounds(String userInput) {

		Point[] fromTo = adjustPoints(userInput);

		for (int i = 0; i < 2; i++) {
			if (fromTo[i].x > 8 || fromTo[i].x < 0)
				return true;

			if (fromTo[i].y > 8 || fromTo[i].y < 0)
				return true;
		}

		return false;
	}

	/**
	 * make the initial board of the game
	 * 
	 * @return boardSkeleton a 2D object array
	 */
	// public void drawSkeleton() {
	//
	// // white pawns at row 6
	// for (int y = 0; y < 8; y++) {
	// board[6][y] = new Pawn(new Point(6, y), Piece.WHITE_ARMY, board,null);
	// }
	// // black pawns at row 1
	// for (int y = 0; y < 8; y++) {
	// board[1][y] = new Pawn(new Point(1, y), Piece.BLACK_ARMY, board,null);
	// }
	//
	// // white rooks at (7,0) & (7,7)
	// board[7][0] = new Rook(new Point(7, 0), Piece.WHITE_ARMY, board,null);
	// board[7][7] = new Rook(new Point(7, 7), Piece.WHITE_ARMY, board,null);
	// // black rooks at (0,0) & (0,7)
	// board[0][0] = new Rook(new Point(0, 0), Piece.BLACK_ARMY, board,null);
	// board[0][7] = new Rook(new Point(0, 7), Piece.BLACK_ARMY, board,null);
	//
	// // white knights at (7,1) & (7,6)
	// board[7][1] = new Knight(new Point(7, 1), Piece.WHITE_ARMY, board,null);
	// board[7][6] = new Knight(new Point(7, 6), Piece.WHITE_ARMY, board,null);
	// // black knights at (0,1) & (0,6)
	// board[0][1] = new Knight(new Point(0, 1), Piece.BLACK_ARMY, board,null);
	// board[0][6] = new Knight(new Point(0, 6), Piece.BLACK_ARMY, board,null);
	//
	// // white bishops at (7,2) & (7,5)
	// board[7][2] = new Bishop(new Point(7, 2), Piece.WHITE_ARMY, board,null);
	// board[7][5] = new Bishop(new Point(7, 5), Piece.WHITE_ARMY, board,null);
	// // black bishops at (0,2) & (0,5)
	// board[0][2] = new Bishop(new Point(0, 2), Piece.BLACK_ARMY, board,null);
	// board[0][5] = new Bishop(new Point(0, 5), Piece.BLACK_ARMY, board,null);
	//
	// // white queen
	// board[7][3] = new Queen(new Point(7, 3), Piece.WHITE_ARMY, board,null);
	// // black queen
	// board[0][3] = new Queen(new Point(0, 3), Piece.BLACK_ARMY, board,null);
	//
	// // white king
	// board[7][4] = new King(new Point(7, 4), Piece.WHITE_ARMY, board,null);
	// // black king
	// board[0][4] = new King(new Point(0, 4), Piece.BLACK_ARMY, board,null);
	//
	// blackAndWhite();
	//
	// for (int i = 0; i < BOARD_HIGH_LIMIT; i++) {
	// for (int j = 0; j < BOARD_HIGH_LIMIT; j++) {
	// try {
	//
	// Piece piece = (Piece) board[i][j];
	//
	// piece.setAvailablePositions(board);
	// }
	// // if object is not a piece , then it's a free square
	// catch (ClassCastException e) {
	//
	// }
	// }
	// }
	// }

	/*
	 * board 2D object array and draw a 2d string array
	 * 
	 * @param boardSkeleton the 2D object array
	 * 
	 * @return board the 2D string array
	 */
	// public void drawBoard() {
	//
	// blackAndWhite();
	//
	// for (int x = 0; x < 8; x++) {
	// for (int y = 0; y < 8; y++) {
	// Object square = board[x][y];
	// String squareClass = square.getClass().getSimpleName();
	//
	// switch (squareClass) {
	//
	// case "String":
	// String blackOrWhite = (String) square;
	// boardDisplay[x][y] = blackOrWhite;
	// break;
	//
	// case "Pawn":
	// Piece pawn = (Piece) square;
	// if (pawn.getArmyType() == Piece.WHITE_ARMY)
	// boardDisplay[x][y] = "wP";
	// else
	// boardDisplay[x][y] = "bP";
	// break;
	//
	// case "Rook":
	// Piece rook = (Piece) square;
	// if (rook.getArmyType() == Piece.WHITE_ARMY)
	// boardDisplay[x][y] = "wR";
	// else
	// boardDisplay[x][y] = "bR";
	// break;
	//
	// case "Bishop":
	// Piece bishop = (Piece) square;
	// if (bishop.getArmyType() == Piece.WHITE_ARMY)
	// boardDisplay[x][y] = "wB";
	// else
	// boardDisplay[x][y] = "bB";
	// break;
	//
	// case "Knight":
	// Piece knight = (Piece) square;
	// if (knight.getArmyType() == Piece.WHITE_ARMY)
	// boardDisplay[x][y] = "wN";
	// else
	// boardDisplay[x][y] = "bN";
	// break;
	//
	// case "Queen":
	// Piece queen = (Piece) square;
	// if (queen.getArmyType() == Piece.WHITE_ARMY)
	// boardDisplay[x][y] = "wQ";
	// else
	// boardDisplay[x][y] = "bQ";
	// break;
	//
	// case "King":
	// Piece king = (Piece) square;
	// if (king.getArmyType() == Piece.WHITE_ARMY)
	// boardDisplay[x][y] = "wK";
	// else
	// boardDisplay[x][y] = "bK";
	// break;
	//
	// }
	// }
	// }
	//
	// }

	// public boolean makeMove(Point from, Point to) {
	// try {
	// if (((Piece) board[from.x][from.y]).move(to, board, graveyard)) {
	// board[to.x][to.y] = board[from.x][from.y];
	// board[from.x][from.y] = null;
	// blackAndWhite();
	//
	// return true;
	// }
	// return false;
	// }
	//
	// catch (Exception e) {
	// System.out.println("\nWrong input\n");
	// return false;
	// }
	//
	// }

	public void displayBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(boardDisplay[i][j]);
				if (j < 7) {
					System.out.print(" ");
				}
			}
			if (i < 7) {
				System.out.println();
			}
		}
		System.out.println();
	}

	public void welcomeMessage() {
		System.out.println(
				"Welcome to my chess game\nIt's a human vs human game");
		System.out.println("Enjoy\n\n");
	}

	public void turnMessage(boolean playerTurn) {
		String army = "black";
		if (playerTurn)
			army = "white";

		System.out.println("It's " + army + " turn");
	}

	private static Scanner input;

	public static void main(String[] args) {
		input = new Scanner(System.in);
		Board board = new Board();
		board.welcomeMessage();

		// Object[][] boardSkeleton = drawSkeleton();
		// String[][] board = drawBoard(boardSkeleton);
		// board.drawSkeleton();

		boolean playerTurn = true; // true if white's turn and vice versa
		boolean gameEnd = false;
		String userInput = "";

		while (!gameEnd) {
			board.turnMessage(playerTurn);

			// board.drawBoard();
			board.displayBoard();

			userInput = input.next();

			// System.out.println(board.adjustPoints(userInput)[0] + " " +
			// board.adjustPoints(userInput)[1]);

			if (userInput.length() == 4 && !board.outOfBounds(userInput)) {
				/*
				 * System.out.println("\n\n" + ((Piece)
				 * board.board[board.adjustPoints(userInput)[0].x][board.
				 * adjustPoints(userInput)[0].y]) .getAvailablePositions() +
				 * "\n\n");
				 */

				// if (board.makeMove(board.adjustPoints(userInput)[0],
				// board.adjustPoints(userInput)[1])) {
				// if (playerTurn) {
				// playerTurn = false;
				// } else {
				// playerTurn = true;
				// }
				// }

				/*
				 * System.out.println("\n\n" + ((Piece)
				 * board.board[board.adjustPoints(userInput)[1].x][board.
				 * adjustPoints(userInput)[1].y]) .getAvailablePositions() +
				 * "\n\n");
				 */
			}

		}

	}
}
