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

	protected Board() {
		setLayout(null);
		pieceMovmentSound = new PieceMovmentSound();
		new ChessBoard(this,PlayerType.HUMAN);
	}

	@Override
	protected void paintComponent(Graphics g) {
		ChessTiles.drawTiles(g);
	}

}
