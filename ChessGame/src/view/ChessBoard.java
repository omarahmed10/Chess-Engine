package view;

import java.awt.Graphics;
import javax.swing.JPanel;
import chess.ChessTile;

public class ChessBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ChessBoard() {
		ChessTile.createSkeletonBoard(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		ChessTile.drawTiles(g);
	}
}
