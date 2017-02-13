package chessBoard;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import pieces.Piece;
import player.Player;

public class ChessBoard {

	public final static Point startPoint = new Point(0, Piece.pieceHieght);
	private Map<String, Tile> tilesMap;
	private Piece currentPiece;
	private final Player whitePlayer;
	private final Player blackPlayer;
	private Grave whiteGraveYard, blackGraveYard;
	/*
	 * if playerTurn is (1) then it is whitePlayer turn if (-1) then it is
	 * blackPlayer turn
	 */
	private int playerTurn;
	private JPanel guiBoard;

	public ChessBoard(JPanel guiBoard) {
		whitePlayer = new Player(Piece.WHITE_ARMY);
		blackPlayer = new Player(Piece.BLACK_ARMY);
		// whitePlayer start the game.
		playerTurn = 1;
		this.guiBoard = guiBoard;
		//////////////////////////////
		ChessTiles.createSkeletonBoard(this, whitePlayer, blackPlayer);
		tilesMap = ChessTiles.getBoardTiles();
		//////////////////////////////
		blackGraveYard = new Grave(guiBoard.getX(), guiBoard.getY());
		whiteGraveYard = new Grave(guiBoard.getX(),
				ChessBoard.startPoint.y + 8 * Tile.TILEWIDTH);
		guiBoard.add(whiteGraveYard);
		guiBoard.add(blackGraveYard);
	}

	public boolean markAvaliableTiles(Piece selectedPiece, List<String> list) {
		if (playerTurn == 1
				&& whitePlayer.getArmy().containsValue(selectedPiece)) {
			currentPiece = selectedPiece;
			for (String s : list) {
				tilesMap.get(s).setAvaliability(true);
			}
			guiBoard.repaint();
			return true;
		} else if (playerTurn == -1
				&& blackPlayer.getArmy().containsValue(selectedPiece)) {
			currentPiece = selectedPiece;
			for (String s : list) {
				tilesMap.get(s).setAvaliability(true);
			}
			guiBoard.repaint();
			return true;
		}
		return false;
	}

	public void moveSelectedPiece(String toPosition) {
		LinkedList<Piece> graveyard;
		Grave grave;
		// if white player kill a piece then it will be add in black player
		// grave yard and vice versa.
		if (playerTurn == 1) {
			grave = blackGraveYard;
			graveyard = blackPlayer.getDeadArmy();
		} else {
			grave = whiteGraveYard;
			graveyard = whitePlayer.getDeadArmy();
		}
		if (currentPiece != null) {
			int action = currentPiece.move(toPosition, graveyard);
			if (action == Piece.ATTACK || action == Piece.MOVED) {
				tilesMap.get(toPosition).setPiece(currentPiece);
				ChessTiles.setAvailablePositions();
				// switch turns.
				playerTurn *= -1;
				if (action == Piece.ATTACK) {
					grave.addDeadPiece(graveyard.getLast());
				}
				guiBoard.repaint();
			}
		}
	}

	public void addMouseListener(Tile t) {
		guiBoard.addMouseListener(t);
	}
}
