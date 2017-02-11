package gui;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import chessBoard.ChessTile;
import chessBoard.Tile;
import pieces.Piece;

public class ChessBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Tile> tilesMap;
	private Piece currentPiece;
	private List<Piece> graveyard;

	protected ChessBoard() {
		ChessTile.createSkeletonBoard(this);
		tilesMap = ChessTile.getBoardTiles();
		graveyard = new LinkedList<Piece>();
	}

	@Override
	protected void paintComponent(Graphics g) {
		ChessTile.drawTiles(g);
	}

	public void markAvaliableTiles(Piece selectedPiece, List<String> list) {
		currentPiece = selectedPiece;
		for (String s : list) {
			System.out.println(tilesMap.get(s).getPostion());
			tilesMap.get(s).setAvaliability(true);
		}
		repaint();
	}

	public void moveSelectedPiece(String toPosition) {
		if (currentPiece.move(toPosition, graveyard)) {
			tilesMap.get(toPosition).setPiece(currentPiece);
			ChessTile.setAvailablePositions();
			repaint();
		}
	}
}
