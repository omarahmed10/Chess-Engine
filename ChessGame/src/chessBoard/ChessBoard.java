package chessBoard;

import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.JPanel;

import pieces.Piece;
import player.Player;
import player.PlayerType;

public class ChessBoard implements Cloneable {

	public final static Point START_POINT = new Point(0, Piece.PIECE_HEIGHT);
	public final static int WHITE_TURN = 1, BLACK_TURN = -1;
	private Player whitePlayer, blackPlayer;
	private int playerTurn;
	private Map<String, Tile> tilesMap;
	private Piece currentPiece;
	private Grave whiteGraveYard, blackGraveYard;
	private Stack<Move> history;
	private JPanel guiBoard;
	private Move currentMove;

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

	/*
	 * move function for Human Player only the mouse listener will call this
	 * one.
	 */
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
			currentMove = currentPiece.hasMoveTo(toPosition);
			if (currentMove != null) {
				currentMove.doMove(getOpponentPlayer());
				if (currentMove.isDone()) {
					ChessTiles.setAvailablePositions(tilesMap);
					// switch turns.
					switchPlayers();
					if (currentMove.isAttack()) {
						grave.addDeadPiece(graveyard.getLast());
					}
					guiBoard.repaint();

					// Adding Move after movement
					history.add(currentMove);

					gui.Board.pieceMovmentSound.start();

				}
			}
		}
	}

	public void switchPlayers() {
		playerTurn *= BLACK_TURN;
	}

	public void setBoardMap(Map<String, Tile> map) {
		tilesMap = map;
	}

	public void setPlayerTurn(int pT) {
		playerTurn = pT;
	}

	public Player getCurrentPlayer() {
		if (playerTurn == WHITE_TURN) {
			return whitePlayer;
		} else {
			return blackPlayer;
		}
	}

	public Player getOpponentPlayer() {
		if (playerTurn == WHITE_TURN) {
			return blackPlayer;
		} else {
			return whitePlayer;
		}
	}

	public Map<String, Tile> getMap() {
		return tilesMap;
	}

	private Map<String, Tile> getCopy(Map<String, Tile> originalChessBoard) {
		Map<String, Tile> chessBoardCopy = new HashMap<String, Tile>();
		copyPlayer(this.whitePlayer);
		copyPlayer(this.blackPlayer);
		
		for (String position : originalChessBoard.keySet()) {
			try {
				Tile tileCopy = (Tile) originalChessBoard.get(position).clone();
				if (tileCopy.getPiece() != null) {
					Piece pieceCopy = (Piece) tileCopy.getPiece().clone();
					if (pieceCopy.getArmyType() == Piece.WHITE_ARMY) {
						this.whitePlayer.addPiece(pieceCopy);
					} else {
						this.blackPlayer.addPiece(pieceCopy);
					}
				}
				chessBoardCopy.put(position, tileCopy);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}

		return chessBoardCopy;
	}

	private void copyEverything() {
		this.tilesMap = getCopy(this.tilesMap);
	}

	private static void copyPlayer(Player player) {
		try {
			Player playerCopy = (Player) player.clone();
			player = playerCopy;
			player.reset();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void undo() {
		history.pop();

		currentMove.undoMove(getCurrentPlayer());
	}

	public Stack<Move> getHistory() {
		return history;
	}

	public void addMouseListener(Tile t) {
		guiBoard.addMouseListener(t);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		copyEverything();
		return super.clone();
	}
}
