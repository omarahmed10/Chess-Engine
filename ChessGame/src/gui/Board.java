package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import chessBoard.ChessBoard;
import chessBoard.ChessTiles;

public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ChessBoard chessBoard;

	protected Board() {
		setLayout(null);
		chessBoard = new ChessBoard(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		ChessTiles.drawTiles(g);
	}

}
