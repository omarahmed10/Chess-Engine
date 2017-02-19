package chessBoard;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import pieces.Piece;
import player.Player;
import player.PlayerType;

public class ChessBoard {

	public final static Point START_POINT = new Point(0, Piece.PIECE_HEIGHT);
	public final static int WHITE_TURN = 1, BLACK_TURN = -1;
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

	public ChessBoard(JPanel guiBoard, PlayerType opponentPlayer) {
		whitePlayer = new Player(Piece.WHITE_ARMY, PlayerType.HUMAN);
		blackPlayer = new Player(Piece.BLACK_ARMY, opponentPlayer);
		// whitePlayer start the game.
		playerTurn = WHITE_TURN;
		this.guiBoard = guiBoard;
		//////////////////////////////
		ChessTiles.createSkeletonBoard(this, whitePlayer, blackPlayer);
		tilesMap = ChessTiles.getBoardTiles();
		//////////////////////////////
		blackGraveYard = new Grave(guiBoard.getX(), guiBoard.getY());
		whiteGraveYard = new Grave(guiBoard.getX(),
				ChessBoard.START_POINT.y + 8 * Tile.TILEWIDTH);
		guiBoard.add(whiteGraveYard);
		guiBoard.add(blackGraveYard);
	}

	public boolean markAvaliableTiles(Piece selectedPiece, List<Move> list) {
		Player currentPlayer = getCurrentPlayer();
		if (currentPlayer.getArmy().contains(selectedPiece)) {
			currentPiece = selectedPiece;
			for (Move m : list) {
				tilesMap.get(m.getToPosition()).setAvaliability(true);
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
		if (playerTurn == WHITE_TURN) {
			grave = blackGraveYard;
			graveyard = blackPlayer.getDeadArmy();
		} else {
			grave = whiteGraveYard;
			graveyard = whitePlayer.getDeadArmy();
		}
		if (currentPiece != null) {
			int action = currentPiece.move(toPosition, graveyard);
			if (action == Move.ATTACK || action == Move.MOVE) {
				tilesMap.get(toPosition).setPiece(currentPiece);
				ChessTiles.setAvailablePositions();
				// switch turns.
				playerTurn *= BLACK_TURN;
				if (action == Move.ATTACK) {
					grave.addDeadPiece(graveyard.getLast());
				}
				guiBoard.repaint();
				gui.Board.pieceMovmentSound.start();
			}
		}
	}

	public List<Piece> getPlayerGrave() {
		if (playerTurn == WHITE_TURN) {
			return blackPlayer.getDeadArmy();
		} else {
			return whitePlayer.getDeadArmy();
		}
	}

	public void switchPlayers() {
		playerTurn *= BLACK_TURN;
	}

	public Map<String, Tile> getBoardMap() {
		return tilesMap;
	}

	public Player getCurrentPlayer() {
		if (playerTurn == WHITE_TURN) {
			return whitePlayer;
		} else {
			return blackPlayer;
		}
	}

	public void addMouseListener(Tile t) {
		guiBoard.addMouseListener(t);
	}
}
