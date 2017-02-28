package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import chessBoard.ChessBoard;
import chessBoard.ChessTiles;
import player.PlayerType;

public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static PieceMovmentSound pieceMovmentSound;
	private ChessBoard chessBoard;

	protected Board() {
		setLayout(null);
		pieceMovmentSound = new PieceMovmentSound();
		chessBoard = new ChessBoard(this, PlayerType.COMPUTER);
	}

	@Override
	public void paintComponent(Graphics g) {
		ChessTiles.drawTiles(g);
	}

}
